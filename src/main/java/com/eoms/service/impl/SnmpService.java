package com.eoms.service.impl;

import com.eoms.domain.nms.Terminal;
import com.eoms.snmp.SnmpUtils;
import org.snmp4j.PDU;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SnmpService {

    public Terminal getDeviceInfo(String ip) {
        List<OID> oidList = new ArrayList<>();
        oidList.add(SnmpConstants.sysDescr);
        oidList.add(SnmpConstants.sysObjectID);
        oidList.add(SnmpConstants.sysUpTime);
        oidList.add(SnmpConstants.sysContact);
        oidList.add(SnmpConstants.sysName);
        oidList.add(SnmpConstants.sysLocation);
        oidList.add(SnmpConstants.sysServices);
        PDU responsePDU = SnmpUtils.snmpGetListByOid(ip,"public",oidList);
        Terminal terminal = new Terminal();
        if (responsePDU == null){
            return null;
        }
        return terminal.toEntity(terminal,responsePDU);
    }

    public static void main(String[] args) {
        SnmpService snmpService = new SnmpService();
        snmpService.getDeviceInfo("192.168.56.3");
    }
}
