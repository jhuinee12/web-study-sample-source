<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" class="user.User" scope="page" /> <!-- 현재 페이지에서 User.java를 beans로 사용 -->
<jsp:setProperty name="user" property="userID" />	<!-- 로그인 페이지에서 넘겨준 userID를 받아서 사용자 userID에 넣음 -->
<jsp:setProperty name="user" property="userPassword" />
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
		
		UserDAO userDAO = new UserDAO();
		int result = userDAO.login(user.getUserID(), user.getUserPassword());
		if (result == 1) {
			session.setAttribute("userID", user.getUserID());	// 세션 부여
			
			PrintWriter script = response.getWriter();
			script.println("<script>");	// 스크립트 문장 실행
			script.println("location.href = 'main.jsp'");	// 로그인 성공 시 main.jsp로 이동
			script.println("</script>");
		}
		else if (result == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 틀립니다.')");	
			script.println("history.back()");	// 이전 페이지(로그인 페이지)로 돌려보냄
			script.println("</script>");
		}
		else if (result == -1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재하지 않는 아이디입니다.')");	
			script.println("history.back()");	// 이전 페이지(로그인 페이지)로 돌려보냄
			script.println("</script>");
		}
		else if (result == -2) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생했습니다.')");	
			script.println("history.back()");	// 이전 페이지(로그인 페이지)로 돌려보냄
			script.println("</script>");
		}
	%>
</body>
</html>