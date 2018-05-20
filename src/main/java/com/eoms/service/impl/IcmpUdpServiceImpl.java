package com.eoms.service.impl;

import com.eoms.domain.nms.IcmpUdp;
import com.eoms.domain.nms.Terminal;
import com.eoms.repository.BaseRepository;
import com.eoms.repository.IcmpUdpRepository;
import com.eoms.service.IcmpUdpService;
import com.eoms.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IcmpUdpServiceImpl extends BaseServiseImpl<IcmpUdp,String> implements IcmpUdpService {

    @Autowired
    private IcmpUdpRepository icmpUdpRepository;

    @Autowired
    private TerminalService terminalService;

    @Autowired
    private SnmpService snmpService;

    @Override
    public IcmpUdp findByTerminalIp(String terminalId) {
        Terminal terminal = terminalService.getOne(terminalId);
        if (terminal == null) {
            return null;
        }
        IcmpUdp icmpUdp1 = icmpUdpRepository.findByTerminalId(terminalId);
        IcmpUdp icmpUdp = snmpService.getIcmpUdp(terminal.getTerminalIp());
        icmpUdp.setTerminalId(terminalId);
        if (icmpUdp1 != null) {
            icmpUdp.setId(icmpUdp1.getId());
        }
        return  save(icmpUdp);
    }

    @Override
    protected BaseRepository<IcmpUdp,String> getBaseRepository() {
        return icmpUdpRepository;
    }
}
