package tree.moe.epet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static tree.moe.epet.constant.ResultEnum.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import tree.moe.epet.entity.Address;
import tree.moe.epet.entity.Result;
import tree.moe.epet.entity.User;
import tree.moe.epet.entity.UserVO;
import tree.moe.epet.exception.*;
import tree.moe.epet.service.UserService;
import tree.moe.epet.util.JudgeParameter;
import tree.moe.epet.util.JwtUtil;

@RestController
@CrossOrigin
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/user/updateinfo")
	@ResponseBody
	public User updateUserinfo(HttpServletRequest request,@RequestBody User userinfo) throws Exception
	{
		userService.updateUserinfo(userinfo);
		String token = "";
		token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		userinfo.setId((int)info.get("id"));
		
		if(JudgeParameter.validateFild(userinfo))
		{
			throw new LackParameterException();
		}
		UserVO uservo = new UserVO();
		uservo.setUsername(userinfo.getUsername());
		return userService.getUserByUsername(uservo.getUsername());
	}
	
	@RequestMapping(value="/user/getuserinfo")
	public User getUserinfo(HttpServletRequest request)throws Exception
	{
		User user;
		String token = "";
		token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		if((int)info.get("id") == 0)
		{
			throw new LackParameterException();
		}
		user = userService.getUserByUsername((String) info.get("username"));
		user.setPassword("");
		if(user == null)
		{
			throw new UserNoExistException();
		}
		return user;
	}
	
	@RequestMapping(value="/user/register")
	public Result registerNewUser(@RequestBody User user) throws Exception
	{
		UserVO uservo = new UserVO();
		Result result = new Result();
		uservo.setUsername(user.getUsername());
		
		if(user.getPassword().length()<6||user.getPassword().length()>30||user.getUsername().length()<6)
		{
			throw new ParameterException();
		}
		if(user.getNickname()==null)
		{
			user.setNickname("newUser");
		}
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		if(userService.getUserByUsername(uservo.getUsername())!=null)
		{
			throw new UserExistException();
		}
		else
		{
			userService.registrtNewUser(user);
			result.setCode(REGISTER_SUCCESS.getCode());
			result.setMsg(REGISTER_SUCCESS.getMsg());
		}
		result.setData(uservo);
		return result;
	}
}
