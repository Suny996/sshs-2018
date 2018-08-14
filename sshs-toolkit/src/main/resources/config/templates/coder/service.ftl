package ${coder.packageName}.service;

import com.sshs.core.base.service.IBaseService;
 <#-- import com.sshs.core.page.Page;-->
import ${coder.packageName}.model.${coder.className};

 /** 
 * ${coder.title}service接口
 * @author ${coder.systemUser}
 * @date ${coder.crtDate?string("yyyy/MM/dd")}
 */
public interface I${coder.className}Service extends IBaseService<${coder.className}> {
 <#-- 
	
	/**
	 * 新增保存
	 * 
	 * @param ${coder.classDeclare}
	 * @throws Exception
	 */
	public int save(${coder.className} ${coder.classDeclare}) throws Exception ;
	
	/**
	 * 修改保存
	 * 
	 * @param ${coder.classDeclare}
	 * @throws Exception
	 */
	public int update(${coder.className} ${coder.classDeclare}) throws Exception ;
	
	/**
	 * 删除
	 * 
	 * @param ${coder.classDeclare}Id
	 * @throws Exception
	 */
	public int delete(String ${coder.classDeclare}Id) throws Exception ;
	
	/**
	 * 分页查询列表(主表)
	 * 
	 * @param page
	 * @throws Exception
	 */
	public void find${coder.className}ForList(Page<${coder.className}> page) throws Exception ;
	 -->
}

