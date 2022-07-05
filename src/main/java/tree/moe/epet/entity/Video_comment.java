package tree.moe.epet.entity;

import java.util.Date;

public class Video_comment {
	private long id;
	private long video_id;
	private long user_id;
	private String content;
	private long is_parent;
	private long parent_id;
	private Date create_time;
	private long reply_to;
	private int is_unread;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getVideo_id() {
		return video_id;
	}
	public void setVideo_id(long video_id) {
		this.video_id = video_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getIs_parent() {
		return is_parent;
	}
	public void setIs_parent(long is_parent) {
		this.is_parent = is_parent;
	}
	public long getParent_id() {
		return parent_id;
	}
	public void setParent_id(long parent_id) {
		this.parent_id = parent_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public long getReply_to() {
		return reply_to;
	}
	public void setReply_to(long reply_to) {
		this.reply_to = reply_to;
	}
	public int getIs_unread() {
		return is_unread;
	}
	public void setIs_unread(int is_unread) {
		this.is_unread = is_unread;
	}
	
}
