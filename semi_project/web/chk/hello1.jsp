<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Hello JSP</title>

</head>
<body>

<form action="/java_jspservlet2/JdbcJspServletOneRow" method="get" id="frm">
<input type="text" name="emp_name" />

<input type="submit" value="제출" id="sm" />
</form>
<h1>${hello1}</h1>

</body>
</html>