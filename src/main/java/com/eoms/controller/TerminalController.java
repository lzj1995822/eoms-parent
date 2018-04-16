package com.eoms.controller;

import com.eoms.domain.nms.Terminal;
import com.eoms.service.TerminalService;
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

    @GetMapping("/scan")
    public List<Terminal> scanTerminal(){
        return terminalService.scanTerminal();
    }
}
