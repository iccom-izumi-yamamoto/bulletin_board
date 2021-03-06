package bulletin_board.dao;

import static bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bulletin_board.beans.SearchMessage;
import bulletin_board.exception.SQLRuntimeException;

public class UserSearchDao {

	public List<SearchMessage> getUserMessages(Connection connection, int limitNum, String searchCategory,
			String searchTimeBefore, String searchTimeAfter) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_contribution ");
			sql.append("WHERE insert_date BETWEEN ");
			sql.append("? AND" + " ? " );

			if (!StringUtils.isEmpty(searchCategory)) {
				sql.append( " AND " );
				sql.append("category = " + "?" );


			}
			sql.append("ORDER BY insert_date DESC " );

			ps = connection.prepareStatement(sql.toString());


			ps.setString(1, searchTimeBefore + " 00:00:00");


			ps.setString(2, searchTimeAfter + " 23:59:59");



			if (StringUtils.isNotEmpty(searchCategory)) {

				ps.setString(3, searchCategory);

			}


			ResultSet rs = ps.executeQuery();
			List<SearchMessage> ret = toUserMessageList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close (ps);
		}
	}

	private List<SearchMessage> toUserMessageList(ResultSet rs) throws SQLException {
		List<SearchMessage> ret = new ArrayList<SearchMessage>();
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
				int branchId = rs.getInt("branch_id");
				int departmentId = rs.getInt("department_id");
				int suspention = rs.getInt("suspention");

				SearchMessage message = new SearchMessage();

				message.setContributionId(contributionId);
				message.setTitle(title);
				message.setBody(body);
				message.setCategory(category);
				message.setInsertDate(insertDate);
				message.setLoginId(loginId);
				message.setUserName(userName);
				message.setUserId(userId);
				message.setBranchId(branchId);
				message.setDepartmentId(departmentId);
				message.setSuspention(suspention);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
