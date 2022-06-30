package tree.moe.epet.mapper;

import org.apache.ibatis.annotations.Mapper;

import tree.moe.epet.entity.User;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM USER WHERE USERNAME=#{username}")
	User getUserByUsername(String username);//get user by username
	
	@Update("update user set username=#{username},password=#{password},"
			+ "nickname=#{nickname},telephone=#{telephone},sex=#{sex},"
			+ "signature=#{signature},role=#{role},is_enabled=#{is_enabled},"
			+ "default_address=#{default_address} where id=#{id}")
	void updateUserinfo(User user);//updateUserinfo
	
	@Insert("insert into user (username,password,nickname)"
			+ "values(#{username},#{password},#{nickname})")
	void registerNewUser(User user);
	
}


/**/