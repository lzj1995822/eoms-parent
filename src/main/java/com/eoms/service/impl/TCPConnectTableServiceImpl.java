package com.eoms.service.impl;

import com.eoms.domain.nms.TCPConnectTable;
import com.eoms.domain.nms.Terminal;
import com.eoms.repository.BaseRepository;
import com.eoms.repository.TCPConnectTableRepository;
import com.eoms.service.TCPConnectTableService;
import com.eoms.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TCPConnectTableServiceImpl extends BaseServiseImpl<TCPConnectTable,String> implements TCPConnectTableService {

    @Autowired
    private TCPConnectTableRepository tcpConnectTableRepository;

    @Autowired
    private TerminalService terminalService;

    @Autowired
    private SnmpService snmpService;


    @Override
    protected BaseRepository<TCPConnectTable, String> getBaseRepository() {
        return tcpConnectTableRepository;
    }

    @Override
    public List<TCPConnectTable> findTCPConnectTablesByTerminalId(String terminalId) {
        Terminal terminal = terminalService.getOne(terminalId);
        if (terminal == null) {
            return null;
        }
        List<TCPConnectTable> tcpConnectTableList = tcpConnectTableRepository.findTCPConnectTablesByTerminalId(terminalId);
        if (!tcpConnectTableList.isEmpty()) {
            tcpConnectTableList.forEach(tcpConnectTable -> {
                tcpConnectTableRepository.delete(tcpConnectTable);
            });
        }
        tcpConnectTableList.clear();
        tcpConnectTableList = snmpService.getTCPTable(terminal.getTerminalIp());
        if (!tcpConnectTableList.isEmpty()) {
            tcpConnectTableList.forEach(tcpConnectTable -> {
                tcpConnectTable.setTerminalId(terminalId);
                tcpConnectTableRepository.save(tcpConnectTable);
            });
        }
        return tcpConnectTableList;
    }
}
