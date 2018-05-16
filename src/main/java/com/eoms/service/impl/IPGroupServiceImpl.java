package com.eoms.service.impl;

import com.eoms.domain.nms.IPGroup;
import com.eoms.domain.nms.Terminal;
import com.eoms.repository.BaseRepository;
import com.eoms.repository.IPGroupRepository;
import com.eoms.service.IPGroupService;
import com.eoms.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class IPGroupServiceImpl extends BaseServiseImpl<IPGroup,String> implements IPGroupService {

    @Autowired
    private IPGroupRepository ipGroupRepository;

    @Autowired
    private TerminalService terminalService;

    @Autowired
    private SnmpService snmpService;

    @Override
    protected BaseRepository<IPGroup, String> getBaseRepository() {
        return ipGroupRepository;
    }

    @Override
    public IPGroup getIPGroup(String terminalId) {
        Terminal terminal = terminalService.getOne(terminalId);
        if (terminal == null) {
            return null;
        }
        IPGroup ipGroup1 = ipGroupRepository.findByTerminalId(terminalId);
        IPGroup ipGroup = snmpService.getIPGoup(terminal.getTerminalIp());
        ipGroup.setTerminalId(terminalId);
        if (ipGroup1 != null) {
            ipGroup.setId(ipGroup1.getId());
        }
        return  save(ipGroup);
    }
}
