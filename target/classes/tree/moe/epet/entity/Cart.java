package tree.moe.epet.entity;

public class Cart {
	private long id;
	private long user_id;
	private long sku_id;
	private long shop_id;
	private double ori_price;
	private int num;
	public long getId() {
		return id;
	}
	public void setCart(long id) {
		this.id = id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getSku_id() {
		return sku_id;
	}
	public void setSku_id(long sku_id) {
		this.sku_id = sku_id;
	}
	public long getShop_id() {
		return shop_id;
	}
	public void setShop_id(long shop_id) {
		this.shop_id = shop_id;
	}
	public double getOri_price() {
		return ori_price;
	}
	public void setOri_price(double ori_price) {
		this.ori_price = ori_price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
