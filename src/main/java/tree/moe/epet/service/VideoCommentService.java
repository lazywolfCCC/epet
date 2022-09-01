package tree.moe.epet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tree.moe.epet.entity.CommentVO;
import tree.moe.epet.entity.Video;
import tree.moe.epet.entity.Video_comment;
import tree.moe.epet.mapper.VideoCommentMapper;

@Service
public class VideoCommentService {

	@Autowired
	VideoCommentMapper commentMapper;
	
	public List<Video_comment> getCommentByVideoId(Video video)
	{
		return commentMapper.getCommentByvideoId(video.getId());
	}
	
	public void insertNewComment(Video_comment comment)
	{
		commentMapper.insertNewComment(comment);
	}
	
	public List<Video_comment> getCommentByPage(CommentVO commentvo){
		return commentMapper.getCommentByPage(commentvo.getVideo_id(),commentvo.getLeft(),commentvo.getRight());
	}
}
