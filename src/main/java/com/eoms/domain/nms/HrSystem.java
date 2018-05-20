
package com.eoms.domain.nms;

import com.eoms.domain.BaseEntity;
import com.eoms.snmp.SnmpConstant;
import lombok.Getter;
import lombok.Setter;
import org.snmp4j.PDU;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.UnsupportedEncodingException;

@Getter
@Setter
@Entity
@Table(name = "nms_hr_table")
public class HrSystem extends BaseEntity<String> {

    private String terminalId;

    @OneToOne
    @JoinColumn(name = "terminalId",updatable = false,insertable = false)
    private Terminal terminal;

    private String hrSystemDate;
//    OID：[图片]1.3.6.1.[图片]2.1.25.1.2
//    对象类型：
//    访问模式：读写
//    描述：

    private String hrSystemNumUsers;
//    OID：[图片]1.3.6.1.[图片]2.1.25.1.5
//    对象类型：
//    访问模式：读写
//    描述：

    private String hrSystemProcesses;
//    OID：[图片]1.3.6.1.[图片]2.1.25.1.6
//    对象类型：
//    访问模式：读写
//    描述：

    private String hrMemorySize;
//    OID：[图片]1.3.6.1.[图片]2.1.25.2.2
//    对象类型：
//    访问模式：读写
//    描述：
    public HrSystem toEntity(HrSystem hrSystem, PDU pdu) {
        OctetString octetString = (OctetString)pdu.getVariable(new OID(SnmpConstant.hrSystemDate));
        String str = hexToDateTime(octetString.toString());
        hrSystem.hrSystemDate = str;
        hrSystem.hrSystemNumUsers = pdu.getVariable(new OID(SnmpConstant.hrSystemNumUsers)).toString();
        hrSystem.hrSystemProcesses = pdu.getVariable(new OID(SnmpConstant.hrSystemProcesses)).toString();
        hrSystem.hrMemorySize = pdu.getVariable(new OID(SnmpConstant.hrMemorySize)).toString();
        return hrSystem;
    }
    //十六进制转时间格式
    private static String hexToDateTime(String hexString) {
        if(hexString == null || "".equals(hexString))
            return "";
        String dateTime = "";
        try {
            byte[] values = OctetString.fromHexString(hexString).getValue();
            int year, month, day, hour, minute;

            year = values[0] * 256 + 256 + values[1];
            month = values[2];
            day = values[3];
            hour = values[4];
            minute = values[5];

            char format_str[] = new char[22];
            int index = 3;
            int temp = year;
            for (; index >= 0; index--) {
                format_str[index] = (char) (48 + (temp - temp / 10 * 10));
                temp /= 10;
            }
            format_str[4] = '-';
            index = 6;
            temp = month;
            for (; index >= 5; index--) {
                format_str[index] = (char) (48 + (temp - temp / 10 * 10));
                temp /= 10;
            }
            format_str[7] = '-';
            index = 9;
            temp = day;
            for (; index >= 8; index--) {
                format_str[index] = (char) (48 + (temp - temp / 10 * 10));
                temp /= 10;
            }
            format_str[10] = ' ';
            index = 12;
            temp = hour;
            for (; index >= 11; index--) {
                format_str[index] = (char) (48 + (temp - temp / 10 * 10));
                temp /= 10;
            }
            format_str[13] = ':';
            index = 15;
            temp = minute;
            for (; index >= 14; index--) {
                format_str[index] = (char) (48 + (temp - temp / 10 * 10));
                temp /= 10;
            }
            dateTime = new String(format_str,0,format_str.length).substring(0, 16);
        } catch (Exception e) {
//LogFactory.getLog(getClass()).error(e);
        }
        return dateTime;
    }
}
