package tree.moe.epet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tree.moe.epet.entity.Collection;
import tree.moe.epet.entity.User;
import tree.moe.epet.mapper.CollectionMapper;

@Service
public class CollectionService {
	@Autowired
	CollectionMapper collectionMapper;
	
	public List<Collection> getCollectionByUserid(User user)
	{
		return collectionMapper.getCollectionByUserid(user);
	}
	
	public void inserNewCollection(Collection collection)
	{
		collectionMapper.insertNewCollection(collection);
	}
	
	public void deleteCollectino(Collection collection)
	{
		collectionMapper.deleteCollection(collection);
	}
	
	public Collection getCollectionByid(Collection colletion)
	{
		return collectionMapper.getCollectionByid(colletion);
	}
	
	public Collection getCollectionByItemidAndUserId(Collection collection)
	{
		return collectionMapper.getCollectionByUseridAndItemid(collection);
	}
}
