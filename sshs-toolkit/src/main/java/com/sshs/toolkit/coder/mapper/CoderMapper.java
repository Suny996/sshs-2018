package com.sshs.toolkit.coder.mapper;

import com.sshs.toolkit.coder.model.Coder;
import com.sshs.toolkit.coder.model.Column;
import reactor.core.publisher.Flux;
import tk.mybatis.mapper.common.Mapper;
/**
 * 代码生成dao接口
 *
 * @author Suny
 * @date 2017-10-15
 */
public interface CoderMapper extends Mapper<Coder> {
    /**
     * 新增
     *
     * @param coder
     * @return
     */
    int save(Coder coder);

    /**
     * 修改
     *
     * @param coder
     * @return
     */
    int update(Coder coder);

    /**
     * 删除
     *
     * @param dictId
     * @return
     */
    int delete(String dictId);

    /**
     * 删除
     *
     * @param tableName
     * @return
     */
    int deleteByTableName(String tableName);

    /**
     * 根据主键查询
     *
     * @param dictId
     * @return
     */
    Coder findById(String dictId);

    /**
     * 查询所有字段
     *
     * @param tableName
     * @return
     */
    Flux<Column> findColumnAll(String tableName);
}
