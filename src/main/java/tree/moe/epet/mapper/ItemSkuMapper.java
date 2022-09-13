package tree.moe.epet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.Item_sku;

@Mapper
public interface ItemSkuMapper {
	
	List<Item_sku> getItemSkuByItemid(long id);
	@Select("Select * from item_sku where id=#{id}")
	Item_sku getItemSkuById(Item_sku sku);
	
	Item_sku getItemsku(long id);
	
}
