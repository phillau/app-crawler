package com.anzhi.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * 下载工具类
 */
public class DownloadUtil {

    public static void download(String urlStr, String filename, String savePath) {
        InputStream in = null;
        OutputStream out = null;
        try {
            URL url = new URL(urlStr);
            //打开url连接
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36");
            //请求超时时间
            connection.setConnectTimeout(5000);
            //输入流
            in = connection.getInputStream();
            //缓冲数据
            byte[] bytes = new byte[1024];
            //数据长度
            int len;
            //文件
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            out = new FileOutputStream(file.getPath() + "/" + filename);
            //先读到bytes中
            while ((len = in.read(bytes)) != -1) {
                //再从bytes中写入文件
                out.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭IO
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
