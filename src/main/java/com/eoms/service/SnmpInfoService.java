package com.eoms.service;

import com.eoms.domain.nms.SnmpInfo;
import org.springframework.stereotype.Service;

@Service
public interface SnmpInfoService extends BaseService<SnmpInfo,String> {

    SnmpInfo findByTerminalId(String terminalId);
}
