package com.pc6.dao;

import com.pc6.pojo.AppInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AppInfoDao extends JpaRepository<AppInfo, String>, JpaSpecificationExecutor<AppInfo> {
}