package tree.moe.epet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.ItemAdmin;
import tree.moe.epet.entity.ItemAdminVo;
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
	
	@Select("Select * from item where shop_id=#{shop_id} limit #{left},#{right}")
	List<Item> getItemsByShopid(long shop_id,int left , int right);
	
	
	
	Item getItem(long id);
	
	@Select("Select * from item limit #{left},#{right}")
	List<Item> getItemByPage(int page,int left , int right);
	
	@Select("Select * from item where cat_id=#{cat_id} limit #{left},#{right}")
	List<Item> getItemByPageWithCatid(int page , long cat_id , int left , int right);
	
	/*@Select("Select * from item where name like #{keywords} limit #{left},#{right}")
	List<Item> searchItem(String keywords , int left , int right);*/
	
	@Select("Select count(id) from item")
	Integer getPageCount();
	
	@Select("Select count(id) from item where name like #{keywords} or subtitle like #{keywords}")
	Integer getPageCountByKeywords(String keywords);
	
	//@Select("Select * from item where name like #{keywords} order by #{orderkey} limit #{left},#{right}")
	List<Item> searchItem(String keywords , int left , int right ,String orderkey ,String sequence,long cat_id);
	/*
	 * Natuki Added Below
	 * Natuki Added Below
	 * Natuki Added Below
	 * 
	 */
	
	public List<ItemAdmin> getItemAdminList(ItemAdminVo itemAdminVo);
	
	public ItemAdmin getItemAdminById(@Param("id") Long id);
	
	public int updateItem(ItemAdmin itemAdmin);
	
	public int deleteItem(@Param("id") Long id);
	
	public int updateItemSales(ItemAdmin itemAdmin);
	
	public int updateItemPrice(ItemAdmin itemAdmin);
	
	public int createItem(ItemAdmin itemAdmin);
	
	
}
