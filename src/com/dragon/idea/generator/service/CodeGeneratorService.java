package com.dragon.idea.generator.service;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.dragon.idea.generator.model.GenerateConfig;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CodeGenerator
 * @Author pengl
 * @Date 2019-05-10 10:15
 * @Description 代码生成器
 * @Version 1.0
 */
public class CodeGeneratorService {

    /**
     * @MethodName: generator
     * @Author: pengl
     * @Date: 2019-05-10 10:57
     * @Description: 代码生成器
     * @Version: 1.0
     * @Param: DataSourceConfig
     * @Return:
     **/
    public static void generator(GenerateConfig config) {
        AutoGenerator generator = new AutoGenerator();
        /**
         * 设置模版引擎
         */
        generator.setTemplateEngine(new FreemarkerTemplateEngine());

        /**
         * 全局配置
         */
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(config.getPath() + "/src/main/java");
        gc.setAuthor(System.getenv().get("USERNAME"));
        gc.setOpen(false);
        gc.setSwagger2(false);
        generator.setGlobalConfig(gc);

        /**
         * 数据源配置
         */
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(config.getUrl());
        dsc.setDriverName(config.getDriverName());
        dsc.setUsername(config.getUserName());
        dsc.setPassword(config.getPassWord());
        generator.setDataSource(dsc);

        /**
         * 包配置
         */
        PackageConfig pc = new PackageConfig();
        String pk = config.getPackageName();
        pc.setParent(pk.substring(0, pk.lastIndexOf('.')));
        pc.setModuleName(pk.substring(pk.lastIndexOf('.') + 1));
        pc.setEntity("model");
        generator.setPackageInfo(pc);

        /**
         * 自定义配置
         */
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                //TODO
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        /**
         * 自定义输出配置:定义配置会被优先输出
         * 自定义输出文件名称
         */
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return config.getPath() + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);

        /**
         * 配置模板
         */
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        generator.setTemplate(templateConfig);

        /**
         * 策略配置  : 驼峰
         */
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(config.getTableNames().split(","));
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setEntityTableFieldAnnotationEnable(true);
        generator.setStrategy(strategy);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
    }
}
