package com.dragon.idea.generator.model;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @ClassName GenerateConfig
 * @Author pengl
 * @Date 2019-12-05 15:07
 * @Description TODO
 * @Version 1.0
 */
@State(name = "GenerateConfig", storages = {@Storage("generateConfig.xml")})
public class GenerateConfig implements PersistentStateComponent<GenerateConfig> {
    public static final String MYSQL_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    /**
     * JDBC相关配置
     */
    private String url;

    private String driverName = MYSQL_DRIVER_NAME;

    private String userName;

    private String passWord;

    /**
     * 当前工程路径
     */
    private String path;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 表名，多个逗号分隔
     */
    private String tableNames;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getTableNames() {
        return tableNames;
    }

    public void setTableNames(String tableNames) {
        this.tableNames = tableNames;
    }

    @Nullable
    @Override
    public GenerateConfig getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull GenerateConfig generateConfig) {
        XmlSerializerUtil.copyBean(generateConfig, this);
    }

    @Nullable
    public static GenerateConfig getInstance(Project project) {
        GenerateConfig config =  ServiceManager.getService(project, GenerateConfig.class);
        if (config == null) {
            config = new GenerateConfig();
        }
        return config;
    }

    @Override
    public String toString() {
        return "GenerateConfig{" +
                "url='" + url + '\'' +
                ", driverName='" + driverName + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", path='" + path + '\'' +
                ", packageName='" + packageName + '\'' +
                ", tableNames='" + tableNames + '\'' +
                '}';
    }
}
