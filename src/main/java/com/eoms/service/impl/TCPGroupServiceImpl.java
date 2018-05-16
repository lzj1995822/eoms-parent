package com.eoms.service.impl;

import com.eoms.domain.nms.TCPGroup;
import com.eoms.domain.nms.Terminal;
import com.eoms.repository.BaseRepository;
import com.eoms.repository.TCPGroupRepository;
import com.eoms.service.TCPGroupService;
import com.eoms.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TCPGroupServiceImpl extends BaseServiseImpl<TCPGroup,String> implements TCPGroupService {

    @Autowired
    private TCPGroupRepository tcpGroupRepository;


    @Autowired
    private TerminalService terminalService;

    @Autowired
    private SnmpService snmpService;

    @Override
    protected BaseRepository<TCPGroup, String> getBaseRepository() {
        return tcpGroupRepository;
    }

    @Override
    public TCPGroup findTCPGroupByTerminalId(String terminalId) {
        Terminal terminal = terminalService.getOne(terminalId);
        if (terminal == null) {
            return null;
        }
        TCPGroup tcpGroup1 = tcpGroupRepository.findTCPGroupByTerminalId(terminalId);
        TCPGroup tcpGroup = snmpService.getTCPGoup(terminal.getTerminalIp());
        tcpGroup.setTerminalId(terminalId);
        if (tcpGroup1 != null) {
            tcpGroup.setId(tcpGroup1.getId());
        }
        return  save(tcpGroup);
    }
}
