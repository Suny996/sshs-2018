package com.sshs.core.customise.router;

import com.sshs.core.customise.handler.CustomiseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class CustomiseRouter {
    @Bean
    public RouterFunction<ServerResponse> route(CustomiseHandler customiseHandler) {
       /* return RouterFunctions
                .nest(RequestPredicates.GET("/customise").nest(RequestPredicates.GET("/get/{id}")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        handler::hello);*/
      return  RouterFunctions.nest(
                path("/customise"),
              RouterFunctions.nest(accept(APPLICATION_JSON),
                      RouterFunctions.route(GET("/{id}"), customiseHandler::getCustomise)
                                .andRoute(method(HttpMethod.GET), customiseHandler::listCustomises)
                ).andRoute(POST("/").and(contentType(APPLICATION_JSON)), customiseHandler::addCustomise)
        );
    }
}
