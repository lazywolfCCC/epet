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
import tree.moe.epet.entity.ItemAdmin;
import tree.moe.epet.entity.ItemAdminVo;
import tree.moe.epet.entity.ItemVO;
import tree.moe.epet.entity.Item_cat;
import tree.moe.epet.entity.OrderVO;
import tree.moe.epet.entity.Result;
import tree.moe.epet.entity.Shop;
import tree.moe.epet.entity.ShopVO;
import tree.moe.epet.exception.LackParameterException;
import tree.moe.epet.exception.ObjectNoFoundException;
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
	public List<Item> getItemsByShopId(@RequestBody ShopVO shopvo) throws Exception
	{
		int count = 20;
		if(shopvo.getId()==0)
		{
			throw new LackParameterException();
		}
		if(shopvo.getPage() <= 0)
		{
			shopvo.setPage(0);
		}
		int left = count * (shopvo.getPage()-1);
		int right = count;
		return itemService.getItemsByShopId(shopvo.getId(),left,right);
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
	
	@RequestMapping(value="/item/search")
	@ResponseBody
	public Result searchItem(@RequestBody ItemVO keywords) throws Exception 
	{
		/*if(keywords.getKeywords().isEmpty())
		{
			throw new LackParameterException();
		}*/
		if(keywords.getPage() <=0)
		{
			keywords.setPage(1);
		}
		int count = 6;
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
	}
	
	@RequestMapping(value="/item/getPageCountByKeywords")
	@ResponseBody
	public Result getPageCountByKeywords(@RequestBody ItemVO info) throws Exception
	{
		Result result = new Result();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(itemService.getPageCountByKeyWords("%"+info.getKeywords()+"%"));
		return result;
	}
	
	
	
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
		if(info.getOrderedKey()==null || info.getOrderedKey().length() <=0)
		{
			info.setOrderedKey("id");
		}
		if(info.getSequence()==null || info.getSequence().length()<=0)
		{
			info.setSequence("desc");
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
	
	@RequestMapping(value="/item/getPageCount")
	@ResponseBody
	public Result getPageCount()
	{
		Result<Integer> result = new Result();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(itemService.getPageCount());
		return result;
	}
	/*
	 * Natuki Added Below
	 * Natuki Added Below
	 * Natuki Added Below
	 * 
	 */
	@RequestMapping(value="/item/getItemListEx")
	@ResponseBody
	public Result getItemListEx(@RequestBody ItemAdminVo itemAdminVo) {
		Integer page = itemAdminVo.getPage();
		//System.out.println(page);
		if(page == null || page.intValue() <= 0) {
			page = 1;
			itemAdminVo.setPage(page);
		}
		Integer limit = itemAdminVo.getLimit();
		if(limit == null || limit.intValue() <= 0) {
			limit = 20;
			itemAdminVo.setLimit(limit);
		}
		int offset = (page - 1) * limit;
		itemAdminVo.setOffset(offset);
		List<ItemAdmin> list = itemService.getItemAdminList(itemAdminVo);
		
		Result<List<ItemAdmin>> result = new Result<>();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(list);
		return result;
	}
	
	@RequestMapping(value="/item/getItemByIdEx")
	@ResponseBody
	public Result getItemAdminById(@RequestBody ItemAdmin itemAdmin) throws Exception{
		if(itemAdmin == null || itemAdmin.getId() == null || itemAdmin.getId().intValue() <= 0) {
			throw new LackParameterException();
		}
		
		ItemAdmin itemFound = itemService.getItemAdminById(itemAdmin.getId());
		
		Result<ItemAdmin> result = new Result<ItemAdmin>();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(itemFound);
		return result;
	}
	
	@RequestMapping(value="/item/updateItem")
	@ResponseBody
	public Result updateItem(@RequestBody ItemAdmin itemAdmin) throws Exception {
		if(itemAdmin == null || itemAdmin.getId() == null || itemAdmin.getId().intValue() <= 0) {
			throw new LackParameterException();
		}
		
		ItemAdmin itemFound = itemService.getItemAdminById(itemAdmin.getId());
		if(itemFound == null) {
			throw new ObjectNoFoundException();
		}
		
		boolean updateRes = itemService.updateItem(itemAdmin);
		
		Result<ItemAdmin> result = new Result<ItemAdmin>();
		if(updateRes) {
			result.setCode(REQUEST_SUCCESS.getCode());
			result.setMsg(REQUEST_SUCCESS.getMsg());
			result.setData(itemAdmin);
		} else {
			result.setCode(UPDATE_FAILED.getCode());
			result.setMsg(UPDATE_FAILED.getMsg());
		}
		
		return result;
	}
	
	@RequestMapping(value="/item/deleteItem")
	@ResponseBody
	public Result deleteItem(@RequestBody ItemAdmin itemAdmin) throws Exception {
		if(itemAdmin == null || itemAdmin.getId() == null || itemAdmin.getId().intValue() <= 0) {
			throw new LackParameterException();
		}
		
		ItemAdmin itemFound = itemService.getItemAdminById(itemAdmin.getId());
		if(itemFound == null) {
			throw new ObjectNoFoundException();
		}
		
		boolean deleteRes = itemService.deleteItem(itemAdmin.getId());
		
		Result<ItemAdmin> result = new Result<ItemAdmin>();
		if(deleteRes) {
			result.setCode(REQUEST_SUCCESS.getCode());
			result.setMsg(REQUEST_SUCCESS.getMsg());
		} else {
			result.setCode(DELETE_FAILED.getCode());
			result.setMsg(DELETE_FAILED.getMsg());
		}
		return result;
	}
	
	@RequestMapping(value="/item/createItem")
	@ResponseBody
	public Result createItem(@RequestBody ItemAdmin itemAdmin) throws Exception {
		if(itemAdmin == null || itemAdmin.getShopId() == null || itemAdmin.getShopId().intValue() <= 0) {
			throw new LackParameterException();
		}
		if(itemAdmin.getShopId() == null || itemAdmin.getShopId().intValue() <= 0) {
			throw new LackParameterException();
		}
		if(itemAdmin.getName() == null) {
			throw new LackParameterException();
		}
		
		itemAdmin.setPrice(0.0);
		itemAdmin.setSales(0);
		
		boolean createRes = itemService.createItem(itemAdmin);
		
		Result<ItemAdmin> result = new Result<ItemAdmin>();
		if(createRes) {
			result.setCode(REQUEST_SUCCESS.getCode());
			result.setMsg(REQUEST_SUCCESS.getMsg());
			result.setData(itemAdmin);
		} else {
			result.setCode(CREATE_FAILED.getCode());
			result.setMsg(CREATE_FAILED.getMsg());
		}
		
		return result;
	}
}
