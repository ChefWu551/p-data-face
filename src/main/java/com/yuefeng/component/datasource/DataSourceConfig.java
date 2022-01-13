package com.yuefeng.component.datasource;

import com.mysql.jdbc.Driver;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.alibaba.druid.pool.DruidDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 多数据源配置
 * @Author: Wu Yuefeng
 * @Date: Created on 24/02/2018
 */

// todo: 查看和mybatisConfig有什么区别，为什么需要后置处理器才能处理循环依赖的问题
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public DataSourceConfig() {
        logger.info("正在初始化mybatis配置信息......");
    }

    @Bean(name = "resourceLoader")
    public ResourceLoader resourceLoader() {
        return new DefaultResourceLoader();
    }

    @Bean(name = "resourcePatternResolver")
    public ResourcePatternResolver resourcePatternResolver() {
        return new PathMatchingResourcePatternResolver();
    }

    /**
     * 配置数据库.
     * @return 数据连接池
     */

    @Bean(name = "dataFaceDB", initMethod = "init", destroyMethod = "close")
    @Primary
    public DruidDataSource dataFaceDB(Environment environment) throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/interface_config");
        druidMysqlSettings(dataSource);

        return dataSource;
    }

    @Bean(name = "abcTestDB", initMethod = "init", destroyMethod = "close")
    public DruidDataSource abcTestDB(Environment environment) throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/abc_test");
        druidMysqlSettings(dataSource);

        return dataSource;
    }


    @Bean(name = "dataSource")
    public DynamicDataSource dataSource(@Qualifier("dataFaceDB")DruidDataSource dataFaceDB,
                                        @Qualifier("abcTestDB")DruidDataSource abcTestDB) {

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

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManagerDss(DynamicDataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
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
