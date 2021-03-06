<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href = "./css/style.css" rel="stylesheet" type="text/css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン</title>
</head>
<body>

<div class="login">
<h1>BSG掲示板システム</h1>
<hr>





<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">

			<c:forEach items="${ errorMessages }" var="message">
				<c:out value="${ message }"/><br>
			</c:forEach>

	</div>
	<c:remove var="errorMessages" scope="session" />
</c:if>


<form action="login" method="post"><br/>
<label for="login_id">ログインID</label><br>
<input name="login_id"value="${ errorLogin }" id="login_id"/><br/><br>

<label for="password">パスワード</label><br>
<input name="password" type="password" id="password" /> <br/>
<br>
	<input type="submit" value="ログイン"/> <br/>
</form>

</div>
</body>
</html>