package com.sshs.core.customise.handler;

import com.sshs.core.customise.model.Customise;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * 类名称： 自定义查询
 *
 * @author Suny
 * @date 2017年10月23日
 *
 * @version 1.0
 */

@Component
public class CustomiseHandler {
    Log logger = LogFactory.getLog(CustomiseHandler.class);
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    public Mono<ServerResponse> addCustomise(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Hello, City!"));
    }

    public Mono<ServerResponse> getCustomise(ServerRequest request) {
        String pageId = request.pathVariable("id");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Customise custom = new Customise();
        custom.setPageId(pageId);
        custom.setUserCode("admin");
        List<Customise> customises = sqlSessionTemplate.selectList("com.sshs.core.customise.mapper.CustomiseMapper.getCustomises",
                custom);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(customises)).switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> listCustomises(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Hello, City!"));
    }
}
