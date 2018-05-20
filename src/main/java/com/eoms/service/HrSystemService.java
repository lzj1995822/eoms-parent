package com.eoms.service;

import com.eoms.domain.nms.HrSystem;

public interface HrSystemService extends BaseService<HrSystem,String> {
    HrSystem findByTerminalId(String terminalId);
}
