<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="bbs.Bbs" %>
<%@ page import="bbs.BbsDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<!-- css/bootstrap.css로 기본 레이아웃 설정 -->
<link rel="stylesheet" href="css/bootstrap.css">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String)session.getAttribute("userID");
		}
		// UserID 가 없음 :: 로그인하지 않은 상태
		if (userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요.')");
			script.println("location.href = 'login.jsp'");
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
		}
	%>

	<!-- nav : 웹 사이트의 전반적인 구성 => 헤더화면 구성 -->
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-targer="#bs-example-navbar-collapse-1"
			aria-expanded="false">
				<!-- 상단 메뉴 아이콘의 작대기 -->
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<!-- navbar-brand :: 로고 -->
			<a class="navbar-brand" href="main.jsp">JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<!-- 리스트 -->
			<ul class="nav navbar-nav">
				<!-- active : 현재 선택이 되어있다는 뜻 -->
				<li><a href="main.jsp">메인</a>
				<li class="active"><a href="bbs.jsp">게시판</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="LogoutAction.jsp">로그아웃</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
	
	<div class="container">
		<div class="row">
			<form method="post" action="UpdateAction.jsp?bbsID=<%= bbsID %>">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead> <!-- 테이블의 제목 -->
						<tr> <!-- 행(줄) -->
							<th style="backgroud-color: #eeeee; text-align: center">게시판 글쓰기 양식</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="from-control" placeholder="글 제목" name="bbsTitle" maxlength="50" value="<%= bbs.getBbsTitle() %>"></td>
						</tr>
						<tr>
							<td><textarea class="from-control" placeholder="글 내용" name="bbsContent" maxlength="2048" style="width: 100%; height: 350px;"><%= bbs.getBbsContent() %></textarea></td>
						</tr>
					</tbody>
				</table>
				<input type="submit" class="btn btn-primary pull-right" value="글수정">
			</form>
		</div>
	</div>
		
	<!-- jquery 가져옴 -->
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<!-- bootstrap의 jquery 가져옴 -->
	<script src="js/bootstrap.js"></script>
</body>
</html>