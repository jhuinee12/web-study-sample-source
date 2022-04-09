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
		int bbsID = 0;
		if (request.getParameter("bbsID") != null) {
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
		}
		if (bbsID == 0) {	// 번호가 존재해야만 글 보기 가능
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 글입니다.')");
			script.println("location.href = 'bbs.jsp'");
			script.println("</script>");
		}
		Bbs bbs = new BbsDAO().getBbs(bbsID);
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
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead> <!-- 테이블의 제목 -->
					<tr> <!-- 행(줄) -->
						<th colspan="3" style="backgroud-color: #eeeee; text-align: center">게시판 글 보기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<!-- 글에 특수문자 입력 시 제대로 처리가 불가능함 (html로 읽어들이기 때문에 소스코드로 보는 경우 다분)
						     따라서 replace를 이용해 특수문자 처리 -->
						<td style="width:20%">글제목</td>
						<td colspan="2">
							<%= 
								bbs.getBbsTitle() 
										.replaceAll(" ","&nbsp;")
										.replaceAll("<", "&lt;")
										.replaceAll(">", "&gt;")
										.replaceAll("\n", "</br>")
							%>
						</td>
					</tr>
					<tr>
						<td style="width:20%">작성자</td>
						<td colspan="2"><%= bbs.getUserID() %></td>
					</tr>
					<tr>
						<td style="width:20%">작성일자</td>
						<td colspan="2"><%= bbs.getBbsDate().substring(0, 11) + bbs.getBbsDate().substring(11,13) + "시" 
								+ bbs.getBbsDate().substring(14, 16) + "분" %></td>
					</tr>
					<tr>
						<td style="width:20%">내용</td>
						<!-- 글에 특수문자 입력 시 제대로 처리가 불가능함 (html로 읽어들이기 때문에 소스코드로 보는 경우 다분)
						     따라서 replace를 이용해 특수문자 처리 -->
						<td colspan="2" style="min-height:200px; text-align:left;">
							<%= 
								bbs.getBbsContent()
										.replaceAll(" ","&nbsp;")
										.replaceAll("<", "&lt;")
										.replaceAll(">", "&gt;")
										.replaceAll("\n", "</br>")
							%></td>
					</tr>
				</tbody>
			</table>
			<a href="bbs.jsp" class="btn btn-primary">목록</a>
			<!-- 작성자가 본인이면 수정/삭제 가능하도록 버튼 생성 -->
			<%
				if (userID != null && userID.equals(bbs.getUserID())) {
			%>
					<a href="update.jsp?bbsID=<%= bbsID %>" class="btn btn-primary">수정</a>
					<!-- 삭제할 때 안내 메세지 호출 -->
					<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="DeleteAction.jsp?bbsID=<%= bbsID %>" class="btn btn-primary">삭제</a>
			<%
				}
			%>
			<input type="submit" href="write.jsp" class="btn btn-primary pull-right" value="글쓰기">
		</div>
	</div>
		
	<!-- jquery 가져옴 -->
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<!-- bootstrap의 jquery 가져옴 -->
	<script src="js/bootstrap.js"></script>
</body>
</html>