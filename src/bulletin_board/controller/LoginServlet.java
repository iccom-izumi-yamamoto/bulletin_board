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
import bulletin_board.service.LoginService;

@WebServlet (urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");



		LoginService loginService = new LoginService();
		User user = loginService.login(login_id, password);

		HttpSession session = request.getSession();
		if (user != null && user.getSuspention() == 0) {

			session.setAttribute("loginUser", user);
			response.sendRedirect("./top");
		} else {

			request.setAttribute("errorLogin", login_id);

			List<String> messages = new ArrayList <String>();
			messages.add("ログインに失敗しました");

			if (login_id.isEmpty()) {
				messages.add("ログインIDを入力してください");
			}

			if (password.isEmpty()) {
				messages.add("パスワードを入力してください");
			}
			if (user != null && user.getSuspention() == 1) {
				messages.add("停止されたアカウントです");
			}


			session.setAttribute("errorMessages", messages);

			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
