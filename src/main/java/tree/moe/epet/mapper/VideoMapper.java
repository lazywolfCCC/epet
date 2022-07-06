package tree.moe.epet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tree.moe.epet.entity.Video;

@Mapper
public interface VideoMapper {

	
	List<Video> getAllVideo(); 
	
	
	Video getVideoById(long id); 
}
