package com.eoms.controller;

import com.eoms.domain.dto.SystemDTO;
import com.eoms.domain.nms.InterfaceDetail;
import com.eoms.domain.nms.Terminal;
import com.eoms.service.TerminalService;
import com.eoms.service.impl.SnmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public List<SystemDTO> scanTerminal(){
        return terminalService.scanTerminal();
    }

    @GetMapping("/cal")
    public void calTerminal(){
        snmpService.calAllTerInt();
    }

    @GetMapping("/all")
    public List<Terminal> getAll() {
        return terminalService.findAll(new Sort(Sort.Direction.DESC,"id"));
    }

}
