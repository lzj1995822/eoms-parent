package com.eoms.repository;

import com.eoms.domain.nms.SysAbility;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysAbilityRepository extends BaseRepository<SysAbility,String> {

    List<SysAbility> findTop50ByTerminalIdOrderByCreatedAtDesc(String terminalId);
}
