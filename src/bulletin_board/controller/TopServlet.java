package bulletin_board.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import bulletin_board.beans.SearchMessage;
import bulletin_board.beans.User;
import bulletin_board.beans.UserComment;
import bulletin_board.beans.UserMessage;
import bulletin_board.service.CategoryService;
import bulletin_board.service.CommentService;
import bulletin_board.service.MessageSearchService;
import bulletin_board.service.MessageService;

@WebServlet (urlPatterns = {"/top"})
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID =1L;
	private static String searchCategory=null, searchTimeBefore = null, searchTimeAfter = null;


	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {

		searchCategory = request.getParameter("searchCategory");

		//System.out.println(searchCategory);

		String searchTimeBeforeYear = request.getParameter("searchTimeBeforeYear");
		String searchTimeBeforeMonth = request.getParameter("searchTimeBeforeMonth");
		String searchTimeBeforeDay = request.getParameter("searchTimeBeforeDay");
		String searchTimeAfterYear = request.getParameter("searchTimeAfterYear");
		String searchTimeAfterMonth = request.getParameter("searchTimeAfterMonth");
		String searchTimeAfterDay = request.getParameter("searchTimeAfterDay");

		if ( !StringUtils.isEmpty(searchTimeBeforeYear) && !StringUtils.isEmpty(searchTimeBeforeMonth) &&
				!StringUtils.isEmpty(searchTimeBeforeDay) ) {
			searchTimeBefore = searchTimeBeforeYear + "/" + searchTimeBeforeMonth + "/" +
					searchTimeBeforeDay;

		} else if ( !(StringUtils.isEmpty(searchTimeBeforeYear) && StringUtils.isEmpty(searchTimeBeforeMonth) &&
				StringUtils.isEmpty(searchTimeBeforeDay))) {
			List<String> searchmessages = new ArrayList<String>();
			searchmessages.add("【年月日】全て選択してください");
			request.setAttribute("errorMessages", searchmessages);

		} else {

//			Date date = new Date();
//	        SimpleDateFormat year = new SimpleDateFormat("yyyy/");
//	        SimpleDateFormat month = new SimpleDateFormat("MM/");
//	        SimpleDateFormat day = new SimpleDateFormat("dd");
//			searchTimeBefore = (year.format(date) + (month.format(date)) + (day.format(date)));

			searchTimeBefore = "2016/01/01";
			//System.out.println(searchTimeBefore);
		}

		if ( !StringUtils.isEmpty(searchTimeAfterYear) && !StringUtils.isEmpty(searchTimeAfterMonth) &&
				!StringUtils.isEmpty(searchTimeAfterDay) ) {
			searchTimeAfter = searchTimeAfterYear + "/" + searchTimeAfterMonth + "/" +
					searchTimeAfterDay;

		} else if (!(StringUtils.isEmpty(searchTimeAfterYear) && StringUtils.isEmpty(searchTimeAfterMonth) &&
				StringUtils.isEmpty(searchTimeAfterDay))) {
			List<String> searchmessages = new ArrayList<String>();
			searchmessages.add("【年月日】全て選択してください");
			request.setAttribute("errorMessages", searchmessages);

		} else {
			Date date = new Date();
	        SimpleDateFormat year = new SimpleDateFormat("yyyy/");
	        SimpleDateFormat month = new SimpleDateFormat("MM/");
	        SimpleDateFormat day = new SimpleDateFormat("dd");
			searchTimeAfter = (year.format(date) + (month.format(date)) + (day.format(date))); //本日の日付を入力
			//System.out.println(searchTimeAfter);
		}

		User loginuser = (User) request.getSession().getAttribute("loginUser");
//
		List<UserMessage> categories = new CategoryService().getSearchCategory();
		List<UserMessage> contributions = new MessageService().getMessage();
		List<UserMessage> messages = new MessageService().getMessage();
		List<UserComment> comments = new CommentService().getComment();

		request.setAttribute("messages", messages);
		request.setAttribute("comments", comments);

		List<SearchMessage> searches = new MessageSearchService().getMessage(searchCategory,
					searchTimeBefore, searchTimeAfter);


		if (searchCategory != null || searchTimeBefore != null || searchTimeAfter != null) {

			searchTimeBeforeYear = request.getParameter("searchTimeBeforeYear");
			searchTimeBeforeMonth = request.getParameter("searchTimeBeforeMonth");
			searchTimeBeforeDay = request.getParameter("searchTimeBeforeDay");
			searchTimeAfterYear = request.getParameter("searchTimeAfterYear");
			searchTimeAfterMonth = request.getParameter("searchTimeAfterMonth");
			searchTimeAfterDay = request.getParameter("searchTimeAfterDay");
			request.setAttribute("messages", searches);

			if (searches.isEmpty()) {
				List<String> searchmessages = new ArrayList<String>();
				searchmessages.add("検索結果:該当する記事は0件でした。");
				request.setAttribute("errorMessages", searchmessages);
			}

			request.setAttribute("searchTimeBeforeYear", searchTimeBeforeYear);
			request.setAttribute("searchTimeBeforeMonth", searchTimeBeforeMonth);
			request.setAttribute("searchTimeBeforeDay", searchTimeBeforeDay);
			request.setAttribute("searchTimeAfterYear", searchTimeAfterYear);
			request.setAttribute("searchTimeAfterMonth", searchTimeAfterMonth);
			request.setAttribute("searchTimeAfterDay", searchTimeAfterDay);


		} else {

			request.setAttribute("messages", messages);

			request.setAttribute("contributions", contributions);
			request.setAttribute("comments", comments);

		}

		request.setAttribute("categories", categories);
		request.setAttribute("searchCategory", searchCategory);
		request.setAttribute("loginUser", loginuser);

		request.getRequestDispatcher("/top.jsp").forward(request, response);
//		response.sendRedirect("top");
	}

}
