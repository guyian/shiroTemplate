package com.yw.shirotemplate.myShiro.controller;


import com.yw.shirotemplate.myShiro.entity.User;
import com.yw.shirotemplate.myShiro.mapper.UserMapper;
import com.yw.shirotemplate.myShiro.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chengx
 * @since 2019-06-13
 */
@RestController
public class UserController {

    @Resource
    private IUserService userService;
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/getList")
    public List<User> getList(){
        Map map = new HashMap();
        map.put("start",0);
        map.put("pageSize",3);
        return userMapper.getAllUser(map);
    }

}
