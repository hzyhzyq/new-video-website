package com.yunqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Tony
 */

@SpringBootApplication()
@EnableTransactionManagement
@EnableScheduling
public class VideoWebsiteApp {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(VideoWebsiteApp.class,args);
    }
}
