package com.sinocare.base.core.aspect;

import com.alibaba.fastjson.JSON;
import com.sinocare.base.core.annotation.SysLogger;
import com.sinocare.base.core.util.HttpContextUtils;
import com.sinocare.base.core.util.IPUtils;
import com.sinocare.base.po.log.SysLog;
import com.sinocare.base.po.sys.SysUser;
import com.sinocare.base.service.log.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Description: spring-boot-base
 * Created by jeikerxiao on 2018/6/28 上午11:45
 */
@Aspect
@Component
@Slf4j
public class SysLoggerAspect {

    @Autowired
    private SysLogService sysLogService;

    // 切点（注解为切点）
    @Pointcut("@annotation(com.sinocare.base.core.annotation.SysLogger)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        saveSysLog(point, time);
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        SysLogger sysLogger = method.getAnnotation(SysLogger.class);
        if (sysLogger != null) {
            //注解上的描述
            sysLog.setOperation(sysLogger.value());
        }

        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSON.toJSONString(args[0]);
            sysLog.setParams(params);
        } catch (Exception e) {
            log.error("@SysLogger toJSONString error {}", e);
        }

        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 获取IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));

        // 用户名
        try {
            String username = ((SysUser) SecurityUtils.getSubject().getPrincipal()).getUsername();
            sysLog.setUsername(username);
        } catch (Exception e) {
            log.error("@SysLogger get username error {}", e);
        }

        sysLog.setTime(time);
        sysLog.setCreateDate(new Date());
        //保存系统日志
        sysLogService.save(sysLog);
    }
}
