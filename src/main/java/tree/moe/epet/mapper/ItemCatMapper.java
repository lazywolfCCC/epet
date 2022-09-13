package tree.moe.epet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.Item_cat;

@Mapper
public interface ItemCatMapper {
	@Select("SELECT * FROM item_cat")
	List<Item_cat> getAllCat();
	
	@Select("SELECT * FROM item_cat where parent_id =#{id}")
	List<Item_cat> getItemCatById(long id);
	
	@Select("Select * from item_cat limit #{left},#{right}")
	List<Item_cat> getAllItemCat(int left,int right);
	
	@Delete("Delete from item_cat where id=#{id}")
	void deleteItemCatById(long id);
	
	@Select("Select * from item_cat where id=#{id}")
	Item_cat getCatById(long id);
	
	@Update("update item_cat set name=#{name},flag=#{flag}"
			+ ",img=#{img},parent_id=#{parent_id},"
			+ "priority=#{priority} where id =#{id}")
	void updateItemCatById(Item_cat itemcat);
}
