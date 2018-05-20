package com.eoms.repository;

import com.eoms.domain.nms.SnmpInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface SnmpInfoRepository extends BaseRepository<SnmpInfo,String> {

    SnmpInfo findByTerminalId(String terminalId);
}
