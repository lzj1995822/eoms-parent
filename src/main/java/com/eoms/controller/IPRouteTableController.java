package com.eoms.controller;

import com.eoms.domain.nms.IPRouteTable;
import com.eoms.service.IPRouteTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ipRouteTables")
public class IPRouteTableController {

    @Autowired
    private IPRouteTableService ipRouteTableService;

    @GetMapping("/{terminalId}")
    public List<IPRouteTable> findByTerminalId(@PathVariable String terminalId) {
        return ipRouteTableService.findIPRouteTableByTerminalId(terminalId);
    }
}
