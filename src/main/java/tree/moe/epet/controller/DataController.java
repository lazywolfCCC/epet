package tree.moe.epet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tree.moe.epet.entity.Data;
import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.Item_cat;
import tree.moe.epet.entity.Order_item;
import tree.moe.epet.entity.Result;
import tree.moe.epet.service.*;

import static tree.moe.epet.constant.ResultEnum.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping(value="/data")
public class DataController {
	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemCatService itemCatService;
	@Autowired
	ItemSkuService itemSkuService;
	@Autowired
	OrderListService orderlistService;
	
	@RequestMapping(value="/getOrderItemsData")
	public Result<List> getEchartsData()
	{
		Result<List> result = new Result();
		List<Data> list = new ArrayList();
		Date date = new Date();
		List<Order_item> temp = new ArrayList();
		Map<String,Double> map = new HashMap();
		temp = orderlistService.getEchartsData();
		for(int i = 0; i < temp.size();i++)
		{
			double value = temp.get(i).getSubtotal();
			String key= temp.get(i).getProduct_name();
			if(map.containsKey(key))
			{
				value += map.get(key);
				map.put(key, value);
				continue;
			}
			else
			{
				map.put(key, value);
			}
		}
		for(int j = 0 ; j < map.size();j++)
		{
			Data dataTemp = 
					new Data(map.keySet().toArray()[j].toString(),map.get(map.keySet().toArray()[j].toString()));
			list.add(dataTemp);
		}
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(list);
		return result;
	}
	@RequestMapping(value="/getItemCatData")
	public Result<List<Data>> getSkuEchartsData()
	{
		Map<String,Integer> map = new HashMap();
		Map<Long,Integer> temp = new HashMap();
		List<Data> dataList = new ArrayList();
		Result<List<Data>> result = new Result();
		List<Item_cat> list = itemCatService.getCatAdmin();
		List<Item> itemList = itemService.getItemOnlyCat();
		for(int i = 0 ; i < list.size() ; i++)
		{
			map.put(list.get(i).getName(), 0);
			temp.put(list.get(i).getId(), 0);
		}
		for(int j = 0 ; j < itemList.size() ; j++)
		{
			int value = 0;
			if(temp.containsKey(itemList.get(j).getCat_id()))
			{
				value = temp.get(itemList.get(j).getCat_id())+1;
				temp.put(itemList.get(j).getCat_id(), value);
			}
		}
		for(int i = 0 ; i < list.size() ; i++)
		{
			Data dTemp = new Data(list.get(i).getName(),temp.get(list.get(i).getId()));
			dataList.add(dTemp);
		}
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(dataList);
		return result;
	}
}
