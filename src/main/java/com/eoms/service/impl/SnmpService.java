package com.eoms.service.impl;

import com.eoms.domain.dto.SystemDTO;
import com.eoms.domain.nms.*;
import com.eoms.service.*;
import com.eoms.snmp.SnmpConstant;
import com.eoms.snmp.SnmpUtils;
import org.snmp4j.PDU;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SnmpService {

    @Autowired
    private TerminalService terminalService;

    @Autowired
    private InterfaceSevice interfaceSevice;

    @Autowired
    private IPRouteTableService ipRouteTableService;

    @Autowired
    private InterfaceDetailService interfaceDetailService;

    @Autowired
    private SysAbilityService sysAbilityService;

    public Terminal getDeviceInfo(String ip) {
        List<OID> oidList = new ArrayList<>();
        oidList.add(SnmpConstants.sysDescr);
        oidList.add(SnmpConstants.sysObjectID);
        oidList.add(SnmpConstants.sysUpTime);
        oidList.add(SnmpConstants.sysContact);
        oidList.add(SnmpConstants.sysName);
        oidList.add(SnmpConstants.sysLocation);
        oidList.add(SnmpConstants.sysServices);
        oidList.add(new OID("1.3.6.1.2.1.2.2.1.16.16"));
        PDU responsePDU = SnmpUtils.snmpGetListByOid(ip,"public",oidList);
        Terminal terminal = new Terminal();
        if (responsePDU == null){
            return null;
        }
        return terminal.toEntity(terminal,responsePDU);
    }

    private Integer getIfNumber(String ip) {
        PDU responsePDU = SnmpUtils.snmpGet(ip,"public","1.3.6.1.2.1.2.1.0");
        Variable variable = responsePDU.getVariable(new OID("1.3.6.1.2.1.2.1.0"));
        return new Integer(variable.toString());
    }

    public Interface getInterfaceInfo(String ip,int index) {
        List<OID> oidList = new ArrayList<>();
        for (int i = 1;i <= 22; i++) {
            oidList.add(new OID("1.3.6.1.2.1.2.2.1."+i+"."+index));
        }
        PDU responsePDU = SnmpUtils.snmpGetListByOid(ip,"public",oidList);
        if (responsePDU.getErrorStatus() != 0) {
            return null;
        }
        Interface itf = new Interface();
        itf.setIfIndex(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.1."+index)).toInt());
        OctetString octetString = (OctetString)responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.2."+index));
        byte bytes[] = octetString.toByteArray();
        String str = null;
        try {
            str = new String(bytes,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        itf.setIfDescr(str);
        itf.setIfType(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.3."+index)).toString());
        itf.setIfMtu(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.4."+index)).toInt());
        itf.setIfSpeed(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.5."+index)).toInt());
        itf.setIfPhysAddress(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.6."+index)).toString());
        itf.setIfAdminStatus(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.7."+index)).toString());
        itf.setIfOperStatus(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.8."+index)).toString());
        itf.setIfLastChange(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.9."+index)).toLong());
        itf.setIfInOctets(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.10."+index)).toLong());
        itf.setIfInUcastPkts(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.11."+index)).toInt());
        itf.setIfInNUcastPkts(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.12."+index)).toInt());
        itf.setIfInDiscards(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.13."+index)).toInt());
        itf.setIfInErrors(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.14."+index)).toInt());
        itf.setIfInUnknownProtos(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.15."+index)).toInt());
        itf.setIfOutOctets(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.16."+index)).toLong());
        itf.setIfOutUcastPkts(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.17."+index)).toInt());
        itf.setIfOutNUcastPkts(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.18."+index)).toInt());
        itf.setIfOutDiscards(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.19."+index)).toInt());
        itf.setIfOutErrors(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.20."+index)).toInt());
        itf.setIfOutQLen(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.21."+index)).toInt());
        itf.setIfSpecific(responsePDU.getVariable(new OID("1.3.6.1.2.1.2.2.1.22."+index)).toString());
        return itf;
    }
    // params id 终端id
    public List<Interface> getInterfaceList(String id) {
        Terminal terminal = isExisted(id);
        String ip = terminal.getTerminalIp();
        int ifNumber = getIfNumber(ip);
        List<Interface> interfaceList = new ArrayList<>();
        interfaceList.add(new Interface());
        Interface itf;
        List<Interface> existedList = interfaceSevice.findAllByTerminalId(terminal.getId());
        if (existedList.size() == 0) { //已存在接口列表大小为0 肯定是新增
            for (int index = 1;index <= ifNumber;index++) {
                itf = getInterfaceInfo(ip,index);
                if (itf == null) {
                    itf = new Interface();
                }
                itf.setTerminalId(id);
                interfaceSevice.save(itf);
                if (itf.getIfIndex() == null) {
                    ifNumber++;
                    continue;
                }
                interfaceList.add(itf);
            }
        }else if (existedList.size() >= ifNumber) { //已存在接口大小 == snmp接口数 为更新，不考虑同时增多或减少相同个数
            for (int index = 1;index <= ifNumber;index++) {
                itf = getInterfaceInfo(ip,index);
                if (itf == null) {
                    itf = new Interface();
                }
                itf.setTerminalId(id);
                itf.setId(existedList.get(index-1).getId());
                interfaceSevice.save(itf);
                if (itf.getIfIndex() == null) {
                    ifNumber++;
                    continue;
                }
                interfaceList.add(itf);
            }
        }
        // TODO: 2018/5/6  总接口数新增 处理 更新原有数据，加入新的接口记录 
        return interfaceList;
    }

    // params id 终端id
    public List<Interface> getInterfaceList(Terminal terminal) {
        String id = terminal.getId();
        String ip = terminal.getTerminalIp();
        int ifNumber = getIfNumber(ip);
        List<Interface> interfaceList = new ArrayList<>();
        interfaceList.add(new Interface());
        Interface itf;
        List<Interface> existedList = interfaceSevice.findAllByTerminalId(terminal.getId());
        if (existedList.size() == 0) { //已存在接口列表大小为0 肯定是新增
            for (int index = 1;index <= ifNumber;index++) {
                itf = getInterfaceInfo(ip,index);
                if (itf == null) {
                    itf = new Interface();
                }
                itf.setTerminalId(id);
                interfaceSevice.save(itf);
                if (itf.getIfIndex() == null) {
                    ifNumber++;
                    continue;
                }
                interfaceList.add(itf);
            }
        }else if (existedList.size() >= ifNumber) { //已存在接口大小 == snmp接口数 为更新，不考虑同时增多或减少相同个数
            for (int index = 1;index <= ifNumber;index++) {
                itf = getInterfaceInfo(ip,index);
                if (itf == null) {
                    itf = new Interface();
                }
                itf.setTerminalId(id);
                itf.setId(existedList.get(index-1).getId());
                interfaceSevice.save(itf);
                if (itf.getIfIndex() == null) {
                    ifNumber++;
                    continue;
                }
                interfaceList.add(itf);
            }
        }
        return interfaceList;
    }

    private Terminal isExisted(String id) {
        Terminal terminal = terminalService.getOne(id);
        if (terminal == null) {
            return null;
        }
        return terminal;
    }

    // params terminal 终端
//    获取活动的接口列表
    private List<Interface> getActiveInterfaceList(Terminal terminal) {
        String ip = terminal.getTerminalIp();
        int ifNumber = getIfNumber(ip);
        List<Interface> interfaceList = new ArrayList<>();
        List<Interface> existedList = interfaceSevice.findAllByTerminalId(terminal.getId());
        for (Interface itf : existedList) {
            System.out.print(itf.toString());
            if (itf != null && itf.getIfInOctets() != null && itf.getIfInOctets() > 0) {
                interfaceList.add(itf);
            }
        }
        return interfaceList;
    }

    //计算接口性能参数
    public InterfaceDetail calInterface(Interface itf,Interface itfY){
        InterfaceDetail interfaceDetail = new InterfaceDetail();
        interfaceDetail.setInterfaceId(itf.getId());
        //计算接口利用率
        PDU res = SnmpUtils.snmpGet(itf.getTerminal().getTerminalIp(),"public","1.3.6.1.2.1.1.3.0");
        TimeTicks timeTicks = (TimeTicks)res.getVariable(new OID("1.3.6.1.2.1.1.3.0"));
        Long sysuptime = timeTicks.toMilliseconds()/1000; //单位秒
//        bps 总字节数
        Long totalBits = (itfY.getIfInOctets()-itf.getIfInOctets()) + (itfY.getIfOutOctets()-itf.getIfOutOctets());
        System.out.print("1秒钟的输入输出总字节数 Byte:"+totalBits+"Bit:"+totalBits*8);
        System.out.print("该接口速率为Byte p s:"+itf.getIfSpeed()/8+"bps:"+itf.getIfSpeed());
        Long bps = totalBits/60;
        BigDecimal itfUtilz = new BigDecimal(bps*8*100).divide(new BigDecimal(itf.getIfSpeed()),4, BigDecimal.ROUND_HALF_EVEN);
        interfaceDetail.setInterfaceUtilization(itfUtilz.toString());
        Integer intervalData = (itfY.getIfInUcastPkts()-itf.getIfInUcastPkts())+(itfY.getIfInNUcastPkts()-itf.getIfInNUcastPkts());
        Integer intervalDatab = (itfY.getIfOutUcastPkts()-itf.getIfOutUcastPkts())+(itfY.getIfOutNUcastPkts()-itf.getIfOutNUcastPkts());
        if (intervalData != 0 && intervalDatab != 0) {
            BigDecimal inErrorRate = new BigDecimal((itfY.getIfInErrors() - itf.getIfInErrors()) * 100).divide(new BigDecimal(intervalData), 4, BigDecimal.ROUND_HALF_EVEN);
            interfaceDetail.setInputErrorRate(inErrorRate.toString());
            BigDecimal outErrorRate = new BigDecimal((itf.getIfOutErrors() - itf.getIfOutErrors()) * 100).divide(new BigDecimal(intervalDatab), 4, BigDecimal.ROUND_HALF_EVEN);
            interfaceDetail.setOutputErrorRate(outErrorRate.toString());
            BigDecimal inputLossRate = new BigDecimal((itfY.getIfInDiscards() - itf.getIfInDiscards()) * 100).divide(new BigDecimal(intervalData), 4, BigDecimal.ROUND_HALF_EVEN);
            interfaceDetail.setInputLossRate(inputLossRate.toString());
            BigDecimal outLossRate = new BigDecimal((itfY.getIfOutDiscards() - itf.getIfOutDiscards()) * 100).divide(new BigDecimal(intervalDatab), 4, BigDecimal.ROUND_HALF_EVEN);
            interfaceDetail.setOutputLossRate(outLossRate.toString());
            Long inputFlow = (itfY.getIfInOctets()-itf.getIfInOctets())*8/60; //单位bps
            Long outputFlow = (itfY.getIfOutOctets()-itf.getIfOutOctets())*8/60;
            interfaceDetail.setInputFlow(inputFlow.toString());
            interfaceDetail.setOutputFlow(outputFlow.toString());
        }
        return interfaceDetail;
    }

//    计算所有终端活动接口的性能参数
    public void calAllTerInt() {
        List<Terminal> terminalList = terminalService.findAll();
        List<Interface> interfaceList;
        List<Interface> interfaceListY = new ArrayList<>();
        for (Terminal terminal : terminalList) {
            interfaceList = getActiveInterfaceList(terminal);
            try {
                Thread.currentThread().sleep(25000);
                getInterfaceList(terminal);
                interfaceListY = getActiveInterfaceList(terminal);
                //获取2个时间点的接口数据以计算
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (interfaceListY.size() == 0) {
                return;
            }
            for (int i = 0;i<interfaceList.size();i++) {
                InterfaceDetail interfaceDetail = calInterface(interfaceList.get(i),interfaceListY.get(i));
                interfaceDetailService.save(interfaceDetail);
            }
        }
    }

    public IPGroup getIPGoup(String ip) {
        List<String> oidList = new ArrayList<>();
        oidList.add(SnmpConstant.ipForwarding);
        oidList.add(SnmpConstant.ipDefaultTTL);
        oidList.add(SnmpConstant.ipForwDatagrams);
        oidList.add(SnmpConstant.ipFragOK);
        oidList.add(SnmpConstant.ipFragsCreates);
        oidList.add(SnmpConstant.ipFragsFails);
        oidList.add(SnmpConstant.ipInaddrErrors);
        oidList.add(SnmpConstant.ipInDelivers);
        oidList.add(SnmpConstant.ipInDiscards);
        oidList.add(SnmpConstant.ipInHdrError);
        oidList.add(SnmpConstant.ipInReceives);
        oidList.add(SnmpConstant.ipInUnknownProtos);
        oidList.add(SnmpConstant.ipOutDiscards);
        oidList.add(SnmpConstant.ipOutNoRoutes);
        oidList.add(SnmpConstant.ipOutRequests);
        oidList.add(SnmpConstant.ipReasmFails);
        oidList.add(SnmpConstant.ipReasmReqds);
        oidList.add(SnmpConstant.ipReasmOKs);
        oidList.add(SnmpConstant.ipReasmTimeout);
        oidList.add(SnmpConstant.ipRoutingDiscards);
        PDU responsePDU = SnmpUtils.snmpGetList(ip,"public",oidList);
        IPGroup ipGroup = new IPGroup();
        if (responsePDU == null){
            return null;
        }
        return ipGroup.toEntity(ipGroup,responsePDU);
    }

    public List<IPRouteTable> getIPRouteTable(String ip) {
        List<PDU> pduList = SnmpUtils.snmpWalk(ip,"public","1.3.6.1.2.1.4.21");
        List<IPRouteTable> ipRouteTableList = new ArrayList<>();
        IPRouteTable ipRouteTable = new IPRouteTable();
        for (int i = 0 ;i<pduList.size(); i++){
            ipRouteTableList = ipRouteTable.toEntity(ipRouteTableList,pduList.get(i));
        }
        return ipRouteTableList;
    }

    public List<IPNtmTable> getIPNtmTable(String ip) {
        List<PDU> pduList = SnmpUtils.snmpWalk(ip,"public","1.3.6.1.2.1.4.22");
        List<IPNtmTable> ipNtmTableList = new ArrayList<>();
        IPNtmTable ipNtmTable = new IPNtmTable();
        for (int i = 0 ;i<pduList.size(); i++){
            ipNtmTableList = ipNtmTable.toEntity(ipNtmTableList,pduList.get(i));
        }
        return ipNtmTableList;
    }

    public TCPGroup getTCPGoup(String ip) {
        List<String> oidList = new ArrayList<>();
        oidList.add(SnmpConstant.tcpRtoAlgorithm);
        oidList.add(SnmpConstant.tcpRtoMin);
        oidList.add(SnmpConstant.tcpRtoMax);
        oidList.add(SnmpConstant.tcpMaxConn);
        oidList.add(SnmpConstant.tcpActiveOpens);
        oidList.add(SnmpConstant.tcpPassiveOpens);
        oidList.add(SnmpConstant.tcpAttemptFails);
        oidList.add(SnmpConstant.tcpEstabResets);
        oidList.add(SnmpConstant.tcpCurrEstab);
        oidList.add(SnmpConstant.tcpInSegs);
        oidList.add(SnmpConstant.tcpOutSegs);
        oidList.add(SnmpConstant.tcpRetransSegs);
        oidList.add(SnmpConstant.tcpInErrs);
        oidList.add(SnmpConstant.tcpOutRsts);
        PDU responsePDU = SnmpUtils.snmpGetList(ip,"public",oidList);
        TCPGroup tcpGroup = new TCPGroup();
        if (responsePDU == null){
            return null;
        }
        tcpGroup = tcpGroup.toEntity(tcpGroup,responsePDU);
        return tcpGroup;
    }

    public List<TCPConnectTable> getTCPTable(String ip){
        List<PDU> pduList = SnmpUtils.snmpWalk(ip,"public","1.3.6.1.2.1.6.13");
        List<TCPConnectTable> tcpConnectTableList = new ArrayList<>();
        TCPConnectTable tcpConnectTable = new TCPConnectTable();
        for (int i = 0 ;i<pduList.size(); i++){
            tcpConnectTableList = tcpConnectTable.toEntity(tcpConnectTableList,pduList.get(i));
        }
        return tcpConnectTableList;
    }

    public IcmpUdp getIcmpUdp(String ip) {
        List<String> oidList = new ArrayList<>();
        oidList.add(SnmpConstant.icmpInErrors);
        oidList.add(SnmpConstant.icmpInMsgs);
        oidList.add(SnmpConstant.icmpOutErrors);
        oidList.add(SnmpConstant.icmpOutMsgs);
        oidList.add(SnmpConstant.udpInDatagrams);
        oidList.add(SnmpConstant.udpInErrors);
        oidList.add(SnmpConstant.udpOutDatagrams);
        PDU responsePDU = SnmpUtils.snmpGetList(ip,"public",oidList);
        IcmpUdp icmpUdp = new IcmpUdp();
        if (responsePDU == null) {
            return null;
        }
        icmpUdp = icmpUdp.toEntity(icmpUdp,responsePDU);
        return  icmpUdp;
    }

    public SnmpInfo getSnmpInfo(String ip) {
        List<String> oidList = new ArrayList<>();
        oidList.add(SnmpConstant.snmpInPkts);
        oidList.add(SnmpConstant.snmpOutPkts);
        oidList.add(SnmpConstant.snmpInBadVersions);
        oidList.add(SnmpConstant.snmpInBadCommunityNames);
        oidList.add(SnmpConstant.snmpInBadCommunityUses);
        oidList.add(SnmpConstant.snmpInGetRequests);
        oidList.add(SnmpConstant.snmpInSetRequests);
        oidList.add(SnmpConstant.snmpOutNoSuchNames);
        PDU responsePDU = SnmpUtils.snmpGetList(ip,"public",oidList);
        SnmpInfo snmpInfo = new SnmpInfo();
        if (responsePDU == null) {
            return null;
        }
        snmpInfo = snmpInfo.toEntity(snmpInfo,responsePDU);
        return  snmpInfo;
    }

    public HrSystem getHySystem(String ip) {
        List<String> oidList = new ArrayList<>();
        oidList.add(SnmpConstant.hrSystemDate);
        oidList.add(SnmpConstant.hrSystemNumUsers);
        oidList.add(SnmpConstant.hrSystemProcesses);
        oidList.add(SnmpConstant.hrMemorySize);
        PDU responsePDU = SnmpUtils.snmpGetList(ip,"public",oidList);
        HrSystem hrSystem = new HrSystem();
        if (responsePDU == null) {
            return null;
        }
        hrSystem = hrSystem.toEntity(hrSystem,responsePDU);
        return  hrSystem;
    }

    public SystemDTO get(Terminal terminal) {
        SystemDTO systemDTO = SnmpUtils.collectMemory(terminal.getTerminalIp());
        systemDTO.setCpuUzi(SnmpUtils.collectCPU(terminal.getTerminalIp()));
        systemDTO.setDiskUzi(SnmpUtils.collectDisk(terminal.getTerminalIp()));
        SysAbility sysAbility = new SysAbility();
        BeanUtils.copyProperties(systemDTO,sysAbility);
        sysAbility.setTerminalId(terminal.getId());
        sysAbilityService.save(sysAbility);
        return systemDTO;
    }

    public static void main(String[] args) {
        SnmpService snmpService = new SnmpService();
//        SnmpUtils.snmpGet("127.0.0.1","public","1.3.6.1.2.1.4.21.1.1.127.0.0.1");
//        snmpService.getHySystem("192.168.0.106");
//            snmpService.getDeviceInfo("192.168.0.101");
//        SnmpUtils.collectDisk();
//        SnmpUtils.collectMemory();

//        List<Interface> interfaceList = new ArrayList<>();
//        interfaceList =  snmpService.getInterfaceList("127.0.0.1");
//        System.out.print(interfaceList);
//        snmpService.calAllTerInt();
    }
}
