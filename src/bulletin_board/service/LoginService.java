package bulletin_board.service;

import static bulletin_board.utils.CloseableUtil.*;
import static bulletin_board.utils.DBUtil.*;

import java.sql.Connection;

import bulletin_board.beans.User;
import bulletin_board.dao.UserDao;
import bulletin_board.utils.CipherUtil;

public class LoginService {

	public User login(String login_id, String password) {
		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);
			User user = userDao.getLoginUser(connection, login_id, encPassword);

			commit(connection);
			return user;
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
