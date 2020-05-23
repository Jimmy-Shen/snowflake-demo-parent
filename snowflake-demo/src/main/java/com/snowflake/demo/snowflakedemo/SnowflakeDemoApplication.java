package com.snowflake.demo.snowflakedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SnowflakeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnowflakeDemoApplication.class, args);
    }
}
