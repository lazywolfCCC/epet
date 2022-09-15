package tree.moe.epet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import tree.moe.epet.entity.Video;

@Mapper
public interface VideoMapper {

	
	List<Video> getAllVideo();	
	
	Video getVideoById(long id);
	
	/*wujie Added below */
	/*wujie Added below */
	/*wujie Added below */
	
	@Select(" Select * from video where title LIKE '%${title}%' ")
	List<Video> getVideosByTitle(Video video);
	
	@Select(" Select * from video limit #{page},#{right} ")
	List<Video> getVideosByPaging(int right,int page);
	
	@Select(" Select count(id) from video")
	int getVideosCount();
	
	@Insert({"Insert into video(url,title,description) values(#{url},#{title},#{description})"})
	int InsertVideo(Video video);
	
	@Update("Update video Set url=#{url},title=#{title},description=#{description} where id=#{id}")
	int updateVideo(Video video);
	
	@Delete("Delete from video where id=#{id}")
	int deleteVideo(Video video);
}
