<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="styles/style.css"></link>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/rex.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript">
	$(function() {
		//$("#name").blur(checkName);
		$(".title1 .tdiv:first").css("background-color", "#ffd800");
		$(".title1 .tdiv:first").mouseover(function() {
			$(".table1 tr td").remove();
			$(".title1 .tdiv:first").css("background-color", "#ffd800");
			$(".title1 .tdiv:eq(1)").css("background-color", "white");
			$(".title1 .tdiv:eq(2)").css("background-color", "white");
			$(this).css("background-color", "#ffd800");
			var arr = search("index/popularRoute")
			$.each(arr, function(i, item) {
				$(".table1 tr").append(tab1(item))
			})
		})
		$(".title1 .tdiv:eq(1)").mouseover(function() {
			$(".table1 tr td").remove();
			$(".title1 .tdiv:eq(1)").css("background-color", "#ffd800");
			$(".title1 .tdiv:eq(2)").css("background-color", "white");
			$(".title1 .tdiv:eq(0)").css("background-color", "white");
			$(this).css("background-color", "#ffd800");
			var arr = search("index/newRoute")
			$.each(arr, function(i, item) {
				$(".table1 tr").append(tab1(item))
			})
		})
		$(".title1 .tdiv:eq(2)").mouseover(function() {
			$(".table1 tr td").remove();
			$(".title1 .tdiv:eq(2)").css("background-color", "#ffd800");
			$(".title1 .tdiv:eq(1)").css("background-color", "white");
			$(".title1 .tdiv:eq(0)").css("background-color", "white");
			$(this).css("background-color", "#ffd800");
			var arr = search("index/randRoute")
			$.each(arr, function(i, item) {
				$(".table1 tr").append(tab1(item))
			})
		});

		(function() {
			var data = search("index/popularRoute");
			$.each(data, function(i, item) {
				$(".table1 tr").append(tab1(item))
			});
			data = search("index/navigation");
			$
					.each(
							data,
							function(i, item) {
								$(".ultitle>li:last")
										.after(
												'<\li onmouseover="$(this).css(\'background-color\'\,\'rgb(255, 216, 0)\')";" onmouseout="$(this).css(\'background-color\'\,\'#ffc900\')"><a value='+item.cid+' href="route_list.html?cid='+item.cid+'">'
														+ item.cname
														+ '</a></li>');
							});
			data = search("index/inlandRoute");
			$.each(data, function(i, item) {
				var f = 0;
				if ((i + 1) % 2 == 0) {
					$('.table2:eq(0)').append("<tr></tr>");
					f += 1;
				}
				$('.table2:eq(0) tr:eq(' + f + ')').append(tab2(item));
			});
			data = search("index/abordRoute");
			$.each(data, function(i, item) {
				var f = 0;
				if ((i + 1) % 2 == 0) {
					$('.table2:eq(1)').append("<tr></tr>");
					f += 1;
				}
				$('.table2:eq(1) tr:eq(' + f + ')').append(tab2(item));
			});
			debugger
			var hasUsername="<%=request.getSession().getAttribute("username")%>";
			if(hasUsername!=""&&hasUsername!="null"){
				$(".head>div:eq(0)").css("display","none")
				$(".head>div:eq(1)").css("display","block")
				$(".head>div:eq(1) ul>li:eq(0)>a").
				html(hasUsername);
			}
		})();
		$("#logout").click(function() {
				$.ajax({
					async : false,
					cache : true,
					url : "user/logout"
				})
			location.reload();
		})
		fordiv();
	$("#username").blur(checkUserName);
	$("#telephone").blur(checkPhoneNumber);
	$("#email").blur(checkEmail);
	$("#password").blur(checkPassword);
	$("#telephone").blur(checkPhoneNumber);
	//$("#birthday").blur(checkBirthday);
	$("#code").blur(checkCode);
		function checkCode() {
			var tag;
			if (code == "") {
				$("#code").css("border", "1px solid orange");
				$("#code").next().next().html("*验证码不能为空").css("display", "inline")
				tab= false;
			}
			var code = $("#code").val().toLowerCase();
			$.ajax({
				url:'code?method=checkCode&code='+code+'',
				async: false,
				success: function(data) {
					 if (data=="1") {
						 $("#code").css("border", "1px solid #808080");
							$("#code").next().next().css("display", "none")
							tag = true;
						} else {
							$("#code").css("border", "1px solid orange");
							$("#code").next().next().html("*验证码不正确").css("display", "inline")
							tag = false;
						}
				}
			})
			return tag;
		}

		$("#btnLog")
				.click(
						function() {
							$.ajax({
								url:"code?method=''",
								async: false,
								cache: false,
								success:function(data){
									$("#code").next().attr("src","data:image/png;base64,"+data)
								}
							})
							$('#formData>div>lable:eq(1)').next().css("display",
									"none");
							$('#formData>div>input').css("border",
									"1px solid #808080")
							$("#labBtn").html('');
							$("#innerBtn")
									.html(
											'<span>没有账号?</span>&nbsp<a id="logreg" onclick="unknownAccount()" style="color: #5cb8fd;"><u>注册</u></a>')
							$("#logreg").on("click", function() {
								unknownAccount();
							})
							$("#logreg").mouseover(function() {
								$("#logreg").css("cursor", "pointer");
							})
							showOverlay();
							$("#regInfo").hide();
							$("#btnLogin").val("登录")
						});
		function knownAcount() {
			debugger
			document.getElementById("formData").reset()
			$("#username").next().css("display", "none")
			$("#password").next().css("display", "none")
			$("#email").next().css("display", "none")
			$("#telephone").next().css("display", "none")
			$('#formData>div>input').css("border", "1px solid #808080")
			$("#regInfo").hide();
			$('#logreg>u').html("注册")
			$("#logreg").on("click", function() {
				unknownAccount();
				$("#logreg").mouseover(function() {
					$("#logreg").css("cursor", "pointer");
				})
			})
			$("#btnLogin").val("登录")
		}
		function unknownAccount() {
			document.getElementById("formData").reset()
			$("#labBtn").css("display","none")
			$("#username").next().css("display", "none")
			$("#password").next().css("display", "none")
			$("#email").next().css("display", "none")
			$("#telephone").next().css("display", "none")
			$('#formData>div>input').css("border", "1px solid #808080")
			$('#logreg>u').html("登录")
			$("#logreg").on("click", function() {
				knownAcount();
				$("#logreg").mouseover(function() {
					$("#logreg").css("cursor", "pointer");
				})
			})
			showOverlay();
			$("#btnLogin").val("注册")
			$("#regInfo").show();
		}
		$("#btnLogin,#register,#closeDialogIn").mouseover(function() {
			$("#register").css("cursor", "pointer");
			$("#closeDialogIn").css("cursor", "pointer");
			$("#btnLogin").css("cursor", "pointer");
		})
		$("#closeDialogIn").click(function() {
			$("#Logindialog").hide();
			$("#overlay").hide();
		})

		$("#regInfo").hide();
		$("#btnreg")
				.click(
						function() {
							$.ajax({
								url:"code?method=''",
								async: true,
								cache: false,
								success:function(data){
									$("#code").next().attr("src","data:image/png;base64,"+data)
								}
							})
							$('#formData>div>lable:eq(1)').next().css("display",
									"none");
							$('#formData>div>input').css("border",
									"1px solid #808080")
							$("#labBtn").html('');
							$("#innerBtn")
									.html(
											'<span>已有账号?</span>&nbsp<a id="logreg" style="color: #5cb8fd;"><u>登录</u></a>')
							$("#logreg").on("click", function() {
								knownAcount();
							})
							$("#logreg").mouseover(function() {
								$("#logreg").css("cursor", "pointer");
							})
							showOverlay();
							$("#regInfo").show();
							$("#btnLogin").val("注册")
						})

		$("#btnLogin").click(function() {
			var flag = $("#btnLogin").val();
			if (flag == "登录") {
				debugger
				var tag = checkUserName();
				var c;
				tag = checkPassword() && tag;
				c = checkCode();
				tag = c && tag;
				if(c==false){
					$.ajax({
						url:"code?method=''",
						async: true,
						cache: false,
						success:function(data){
							$("#code").next().attr("src","data:image/png;base64,"+data)
						}
					})
				}
				if (tag) {
					var data = {
						"username" : $("#username").val(),
						"password" : $("#password").val()
					}
					logAndreg("user/login", data, flag);
				}
				return;
			} else if (flag == "注册") {
				var tag = checkUserName();
				var c;
				tag = checkPassword() && tag;
				tag = checkEmail() && tag;
				tag = checkPhoneNumber() && tag;
				c=checkCode();
				tag = c && tag;
				if(c==false){
					$.ajax({
						url:"code?method=''",
						async: false,
						cache: false,
						success:function(data){
							$("#code").next().attr("src","data:image/png;base64,"+data)
						}
					})
				}
				if (tag) {
					var data = $("#formData").serialize();
					logAndreg("user/register", data, flag);
				}
			}
		})
		function search(url) {
			var result;
			$.ajax({
				async : false,
				cache : true,
				url : url,
				success : function(data) {
					result = data;
				}
			})
			return result;
		}
		function showOverlay() {
			$("#overlay").show();
			$("#Logindialog").show();
			document.getElementById("formData").reset()
		}

		function tab1(item) {
			return '<td style="width:100px;vertical-align:top;line-height:20px;position:relative;"><a value ="'+item.rid+'" href="route_detail.html?rid='+item.rid+'"  ><img style="witdh:100%;height:178px;" alt="" src="'
					+ item.rimage
					+ '"></a><p style="font-size:13px;margin-bottom:28px;">'
					+ item.rname
					+ '</p><p style="position: absolute;bottom:0;">网售价<span style="color: red;">￥<span style="font-size: 25px;">'
					+ item.price + '</span>起</span></p></td>';
		}
		function tab2(item) {
			return '<td style="width:100px;vertical-align:top;line-height:20px;position:relative;"><a value ="'+item.rid+'" href="route_detail.html?rid='+item.rid+'"  ><img style="witdh:100%;height:164px;" alt="" src="'
					+ item.rimage
					+ '"></a><p style="font-size:13px;margin-bottom:28px;">'
					+ item.rname
					+ '</p><p style="position: absolute;bottom:0;">网售价<span style="color: red;">￥<span style="font-size: 25px;">'
					+ item.price + '</span>起</span></p></td>';
		}
		function logAndreg(url, data, flag) {
			debugger
			$.ajax({
				url : url,
				dataType : "text",
				type : "POST",
				async : false,
				data : data,
				success : function(data) {
					$("#labBtn").css({
						"color" : "green"
					});
					if (data == "1") {
						if (flag == "注册"){
							$("#labBtn").css("margin-left","10px")
							$("#labBtn").fadeIn(800)
							$("#labBtn").html("注册成功,请到邮箱激活账号")
							$("#logreg").click();
							$.ajax({
								url:"code?method=''",
								async: false,
								cache: false,
								success:function(data){
									$("#code").next().attr("src","data:image/png;base64,"+data)
								}
							})
						}
						if (flag == "登录") {
							$("#labBtn").css("margin-left","80px")
							$("#labBtn").html("登陆成功")
							$("#labBtn").fadeIn(800, function() {
								// $(".myFavorite").attr("href","myFavorite.html")
								$(".head>div:eq(0)").css("display","none")
								$(".head>div:eq(1)").css("display","block")
								$(".head>div:eq(1) ul>li:eq(0)>a").
								html("<%=request.getSession().getAttribute("username")%>");
														$("#closeDialogIn")
																.click();
														location.reload();
													})
								}
							} else {
								$("#labBtn").show();
								if (flag == "登录"){
									if(data=="2"){
										$("#labBtn").html("你的账号尚未激活，请到邮箱激活后登录");
										$("#labBtn").css({
											"margin-left" : "10px"
										});
										$.ajax({
											url:"code?method=''",
											async: false,
											cache: false,
											success:function(data){
												$("#code").next().attr("src","data:image/png;base64,"+data)
											}
										})
									}
									else
										$("#labBtn").html(data)
									}
								if (flag == "注册")
									$("#labBtn").html(data)
								$("#labBtn").css({
									"color" : "red",
									"margin-left" : "10px"
								});
							}
						},
						error : function(data) {
							$("#labBtn").fadeIn(300);
							$("#labBtn").show();
							$("#labBtn").html(data)
							$("#labBtn").css({
								"color" : "red",
								"margin-left" : "10px"
							});
							return;
						}
					});
		}
		var oid;
		var flag = 1;
		var oflag;
		function mover(obj) {

			if (oflag != null)
				document.getElementById(oflag).style.background = "transparent";
			var imgid = document.getElementById("imgc");
			imgid.src = "images/banner_" + obj.id + ".jpg";
			obj.style.background = "#ffffff";

			oflag = obj.id;
		}
		function circulateimg() {

			var imgid = document.getElementById("imgc");
			imgid.src = "images/banner_" + flag + ".jpg";
			document.getElementById(flag).style.background = "#ffffff";
			if (oflag != null)
				document.getElementById(oflag).style.background = "transparent";
			oflag = flag;
			if (flag++ == 6) {
				flag = 1;
			}
		}
		window.setInterval(circulateimg, 1000);
		function fordiv() {
			var banner = document.getElementById("banner");
			var px = 700;
			for (var i = 1; i <= 6; i++) {
				var node = document.createElement("button");
				node.id = i;
				node.style.cssText = "height: 15px; background:transparent; width: 15px; border: 1px solid white; border-radius: 50%; position: absolute; left: "
						+ px + "px; top: 580px";
				node.onmousemove = function() {
					mover(this);
				}
				// node.onmouseout = function () {
				// 	mout(this);
				// }
				banner.appendChild(node);
				px += 30;
			}
			document.getElementById("1").style.background = "#ffffff";
		}
		;
	});
