package com.tencent.task;

import com.tencent.download.Downloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AppInfoTask {
    @Autowired
    private Downloader downloader;

    /**
     * 定时将爬取的url包括图片，app安装包下载到本地
     */
    @Scheduled(cron = "0 0/1 * * * *")
    public void aiTask() {
        System.out.println("开始下载");
        downloader.start();
    }
}
