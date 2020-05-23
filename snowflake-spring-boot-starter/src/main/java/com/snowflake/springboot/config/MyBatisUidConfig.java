package com.snowflake.springboot.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.snowflake.springboot.mapper.WorkerNodeMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * uid datasource
 *
 * mybatis-plus config
 * include:
 * 1、dataSource
 * 2、sqlSessionFactory
 * 3、PaginationInterceptor
 *
 * @author shenhongjun
 * @since 2020/4/16
 */
@ConditionalOnProperty(name = "spring.datasource.uid.enable",havingValue = "true")
@Configuration
@MapperScan(basePackageClasses = {WorkerNodeMapper.class},sqlSessionFactoryRef = "sqlSessionFactoryUid")
public class MyBatisUidConfig {

    /**
     * dataSource
     * @return
     */
    @ConditionalOnMissingBean(name = {"uid"})
    @Primary
    @Bean(name = "uid")
    @ConfigurationProperties(prefix = "spring.datasource.uid")
    public DataSource db1() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * sqlSessionFactory
     * @return
     * @throws Exception
     */
    @ConditionalOnMissingBean(name = {"sqlSessionFactoryUid"})
    @Bean(name = "sqlSessionFactoryUid")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("uid") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/snowflake/springboot/mapper/xml/WorkNodeMapper.xml"));

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);

        return sqlSessionFactory.getObject();
    }

    /**
     * SqlSessionTemplate
     * @param sqlSessionFactory session工程
     * @return
     */
    @ConditionalOnMissingBean(name = {"sqlSessionTemplateUid"})
    @Bean(name = "sqlSessionTemplateUid")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryUid") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
