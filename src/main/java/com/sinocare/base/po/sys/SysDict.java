package com.sinocare.base.po.sys;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_dict")
public class SysDict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父id: 0表示顶级
     */
    private Long pid;

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 子配置项计数
     */
    @Column(name = "child_count")
    private Integer childCount;

    /**
     * 序号
     */
    private Integer seq;

    /**
     * 是否开启
     */
    @Column(name = "is_enabled")
    private Integer isEnabled;

    /**
     * 是否删除
     */
    @Column(name = "is_deleted")
    private Integer isDeleted;

    /**
     * 创建者id
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取父id: 0表示顶级
     *
     * @return pid - 父id: 0表示顶级
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父id: 0表示顶级
     *
     * @param pid 父id: 0表示顶级
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取代码
     *
     * @return code - 代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置代码
     *
     * @param code 代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * 获取子配置项计数
     *
     * @return child_count - 子配置项计数
     */
    public Integer getChildCount() {
        return childCount;
    }

    /**
     * 设置子配置项计数
     *
     * @param childCount 子配置项计数
     */
    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    /**
     * 获取序号
     *
     * @return seq - 序号
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置序号
     *
     * @param seq 序号
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取是否开启
     *
     * @return is_enabled - 是否开启
     */
    public Integer getIsEnabled() {
        return isEnabled;
    }

    /**
     * 设置是否开启
     *
     * @param isEnabled 是否开启
     */
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * 获取是否删除
     *
     * @return is_deleted - 是否删除
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除
     *
     * @param isDeleted 是否删除
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取创建者id
     *
     * @return create_user_id - 创建者id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建者id
     *
     * @param createUserId 创建者id
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}