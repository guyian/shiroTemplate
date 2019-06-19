package com.yw.shirotemplate.myShiro.service.impl;

import com.yw.shirotemplate.myShiro.entity.Org;
import com.yw.shirotemplate.myShiro.entity.Role;
import com.yw.shirotemplate.myShiro.entity.User;
import com.yw.shirotemplate.myShiro.mapper.UserMapper;
import com.yw.shirotemplate.myShiro.service.IUserService;
import com.yw.shirotemplate.shiroUtils.commons.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Chengx
 * @since 2019-06-12
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserMapper userMapper;
    //
    @Value("${redis.identifyingTokenExpireTime}")
    private long identifyingTokenExpireTime;
    //redis中jwtToken过期时间
    @Value("${redis.refreshJwtTokenExpireTime}")
    private long refreshJwtTokenExpireTime;

    @Override
    public User findByUserCode(String userCode) {
        return this.userMapper.findByUserCode(userCode);
    }

    @Override
    public Map<String, Object> getRolesAndOrgsByUserCode(String userCode) {
        Role role = null;
        Org org = null;
        Set<String> roles = new HashSet<String>();
        Set<String> orgs = new HashSet<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        User user = this.userMapper.listRolesAndOrg(userCode);
        for (int i = 0; i < user.getRoles().size(); i++) {
            role = user.getRoles().get(i);
            roles.add(role.getRoleName());
            for (int j = 0; j < role.getOrgs().size(); j++) {
                org = role.getOrgs().get(i);
                orgs.add(org.getOrgName());
            }
        }
        map.put("allRoles", roles);
        map.put("orgs", orgs);
        return map;
    }

    @Override
    public Map<String, Object> createRandomToken(String str) {
        //生成一个token
        String sToken = UUID.randomUUID().toString();
        //生成验证码对应的token  以token为key  验证码为value存在redis中
        redisTemplate.opsForValue().set(CommonConstant.NO_REPEAT_PRE + sToken, str, identifyingTokenExpireTime, TimeUnit.MINUTES);
        Map<String, Object> map = new HashMap<>();
        map.put("cToken", sToken);
        return map;
    }

    @Override
    public boolean checkRandomToken(String sToken, String str) {
        Object value = redisTemplate.opsForValue().get(CommonConstant.NO_REPEAT_PRE + sToken);
        if (value != null && str.equals(value)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addTokenToRedis(String userCode, String jwtTokenStr) {
        String key = CommonConstant.JWT_TOKEN + userCode;
        redisTemplate.opsForValue().set(key, jwtTokenStr, refreshJwtTokenExpireTime, TimeUnit.MINUTES);
    }

    @Override
    public boolean removeJWTToken(String userCode) {
        String key = CommonConstant.JWT_TOKEN + userCode;
        return redisTemplate.delete(key);
    }

    @Override
    public List<User> listOnLineUser() {
        Set setNames = redisTemplate.keys(CommonConstant.JWT_TOKEN + "*");
        List list = new ArrayList<>(setNames.size());
        Iterator<String> iter = setNames.iterator();
        while (iter.hasNext()) {
            String temp = iter.next();
            list.add(temp.substring(temp.lastIndexOf("_") + 1));
        }
        return userMapper.listUserByCode(list.toArray());
    }
}
