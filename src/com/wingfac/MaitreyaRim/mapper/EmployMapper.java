package com.wingfac.MaitreyaRim.mapper;

import java.util.List;
import java.util.Map;
import com.wingfac.MaitreyaRim.po.Employ;

public interface EmployMapper {

	public List<Employ> selectAll();

	public List<Employ> selectAllFen(Map<String, Object> map);

	public List<Employ> selectLike(String position);

	public List<Employ> selectLikeFen(Map<String, Object> map);

	public Employ selectById(Integer empId);

	public List<Employ> selectAllPass(Map<String, Object> map);

	public List<Employ> selectAllByauId(Map<String, Object> map);

	public Integer selectNumByauId(Integer auId);

	public Integer modifyEmpInfo(Map<String, Object> map);

	public Integer auditEmpInfo(Integer empId);

	public Integer deleteEmpInfo(Integer empId);

	public Integer insertEmpInfo(Map<String, Object> map);
}
