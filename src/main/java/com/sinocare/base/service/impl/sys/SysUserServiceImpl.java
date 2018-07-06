package com.sinocare.base.service.impl.sys;

import com.sinocare.base.core.AbstractService;
import com.sinocare.base.core.exception.ServiceException;
import com.sinocare.base.core.result.ResultCode;
import com.sinocare.base.core.util.Constant;
import com.sinocare.base.dao.sys.SysUserMapper;
import com.sinocare.base.po.sys.SysUser;
import com.sinocare.base.service.sys.SysRoleService;
import com.sinocare.base.service.sys.SysUserRoleService;
import com.sinocare.base.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * SysUser
 *
 * @author jeikerxiao
 * @version 2018/06/27
 */
@Service
@Transactional
@Slf4j
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    @Transactional
    public void add(SysUser user) {
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        user.setCreateTime(new Date());
        sysUserMapper.insertSelective(user);

        //检查角色是否越权
        checkRole(user);
        // 优化点，返回插入的id
        Condition condition = new Condition(SysUser.class);
        condition.createCriteria()
                .andCondition("username = ", user.getUsername());
        List<SysUser> userList = sysUserMapper.selectByCondition(condition);
        SysUser user1 = new SysUser();
        if (CollectionUtils.isNotEmpty(userList)) {
            user1 = userList.get(0);
        }
        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user1.getUserId(), user.getRoleIdList());
    }

    @Override
    public void updateUser(SysUser user) {
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(null);
        }else{
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        sysUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional
    public void deleteByIds(Long[] userIds) {
        for (Long userId : userIds) {
            SysUser user = new SysUser();
            user.setUserId(userId);
            sysUserMapper.delete(user);
        }
    }

    @Override
    public List<String> queryAllPerms(Long userId) {
        return sysUserMapper.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserMapper.queryAllMenuId(userId);
    }

    @Override
    public SysUser queryByUserName(String userName) {
        SysUser user = new SysUser();
        user.setUsername(userName);
        List<SysUser> userList = sysUserMapper.select(user);
        return userList.get(0);
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        SysUser user = new SysUser();
        user.setPassword(newPassword);

        Condition condition = new Condition(SysUser.class);
        condition.createCriteria()
                .andCondition("user_id = ", userId)
                .andCondition("password = ", password);
        int result = sysUserMapper.updateByConditionSelective(user, condition);
        if (result <= 0) {
            log.error("updatePassword failure result: {}", result);
            return false;
        }
        return true;
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUser user){
        if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
            return;
        }
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if(user.getCreateUserId() == Constant.SUPER_ADMIN){
            return ;
        }
        //查询用户创建的角色列表
        List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());
        //判断是否越权
        if(!roleIdList.containsAll(user.getRoleIdList())){
            throw new ServiceException(ResultCode.USER_ROLE_ERROR);
        }
    }
}
