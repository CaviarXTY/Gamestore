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
		<!-- ����ѡ�б�ɫ��jquery���� -->
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
			<!-- ����ֲ� -->
			<div class="div-ad">
				<div id="Billboards" class="carousel slide">
					<!-- �ֲ���Carousel��ָ�� -->
					<ol class="carousel-indicators">
						<li data-target="#Billboards" data-slide-to="0" class="active"></li>
						<li data-target="#Billboards" data-slide-to="1"></li>
						<li data-target="#Billboards" data-slide-to="2"></li>
					</ol>
					<!-- �ֲ���Carousel����Ŀ -->
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
					<!-- �ֲ���Carousel������ -->
					<!-- ��һҳ -->
					<a class="carousel-control left" href="#Billboards"
						data-slide="prev"></a>
					<!-- ��һҳ -->
					<a class="carousel-control right" href="#Billboards"
						data-slide="next"></a>
				</div>
			</div>
			<!-- �����Ƽ� -->
			<div class="hot-recommendation">
				<h2 class="title">
					�����Ƽ�
				</h2>
				<p class="subtit">
					�۽�������������
				</p>
			</div>


			<!-- ������Ϸ -->

			<%
			    String[] gameid  = { "0001", "0002", "0003", "0004", "0005" };
				String[] gamename = { "�Լ�", "Getting Over It with Bennett Foddy", "GTA5", "Ӣ������", "PLAYERUNKNOWN'S BATTLEGROUNDS" };
				String[] gametext = { "aaa", "bbb", "ccc", "ddd", "333" };
				String[] gameM = { "���", "�ڲ�", "���", "���", "���" };

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


			<!-- <a class="viewmore"><span class="widget-viewmore-cont">������Ϸ�Ƽ�<i class="icon icon-arr-r"></i></span></a> -->
			<div class="placeholder"></div>
			</section>
			<!-- ҳβ -->
			<footer>
			<div class="copyright">
				<p>
					COPYRIGHT &copy; 2017 - 2017 MONSTER GAME. ALL RIGHTS RESERVED.
				</p>
				<p>
					����MONSTER GAME
				</p>
			</div>
			</footer>
		</div>
	</body>
</html>