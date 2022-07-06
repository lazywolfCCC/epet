package tree.moe.epet.mapper;

import org.apache.ibatis.annotations.Mapper;

import tree.moe.epet.entity.User;
import tree.moe.epet.entity.UserVO;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM user WHERE username=#{username}")
	User getUserByUsername(String username);//get user by username
	
	@Select("SELECT * FROM user WHERE id=#{id}")
	User getUserById(UserVO user);
	
	@Update("update user set username=#{username},password=#{password},"
			+ "nickname=#{nickname},telephone=#{telephone},sex=#{sex},"
			+ "signature=#{signature},role=#{role},is_enabled=#{is_enabled},"
			+ "default_address=#{default_address} where id=#{id}")
	void updateUserinfo(User user);//updateUserinfo
	
	@Insert("insert into user (username,password,nickname)"
			+ "values(#{username},#{password},#{nickname})")
	void registerNewUser(User user);
	
	@Update("update user set default_address=#{address_id} where id=#{user_id}")
	void changeDefaultAddress(long address_id , long user_id);
	
}


/**/