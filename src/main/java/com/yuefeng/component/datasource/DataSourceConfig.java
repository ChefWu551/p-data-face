package com.yuefeng.component.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Driver;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

// todo: 查看和mybatisConfig有什么区别，为什么需要后置处理器才能处理循环依赖的问题
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Bean(name = "resourceLoader")
    public ResourceLoader resourceLoader() {
        return new DefaultResourceLoader();
    }

    @Bean(name = "resourcePatternResolver")
    public ResourcePatternResolver resourcePatternResolver() {
        return new PathMatchingResourcePatternResolver();
    }


    @Bean(name = "dataFaceDB", initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataFaceDB() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/interface_config?useUnicode=true&characterEncoding=utf-8");
        druidMysqlSettings(dataSource);

        return dataSource;
    }

    @Bean(name = "abcTestDB", initMethod = "init", destroyMethod = "close")
    public DruidDataSource abcTestDB() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/abc_test?useUnicode=true&characterEncoding=utf-8");
        druidMysqlSettings(dataSource);

        return dataSource;
    }

    // todo: 默认db为什么没有生效！
    @Bean(name = "dataSource")
    public DynamicDataSource dataSource(DruidDataSource dataFaceDB,
                                        DruidDataSource abcTestDB) throws Exception{

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object,Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put("dataFaceDB", dataFaceDB);
        targetDataSources.put("abcTestDB", abcTestDB);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(dataFaceDB);
        return dynamicDataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean(Environment environment,
                                                       DynamicDataSource dataSource,
                                                       ResourceLoader resourceLoader,
                                                       ResourcePatternResolver resourcePatternResolver) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.yuefeng.model");
        return sqlSessionFactoryBean;
    }

    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer(Environment environment) {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.yuefeng.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

    private void druidMysqlSettings(DruidDataSource druidDataSource) throws Exception {
        druidDataSource.setMaxActive(1000);
        druidDataSource.setInitialSize(0);
        druidDataSource.setMinIdle(0);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(100);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTimeBetweenEvictionRunsMillis(6000);
        druidDataSource.setMinEvictableIdleTimeMillis(2520000);
        druidDataSource.setRemoveAbandoned(true);
        druidDataSource.setRemoveAbandonedTimeout(18000);
        druidDataSource.setLogAbandoned(true);
        druidDataSource.setFilters("mergeStat");
        druidDataSource.setDriver(new Driver());
    }
}

