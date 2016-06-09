package bulletin_board.beans;

import java.io.Serializable;
import java.util.Date;

public class UserComment implements Serializable {
	private static final long sericalVersionUID = 1L;

	private int commentId;
	private String body;
	private Date insertDate;
	private Date updateDate;
	private int contributionId;
	private String userName;
	private int userId;
	private int branch_id;
	private int userDepartment;

	public static long getSericalversionuid() {
		return sericalVersionUID;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	public int getContributionId() {
		return contributionId;
	}
	public void setContributionId(int contributionId) {
		this.contributionId = contributionId;
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
	public int getUserBranch() {
		return branch_id;
	}
	public void setUserBranch(int userBranch) {
		this.branch_id = userBranch;
	}
	public int getUserDepartment() {
		return userDepartment;
	}
	public void setUserDepartment(int userDepartment) {
		this.userDepartment = userDepartment;
	}


}
