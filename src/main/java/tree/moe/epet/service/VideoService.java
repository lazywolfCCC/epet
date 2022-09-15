package tree.moe.epet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tree.moe.epet.entity.Video;
import tree.moe.epet.mapper.VideoMapper;

@Service
public class VideoService {
	@Autowired
	VideoMapper videoMapper;
	
	public List<Video> getAllVideo() 
	{
		return videoMapper.getAllVideo();
	} 
	
	public Video getVideoById(long id) 
	{
		return videoMapper.getVideoById(id);
	} 
	
	/*wujie Added below */
	/*wujie Added below */
	/*wujie Added below */
	
	public List<Video> getVideosByPaging(int right,int page){
		return videoMapper.getVideosByPaging(right,page);
	};
	
	public List<Video> searchVideoByTitle(Video video)
	{
		return videoMapper.getVideosByTitle(video);
	}
	public int getVideosCount()
	{
		return videoMapper.getVideosCount();
	}
	 public int insertVideo(Video video)
	 {
		 return videoMapper.InsertVideo(video);
	 }
	 public int updateVideo(Video video)
	 {
		 return videoMapper.updateVideo(video);
	 }
	 public int deleteVideo(Video video)
	 {
		 return videoMapper.deleteVideo(video);
	 }
	
}
