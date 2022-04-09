<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
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
	<%
		if(userID == null) { 	// userID가 null이면 로그인/회원가입 보여주기
	%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul>
				</li>
			</ul>
	<%
		} else {	// userID가 null이 아니면 로그아웃 보여주기
	%>
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
	<%	
		}
	%>
		</div>
	</nav>
	
	<div class="container">
		<div class="row">
			<form method="post" action="WriteAction.jsp">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead> <!-- 테이블의 제목 -->
						<tr> <!-- 행(줄) -->
							<th style="backgroud-color: #eeeee; text-align: center">게시판 글쓰기 양식</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="from-control" placeholder="글 제목" name="bbsTitle" maxlength="50" style="width: 100%;"></td>
						</tr>
						<tr>
							<td><textarea class="from-control" placeholder="글 내용" name="bbsContent" maxlength="2048" style="width: 100%; height: 350px;"></textarea></td>
						</tr>
					</tbody>
				</table>
				<input type="submit" href="write.jsp" class="btn btn-primary pull-right" value="글쓰기">
			</form>
		</div>
	</div>
		
	<!-- jquery 가져옴 -->
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<!-- bootstrap의 jquery 가져옴 -->
	<script src="js/bootstrap.js"></script>
</body>
</html>