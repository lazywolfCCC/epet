package tree.moe.epet.entity;

public class Video {
	private long id;
	private long user_id;
	private String url;
	private int like_num;
	private int collect_num;
	private long item_id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getLike_num() {
		return like_num;
	}
	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}
	public long getCollect_num() {
		return collect_num;
	}
	public void setCollect_num(int collect_num) {
		this.collect_num = collect_num;
	}
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}
	
}
