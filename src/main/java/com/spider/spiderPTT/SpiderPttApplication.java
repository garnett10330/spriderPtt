package com.spider.spiderPTT;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.spider.spiderPTT"
//,excludeFilters = {@Filter(type=FilterType.REGEX,pattern = {"com\\.activity\\.redpacket\\.task\\.[\\s\\S]*"})}
)
@MapperScan({"com.spider.spiderPTT.dao"})
@EnableScheduling
public class SpiderPttApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpiderPttApplication.class, args);
	}
}
