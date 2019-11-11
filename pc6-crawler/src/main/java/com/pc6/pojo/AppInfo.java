package com.pc6.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "app_info")
@Data
@ToString
public class AppInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    /**
     * logo图片
     */
    private String logo;
    /**
     * 应用说明截图
     */
    private String showimgs;
    /**
     * 网页标题
     */
    private String title;
    /**
     * APP名称
     */
    private String name;
    /**
     * 版本号
     */
    private String version;
    /**
     * 软件大小
     */
    private String size;
    /**
     * 分类名称
     */
    private String category;
    /**
     * 软件介绍
     */
    private String description;
    /**
     * 抓取页的url
     */
    private String pageurl;
    /**
     * app下载地址
     */
    private String appurl;
    /**
     * 来源市场
     */
    private String source;
    /**
     * 图片和安装包是否已经下载完毕标识
     */
    private String flag;
    /**
     * 抓取时间
     */
    private Date createtime;
    /**
     * 更新时间
     */
    private Date updatetime;
}
