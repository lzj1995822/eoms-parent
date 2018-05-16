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

@Entity
@Getter
@Setter
@Table(name = "nms_tcp_group")
public class TCPGroup extends BaseEntity<String> {

    private String terminalId;

    @OneToOne
    @JoinColumn(name = "terminalId",updatable = false,insertable = false)
    private Terminal terminal;

    private Long tcpRtoAlgorithm;
//    OID：tcp.1
//    对象类型：Counter
//    访问模式：只读
//    描述：重发时间算法，可能包含的值：other(1)、constant(2)、rsre(3)和
//    vanj(4)

    private Long tcpRtoMin;
//    OID：tcp.2
//    对象类型：Counter
//    访问模式：只读
//    描述：重发定时器最小值

    private Long tcpRtoMax;
//    OID：tcp.3
//    对象类型：Counter
//    访问模式：只读
//    描述：重发定时器最大值

    private Long tcpMaxConn;
//    OID：tcp.4
//    对象类型：Counter
//    访问模式：只读
//    描述：该系统支持TCP连接的总数（限制）

    private Long tcpActiveOpens;
//    OID：tcp.5
//    对象类型：Counter
//    访问模式：只读
//    描述：该系统支持的主动打开的连接数量

    private Long tcpPassiveOpens;
//    OID：tcp.6
//    对象类型：Counter
//    访问模式：只读
//    描述：该系统支持的被动打开的连接数量

    private Long tcpAttemptFails;
//    OID：tcp.7
//    对象类型：Counter
//    访问模式：只读
//    描述：该系统上发生的试图连接失败的数量

    private Long tcpEstabResets;
//    OID：tcp.8
//    对象类型：Counter
//    访问模式：只读
//    描述：在该系统上发生的连接复位数量

    private Long tcpCurrEstab;
//    OID：tcp.9
//    对象类型：Counter
//    访问模式：只读
//    描述：处于ESTABLISHED或CLOSE-WAIT状态的TCP连接的数量

    private Long tcpInSegs;
//    OID：tcp.10
//    对象类型：Counter
//    访问模式：只读
//    描述：接收到的分组数量，包括接收到的错误分组

    private Long tcpOutSegs;
//    OID：tcp.11
//    对象类型：Counter
//    访问模式：只读
//    描述：发送的分组数量，但除了那些包含重发的字节的分组

    private Long tcpRetransSegs;
//    OID：tcp.12
//    对象类型：Counter
//    访问模式：只读
//    描述：重发分组数量


    private Long tcpInErrs;
//    OID：tcp.12
//    对象类型：Counter
//    访问模式：只读
//    描述：重发分组数量

    private Long tcpOutRsts;
//    OID：tcp.13
//    对象类型：SequenceoftcpConnEntry
//    访问模式：不可访问
//    描述：TCP连接信息

    public TCPGroup toEntity(TCPGroup tcpGroup,PDU pdu) {
        tcpGroup.tcpRtoAlgorithm =  pdu.getVariable(new OID(SnmpConstant.tcpRtoAlgorithm)).toLong();
        tcpGroup.tcpActiveOpens =  pdu.getVariable(new OID(SnmpConstant.tcpActiveOpens)).toLong();
        tcpGroup.tcpAttemptFails=  pdu.getVariable(new OID(SnmpConstant.tcpAttemptFails)).toLong();
        tcpGroup.tcpCurrEstab =  pdu.getVariable(new OID(SnmpConstant.tcpCurrEstab)).toLong();
        tcpGroup.tcpOutRsts =  pdu.getVariable(new OID(SnmpConstant.tcpOutRsts)).toLong();
        tcpGroup.tcpInErrs =  pdu.getVariable(new OID(SnmpConstant.tcpInErrs)).toLong();
        tcpGroup.tcpRetransSegs =  pdu.getVariable(new OID(SnmpConstant.tcpRetransSegs)).toLong();
        tcpGroup.tcpOutSegs =  pdu.getVariable(new OID(SnmpConstant.tcpOutSegs)).toLong();
        tcpGroup.tcpInSegs =  pdu.getVariable(new OID(SnmpConstant.tcpInSegs)).toLong();
        tcpGroup.tcpCurrEstab =  pdu.getVariable(new OID(SnmpConstant.tcpCurrEstab)).toLong();
        tcpGroup.tcpEstabResets =  pdu.getVariable(new OID(SnmpConstant.tcpEstabResets)).toLong();
        tcpGroup.tcpPassiveOpens =  pdu.getVariable(new OID(SnmpConstant.tcpPassiveOpens)).toLong();
        tcpGroup.tcpMaxConn =  pdu.getVariable(new OID(SnmpConstant.tcpMaxConn)).toLong();
        tcpGroup.tcpRtoMax =  pdu.getVariable(new OID(SnmpConstant.tcpRtoMax)).toLong();
        tcpGroup.tcpRtoMin =  pdu.getVariable(new OID(SnmpConstant.tcpRtoMin)).toLong();
        return tcpGroup;
    }
}
