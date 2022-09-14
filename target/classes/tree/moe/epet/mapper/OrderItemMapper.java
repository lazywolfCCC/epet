package tree.moe.epet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import tree.moe.epet.entity.Order_item;

@Mapper
public interface OrderItemMapper {
	
	@Select("Select * from order_item where orderlist_id=#{orderlist_id}")
	List<Order_item> getOrderitemByOrderlistId(long orderlist_id);
	
	@Insert("insert into order_item (orderlist_id,item_id"
			+ ",sku_id,product_name,product_price"
			+ ",sku_name,discount_rate,discount_amount,number,subtotal,remark)"
			+ "values(#{orderlist_id},#{item_id},#{sku_id},#{product_name},#{product_price},"
			+ "#{sku_name},#{discount_rate},#{discount_amount},#{number},#{subtotal},#{remark}) ")
	@Options(useGeneratedKeys=true , keyProperty="id" , keyColumn="id")
	int createNewOrderItem(Order_item order_item);
}
