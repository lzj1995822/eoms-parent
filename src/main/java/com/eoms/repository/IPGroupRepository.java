package com.eoms.repository;

import com.eoms.domain.nms.IPGroup;
import org.springframework.stereotype.Repository;

@Repository
public interface IPGroupRepository extends BaseRepository<IPGroup,String> {

    IPGroup findByTerminalId(String terminalId);
}
