package tree.moe.epet.entity;

import java.util.Date;

public class Videohistory {
	private int id;
	private int user_id;
	private int video_id;
	private Date viewtime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getVideo_id() {
		return video_id;
	}
	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}
	public Date getViewtime() {
		return viewtime;
	}
	public void setViewtime(Date viewtime) {
		this.viewtime = viewtime;
	}
	
}
