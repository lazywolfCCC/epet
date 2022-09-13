package tree.moe.epet.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tree.moe.epet.entity.OrderList;
import tree.moe.epet.entity.Order_item;
import tree.moe.epet.entity.OrderlistVO;
import tree.moe.epet.mapper.OrderItemMapper;
import tree.moe.epet.mapper.OrderListMapper;

@Service
public class OrderListService {
	@Autowired
	OrderListMapper orderlistMapper;
	
	@Autowired
	OrderItemMapper orderItemMapper;
	
	public List<OrderList> getOrderlistByUserid(long user_id,int left , int right)
	{
		return orderlistMapper.getOrderlistByUserid(user_id, left, right);
	}
	
	public List<OrderList> getAllOrderlists(int left,int right,String orderkey,String sequnence)
	{
		return orderlistMapper.getAllOrderlists(left, right, orderkey, sequnence);
	}
	
	public void deleteOrderListById(long id)
	{
		orderlistMapper.deleteOrderListById(id);
	}
	
	public OrderList getOrderlistByid(long id)
	{
		return orderlistMapper.getOrderlistByid(id);
	}
	
	public void updateOrderListStatusById(OrderlistVO orderlistVO)
	{
		orderlistMapper.updateOrderListById(orderlistVO);
	}
	public void insertNewOrderList(OrderList orderlist)
	{
		orderlistMapper.insertNewOrderList(orderlist);
	}
	
	public List<Order_item> getOrderItemsByOrderlistid(long orderlistid)
	{
		return orderItemMapper.getOrderitemByOrderlistId(orderlistid);
	}
	
	public void updateTimeoutOrder(long id,String reason,int order_status)
	{
		orderlistMapper.updateTimeOutOrder(id, reason, order_status);
	}
}
