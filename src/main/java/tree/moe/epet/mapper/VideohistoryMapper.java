package tree.moe.epet.mapper;

import org.apache.ibatis.annotations.Mapper;

import tree.moe.epet.entity.UserVO;
import tree.moe.epet.entity.Videohistory;

import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface VideohistoryMapper {
	@Select("SELECT * FROM videohistory where user_id=#{id}")
	List<Videohistory> getVideohistoryByUserid(UserVO user);
	
	@Select("SELECT * FROM videohistory where id=#{id}")
	Videohistory getVideoHistoryById(Videohistory history);
	
	@Delete("DELETE from videohistory where id=#{#id}")
	void deleteHistory(Videohistory history);
	
	@Insert("INSERT INTO videohistory (user_id,video_id,viewtime) "
			+ "values(#{user_id},#{video_id},#{viewtime})")
	void insertNewHistory(Videohistory history);
	
	@Update("Update videohistory set viewtime=#{viewtime} where id=#{id} ")
	void updateHistory(Videohistory history);
}
