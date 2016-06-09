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

@WebServlet (urlPatterns = {"/settings"})
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		HttpSession session = request.getSession();

		int id = 0;
		List<String> messages = new ArrayList<>();

		if(request.getParameter("id") == null && session.getAttribute("id") == null){
			messages.add("無効な入力です");
			session.setAttribute("messages", messages);
			response.sendRedirect("userManagement");

			System.out.println("idが無効入力");

			return;
		}else if(request.getParameter("id") != null){
			if(request.getParameter("id").matches("^[0-9]{1,10}$")){
				id = Integer.valueOf(request.getParameter("id"));

			}else{
				messages.add("無効なIDが入力されました");
				session.setAttribute("messages", messages);
				response.sendRedirect("userManagement");

				System.out.println("idとれてる");

				return;
			}
		}else{
			String sessionId = session.getAttribute("id").toString();
			id = new Integer(sessionId).intValue();
		}
		User editedUser = getEditedUser(request, id);

		if(editedUser == null){
			messages.add("IDは存在しません");
			session.setAttribute("messages", messages);
			response.sendRedirect("userManagement");

			System.out.println("idないよ");

			return;
		}

		List<Branch> branches = new BranchService().getBranch();
		request.setAttribute("branches", branches);

		List<Department> departments = new DepartmentService().getDepartment();
		request.setAttribute("departments", departments);

		session.setAttribute("editedUser", editedUser);

		request.getRequestDispatcher("/settings.jsp").forward(request, response);
		session.removeAttribute("editUser");
	}



	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<String> messages = new ArrayList<>();
		HttpSession session = request.getSession();

		User editUser = getEditUser(request);

		if(isValid(request, messages) == true){

			System.out.println("うまくいってる");

			new UserService().update(editUser);
			messages.add("ユーザー情報の編集が完了しました");
			session.setAttribute("messages", messages);
			response.sendRedirect("userManagement");
		}else{

			System.out.println("validでてる");

			request.setAttribute("editUser", editUser);
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("settings.jsp").forward(request, response);
//			response.sendRedirect("settings");
		}
	}


	private User getEditedUser(HttpServletRequest request, int id)
			throws IOException, ServletException{
		UserService getUser = new UserService();
		User editedUser = getUser.getEditedUser(id);

		return editedUser;
	}


	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException{
		User editUser = new User();

		editUser.setId(Integer.valueOf(request.getParameter("id")));
		editUser.setLogin_id(request.getParameter("login_id"));
		editUser.setName(request.getParameter("name"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setBranch_id(request.getParameter("branch_id"));
		editUser.setDepartment_id(request.getParameter("department_id"));

		return editUser;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages){

		String loginId = request.getParameter("login_id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("password_check");
		int branch = Integer.valueOf(request.getParameter("branch_id"));
		int department = Integer.valueOf(request.getParameter("department_id"));


		if (StringUtils.isEmpty(loginId) == true ) {
			messages.add("ユーザーIDを入力してください");
		} else if (StringUtils.length(loginId) < 6) {
			messages.add("ユーザーIDは6文字以上です");
		} else if (StringUtils.length(loginId) > 20 ) {
			messages.add("ユーザーIDは20文字以下です");
		}

		if (StringUtils.isEmpty(password) == true ) {
			messages.add("パスワードを入力してください");
		} else if (StringUtils.length(password) < 6) {
			messages.add("パスワードは6文字以上です");
		} else if (StringUtils.length(password) > 255) {
			messages.add("パスワードは255文字以下です");
		}

		if (StringUtils.isEmpty(passwordCheck) == true ) {
			messages.add("パスワードを入力してください");
		} else if (password.equals(passwordCheck) == false) {
			messages.add("パスワードが違います");
		}

		if (StringUtils.isEmpty(name) == true ) {
			messages.add("名前を入力してください");
		} else if (StringUtils.length(name) > 10) {
			messages.add("名前は10文字以下です");
		}

		if (branch == 0 ) {
			messages.add("支店を選択してください");
		}

		if (department == 0 ) {
			messages.add("部署・役職を選択してください");
		}

		if((branch == 1) && (department != 1) && (department != 2)){
			messages.add("所属が【本社】の選択項目は【人事総務部】か【情報管理部】のみです");
		}
		if((branch == 2) && (department != 3) && (department != 4)){
			messages.add("所属が【支店A】の選択項目は【支店長】か【店員】のみです");
		}
		if((branch == 3) && (department != 3) && (department != 4)){
			messages.add("所属が【支店B】の選択項目は【支店長】か【店員】のみです");
		}
		if((branch == 4) && (department != 3) && (department != 4)){
			messages.add("所属が【支店C】の選択項目は【支店長】か【店員】のみです");
		}

		if(messages.size() == 0){
			return true;
		}else{
			return false;
		}
	}


}