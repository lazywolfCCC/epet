package tree.moe.epet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import tree.moe.epet.entity.*;
import tree.moe.epet.entity.Video_comment;

@Mapper
public interface VideoCommentMapper {

	@Select("Select * from video_comment where video_id=#{id}")
	List<Video_comment> getCommentByvideoId(Video video);
	
	@Insert("Insert into video_comment (video_id,user_id,content,is_parent"
			+ ",parent_id,create_time,reply_to,is_unread)values(#{video_id},"
			+ "#{user_id},#{content},#{is_parent},#{parent_id},#{create_time},"
			+ "#{reply_to},#{is_unread})")
	void insertNewComment(Video_comment comment);
	
	@Select("Select * from video_comment where video_id=#{video_id} limit #{left},#{right}")
	List<Video_comment> getCommentByPage(CommentVO commentvo);
	
}
