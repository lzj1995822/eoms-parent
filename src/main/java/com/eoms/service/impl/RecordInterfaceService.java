package com.eoms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RecordInterfaceService {

    @Autowired
    private SnmpService snmpService;

    @Scheduled(fixedDelay = 30000) // 间隔5分钟执行
    public void record() {
        snmpService.calAllTerInt();
    }
}
