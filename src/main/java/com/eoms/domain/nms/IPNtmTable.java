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

@Getter
@Setter
@Entity
@Table(name = "nms_ntm_table")
public class IPNtmTable extends BaseEntity<String> {

    private String terminalId;

    @OneToOne
    @JoinColumn(name = "terminalId",updatable = false,insertable = false)
    private Terminal terminal;

    private Integer ipNetToMediaIfIndex;
//    OID：ipNetToMediaEntry.1
//    对象类型：Integer
//    访问模式：读写
//    描述：用来标识符本地接口的索引，网络地址和物理地址映射是从该接口获
//    得的ipNetToMediaTable表包含以下对象的集合：

    private String ipNetToMediaPhysAddress;
//    OID：ipNetToMediaEntry.2
//    对象类型：PhysAddress
//    访问模式：读写
//    描述：介质相关的物理地址；例如，Ethernet地址

    private String ipNetToMediaNetAddress;
//    OID：ipNetToMediaEntry.3
//    对象类型：IpAddress
//    访问模式：读写
//    描述：对应该介质相关物理地址的IP地址

    private Long ipNetToMediaType;
//    OID：ipNetToMediaEntry.4
//    对象类型：Counter
//    访问模式：读写
//    描述：产生地址映射的类型。类型包括：other(1)、invalid(2)、dynamic(3)
//    和static(4)

    private String ip;

    private IPNtmTable get(List<IPNtmTable> list,String ip) {
        for (IPNtmTable ipNtmTable : list) {
            if (ipNtmTable.getIp().equals(ip)) {
                return ipNtmTable;
            }
        }
        return null;
    }

    public List<IPNtmTable> toEntity(List<IPNtmTable> ipNtmTableList, PDU pdu) {
        VariableBinding variableBinding = pdu.getVariableBindings().elementAt(0);
        String str = null;
        String ip = null;
        if (!(variableBinding.getOid().toString().equals("1.3.6.1.2.1.4.23.0"))) {
            str = variableBinding.getOid().toString().substring(0, 20);
            ip = variableBinding.getOid().toString().substring(21);
        }
        IPNtmTable ipNtmTable = get(ipNtmTableList,ip);
        if (ipNtmTable == null) {
            ipNtmTable = new IPNtmTable();
            ipNtmTable.setIp(ip);
            ipNtmTableList.add(ipNtmTable);
        }
        String value = variableBinding.getVariable().toString();
        if (str == null) {
            return ipNtmTableList;
        }
        switch (str) {
            case SnmpConstant.ipNetToMediaIfIndex:
                ipNtmTable.setIpNetToMediaIfIndex(new Integer(value));
                break;
            case SnmpConstant.ipNetToMediaPhysAddress:
                ipNtmTable.setIpNetToMediaPhysAddress(value);
                break;
            case SnmpConstant.ipNetToMediaNetAddress:
                ipNtmTable.setIpNetToMediaNetAddress(value);
                break;
            case SnmpConstant.ipNetToMediaType:
                ipNtmTable.setIpNetToMediaType(new Long(value));
                break;
        }
        return ipNtmTableList;
    }
}
