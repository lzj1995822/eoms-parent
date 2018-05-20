package com.eoms.domain.dto;

import com.eoms.domain.nms.HrSystem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemDTO {

    private String id;
    //    cpu占用率
    private int cpuUzi;

    //虚拟内存
    private long virtualMem;

    //物理内存
    private long phyMem;

//    虚拟内存使用率
    private long virtualUzi;

    //物理内存使用率
    private long phyUzi;

    //磁盘使用率
    private long diskUzi;

    private HrSystem hrSystem;

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
}
