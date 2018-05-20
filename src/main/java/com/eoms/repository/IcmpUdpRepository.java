package com.eoms.repository;

import com.eoms.domain.nms.IcmpUdp;
import org.springframework.stereotype.Repository;

@Repository
public interface IcmpUdpRepository extends BaseRepository<IcmpUdp,String> {

    IcmpUdp findByTerminalId(String termianlId);

}
