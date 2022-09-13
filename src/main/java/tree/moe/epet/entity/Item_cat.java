package tree.moe.epet.entity;

public class Item_cat {//商品分类
	private long id;
	private String name;
	private int flag;
	private long parent_id;
	private String img;
	private int priority;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public long getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return "Item_cat [id=" + id + ", name=" + name + ", flag=" + flag + ", parent_id=" + parent_id + ", img=" + img
				+ ", priority=" + priority + "]";
	}
	
}
