package com.ljw.springboot_mybatis_redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ljw.springboot_mybatis_redis.dao")
@SpringBootApplication
public class SpringbootMybatisRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisRedisApplication.class, args);
	}
}
