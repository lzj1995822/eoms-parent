package com.eoms.controller;

import com.eoms.domain.nms.IPGroup;
import com.eoms.service.IPGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/IPGroups")
public class IPGroupController {

    @Autowired
    private IPGroupService ipGroupService;

    @GetMapping("/{terminalId}")
    public IPGroup getIPGroup(@PathVariable String terminalId){
        return ipGroupService.getIPGroup(terminalId);
    }

}
