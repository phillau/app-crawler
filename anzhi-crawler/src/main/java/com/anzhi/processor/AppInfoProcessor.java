package com.anzhi.processor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 设置抓取的一些配置，以及编写处理加工数据逻辑
 */
@Component
public class AppInfoProcessor implements PageProcessor {
    /**
     * process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
     */
    @Override
    public void process(Page page) {
        List<String> urls = page.getHtml().links().regex("http://www.anzhi.com/pkg/[A-Z a-z 0-9 - . _]+.html").all();
        System.out.println("当前页面总链接数为：" + page.getHtml().links().all().size() + " 有效链接数为：" + urls.size());
        //从页面发现后续的url地址来抓取
        page.addTargetRequests(urls);
        /**
         * 只有应用详情页才清洗数据并发送到pipeline，否则直接跳过
         */
        if (Pattern.matches("http://www.anzhi.com/pkg/[A-Z a-z 0-9 - . _]+.html", page.getUrl().get())) {
            try {
                //app名称
                String name = page.getHtml().xpath("/html/body/div[5]/div[1]/div[2]/div[1]/div[2]/div[1]/h3/text()").get();
                String logo = page.getHtml().xpath("/html/body/div[5]/div[1]/div[2]/div[1]/div[1]").css("img", "src").get();
                List<String> showimgList = page.getHtml().xpath("/html/body/div[5]/div[1]/div[2]/div[8]/div[2]/div/div/ul//li").css("img", "src").all();
                String version = page.getHtml().xpath("/html/body/div[5]/div[1]/div[2]/div[1]/div[2]/div[1]/span/text()").get().replace("(", "").replace(")", "");
                String size = page.getHtml().xpath("//*[@id=\"detail_line_ul\"]/li[4]/span/text()").get().replace("大小：", "");
                String category = page.getHtml().xpath("//*[@id=\"detail_line_ul\"]/li[1]/text()").get().replace("分类：", "");
                String description = page.getHtml().xpath("/html/body/div[5]/div[1]/div[2]/div[4]/div[2]/p/text()").get();
                String pageurl = page.getUrl().get();
                String appurl = page.getHtml().xpath("/html/body/div[5]/div[1]/div[2]/div[1]/div[3]/div[2]").css("a", "onclick").get().replace("opendown(", "").replace(")", "").replace(";", "");
                String source = "www.anzhi.com";
                page.putField("name", name);
                page.putField("logo", logo);
                String showimgs = "";
                for (String showimg : showimgList) {
                    showimgs += showimg + ",,,";
                }
                page.putField("showimgs", showimgs);
                page.putField("version", version);
                page.putField("size", size);
                page.putField("category", category);
                page.putField("description", description);
                page.putField("pageurl", pageurl);
                page.putField("appurl", appurl);
                page.putField("source", source);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            page.setSkip(true);
        }
        /**
         * 不定时休眠，更仿真
         */
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
     */
    @Override
    public Site getSite() {
        return Site.me()
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_1) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Safari/605.1.15")
                .setCharset("UTF-8")
                .setRetryTimes(3)
                .setTimeOut(10 * 1000)
                .setSleepTime(3000);
    }
}
