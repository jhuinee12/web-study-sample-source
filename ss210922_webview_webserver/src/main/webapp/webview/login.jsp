<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery 사용 -->
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(function() {
	login("kim","1234");
});
function login(userid, pwd) {
	var param = "userid=" + userid + "&passwd=" + pwd;
	// 비동기적인 방식 (백그라운드에서 실행)으로 서블릿 호출
	$.ajax({
		type : "post",
		url : "/ss210922_webview_webserver/webview_servle/login.do",
		data : param,
		success : function(result) {
			// responseText 응답텍스트
			console.log(result);
			$("#result").html(result);	// div에 결과 출력
			// 안드로이드의 텍스트뷰에 결과 출력
			window.android.setMessage(result);
		}
	})
}
</script>
</head>
<body>
	<h2>웹뷰와의 통신</h2>
	<div id="result"></div>
</body>
</html>