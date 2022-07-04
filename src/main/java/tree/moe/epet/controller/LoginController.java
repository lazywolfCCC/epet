package tree.moe.epet.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static tree.moe.epet.constant.ResultEnum.*;

import tree.moe.epet.entity.Result;
import tree.moe.epet.entity.User;
import tree.moe.epet.entity.UserVO;
import tree.moe.epet.service.UserService;
import tree.moe.epet.util.JwtUtil;

@RestController
@CrossOrigin
public class LoginController {
	@Autowired
	UserService userService;
	
	@PostMapping(value="/login")
	@ResponseBody
	public Result checkLoginData(@RequestBody UserVO data) //check is username and password match
	{
		User user;
		Result<UserVO> result = new Result();
		UserVO userVO = new UserVO();
		user = userService.getUserByUsername(data);
		data.setPassword(DigestUtils.md5DigestAsHex(data.getPassword().getBytes()));
		
		if(user.getUsername() == null || user.getPassword() == null)
		{
			result.setCode(NO_SUCH_USER.getCode());
			result.setMsg(NO_SUCH_USER.getMsg());
			result.setData(userVO);
		}
		if(data.getPassword().equals(user.getPassword()))
		{
			result.setCode(LOGIN_SUCCESS.getCode());
			result.setMsg(LOGIN_SUCCESS.getMsg());
			userVO.setId(user.getId());
			userVO.setUsername(user.getUsername());
			Map<String, Object> info = new HashMap<>();
	        info.put("username", user.getUsername());
	        info.put("id", user.getId());
	        userVO.setToken(JwtUtil.sign("epet", info)) ;
		}
		result.setData(userVO);
        
		return result;

	}
}
