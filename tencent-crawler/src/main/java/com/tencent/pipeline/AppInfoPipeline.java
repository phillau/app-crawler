package com.tencent.pipeline;

import com.tencent.dao.AppInfoDao;
import com.tencent.pojo.AppInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;

/**
 * 负责数据库的存入操作
 */
@Component
public class AppInfoPipeline implements Pipeline {

    @Autowired
    private AppInfoDao appInfoDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        String name = resultItems.get("name");
        String logo = resultItems.get("logo");
        String showimgs = resultItems.get("showimgs");
        String version = resultItems.get("version");
        String size = resultItems.get("size");
        String category = resultItems.get("category");
        String description = resultItems.get("description");
        String pageurl = resultItems.get("pageurl");
        String appurl = resultItems.get("appurl");
        String source = resultItems.get("source");
        if (StringUtils.isNotBlank(name)
                && StringUtils.isNotBlank(logo)
                && StringUtils.isNotBlank(version)
                && StringUtils.isNotBlank(appurl)
                && StringUtils.isNotBlank(showimgs)
                && StringUtils.isNotBlank(description)
                && StringUtils.isNotBlank(category)
                && StringUtils.isNotBlank(size)) {
            AppInfo appInfo = new AppInfo();
            appInfo.setName(name);
            appInfo.setLogo(logo);
            appInfo.setShowimgs(showimgs);
            appInfo.setVersion(version);
            appInfo.setSize(size);
            appInfo.setCategory(category);
            appInfo.setDescription(description);
            appInfo.setPageurl(pageurl);
            appInfo.setAppurl(appurl);
            appInfo.setSource(source);
            appInfo.setFlag("0");
            appInfo.setCreatetime(new Date());
            appInfo.setUpdatetime(new Date());

            System.out.println("爬取到的app信息为：" + appInfo);
            appInfoDao.save(appInfo);
        }
    }
}
