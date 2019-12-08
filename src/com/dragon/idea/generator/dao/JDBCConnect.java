package com.dragon.idea.generator.dao;

import com.alibaba.druid.util.JdbcUtils;
import com.dragon.idea.generator.model.GenerateConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @ClassName JDBCConnect
 * @Author pengl
 * @Date 2018/11/22 16:38
 * @Description JDBC Connect获取
 * @Version 1.0
 */
public class JDBCConnect {
    private String url;
    private String userName;
    private String passWord;
    private String driverClass;
    private Connection conn;

    public JDBCConnect(GenerateConfig config) {
        this.url = config.getUrl();
        this.userName = config.getUserName();
        this.passWord = config.getPassWord();
        try {
            this.driverClass = JdbcUtils.getDriverClassName(url);
        } catch (SQLException e) {
            this.driverClass = "";
        }
    }

    public Connection getConnection() {
        if(conn == null){
            try {
                Class.forName(driverClass);
                conn = DriverManager.getConnection(url, userName, passWord);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("缺少数据库连接驱动！");
            } catch (SQLException e) {
                throw new RuntimeException("数据库连接失败：" + e.getMessage());
            }
        }
        return conn;
    }

    /**
     * 释放连接 Connection
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if(conn !=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        conn = null;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
