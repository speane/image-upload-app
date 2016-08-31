<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Upload image</title>
	</head>
	<body>
		<div style="text-align: center; padding-top: 200px;">
			<form action="imageuploading" method="post" enctype="multipart/form-data">
				<p>Имя файла</p>
				<input type="text" name="file_name" >
				<p>Изображение</p>
				<input type="file" name="file_to_upload" /><br><br>
				<input type="submit" value="Сохранить" /><br><br>
				<% if (request.getAttribute("errorMessage") != null) { %>
						<p style="color: red"><%=request.getAttribute("errorMessage")%></p>
				<% } %>
			</form>
		</div>
	</body>
</html>