</script>
</head>
<div id="overlay" class="overlay" style=""></div>
<div id="Logindialog" style="border-radius: 10px;">
	<div style="height: 30px; height: 20px;">
		<a id="closeDialogIn" style="float: right;"><img alt=""
			src="images/close_1.png" style="width: 30px; height: 30px;"></a><br>
	</div>
	<div>
		<form id="formData" action=""
			style="position: relative; top: 50px; left: 80px;">
			<div>
				<label class="mark">账号:</label><input type="text" id="username"
					name="username" />&nbsp<label id="labUserName"
					style="color: orange; font-size: 13px; display: none;"></label>
			</div>
			<div>
				<label class="mark">密码:</label><input type="password" id="password"
					name="password" />&nbsp<label id="labPassWord"
					style="color: orange; font-size: 13px; display: none;"></label>
			</div>

			<div id="regInfo">
				<div>
					<label class="mark">名字:</label><input type="text" id="name"
						name="name" />&nbsp<label id=""
						style="color: orange; font-size: 13px; display: none;"></label>
				</div>
				<div>
					<label class="mark">邮箱:</label><input type="text" id="email"
						name="email" />&nbsp<label id=""
						style="color: orange; font-size: 13px; display: none;"></label>
				</div>
				<div>
					<label class="mark">手机号:</label><input type="text" id="telephone"
						name="telephone" />&nbsp<label id=""
						style="color: orange; font-size: 13px; display: none;"></label>
				</div>
				<div>
					<label class="mark">生日:</label><input type="date" id="birthday"
						name="birthday" />&nbsp<label id=""
						style="color: orange; font-size: 13px; display: none;"></label>
				</div>
				<div>
					<label class="mark">性别:</label><input type="radio" name="sex"
						value="男" style="width: 20px; height: 15px;" checked="checked" />男<input
						type="radio" name="sex" value="女"
						style="width: 20px; height: 15px;" />女&nbsp<label id=""
						style="color: orange; font-size: 13px; display: none;"></label>
				</div>
			</div>
			<div>
				<label class="mark">验证码:</label><input type="text" id="code"
					style="width: 80px; vertical-align: top;" /><img src=""
					style="display: inline-block; width: 60px;">&nbsp<label id=""
					style="color: orange; font-size: 13px; display: none; vertical-align: top;"></label>
			</div>
			<div style="margin-bottom: -1px;">
				<input type="button" id="btnLogin"
					style="height: 30px; width: 210px; border-radius: 10px; border: 1px solid #0084ff; background-color: #0084ff; color: white; font-size: 15px;"
					value="登录">
			</div>
			<div>
				<label id="labBtn" style="font-size: 13px; display: none;"></label>
			</div>
		</form>
		<div id="innerBtn"
			style="height: 50px; width: 400px; background-color: #f6f6f6; position: absolute; top: 450px; text-align: center; line-height: 50px;">

		</div>
	</div>
