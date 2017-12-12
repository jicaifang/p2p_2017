package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by seemygo on 2017/10/23.
 */
@SpringBootApplication
@MapperScan({"com.xmg.p2p.base.mapper","com.xmg.p2p.business.mapper"})
@ImportResource("classpath:applicationContext-tx.xml")
@PropertySources({
        @PropertySource("classpath:sms.properties"),
        @PropertySource("classpath:email.properties")
})
public class CoreCofing {
    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    public static void main(String[] args) {
        SpringApplication.run(CoreCofing.class,args);
    }
}
