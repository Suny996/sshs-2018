package com.sshs.system.org.service;

import com.sshs.core.base.service.IBaseService;
import com.sshs.system.org.model.Org;

/**
 * 系统管理->系统管理-菜单表service接口
 *
 * @author Suny
 * @date 2018/03/13
 */
public interface IOrgService extends IBaseService<Org> {
    public Org getOrgTree(String rootId);
    public int delete(String id) throws Exception;
}

