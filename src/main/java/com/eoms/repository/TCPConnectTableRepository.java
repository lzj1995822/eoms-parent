package com.eoms.repository;

import com.eoms.domain.nms.TCPConnectTable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TCPConnectTableRepository extends BaseRepository<TCPConnectTable,String> {

    List<TCPConnectTable> findTCPConnectTablesByTerminalId(String terminalId);
}
