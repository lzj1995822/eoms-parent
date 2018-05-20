package com.eoms.controller;

import com.eoms.domain.nms.HrSystem;
import com.eoms.service.HrSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/hrSystems")
public class HrSystemController {

    @Autowired
    private HrSystemService hrSystemService;

    @GetMapping("{terminalId}")
    public HrSystem get(@PathVariable String terminalId) {
        return hrSystemService.findByTerminalId(terminalId);
    }
}
