<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <body>
      <h2>Hello src.register.jsp!</h2>
      
      
<form action="register.do" method="post">
<p>登录名：<input type="text" name="id"></p>
<p>密码：<input type="text" name="pwd"></p>
<p>来自域：<input type="text" name="LOCAL_SERVICE" value="${LOCAL_SERVICE}" readonly></p>
<p><input type="submit" value="注册"></p>
</form>
   </body>
</html>