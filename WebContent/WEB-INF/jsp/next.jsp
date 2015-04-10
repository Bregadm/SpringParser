
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Результат парсинга</title>
</head>
<body>

<style>
#info {
	text-align: left;
	margin: 0 auto;
	position: relative;
	width: 600px;
}
</style>

	<jsp:useBean id="fnm" scope="request"
		class="ru.eldorado.Controller.MainController">
	</jsp:useBean>
	<center>
		<%
			request.getAttribute("content");
		%>
		<h2><%=request.getAttribute("response")%></h2>
		<br>
		<p>
			Путь хранения:
			<%=request.getRealPath("/") + "files"%>\<span class="file"><c:out
					value="${file}"></c:out></span>
		</p>
		<%
			String xmlPath = request.getRealPath("/") + "upload" + "/"
					+ request.getAttribute("content");
		%>
		<%
			if (request.getAttribute("response").equals("Файл  не загружен")) {
		%>
		<c:set var="title" scope="request" value="Выберите файл .xml">
		</c:set>
		<h3>
			<c:out value="${title}"></c:out>
		</h3> 
		<%} else if (request.getAttribute("checkfileXSD").equals("Неверная XSD-схема")) { %>
		 <span  style = "red;"><%= request.getAttribute("checkfileXSD") %> </span>
		<%
			} else {
		%>
		<%
			out.print("<h2>Просмотр XML файла</h2>");
		%>
		<div id="info">
		 <span  style = "color:green;"><%=request.getAttribute("checkfileXSD") %></span></br>
			<span>Общая сумма заказов всех клиентов:</span> <b><%=request.getAttribute("totalSum")%></b></br>
			<span>Клиент с максимальной суммой заказа:</span> <b> <%=request.getAttribute("maxVal")%></b></br>
			<span>Общее количество заказов:</span> <b> <%=request.getAttribute("countOrder")%></b></br>
			<span>Сумма максимального заказа:</span> <b> <%=request.getAttribute("maxOrder")%></b></br>
			<span>Сумма минимального заказа:</span> <b> <%=request.getAttribute("minOrder")%></b></br>
			<span>Сумма среднего заказа:</span> <b> <%=request.getAttribute("middleOrder")%></b>
		</div>
		<%
			}
		%>
	</center>
</body>
</html>