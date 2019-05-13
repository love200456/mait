package com.wingfac.MaitreyaRim.mapper;

import java.util.List;
import java.util.Map;

import com.wingfac.MaitreyaRim.po.FinanceStatistics;

public interface FinanceStatisticsMapper {

	public Integer insertFS(FinanceStatistics financeStatistics);

	public List<FinanceStatistics> selectAll();

	public List<FinanceStatistics> selectAllSum();

	public List<FinanceStatistics> selectPage(Map<String, Object> map);

	public List<FinanceStatistics> selectLikeAll(String s_name);

	public List<FinanceStatistics> selectLikeAllSum(String s_name);

	public List<FinanceStatistics> selectLike(Map<String, Object> map);

	public Integer selectTimeTotal(Map<String, Object> map);

	public List<FinanceStatistics> selectTimeTotalSum(Map<String, Object> map);

	public List<FinanceStatistics> selectTimePage(Map<String, Object> map);

	public Integer delectFS(String[] id);

	public FinanceStatistics viewFinancialinformation(String o_number);

}
