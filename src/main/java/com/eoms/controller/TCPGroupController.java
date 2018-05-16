package com.eoms.controller;

import com.eoms.domain.nms.TCPGroup;
import com.eoms.service.TCPGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tcpGroups")
public class TCPGroupController {

    @Autowired
    private TCPGroupService tcpGroupService;

    @GetMapping("/{terminalId}")
    public TCPGroup findTCPGroupByTerminalId(@PathVariable String terminalId) {
        return tcpGroupService.findTCPGroupByTerminalId(terminalId);
    }
}
