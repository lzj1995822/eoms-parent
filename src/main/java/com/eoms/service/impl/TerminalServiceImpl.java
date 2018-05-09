package com.eoms.service.impl;

import com.eoms.domain.nms.Terminal;
import com.eoms.repository.BaseRepository;
import com.eoms.repository.TerminalRepository;
import com.eoms.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
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
    public List<Terminal> scanTerminal() {
        List<String> ipList = new ArrayList();
        ipList.add("192.168.0.101");
        for (String ip : ipList) {
            Terminal terminal = snmpService.getDeviceInfo(ip);
            terminal.setTerminalIp(ip);
            if (terminal != null) {
                save(terminal);
            }
        }
        return findAll();
    }
}
