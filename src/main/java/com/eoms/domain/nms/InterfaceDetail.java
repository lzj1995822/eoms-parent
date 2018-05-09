package com.eoms.domain.nms;

import com.eoms.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "nms_interface_detail")
public class InterfaceDetail extends BaseEntity<String> {

    // interfaceid
    private String interfaceId;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "interfaceId",updatable = false,insertable = false)
    private Interface itf;

//    接口利用率
    private String interfaceUtilization;

//    输入错误率
    private String inputErrorRate;

//    输出错误率
    private String outputErrorRate;

//    输入丢包率
    private String inputLossRate;

//    输出丢包率
    private String outputLossRate;

//    接口输入流量
    private String inputFlow;

//    接口输出流量
    private String outputFlow;

}
