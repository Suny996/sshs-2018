package com.sshs.system.org.router;

import com.sshs.system.org.handler.OrgHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;

@Configuration
public class OrgRouter {
    @Bean
    public RouterFunction<ServerResponse> menuRoute(OrgHandler orgHandler) {
        return nest(
                path("/system/org"), //nest(accept(MediaType.APPLICATION_JSON),
                RouterFunctions.route(GET("/{orgCode}/{type}"), orgHandler::getOrgById)
                        .andRoute(POST("/"), orgHandler::save).andRoute(DELETE("/{orgCode}"), orgHandler::delete));
    }
}
