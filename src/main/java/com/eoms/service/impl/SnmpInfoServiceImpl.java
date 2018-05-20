package com.eoms.service.impl;

import com.eoms.domain.nms.SnmpInfo;
import com.eoms.domain.nms.Terminal;
import com.eoms.repository.BaseRepository;
import com.eoms.repository.SnmpInfoRepository;
import com.eoms.service.SnmpInfoService;
import com.eoms.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnmpInfoServiceImpl extends BaseServiseImpl<SnmpInfo,String> implements SnmpInfoService {

    @Autowired
    private SnmpInfoRepository snmpInfoRepository;

    @Autowired
    private TerminalService terminalService;

    @Autowired
    private SnmpService snmpService;

    @Override
    public SnmpInfo findByTerminalId(String terminalId) {
        Terminal terminal = terminalService.getOne(terminalId);
        if (terminal == null) {
            return null;
        }
        SnmpInfo snmpInfo1 = snmpInfoRepository.findByTerminalId(terminalId);
        SnmpInfo snmpInfo = snmpService.getSnmpInfo(terminal.getTerminalIp());
        snmpInfo.setTerminalId(terminalId);
        if (snmpInfo1 != null) {
            snmpInfo.setId(snmpInfo1.getId());
        }
        return  save(snmpInfo);
    }

    @Override
    protected BaseRepository<SnmpInfo, String> getBaseRepository() {
        return snmpInfoRepository;
    }
}
