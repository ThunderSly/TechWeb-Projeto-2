<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<span>Username</span>
	<input id="username-input" type="text" />
	<span>Password</span>
	<input id="password-input" type="text" />

	<button onclick="onSubmitPost()">POST</button>
	<button onclick="onSubmitPut()">PUT</button>
</body>

<script>
	function onSubmitPost() {
		let username = document.getElementById("username-input").value
		let password = document.getElementById("password-input").value

		data = {
			'username' : username,
			'password' : password
		}

		fetch('/testespring/teste', {
			method : 'POST',
			body : JSON.stringify(data)
		})
	}

	function onSubmitPut() {
		let username = document.getElementById("username-input").value
		let password = document.getElementById("password-input").value

		data = {
			'username' : username,
			'password' : password
		}

		fetch('/testespring/teste', {
			method : 'PUT',
			body : JSON.stringify(data)
		})
	}
</script>
</html>

