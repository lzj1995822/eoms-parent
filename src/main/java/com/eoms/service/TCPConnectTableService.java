package com.eoms.service;

import com.eoms.domain.nms.TCPConnectTable;

import java.util.List;

public interface TCPConnectTableService extends BaseService<TCPConnectTable,String>  {

    List<TCPConnectTable> findTCPConnectTablesByTerminalId(String terminalId);

}
