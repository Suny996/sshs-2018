package com.sshs.toolkit.coder.router;

import com.sshs.toolkit.coder.handler.CoderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;

@Configuration
public class CoderRouter {
    @Bean
    public RouterFunction<ServerResponse> dictionaryRoute(CoderHandler coderHandler) {
        return nest(
                path("/toolkit/coder"), //nest(accept(MediaType.APPLICATION_JSON),
                RouterFunctions.route(POST("/tableList"), coderHandler::getTableList)
                        .andRoute(GET("/columnList/{tableName}"), coderHandler::getColunmList));
    }
}
