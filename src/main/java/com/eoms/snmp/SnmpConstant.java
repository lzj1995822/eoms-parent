package com.eoms.snmp;

import org.snmp4j.smi.OID;

public final class SnmpConstant {
    public static final String sysDescr = "1.3.6.1.2.1.1.1.0";
    public static final String sysObjectID = "1.3.6.1.2.1.1.2.0";
    public static final String sysUpTime = "1.3.6.1.2.1.1.3.0";
    public static final String sysContact = "1.3.6.1.2.1.1.4.0";
    public static final String sysName = "1.3.6.1.2.1.1.5.0";
    public static final String sysLocation = "1.3.6.1.2.1.1.6.0";
    public static final String sysServices = "1.3.6.1.2.1.1.7.0";

    public static final String ipForwarding = "1.3.6.1.2.1.4.1.0";
    //OID：ip.1   1.3.6.1.2.1.4.1
    //对象类型：Integer
    //访问模式：读写
    //描述：指出系统是否作为一个IP网关（路由器）或者仅作为一个不提供转
    //发服务的正规主机。可取的值有Forwarding(1)和notForwarding(2)
    public static final String ipDefaultTTL = "1.3.6.1.2.1.4.2.0";
    //OID：ip1.3.6.1.2.1.4.2
    //对象类型：Integer
    //访问模式：读写
    //描述：置于IP报文的TTL字段中的生存期值
    public static final String ipInReceives = "1.3.6.1.2.1.4.3.0";
    //OID：ip1.3.6.1.2.1.4.3
    //对象类型：Counter
    //访问模式：只读
    //描述：从系统所有可操作接口接收的输入报文的总数
    public static final String ipInHdrError = "1.3.6.1.2.1.4.4.0";
    //OID：ip1.3.6.1.2.1.4.4
    //对象类型：Counter
    //访问模式：只读
    //描述：由于IP报文头部错误而丢弃的输入报文数量
    public static final String ipInaddrErrors = "1.3.6.1.2.1.4.5.0";
    //OID：ip1.3.6.1.2.1.4.5
    //对象类型：Counter
    //访问模式：只读
    //描述：对该系统来说，因为最终IP目的地址无效而被丢弃的输入报文数量
    public static final String ipForwDatagrams = "1.3.6.1.2.1.4.6.0";
    //OID：ip1.3.6.1.2.1.4.6
    //对象类型：Counter
    //访问模式：只读
    //描述：本地系统作为网关或路由器试图转发的报文数量
    public static final String ipInUnknownProtos = "1.3.6.1.2.1.4.7.0";
    //OID：ip1.3.6.1.2.1.4.7
    //对象类型：NetworkAddress
    //访问模式：只读
    //描述：从网络上成功接收，但由于系统对报文所请求的网络层协议不支持或
    //者未知，而丢弃的报文数量
    //
    //
    public static final String ipInDiscards = "1.3.6.1.2.1.4.8.0";
    //OID：ip1.3.6.1.2.1.4.8
    //对象类型：Counter
    //访问模式：只读
    //描述：由于缺乏缓冲空间或其他与报文自身无关的条件，而丢弃的输入报文
    //的数量
    public static final String ipInDelivers = "1.3.6.1.2.1.4.9.0";
    //OID：ip1.3.6.1.2.1.4.9
    //对象类型：Counter
    //访问模式：只读
    //描述：成功传递给上层协议的输入报文的数量
    public static final String ipOutRequests = "1.3.6.1.2.1.4.10.0";
    //OID：ip1.3.6.1.2.1.4.10
    //对象类型：Counter
    //访问模式：只读
    //描述：上层协议为发送而传递给IP协议的IP报文的数量
    public static final String ipOutDiscards = "1.3.6.1.2.1.4.11.0";
    //OID：ip1.3.6.1.2.1.4.11
    //对象类型：Counter
    //访问模式：只读
    //描述：由于缺乏缓冲空间或其他与报文自身无关的条件，而丢弃的输出报文
    //的数量
    //
    public static final String ipOutNoRoutes = "1.3.6.1.2.1.4.12.0";
    //OID：ip1.3.6.1.2.1.4.12
    //对象类型：Counter
    //访问模式：只读
    //描述：因为没有路由到所需目标网络，而丢弃的报文数量
    public static final String ipReasmTimeout = "1.3.6.1.2.1.4.13.0";
    //OID：ip1.3.6.1.2.1.4.13
    //对象类型：Counter
    //访问模式：只读
    //描述：输入的IP分组报文在它们被重组之前保留的时间间隔（以秒为单位）
    public static final String ipReasmReqds = "1.3.6.1.2.1.4.14.0";
    //OID：ip1.3.6.1.2.1.4.14
    //对象类型：Counter
    //访问模式：只读
    //描述：接收到的必须重组的IP分组报文数量
    public static final String ipReasmOKs = "1.3.6.1.2.1.4.15.0";
    //OID：ip1.3.6.1.2.1.4.15
    //对象类型：Counter
    //访问模式：只读
    //描述：成功重组的IP分组报文的数量
    public static final String ipReasmFails = "1.3.6.1.2.1.4.16.0";
    //OID：ip1.3.6.1.2.1.4.16
    //对象类型：Counter
    //访问模式：只读
    //描述：检测到的重组失败的数量
    public static final String ipFragOK = "1.3.6.1.2.1.4.17.0";
    //OID：ip1.3.6.1.2.1.4.17
    //对象类型：Counter
    //访问模式：只读
    //描述：已经被成功分组的报文数量
    public static final String ipFragsFails = "1.3.6.1.2.1.4.18.0";
    //OID：ip1.3.6.1.2.1.4.18
    //对象类型：Counter
    //访问模式：只读
    //描述：因为IP头部包含不分组标志，使得没有分组的报文数量
    public static final String ipFragsCreates = "1.3.6.1.2.1.4.19.0";
    //OID：ip1.3.6.1.2.1.4.19
    //对象类型：Counter
    //访问模式：只读
    //描述：该系统上产生的IP报文分组的数量
    //public static final String ipAddrTable
    //OID：ip.20
    //对象类型：SequenceofIpAddrEntry
    //访问模式：不可访问
    //描述：有关系统的IP地址的地址信息表
    //public static final String ipRouteTable（1）
    //OID：ip.21
    //对象类型：SequenceofIpRouteEntry
    //访问模式：不可访问
    //描述：到特定目标的路由
    //public static final String ipNetToMediaTable;
    //OID：ip.22
    //对象类型：SequenceofIpNetToMediaEntry
    //访问模式：不可访问
    //描述：IP地址和数据链路地址之间的映射
    public static final String ipRoutingDiscards = "1.3.6.1.2.1.4.23.0";
    //OID：ip1.3.6.1.2.1.4.23
    //对象类型：Counter
    //访问模式：只读
    //描述：尽管事实上有效，但被丢弃的报文数量
    //

