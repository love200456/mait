package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.EmployMapper;
import com.wingfac.MaitreyaRim.po.Employ;

public class EmployService {

	@Autowired
	private EmployMapper employMapper;

	public List<Employ> selectAll() {
		return employMapper.selectAll();
	}

	public List<Employ> selectAllFen(Map<String, Object> map) {
		return employMapper.selectAllFen(map);
	}

	public List<Employ> selectLike(String position) {
		return employMapper.selectLike(position);
	}

	public List<Employ> selectLikeFen(Map<String, Object> map) {
		return employMapper.selectLikeFen(map);
	}

	public Employ selectById(Integer empId) {
		return employMapper.selectById(empId);
	}

	public List<Employ> selectAllPass(Map<String, Object> map) {
		return employMapper.selectAllPass(map);
	}

	public List<Employ> selectAllByauId(Map<String, Object> map) {
		return employMapper.selectAllByauId(map);
	}

	public Integer selectNumByauId(Integer auId) {
		return employMapper.selectNumByauId(auId);
	}

	public Integer modifyEmpInfo(Map<String, Object> map) {
		return employMapper.modifyEmpInfo(map);
	}

	public Integer auditEmpInfo(Integer empId) {
		return employMapper.auditEmpInfo(empId);
	}

	public Integer deleteEmpInfo(Integer empId) {
		return employMapper.deleteEmpInfo(empId);
	}

	public Integer insertEmpInfo(Map<String, Object> map) {
		return employMapper.insertEmpInfo(map);
	}

}
