package com.sinocare.base.po.sys;

import javax.persistence.*;

@Table(name = "sys_config")
public class SysConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * key
     */
    @Column(name = "param_key")
    private String paramKey;

    /**
     * value
     */
    @Column(name = "param_value")
    private String paramValue;

    /**
     * 状态   0：隐藏   1：显示
     */
    private Byte status;

    /**
     * 备注
     */
    private String remark;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取key
     *
     * @return param_key - key
     */
    public String getParamKey() {
        return paramKey;
    }

    /**
     * 设置key
     *
     * @param paramKey key
     */
    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    /**
     * 获取value
     *
     * @return param_value - value
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 设置value
     *
     * @param paramValue value
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * 获取状态   0：隐藏   1：显示
     *
     * @return status - 状态   0：隐藏   1：显示
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态   0：隐藏   1：显示
     *
     * @param status 状态   0：隐藏   1：显示
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}