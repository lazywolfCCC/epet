package tree.moe.epet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.Video;
import tree.moe.epet.entity.Video_comment;
import tree.moe.epet.service.VideoCommentService;

@RestController
@CrossOrigin
public class VideoCommentController {
	@Autowired
	VideoCommentService commentService;
	
	@RequestMapping(value="/comment/getComment")
	@ResponseBody
	public List<Video_comment> getCommentByVideoId(@RequestBody Video video)
	{
		
		return commentService.getCommentByVideoId(video);
	}
	
	@RequestMapping(value="/comment/insertComment")
	@ResponseBody
	public String insertComment(@RequestBody Video_comment comment)
	{
		commentService.insertNewComment(comment);
		return "insertNewCommentDone";
	}
}
