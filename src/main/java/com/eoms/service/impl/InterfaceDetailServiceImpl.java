package com.eoms.service.impl;

import com.eoms.domain.nms.InterfaceDetail;
import com.eoms.repository.BaseRepository;
import com.eoms.repository.InterfaceDetailRepository;
import com.eoms.service.InterfaceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterfaceDetailServiceImpl extends BaseServiseImpl<InterfaceDetail,String> implements InterfaceDetailService {

    @Autowired
    private InterfaceDetailRepository interfaceDetailRepository;

    @Override
    protected BaseRepository<InterfaceDetail, String> getBaseRepository() {
        return interfaceDetailRepository;
    }

    @Override
    public List<InterfaceDetail> findAllByInterfaceId(String interfaceId) {
        return interfaceDetailRepository.findAllByInterfaceId(interfaceId);
    }

    @Override
    public List<InterfaceDetail> findTop100ByInterfaceIdOrderByCreatedAtDesc(String interfaceId) {
        return interfaceDetailRepository.findTop100ByInterfaceIdOrderByCreatedAtDesc(interfaceId);
    }

    @Override
    public List<InterfaceDetail> findFirst100ByInterfaceIdOrderByCreatedAtAsc(String interfaceId) {
        return interfaceDetailRepository.findFirst100ByInterfaceIdOrderByCreatedAtAsc(interfaceId);
    }
}
