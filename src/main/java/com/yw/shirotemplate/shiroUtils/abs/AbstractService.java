package com.yw.shirotemplate.shiroUtils.abs;


import com.yw.shirotemplate.shiroUtils.utils.PaginationEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/3.
 */
public class AbstractService {
    public Map<String, Object> handleParams(PaginationEntity<? extends PaginationEntity> page) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("start", page.getStart());
        params.put("pageSize", page.getPageSize());
        return params;
    }
}
