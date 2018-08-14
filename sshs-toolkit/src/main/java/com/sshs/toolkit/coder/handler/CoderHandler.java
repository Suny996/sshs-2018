package com.sshs.toolkit.coder.handler;

import com.sshs.core.page.Page;
import com.sshs.core.util.ReflectHelper;
import com.sshs.core.util.Serializabler;
import com.sshs.core.util.UuidUtil;
import com.sshs.toolkit.coder.helper.CoderGenerator;
import com.sshs.toolkit.coder.model.Coder;
import com.sshs.toolkit.coder.model.Column;
import com.sshs.toolkit.coder.service.ICoderService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyExtractors.toMono;

/**
 * 类名称： 代码生成
 *
 * @author Suny
 * @version 1.0
 * @date 2018年7月23日
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
                        System.out.println("===========>" + p.getPageSize());
                        Page<Coder> list = coderService.findForPageList("com.sshs.toolkit.coder.mapper.CoderMapper.findDbTableForPageList", p);
                        return list;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }), typeReference);

    }

    /**
     * 獲取字段列表
     *
     * @param request
     */
    public Mono<ServerResponse> getColumnList(ServerRequest request) {
        String tableName = request.pathVariable("tableName");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        List<Column> columnList = null;
        try {
            columnList = coderService.findColumnForList(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(columnList)).switchIfEmpty(notFound);
    }

    /**
     * 生成代码
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> runCoder(ServerRequest request) {
        return ServerResponse.ok()
                .body(request.body(toMono(Coder.class)).map(c -> {
                    try {
                        c.setCoderId(UuidUtil.get32UUID());
                        System.out.println("===========>" + c.getTableName());
                        for (Column col : c.getFields()) {
                            if (StringUtils.isEmpty(c.getTableName())) {
                                c.setTableName(col.getTableName());
                                c.setTableComment(col.getTableComment());
                            }
                            col.setPropertyName(ReflectHelper.getPropertyName(col.getColumnName()));
                            col.setPropertyType(CoderGenerator.getPropertyType(col.getColumnType()));
                        }

                        c.setColumns(Serializabler.object2Bytes(c.getFields()));

                        CoderGenerator.generate(c);
                        coderService.save(c);
                        return c;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }), Coder.class);

    }
}
