package com.eoms.domain.nms;

import com.eoms.domain.BaseEntity;
import com.eoms.snmp.SnmpConstant;
import lombok.Getter;
import lombok.Setter;
import org.snmp4j.PDU;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "nms_route_table")
public class IPRouteTable extends BaseEntity<String> {

    private String terminalId;

    @OneToOne
    @JoinColumn(name = "terminalId",updatable = false,insertable = false)
    private Terminal terminal;

    private String ipRouteDest;
//    OID：ipRouteEntry.1
//    对象类型：IpAddress
//    访问模式：读写
//    描述：该路由定义的目标IP地址

    private Integer ipRouteIfIndex;
//    OID：ipRouteEntry.2
//    对象类型：Integer
//    访问模式：读写
//    描述：标识符本地接口的索引，可通过该接口到达该路由的下一跳

    private Integer ipRouteMetric1;
//    OID：ipRouteEntry.3
//    对象类型：Integer
//    访问模式：读写
//    描述：该路由的主路由选择度量

    private Integer ipRouteMetric2;
//    OID：ipRouteEntry.4
//    对象类型：Integer
//    访问模式：读写
//    描述：该路由的一个可选路由选择度量

    private Integer ipRouteMetric3;
//    OID：ipRouteEntry.5
//    对象类型：Integer
//    访问模式：读写
//    描述：该路由的一个可选路由选择度量

    private Integer ipRouteMetric4;
//    OID：ipRouteEntry.6
//    对象类型：Integer
//    访问模式：读写
//    描述：该路由的一个可选路由选择度量

    private String ipRouteNextHop;
//    OID：ipRouteEntry.7
//    对象类型：IpAddress
//    访问模式：读写
//    描述：该路由下一跳的IP地址

    private Integer ipRouteType;
//    OID：ipRouteEntry.8
//    对象类型：Integer
//    访问模式：读写
//    描述：该路由的类型标识符。有效的类型包括：other(1)、invalid(2)、direct(3)和indirect(4)

    private Integer ipRouteProto;
//    OID：ipRouteEntry.9
//    对象类型：Integer
//    访问模式：读写
//    描述：用于了解路由的路由选择机制

    private String ipRouteAge;
//    OID：ipRouteEntry.10
//    对象类型：IpAddress
//    访问模式：读写
//    描述：从路由被更新或验证以来经过的时间（秒）

    private String ipRouteMask;
//    OID：ipRouteEntry.11
//    对象类型：IpAddress
//    访问模式：读写
//    描述：目标地址使用（与）的掩码地址

    private Integer ipRouteMetric5;
//    OID：ipRouteEntry.12
//    对象类型：Integer
//    访问模式：读写
//    描述：该路由的一个可选路由选择度量

    private String ipRouteInfo;
//    OID：ipRouteEntry.13
//    对象类型：ObjectIdentifier
//    访问模式：只读
//    描述：MIB定义指向负责该路由的路由选择协议

    private String ip;

    private IPRouteTable get(List<IPRouteTable> list,String ip) {
        for (IPRouteTable ipRouteTable : list) {
            if (ipRouteTable.getIp().equals(ip) || ("."+ipRouteTable.getIp()).equals(ip)) {
                return ipRouteTable;
            }
        }
        return null;
    }

    public List<IPRouteTable> toEntity(List<IPRouteTable> list, PDU pdu) {
        VariableBinding variableBinding = pdu.getVariableBindings().elementAt(0);
        String[] strArr = variableBinding.getOid().toString().split("\\.");
        String str;
        String ip;
        if (Integer.parseInt(strArr[9]) >= 10) {
            str = variableBinding.getOid().toString().substring(0,21);
            ip = variableBinding.getOid().toString().substring(22);
        } else {
            str = variableBinding.getOid().toString().substring(0, 20);
            ip = variableBinding.getOid().toString().substring(21);
        }
        IPRouteTable ipRouteTable = get(list,ip);
        if (ipRouteTable == null && !ip.equals("1.224.0.0.2")) {
            ipRouteTable = new IPRouteTable();
            ipRouteTable.setIp(ip);
            list.add(ipRouteTable);
        }

        String value = variableBinding.getVariable().toString();
        switch (str) {
            case SnmpConstant.ipRouteDest:
                ipRouteTable.setIpRouteDest(value);
                break;
            case SnmpConstant.ipRouteIfIndex:
                ipRouteTable.setIpRouteIfIndex(new Integer(value));
                break;
            case SnmpConstant.ipRouteMetric1:
                ipRouteTable.setIpRouteMetric1(new Integer(value));
                break;
            case SnmpConstant.ipRouteMetric2:
                ipRouteTable.setIpRouteMetric2(new Integer(value));
                break;
            case SnmpConstant.ipRouteMetric3:
                ipRouteTable.setIpRouteMetric3(new Integer(value));
                break;
            case SnmpConstant.ipRouteMetric4:
                ipRouteTable.setIpRouteMetric4(new Integer(value));
                break;
            case SnmpConstant.ipRouteNextHop:
                ipRouteTable.setIpRouteNextHop(value);
                break;
            case SnmpConstant.ipRouteType:
                ipRouteTable.setIpRouteType(new Integer(value));
                break;
            case SnmpConstant.ipRouteProto:
                ipRouteTable.setIpRouteProto(new Integer(value));
                break;
            case SnmpConstant.ipRouteAge:
                ipRouteTable.setIpRouteAge(value);
                break;
            case SnmpConstant.ipRouteMask:
                ipRouteTable.setIpRouteMask(value);
                break;
            case SnmpConstant.ipRouteMetric5:
                ipRouteTable.setIpRouteMetric5(new Integer(value));
                break;
            case SnmpConstant.ipRouteInfo:
                ipRouteTable.setIpRouteInfo(value);
                break;
        }
        return list;
    }
}

