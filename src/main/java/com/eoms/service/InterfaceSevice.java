package com.eoms.service;

import com.eoms.domain.nms.Interface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InterfaceSevice extends BaseService<Interface,String> {

//    获取某设备的接口列表
    List<Interface> getInterfaceList(String ip);

    Long countByTerminalId(String terminalId);

    List<Interface> findAllByTerminalId(String terminalId);
}
