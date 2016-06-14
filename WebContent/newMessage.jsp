<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href = "./css/style.css" rel="stylesheet" type="text/css">
<title>新規投稿</title>
</head>
<body>

<c:if test="${empty loginUser }">
ログインしてください。<br>
<a href="./top">戻る</a>
</c:if>

<a href="./top">戻る</a>
<div class= "main-contents">
<c:if test="${ not empty loginUser }">
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

<center><h2>新規投稿</h2></center>

<div class="newMessage">
<form action="newMessage" method="post"><br>
	件名(50文字以下)<br/>
	<textarea name="title" rows="1" cols="50"><c:out value="${ errorContribution.title }"/></textarea>
	<br/>
	<br/>

	本文(1000文字以下)<br/>
	<textarea style="width:70%;height:90px" name="body" rows="10" cols="100" wrap="hard"><c:out value="${ errorContribution.body }"/></textarea>
	<br/>
	<br/>

	カテゴリー(10文字以下)<br/>
	<input name="category" maxlength=10 value="${ errorContribution.category }"/>
	<br/>
	<br/>

	<input type="submit" value="投稿する">
	<br/>

<c:remove var="errorContribution" scope="session"/>
</form>
</div>
</c:if>

</div>
<br>
<br>
</body>
</html>