</div>
<body style="width: 1430px; height: 100%;">
	<div class="head" style="height: 170px; width: 100%;">
		<div>
			<ul class="ul1"
				style="list-style: none; font-size: 18px; margin-top: 20px;">
				<li><a type="button" id="btnLog" href="#">登录</a></li>
				<li><a type="button" id="btnreg" href="#">注册</a></li>
			</ul>
		</div>
		<div style="display: none;">
			<ul class="ul1"
				style="list-style: none; font-size: 18px; margin-top: 20px; margin-left: 70%;">
				<li><a type="button" href="#"></a></li>
				<li><a type="button" class="myFavorite" href="myfavorite.html" style="color: red;">我的收藏</a></li>
				<li><a id="logout" type="button" href="#"
					style="text-decoration: underline">退出</a></li>
			</ul>
		</div>
		<div style="padding-top: 50px;">
			<img alt="" src="images/logo.jpg"
				style="vertical-align: middle; margin-left: 135px; margin-right: 140px;">
			<input type="text"
				style="width: 450px; height: 37px; border: 2px solid #ffc900;">
			<button
				style="background-color: #ffc900; height: 44px; width: 90px; vertical-align: middle; border: 3px solid #ffc900; margin-left: -6px;"
				value="">搜索</button>
			<img alt="" src="images/hot_tel.jpg"
				style="vertical-align: middle; margin-left: 90px;"> <span
				style="float: right; margin-right: 100px; margin-top: 10px;">客服热线(9:00-6:00)<br>
				<span style="color: #ffc900">400-000-1111</span></span>
		</div>
		<div class="headfoot"
			style="height: 45px; width: 100%; background-color: #ffc900;">
			<ul class="ultitle"
				style="list-style: none; margin-left: 130px; margin-top: 40px; height: 100%;">
				<li><a style="" href="#">首页</a></li>
			</ul>
		</div>
	</div>

	</div>
	<div class="middleBody" style="height: 100%; width: 100%;">
		<div id="banner" style="width: 100%; overflow: hidden;">
			<img id="imgc" alt="" src="images/banner_1.jpg" style="width: 1430;">
		</div>
		<div style="margin-left: 110px; width: 1230px; margin-top: 20px;">
			<div class="title1" style="width: 100%; height: 38px;">
				<div style="margin-right: 130px;">
					<img alt="" src="images/icon_5.jpg"><span>中国精选</span>
				</div>
				<div class="tdiv" style="width: 110px; height: 38px;">
					<span
						style="display: block; -webkit-transform: skew(+30deg); text-align: center; line-height: 38px;">人气旅游</span>
				</div>
				<div class="tdiv" style="width: 110px; height: 38px;">
					<span
						style="display: block; -webkit-transform: skew(+30deg); text-align: center; line-height: 38px;">最新旅游</span>
				</div>
				<div class="tdiv" style="width: 110px; height: 38px;">
					<span
						style="display: block; -webkit-transform: skew(+30deg); text-align: center; line-height: 38px;">主题旅游</span>
				</div>
			</div>
			<hr style="border: 2px solid #ffcf1e; width: 100%;">
			<div>
				<table class="table1">
					<tr>
					</tr>
				</table>
			</div>
			<div style="margin-right: 130px; margin-top: 20px;">
				<img alt="" src="images/icon_6.jpg"><span>国内游</span>
			</div>
			<hr style="border: 2px solid #ffcf1e; width: 100%;">
			<div style="width: 100%;">
				<div style="width: 355px; float: left;">
					<img alt="" src="images/guonei_1.jpg"
						style="width: 100%; height: 520px;">
				</div>
				<div style="float: right;">
					<table class="table2"
						style="cellspacing: 0; cellpadding: 0; border: 0;">
						<tr>
						</tr>
					</table>
				</div>

			</div>
			<br>
			<div style="margin-right: 130px; clear: both;">
				<img alt="" src="images/icon_7.jpg"><span>境外游</span>

			</div>
			<hr style="border: 2px solid #ffcf1e; width: 100%;">
			<div style="width: 100%;">
				<div style="width: 355px; float: left;">
					<img alt="" src="images/jiangwai_1.jpg"
						style="width: 100%; height: 520px;">
				</div>
				<div style="float: right;">
					<table class="table2"
						style="cellspacing: 0; cellpadding: 0; border: 0;">
						<tr>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<footer>
	<div class="foot" style="height: 110px; clear: both;">
		<div class="foothead" style="height: 70px; background-color: #3d3d3f;">
			<ul class="ulf" style="padding-top: 13px;">
				<li><img alt="" src="images/icon_1.jpg"><span
					style="display: inline-block;"><span
						style="color: white; font-size: 20px;">产品齐全</span><br> <span
						style="color: #817e7b;">产品全自主买，随心买</span></span></li>
				<li><img alt="" src="images/icon_2.jpg"><span
					style="display: inline-block;"><span
						style="color: white; font-size: 20px;">便利快捷</span><br> <span
						style="color: #817e7b;">24小时不打烊，随时买</span></span></li>
				<li><img alt="" src="images/icon_3.jpg"><span
					style="display: inline-block;"><span
						style="color: white; font-size: 20px;">安全支付</span><br> <span
						style="color: #817e7b;">知名支付工具，放心买</span></span></li>
				<li><img alt="" src="images/icon_4.jpg"><span
					style="display: inline-block;"><span
						style="color: white; font-size: 20px;">贴心服务</span><br> <span
						style="color: #817e7b;">客服全年服务，安心买</span></span></li>
			</ul>
		</div>
		<div class="footend" style="background-color: #ffc900; height: 40px;">
			<span style="text-align: center; display: block; line-height: 40px;">中国驴友网股份有限公司
				版权所有Copyright 2006-2019. All Rights Reserved China IPC备00000001</span>

		</div>
	</div>
</footer>

</html>