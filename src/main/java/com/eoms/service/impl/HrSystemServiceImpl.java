package com.eoms.service.impl;

import com.eoms.domain.nms.HrSystem;
import com.eoms.domain.nms.Terminal;
import com.eoms.repository.BaseRepository;
import com.eoms.repository.HrSystemRepository;
import com.eoms.service.HrSystemService;
import com.eoms.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HrSystemServiceImpl extends BaseServiseImpl<HrSystem,String> implements HrSystemService {

    @Autowired
    private TerminalService terminalService;

    @Autowired
    private SnmpService snmpService;

    @Autowired
    private HrSystemRepository hrSystemRepository;

    @Override
    protected BaseRepository<HrSystem, String> getBaseRepository() {
        return hrSystemRepository;
    }

    @Override
    public HrSystem findByTerminalId(String terminalId) {
        Terminal terminal = terminalService.getOne(terminalId);
        if (terminal == null) {
            return null;
        }
        HrSystem hrSystem1 = hrSystemRepository.findByTerminalId(terminalId);
        HrSystem hrSystem = snmpService.getHySystem(terminal.getTerminalIp());
        hrSystem.setTerminalId(terminalId);
        if (hrSystem1 != null) {
            hrSystem.setId(hrSystem1.getId());
        }
        return  save(hrSystem);
    }
}
