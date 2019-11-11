package com.anzhi;

import com.anzhi.util.DownloadUtil;

import java.io.IOException;
import java.util.UUID;

public class DownloadTest {
    public static void main(String[] args) throws IOException {
//        DownloadUtil.download("http://www.anzhi.com/dl_app.php?s=3167229&n=5", UUID.randomUUID().toString().replace("-","_") + ".apk","/Users/liufei/IdeaProjects/app-crawler/apps/");
//        String id = "541";
//        StringBuilder stringBuilder = new StringBuilder(id);
//        int length = id.length();
//        for (int i = 0; i < 5 - length; i++) {
//            stringBuilder.insert(0, "0");
//        }
//        System.out.println(stringBuilder.toString());
        DownloadUtil.download("http://www.pc6.com/down.asp?id=80844", UUID.randomUUID().toString().replace("-", "_") + ".apk", "/Users/liufei/IdeaProjects/app-crawler/apps/");

    }
}
