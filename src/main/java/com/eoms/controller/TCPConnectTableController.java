package com.eoms.controller;

import com.eoms.domain.nms.TCPConnectTable;
import com.eoms.service.TCPConnectTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tcpTables")
public class TCPConnectTableController {

    @Autowired
    private TCPConnectTableService tcpConnectTableService;

    @GetMapping("/{terminalId}")
    public List<TCPConnectTable> getTCPConnect(@PathVariable String terminalId){
        return tcpConnectTableService.findTCPConnectTablesByTerminalId(terminalId);
    }
}
