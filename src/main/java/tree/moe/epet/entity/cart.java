package tree.moe.epet.entity;

public class cart {
	private int cart;
	private int user_id;
	private int sku_id;
	private int shop_id;
	private double ori_price;
	private int num;
	public int getCart() {
		return cart;
	}
	public void setCart(int cart) {
		this.cart = cart;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getSku_id() {
		return sku_id;
	}
	public void setSku_id(int sku_id) {
		this.sku_id = sku_id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
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
