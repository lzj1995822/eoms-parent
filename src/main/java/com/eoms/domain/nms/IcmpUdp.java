package com.eoms.domain.nms;

import com.eoms.domain.BaseEntity;
import com.eoms.snmp.SnmpConstant;
import lombok.Getter;
import lombok.Setter;
import org.snmp4j.PDU;
import org.snmp4j.smi.OID;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "nms_icmp_udp")
public class IcmpUdp extends BaseEntity<String> {

    private String terminalId;

    @OneToOne
    @JoinColumn(name = "terminalId",updatable = false,insertable = false)
    private Terminal terminal;

    //接受到的icmp报文总数 oid 1.3.6.1.2.1.5.1.0
    private Long icmpInMsgs;

    //接收到的特定的ICMP错误的报文个数。例如：错误检验和、错误长度等。 oid 1.3.6.1.2.1.5.2.0
    private Long icmpInErrors;

    //接受到的icmp报文总数 oid 1.3.6.1.2.1.5.14.0
    private Long icmpOutMsgs;

    //没有发送成功的ICMP报文的个数 oid 1.3.6.1.2.1.5.15.0
    private Long icmpOutErrors;

    //UDP输入报文统计 1.3.6.1.2.1.7.1.0
    private Long udpInDatagrams;

    //接收到的有错误（例如校验和错误）而不能提交的UDP数据报个数，不包括因目的端口不可达而丢弃的报文数。 1.3.6.1.2.1.7.3.0
    private Long udpInErrors;

    //表示从本端发送的UDP数据报总数 1.3.6.1.2.1.7.4.0
    private Long udpOutDatagrams;

    public IcmpUdp toEntity(IcmpUdp icmpUdp, PDU pdu) {
        icmpUdp.icmpInMsgs = pdu.getVariable(new OID(SnmpConstant.icmpInMsgs)).toLong();
        icmpUdp.icmpInErrors = pdu.getVariable(new OID(SnmpConstant.icmpInErrors)).toLong();
        icmpUdp.icmpOutMsgs = pdu.getVariable(new OID(SnmpConstant.icmpOutMsgs)).toLong();
        icmpUdp.icmpOutErrors= pdu.getVariable(new OID(SnmpConstant.icmpOutErrors)).toLong();
        icmpUdp.udpInDatagrams = pdu.getVariable(new OID(SnmpConstant.udpInDatagrams)).toLong();
        icmpUdp.udpInErrors =pdu.getVariable(new OID(SnmpConstant.udpInErrors)).toLong();
        icmpUdp.udpOutDatagrams = pdu.getVariable(new OID(SnmpConstant.udpOutDatagrams)).toLong();
        return icmpUdp;
    }
}
