package com.sshs.toolkit.coder.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.toolkit.coder.mapper.CoderMapper;
import com.sshs.toolkit.coder.model.Coder;
import com.sshs.toolkit.coder.model.Column;
import com.sshs.toolkit.coder.service.ICoderService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;

/**
 * 类名称：CreateCodeService 代码生成器
 *
 * @author Suny
 * @version 1.0
 * @date 2015年11月24日
 */
@Service("coderService")
public class CoderServiceImpl extends BaseServiceImpl<Coder> implements ICoderService {
    @Resource(name = "coderMapper")
    private CoderMapper coderMapper;

    /**
     * 新增
     *
     * @param coder
     */
    @Override
    public int save(Coder coder) {
        // 新增一条数据
        coderMapper.deleteByTableName(coder.getTableName());
        return coderMapper.insert(coder);
        // coderDao.save(coder);
    }

    /**
     * 删除
     *
     * @param coderId
     */
    @Override
    public int delete(String coderId) {
        return coderMapper.delete(coderId);
    }

    /**
     * 通过id获取数据
     *
     * @param coderId
     */
    @Override
    public Coder findById(String coderId) {
        return coderMapper.findById(coderId);
    }

    /**
     * 列表(主表)
     *
     * @param tableName
     */
    @Override
    public Flux<Column> findColumnForList(String tableName) {
        return coderMapper.findColumnAll(tableName);
    }

}
