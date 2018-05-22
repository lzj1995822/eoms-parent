package com.eoms.service;

import com.eoms.domain.dto.SystemDTO;
import com.eoms.domain.nms.Terminal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TerminalService extends BaseService<Terminal, String> {

    /**
     * 扫描网络上的终端
     * @return
     */
    List<SystemDTO> scanTerminal(String ip);

    Terminal findTerminalByTerminalIp(String terminalIp);
}