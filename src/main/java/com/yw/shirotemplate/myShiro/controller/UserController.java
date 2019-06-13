package com.yw.shirotemplate.myShiro.controller;


import com.yw.shirotemplate.myShiro.entity.User;
import com.yw.shirotemplate.myShiro.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chengx
 * @since 2019-06-13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("getList")
    public List<User> getList(){
        return userService.listOnLineUser();
    }

}
