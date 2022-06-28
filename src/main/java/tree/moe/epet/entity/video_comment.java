package tree.moe.epet.entity;

import java.util.Date;

public class video_comment {
	private int id;
	private int video_id;
	private int user_id;
	private String content;
	private int is_parent;
	private int parent_id;
	private Date create_time;
	private int reply_to;
	private int is_unread;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVideo_id() {
		return video_id;
	}
	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIs_parent() {
		return is_parent;
	}
	public void setIs_parent(int is_parent) {
		this.is_parent = is_parent;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public int getReply_to() {
		return reply_to;
	}
	public void setReply_to(int reply_to) {
		this.reply_to = reply_to;
	}
	public int getIs_unread() {
		return is_unread;
	}
	public void setIs_unread(int is_unread) {
		this.is_unread = is_unread;
	}
	
}
