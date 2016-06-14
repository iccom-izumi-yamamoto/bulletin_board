package bulletin_board.dao;

import static bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bulletin_board.beans.User;
import bulletin_board.beans.UserView;
import bulletin_board.exception.NoRowsUpdatedRuntimeException;
import bulletin_board.exception.SQLRuntimeException;

public class UserDao {

	public User getLoginUser(Connection connection, String login_id,
			String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM bulletin_board.users WHERE login_id = ? AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, login_id);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public boolean checkLoginId(Connection connection, String login_id, int id){

		PreparedStatement ps = null;
		try{
			String sql = "SELECT * FROM users WHERE login_id = ?;";

			ps = connection.prepareStatement(sql);

			ps.setString(1, login_id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if(userList.size() > 0){
				return false;
			}

			return true;
		}catch (SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String branch_id = rs.getString("branch_id");
				String department_id = rs.getString("department_id");
				int suspention = rs.getInt("suspention");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				Timestamp updateDate = rs.getTimestamp("update_date");

				User user = new User();
				user.setId(id);
				user.setLogin_id(login_id);
				user.setName(name);
				user.setPassword(password);
				user.setBranch_id(branch_id);
				user.setDepartment_id(department_id);
				user.setSuspention(suspention);
				user.setInsertDate(insertDate);
				user.setUpdateDate(updateDate);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert (Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append("login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(", suspention");
			sql.append(", insert_date");
			sql.append(", update_date");
			sql.append(")VALUES(");
			sql.append("?"); //login_id
			sql.append(", ?"); //password
			sql.append(", ?"); //name
			sql.append(", ?"); //branch_id
			sql.append(", ?"); //department_id
			sql.append(", 0"); //suspention
			sql.append(", CURRENT_TIMESTAMP"); //insert_date
			sql.append(", CURRENT_TIMESTAMP"); //update_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLogin_id());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getBranch_id());
			ps.setString(5, user.getDepartment_id());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException (e);
		} finally {
			close (ps);
		}
	}

	public List<User> getUser(Connection connection){

		PreparedStatement ps = null;
		try{
			String sql = "SELECT * FROM users ;";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if(userList.isEmpty() == true){
				return null;
			}else{
				return userList;
			}
		}catch (SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	private List<UserView> toUserViewList(ResultSet rs) throws SQLException {

		List<UserView> ret = new ArrayList<>();
		try{
			while (rs.next()){
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				String branch = rs.getString("branch");
				String department = rs.getString("department");
				int suspention = rs.getInt("suspention");
				Timestamp userInsertDate = rs.getTimestamp("insert_date");
				Timestamp userUpdateDate = rs.getTimestamp("update_date");

				UserView user = new UserView();
				user.setId(id);
				user.setLogin_id(loginId);
				user.setName(name);
				user.setBranch(branch);
				user.setDepartment(department);
				user.setSuspention(suspention);
				user.setInsert_date(userInsertDate);
				user.setUpdate_date(userUpdateDate);

				ret.add(user);
			}
			return ret;
		}finally{
			close(rs);
		}
	}

	public List<UserView> getUserView(Connection connection){

		PreparedStatement ps = null;
		try{
			String sql = "SELECT * FROM user_view ORDER BY branch_id, department_id ASC ";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<UserView> userList = toUserViewList(rs);
			if(userList.isEmpty() == true){
				return null;
			}else{
				return userList;
			}
		}catch (SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	public User getEditedUser(Connection connection, int id) {

		PreparedStatement ps = null;
		try {

			String sql = "SELECT * FROM bulletin_board.users WHERE id=? ";


			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public void updateSuspention(Connection connection, User user, int id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder ();
			sql.append("UPDATE users SET");
			sql.append(" suspention = ?");
			sql.append(", update_date = CURRENT_TIMESTAMP ");

			sql.append("WHERE");
			sql.append(" id = ?");


			ps = connection.prepareStatement(sql.toString());

			if (user.getSuspention() == 0) {
				ps.setInt(1, 1);
			} else {
				ps.setInt(1, 0);
			}
			ps.setInt(2, user.getId());

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}


	}

	public void update(Connection connection, User user) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" login_id = ?");

			if (! user.getPassword().isEmpty()) {
				sql.append(", password = ?");
			}

			sql.append(", name = ?");
			sql.append(", branch_id = ?");
			sql.append(", department_id = ?");
			sql.append(", update_date = CURRENT_TIMESTAMP");

			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLogin_id());

			if (! user.getPassword().isEmpty()) {
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getName());
				ps.setString(4, user.getBranch_id());
				ps.setString(5, user.getDepartment_id());
				ps.setInt(6, user.getId());
			} else {
				ps.setString(2, user.getName());
				ps.setString(3, user.getBranch_id());
				ps.setString(4, user.getDepartment_id());
				ps.setInt(5, user.getId());
			}
			System.out.println(ps.toString());

			int count = ps.executeUpdate();
			if (count == 0) {

				throw new NoRowsUpdatedRuntimeException();
			}

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
				close(ps);
		}
	}

	public List<User> getUserManagementList(Connection connection, int limitNum) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public void deleteUser(Connection connection, int id) {
		PreparedStatement ps = null;

		try{
			StringBuilder sql = new StringBuilder();
			sql.append("delete from users where id = ?");

			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, id);

			if((ps.executeUpdate()) == 0){
				throw new NoRowsUpdatedRuntimeException();
			}
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}

	}

}
