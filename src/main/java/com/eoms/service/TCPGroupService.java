package com.eoms.service;

import com.eoms.domain.nms.TCPGroup;
import org.springframework.stereotype.Service;


public interface TCPGroupService extends BaseService<TCPGroup,String> {

    TCPGroup findTCPGroupByTerminalId(String terminalId);

}
