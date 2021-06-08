package com.lzg.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.lzg.common.event.ApplicationStartEvent;

@SpringBootApplication
public class LzgManagerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(LzgManagerApplication.class, args);
        applicationContext.publishEvent(new ApplicationStartEvent(applicationContext));
    }

}
