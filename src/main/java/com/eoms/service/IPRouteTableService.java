package com.eoms.service;

import com.eoms.domain.nms.IPRouteTable;

import java.util.List;

public interface IPRouteTableService extends BaseService<IPRouteTable,String> {

    List<IPRouteTable> findIPRouteTableByTerminalId(String termianlId);
}
