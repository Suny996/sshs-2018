package com.sshs.core.customise.router;

import com.sshs.core.customise.handler.CommonHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CommonRouter {
    @Bean
    public RouterFunction<ServerResponse> commonRoute(CommonHandler commonHandler) {
        return nest(
                path("/plist"),
                nest(accept(MediaType.ALL), route(GET("/{dictCode}"), commonHandler::getList)
                        .andRoute(method(HttpMethod.GET), commonHandler::getAllList
                        )));
       /* return nest(
                path("/plist"),
                RouterFunctions.route(GET("/"), commonHandler::getAllList).andRoute(GET("/{dictCode}"), commonHandler::getList));*/
    }
}
