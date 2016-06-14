package bulletin_board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletin_board.beans.User;
import bulletin_board.service.LoginService;
import bulletin_board.service.UserService;

@WebServlet(urlPatterns = {"/deleteUser"})
public class DeleteUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{

		int id = Integer.parseInt(request.getParameter("id"));
		new UserService().deleteUser(id);

		LoginService loginService = new LoginService();
		List<User> userManagementList =  loginService.UserManagementList();

		request.setAttribute("userManagementList", userManagementList);
		response.sendRedirect("./userManagement");
	}
}
