package com.anzhi;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) throws IOException {
//        String content = "http://www.anzhi.com/pkg/1490_tv.danmakOP.bili.html";
//        String pattern = "http://www.anzhi.com/pkg/[A-Z a-z 0-9 - . _]+.html";
//        boolean isMatch = Pattern.matches(pattern, content);
//        System.out.println(isMatch);

        String content = "https://sj.qq.com/myapp/detail.htm?apkName=com.Qunar";
        String pattern = "https://sj.qq.com/myapp/detail.htm\\?apkName=[A-Z a-z 0-9 - . _]+";
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(isMatch);

//        for (int i = 0; i <10; i++) {
//            int ii = new Random().nextInt(3000);
//            System.out.println(ii);
//        }
//
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet("http://www.anzhi.com/dl_app.php?s=3167229&n=5");
//        CloseableHttpResponse response1 = httpclient.execute(httpGet);
//        try {
//            System.out.println(response1.getStatusLine());
//            HttpEntity entity1 = response1.getEntity();
//            String response= EntityUtils.toString(entity1,"utf-8");
//            System.out.println(response);
//            //必须完全的consume，否则connection manager可能无法复用连接
//            EntityUtils.consume(entity1);
//        } finally {
//            //必须close response ， 否则无法释放持有的connection
//            response1.close();
//        }
    }
}
