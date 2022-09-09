package tree.moe.epet.entity;

import java.util.Date;

public class OrderList {
	private long id;
	private int order_status;
	private int product_count;
	private double product_amount_total;
	private double order_amount_total;
	private double logistics_fee;
	private long address_id;
	private Date create_time;
	private Date pay_time;
	private Date delivery_time;
	private long user_id;
	private String remarks;
	private Date order_settlement_time;
	private String reason;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	public int getProduct_count() {
		return product_count;
	}
	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}
	public double getProduct_amount_total() {
		return product_amount_total;
	}
	public void setProduct_amount_total(double product_amount_total) {
		this.product_amount_total = product_amount_total;
	}
	public double getOrder_amount_total() {
		return order_amount_total;
	}
	public void setOrder_amount_total(double order_amount_total) {
		this.order_amount_total = order_amount_total;
	}
	public double getLogistics_fee() {
		return logistics_fee;
	}
	public void setLogistics_fee(double logistics_fee) {
		this.logistics_fee = logistics_fee;
	}
	public long getAddress_id() {
		return address_id;
	}
	public void setAddress_id(long address_id) {
		this.address_id = address_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getPay_time() {
		return pay_time;
	}
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	public Date getDelivery_time() {
		return delivery_time;
	}
	public void setDelivery_time(Date delivery_time) {
		this.delivery_time = delivery_time;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getOrder_settlement_time() {
		return order_settlement_time;
	}
	public void setOrder_settlement_time(Date order_settlement_time) {
		this.order_settlement_time = order_settlement_time;
	}
	
}
