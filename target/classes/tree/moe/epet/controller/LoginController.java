package tree.moe.epet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping(value="/login")
	@ResponseBody
	public String checkLoginData(@RequestBody LoginData data) //check is username and password match
	{
		User user;
		user = userService.getUserByUsername(data.getUsername());
		data.setPassword(DigestUtils.md5DigestAsHex(data.getPassword().getBytes()));
		
		if(user==null)
		{
			return "loginFail";
		}
		if(data.getPassword().equals(user.getPassword()))
		{
			return "loginSucess";
		}
		return "loginFail";
	}
}
