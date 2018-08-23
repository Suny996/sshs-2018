package com.sshs.system.menu.handler;

import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.system.menu.model.Menu;
import com.sshs.system.menu.service.IMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Date;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;

/**
 * 系统管理->系统管理-菜单表controller类
 *
 * @author Suny
 * @date 2018/03/13
 */
@Component
public class MenuHandler {
    Log logger = LogFactory.getLog(MenuHandler.class);
    @Resource(name = "menuService")
    private final IMenuService menuService;

    @Autowired
    public MenuHandler(final IMenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 系统管理->系统管理-菜单表新增
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Mono<ServerResponse> save(ServerRequest request) {
        return ServerResponse.ok()//.contentType(MediaType.APPLICATION_JSON)
                .body(request.body(toMono(Menu.class)).map(m -> {
                    //System.out.println(">>>>>" + c.getCustomiseName());
                  /*  if (StringUtil.isEmpty(m.getMenuCode())) {
                        m.setMenuCode(UuidUtil.get32UUID());
                    }*/
                    m.setCrtDate(new Date());
                    try {
                        if (menuService.save(m) > 0) {
                            return new Message("100000", m);
                        } else {
                            return new Message("100001");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new BusinessException("-100001");
                    }
                }), Message.class);
    }

    /**
     * 系统管理->系统管理-菜单表修改
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Mono<ServerResponse> update(ServerRequest request) {
        return ServerResponse.ok()//.contentType(MediaType.APPLICATION_JSON)
                .body(request.body(toMono(Menu.class)).map(m -> {
                    try {
                        if (menuService.update(m) > 0) {
                            return new Message("200000", m);
                        } else {
                            return new Message("200001");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new BusinessException("-200001");
                    }
                }), Message.class);
    }

    /**
     * 系统管理->系统管理-菜单表删除
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Mono<ServerResponse> delete(ServerRequest request) {
        String menuCode = request.pathVariable("menuCode");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Message message = null;
        try {
            if (menuService.delete(menuCode) > 0) {
                message = new Message("300000");
            } else {
                message = new Message("300001");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("-300001");
        }
        return ServerResponse.ok()//.contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(message)).switchIfEmpty(notFound);
    }

    /**
     * 获取系统管理->系统管理-菜单表根据主键查询单笔记录
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Mono<ServerResponse> getMenuById(ServerRequest request) {
        String menuCode = request.pathVariable("menuCode");
        String type = request.pathVariable("type");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        try {

            Menu menu = null;
            if (StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("tree")) {
                menu = menuService.getMenuTree(menuCode);
            } else {
                menu = menuService.getById(menuCode);
            }
            return ServerResponse.ok()//.contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(menu)).switchIfEmpty(notFound);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("-400001");
        }

    }

    /**
     * 获取系统管理->系统管理-菜单表列表(分页查询)
     *
     * @param page
     * @param request
     * @return
     * @throws Exception
     */
   /* public Mono<Page<Menu>> getMenuPageList(@RequestBody Page<Menu> page, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {
        menuService.findForPageList("com.sshs.system.menu.MenuDao.findForPageList", page);
        return Mono.justOrEmpty(page);
    }*/

    /**
     * 获取系统管理->系统管理-菜单表列表
     *
     * @param page
     * @param request
     * @return
     * @throws Exception
     */
    /*public Mono<List<Menu>> getMenuList(@RequestBody Page<Menu> page, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        //page.setLimit(999999999);
        return Mono.justOrEmpty(menuService.findForList("com.sshs.system.menu.MenuDao.findForPageList", page));
    }*/
}
