package com.eoms.repository;

import com.eoms.domain.nms.TCPGroup;
import org.springframework.stereotype.Repository;


@Repository
public interface TCPGroupRepository extends BaseRepository<TCPGroup,String> {

    TCPGroup findTCPGroupByTerminalId(String terminalId);
}
