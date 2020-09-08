package com.cyb.ybmobileredis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //开启定时任务
@MapperScan("com.cyb.ybmobileredis.mapper")
public class YbMobileRedisApplication {

	public static void main(String[] args) {

		SpringApplication.run(YbMobileRedisApplication.class, args);
	}
}
