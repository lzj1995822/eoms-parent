package com.eoms.service;

import com.eoms.domain.nms.IcmpUdp;

public interface IcmpUdpService extends BaseService<IcmpUdp,String> {

    IcmpUdp findByTerminalIp(String terminalIp);
}
