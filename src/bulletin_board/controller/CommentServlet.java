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

import org.apache.commons.lang.StringUtils;

import bulletin_board.beans.Comment;
import bulletin_board.beans.User;
import bulletin_board.service.CommentService;

@WebServlet (urlPatterns = {"/comment"})
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		request.getRequestDispatcher("top.jsp").forward(request, response);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		List<String> comments = new ArrayList<String>();

		if (isValid (request, comments) == true ) {

			User user = (User) session.getAttribute("loginUser");
//			System.out.println(request.getParameter("contributionId"));

			Comment comment = new Comment();
			comment.setBody(request.getParameter("body"));
			comment.setContribution_id(Integer.valueOf(request.getParameter("contributionId")));
			comment.setUser_id(user.getId());

			new CommentService().register(comment);

			response.sendRedirect("./top");
		} else {
//			System.out.println("ps.toString()");
			session.setAttribute("errorMessages", comments);
			response.sendRedirect("./top");
			}
		}





	private boolean isValid(HttpServletRequest request, List<String>comments) {

		String body = request.getParameter("body");

//		System.out.println(body);
		if (StringUtils.isEmpty(body) == true ) {
			comments.add("コメントを入力してください");
		}
		if(500 < body.length() ) {
			comments.add("コメントを500文字以下で入力してください");
		}
		if (comments.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}

