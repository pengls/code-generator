/*
 * Copyright (c) 2018. CK. All rights reserved.
 */

package com.dragon.idea.generator.ui;

import com.dragon.idea.generator.dao.JDBCConnect;
import com.dragon.idea.generator.model.GenerateConfig;
import com.dragon.idea.generator.utils.ValidateUtils;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.apache.commons.lang3.StringUtils;
import javax.swing.*;
import java.sql.Connection;

/**
 * @ClassName: CodeSettingsForm
 * @Author: pengl
 * @Date: 2019-12-05 15:26
 * @Description: 表单
 * @Version: 1.0
 **/
public class CodeSettingsForm extends JDialog {
    private JPanel contentPane;
    private JTextField jdbcUrl;
    private JTextField userName;
    private JTextField path;
    private JPasswordField passWord;
    private JTextField packageName;
    private JTextField tableNames;
    private JButton testButton;
    private GenerateConfig config;
    private JButton pathButton;

    public CodeSettingsForm() {
        setContentPane(contentPane);
    }

    public void createUI(Project project) {
        config = GenerateConfig.getInstance(project != null ? project : ProjectManager.getInstance().getDefaultProject());
        //防止弹窗过大，截取url设置到文本框
        String url = config.getUrl();
        if(StringUtils.isNotBlank(url)){
            int ct = url.indexOf('?');
            if(ct != -1){
                url = url.substring(0, ct);
            }
        }

        this.jdbcUrl.setText(url);
        this.userName.setText(config.getUserName());
        this.passWord.setText(config.getPassWord());
        this.path.setText(config.getPath());
        this.packageName.setText(config.getPackageName());
        this.tableNames.setText(config.getTableNames());

        this.testButton.addActionListener(e -> {
            apply();
            if (!ValidateUtils.validateJDBC(project, config)) {
                return;
            }
            Connection conn = null;
            try {
                JDBCConnect jdbcConnect = new JDBCConnect(config);
                conn = jdbcConnect.getConnection();
                Messages.showInfoMessage(project, "连接成功", "OK");
            } catch (Exception ex) {
                ex.printStackTrace();
                Messages.showErrorDialog(project, ex.getMessage(), "Error");
            } finally {
                JDBCConnect.closeConnection(conn);
            }
        });

        this.pathButton.addActionListener(e -> {
            FileChooseUi uiComponentFacade = FileChooseUi.getInstance(project);
            VirtualFile baseDir = project.getBaseDir();
            VirtualFile vf = uiComponentFacade.showSingleFolderSelectionDialog("选择项目路径", baseDir, baseDir);
            if (null != vf) {
                CodeSettingsForm.this.path.setText(vf.getPath());
                apply();
            }
        });

    }

    public void apply() {
        config.setUrl(this.jdbcUrl.getText());
        config.setPassWord(new String(this.passWord.getPassword()));
        config.setUserName(this.userName.getText());
        config.setPackageName(this.packageName.getText());
        config.setTableNames(this.tableNames.getText());
        config.setPath(this.path.getText());
    }


    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

}
