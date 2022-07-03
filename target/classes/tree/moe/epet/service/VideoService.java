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
}
