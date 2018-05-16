package com.eoms.repository;

import com.eoms.domain.nms.IPRouteTable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPRouteTableRepository extends BaseRepository<IPRouteTable,String> {

    List<IPRouteTable> findIPRouteTablesByTerminalId(String terminalId);
}
