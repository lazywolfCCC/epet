package tree.moe.epet.entity;

import java.util.Date;

public class Videohistory {
	private long id;
	private long user_id;
	private long video_id;
	private Date viewtime;
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
	public long getVideo_id() {
		return video_id;
	}
	public void setVideo_id(long video_id) {
		this.video_id = video_id;
	}
	public Date getViewtime() {
		return viewtime;
	}
	public void setViewtime(Date viewtime) {
		this.viewtime = viewtime;
	}
	
}
