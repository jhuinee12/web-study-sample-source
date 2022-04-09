<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" class="user.User" scope="page" /> <!-- 현재 페이지에서 User.java를 beans로 사용 -->
<jsp:setProperty name="user" property="userID" />	<!-- 로그인 페이지에서 넘겨준 userID를 받아서 사용자 userID에 넣음 -->
<jsp:setProperty name="user" property="userPassword" />
<jsp:setProperty name="user" property="userName" />
<jsp:setProperty name="user" property="userGender" />
<jsp:setProperty name="user" property="userEmail" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String)session.getAttribute("userID");
		}
		if (userID != null) {	// 자동로그인
			PrintWriter script = response.getWriter();
			script.println("<script>");	// 스크립트 문장 실행
			script.println("alert('이미 로그인이 되어 있습니다.')");	
			script.println("location.href = 'main.jsp'");	// main.jsp로 이동
			script.println("</script>");
		}
		
		if (user.getUserID() == null || user.getUserPassword() == null || user.getUserName() == null 
				|| user.getUserGender() == null || user.getUserEmail() == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안 된 사항이 있습니다.')");	
			script.println("history.back()");	// 이전 페이지(회원가입 페이지)로 돌려보냄
			script.println("</script>");
		} else {
			UserDAO userDAO = new UserDAO();
			int result = userDAO.join(user);
			if (result == -1) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이미 존재하는 아이디입니다.')");	
				script.println("history.back()");	// 이전 페이지(로그인 페이지)로 돌려보냄
				script.println("</script>");
			} else {
				session.setAttribute("userID", user.getUserID());	// 세션 부여
				
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("location.href = 'main.jsp'");	
				script.println("</script>");
			}
		}
	%>
</body>
</html>