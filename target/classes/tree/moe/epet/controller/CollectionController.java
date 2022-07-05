package tree.moe.epet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static tree.moe.epet.constant.ResultEnum.*;

import tree.moe.epet.entity.Address;
import tree.moe.epet.entity.Collection;
import tree.moe.epet.entity.Result;
import tree.moe.epet.entity.User;
import tree.moe.epet.service.CollectionService;
import tree.moe.epet.util.JwtUtil;

@RestController
@CrossOrigin
public class CollectionController {
	@Autowired
	CollectionService collectionService;
	
	@RequestMapping(value="/collection/getAllCollections")
	@ResponseBody
	public Result<List<Collection>> getAllCollections(HttpServletRequest request/*@RequestBody User user*/)
	{
		List list = new ArrayList();
		Result result = new Result();
		String token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		User user = new User();
		user.setId((int)info.get("id"));
		
		list = collectionService.getCollections(user.getId());
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(list);
		return result;
	}
	
	@RequestMapping(value="/collection/deleteCollection")
	@ResponseBody
	public Result deleteCollection(@RequestBody Collection collection)
	{
		Collection check;
		Result result = new Result();
		check = collectionService.getCollectionByid(collection);
		if(check==null)
		{
			result.setCode(NO_SUCH_COLLECTION_ITEM.getCode());
			result.setMsg(NO_SUCH_COLLECTION_ITEM.getMsg());
		}
		else
		{
			collectionService.deleteCollectino(collection);
			result.setCode(REQUEST_SUCCESS.getCode());
			result.setMsg(REQUEST_SUCCESS.getMsg());
		}
		return result;
	}
	
	@RequestMapping(value="/collection/insertCollection")
	@ResponseBody
	public Result insertNewCollection(@RequestBody Collection collection)
	{
		Collection check;
		Result result = new Result();
		check = collectionService.getCollectionByItemidAndUserId(collection);
		if((check.getUser_id()==collection.getUser_id() && check.getItem_id()==collection.getItem_id()))
		{
			result.setCode(ITEM_HAS_BEEN_IN_COLLECTION.getCode());
			result.setMsg(ITEM_HAS_BEEN_IN_COLLECTION.getMsg());
		}
		else
		{
			result.setCode(REQUEST_SUCCESS.getCode());
			result.setMsg(REQUEST_SUCCESS.getMsg());
			collectionService.inserNewCollection(collection);
		}
		return result;
	}
	
}
