package bulletin_board.beans;

import java.io.Serializable;
import java.util.Date;

public class SearchMessage implements Serializable {

	private static final long serialVersionUID = 1L;


	private int contributionId;
	private String title;
	private String body;
	private String category;
	private Date insertDate;
	private String loginId;
	private String userName;
	private int userId;
	private int branchId;
	private int departmentId;
	private int suspention;

	public int getContributionId() {
		return contributionId;
	}
	public void setContributionId(int contributionId) {
		this.contributionId = contributionId;
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
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getSuspention() {
		return suspention;
	}
	public void setSuspention(int suspention) {
		this.suspention = suspention;
	}


}
