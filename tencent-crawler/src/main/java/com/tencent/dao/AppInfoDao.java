package com.tencent.dao;

import com.tencent.pojo.AppInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AppInfoDao extends JpaRepository<AppInfo, String>, JpaSpecificationExecutor<AppInfo> {
}