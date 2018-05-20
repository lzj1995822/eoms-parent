package com.eoms.controller;

import com.eoms.domain.nms.SnmpInfo;
import com.eoms.service.SnmpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/snmpInfos")
public class SnmpInfoController {

    @Autowired
    private SnmpInfoService snmpInfoService;

    @GetMapping("/{terminalId}")
    public SnmpInfo getIcmpUdp(@PathVariable String terminalId) {
        return snmpInfoService.findByTerminalId(terminalId);
    }
}
