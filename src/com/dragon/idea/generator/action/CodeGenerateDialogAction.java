package com.dragon.idea.generator.action;

import com.dragon.idea.generator.model.GenerateConfig;
import com.dragon.idea.generator.service.CodeGeneratorService;
import com.dragon.idea.generator.ui.CodeGenerateDialogWrapper;
import com.dragon.idea.generator.utils.ValidateUtils;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class CodeGenerateDialogAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        CodeGenerateDialogWrapper wrapper = new CodeGenerateDialogWrapper(project);
        boolean result = wrapper.showAndGet();
        if (result) {
            wrapper.getCodeSettingsForm().apply();
            GenerateConfig config = GenerateConfig.getInstance(project);
            if (!ValidateUtils.validateConfig(project, config)) {
                return;
            }
            CodeGeneratorService.generator(config);
            Messages.showInfoMessage(project, "代码生成成功", "Success");
        }
    }
}
