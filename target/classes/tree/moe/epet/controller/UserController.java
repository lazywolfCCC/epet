package tree.moe.epet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static tree.moe.epet.constant.ResultEnum.*;


import tree.moe.epet.entity.Result;
import tree.moe.epet.entity.User;
import tree.moe.epet.entity.UserVO;
import tree.moe.epet.exception.*;
import tree.moe.epet.service.UserService;

@RestController
@CrossOrigin
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/user/updateinfo")
	@ResponseBody
	public User updateUserinfo(@RequestBody User userinfo)
	{
		userService.updateUserinfo(userinfo);
		UserVO uservo = new UserVO();
		uservo.setUsername(userinfo.getUsername());
		return userService.getUserByUsername(uservo);
	}
	
	@RequestMapping(value="/user/getuserinfo")
	public User getUserinfo(@RequestBody UserVO username)
	{
		User user; 
		System.out.println(username);
		user = userService.getUserByUsername(username);
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
		if(userService.getUserByUsername(uservo)!=null)
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
