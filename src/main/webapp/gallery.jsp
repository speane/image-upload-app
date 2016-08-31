<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Gallery</title>
	</head>
	<body>
		<div style="text-align: center; padding-top: 200px;">
			<h2>Галерея<h2>
			<form action="/imageuploadapp" method="GET">
				<input type="submit" value="Вернуться к загрузке"><br><br>
			</form>
			<%! String path = System.getenv("CATALINA_HOME") + File.separator + "webapps" 
				+ File.separator + "ROOT" + File.separator + "images"; %>
			<%! File imageDir = new File(path); %>
			<% for (File file : imageDir.listFiles()) { %>
					<img width="300" height="300" src="/images/<%=file.getName()%>" />
			<% } %>
		</div>
	</body>
</html>