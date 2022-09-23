package tree.moe.epet.mapper;

import org.apache.ibatis.annotations.Mapper;

import tree.moe.epet.entity.User;
import tree.moe.epet.entity.UserVO;

import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM user WHERE username=#{username}")
	User getUserByUsername(String username);//get user by username
	
	@Select("SELECT * FROM user WHERE id=#{id}")
	User getUserById(long id);
	
	@Update("update user set username=#{username},password=#{password},"
			+ "nickname=#{nickname},telephone=#{telephone},sex=#{sex},"
			+ "signature=#{signature},role=#{role},is_enabled=#{is_enabled},"
			+ "default_address=#{default_address} where id=#{id}")
	void updateUserinfo(User user);//updateUserinfo
	
	@Update("update user set "
			+ "nickname=#{nickname},telephone=#{telephone},sex=#{sex},"
			+ "role=#{role},is_enabled=#{is_enabled},money=#{money}"
			+ " where id=#{id}")
	void updateUserinfoByAdmin(User user);
	
	@Update("update user set "
			+ "money=#{money}"
			+ " where id=#{id}")
	void updateUserMoneyByAdmin(User user);
	
	@Select("Select count(id) from user ")
	int getPageTotal();
	
	@Insert("insert into user (username,password,nickname)"
			+ "values(#{username},#{password},#{nickname})")
	void registerNewUser(User user);
	
	@Update("update user set default_address=#{address_id} where id=#{user_id}")
	void changeDefaultAddress(long address_id , long user_id);
	
	@Select("SELECT id,username,nickname,telephone,sex,signature,is_enabled,default_address,money FROM user WHERE id=#{id}")
	User getUserWithOutPasswordById(long id);
	
	/*yifan added below*/
	/*yifan added below*/
	/*yifan added below*/
	
	@Select("Select * from user where username like #{username}")
	List<User> getUserByUsername1(String username);//通过username查询数据
	
	@Insert("insert into user (username,password,nickname,role,sex,telephone,is_enabled,default_address,signature)"
			+ "values(#{username},#{password},#{nickname},#{role},#{sex},#{telephone},#{is_enabled},#{default_address},#{signature})")
	void addNewUser(User user);
	
	 //查询全部
	@Select("select * from user")
	List<User> findAll(); 
	
	//删除数据
	@Delete("delete from user where id=#{id}")
	public Integer delete(long id); 
	
}


/**/