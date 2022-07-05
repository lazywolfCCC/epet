package tree.moe.epet.entity;

public class Item_sku {
	private long id;
	private long item_id;
	private String name;
	private double price;
	private String img;
	private Item item;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	
}
