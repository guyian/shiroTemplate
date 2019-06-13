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
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id ;

    /**
     * 角色 code
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    private List<Org> orgs;

}
