/*
 * Copyright (c) 2019. CK. All rights reserved.
 */

package com.dragon.idea.generator.ui;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @ClassName: UiComponentFacade
 * @Author: pengl
 * @Date: 2019-12-06 11:20
 * @Description: 文件选择器
 * @Version: 1.0
 **/
public class FileChooseUi {

    private final Project project;

    private final FileEditorManager fileEditorManager;

    private FileChooseUi(Project project) {
        this.project = project;
        this.fileEditorManager = FileEditorManager.getInstance(project);
    }

    public static FileChooseUi getInstance(@NotNull Project project) {
        return new FileChooseUi(project);
    }

    public VirtualFile showSingleFolderSelectionDialog(@NotNull String title,
                                                       @Nullable VirtualFile toSelect,
                                                       @Nullable VirtualFile... roots) {
        final FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();
        descriptor.setTitle(title);
        if (null != roots) {
            descriptor.setRoots(roots);
        }
        return FileChooser.chooseFile(descriptor, project, toSelect);
    }
}
