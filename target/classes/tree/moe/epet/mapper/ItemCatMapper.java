package tree.moe.epet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tree.moe.epet.entity.Item_cat;

@Mapper
public interface ItemCatMapper {
	@Select("SELECT * FROM item_cat")
	List<Item_cat> getAllCat();
	
	@Select("SELECT * FROM item_cat where parent_id =#{id}")
	List<Item_cat> getItemCatById(long id);
}
