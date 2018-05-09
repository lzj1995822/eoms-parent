package com.eoms.repository;

import com.eoms.domain.nms.Interface;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterfaceRepository extends BaseRepository<Interface,String> {

    Long countByTerminalId(String terminalId);

    List<Interface> findAllByTerminalId(String terminalId);
}
