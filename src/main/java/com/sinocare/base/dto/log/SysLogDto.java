package com.sinocare.base.dto.log;

import lombok.Data;

import java.util.Date;

/**
 * Description: spring-boot-base
 * Created by jeikerxiao on 2018/6/29 上午11:00
 */
@Data
public class SysLogDto {

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 执行时长(毫秒)
     */
    private Long time;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 创建时间
     */
    private Date createDate;
}
