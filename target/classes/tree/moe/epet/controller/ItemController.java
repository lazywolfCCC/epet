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
import tree.moe.epet.exception.ParameterException;
import tree.moe.epet.service.ItemService;

import static tree.moe.epet.constant.ResultEnum.*;

@RestController
@CrossOrigin
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@RequestMapping(value="/item/getAllitems")
	@ResponseBody
	public List<Item> getAllItems()
	{
		return itemService.getAllItems();
	}
	
	@RequestMapping(value="/item/getitemsBycat")
	@ResponseBody
	public List<Item> getItemsBycat(@RequestBody Item_cat cate)
	{
		return itemService.getItemsByCategory(cate);
	}
	
	@RequestMapping(value="/item/getItemById")
	@ResponseBody
	public Item getItemById(@RequestBody Item item)
	{
		return itemService.getItemById(item);
	}
	
	@RequestMapping(value="/item/getItemByShopId")
	@ResponseBody
	public List<Item> getItemsByShopId(@RequestBody Shop shop)
	{
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
		list = itemService.getItemByPage(item);
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
	
	@RequestMapping(value="/item/search")
	@ResponseBody
	public Result searchItem(@RequestBody Item keywords) throws Exception 
	{
		Result<List<Item>> result = new Result();
		List<Item> list = new ArrayList();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		list = itemService.searchItem("%"+keywords.getName()+"%");
		result.setData(list);
		return result;
	}
}
