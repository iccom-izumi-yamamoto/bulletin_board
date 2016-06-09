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

import bulletin_board.beans.Message;
import bulletin_board.beans.User;
import bulletin_board.service.MessageService;

@WebServlet (urlPatterns = {"/newMessage"})
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		request.getRequestDispatcher("newMessage.jsp").forward(request, response);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();


		if (isValid (request, messages) == true ) {

			User user = (User) session.getAttribute("loginUser");

			Message message = new Message();
			message.setTitle(request.getParameter("title"));
			message.setBody(request.getParameter("body"));
			message.setCategory (request.getParameter("category"));
			message.setUser_id(user.getId());

			new MessageService().register(message);

			response.sendRedirect("./top");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("newMessage");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String>messages) {

		String title = request.getParameter("title");
		String body = request.getParameter("body");
		String category = request.getParameter("category");


		if (StringUtils.isEmpty(title) == true ) {
			messages.add("件名を入力してください");
		}
		if (50 < title.length()) {
			messages.add("件名を50文字以下で入力してください");
		}

		if (StringUtils.isEmpty(body) == true ) {
			messages.add("本文を入力してください");
		}
		if(1000 < body.length() ) {
			messages.add("本文を1000文字以下で入力してください");
		}
		if(StringUtils.isEmpty(category) == true ) {
			messages.add("カテゴリーを入力してください");
		}
		if (10 < category.length()) {
			messages.add("カテゴリーを10文字以下で入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
