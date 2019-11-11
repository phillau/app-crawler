package com.pc6.dao;

import com.pc6.pojo.AppInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AppInfoDao extends JpaRepository<AppInfo, String>, JpaSpecificationExecutor<AppInfo> {
    List<AppInfo> findByFlagAndSource(@Param(value = "flag") String flag, @Param(value = "source") String source);

    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query("update AppInfo set flag = :flag where id = :id")
    void updateFlagById(@Param(value = "id") String id, @Param(value = "flag") String flag);
}