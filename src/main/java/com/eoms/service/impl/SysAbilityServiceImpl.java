package com.eoms.service.impl;

import com.eoms.domain.nms.SysAbility;
import com.eoms.repository.BaseRepository;
import com.eoms.repository.SysAbilityRepository;
import com.eoms.service.SysAbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysAbilityServiceImpl extends BaseServiseImpl<SysAbility,String> implements SysAbilityService {

    @Autowired
    private SysAbilityRepository sysAbilityRepository;

    @Override
    public List<SysAbility> findTop50ByTerminalIdOrderByCreatedAtDesc(String terminalId) {
        return sysAbilityRepository.findTop50ByTerminalIdOrderByCreatedAtDesc(terminalId);
    }

    @Override
    protected BaseRepository<SysAbility, String> getBaseRepository() {
        return sysAbilityRepository;
    }
}
