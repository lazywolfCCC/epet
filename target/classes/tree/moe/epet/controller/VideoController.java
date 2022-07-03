package tree.moe.epet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.Video;
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
}
