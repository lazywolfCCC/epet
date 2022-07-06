package tree.moe.epet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tree.moe.epet.entity.User;
import tree.moe.epet.entity.UserVO;
import tree.moe.epet.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	UserMapper usermapper;
	
	public User getUserByUsername(String username)
	{
		User user;
		user = usermapper.getUserByUsername(username);
		return user;
	}
	
	public void updateUserinfo(User user)
	{
		usermapper.updateUserinfo(user);
	}
	
	public void registrtNewUser(User user)
	{
		usermapper.registerNewUser(user);
	}
	
	public void changeDefaultAddress(long address_id , long user_id)
	{
		usermapper.changeDefaultAddress(address_id, user_id);
	}
	
	public User getUserWithOutPasswordById(long id)
	{
		return usermapper.getUserById(id);
	}
}
