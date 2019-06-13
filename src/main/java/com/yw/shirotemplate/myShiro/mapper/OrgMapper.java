package com.yw.shirotemplate.myShiro.mapper;

import java.util.Set;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Chengx
 * @since 2019-06-12
 */
public interface OrgMapper {

    public Set<String> listOrg(String userCode);

}
