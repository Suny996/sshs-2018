package com.sshs.system.org.handler;

import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.system.org.model.Org;
import com.sshs.system.org.service.IOrgService;
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
 * 系统管理->系统管理-机构表handler类
 *
 * @author Suny
 * @date 2018/03/13
 */
@Component
public class OrgHandler {
    Log logger = LogFactory.getLog(OrgHandler.class);
    @Resource(name = "orgService")
    private final IOrgService orgService;

    @Autowired
    public OrgHandler(final IOrgService orgService) {
        this.orgService = orgService;
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
                .body(request.body(toMono(Org.class)).map(m -> {
                    //System.out.println(">>>>>" + c.getCustomiseName());
                  /*  if (StringUtil.isEmpty(m.getMenuCode())) {
                        m.setMenuCode(UuidUtil.get32UUID());
                    }*/
                    m.setCrtDate(new Date());
                    try {
                        if (orgService.save(m) != null) {
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
     * 系统管理->系统管理-机构表修改
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Mono<ServerResponse> update(ServerRequest request) {
        return ServerResponse.ok()//.contentType(MediaType.APPLICATION_JSON)
                .body(request.body(toMono(Org.class)).map(m -> {
                    try {
                        if (orgService.update(m) > 0) {
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
     * 系统管理->系统管理-机构表删除
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
            if (orgService.delete(menuCode) > 0) {
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
     * 获取系统管理->系统管理-机构表根据主键查询单笔记录
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Mono<ServerResponse> getOrgById(ServerRequest request) {
        String orgCode = request.pathVariable("orgCode");
        String type = request.pathVariable("type");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        try {

            Org org = null;
            if (StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("tree")) {
                org = orgService.getOrgTree(orgCode);
            } else {
                org = orgService.getById(orgCode);
            }
            if (org == null) {
                return notFound;
            } else {
                return ServerResponse.ok()//.contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(org)).switchIfEmpty(notFound);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("-400001");
        }

    }

    /**
     * 获取系统管理->系统管理-机构表列表(分页查询)
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
     * 获取系统管理->系统管理-机构表列表
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
