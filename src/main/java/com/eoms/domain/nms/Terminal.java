package com.eoms.domain.nms;

import com.eoms.domain.BaseEntity;
import com.eoms.snmp.SnmpConstant;
import lombok.Getter;
import lombok.Setter;
import org.snmp4j.PDU;
import org.snmp4j.smi.VariableBinding;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Vector;

@Getter
@Setter
@Entity
@Table(name = "nms_terminal")
public class Terminal extends BaseEntity<String>{

    private String  terminalName;

    private String  terminalType;

    private String  terminalDes;

    private String terminalUpTime;

    private String  terminalObjectId;

    private String  terminalContact;

    private String  terminalLocation;

    private Integer  terminalServices;

    private Integer  terminalNumUser;

    private Integer  terminalProcess;

    private String  terminalMac;

    private String  terminalIp;

    public Terminal toEntity(Terminal terminal,PDU pdu) {
        Vector<? extends VariableBinding> variableBindings = pdu.getVariableBindings();
        for (int i = 0;i < variableBindings.size();i++){
            switch (variableBindings.elementAt(i).getOid().toString()) {
                case SnmpConstant.sysDescr:
                    terminal.terminalDes = variableBindings.elementAt(i).getVariable().toString();
                    break;
                case SnmpConstant.sysObjectID:
                    terminal.terminalObjectId = variableBindings.elementAt(i).getVariable().toString();
                    break;
                case SnmpConstant.sysUpTime:
                    terminal.terminalUpTime = variableBindings.elementAt(i).getVariable().toString();
                    break;
                case SnmpConstant.sysContact:
                    terminal.terminalContact = variableBindings.elementAt(i).getVariable().toString();
                    break;
                case SnmpConstant.sysName:
                    terminal.terminalName = variableBindings.elementAt(i).getVariable().toString();
                    break;
                case SnmpConstant.sysLocation:
                    terminal.terminalLocation = variableBindings.elementAt(i).getVariable().toString();
                    break;
                case SnmpConstant.sysServices:
                    if (variableBindings.elementAt(i).getVariable().toString().matches("[0-9]+"))
                        terminal.terminalServices = new Integer(variableBindings.elementAt(i).getVariable().toString());
                    break;
            }
        }
        return terminal;
    }

}
