package com.eoms.service;

import com.eoms.domain.nms.SysAbility;

import java.util.List;

public interface SysAbilityService extends BaseService<SysAbility,String> {

    List<SysAbility> findTop50ByTerminalIdOrderByCreatedAtDesc(String terminalId);

}
