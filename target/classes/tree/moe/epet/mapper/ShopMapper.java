package tree.moe.epet.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tree.moe.epet.entity.Shop;

@Mapper
public interface ShopMapper {
	
	@Select("Select * from shop where id=#{id}")
	Shop getShopByid(Shop shop);
}
