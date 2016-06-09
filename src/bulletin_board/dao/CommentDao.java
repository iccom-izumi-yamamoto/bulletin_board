package bulletin_board.dao;

import static bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bulletin_board.beans.Comment;
import bulletin_board.exception.SQLRuntimeException;


public class CommentDao {

	public void insert (Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append("body");
			sql.append(", insert_date");
			sql.append(", update_date");
			sql.append(", user_id");
			sql.append(", contribution_id");
			sql.append(")VALUES(");
			sql.append("?"); //body
			sql.append(", CURRENT_TIMESTAMP"); //insert_date
			sql.append(", CURRENT_TIMESTAMP"); // update_date
			sql.append(", ?"); //user_id
			sql.append(", ?");//contribution_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, comment.getBody());
			ps.setInt(2, comment.getUser_id());
			ps.setInt(3, comment.getContribution_id());

//			System.out.println(ps.toString());


			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void deleteContributionComment(Connection connection, int id) {
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM comments WHERE contribution_id = ? ;");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, id);
			System.out.println(sql.toString());

			ps.executeUpdate();
		}catch (SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}

	}

	public void deleteComment(Connection connection, int id) {
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM comments WHERE id = ? ;");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, id);

			 ps.executeUpdate();

		}catch (SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

}
