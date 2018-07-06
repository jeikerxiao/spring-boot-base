package com.sinocare.base.service.impl.sys;

import com.sinocare.base.core.AbstractService;
import com.sinocare.base.dao.sys.SysCaptchaMapper;
import com.sinocare.base.po.sys.SysCaptcha;
import com.sinocare.base.service.sys.SysCaptchaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * SysCaptcha
 *
 * @version 2018/07/02
 * @author jeikerxiao
 */
@Service
@Transactional
public class SysCaptchaServiceImpl extends AbstractService<SysCaptcha> implements SysCaptchaService {

    @Resource
    private SysCaptchaMapper sysCaptchaMapper;

}
