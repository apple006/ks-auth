package com.kingfisher;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = "com.kingfisher.mapper")
public class KingfisherBootstrap {
	public static void main(String[] args) {
//		SpringApplication springApplication =new SpringApplication(KingfisherBootstrap.class);
//        springApplication.addListeners(new StartAddCacheListener());
//        springApplication.run(args);
		SpringApplication.run(KingfisherBootstrap.class, args);
	}

}
