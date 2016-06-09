package bulletin_board.service;

import static bulletin_board.utils.CloseableUtil.*;
import static bulletin_board.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletin_board.beans.Branch;
import bulletin_board.dao.BranchDao;

public class BranchService {

	private static final int limit_num = 1000;

	public List<Branch> getBranch() {

		Connection connection = null;
		try {
			connection = getConnection();

			BranchDao messageDao = new BranchDao();
			List<Branch> ret = messageDao.getBranches(connection, limit_num);

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
