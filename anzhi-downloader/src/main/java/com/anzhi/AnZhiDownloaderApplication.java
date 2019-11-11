package com.anzhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AnZhiDownloaderApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnZhiDownloaderApplication.class);
    }
}
