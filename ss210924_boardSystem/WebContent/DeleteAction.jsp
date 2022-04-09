<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bbs.BbsDAO" %>
<%@ page import="bbs.Bbs" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
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
		if (userID == null) {	// 자동로그인
			PrintWriter script = response.getWriter();
			script.println("<script>");	// 스크립트 문장 실행
			script.println("alert('로그인을 하세요.')");	
			script.println("location.href = 'login.jsp'");	// main.jsp로 이동
			script.println("</script>");
		} 

		int bbsID = 0;
		if (request.getParameter("bbsID") != null) {
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
		}
		// bbsID 가 없음 :: 글의 번호가 없음
		if (bbsID == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 글입니다.')");
			script.println("location.href = 'bbs.jsp'");
			script.println("</script>");
		}
		// 현재 작성한 글이 본인인지 확인 :: 세션 관리가 필요함
		Bbs bbs = new BbsDAO().getBbs(bbsID);
		if (!userID.equals(bbs.getUserID())) {	// ID 값이 다름
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('권한이 없습니다.')");
			script.println("location.href = 'bbs.jsp'");
			script.println("</script>");
		} else {	// 권한이 있는 사람 :: 수정(삭제) 처리
			BbsDAO bbsDAO = new BbsDAO();
			int result = bbsDAO.delete(bbsID);
			if (result == -1) {	// 오류 발생
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('글삭제에 실패했습니다.')");	
				script.println("history.back()");	// 이전 페이지(로그인 페이지)로 돌려보냄
				script.println("</script>");
			} else {			// 성공
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("location.href = 'bbs.jsp'");	
				script.println("</script>");
			}
		}
	%>
</body>
</html>