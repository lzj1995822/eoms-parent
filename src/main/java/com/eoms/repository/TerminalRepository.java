package com.eoms.repository;

import com.eoms.domain.nms.Terminal;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository  extends BaseRepository<Terminal, String> {

    Terminal findTerminalByTerminalIp(String terminalIp);
}
