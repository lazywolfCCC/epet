package tree.moe.epet.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.Result;
import tree.moe.epet.entity.UserVO;
import tree.moe.epet.entity.Videohistory;
import tree.moe.epet.exception.ParameterException;
import tree.moe.epet.service.UserService;
import tree.moe.epet.service.VideohistoryService;
import tree.moe.epet.util.JwtUtil;

import static tree.moe.epet.constant.ResultEnum.*;

@RestController
@CrossOrigin
public class VideohistoryController {
	@Autowired
	VideohistoryService historyService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/history/getAllHistoryByuserId")
	@ResponseBody
	public Result<List<Videohistory>> getAllHistoryById(HttpServletRequest request,@RequestBody UserVO user) throws Exception
	{
		Result result = new Result<List<Videohistory>>();
		UserVO check;
		String token = "";
		token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		user.setId((int)info.get("id"));
		if(user.getId() == 0 || userService.getUserByUsername(user.getUsername())==null )
		{
			throw new ParameterException();
		}
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(historyService.getVideohistoryByUserid(user));
		
		return result;
	}
	
	@RequestMapping(value="/history/insertHistory")
	@ResponseBody
	public Result<Videohistory> getHistoryById(HttpServletRequest request,@RequestBody Videohistory history) throws Exception
	{
		Result result = new Result<List<Videohistory>>();
		Date date = new Date();
		history.setViewtime(date);
		String token = "";
		token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		history.setUser_id((int)info.get("id"));
		if(history.getId() == 0)
		{
			throw new ParameterException();
		}
		if(historyService.getVideoHistoryById(history)!=null)
		{
			result.setCode(UPDATEVIEWTIME_SUECSS.getCode());
			result.setMsg(UPDATEVIEWTIME_SUECSS.getMsg());
			result.setData(history);
			historyService.updateHistory(history);
		}
		else
		{
			result.setCode(REQUEST_SUCCESS.getCode());
			result.setMsg(REQUEST_SUCCESS.getMsg());
			result.setData(history);
			historyService.insertNewHistory(history);
		}
	
		return result;
	}
	
	@RequestMapping(value="/history/deleteHistoryById")
	@ResponseBody
	public Result deleteHistoryById(@RequestBody Videohistory history) throws Exception
	{
		Result result = new Result<List<Videohistory>>();
		if(history.getId() == 0)
		{
			throw new ParameterException();
		}
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		return result;
	}
	
}
