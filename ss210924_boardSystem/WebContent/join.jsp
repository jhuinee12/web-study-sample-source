<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<li><a href="main.jsp">메인</a>
				<li><a href="main.jsp">게시판</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<!-- active : 현재 선택이 되어있다는 뜻 -->
						<li class="active"><a href="join.jsp">회원가입</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
	
	<!-- 회원가입 양식 생성 -->
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<!-- post: 정보를 숨기면서 보냄, JoinAction.jsp로 로그인 정보를 보냄 -->
				<form method="post" action="JoinAction.jsp">
					<h3 style="text-align: center;">회원가입 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="userID" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름" name="userName" maxlength="20">
					</div>
					<div class="form-group" style="text-align: center;">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary active"> <!-- active : 현재 활성화 상태 -->
								<input type="radio" name="userGender" autocomlete="off" value="남자" checked>남자
							</label>
							<label class="btn btn-primary">
								<input type="radio" name="userGender" autocomlete="off" value="여자" checked>여자
							</label>
						</div>
					</div>
					<div class="form-group">
						<input type="email" class="form-control" placeholder="이메일" name="userEmail" maxlength="20">
					</div>
					<input type="submit" class="btn btn-primary form-control" value="회원가입">
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
		
	<!-- jquery 가져옴 -->
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<!-- bootstrap의 jquery 가져옴 -->
	<script src="js/bootstrap.js"></script>
</body>
</html>