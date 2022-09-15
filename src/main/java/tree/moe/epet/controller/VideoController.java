package tree.moe.epet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.ItemVO;
import tree.moe.epet.entity.Video;
import tree.moe.epet.exception.LackParameterException;
import tree.moe.epet.service.VideoService;

@RestController
@CrossOrigin
public class VideoController {
	
	@Autowired
	VideoService videoService;
	
	@RequestMapping(value="/video/getVideos")
	@ResponseBody
	public List<Video> getAllVideo()
	{
		return videoService.getAllVideo();
	}
	
	@RequestMapping(value="/video/getVideoById")
	@ResponseBody
	public Video getVideoById(@RequestBody Video video) throws Exception
	{
		if(video.getId() == 0)
		{
			throw new LackParameterException();
		}
		return videoService.getVideoById(video.getId());
	}
	/*wujie added below*/
	/*wujie added below*/
	/*wujie added below*/
	@RequestMapping(value="/video/getVideosCount")
	@ResponseBody
	public int getVideosCount()
	{
		return videoService.getVideosCount();
	}
	
	@RequestMapping(value="/video/getVideosByPaging")
	@ResponseBody
	public List<Video> getVideosByPaging(@RequestBody ItemVO vo)throws Exception
	{
		int count = 3;
		if(vo.getPage()<=0)
		{
			vo.setPage(1);
		}
		return videoService.getVideosByPaging(count,vo.getPage()-1);
	}
	
	@RequestMapping(value="/video/searchVideos")
	@ResponseBody
	public List<Video> searchVideosByTitle(@RequestBody Video video) throws Exception
	{
		if(video.getTitle() == null)
		{
			throw new LackParameterException();
		}
		return videoService.searchVideoByTitle(video);
	}
	
	@RequestMapping(value="/video/insertVideo")
	@ResponseBody
	public int insertVideo(@RequestBody Video video) throws Exception
	{
		return videoService.insertVideo(video);
	}

	@RequestMapping(value="/video/updateVideo")
	@ResponseBody
	public int updateVideo(@RequestBody Video video) throws Exception
	{
		return videoService.updateVideo(video);
	}
	
	@RequestMapping(value="/video/deleteVideo")
	@ResponseBody
	public int deleteVideo(@RequestBody Video video) throws Exception
	{
		return videoService.deleteVideo(video);
	}
}
