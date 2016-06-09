package bulletin_board.service;

import static bulletin_board.utils.CloseableUtil.*;
import static bulletin_board.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletin_board.beans.Department;
import bulletin_board.dao.DepartmentDao;

public class DepartmentService {

	private static final int limit_num = 1000;

	public List<Department> getDepartment() {

		Connection connection = null;
		try {
			connection = getConnection();

			DepartmentDao messageDao = new DepartmentDao();
			List<Department> ret = messageDao.getDepartments(connection, limit_num);

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
