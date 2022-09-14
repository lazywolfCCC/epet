package tree.moe.epet.entity;

import java.util.List;

public class OrderVO {
	private OrderList orderlist;
	private List<Order_item> orderItemList;
	public OrderList getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(OrderList orderlist) {
		this.orderlist = orderlist;
	}
	public List<Order_item> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<Order_item> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
}
