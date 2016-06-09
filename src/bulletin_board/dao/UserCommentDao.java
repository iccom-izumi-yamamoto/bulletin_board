package bulletin_board.dao;

import static bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bulletin_board.beans.UserComment;
import bulletin_board.exception.SQLRuntimeException;

public class UserCommentDao {

	public List<UserComment> getUserComments (Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_comment ");
			sql.append("ORDER BY insert_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserComment> ret = toUserCommentList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close (ps);
		}
	}

	private List<UserComment> toUserCommentList(ResultSet rs) throws SQLException {

		List<UserComment> ret = new ArrayList<UserComment>();
		try {
			while (rs.next()) {
				int commentId = rs.getInt("comment_id");
				String body = rs.getString("body");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				int contributionId = rs.getInt("contribution_id");
				String userName = rs.getString("user_name");
				int userId = rs.getInt("user_id");
				int userBranch = rs.getInt("user_branch");
				int userDepartment = rs.getInt("user_department");

				UserComment comment = new UserComment();
				comment.setCommentId (commentId);
				comment.setBody (body);
				comment.setInsertDate(insertDate);
				comment.setContributionId (contributionId);
				comment.setUserName (userName);
				comment.setUserId(userId);
				comment.setUserBranch(userBranch);
				comment.setUserDepartment(userDepartment);

				ret.add(comment);

			}
			return ret;
		} finally {
			close (rs);
		}
	}
}


