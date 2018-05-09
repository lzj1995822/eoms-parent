package com.eoms.service;

import com.eoms.domain.nms.InterfaceDetail;
import io.swagger.models.auth.In;

import java.util.List;

public interface InterfaceDetailService extends BaseService<InterfaceDetail,String> {

    List<InterfaceDetail> findAllByInterfaceId(String interfaceId);

    List<InterfaceDetail> findTop100ByInterfaceIdOrderByCreatedAtDesc(String interfaceId);

    List<InterfaceDetail> findFirst100ByInterfaceIdOrderByCreatedAtAsc(String interfaceId);
}
