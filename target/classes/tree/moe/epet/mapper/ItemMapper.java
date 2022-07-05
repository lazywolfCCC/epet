package tree.moe.epet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.Item_cat;
import tree.moe.epet.entity.Shop;

@Mapper
public interface ItemMapper {

	@Select("SELECT * FROM ITEM")
	List<Item> getAllItems();
	
	@Select("SELECT * FROM ITEM WHERE cat_id=#{id}")
	List<Item> getItemsByCategory(Item_cat cat);
	
	@Select("SELECT * FROM ITEM WHERE ID=#{id}")
	Item getItemById(Item item);
	
	@Select("Select * from item where shop_id=#{id}")
	List<Item> getItemsByShopid(Shop shop);
	
	@Select("Select * from item where id=#{id}")
	Item getItem(long id);
}
