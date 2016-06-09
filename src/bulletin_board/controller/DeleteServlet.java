package bulletin_board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bulletin_board.beans.User;
import bulletin_board.service.CommentService;
import bulletin_board.service.MessageService;

@WebServlet(urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet{
	private static final long serialVersionUD = 1L;

	public static long getSerialversionud() {
		return serialVersionUD;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.sendRedirect("./top");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<>();
		User user = (User) request.getSession().getAttribute("loginUser");

		String submit = request.getParameter("submit");
		int contributionId = Integer.valueOf(request.getParameter("contribution_id"));

		if(submit.equals("投稿を削除")){

			MessageService deleteContribution = new MessageService();
			deleteContribution.deleteContribution(contributionId);
			messages.add("投稿を削除しました");

		}else if(submit.equals("コメントを削除")){

			int commentId = Integer.valueOf(request.getParameter("comment_id"));
			CommentService deleteComment = new CommentService();
			deleteComment.deleteComment(commentId);
			messages.add("コメントを削除しました");

		}
		session.setAttribute("messages", messages);
		response.sendRedirect("./top");
	}

}

