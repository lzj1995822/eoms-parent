package com.eoms.service;

import com.eoms.domain.nms.IPNtmTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPNtmTableService extends BaseService<IPNtmTable,String>  {

    List<IPNtmTable> findIPNtmTablesByTerminalId(String terminalId);

}
