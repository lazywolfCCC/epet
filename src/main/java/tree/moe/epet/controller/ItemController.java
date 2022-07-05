package tree.moe.epet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.Item_cat;
import tree.moe.epet.entity.Shop;
import tree.moe.epet.service.ItemService;

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
}
