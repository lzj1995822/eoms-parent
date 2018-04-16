package com.eoms.service;

import com.eoms.domain.nms.Terminal;

import java.util.List;

public interface TerminalService extends BaseService<Terminal, String> {

    /**
     * 扫描网络上的终端
     * @return
     */
    List<Terminal> scanTerminal();
}