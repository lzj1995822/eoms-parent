package com.eoms.service;

import com.eoms.domain.nms.IPGroup;

public interface IPGroupService  extends BaseService<IPGroup,String> {

    IPGroup getIPGroup(String terminalId);
}
