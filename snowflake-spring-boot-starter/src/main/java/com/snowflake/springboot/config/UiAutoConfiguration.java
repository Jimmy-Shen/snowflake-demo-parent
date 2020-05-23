package com.snowflake.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.snowflake.spring.UidGenerator;
import com.snowflake.spring.impl.CachedUidGenerator;
import com.snowflake.springboot.DisposableWorkerIdAssigner;
import com.snowflake.springboot.service.UidGenService;
import com.snowflake.springboot.service.UidGenServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 生成bean对象
 *
 * @author shenhongjun
 * @since 2020/5/22
 */
@ConditionalOnProperty(name = "spring.datasource.uid.enable",havingValue = "true")
@Configuration
@ConditionalOnClass(DruidDataSource.class)
public class UiAutoConfiguration {

    /**
     * 机器ID持久化对象实例化
     * @return 返回DisposableWorkerIdAssigner类型
     */
    @Bean(name = "disposableWorkerIdAssigner")
    public DisposableWorkerIdAssigner disposableWorkerIdAssigner() {
        DisposableWorkerIdAssigner disposableWorkerIdAssigner = new DisposableWorkerIdAssigner();
        return disposableWorkerIdAssigner;
    }

    /**
     * 生成Uid对象实例化
     * @return 返回UidGenerator类型
     */
    @Bean(name = "cachedUidGenerator")
    public UidGenerator uidGenerator(DisposableWorkerIdAssigner disposableWorkerIdAssigner) {
        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        // 支持32年
        cachedUidGenerator.setTimeBits(30);
        cachedUidGenerator.setSeqBits(13);
        // 可重启100万次
        cachedUidGenerator.setWorkerBits(20);
        cachedUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner);
        return cachedUidGenerator;
    }

    /**
     * 生成uidGenService的bean对象
     * @return 返回uidGenService的bean
     */
    @Bean
    public UidGenService uidGenService() {
        return new UidGenServiceImpl();
    }
}
