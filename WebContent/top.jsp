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
	<meta http-equiv="refresh"content="0;URL=http://localhost:8080/bulletin_board/login">
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
			<c:forEach var="searchTimeBeforeYear" begin="2000" end="2020">
			<option><c:out value="${searchTimeBeforeYear}"/></option>
			</c:forEach>



		</select> /

		<select name="searchTimeBeforeMonth">
			<option value="${searchTimeBeforeMonth }">${searchTimeBeforeMonth }</option>
			<c:forEach var="i" begin="01" end="12">
			<option><c:out value="${i }"/></option>
			</c:forEach>

		</select> /

		<select name="searchTimeBeforeDay">
			<option value="${searchTimeBeforeDay }">${searchTimeBeforeDay }</option>
			<c:forEach var="i" begin="01" end="31">
			<option><c:out value="${i }"/></option>
			</c:forEach>

		</select> から

		<select name="searchTimeAfterYear">
			<option value="${searchTimeAfterYear }">${searchTimeAfterYear }</option>
			<c:forEach var="i" begin="2000" end="2020">
			<option><c:out value="${i }"/></option>
			</c:forEach>
		</select> /

		<select name="searchTimeAfterMonth">
			<option value="${searchTimeAfterMonth }">${searchTimeAfterMonth }</option>
		<c:forEach var="i" begin="01" end="12">
			<option><c:out value="${i }"/></option>
			</c:forEach>
		</select> /

		<select name="searchTimeAfterDay">
			<option value="${searchTimeAfterDay }">${searchTimeAfterDay }</option>
			<c:forEach var="i" begin="01" end="31">
			<option><c:out value="${i }"/></option>
			</c:forEach>
		</select>　まで

		<br><br><input type="submit" value="検索"/>
	</form><a href="./top"><button>検索リセット</button></a>
</div>



<div class="messages">

<center>
<c:if test= "${ not empty errorMessages }">
	<div class = "errorMessages">

			<c:forEach items= "${ errorMessages }" var="message">
				<c:out value="${ message }" />
			</c:forEach>

	</div>
	<c:remove var="errorMessages" scope="session" />
</c:if>

<br><br>
▼△▼△▼△▼△▼△▼△▼△▼△▼投稿一覧▼△▼△▼△▼△▼△▼△▼△▼△▼

</center>


	<c:forEach items="${messages}" var="message">
		<div class="message"><br>

			<div class="title"><c:out value="${message.title}" /></div><br>
			<hr>
			<div class="userName">【カテゴリー】 <c:out value="${message.category}" />　　
								 【投稿者】 <c:out value="${ message.userName }"/>さん　
			 					<fmt:formatDate value="${message.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /><br></div><br>
			<div class="body"><pre><c:out value="${message.body}" /></pre></div>

		 <c:if test="${(loginUser.id == message.userId) || (loginUser.department_id == 2) ||
					((loginUser.department_id == 3) && (loginUser.branch_id == message.branchId))}">
			<form action="delete" method=post>
				<input type = "hidden" name = "contribution_id" id = "contribution_id" value = "${message.contributionId }" />
				<input type = "hidden" name = "user_id" id = "user_id" value = "${message.userId }" />
				<input class = "delete" type = "submit" name = "submit" value = "投稿を削除"
				 onclick = 'return confirm("削除してよろしいですか？");'/>
			</form>
		 </c:if><br>


<form action="comment" method="post">
<div class="commenttitle">【コメント】(500文字以下)<br/></div>
<pre><textarea name="body" rows="10" cols="50" class="comment"style="width:70%;height:90px" wrap="hard"></textarea></pre>
<input name="contributionId" type="hidden" id="contributionId" value="${message.contributionId}"/>
<br><input type="submit" value="コメントする">
				<br/>
				<br/>
			</form>


			<c:forEach items="${comments}" var="comment">
			<c:if test="${ comment.contributionId == message.contributionId }">
<div class="eachComment">
					<div class="userName">【投稿者】 <c:out value="${ comment.userName }"/>さん　
					 <fmt:formatDate value="${comment.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
					<div class="commentbody"><pre><c:out value="${comment.body}"/></pre></div>

					<c:if test="${(loginUser.id == comment.userId) || (loginUser.department_id == 2) ||
						 ((loginUser.department_id == 3) && (loginUser.branch_id == comment.userBranch))}">
						 <form action="delete" method=post>
						 	<input type = "hidden" name = "contribution_id" id = "contribution_id" value = "${message.contributionId }" />
						 	<input type = "hidden" name = "comment_id" id = "comment_id" value = "${comment.commentId }" />
							<input type = "hidden" name = "user_id" id = "user_id" value = "${comment.userId }" />
							<input class = "delete" type = "submit" name = "submit" value = "コメントを削除"
							 onclick = 'return confirm("このコメントを本当に削除してよろしいですか？");'/>
						 </form>
					</c:if>
</div>
			</c:if>
			</c:forEach>

</div>





	</c:forEach>
</div>
</c:if>


</div>
</body>
</html>