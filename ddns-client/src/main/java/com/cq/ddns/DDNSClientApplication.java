package com.cq.ddns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cq.ddns.config.DDNSProperties;

@EnableScheduling
@EnableConfigurationProperties(DDNSProperties.class)
@SpringBootApplication
public class DDNSClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DDNSClientApplication.class, args);
	}

}
