package com.yw.shirotemplate.myShiro.service;

import com.yw.shirotemplate.myShiro.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Chengx
 * @since 2019-06-12
 */
public interface IUserService {

    /**
     * 通过 用户code获取用户信息
     *
     * @param userCode
     * @return
     */
    User findByUserCode(String userCode);

    /**
     * 获取 角色 权限 通过用户code
     *
     * @param userCode
     * @return
     */
    Map<String, Object> getRolesAndOrgsByUserCode(String userCode);

    /**
     * 创建随机token
     *
     * @param str
     * @return
     */
    Map<String, Object> createRandomToken(String str);

    boolean checkRandomToken(String sToken, String str);

    /**
     * 添加 token 信息到redis
     *
     * @param userCode
     * @param jwtTokenStr
     */
    void addTokenToRedis(String userCode, String jwtTokenStr);

    boolean removeJWTToken(String userCode);

    List<User> listOnLineUser();


/*

    public boolean doCreate(User vo);

    public boolean doUpdate(User vo);

    public boolean doRemove(Object[] ids);
*/


}
