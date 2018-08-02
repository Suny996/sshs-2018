package com.sshs.toolkit.coder.handler;

import com.sshs.core.page.Page;
import com.sshs.toolkit.coder.model.Coder;
import com.sshs.toolkit.coder.model.Column;
import com.sshs.toolkit.coder.service.ICoderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyExtractors.toMono;

/**
 * 类名称： 自定义查询
 *
 * @author Suny
 * @version 1.0
 * @date 2017年10月23日
 */

@Component
public class CoderHandler {
    Log logger = LogFactory.getLog(CoderHandler.class);
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Resource
    private ICoderService coderService;

    /**
     * 獲取表列表
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getTableList(ServerRequest request) {
        ParameterizedTypeReference<Page<Coder>> typeReference = new ParameterizedTypeReference<Page<Coder>>() {
        };
        //Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return ServerResponse.ok()
                .body(request.body(toMono(typeReference)).defaultIfEmpty(new Page<Coder>()).map(p -> {
                    try {
                        System.out.println("===========>"+p.getPageSize());
                        Page<Coder> list=  coderService.findForPageList("com.sshs.toolkit.coder.mapper.CoderMapper.findDbTableForPageList", p);
                        return list;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }),typeReference);

    }

    /**
     * 獲取字段列表
     *
     * @param request
     */
    public Mono<ServerResponse> getColunmList(ServerRequest request) {
        String tableName = request.pathVariable("tableName");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Flux<Column> columnList = null;
        try {
            columnList = coderService.findColumnForList(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(columnList)).switchIfEmpty(notFound);
    }
}
