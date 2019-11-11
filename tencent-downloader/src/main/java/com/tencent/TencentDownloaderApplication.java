package com.tencent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TencentDownloaderApplication {
    public static void main(String[] args) {
        SpringApplication.run(TencentDownloaderApplication.class);
    }
}
