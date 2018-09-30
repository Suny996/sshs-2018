package com.sshs.system.org.mapper;

import com.sshs.system.org.model.Org;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 系统管理->系统管理-机构表dao接口类
 *
 * @author Suny
 * @date 2018/03/13
 */
public interface OrgMapper extends Mapper<Org> {
    /**
     * 根据orgCode查询机构记录，orgCode为null时返回跟节点（parentOrgCode为空的记录）
     * @param orgCode
     * @return
     */
    public Org findOrgById(String orgCode);

    /**
     * 查询所有记录
     * @return
     */
    public List<Org> findOrgAll();
}