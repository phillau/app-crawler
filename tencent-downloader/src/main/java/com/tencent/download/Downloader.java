package com.tencent.download;

import com.tencent.dao.AppInfoDao;
import com.tencent.pojo.AppInfo;
import com.tencent.util.DownloadUtil;
import com.tencent.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class Downloader {
    @Autowired
    private AppInfoDao appInfoDao;

    @Value("${download.path}")
    private String downloadPath;

    public void start() {
        List<AppInfo> appInfoList = appInfoDao.findByFlagAndSource("0", "sj.qq.com");
        System.out.println(appInfoList.size());
        for (AppInfo appInfo : appInfoList) {
            try {
                Thread.sleep(new Random().nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                DownloadUtil.download(appInfo.getAppurl(), UUID.randomUUID().toString().replace("-", "_") + ".apk", downloadPath + appInfo.getCategory() + "/" + addZero(appInfo.getId()) + appInfo.getName());
                DownloadUtil.download(appInfo.getLogo(), "logo_" + UUID.randomUUID().toString().replace("-", "") + ".png", downloadPath + appInfo.getCategory() + "/" + addZero(appInfo.getId()) + appInfo.getName());
                String[] showimgs = appInfo.getShowimgs().split(",,,");
                for (String showimg : showimgs) {
                    DownloadUtil.download(showimg, "showimg_" + UUID.randomUUID().toString().replace("-", "") + ".png", downloadPath + appInfo.getCategory() + "/" + addZero(appInfo.getId()) + appInfo.getName());
                }
                FileUtil.saveAsFileWriter(appInfo.getDescription(), downloadPath + appInfo.getCategory() + "/" + addZero(appInfo.getId()) + appInfo.getName() + "/description.txt");
                appInfoDao.updateFlagById(appInfo.getId(), "1");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String addZero(String id) {
        StringBuilder stringBuilder = new StringBuilder(id);
        int length = id.length();
        for (int i = 0; i < 8 - length; i++) {
            stringBuilder.insert(0, "0");
        }
        return stringBuilder.toString();
    }
}
