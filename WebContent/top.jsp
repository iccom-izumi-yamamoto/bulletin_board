<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href = "./css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>バリストライドグループ掲示板システム</title>

</head>
<body>



<div class="main-contents">


<div class="header">
	<c:if test="${ empty loginUser }">
		<a href="login">ログイン</a>
	<%-- 	<a href="signup">登録する</a> --%>
	</c:if>

	<c:if test="${loginUser.suspention == 1 }">
	停止されたアカウントです。<br>


	</c:if>

	<c:if test="${ loginUser.suspention == 0 }">
		${loginUser.name }さん(@${loginUser.login_id})がログインしています。<br/><br>

		<c:if test="${loginUser.department_id == 1 }">
		<a href="userManagement">◆ ユーザー管理</a>　　　</c:if>
		<a href="newMessage">◆ 新規投稿</a>　　　
		<a href="logout">◆ ログアウト</a><br/>
	</c:if>


</div>

<br><br>

<c:if test="${ loginUser.suspention == 0 }">
<c:if test= "${ not empty errorMessages }">
	<div class = "errorMessages">
		<ul>
			<c:forEach items= "${ errorMessages }" var="message">
				<li><c:out value="${ message }" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session" />
</c:if>





<div class="search">絞り込み検索<br>

	<form action="top" method=get>
		<select name="searchCategory">
			<option value=>選択してください</option>
			<c:forEach items="${categories}" var="category">

			<c:if test="${ searchCategory == category.category}">
			<option selected='selected' value="${category.category }">${category.category }</option></c:if>
			<c:if test="${ searchCategory != category.category }">
			<option value="${ category.category}">${ category.category }</option></c:if>

		 	</c:forEach>
		</select><br><br>


		<select name="searchTimeBeforeYear">
			<option value="${searchTimeBeforeYear }">${searchTimeBeforeYear }</option>
			<option value="2016">2016</option>

		</select> /

		<select name="searchTimeBeforeMonth">
			<option value="${searchTimeBeforeMonth }">${searchTimeBeforeMonth }</option>
			<option value="1">01</option>
			<option value="2">02</option>
			<option value="3">03</option>
			<option value="4">04</option>
			<option value="5">05</option>
			<option value="6">06</option>
			<option value="7">07</option>
			<option value="8">08</option>
			<option value="9">09</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		</select> /

		<select name="searchTimeBeforeDay">
			<option value="${searchTimeBeforeDay }">${searchTimeBeforeDay }</option>
			<option value="1">01</option>
			<option value="2">02</option>
			<option value="3">03</option>
			<option value="4">04</option>
			<option value="5">05</option>
			<option value="6">06</option>
			<option value="7">07</option>
			<option value="8">08</option>
			<option value="9">09</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
			<option value="16">16</option>
			<option value="17">17</option>
			<option value="18">18</option>
			<option value="19">19</option>
			<option value="20">20</option>
			<option value="21">21</option>
			<option value="22">22</option>
			<option value="23">23</option>
			<option value="24">24</option>
			<option value="25">25</option>
			<option value="26">26</option>
			<option value="27">27</option>
			<option value="28">28</option>
			<option value="29">29</option>
			<option value="30">30</option>
			<option value="31">31</option>
		</select> から

		<select name="searchTimeAfterYear">
			<option value="${searchTimeAfterYear }">${searchTimeAfterYear }</option>
			<option value="2016">2016</option>
		</select> /

		<select name="searchTimeAfterMonth">
			<option value="${searchTimeAfterMonth }">${searchTimeAfterMonth }</option>
			<option value="1">01</option>
			<option value="2">02</option>
			<option value="3">03</option>
			<option value="4">04</option>
			<option value="5">05</option>
			<option value="6">06</option>
			<option value="7">07</option>
			<option value="8">08</option>
			<option value="9">09</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		</select> /

		<select name="searchTimeAfterDay">
			<option value="${searchTimeAfterDay }">${searchTimeAfterDay }</option>
			<option value="1">01</option>
			<option value="2">02</option>
			<option value="3">03</option>
			<option value="4">04</option>
			<option value="5">05</option>
			<option value="6">06</option>
			<option value="7">07</option>
			<option value="8">08</option>
			<option value="9">09</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
			<option value="16">16</option>
			<option value="17">17</option>
			<option value="18">18</option>
			<option value="19">19</option>
			<option value="20">20</option>
			<option value="21">21</option>
			<option value="22">22</option>
			<option value="23">23</option>
			<option value="24">24</option>
			<option value="25">25</option>
			<option value="26">26</option>
			<option value="27">27</option>
			<option value="28">28</option>
			<option value="29">29</option>
			<option value="30">30</option>
			<option value="31">31</option>
		</select>　まで

		<br><br><input type="submit" value="検索"/>
	</form><a href="./top"><button>検索リセット</button></a>
</div>

<br><br>

<div class="messages">

▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼
	<c:forEach items="${messages}" var="message">
		<div class="message"><br>

			<div class="title"><c:out value="${message.title}" /></div><br>
			<div class="body"><br><c:out value="${message.body}" /></div><br>
			<div class="userName">【カテゴリー】 <c:out value="${message.category}" /><br>
								 【投稿者】 <c:out value="${ message.userName }"/>
			 					<fmt:formatDate value="${message.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /><br></div><br>

		 <c:if test="${(loginUser.id == message.userId) || (loginUser.department_id == 2) ||
					((loginUser.department_id == 3) && (loginUser.branch_id == message.branchId))}">
			<form action="delete" method=post>
				<input type = "hidden" name = "contribution_id" id = "contribution_id" value = "${message.contributionId }" />
				<input type = "hidden" name = "user_id" id = "user_id" value = "${message.userId }" />
				<input class = "delete" type = "submit" name = "submit" value = "投稿を削除"
				 onclick = 'return confirm("削除してよろしいですか？");'/>
			</form>
		 </c:if>

		</div>--------------------------------------------------------------------------------<br><br>






		<div class="comments">

			<form action="comment" method="post">
				【コメント】(500文字以下)<br/>
				<textarea name="body" rows="10" cols="50" class="comment">
				</textarea>
				<br/><input name="contributionId" type="hidden" id="contributionId" value="${message.contributionId}"/>
				<br>
				<input type="submit" value="コメントする">
				<br/>
				<br/>
			</form>


			<c:forEach items="${comments}" var="comment">
			<c:if test="${ comment.contributionId == message.contributionId }">
				<div class="comment">
					<div class="body"> <c:out value="${comment.body}"/></div><br>
					<div class="userName">【投稿者】 <c:out value="${ comment.userName }"/>
					 <fmt:formatDate value="${comment.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div><br>

					<c:if test="${(loginUser.id == comment.userId) || (loginUser.department_id == 2) ||
						 ((loginUser.department_id == 3) && (loginUser.branch_id == comment.branch_id))}">
						 <form action="delete" method=post>
						 	<input type = "hidden" name = "contribution_id" id = "contribution_id" value = "${message.contributionId }" />
						 	<input type = "hidden" name = "comment_id" id = "comment_id" value = "${comment.commentId }" />
							<input type = "hidden" name = "user_id" id = "user_id" value = "${comment.userId }" />
							<input class = "delete" type = "submit" name = "submit" value = "コメントを削除"
							 onclick = 'return confirm("このコメントを本当に削除してよろしいですか？");'/>
						 </form>
					</c:if><br>

					*****************
				</div>
			</c:if>
			</c:forEach>






		</div>▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼<br>
	</c:forEach>
</div>
</c:if>


</div>
</body>
</html>