package bulletin_board.dao;

import static bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletin_board.beans.UserMessage;
import bulletin_board.exception.SQLRuntimeException;

public class CategoryDao {

	public List<UserMessage> getcategories(Connection connection, int limitNum) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT category FROM bulletin_board.contributions ");
			sql.append("ORDER BY insert_date limit " + limitNum);

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
				String category = rs.getString("category");

				UserMessage message = new UserMessage();
				message.setCategory(category);

				ret.add(message);
			}
			return ret;
		} finally {
			close (rs);
		}
	}

}
