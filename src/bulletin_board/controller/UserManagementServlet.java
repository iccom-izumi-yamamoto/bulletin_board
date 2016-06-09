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
import bulletin_board.beans.UserView;
import bulletin_board.service.UserService;

@WebServlet (urlPatterns= {"/userManagement"})
public class UserManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<UserView> users = new UserService().getUserView();

		request.setAttribute("users", users);
		request.getRequestDispatcher("/userManagement.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		int id = Integer.valueOf(request.getParameter("id"));
		UserService getUser = new UserService();
		User editUser = getUser.getEditedUser(id);


		new UserService().updateSuspention(editUser,id);

		List<String> messages = new ArrayList<>();
		String submit = request.getParameter("submit");
		String name = editUser.getName();

		if(submit.equals("停止")){
			messages.add(name + "さんを稼動停止しました");
		}else{
			messages.add(name + "さんの利用を再開しました");
		}

		HttpSession session = request.getSession();
		session.setAttribute("errorMessages", messages);
		response.sendRedirect("userManagement");
	}
}



