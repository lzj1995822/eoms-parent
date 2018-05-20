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
@Table(name = "nms_snmp_table")
public class SnmpInfo extends BaseEntity<String> {

    private String terminalId;

    @OneToOne
    @JoinColumn(name = "terminalId", updatable = false, insertable = false)
    private Terminal terminal;

    private Long snmpInPkts;
//    OID：1.3.6.1.2.1.11.1
//    对象类型：Counter32
//    访问模式：读写
//    描述：SNMP实体收到的消息总数

    private Long snmpOutPkts;
//    OID：1.3.6.1.2.1.11.2
//    对象类型：Counter32
//    访问模式：读写
//    描述：SNMP实体发送的消息总数

    private Long snmpInBadVersions;
//    OID：1.3.6.1.2.1.11.3
//    对象类型：Counter32
//    访问模式：读写
//    描述：SNMP实体收到的消息中，有一些使用的是当前不支持的SNMP版本，该值即为这些消息的总数。

    private Long snmpInBadCommunityNames;
//    OID：1.3.6.1.2.1.11.4
//    对象类型：Counter32
//    访问模式：读写
//    描述：SNMP实体收到的消息中，有一些使用的是当前实体不能识别的SNMP团体名，该值即为这些消息的总数

    private Long snmpInBadCommunityUses;
//    OID：1.3.6.1.2.1.11.5
//    对象类型：Counter32
//    访问模式：读写
//    描述：SNMP实体收到的消息中，有一些涉及此消息使用的团体名中不允许的SNMP操作，该值即为这些消息的总数

    private Long snmpInGetRequests;
//    OID：1.3.6.1.2.1.11.15
//    对象类型：Counter32
//    访问模式：读写
//    描述：SNMP实体收到并且处理的Get-Request报文总数

    private Long snmpInGetNexts;
//    OID：1.3.6.1.2.1.11.16
//    对象类型：Counter32
//    访问模式：读写
//    描述：SNMP实体收到并且处理的Get-Next报文总数

    private Long snmpInSetRequests;
//    OID：1.3.6.1.2.1.11.17
//    对象类型：Counter32
//    访问模式：读写
//    描述：SNMP实体收到并且处理的Set-Request报文总数

    private Long snmpOutNoSuchNames;
//    OID：1.3.6.1.2.1.11.21
//    对象类型：Counter32
//    访问模式：读写
//    描述：SNMP实体产生的SNMP PUD报文中，有一些报文的错误状态为"noSuchName",该值即为这些SNMP PUD报文的总数

    public SnmpInfo toEntity(SnmpInfo snmpInfo, PDU pdu) {
        snmpInfo.snmpInPkts = pdu.getVariable(new OID(SnmpConstant.snmpInPkts)).toLong();
        snmpInfo.snmpOutPkts = pdu.getVariable(new OID(SnmpConstant.snmpOutPkts)).toLong();
        snmpInfo.snmpInBadVersions = pdu.getVariable(new OID(SnmpConstant.snmpInBadVersions)).toLong();
        snmpInfo.snmpInBadCommunityNames = pdu.getVariable(new OID(SnmpConstant.snmpInBadCommunityNames)).toLong();
        snmpInfo.snmpInBadCommunityUses = pdu.getVariable(new OID(SnmpConstant.snmpInBadCommunityUses)).toLong();
        snmpInfo.snmpInGetRequests = pdu.getVariable(new OID(SnmpConstant.snmpInGetRequests)).toLong();
        snmpInfo.snmpInSetRequests = pdu.getVariable(new OID(SnmpConstant.snmpInSetRequests)).toLong();
        snmpInfo.snmpOutNoSuchNames = pdu.getVariable(new OID(SnmpConstant.snmpOutNoSuchNames)).toLong();
        return snmpInfo;
    }
}
