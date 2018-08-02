package com.sshs.toolkit.coder.service;

import com.sshs.core.base.service.IBaseService;
import com.sshs.toolkit.coder.model.Coder;
import com.sshs.toolkit.coder.model.Column;
import reactor.core.publisher.Flux;

/**
 * 类名称：代码生成器接口类
 * 
 * @author Suny
 * @date 2017年10月24日
 * @version
 */
public interface ICoderService extends IBaseService<Coder> {

	/**
	 * 新增
	 * 
	 * @param coder
	 * @throws Exception
	 * @return
	 */
    int save(Coder coder) throws Exception;

	/**
	 * 删除
	 * 
	 * @param coderId
	 * @throws Exception
	 * @return
	 */
    int delete(String coderId) throws Exception;

	/**
	 * 通过id获取数据
	 * 
	 * @param coderId
	 * @throws Exception
	 * @return
	 */
    Coder findById(String coderId) throws Exception;

	/**
	 * 列表(主表)
	 * 
	 * @param tableName
	 * @throws Exception
	 * @return
	 */
    Flux<Column> findColumnForList(String tableName) throws Exception;

}
