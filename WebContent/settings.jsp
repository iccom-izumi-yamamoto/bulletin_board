<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${editedUser.name }さんの設定</title>
<link href = "./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class ="main-contents">
<c:if test="${ not empty loginUser && loginUser.department_id == 1 }">
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${ errorMessages }" var="message">
				<li><c:out value="${ message }"/>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>
<div class="settings">
<form action="settings" method="post"><br/>

	<input name="id" type="hidden" id="id" value="${editedUser.id}"/>

	<label for="login_id">ログインID (半角英数字：6文字以上、20文字以下)</label><br>
	<input name="login_id" value="${editedUser.login_id }" id="login_id" /><br/>

	<label for="password">パスワード (半角英数字：6文字以上、255文字以下) </label><br>
	<input name="password" type="password" id="password"/><br/>

	<label for="password_check">確認のため、もう一度パスワードを入力してください </label><br>
	<input name="password_check" type="password" id="password_check"/><br/>

	<label for="name">名前(10文字以下)</label><br>
	<input name="name" value="${editedUser.name }" id="name" /><br/>

	<label for="branch_id">支店</label>
	<select name="branch_id">
		<option value="0">選択してください</option>
		<c:forEach items="${ branches }" var="branch">

		<c:if test="${ editedUser.getBranch_id() == branch.id }">
			<option selected='selected' value="${ branch.id }">${ branch.name }</option></c:if>
		<c:if test="${ editedUser.getBranch_id() != branch.id }">
			<option value="${ branch.id }">${ branch.name }</option></c:if>

		</c:forEach>
	</select>
	<br/>
	<label for="department_id">部署・役職</label>
	<select name="department_id">
		<option value="0">選択してください</option>
		<c:forEach items="${ departments }" var="department">

		<c:if test="${ editedUser.getDepartment_id() == department.id }">
			<option selected='selected' value="${ department.id }">${ department.name }</option></c:if>
		<c:if test="${ editedUser.getDepartment_id() != department.id }">
			<option value="${ department.id}">${ department.name }</option></c:if>

		</c:forEach>
	</select>
	<br/>
	<input type="submit" value="登録"/> <br/><br>
	<a href="./userManagement">戻る</a>
</form>
</div>
</c:if>

<c:if test="${ empty loginUser || loginUser.department_id != 1 }">
	アクセス権限のないユーザーです。<br><br>
	<a href="./top">戻る</a>
</c:if>

</div>
</body>
</html>