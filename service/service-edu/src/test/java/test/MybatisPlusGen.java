//package test;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author wzh
// * @date 2020/10/18 - 22:16
// */
//
//public class MybatisPlusGen {
//    public static void main(String[] args) {
//        // 代码生成器
//        AutoGenerator autoGenerator = new AutoGenerator();
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        StrategyConfig strategyConfig = new StrategyConfig();
//        PackageConfig packageConfig = new PackageConfig();
//        GlobalConfig globalConfig = new GlobalConfig();
//        // 自定义配置
//        InjectionConfig injectionConfig = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//        autoGenerator.setDataSource(dataSourceConfig);
//        autoGenerator.setStrategy(strategyConfig);
//        autoGenerator.setPackageInfo(packageConfig);
//        autoGenerator.setGlobalConfig(globalConfig);
//        autoGenerator.setCfg(injectionConfig);
//        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
//
//        // 配置数据源
//        dataSourceConfig.setDbType(DbType.MYSQL);
//        dataSourceConfig.setTypeConvert(new MySqlTypeConvert());
//        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
//        dataSourceConfig.setUrl("jdbc:mysql://47.111.28.188:3306/knife_edu?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
//        dataSourceConfig.setUsername("root");
//        dataSourceConfig.setPassword("123456");
//
//        // 数据表配置
//        strategyConfig.setCapitalMode(false);
////        strategyConfig.setSuperEntityClass(ObjectConvert.class);
//        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
//        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategyConfig.setTablePrefix("knife_");
////        strategyConfig.setInclude("qbcl_yssj_bganjyts");
//        strategyConfig.setEntityBuilderModel(true);
//        strategyConfig.setEntityLombokModel(false);
//        strategyConfig.setRestControllerStyle(true);
//        strategyConfig.setControllerMappingHyphenStyle(true);
//
//        // 配置包名
//        packageConfig.setParent("com.knife");
//        packageConfig.setEntity("domain.entity");
////        packageConfig.setController("yssj");
//        packageConfig.setService("service");
//        packageConfig.setServiceImpl("service.impl");
//        packageConfig.setMapper("mapper");
////        packageConfig.setXml("mapper/xml");
//
//        // 配置全局策略
//        globalConfig.setSwagger2(true);
//        globalConfig.setOpen(false);
//        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
//        globalConfig.setFileOverride(false);
//        globalConfig.setAuthor("Mr_W");
//        globalConfig.setEntityName("%sDO");
//        globalConfig.setMapperName("%sMapper");
////        globalConfig.setXmlName("%sMapper");
//        globalConfig.setServiceName("%sService");
//        globalConfig.setServiceImplName("%sServiceImpl");
////        globalConfig.setControllerName("%sController");
//
//
//        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return System.getProperty("user.dir") + "/src/main/resources/"+ packageConfig.getParent() +"/mapper/"
//                        + "/" + tableInfo.getMapperName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        injectionConfig.setFileOutConfigList(focList);
//
//        autoGenerator.execute();
//    }
//}
