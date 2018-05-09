package com.eoms.controller;

import com.eoms.domain.nms.InterfaceDetail;
import com.eoms.domain.nms.Terminal;
import com.eoms.service.TerminalService;
import com.eoms.service.impl.SnmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/terminals")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @Autowired
    private SnmpService snmpService;

    @GetMapping("/scan")
    public List<Terminal> scanTerminal(){
        return terminalService.scanTerminal();
    }

    @GetMapping("/cal")
    public void calTerminal(){
        snmpService.calAllTerInt();
    }
}
