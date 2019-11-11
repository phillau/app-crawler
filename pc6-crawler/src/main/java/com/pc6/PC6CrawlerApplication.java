package com.pc6;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import us.codecraft.webmagic.scheduler.RedisScheduler;

@SpringBootApplication
@EnableScheduling
public class PC6CrawlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PC6CrawlerApplication.class);
    }

    @Value("${spring.redis.host}")
    private String redisHost;

    /**
     * webmagic中，Scheduler主要用来防止重复爬取同一页面，其中RedisScheduler可用于分布式爬取场景，只需要设置为同一个redis即可
     */
    @Bean
    public RedisScheduler redisScheduler() {
        return new RedisScheduler(redisHost);
    }
}
