
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

import bulletin_board.beans.Branch;
import bulletin_board.beans.Department;
import bulletin_board.beans.User;
import bulletin_board.service.BranchService;
import bulletin_board.service.DepartmentService;
import bulletin_board.service.UserService;

@WebServlet (urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//ここにリスト追加

		User loginuser = (User) request.getSession().getAttribute("loginUser");
		request.setAttribute("loginUser", loginuser);

		List<Branch> branches = new BranchService().getBranch();
		request.setAttribute("branches", branches);

		List<Department> departments = new DepartmentService().getDepartment();
		request.setAttribute("departments", departments);

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();


		User user = new User();
		user.setLogin_id(request.getParameter("login_id"));
		user.setPassword(request.getParameter("password"));
		user.setPassword_check(request.getParameter("password_check"));
		user.setName(request.getParameter("name"));
		user.setBranch_id(request.getParameter("branch_id"));
		user.setDepartment_id(request.getParameter("department_id"));

		if (isValid(request, messages) == true ) {

			new UserService().register(user);

			response.sendRedirect("./userManagement");
		} else {

			session.setAttribute("errorMessages", messages);
//
//
			request.setAttribute("errorUser", user);
//			response.sendRedirect("signup");  //これなら戻るしちゃんと候補も出るけど、保持できない

			List<Branch> branches = new BranchService().getBranch();
			request.setAttribute("branches", branches);

			List<Department> departments = new DepartmentService().getDepartment();
			request.setAttribute("departments", departments);


			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}

	private boolean isValid (HttpServletRequest request, List<String> messages) {
		String login_id =request.getParameter("login_id");
		String password = request.getParameter("password");
		String password_check = request.getParameter("password_check");
		String name = request.getParameter("name");
		String branch_id = request.getParameter("branch_id");
		String department_id = request.getParameter("department_id");

		if (StringUtils.isEmpty(login_id) == true ) {
			messages.add("ユーザーIDを入力してください");
		} else if (StringUtils.length(login_id) < 6) {
			messages.add("ユーザーIDは6文字以上です");
		} else if (StringUtils.length(login_id) > 20 ) {
			messages.add("ユーザーIDは20文字以下です");
		}

		if (StringUtils.isEmpty(password) == true ) {
			messages.add("パスワードを入力してください");
		} else if (StringUtils.length(password) < 6) {
			messages.add("パスワードは6文字以上です");
		} else if (StringUtils.length(password) > 255) {
			messages.add("パスワードは255文字以下です");
		}

		if (StringUtils.isEmpty(password_check) == true ) {
			messages.add("パスワードを入力してください");
		} else if (password.equals(password_check) == false) {
			messages.add("パスワードが違います");
		}

		if (StringUtils.isEmpty(name) == true ) {
			messages.add("名前を入力してください");
		} else if (StringUtils.length(name) > 10) {
			messages.add("名前は10文字以下です");
		}

		if (branch_id.equals("0") ) {
			messages.add("支店を選択してください");
		}

		if (department_id.equals("0") ) {
			messages.add("部署・役職を選択してください");
		}

		if (messages.size() == 0 ) {
			return true;
		} else {
			return false;
		}
	}

}
