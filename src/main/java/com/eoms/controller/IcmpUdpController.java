package com.eoms.controller;

import com.eoms.domain.nms.IcmpUdp;
import com.eoms.service.IcmpUdpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/icmpUdps")
public class IcmpUdpController {

    @Autowired
    private IcmpUdpService icmpUdpService;

    @GetMapping("/{terminalId}")
    public IcmpUdp getIcmpUdp(@PathVariable String terminalId) {
        return icmpUdpService.findByTerminalIp(terminalId);
    }
}
