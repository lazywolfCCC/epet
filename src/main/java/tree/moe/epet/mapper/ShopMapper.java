package tree.moe.epet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tree.moe.epet.entity.Shop;

@Mapper
public interface ShopMapper {
	
	@Select("Select * from shop where id=#{id}")
	Shop getShopByid(Shop shop);
	
	@Select("Select * from shop")
	List<Shop> getShops();
	
	@Select("Select * from shop where id=#{id}")
	Shop getShop(long id);
	
	/*wujie Added below */
	/*wujie Added below */
	/*wujie Added below */
	@Select(" Select count(id) from shop")
	int getShopsCount();
	
	@Select(" Select * from shop where name LIKE '%${name}%' ")
	List<Shop> getShopsByname(Shop shop);
	
	@Select(" Select * from shop limit #{page},#{right} ")
	List<Shop> getShopsByPaging(int right,int page);
	
	@Delete("Delete from shop where id=#{id}")
	int deleteShopByid(Shop shop);
	
	@Update("Update shop Set name=#{name},tel=#{tel},province=#{province},city=#{city},county=#{county},address_detail=#{address_detail},qq=#{qq},description=#{description} where id=#{id}")
	int UpdateShop(Shop shop);
	
	@Insert({"Insert into shop(id,name,tel,province,city,county,address_detail,qq,description) values(#{id},#{name},#{tel},#{province},#{city},#{county},#{address_detail},#{qq},#{description})"})
	int InsertShop(Shop shop);
}
