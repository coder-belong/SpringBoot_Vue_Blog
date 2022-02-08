package com.belong.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;

public class CodeGenerator {
    public static void main(String[] args) {
        // 构建一个代码生成器对象
        AutoGenerator mpg = new AutoGenerator();

        // 1. 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");  // 获取当前IDEA的project目录

        //将代码生成到哪个目录下
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("belong");  // 新增作者注释
        gc.setOpen(false);  // 执行成功后是否打开资源文件夹
        gc.setFileOverride(false);  // 是否覆盖原有的文件
        gc.setSwagger2(true); // 实体属性 Swagger2 注解
        gc.setServiceName("%sService");  // 去除Service的I前缀
        mpg.setGlobalConfig(gc);

        // 2. 数据源配置
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setUrl("jdbc:mysql://localhost:3306/springboot_vue_blog");
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");
        mpg.setDataSource(dataSource);

        // 3. 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.belong");
        mpg.setPackageInfo(pc);

        // 4. 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 根据数据表名来生成代码，可以传入多个数据库名
        strategy.setInclude("m_blog", "m_user");
        // 数据库驼峰转下划线规则
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 实体类字段如上
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 开启驼峰命名
        strategy.setRestControllerStyle(true);
        // 接口路径采用下划线风格：localhost:8080/hello_id_2
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityLombokModel(true);  // 是否使用lombok开启注解
        strategy.setTablePrefix("m_"); // 设置数据表前缀


        // 4.1 自动填充功能(可选，也可以在数据库层实现)
        TableFill create_time = new TableFill("create_time", FieldFill.INSERT);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(create_time);
        strategy.setTableFillList(tableFills);


        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();  // 执行
    }
}