    public static final String ipRouteDest = "1.3.6.1.2.1.4.21.1.1";
    public static final String ipRouteIfIndex = "1.3.6.1.2.1.4.21.1.2";
    public static final String ipRouteMetric1 = "1.3.6.1.2.1.4.21.1.3";
    public static final String ipRouteMetric2 = "1.3.6.1.2.1.4.21.1.4";
    public static final String ipRouteMetric3 = "1.3.6.1.2.1.4.21.1.5";
    public static final String ipRouteMetric4 = "1.3.6.1.2.1.4.21.1.6";
    public static final String ipRouteNextHop = "1.3.6.1.2.1.4.21.1.7";
    public static final String ipRouteType = "1.3.6.1.2.1.4.21.1.8";
    public static final String ipRouteProto = "1.3.6.1.2.1.4.21.1.9";
    public static final String ipRouteAge = "1.3.6.1.2.1.4.21.1.10";
    public static final String ipRouteMask = "1.3.6.1.2.1.4.21.1.11";
    public static final String ipRouteMetric5 = "1.3.6.1.2.1.4.21.1.12";
    public static final String ipRouteInfo = "1.3.6.1.2.1.4.21.1.13";


    public static final String ipNetToMediaIfIndex = "1.3.6.1.2.1.4.22.1.1";
    public static final String ipNetToMediaPhysAddress = "1.3.6.1.2.1.4.22.1.2";
    public static final String ipNetToMediaNetAddress = "1.3.6.1.2.1.4.22.1.3";
    public static final String ipNetToMediaType = "1.3.6.1.2.1.4.22.1.4";

    public static final String tcpRtoAlgorithm = "1.3.6.1.2.1.6.1.0";
    public static final String tcpRtoMin = "1.3.6.1.2.1.6.2.0";
    public static final String tcpRtoMax = "1.3.6.1.2.1.6.3.0";
    public static final String tcpMaxConn = "1.3.6.1.2.1.6.4.0";
    public static final String tcpActiveOpens = "1.3.6.1.2.1.6.5.0";
    public static final String tcpPassiveOpens = "1.3.6.1.2.1.6.6.0";
    public static final String tcpAttemptFails = "1.3.6.1.2.1.6.7.0";
    public static final String tcpEstabResets = "1.3.6.1.2.1.6.8.0";
    public static final String tcpCurrEstab = "1.3.6.1.2.1.6.9.0";
    public static final String tcpInSegs = "1.3.6.1.2.1.6.10.0";
    public static final String tcpOutSegs = "1.3.6.1.2.1.6.11.0";
    public static final String tcpRetransSegs = "1.3.6.1.2.1.6.12.0";
    public static final String tcpInErrs = "1.3.6.1.2.1.6.14.0";
    public static final String tcpOutRsts = "1.3.6.1.2.1.6.15.0";

    public static final String tcpConnState = "1.3.6.1.2.1.6.13.1.1";
    public static final String tcpConnLocalAddress = "1.3.6.1.2.1.6.13.1.2";
    public static final String tcpConnLocalPort = "1.3.6.1.2.1.6.13.1.3";
    public static final String tcpConnRemAddress = "1.3.6.1.2.1.6.13.1.4";
    public static final String tcpConnRemPort = "1.3.6.1.2.1.6.13.1.5";


}

