package com.eoms.service.impl;

import com.eoms.domain.dto.SystemDTO;
import com.eoms.domain.nms.HrSystem;
import com.eoms.domain.nms.Terminal;
import com.eoms.repository.BaseRepository;
import com.eoms.repository.TerminalRepository;
import com.eoms.service.TerminalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TerminalServiceImpl extends BaseServiseImpl<Terminal,String> implements TerminalService {

    @Autowired
    private TerminalRepository termainalRepository;

    @Autowired
    private SnmpService snmpService;

    @Override
    protected BaseRepository<Terminal, String> getBaseRepository() {
        return termainalRepository;
    }

    @Override
    public List<SystemDTO> scanTerminal(String ipa) {
        List<String> ipList = new ArrayList();
        if (ipa != null) {
            ipList.add(ipa);
        }
        for (String ip : ipList) {
            Terminal terminal1 = findTerminalByTerminalIp(ip);
            Terminal terminal = snmpService.getDeviceInfo(ip);
            if (terminal1 != null) {
                terminal.setId(terminal1.getId());
            }
            if (terminal != null) {
                terminal.setTerminalIp(ip);
                save(terminal);
            }
        }
        List<SystemDTO> systemDTOList = new ArrayList<>();
        findAll().forEach(terminal -> {
            SystemDTO systemDTO = snmpService.get(terminal);
            HrSystem hrSystem = snmpService.getHySystem(terminal.getTerminalIp());
            BeanUtils.copyProperties(terminal,systemDTO);
            systemDTO.setId(terminal.getId());
            systemDTO.setHrSystem(hrSystem);
            systemDTOList.add(systemDTO);
        });
        return systemDTOList;
    }

    @Override
    public Terminal findTerminalByTerminalIp(String terminalIp) {
        return termainalRepository.findTerminalByTerminalIp(terminalIp);
    }
}
