package com.eoms.domain.nms;

import com.eoms.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "nms_sys_ability")
public class SysAbility  extends BaseEntity<String> {

    private String terminalId;

    @ManyToOne
    @JoinColumn(name = "terminalId",insertable = false,updatable = false)
    private Terminal terminal;

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
}
