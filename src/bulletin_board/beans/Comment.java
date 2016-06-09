package bulletin_board.beans;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
	private static final long sericalVersionUID = 1L;

	private int id;
	private String body;
	private Date insertDate;
	private Date updateDate;
	private int user_id;
	private int contribution_id;

	public static long getSericalversionuid() {
		return sericalVersionUID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getContribution_id() {
		return contribution_id;
	}
	public void setContribution_id(int contribution_id) {
		this.contribution_id = contribution_id;
	}

}