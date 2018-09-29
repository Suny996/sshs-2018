package com.sshs.system.dictionary.handler;

import com.sshs.core.customise.model.Customise;
import com.sshs.core.util.UuidUtil;
import com.sshs.system.dictionary.model.Dictionary;
import com.sshs.system.dictionary.service.IDictionaryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Date;

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
public class DictionaryHandler {
    Log logger = LogFactory.getLog(DictionaryHandler.class);
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Resource(name="dictionaryService")
    private IDictionaryService dictionaryService;
    /**
     * 添加自定义查询方法
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> addDictionary(ServerRequest request) {
        return ServerResponse.ok()//.contentType(MediaType.APPLICATION_JSON)
                .body(request.body(toMono(Customise.class)).map(c -> {
                    //System.out.println(">>>>>" + c.getCustomiseName());
                    c.setCustomiseId(UuidUtil.get32UUID());
                    c.setUserCode("admin");
                    c.setCrtDate(new Date());
                    sqlSessionTemplate.insert("com.sshs.core.customise.mapper.CustomiseMapper.save", c);
                    return c;
                }), Customise.class);
    }

    /**
     * 删除自定义查询方法
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> delDictionary(ServerRequest request) {
        //System.out.println(">>>>>>>>>del");
        return ServerResponse.ok()//.contentType(MediaType.APPLICATION_JSON)
                .body(request.body(toMono(Customise.class)).map(c -> {
                    //System.out.println(">>>>>>>>>"+c.getCustomiseId());
                    sqlSessionTemplate.delete("com.sshs.core.customise.mapper.CustomiseMapper.delete", c);
                    return c;
                }), Customise.class);
    }

    /**
     * 数据字典-查询方法
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getDictionarys(ServerRequest request) {
        String dictCode = request.pathVariable("dictCode");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Dictionary dictionary = dictionaryService.getDictionaryByCode(dictCode);

        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(dictionary.getChildren())).switchIfEmpty(notFound);
    }
}
