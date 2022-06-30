package tree.moe.epet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.Collection;
import tree.moe.epet.entity.User;
import tree.moe.epet.service.CollectionService;

@RestController
@CrossOrigin
public class CollectionController {
	@Autowired
	CollectionService collectionService;
	
	@RequestMapping(value="/collection/getAllCollections")
	@ResponseBody
	public List<Collection> getAllCollections(@RequestBody User user)
	{
		List list;
		try 
		{
			list = collectionService.getCollectionByUserid(user);
		}
		catch(Exception e)
		{
			return null;
		}
		return list;
	}
	
	@RequestMapping(value="/collection/deleteCollection")
	@ResponseBody
	public String deleteCollection(@RequestBody Collection collection)
	{
		Collection check;
		try 
		{
			check = collectionService.getCollectionByid(collection);
			if(check==null)
			{
				return "theCollectionNotExist";
			}
			else
			{
				collectionService.deleteCollectino(collection);
			}
		}
		catch(Exception e)
		{
			return "error";
		}
		
		return "deleteCollectionDone";
	}
	
	@RequestMapping(value="/collection/insertCollection")
	@ResponseBody
	public String insertNewCollection(@RequestBody Collection collection)
	{
		Collection check;
		try 
		{
			check = collectionService.getCollectionByItemidAndUserId(collection);
			if((check.getUser_id()==collection.getUser_id() && check.getItem_id()==collection.getItem_id()))
			{
				return "collectionExist";
			}
			else
			{
				collectionService.inserNewCollection(collection);
			}
		}
		catch(Exception e)
		{
			return "error"+e.getMessage();
		}
		return "insertCollectionDone";
	}
	
}
