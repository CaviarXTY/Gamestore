<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.data.*"%>

<!DOCTYPE html>
<html>
	<%!String jsp = "user";%>
	<!-- session -->
	<jsp:useBean id="User" class="bean.data.User_Bean" scope="session" />
	<head>
		<link rel="stylesheet" type="text/css" href="./css/user.css" />
		<link rel="stylesheet"
			href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
		<link rel="stylesheet"
			href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
		<script
			src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script
			src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/loginwindows.js"></script>
		<title>steam</title>
		<!-- 控制选中变色的jquery代码 -->
		<script>
	$(document).ready(function() {
		$("ul li").click(function() {
			$(this).addClass("cur").siblings().removeClass("cur")
		});
	});
</script>
		<!-- session -->
		<jsp:useBean id="Login" class="bean.data.Login_Bean" scope="session" />

	</head>
	<body class="store-bg">
		<%@ include file="head.txt"%>
		<!-- 弹出修改密码窗口 -->
		<div class="ui-mask" id="mask3" onselectstart="return false"></div>
		<div class="ui-dialog" id="dialogMove3" onselectstart='return false;'>
			<div class="ui-dialog-title" id="dialogDrag3"
				onselectstart="return false;">
				<span> 修改密码 <a class="ui-dialog-closebutton"
					href="javascript:hideDialog3();">×</a> </span>
			</div>
			<div class="ui-dialog-content">
				<form action="changepw" id="changepw_form" Method="post">

					<div class="ui-dialog-l40 ui-dialog-pt15">
						<input name="oldpw"
							class="ui-dialog-input ui-dialog-input-password" type="password"
							placeholder="请输入旧密码" value="" />
					</div>
					<div class="ui-dialog-l40 ui-dialog-pt15">
						<input name="newpw"
							class="ui-dialog-input ui-dialog-input-password" type="password"
							placeholder="请输入新密码" value="" />
					</div>
					<div class="ui-dialog-l40 ui-dialog-pt15">
						<input name="newpw2"
							class="ui-dialog-input ui-dialog-input-password" type="password"
							placeholder="请确认新密码" value="" />
					</div>
					<div class="ui-dialog-submit">
						<a href="javascript:document:changepw_form.submit();">确认修改</a>
					</div>
				</form>
			</div>
		</div>
		<section>
		<div class="placeholder"></div>
		<div class="placeholder"></div>
		<div class="placeholder"></div>
		<div class="placeholder"></div>
		<div class="placeholder"></div>
		<div class="placeholder"></div>

		<!-- 游戏详情 -->
		<div class="game-detail">
			<div class="side-bar">
				<div class="avatar">
					<img src="./image/avatar/linwene.png">
				</div>
				<div class="user-name">
					<b><jsp:getProperty name="User" property="userID" /></b>
				</div>
				<!-- 修改密码 -->
				<div class="change-pw">
					<!-- <em class="tx-em">25</em> -->
					<div class="btn-change">
						<a href="javascript:showDialog3();"><span>修改密码</span> </a>
					</div>
				</div>
			</div>
			<div class="img-box">
				<img width="600" height="338" src="./image/user-ui-img.jpg">
			</div>
		</div>

		<!-- 热门推荐 -->
		<div class="hot-recommendation">
			<h2 class="title">
				我的游戏
			</h2>
			<p class="subtit">
				已拥有
				<span id="gameNum"><%=User.getOwnNum()%></span>个游戏
			</p>
		</div>

		<!-- 拥有游戏 -->
		<div class="hot-game">
			<%
				LinkedList own = User.getUserBought();
				if (own == null) {
			%>
			<div class="game-box">
				<div class="gamelist">
					<div class="gameImage">
						<img src="./image/games/nfs.jpg" alt="">
					</div>
					<div class="gameword">
						<h3>
							Null
						</h3>
					</div>
				</div>
			</div>

			<%
				} else {
					Iterator<String> iterator = own.iterator();
					while (iterator.hasNext()) {
						String id = iterator.next();
			%>
			<form action=game id="game_from_<%=id%>" method=post>
				<input type=hidden name="gameid" value="<%=id%>" />
				<a href="javascript:document:game_from_<%=id%>.submit();">
					<div class="game-box">
						<div class="gamelist">
							<div class="gameImage">
								<img src="./image/game/<%=id%>/00.jpg" alt="">
							</div>
							<div class="gameword">
								<h3>
									<%=id%>
								</h3>
								<span class="uptime">2017-10-27 狂飙不限号</span>
								<div class="game-label">
									<a href="">赛车</a>
									<a href="">竞赛</a>
									<a href="">实时对决</a>
									<a href="">速度激情</a>
								</div>
							</div>
							<div class="gameprice">
								<h5>
									免费
								</h5>
							</div>
						</div>
					</div> </a>
			</form>
			<%
				}
				}
			%>

		</div>

		<!-- <a class="viewmore"><span class="widget-viewmore-cont">更多游戏推荐<i class="icon icon-arr-r"></i></span></a> -->
		<div class="placeholder"></div>
		</section>
		<!-- 页尾 -->
		<footer>
		<div class="copyright">
			<p>
				COPYRIGHT &copy; 2017 - 2017 MONSTER GAME. ALL RIGHTS RESERVED.
			</p>
			<p>
				关于MONSTER GAME
			</p>
		</div>
		</footer>
		</div>
	</body>
</html>