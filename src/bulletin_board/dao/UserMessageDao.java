package bulletin_board.dao;

import static bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bulletin_board.beans.UserMessage;
import bulletin_board.exception.SQLRuntimeException;

public class UserMessageDao {

	public List<UserMessage> getUserMessages (Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_contribution ");
			sql.append("ORDER BY insert_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close (ps);
		}
	}

	private List<UserMessage> toUserMessageList(ResultSet rs) throws SQLException {

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {
				int contributionId = rs.getInt("contribution_id");
				String title = rs.getString("title");
				String body = rs.getString("body");
				String category = rs.getString("category");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				String loginId = rs.getString("login_id");
				String userName = rs.getString("user_name");
				int userId = rs.getInt("user_id");
				int suspention = rs.getInt("suspention");
				int branch_id = rs.getInt("branch_id");

				UserMessage message = new UserMessage();
				message.setContributionId (contributionId);
				message.setTitle (title);
				message.setBody (body);
				message.setCategory (category);
				message.setInsertDate(insertDate);
				message.setUserId(userId);
				message.setUserName (userName);
				message.setLoginId(loginId);
				message.setSuspention(suspention);
				message.setBranch_id(branch_id);

				ret.add(message);
			}
			return ret;
		} finally {
			close (rs);
		}
	}

}


