package com.eoms.domain.nms;

import com.eoms.domain.BaseEntity;
import com.eoms.snmp.SnmpConstant;
import lombok.Getter;
import lombok.Setter;
import org.snmp4j.PDU;
import org.snmp4j.smi.VariableBinding;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "nms_tcp_table")
public class TCPConnectTable extends BaseEntity<String> {

    private String terminalId;

    @OneToOne
    @JoinColumn(name = "terminalId", insertable = false, updatable = false)
    private Terminal terminal;

    private Integer tcpConnState;

    private String tcpConnLocalAddress;

    private String tcpConnLocalPort;

    private String tcpConnRemAddress;

    private String tcpConnRemPort;

    private String ip;

    private TCPConnectTable get(List<TCPConnectTable> list, String ip) {
        for (TCPConnectTable tcpConnectTable : list) {
            if (tcpConnectTable.getIp().equals(ip) || ("." + tcpConnectTable.getIp()).equals(ip)) {
                return tcpConnectTable;
            }
        }
        return null;
    }

    public List<TCPConnectTable> toEntity(List<TCPConnectTable> list, PDU pdu) {
        VariableBinding variableBinding = pdu.getVariableBindings().elementAt(0);
        String str = null;
        String ip = null;
        try {
            str = variableBinding.getOid().toString().substring(0, 20);
            ip = variableBinding.getOid().toString().substring(21);
        }catch (StringIndexOutOfBoundsException e){
            str = null;
            ip = null;
        }

        String value = variableBinding.getVariable().toString();
        TCPConnectTable tcpConnectTable = get(list, ip);
        if (tcpConnectTable == null) {
            tcpConnectTable = new TCPConnectTable();
            tcpConnectTable.setIp(ip);
            list.add(tcpConnectTable);
        }
        if (str == null) {
            return list;
        }
        switch (str) {
            case SnmpConstant.tcpConnState:
                tcpConnectTable.setTcpConnState(Integer.parseInt(value));
                break;
            case SnmpConstant.tcpConnLocalAddress:
                tcpConnectTable.setTcpConnLocalAddress(value);
                break;
            case SnmpConstant.tcpConnLocalPort:
                tcpConnectTable.setTcpConnLocalPort(value);
                break;
            case SnmpConstant.tcpConnRemAddress:
                tcpConnectTable.setTcpConnRemAddress(value);
                break;
            case SnmpConstant.tcpConnRemPort:
                tcpConnectTable.setTcpConnRemPort(value);
                break;
        }
        return list;
    }
}
