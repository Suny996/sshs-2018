package com.sshs.system.menu.router;

import com.sshs.system.menu.handler.MenuHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;

@Configuration
public class MenuRouter {
    @Bean
    public RouterFunction<ServerResponse> menuRoute(MenuHandler menuHandler) {
        return nest(
                path("/system/menu"), //nest(accept(MediaType.APPLICATION_JSON),
                RouterFunctions.route(GET("/{menuCode}/{type}"), menuHandler::getMenuById)
                        .andRoute(POST("/"), menuHandler::save).andRoute(DELETE("/"), menuHandler::delete));
    }
}
