package tree.moe.epet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.ItemVO;
import tree.moe.epet.entity.Item_cat;
import tree.moe.epet.entity.Shop;
import tree.moe.epet.mapper.ItemMapper;

@Service
public class ItemService {
	@Autowired
	ItemMapper itemMapper;
	
	public List<Item> getAllItems()
	{
		List<Item> itemlist = itemMapper.getAllItems();
		return itemlist;
	}
	
	public List<Item> getItemsByCategory(Item_cat cat)
	{
		List<Item> itemlist = itemMapper.getItemsByCategory(cat);
		return itemlist;
	}
	
	public Item getItemById(Item item)
	{
		return itemMapper.getItem(item.getId());
	}
	
	public List<Item> getItemsByShopId(Shop shop)
	{
		return itemMapper.getItemsByShopid(shop);
	}
	
	public List<Item> getItemByPage(ItemVO itemvo)
	{
		return itemMapper.getItemByPage(itemvo);
	}
	
	public List<Item> searchItem(String keywords)
	{
		return itemMapper.searchItem(keywords);
	}
}
