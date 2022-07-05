package tree.moe.epet.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.CommentVO;
import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.ItemVO;
import tree.moe.epet.entity.Result;
import tree.moe.epet.entity.Video;
import tree.moe.epet.entity.Video_comment;
import tree.moe.epet.service.VideoCommentService;

import static tree.moe.epet.constant.ResultEnum.*;

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
	public Result insertComment(@RequestBody Video_comment comment)
	{
		if(comment.getCreate_time()==null)
		{
			comment.setCreate_time(new Date());
		}
		commentService.insertNewComment(comment);
		Result result = new Result();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		return result;
	}
	
	@RequestMapping(value="/comment/getCommentByPage")
	@ResponseBody
	public Result getCommentByPage(@RequestBody CommentVO commentvo) throws Exception 
	{
		Result<List<Video_comment>> result = new Result();
		List<Video_comment> list = new ArrayList();
		int count = 20;//设置每页返回的物品数量
		commentvo.setLeft(count*(commentvo.getPage()-1));
		commentvo.setRight(count);
		list = commentService.getCommentByPage(commentvo);
		if(list.size() >= count)
		{
			result.setCode(REQUEST_SUCCESS.getCode());
			result.setMsg(REQUEST_SUCCESS.getMsg());
		}
		else
		{
			result.setCode(REACH_ITEM_BOTTOM.getCode());
			result.setMsg(REACH_ITEM_BOTTOM.getMsg());
		}
		result.setData(list);
		return result;
	}
}
