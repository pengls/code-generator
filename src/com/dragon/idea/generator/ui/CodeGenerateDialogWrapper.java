/*
 * Copyright (c) 2019. CK. All rights reserved.
 */

package com.dragon.idea.generator.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;
import javax.swing.*;
import java.awt.*;

/**
 * @ClassName: CodeGenerateDialogWrapper
 * @Author: pengl
 * @Date: 2019-12-05 16:00
 * @Description: 代码生成弹窗
 * @Version: 1.0
 **/
public class CodeGenerateDialogWrapper extends DialogWrapper {
    final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    private final Project project;
    private CodeSettingsForm contentPanel;

    public CodeGenerateDialogWrapper(@Nullable Project project) {
        super(project);
        this.project = project;

        setOKButtonText("生成代码");
        setCancelButtonText("取消");

        //this.setSize((int) (width * 0.6f), (int) (height * 0.5f));

        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        contentPanel = new CodeSettingsForm();
        contentPanel.createUI(this.project);
        return contentPanel.getContentPane();
    }

    public CodeSettingsForm getCodeSettingsForm(){
        return this.contentPanel;
    }
}
