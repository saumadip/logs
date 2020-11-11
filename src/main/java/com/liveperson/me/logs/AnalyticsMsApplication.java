package com.liveperson.me.logs;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class AnalyticsMsApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AnalyticsMsApplication.class, args);
	}

}
