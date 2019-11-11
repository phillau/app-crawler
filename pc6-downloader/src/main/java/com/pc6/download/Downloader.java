package com.pc6.download;

import com.pc6.dao.AppInfoDao;
import com.pc6.pojo.AppInfo;
import com.pc6.util.DownloadUtil;
import com.pc6.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
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
        List<AppInfo> appInfoList = appInfoDao.findByFlagAndSource("0", "www.pc6.com");
        System.out.println(appInfoList.size());
        for (AppInfo appInfo : appInfoList) {
            try {
                Thread.sleep(new Random().nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean flag = true;
            try {
                String redirectUrl = DownloadUtil.getRedirectUrl("http://www.pc6.com" + appInfo.getAppurl());
                for (int i = 0; i < 5; i++) {
                    if ("ok".equals(isApk(redirectUrl))) {
                        DownloadUtil.download(redirectUrl, UUID.randomUUID().toString().replace("-", "_") + ".apk", downloadPath + appInfo.getCategory() + "/" + addZero(appInfo.getId()) + appInfo.getName());
                        break;
                    }
                    if ("continue".equals(isApk(redirectUrl))) {
                        redirectUrl = DownloadUtil.getRedirectUrl(redirectUrl);
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    DownloadUtil.download(appInfo.getLogo(), "logo_" + UUID.randomUUID().toString().replace("-", "") + ".png", downloadPath + appInfo.getCategory() + "/" + addZero(appInfo.getId()) + appInfo.getName());
                    String[] showimgs = appInfo.getShowimgs().split(",,,");
                    for (String showimg : showimgs) {
                        DownloadUtil.download(showimg, "showimg_" + UUID.randomUUID().toString().replace("-", "") + ".png", downloadPath + appInfo.getCategory() + "/" + addZero(appInfo.getId()) + appInfo.getName());
                    }
                    FileUtil.saveAsFileWriter(appInfo.getDescription(), downloadPath + appInfo.getCategory() + "/" + addZero(appInfo.getId()) + appInfo.getName() + "/description.txt");
                    appInfoDao.updateFlagById(appInfo.getId(), "1");
                } else {
                    appInfoDao.deleteById(appInfo.getId());
                }
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

    public String isApk(String url) {
        if (StringUtils.isNotBlank(url) && url.contains(".apk")) {
            return "ok";
        } else if (StringUtils.isNotBlank(url)) {
            return "continue";
        } else {
            return "finish";
        }
    }
}
