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
@Table(name = "nms_ip_group")
public class IPGroup extends BaseEntity<String> {

    private String terminalId;

    @OneToOne
    @JoinColumn(name = "terminalId",updatable = false,insertable = false)
    private Terminal terminal;
        //网际协议组:ip组包含以下对象集(.1.3.6.1.2.1.4)：
    private Integer ipForwarding;
    //OID：ip.1   1.3.6.1.2.1.4.1
    //对象类型：Integer
    //访问模式：读写
    //描述：指出系统是否作为一个IP网关（路由器）或者仅作为一个不提供转
    //发服务的正规主机。可取的值有Forwarding(1)和notForwarding(2)
    private Integer ipDefaultTTL;
    //OID：ip1.3.6.1.2.1.4.2
    //对象类型：Integer
    //访问模式：读写
    //描述：置于IP报文的TTL字段中的生存期值
    private Long ipInReceives;
    //OID：ip1.3.6.1.2.1.4.3
    //对象类型：Counter
    //访问模式：只读
    //描述：从系统所有可操作接口接收的输入报文的总数
    private Long ipInHdrError;
    //OID：ip1.3.6.1.2.1.4.4
    //对象类型：Counter
    //访问模式：只读
    //描述：由于IP报文头部错误而丢弃的输入报文数量
    private Long ipInaddrErrors;
    //OID：ip1.3.6.1.2.1.4.5
    //对象类型：Counter
    //访问模式：只读
    //描述：对该系统来说，因为最终IP目的地址无效而被丢弃的输入报文数量
    private Long ipForwDatagrams;
    //OID：ip1.3.6.1.2.1.4.6
    //对象类型：Counter
    //访问模式：只读
    //描述：本地系统作为网关或路由器试图转发的报文数量
    private Long ipInUnknownProtos;
    //OID：ip1.3.6.1.2.1.4.7
    //对象类型：NetworkAddress
    //访问模式：只读
    //描述：从网络上成功接收，但由于系统对报文所请求的网络层协议不支持或
    //者未知，而丢弃的报文数量
    //
    //
    private Long ipInDiscards;
    //OID：ip1.3.6.1.2.1.4.8
    //对象类型：Counter
    //访问模式：只读
    //描述：由于缺乏缓冲空间或其他与报文自身无关的条件，而丢弃的输入报文
    //的数量
    private Long ipInDelivers;
    //OID：ip1.3.6.1.2.1.4.9
    //对象类型：Counter
    //访问模式：只读
    //描述：成功传递给上层协议的输入报文的数量
    private Long ipOutRequests;
    //OID：ip1.3.6.1.2.1.4.10
    //对象类型：Counter
    //访问模式：只读
    //描述：上层协议为发送而传递给IP协议的IP报文的数量
    private Long ipOutDiscards;
    //OID：ip1.3.6.1.2.1.4.11
    //对象类型：Counter
    //访问模式：只读
    //描述：由于缺乏缓冲空间或其他与报文自身无关的条件，而丢弃的输出报文
    //的数量
    //
    private Long ipOutNoRoutes;
    //OID：ip1.3.6.1.2.1.4.12
    //对象类型：Counter
    //访问模式：只读
    //描述：因为没有路由到所需目标网络，而丢弃的报文数量
    private Long ipReasmTimeout;
    //OID：ip1.3.6.1.2.1.4.13
    //对象类型：Counter
    //访问模式：只读
    //描述：输入的IP分组报文在它们被重组之前保留的时间间隔（以秒为单位）
    private Long ipReasmReqds;
    //OID：ip1.3.6.1.2.1.4.14
    //对象类型：Counter
    //访问模式：只读
    //描述：接收到的必须重组的IP分组报文数量
    private Long ipReasmOKs;
    //OID：ip1.3.6.1.2.1.4.15
    //对象类型：Counter
    //访问模式：只读
    //描述：成功重组的IP分组报文的数量
    private Long ipReasmFails;
    //OID：ip1.3.6.1.2.1.4.16
    //对象类型：Counter
    //访问模式：只读
    //描述：检测到的重组失败的数量
    private Long ipFragOK;
    //OID：ip1.3.6.1.2.1.4.17
    //对象类型：Counter
    //访问模式：只读
    //描述：已经被成功分组的报文数量
    private Long ipFragsFails;
    //OID：ip1.3.6.1.2.1.4.18
    //对象类型：Counter
    //访问模式：只读
    //描述：因为IP头部包含不分组标志，使得没有分组的报文数量
    private Long ipFragsCreates;
    //OID：ip1.3.6.1.2.1.4.19
    //对象类型：Counter
    //访问模式：只读
    //描述：该系统上产生的IP报文分组的数量
    //private Long ipAddrTable
    //OID：ip.20
    //对象类型：SequenceofIpAddrEntry
    //访问模式：不可访问
    //描述：有关系统的IP地址的地址信息表
    //private Long ipRouteTable（1）
    //OID：ip.21
    //对象类型：SequenceofIpRouteEntry
    //访问模式：不可访问
    //描述：到特定目标的路由
    //private Long ipNetToMediaTable;
    //OID：ip.22
    //对象类型：SequenceofIpNetToMediaEntry
    //访问模式：不可访问
    //描述：IP地址和数据链路地址之间的映射
    private Long ipRoutingDiscards;
    //OID：ip1.3.6.1.2.1.4.23
    //对象类型：Counter
    //访问模式：只读
    //描述：尽管事实上有效，但被丢弃的报文数量
    public IPGroup toEntity(IPGroup ipGroup, PDU pdu) {
        ipGroup.ipForwarding = pdu.getVariable(new OID(SnmpConstant.ipForwarding)).toInt();
        ipGroup.ipDefaultTTL = pdu.getVariable(new OID(SnmpConstant.ipDefaultTTL)).toInt();
        ipGroup.ipForwDatagrams = pdu.getVariable(new OID(SnmpConstant.ipForwDatagrams)).toLong();
        ipGroup.ipFragOK= pdu.getVariable(new OID(SnmpConstant.ipFragOK)).toLong();
        ipGroup.ipFragsCreates = pdu.getVariable(new OID(SnmpConstant.ipFragsCreates)).toLong();
        ipGroup.ipFragsFails =pdu.getVariable(new OID(SnmpConstant.ipFragsFails)).toLong();
        ipGroup.ipInaddrErrors = pdu.getVariable(new OID(SnmpConstant.ipInaddrErrors)).toLong();
        ipGroup.ipInDelivers = pdu.getVariable(new OID(SnmpConstant.ipInDelivers)).toLong();
        ipGroup.ipInDiscards = pdu.getVariable(new OID(SnmpConstant.ipInDiscards)).toLong();
        ipGroup.ipInHdrError= pdu.getVariable(new OID(SnmpConstant.ipInHdrError)).toLong();
        ipGroup.ipInReceives = pdu.getVariable(new OID(SnmpConstant.ipInReceives)).toLong();
        ipGroup.ipInUnknownProtos = pdu.getVariable(new OID(SnmpConstant.ipInUnknownProtos)).toLong();
        ipGroup.ipOutDiscards = pdu.getVariable(new OID(SnmpConstant.ipOutDiscards)).toLong();
        ipGroup.ipOutNoRoutes = pdu.getVariable(new OID(SnmpConstant.ipOutNoRoutes)).toLong();
        ipGroup.ipOutRequests = pdu.getVariable(new OID(SnmpConstant.ipOutRequests)).toLong();
        ipGroup.ipReasmFails= pdu.getVariable(new OID(SnmpConstant.ipReasmFails)).toLong();
        ipGroup.ipReasmReqds = pdu.getVariable(new OID(SnmpConstant.ipReasmReqds)).toLong();
        ipGroup.ipReasmOKs = pdu.getVariable(new OID(SnmpConstant.ipReasmOKs)).toLong();
        ipGroup.ipReasmTimeout = pdu.getVariable(new OID(SnmpConstant.ipReasmTimeout)).toLong();
        ipGroup.ipRoutingDiscards = pdu.getVariable(new OID(SnmpConstant.ipRoutingDiscards)).toLong();
        return ipGroup;
    }
}
