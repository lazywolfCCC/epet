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
import tree.moe.epet.rabbitmq.CancelOrderSender;

@Service
public class OrderListService {
	public static final Long DELAY_ORDER_CANCEL_TIME = 60000L;
	
	@Autowired
    private CancelOrderSender cancelOrderSender;
	
	
	@Autowired
	OrderListMapper orderlistMapper;
	
	@Autowired
	OrderItemMapper orderItemMapper;
	
	public List<OrderList> getOrderlistByUserid(long user_id,int left , int right)
	{
		return orderlistMapper.getOrderlistByUserid(user_id, left, right);
	}
	
	public List<OrderList> getOrderlistByOrderStatus(OrderlistVO orderlistvo)
	{
		return orderlistMapper.getOrderListByOrderStatus(orderlistvo);
	}
	
	public List<OrderList> getAllOrderlists(int left,int right,String orderkey,String sequnence)
	{
		return orderlistMapper.getAllOrderlists(left, right, orderkey, sequnence);
	}
	
	public void deleteOrderListById(long id)
	{
		orderlistMapper.deleteOrderListById(id);
	}
	
	public List<Order_item> getEchartsData()
	{
		return orderItemMapper.getEchartsData();
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
		if(orderlist.getId() > 0) {
			sendDelayMessageCancelOrder(orderlist.getId());
		}
	}
	
	public int getPageCount(OrderlistVO orderlistvo)
	{
		return orderlistMapper.getPageCount(orderlistvo);
	}
	
	public List<Order_item> getOrderItemsByOrderlistid(long orderlistid)
	{
		return orderItemMapper.getOrderitemByOrderlistId(orderlistid);
	}
	
	public void updateTimeoutOrder(long id,String reason,int order_status)
	{
		orderlistMapper.updateTimeOutOrder(id, reason, order_status);
	}
	
	public Integer getPageCount()
	{
		return orderlistMapper.getPageCount();
	}
	
	public int createNewOrderItem(Order_item order_item)
	{
		return orderItemMapper.createNewOrderItem(order_item);
	}
	private void sendDelayMessageCancelOrder(Long orderId) {
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId, DELAY_ORDER_CANCEL_TIME);
    }
}
