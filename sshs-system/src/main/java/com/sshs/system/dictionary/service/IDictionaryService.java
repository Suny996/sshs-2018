package com.sshs.system.dictionary.service;

import com.sshs.core.page.Page;
import com.sshs.system.dictionary.model.Dictionary;

import java.util.List;

/**
 * 
 * @author Suny
 *
 */
public interface IDictionaryService {
	/**
	 * 新增
	 * 
	 * @param dictionary
	 * @return
	 * @throws Exception
	 */
	int save(Dictionary dictionary) throws Exception;

	/**
	 * 修改
	 * 
	 * @param dictionary
	 * @return
	 * @throws Exception
	 */
	int update(Dictionary dictionary) throws Exception;

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delete(String id) throws Exception;

	/**
	 * 根据主键查询对象
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Dictionary findById(String id) throws Exception;

	/**
	 * 根据dictCode查询字典项
	 * 
	 * @param dictCode
	 * @return
	 */
	Dictionary getDictionaryByCode(String dictCode);

	/**
	 * 根据parentId查询子列表
	 * 
	 * @param parentId
	 * @return
	 */
	List<Dictionary> findByParentId(String parentId);

	/**
	 * 分页查询字典列表
	 * 
	 * @param page
	 * @throws Exception
	 */
    void findForList(Page<Dictionary> page) throws Exception;

	/**
	 * 数据字典初始化
	 */
    void initDictList();
}
