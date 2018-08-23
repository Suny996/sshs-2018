package com.sshs.security.service;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.security.model.Privilege;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Suny
 * @date 2018-01-28
 */
@Service
public class PrivilegeService extends BaseServiceImpl<Privilege> {

	public List<Privilege> findPrivilegeByUserId(String userId) throws Exception {
		return findForList("com.sshs.security.mapper.PrivilegeMapper.findPrivilegeListByUserId", userId);
	}

	public List<Privilege> findPrivilegeByUrl(String url) throws Exception {
		return findForList("com.sshs.security.mapper.PrivilegeMapper.findPrivilegeListByUrl", url);
	}
}
