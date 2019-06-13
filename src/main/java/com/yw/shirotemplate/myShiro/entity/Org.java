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
public class Org implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id ;

    /**
     * 权限 code
     */
    private String orgCode;

    /**
     * 权限名称
     */
    private String orgName;


}
