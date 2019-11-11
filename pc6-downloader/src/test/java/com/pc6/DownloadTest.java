package com.pc6;

import com.pc6.util.DownloadUtil;

import java.util.UUID;

public class DownloadTest {
    public static void main(String[] args) {

//        DownloadUtil.download("http://www.pc6.com/down.asp?id=80844", UUID.randomUUID().toString().replace("-", "_") + ".apk", "/Users/liufei/IdeaProjects/app-crawler/apps/");
        String redirectUrl1 = DownloadUtil.getRedirectUrl("http://www.pc6.com/down.asp?id=68253");
        String redirectUrl2 = DownloadUtil.getRedirectUrl(redirectUrl1);
        String redirectUrl3 = DownloadUtil.getRedirectUrl(redirectUrl2);
        System.out.println(redirectUrl1);
        System.out.println(redirectUrl2);
        System.out.println(redirectUrl3);
    }
}
