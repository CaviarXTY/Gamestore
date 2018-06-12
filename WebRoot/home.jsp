<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.sql.*"%>
<%@ page import="bean.data.*"%>

<!DOCTYPE html>
<%! String jsp="home";%>
<html>
	<!-- session -->
	<jsp:useBean id="Login" class="bean.data.Login_Bean" scope="session" />

	<head>
		<link rel="stylesheet" type="text/css" href="./css/index.css" />
		<link rel="stylesheet"
			href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
		<link rel="stylesheet"
			href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
		<scrip
			src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js">
		</script>
		<script
			src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/loginwindows.js"></script>
		<title>steam</title>
		<!-- 控制选中变色的jquery代码 -->
		<script>
		#(document).ready(function(){
			$("ul li").click(function(){
				$(this).addClass("cur").siblings().removeClass("cur")
			});
		});
	</script>
	</head>

	<body class="store-bg">
		<%@ include file="head.txt"%>

			<section>
			<!-- 广告轮播 -->
			<div class="div-ad">
				<div id="Billboards" class="carousel slide">
					<!-- 轮播（Carousel）指标 -->
					<ol class="carousel-indicators">
						<li data-target="#Billboards" data-slide-to="0" class="active"></li>
						<li data-target="#Billboards" data-slide-to="1"></li>
						<li data-target="#Billboards" data-slide-to="2"></li>
					</ol>
					<!-- 轮播（Carousel）项目 -->
					<div class="carousel-inner">
						<div class="item active">
							<a href="http://www.baidu.com"><img src="./image/game1.jpg"
									alt="game1"> </a>
						</div>
						<div class="item">
							<a href="http://act.tgp.qq.com/danji/xjqx20171212/index.html"><img
									src="./image/game2.jpg" alt="game2"> </a>
						</div>
						<div class="item">
							<a href="http://dnf.qq.com"><img src="./image/game3.jpg"
									alt="game3"> </a>
						</div>
					</div>
					<!-- 轮播（Carousel）导航 -->
					<!-- 上一页 -->
					<a class="carousel-control left" href="#Billboards"
						data-slide="prev"></a>
					<!-- 下一页 -->
					<a class="carousel-control right" href="#Billboards"
						data-slide="next"></a>
				</div>
			</div>
			<!-- 热门推荐 -->
			<div class="hot-recommendation">
				<h2 class="title">
					热门推荐
				</h2>
				<p class="subtit">
					聚焦近期热门新游
				</p>
			</div>


			<!-- 热门游戏 -->

			<%
			    String[] gameid  = { "0001", "0002", "0003", "0004", "0005" };
				String[] gamename = { "吃鸡", "Getting Over It with Bennett Foddy", "GTA5", "英雄联盟", "PLAYERUNKNOWN'S BATTLEGROUNDS" };
				String[] gametext = { "aaa", "bbb", "ccc", "ddd", "333" };
				String[] gameM = { "免费", "内测", "免费", "免费", "免费" };

				for (int i = 0; i < 5; i++) {
			%>
			<form action=game id="game_from_<%=i%>" method=post>
				<input type=hidden name="gameid" value="<%=gameid[i]%>" />
				<a href="javascript:document:game_from_<%=i%>.submit();">
					<div class="hot-game">
						<div class="game-box">
							<div class="gamelist">
								<div class="gameImage">
									<img src="./image/game/<%=gameid[i]%>/00.jpg" alt="">
								</div>
								<div class="gameword">
									<h3>
										<%=gamename[i]%>
									</h3>
									<span class="uptime"><%=gametext[i]%></span>

								</div>
								<div class="gameprice">
									<h5>
										<%=gameM[i]%>
									</h5>
								</div>
							</div>
						</div>
					</div> </a>
			</form>
			<%
				}
			%>


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