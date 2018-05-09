package com.eoms.service.impl;

import com.eoms.domain.nms.Interface;
import com.eoms.repository.BaseRepository;
import com.eoms.repository.InterfaceRepository;
import com.eoms.service.InterfaceSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterfaceServiceImpl extends BaseServiseImpl<Interface,String> implements InterfaceSevice {

    @Autowired
    private InterfaceRepository interfaceRepository;

    @Autowired
    private SnmpService snmpService;

    @Override
    public List<Interface> getInterfaceList(String id) {
        return snmpService.getInterfaceList(id);
    }

    @Override
    public Long countByTerminalId(String terminalId) {
        return interfaceRepository.countByTerminalId(terminalId);
    }

    @Override
    public List<Interface> findAllByTerminalId(String terminalId) {
        return interfaceRepository.findAllByTerminalId(terminalId);
    }

    @Override
    protected BaseRepository<Interface, String> getBaseRepository() {
        return interfaceRepository;
    }
}
