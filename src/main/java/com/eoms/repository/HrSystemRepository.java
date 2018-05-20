package com.eoms.repository;

import com.eoms.domain.nms.HrSystem;
import org.springframework.stereotype.Repository;

@Repository
public interface HrSystemRepository extends BaseRepository<HrSystem,String> {

    HrSystem findByTerminalId(String terminalId);
}
