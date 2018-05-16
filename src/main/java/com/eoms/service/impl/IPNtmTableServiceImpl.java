package com.eoms.service.impl;

import com.eoms.domain.nms.IPNtmTable;
import com.eoms.domain.nms.Terminal;
import com.eoms.repository.BaseRepository;
import com.eoms.repository.IPNtmTableRepository;
import com.eoms.service.IPNtmTableService;
import com.eoms.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPNtmTableServiceImpl extends BaseServiseImpl<IPNtmTable,String> implements IPNtmTableService {

    @Autowired
    private IPNtmTableRepository ipNtmTableRepository;

    @Autowired
    private TerminalService terminalService;

    @Autowired
    private SnmpService snmpService;

    @Override
    public List<IPNtmTable> findIPNtmTablesByTerminalId(String terminalId) {
        Terminal terminal = terminalService.getOne(terminalId);
        if (terminal == null) {
            return null;
        }
        List<IPNtmTable> ipNtmTableList = ipNtmTableRepository.findIPNtmTablesByTerminalId(terminalId);
        if (!ipNtmTableList.isEmpty()) {
            ipNtmTableList.forEach(ipNtmTable -> {
                ipNtmTableRepository.delete(ipNtmTable);
            });
        }
        ipNtmTableList.clear();
        ipNtmTableList = snmpService.getIPNtmTable(terminal.getTerminalIp());
        if (!ipNtmTableList.isEmpty()) {
            ipNtmTableList.forEach(ipNtmTable -> {
                ipNtmTable.setTerminalId(terminalId);
                ipNtmTableRepository.save(ipNtmTable);
            });
        }
        return ipNtmTableList;
    }

    @Override
    protected BaseRepository<IPNtmTable, String> getBaseRepository() {
        return ipNtmTableRepository;
    }
}
