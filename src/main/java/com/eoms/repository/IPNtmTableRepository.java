package com.eoms.repository;

import com.eoms.domain.nms.IPNtmTable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPNtmTableRepository extends BaseRepository<IPNtmTable,String> {

    List<IPNtmTable> findIPNtmTablesByTerminalId(String terminalId);
}
