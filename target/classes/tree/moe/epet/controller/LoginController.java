package tree.moe.epet.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
public class LoginController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/login")
	@ResponseBody
	public String checkLoginData(@RequestBody LoginData data) //check is username and password match
	{
		User user;
		user = userService.getUserByUsername("admin");
		if(data.getPassword().equals(user.getPassword()))
		{
			return "loginSucess";
		}
		return "loginFail";
	}
}
