package bulletin_board.service;

import static bulletin_board.utils.CloseableUtil.*;
import static bulletin_board.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletin_board.beans.UserMessage;
import bulletin_board.dao.CategoryDao;

public class CategoryService {

	private static final int limit_num = 1000;

	public List<UserMessage> getSearchCategory() {

		Connection connection = null;
		try {
			connection = getConnection();

			CategoryDao categoryDao = new CategoryDao();
			List<UserMessage> ret = categoryDao.getcategories(connection, limit_num);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback (connection);
			throw e;
		} catch (Error e) {
			rollback (connection);
			throw e;
		} finally {
			close (connection);
		}
	}

}
