package tree.moe.epet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.ItemAdmin;
import tree.moe.epet.entity.ItemAdminVo;
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
	
	public List<Item> getItemsByShopId(long shop_id,int left,int right)
	{
		return itemMapper.getItemsByShopid(shop_id,left,right);
	}
	
	public List<Item> getItemByPage(int page,int left , int right)
	{
		return itemMapper.getItemByPage(page,left,right);
	}
	
	public List<Item> getItemByPage(int page, long cat_id,int left , int right)
	{
		return itemMapper.getItemByPageWithCatid(page,cat_id,left,right);
	}
	
	/*public List<Item> searchItem(String keywords , int left , int right)
	{
		return itemMapper.searchItem(keywords,left,right);
	}*/
	
	public List<Item> searchItem(String keywords , int left , int right ,String orderkey ,String sequence,long cat_id)
	{
		return itemMapper.searchItem(keywords,left,right,orderkey,sequence,cat_id);
	}
	
	/*
	 * Natuki Added Below
	 * Natuki Added Below
	 * Natuki Added Below
	 * 
	 */
	
	public List<ItemAdmin> getItemAdminList(ItemAdminVo itemAdminVo) {
		return itemMapper.getItemAdminList(itemAdminVo);
	}
	
	public ItemAdmin getItemAdminById(long id) {
		return itemMapper.getItemAdminById(id);
	}
	
	public boolean updateItem(ItemAdmin itemAdmin) {
		int rowCnt = itemMapper.updateItem(itemAdmin);
		if(rowCnt <= 0) {
			return false;
		}
		return true;
	}
	
	public boolean deleteItem(long id) {
		int rowCnt = itemMapper.deleteItem(id);
		if(rowCnt <= 0) {
			return false;
		}
		return true;
	}
	
	public boolean updateItemSales(ItemAdmin itemAdmin) {
		int rowCnt = itemMapper.updateItemSales(itemAdmin);
		if(rowCnt <= 0) {
			return false;
		}
		return true;
	}
	
	public boolean updateItemPrice(ItemAdmin itemAdmin) {
		int rowCnt = itemMapper.updateItemPrice(itemAdmin);
		if(rowCnt <= 0) {
			return false;
		}
		return true;
	}
	
	public boolean createItem(ItemAdmin itemAdmin) {
		int rowCnt = itemMapper.createItem(itemAdmin);
		if(rowCnt <= 0) {
			return false;
		}
		return true;
	}
}
