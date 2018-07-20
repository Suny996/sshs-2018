package com.sshs.system.dictionary.router;

import com.sshs.system.dictionary.handler.DictionaryHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;

@Configuration
public class DictionaryRouter {
    @Bean
    public RouterFunction<ServerResponse> dictionaryRoute(DictionaryHandler dictionaryHandler) {
        return nest(
                path("/system/dictionary"), //nest(accept(MediaType.APPLICATION_JSON),
                RouterFunctions.route(GET("/{id}"), dictionaryHandler::getDictionarys)
                        .andRoute(POST("/"), dictionaryHandler::addDictionary).andRoute(DELETE("/"), dictionaryHandler::delDictionary));
    }
}
