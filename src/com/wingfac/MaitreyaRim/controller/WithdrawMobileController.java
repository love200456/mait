package com.wingfac.MaitreyaRim.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wingfac.MaitreyaRim.po.AverageUser;
import com.wingfac.MaitreyaRim.po.Withdraw;
import com.wingfac.MaitreyaRim.service.impl.AverageUserService;
import com.wingfac.MaitreyaRim.service.impl.WithdrawService;

@Controller
@RequestMapping("WithdrawMobile")
public class WithdrawMobileController {

	@Autowired
	private WithdrawService withdrawService;
	
	@Autowired
	private AverageUserService averageUserService;

	/**
	 * 用户提现
	 * @param request
	 * @param response
	 * @param userID
	 * @param money
	 * @return
	 */
	@RequestMapping("withdraw")
	@ResponseBody
	public Map<String,Object> withdraw(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "userID",required = true) Integer userID,
			@RequestParam(value = "money",required = true) Double money){
		Map<String,Object> result=new HashMap<String,Object>();
		AverageUser averageUser=averageUserService.selectByauId(userID);
		String userName=averageUser.getAuBuyerNick();
		Double myMoney=averageUser.getMoney();
		if(myMoney<money){
			result.put("ResponseStatus","1");
			result.put("msg","账户余额不足!");
			return result;
		}
		Withdraw withdraw=new Withdraw();
		withdraw.setUserID(userID);
		withdraw.setUserName(userName);
		withdraw.setMoney(money);
		//添加提现记录
		withdrawService.insert(withdraw);
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("auId",userID);
		param.put("money",myMoney-money);
		//更新账户余额
		averageUserService.updateMoney(param);
		result.put("ResponseStatus","0");
		result.put("msg","操作完成");
		return result;
	}

}
