package tree.moe.epet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tree.moe.epet.entity.ItemVO;
import tree.moe.epet.entity.Item_cat;
import tree.moe.epet.mapper.ItemCatMapper;

@Service
public class ItemCatService {

	@Autowired
	ItemCatMapper catMapper;
	
	public List<Item_cat> getAllCat()
	{
		return catMapper.getAllCat();
	}
	
	public List<Item_cat> getSonCatsById(long catid)
	{
		return catMapper.getItemCatById(catid);
	}
	
	public List<Item_cat> getAllItemCat(int left , int right)
	{
		return catMapper.getAllItemCat(left, right);
	}
	
	public void deleteItemCatById(long id)
	{
		catMapper.deleteItemCatById(id);
	}
	
	public Item_cat getCatById(long id)
	{
		return catMapper.getCatById(id);
	}
	
	public void updateItemCat(Item_cat itemcat)
	{
		catMapper.updateItemCatById(itemcat);
	}
	
	public int createNewCat(Item_cat item_cat)
	{
		return catMapper.createNewCat(item_cat);
	}
	
	public int getPageCount()
	{
		return catMapper.getPageCount();
	}
}
