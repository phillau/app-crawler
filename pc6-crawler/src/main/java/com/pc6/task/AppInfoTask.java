package com.pc6.task;

import com.pc6.pipeline.AppInfoPipeline;
import com.pc6.processor.AppInfoProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * 设置定时时间以及入口url等
 */
@Component
public class AppInfoTask {

    @Autowired
    private AppInfoProcessor appInfoProcessor;

    @Autowired
    private AppInfoPipeline appInfoPipeline;

    @Autowired
    private RedisScheduler redisScheduler;

    /**
     * 定时爬取app信息
     */
//    @Scheduled(cron = "0 0/1 * * * *")
    @Scheduled(cron = "0 46 18 * * ?")
    public void aiTask() {
        System.out.println("开始爬取");
        Spider spider = Spider.create(appInfoProcessor);
        spider.addUrl("http://www.pc6.com/");
        spider.addPipeline(appInfoPipeline);
        spider.setScheduler(new FileCacheQueueScheduler("/Users/liufei/IdeaProjects/app-crawler/schedule"));
        spider.start();
    }
}
