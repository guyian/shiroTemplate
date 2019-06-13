package com.yw.shirotemplate.myShiro.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id ;

    /**
     * 用户 id
     */
    private Integer userId;

    /**
     * 角色 id
     */
    private Integer roleId;


}
