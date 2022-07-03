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
import tree.moe.epet.service.UserService;

@RestController
@CrossOrigin
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/user/updateinfo")
	@ResponseBody
	public String updateUserinfo(@RequestBody User userinfo)
	{
		userService.updateUserinfo(userinfo);
		return userService.getUserByUsername(userinfo.getUsername()).toString();
	}
	
	@RequestMapping(value="/user/getuserinfo")
	public String getUserinfo(@RequestBody UserVO username)
	{
		User user;
		System.out.println(username);
		user = userService.getUserByUsername(username.getUsername());
		return user.toString();
	}
	
	@RequestMapping(value="/user/register")
	public Result registerNewUser(@RequestBody User user)
	{
		UserVO uservo = new UserVO();
		Result result = new Result();
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		
		if(userService.getUserByUsername(user.getUsername())!=null)
		{
			result.setCode(USER_ALREADY_EXIST.getCode());
			result.setMsg(USER_ALREADY_EXIST.getMsg());
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
