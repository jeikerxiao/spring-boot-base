package com.sinocare.base.service.impl.sys;

import com.sinocare.base.core.AbstractService;
import com.sinocare.base.dao.sys.SysDictMapper;
import com.sinocare.base.po.sys.SysDict;
import com.sinocare.base.service.sys.SysDictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * SysDict
 *
 * @version 2018/07/06
 * @author jeikerxiao
 */
@Service
@Transactional
public class SysDictServiceImpl extends AbstractService<SysDict> implements SysDictService {

    @Resource
    private SysDictMapper sysDictMapper;

}
