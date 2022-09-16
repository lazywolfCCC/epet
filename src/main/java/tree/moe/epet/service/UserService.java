package tree.moe.epet.service;

import java.util.List;

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
	
	public void updateUserByAdmin(User user)
	{
		usermapper.updateUserinfoByAdmin(user);
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
	
	public int getPageTotal()
	{
		return usermapper.getPageTotal();
	}
	
	/*yifan added below*/
	/*yifan added below*/
	/*yifan added below*/
	public List<User> getUserByUsername1(String username)  //通过账号检索
	{
		List<User> user;
		user = usermapper.getUserByUsername1(username);
		return user;
	}
	
	public void addNewUser(User user)
	{
		usermapper.addNewUser(user);
	}
	
	//查询所有
	public List<User> findAll(){  
		return usermapper.findAll();
	}
		
	//删除数据
	public Integer delete(long id) {
		return usermapper.delete(id);
	}
}
