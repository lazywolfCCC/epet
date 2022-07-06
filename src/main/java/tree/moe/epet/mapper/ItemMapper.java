package tree.moe.epet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.ItemVO;
import tree.moe.epet.entity.Item_cat;
import tree.moe.epet.entity.Shop;

@Mapper
public interface ItemMapper {

	
	List<Item> getAllItems();
	
	@Select("SELECT * FROM item WHERE cat_id=#{id}")
	List<Item> getItemsByCategory(Item_cat cat);
	
	@Select("SELECT * FROM item WHERE ID=#{id}")
	Item getItemById(Item item);
	
	@Select("Select * from item where shop_id=#{id}")
	List<Item> getItemsByShopid(Shop shop);
	
	Item getItem(long id);
	
	@Select("Select * from item limit #{left},#{right}")
	List<Item> getItemByPage(int page,int left , int right);
	
	@Select("Select * from item where cat_id=#{cat_id} limit #{left},#{right}")
	List<Item> getItemByPageWithCatid(int page , long cat_id , int left , int right);
	
	@Select("Select * from item where name like #{keywords}")
	List<Item> searchItem(String keywords);
}
