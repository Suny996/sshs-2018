package com.sshs.system.menu.service.impl;


import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.system.menu.mapper.MenuMapper;
import com.sshs.system.menu.model.Menu;
import com.sshs.system.menu.service.IMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统管理->系统管理-菜单表service实现类
 *
 * @author Suny
 * @date 2018/03/13
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {
    @Resource(name = "menuMapper")
    private MenuMapper mapper;

    /**
     * 查询指定menuCode及向下菜单树
     *
     * @param rootId
     * @return
     */
    public Menu getMenuTree(String rootId) {
        Menu menu = mapper.findMenuById(rootId);
        if (menu != null) {
            List<Menu> menus = mapper.findMenuAll();
            menu = initChildren(menu, menus);
        }
        return menu;
    }

    /**
     * 初始化子节点
     *
     * @param menu
     * @param menus
     * @return
     */
    private Menu initChildren(Menu menu, List<Menu> menus) {
        for (Menu m : menus) {
            if (menu.getMenuCode().equals(m.getParentMenuCode())) {
                m = initChildren(m, menus);
                menu.addChild(m);
            }
        }
        return menu;
    }
}

