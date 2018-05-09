package com.eoms.controller;

import com.eoms.domain.nms.InterfaceDetail;
import com.eoms.service.InterfaceDetailService;
import com.eoms.service.impl.RecordInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("interfaceDetails/")
public class InterfaceDetailController {

    @Autowired
    private InterfaceDetailService interfaceDetailService;

    @GetMapping("/interface/{id}")
    public List<InterfaceDetail> getInterfaceDetailList(@PathVariable String id) {
        return interfaceDetailService.findAllByInterfaceId(id);
    }

    @GetMapping("/interface/100/{id}")
    public List<InterfaceDetail> findTop100ByInterfaceIdOrderByCreatedAtDesc(@PathVariable String id) {
        return interfaceDetailService.findTop100ByInterfaceIdOrderByCreatedAtDesc(id);
    }

    @GetMapping("/interface/100asc/{id}")
    public List<InterfaceDetail> findFirst100ByInterfaceIdOrderByCreatedAtAsc(@PathVariable String id) {
        return interfaceDetailService.findFirst100ByInterfaceIdOrderByCreatedAtAsc(id);
    }

}
