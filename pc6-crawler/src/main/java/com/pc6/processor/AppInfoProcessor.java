package com.pc6.processor;

import org.apache.commons.lang3.StringUtils;
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
        List<String> urls1 = page.getHtml().links().regex("http://www.pc6.com/az/[0-9]+.html").all();
        List<String> urls2 = page.getHtml().links().regex("http://www.pc6.com/azyx/[0-9]+.html").all();
        System.out.println("当前页面总链接数为：" + page.getHtml().links().all().size() + " 有效链接数为：" + (urls1.size() + urls2.size()));
        //从页面发现后续的url地址来抓取
        page.addTargetRequests(urls1);
        page.addTargetRequests(urls2);
        /**
         * 只有应用详情页才清洗数据并发送到pipeline，否则直接跳过
         */
        if (Pattern.matches("http://www.pc6.com/azyx/[0-9]+.html", page.getUrl().get())
                || Pattern.matches("http://www.pc6.com/az/[0-9]+.html", page.getUrl().get())) {
            try {
                /**
                 * app名称
                 */
                String name = page.getHtml().xpath("//*[@id=\"dinfo\"]/h1/text()").get();
                /**
                 * logo图片
                 */
                String logo = page.getHtml().xpath("//*[@id=\"dico\"]").css("img", "src").get();
                /**
                 * 简介截图
                 */
                List<String> showimgList = page.getHtml().xpath("//*[@id=\"imageShots\"]//li").css("img", "src").all();
                /**
                 * 版本
                 */
                String version = "";
                String version1 = page.getHtml().xpath("//*[@id=\"dinfo\"]/p[2]/i[2]/text()").get();
                String version2 = page.getHtml().xpath("//*[@id=\"dinfo\"]/p[3]/i[2]/text()").get();
                if (StringUtils.isNotBlank(version1)) {
                    version = version1.replace("版本：", "").replace("v", "");
                } else if (StringUtils.isNotBlank(version2)) {
                    version = version2.replace("版本：", "").replace("v", "");
                } else {
                    System.err.println("没有找到对应版本：" + page.getUrl().toString());
                }
                /**
                 * 大小
                 */
                String size = "";
                String size1 = page.getHtml().xpath("//*[@id=\"dinfo\"]/p[2]/i[3]/text()").get();
                String size2 = page.getHtml().xpath("//*[@id=\"dinfo\"]/p[3]/i[3]/text()").get();
                if (StringUtils.isNotBlank(size1)) {
                    size = size1.replace("大小：", "");
                } else if (StringUtils.isNotBlank(size2)) {
                    size = size2.replace("大小：", "");
                } else {
                    System.err.println("没有找到对应大小：" + page.getUrl().toString());
                }
                /**
                 * 分类
                 */
                String category = "";
                String category1 = page.getHtml().xpath("//*[@id=\"dinfo\"]/p[2]/i[1]/a/text()").get();
                String category2 = page.getHtml().xpath("//*[@id=\"dinfo\"]/p[3]/i[1]/a/text()").get();
                if (StringUtils.isNotBlank(category1)) {
                    category = category1;
                } else if (StringUtils.isNotBlank(category2)) {
                    category = category2;
                } else {
                    System.err.println("没有找到对应分类：" + page.getUrl().toString());
                }
                /**
                 * 描述
                 */
                String description = "";
                String description1 = page.getHtml().xpath("//*[@id=\"soft-info\"]/p[1]/text()").get();
                String description2 = page.getHtml().xpath("//*[@id=\"soft-info\"]/div/p[1]/text()").get();
                if (StringUtils.isNotBlank(description1)) {
                    for (int i = 1; i <= 4; i++) {
                        if (StringUtils.isNotBlank(page.getHtml().xpath("//*[@id=\"soft-info\"]/p[" + i + "]/text()").get())
                                && page.getHtml().xpath("//*[@id=\"soft-info\"]/p[" + i + "]/text()").get().length() > 5) {
                            description += page.getHtml().xpath("//*[@id=\"soft-info\"]/p[" + i + "]/text()").get();
                        }
                    }
                } else if (StringUtils.isNotBlank(description2)) {
                    for (int i = 1; i <= 4; i++) {
                        if (StringUtils.isNotBlank(page.getHtml().xpath("//*[@id=\"soft-info\"]/div/p[" + i + "]/text()").get())
                                && page.getHtml().xpath("//*[@id=\"soft-info\"]/div/p[" + i + "]/text()").get().length() > 5) {
                            description += page.getHtml().xpath("//*[@id=\"soft-info\"]/div/p[" + i + "]/text()").get();
                        }
                    }
                } else {
                    System.err.println("没有找到对应描述：" + page.getUrl().toString());
                }

                /**
                 * 当前页面
                 */
                String pageurl = page.getUrl().get();
                /**
                 * 下载地址
                 */
                String appurl = "";//*[@id="uaddl"]/ul/li[1]
                String appurl1 = page.getHtml().xpath("//*[@id=\"uaddl\"]/ul/li[1]").css("a", "href").get();
                String appurl2 = page.getHtml().xpath("//*[@id=\"uaddl\"]/span").css("a", "href").get();
                if (StringUtils.isNotBlank(appurl1)) {
                    appurl = appurl1;
                } else if (StringUtils.isNotBlank(appurl2)) {
                    appurl = appurl2;
                } else {
                    System.err.println("没有找到对应下载地址：" + page.getUrl().toString());
                }
                /**
                 * 来源
                 */
                String source = "www.pc6.com";
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
                System.err.println("错误页面url=" + page.getUrl().get());
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
                .addHeader("Accept-Language", "zh-cnzz")
                .setRetryTimes(3)
                .setTimeOut(10 * 1000)
                .setSleepTime(3000);
    }
}
