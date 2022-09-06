package tree.moe.epet.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.ItemVO;
import tree.moe.epet.entity.Item_cat;
import tree.moe.epet.entity.Result;
import tree.moe.epet.entity.Shop;
import tree.moe.epet.exception.LackParameterException;
import tree.moe.epet.exception.ParameterException;
import tree.moe.epet.service.ItemCatService;
import tree.moe.epet.service.ItemService;
import tree.moe.epet.util.JudgeParameter;

import static tree.moe.epet.constant.ResultEnum.*;

@RestController
@CrossOrigin
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemCatService itemCatService;
	
	@RequestMapping(value="/item/getAllitems")
	@ResponseBody
	public List<Item> getAllItems()
	{
		return itemService.getAllItems();
	}
	
	@RequestMapping(value="/item/getitemsBycat")
	@ResponseBody
	public List<Item> getItemsBycat(@RequestBody Item_cat cate)throws Exception
	{
		if(cate.getId()==0)
		{
			throw new LackParameterException();
		}
		return itemService.getItemsByCategory(cate);
	}
	
	@RequestMapping(value="/item/getItemById")
	@ResponseBody
	public Item getItemById(@RequestBody Item item) throws Exception
	{
		if(item.getId()==0)
		{
			throw new LackParameterException();
		}
		return itemService.getItemById(item);
	}
	
	@RequestMapping(value="/item/getItemByShopId")
	@ResponseBody
	public List<Item> getItemsByShopId(@RequestBody Shop shop) throws Exception
	{
		if(shop.getId()==0)
		{
			throw new LackParameterException();
		}
		return itemService.getItemsByShopId(shop);
	}
	
	@RequestMapping(value="/item/getItemsByPage")
	@ResponseBody
	public Result getItemByPage(@RequestBody ItemVO item) throws Exception 
	{
		Result<List<Item>> result = new Result();

		List<Item> list = new ArrayList();
		
		int count = 20;//设置每页返回的物品数量
		item.setLeft(count*(item.getPage()-1));
		item.setRight(count);
		if(item.getCat_id()==0)
		{
			list = itemService.getItemByPage(item.getPage(),item.getLeft(),item.getRight());
		}
		else
		{
			list = itemService.getItemByPage(item.getPage(),item.getCat_id(),item.getLeft(),item.getRight());
		}
		
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
	
	/*@RequestMapping(value="/item/search")
	@ResponseBody
	public Result searchItem(@RequestBody ItemVO keywords) throws Exception 
	{
		if(keywords.getKeywords().isEmpty())
		{
			throw new LackParameterException();
		}
		int count = 20;
		int left = count*(keywords.getPage()-1);
		int right = count;
		Result<List<Item>> result = new Result();
		List<Item> list = new ArrayList();
		
		list = itemService.searchItem("%"+keywords.getKeywords()+"%",left,right);
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
	}*/
	
	@RequestMapping(value="/item/getOrderedItems")
	@ResponseBody
	public Result getOrderedItems(@RequestBody ItemVO info) throws Exception 
	{
		
		/*if(info.getKeywords().isEmpty())
		{
			throw new LackParameterException();
		}*/
		int count = 20;
		if(info.getPage()<=0)
		{
			info.setPage(1);
		}
		int left = count*(info.getPage()-1);
		int right = count;
		Result<List<Item>> result = new Result();
		List<Item> list = new ArrayList();
		List<Item_cat> sons = new ArrayList();
		sons = itemCatService.getSonCatsById(info.getCat_id());
		for(int i = 0 ; i < sons.size() ; i++)
		{
			sons.addAll(itemCatService.getSonCatsById(sons.get(i).getId()));
		}
		if(sons.size() <=0)
		{
			list = itemService.searchItem("%"+info.getKeywords()+"%",
				left,right,info.getOrderedKey(),info.getSequence(),info.getCat_id());
		}
		else
		{
			list = itemService.searchItem("%"+info.getKeywords()+"%",
					left,right,info.getOrderedKey(),info.getSequence(),info.getCat_id());
			for(int i = 0 ; i < sons.size();i++)
			{
				list.addAll(itemService.searchItem("%"+info.getKeywords()+"%",
						left,right,info.getOrderedKey(),info.getSequence(), sons.get(i).getId()));
			}
			
		}
		
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
	
	@RequestMapping(value="/item/getClassfiedItems")
	@ResponseBody
	public Result getClassfiedItems(@RequestBody ItemVO info) throws Exception 
	{
		
		/*if(info.getKeywords().isEmpty())
		{
			throw new LackParameterException();
		}*/
		System.out.println(info);
		int count = 20;
		int left = count*(info.getPage()-1);
		int right = count;
		Result<List<Item>> result = new Result();
		List<Item> list = new ArrayList();
		
		list = itemService.searchItem("%"+info.getKeywords()+"%",left,right,
				info.getOrderedKey(),info.getSequence(),info.getCat_id());
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
