package com.eoms.controller;

import com.eoms.domain.nms.IPNtmTable;
import com.eoms.service.IPNtmTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ipNtmTables")
public class IPNtmTableController {

    @Autowired
    private IPNtmTableService ipNtmTableService;

    @GetMapping("/{terminalId}")
    public List<IPNtmTable> getIPGroup(@PathVariable String terminalId){
        return ipNtmTableService.findIPNtmTablesByTerminalId(terminalId);
    }
}
