package com.yw.shirotemplate.myShiro.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Chengx
 * @since 2019-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * code 用于登录
     */
    private String userCode;

    /**
     * 密码 用于登录
     */
    private String userPwd;

    /**
     * 用户名 显示使用
     */
    private String userName;

    /**
     * 用户状态
     */
    private String userStatus;


    private List<Role> roles;
    private String sToken;
    private String str;

}
