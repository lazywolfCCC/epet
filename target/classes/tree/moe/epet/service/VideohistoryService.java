package tree.moe.epet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tree.moe.epet.entity.UserVO;
import tree.moe.epet.entity.Videohistory;
import tree.moe.epet.mapper.VideohistoryMapper;

@Service
public class VideohistoryService {
	@Autowired
	VideohistoryMapper historyMapper;
	
	public List<Videohistory> getVideohistoryByUserid(UserVO user)
	{
		return historyMapper.getVideohistoryByUserid(user);
	}
	
	public Videohistory getVideoHistoryById(Videohistory history)
	{
		return historyMapper.getVideoHistoryById(history);
	}
	
	public void deleteHistory(Videohistory history)
	{
		historyMapper.deleteHistory(history);
	}
	
	public void insertNewHistory(Videohistory history)
	{
		historyMapper.insertNewHistory(history);
	}
	
	public void updateHistory(Videohistory history)
	{
		historyMapper.updateHistory(history);
	}
}
