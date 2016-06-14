<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href = "./css/style.css" rel="stylesheet" type="text/css">
<title>ユーザー管理</title>
</head>
<body>

<div class="main-contents">

<c:if test="${loginUser.department_id == 1 }">
	<a href="./top">◆ホーム</a>　　　
	<a href="signup">◆ ユーザー新規登録</a><br>
<br>
<br>

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${ errorMessages }" var="message">
				<c:out value="${ message }"/>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>


<div class = "users">
<h3>ユーザー一覧表</h3>
<table class="table" border=1>
	<tbody>
		<tr>
			<th>ログインID</th>
			<th>名前</th>
			<th>支店</th>
			<th>部署・役職</th>
			<th>アカウント状態</th>
			<th>設定編集</th>
			<th>アカウント削除</th>
		</tr>
		<c:forEach items = "${users }" var = "user">
		<tr>
			<td><c:out value = "${user.login_id }" /></td>
			<td><c:out value = "${user.name }" /></td>
			<td><c:out value = "${user.branch }" /></td>
			<td><c:out value = "${user.department }" /></td>

			<form action="userManagement" method=post>
				<input name="id" type="hidden" id="id" value="${user.id}"/>
				<c:choose>
					<c:when test="${ user.suspention  == 0 && loginUser.login_id == user.login_id}">
					<td></td></c:when>

					<c:when test="${ user.suspention == 0  }"><td><input name="submit" type="submit" value="停止"
							onclick = 'return confirm("${user.name}さん（${user.login_id}）を停止します。\nよろしいですか？");'/></td>
					</c:when>
					<c:otherwise><td><input name="submit" type="submit" value="利用再開"
							onclick = 'return confirm("${user.name}さん（${user.login_id}）を再開します。\nよろしいですか？");'/></td>

					</c:otherwise>
				</c:choose>

			</form>


			<form action = "settings" method = "get">
				<input type = "hidden" name = "id" value="${user.id}" />
				<td><input class = "submit" id = "submit_button" type = "submit" value = "編集" /></td>
			</form>


			<form action="deleteUser" method=post>
				<c:choose>
				<c:when test="${loginUser.login_id != user.login_id}">
				<td><button type = 'submit' name = 'id' value = "${user.id }"
				onClick = 'return confirm("${user.name}さん（${user.login_id}）を削除します。\nよろしいですか？");'>削除</button></td>
				</c:when>
				<c:otherwise><td></td></c:otherwise>
				</c:choose>
			</form>



		</tr>
		</c:forEach>
	</tbody>
</table>

</div>

</c:if>
</div>

<c:if test="${loginUser.department_id != 1 }">
	アクセス権限のないユーザーです。<br><br>
	<a href="./top">戻る</a>
</c:if>


</body>
</html>