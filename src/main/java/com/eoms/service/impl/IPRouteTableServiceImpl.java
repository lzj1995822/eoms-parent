package com.eoms.service.impl;

import com.eoms.domain.nms.IPRouteTable;
import com.eoms.domain.nms.Terminal;
import com.eoms.repository.BaseRepository;
import com.eoms.repository.IPRouteTableRepository;
import com.eoms.service.IPRouteTableService;
import com.eoms.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPRouteTableServiceImpl extends BaseServiseImpl<IPRouteTable,String> implements IPRouteTableService {

    @Autowired
    private IPRouteTableRepository ipRouteTableRepository;

    @Autowired
    private TerminalService terminalService;

    @Autowired
    private SnmpService snmpService;

    @Override
    protected BaseRepository<IPRouteTable, String> getBaseRepository() {
        return ipRouteTableRepository;
    }

    @Override
    public List<IPRouteTable> findIPRouteTableByTerminalId(String terminalId) {
        Terminal terminal = terminalService.getOne(terminalId);
        if (terminal == null) {
            return null;
        }
        List<IPRouteTable> ipRouteTableList = ipRouteTableRepository.findIPRouteTablesByTerminalId(terminalId);
        if (!ipRouteTableList.isEmpty()) {
            ipRouteTableList.forEach(ipRouteTable -> {
                ipRouteTableRepository.delete(ipRouteTable);
            });
        }
        ipRouteTableList.clear();
        ipRouteTableList = snmpService.getIPRouteTable(terminal.getTerminalIp());
        if (!ipRouteTableList.isEmpty()) {
            ipRouteTableList.forEach(ipRouteTable -> {
                ipRouteTable.setTerminalId(terminalId);
                ipRouteTableRepository.save(ipRouteTable);
            });
        }
        return ipRouteTableList;
    }
}
