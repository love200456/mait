package com.wingfac.MaitreyaRim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wingfac.MaitreyaRim.mapper.FinanceStatisticsMapper;
import com.wingfac.MaitreyaRim.po.FinanceStatistics;

public class FinanceStatisticsService {

	@Autowired
	private FinanceStatisticsMapper financeStatisticsMapper;

	public Integer insertFS(FinanceStatistics financeStatistics) {
		return financeStatisticsMapper.insertFS(financeStatistics);
	}

	public List<FinanceStatistics> selectAll() {
		return financeStatisticsMapper.selectAll();
	}

	public List<FinanceStatistics> selectAllSum() {
		return financeStatisticsMapper.selectAllSum();
	}

	public List<FinanceStatistics> selectPage(Map<String, Object> map) {
		return financeStatisticsMapper.selectPage(map);
	}

	public List<FinanceStatistics> selectLikeAll(String searchVal) {
		return financeStatisticsMapper.selectLikeAll(searchVal);
	}

	public List<FinanceStatistics> selectLikeAllSum(String searchVal) {
		return financeStatisticsMapper.selectLikeAllSum(searchVal);
	}

	public List<FinanceStatistics> selectLike(Map<String, Object> map) {
		return financeStatisticsMapper.selectLike(map);
	}

	public Integer selectTimeTotal(Map<String, Object> map) {
		return financeStatisticsMapper.selectTimeTotal(map);
	}

	public List<FinanceStatistics> selectTimeTotalSum(Map<String, Object> map) {
		return financeStatisticsMapper.selectTimeTotalSum(map);
	}

	public List<FinanceStatistics> selectTimePage(Map<String, Object> map) {
		return financeStatisticsMapper.selectTimePage(map);
	}

	public Integer delectFS(String fs_id) {
		return financeStatisticsMapper.delectFS(fs_id.split(","));
	}

	public FinanceStatistics viewFinancialinformation(String o_number) {
		return financeStatisticsMapper.viewFinancialinformation(o_number);
	}

}
