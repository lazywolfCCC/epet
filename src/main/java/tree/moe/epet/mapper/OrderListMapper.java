package tree.moe.epet.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;

import tree.moe.epet.entity.OrderList;
import tree.moe.epet.entity.OrderlistVO;

@Mapper
public interface OrderListMapper {
	
	@Select("SELECT * FROM orderlist where user_id=#{user_id} order by `id` desc limit #{left},#{right}" )
	List<OrderList> getOrderlistByUserid(long user_id,int left , int right);
	
	List<OrderList> getAllOrderlists(int left,int right,String orderkey,String sequnence);
	
	@Delete("DELETE FROM orderlist where id=#{id}")
	void deleteOrderListById(long id);
	
	@Select("SELECT * FROM orderlist where id=#{id}")
	OrderList getOrderlistByid(long id);
	
	//@Update("Update orderlist set order_status=#{order_status} where  id=#{id}")
	void updateOrderListById(OrderlistVO orderlistVO);
	
	@Insert("Insert into orderlist (order_status,product_count"
			+ ",product_amount_total,order_amount_total,logistics_fee,"
			+ "address_id,create_time,"
			+ "user_id,remarks) "
			+ "values (#{order_status},#{product_count},#{product_amount_total}"
			+ ",#{order_amount_total},#{logistics_fee}"
			+ ",#{address_id},#{create_time}"
			+ ",#{user_id},#{remarks})")
	@Options(useGeneratedKeys=true , keyProperty="id" , keyColumn="id")
	void insertNewOrderList(OrderList orderlist);
	
	@Update("update set order_status=#{order_status},reason=#{reason} where id=#{id}")
	void updateTimeOutOrder(long id,String reason,int order_status);

//	@Select("select count(id) from orderlist")
	Integer getPageCount();
	
	Integer getPageCount(OrderlistVO orderlistvo);
	
	@Select("Select * from orderlist where order_status = #{order_status} limit #{page},#{limit}")
	List<OrderList> getOrderListByOrderStatus(OrderlistVO orderlistvo);
}
