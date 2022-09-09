package tree.moe.epet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import tree.moe.epet.entity.Order_item;

@Mapper
public interface OrderItemMapper {
	
	@Select("Select * from order_item where orderlist_id=#{orderlist_id}")
	List<Order_item> getOrderitemByOrderlistId(long orderlist_id);
}
