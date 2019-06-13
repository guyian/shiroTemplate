package com.yw.shirotemplate.myShiro.mapper;


import com.yw.shirotemplate.myShiro.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Chengx
 * @since 2019-06-12
 */
public interface UserMapper {


    User findByUserCode(String userCode);

    List<User> getAllUser(Map<String, Object> param);

    Integer getUserTotal(Map<String, Object> param);

    User listRolesAndOrg(String userName);

    boolean doCreate(User vo);

    boolean doUpdate(User vo);

    boolean doRemove(Object[] ids);

    List<User> listUserByCode(Object[] names);

}
