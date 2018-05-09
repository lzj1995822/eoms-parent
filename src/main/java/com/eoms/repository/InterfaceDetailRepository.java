package com.eoms.repository;

import com.eoms.domain.nms.InterfaceDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterfaceDetailRepository extends BaseRepository<InterfaceDetail,String> {

//    根据接口id查结果
    List<InterfaceDetail> findAllByInterfaceId(String interfaceId);

    //查询最新的100条记录
    List<InterfaceDetail> findTop100ByInterfaceIdOrderByCreatedAtDesc(String interfaceId);

    //查询最新的100条记录
    List<InterfaceDetail> findFirst100ByInterfaceIdOrderByCreatedAtAsc(String interfaceId);

}
