package com.eoms.domain.nms;

import com.eoms.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "nms_interface")
public class Interface extends BaseEntity<String> {

    private String terminalId;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "terminalId",insertable = false,updatable = false)
    private Terminal terminal;

    private Integer ifIndex;

    private String ifDescr;

    private String ifType;

    private Integer ifMtu;

    private Integer ifSpeed;

    private String ifPhysAddress;

    private String ifAdminStatus;

    private String ifOperStatus;

    private Long ifLastChange;

    private Long ifInOctets;

    private Integer ifInUcastPkts;

    private Integer ifInNUcastPkts;

    private Integer ifInDiscards;

    private Integer ifInErrors;

    private Integer ifInUnknownProtos;

    private Long ifOutOctets;

    private Integer ifOutUcastPkts;

    private Integer ifOutNUcastPkts;

    private Integer ifOutDiscards;

    private Integer ifOutErrors;

    private Integer ifOutQLen;

    private String ifSpecific;

}
