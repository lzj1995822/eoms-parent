package com.eoms.controller;

import com.eoms.domain.nms.Interface;
import com.eoms.service.InterfaceSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/interfaces")
public class InterfaceController {

    @Autowired
    private InterfaceSevice interfaceSevice;

    @GetMapping("/{id}")
    public List<Interface> getInterfaceList(@PathVariable String id) {
        return interfaceSevice.getInterfaceList(id);
    }
}
