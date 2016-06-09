package bulletin_board.beans;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	private static final long sericalVersionUID = 1L;

	private int id;
	private String title;
	private String body;
	private String category;
	private int user_id;
	private Date insertDate;
	private Date updateDate;


	public static long getSericalversionuid() {
		return sericalVersionUID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int i) {
		this.user_id = i;
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

}
