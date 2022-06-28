package tree.moe.epet.mapper;

import org.apache.ibatis.annotations.Mapper;

import tree.moe.epet.entity.User;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM USER WHERE USERNAME=#{username}")
	User getUserByUsername(String username);//get user by username
	
	
}
