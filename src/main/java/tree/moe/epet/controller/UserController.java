package tree.moe.epet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.LoginData;
import tree.moe.epet.entity.User;
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
	public String getUserinfo(@RequestBody LoginData username)
	{
		User user;
		System.out.println(username);
		user = userService.getUserByUsername(username.getUsername());
		return user.toString();
	}
	
	@RequestMapping(value="/user/register")
	public String registerNewUser(@RequestBody User user)
	{
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userService.registrtNewUser(user);
		return "registerSucess";
	}
}
