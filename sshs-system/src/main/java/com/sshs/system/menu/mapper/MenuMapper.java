package com.sshs.system.menu.mapper;

import com.sshs.system.menu.model.Menu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 系统管理->系统管理-菜单表dao接口类
 *
 * @author Suny
 * @date 2018/03/13
 */
public interface MenuMapper extends Mapper<Menu> {
    /**
     * 根据menuCode查询菜单记录，menuCode为null时返回跟节点（parentMenuCode为空的记录）
     * @param menuCode
     * @return
     */
    public Menu findMenuById(String menuCode);

    /**
     * 根据menuCode查询最后一个子菜单（用于生成菜单编号）
     * @param menuCode
     * @return
     */
    public String findLastChildCodeById(String menuCode);

    /**
     * 查询所有记录
     * @return
     */
    public List<Menu> findMenuAll();
}