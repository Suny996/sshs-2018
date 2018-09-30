package com.sshs.system.org.service.impl;


import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.system.org.mapper.OrgMapper;
import com.sshs.system.org.model.Org;
import com.sshs.system.org.service.IOrgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统管理->系统管理-菜单表service实现类
 *
 * @author Suny
 * @date 2018/03/13
 */
@Service("orgService")
public class OrgServiceImpl extends BaseServiceImpl<Org> implements IOrgService {
    @Resource(name = "orgMapper")
    private OrgMapper mapper;

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public int delete(String id) throws Exception {
        Org root = getOrgTree(id);
        int cnt = 0;
        if (root != null) {
            if (root.getChildren() != null && root.getChildren().size() > 0) {
                for (Org o : root.getChildren()) {
                    cnt += delete(o.getOrgCode());
                }
                //return cnt;
            }
            super.delete(root.getOrgCode());
            cnt += 1;
            return cnt;
        } else {
            return 0;
        }
    }

    /**
     * 查询指定menuCode及向下菜单树
     *
     * @param rootId
     * @return
     */
    public Org getOrgTree(String rootId) {
        if("0".equals(rootId)){
            rootId=null;
        }
        Org org = mapper.findOrgById(rootId);
        if (org != null) {
            List<Org> orgs = mapper.findOrgAll();
            org = initChildren(org, orgs);
        }
        return org;
    }

    /**
     * 初始化子节点
     *
     * @param org
     * @param orgs
     * @return
     */
    private Org initChildren(Org org, List<Org> orgs) {
        for (Org o : orgs) {
            if (org.getOrgCode().equals(o.getParentOrgCode())) {
                o = initChildren(o, orgs);
                org.addChild(o);
            }
        }
        return org;
    }
}

