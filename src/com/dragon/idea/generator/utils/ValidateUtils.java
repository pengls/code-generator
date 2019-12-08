/*
 * Copyright (c) 2018. CK. All rights reserved.
 */

package com.dragon.idea.generator.utils;

import com.dragon.idea.generator.model.GenerateConfig;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: ValidateUtils
 * @Author: pengl
 * @Date: 2019-12-05 15:25
 * @Description: 参数校验
 * @Version: 1.0
 **/
public class ValidateUtils {

    /**
     * @MethodName: validateJDBC
     * @Author: pengl
     * @Date: 2019-12-05 16:50
     * @Description: JDBC参数校验
     * @Version: 1.0
     * @Param:
     * @Return:
     **/
    public static boolean validateJDBC(Project project, GenerateConfig config) {
        if (StringUtils.isBlank(config.getUrl())) {
            Messages.showErrorDialog(project, "数据库连接为空.", "Error");
            return false;
        }

        if (StringUtils.isBlank(config.getUserName())) {
            Messages.showErrorDialog(project, "数据库用户名为空.", "Error");
            return false;
        }

        if (StringUtils.isBlank(config.getPassWord())) {
            Messages.showErrorDialog(project, "数据库密码为空.", "Error");
            return false;
        }
        return true;
    }


    /**
     * @MethodName: validateConfig
     * @Author: pengl
     * @Date: 2019-12-05 15:26
     * @Description: 参数校验
     * @Version: 1.0
     * @Param:
     * @Return:
     **/
    public static boolean validateConfig(Project project, GenerateConfig config) {
        if (validateJDBC(project, config)) {
            if (StringUtils.isBlank(config.getPath())) {
                Messages.showErrorDialog(project, "项目路径为空.", "Error");
                return false;
            }

            if (StringUtils.isBlank(config.getPackageName())) {
                Messages.showErrorDialog(project, "包名为空.", "Error");
                return false;
            }

            if (StringUtils.isBlank(config.getTableNames())) {
                Messages.showErrorDialog(project, "表名为空.", "Error");
                return false;
            }
        }
        return true;
    }
}
