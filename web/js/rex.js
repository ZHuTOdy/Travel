// < !--用户的校验 -->
function checkUserName() {
	var username = $("#username").val();
	var reg = /^\w{5,10}$/;
	var tag = reg.test(username);
	if (username == "") {
		debugger
		$("#username").next().text("*账户不能为空").css('display', "inline")
		$("#username").css("border", "1px solid orange");
		tag = false;
	} else if (!tag) {
		// 表示不符合规则
		$("#username").css("border", "1px solid orange");
		$("#username").next().html("*账户的长度为5-10位").css("display", "inline")
		// 当失去焦点的时候自动清空文本内容
		// $("#username").val("");
	} else {
		$("#username").css("border", "1px solid #808080");
		$("#username").next().css("display", "none")
	}
	return tag;
}
// < !--密码的校验 -->
function checkPassword() {
	var password = $("#password").val();
	var reg = /^\w{5,10}$/;
	var tag = reg.test(password);
	if (password == "") {
		$("#password").css("border", "1px solid orange");
		$("#password").next().html("*密码不能为空").css("display", "inline")
		tag = false;
	} else if (!tag) {
		// 表示不符合规则
		$("#password").css("border", "1px solid orange");
		$("#password").next().html("*密码的长度为5-10位").css("display", "inline")
	} else {
		$("#password").css("border", "1px solid #808080");
		$("#password").next().css("display", "none")
	}
	return tag;
}

// < !--日期的校验 -->
/*function checkBirthday() {
	var birthday = $("#birthday").val();
	var tag;
	if (birthday == "") {
		// 表示不符合规则
		$("#birthday").css("border", "1px solid orange");
		tag = false;
	} else {
		$("#birthday").css("border", "");
		tag = true;
	}
	return tag;
}*/
// < !--验证码的校验 -->
/*function checkCode() {
	var check = $("#code").val();
	var tag = false;
	if (check == "") {
		// 表示不符合规则
		$("#check").css("border", "1px solid orange");
		tag = false;
	} else {
		$("#check").css("border", "");
		tag = true;
	}
	return tag;
}*/
// < !--姓名的校验 -->
/*function checkName() {
	var name = $("#name").val();
	var tag = false;
	if (name == "") {
		// 表示不符合规则
		$("#name").css("border", "1px solid orange");
		tag = false;
	} else {
		$("#name").css("border", "");
		tag = true;
	}
	return tag;
}*/

// < !--邮箱的校验 -->
function checkEmail() {
	var email = $("#email").val();
	var reg = /^\w+[@]\w+[.]\w+/;
	var tag = reg.test(email);
	if (email == "") {
		$("#email").css("border", "1px solid orange");
		$("#email").next().html("*邮箱不能为空").css("display", "inline")
		tag = false;
	} else if (!tag) {
		// 表示不符合规则
		$("#email").css("border", "1px solid orange");
		$("#email").next().html("*邮箱格式错误").css("display", "inline")
	} else {
		$("#email").css("border", "1px solid #808080");
		$("#email").next().css("display", "none")
	}
	return tag;
}
// < !--手机号码的校验 -->
function checkPhoneNumber() {
	var telephone = $("#telephone").val();
	var reg = /^$|^[1][35678][0-9]{9}$/;
	var tag = reg.test(telephone);
	if (!tag) {
		// 表示不符合规则
		$("#telephone").css("border", "1px solid orange");
		$("#telephone").next().html("*号码格式错误").css("display", "inline")
	} else {
		$("#telephone").css("border", "1px solid #808080");
		$("#telephone").next().css("display", "none")
	}
	return tag;
}
