<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="com.sun.rowset.*"%>
<%@ page import="bean.data.*"%>

<html>
	<%!String jsp = "game";%>
	<!-- session -->
	<jsp:useBean id="ReviewsRoom" class="bean.data.DataByPage_Bean"
		scope="session" />
	<jsp:useBean id="Login" class="bean.data.Login_Bean" scope="session" />
	<jsp:useBean id="Game" class="bean.data.Game_Bean" scope="session" />

	<head>
		<input name="jsp" type="hidden" value="game" />
		<link rel="stylesheet" type="text/css" href="./css/detail.css" />
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
	</head>
	<body class="store-bg">

		<jsp:setProperty name="ReviewsRoom" property="pageSize"
			param="PageSize" />
		<jsp:setProperty name="ReviewsRoom" property="currentPage"
			param="currentPage" />

		<%@ include file="head.txt"%>


		<div class="placeholder"></div>
		<section>
		<div class="placeholder"></div>
		<div class="placeholder"></div>
		<div class="placeholder"></div>
		<div class="placeholder"></div>
		<div class="placeholder"></div>

		<!-- 游戏详情 -->
		<div class="game-detail">
			<p class="game-name">
				<jsp:getProperty name="Game" property="gameName" />
			</p>
			<div class="img-box">
				<img width="600" height="338" src=".<%=Game.getGameCover()%>00.jpg">
			</div>
			<div class="side-bar">
				<div class="detail-info">
					<p>
						<jsp:getProperty name="Game" property="gameText" />
					</p>
					<div class="game-prop">
						<ul class="game-prop-table">
							<li>
								<div class="tit">
									<span>中文名</span>
								</div>
								<div class="cont">
									<span><jsp:getProperty name="Game" property="gameName" /></span>
								</div>
							</li>
							<li>
								<div class="tit">
									<span>开发商</span>
								</div>
								<div class="cont">
									<span><jsp:getProperty name="Game"
											property="gamePublisher" /></span>
								</div>
							</li>
							<li>
								<div class="tit">
									<span>发行商</span>
								</div>
								<div class="cont">
									<span><jsp:getProperty name="Game"
											property="gamePublisher" /></span>
								</div>
							</li>
							<li>
								<div class="tit">
									<span>发行日期</span>
								</div>
								<div class="cont">
									<span><jsp:getProperty name="Game"
											property="releaseDate" /></span>
								</div>
							</li>
						</ul>
					</div>
					<div class="game-label">
						<a href="">开放世界</a>
						<a href="">沙盒</a>
						<a href="">荒野徒步</a>
						<a href="">单机</a>
					</div>
				</div>
				<!-- 购买按钮 -->
				<div class="game-pay">
					<%
						if (Game.isOwn() == true) {
					%>
					<em class="tx-em">已拥有</em>
					<%
						} else {
					%>
					<em class="tx-em">￥<jsp:getProperty name="Game"
							property="gamePrice" /></em>
					<%
						}
					%>
					<div class="btn-pay">
						<form action="buy" id="buy_form" method=post>
							<input name="id" type="hidden" value="<%=Game.getGameID()%>" />
							<%
								if (Game.isOwn() == true) {
							%>
							<a href="game.exe"><span onclick="">下载</span>
							</a>
							<%
								} else {
							%>
							<span onclick="javascript: document: buy_form.submit();">立即购买</span>
							<%
								}
							%>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- 评论标题 -->
		<div class="comment-label">
			<h2 class="title">
				用户评论
			</h2>
			<p class="subtit">
				您的声音对我们至关重要
			</p>
		</div>
		<!-- 评论 -->
		<div class="comment">
			<!-- 评论框 -->
			<div class="comment-box">
				<!-- 输入框 -->
				<div class="input-box">
					<form action="reviews" id="send_from" method=post>
						<textarea name="text" placeholder="来说说你对核心玩法，游戏画面，背景剧情等方面的看法吧..."></textarea>
						<!-- 发送按钮 -->
						<div class="btn-send">
							<i class="notice-ico"></i> 您的评论对游戏很重要，无意义的谩骂可能会被删除。
							<span onclick="javascript:document:send_from.submit();">发送</span>
						</div>
					</form>
				</div>
			</div>

			<!-- 所有评论 -->
			<div class="all-comment">
				<!-- 讨论标签 -->
				<div class="discuss-label">
					所有评论
				</div>
				<!-- 详细评论 -->
				<%
					//获取表集
					CachedRowSetImpl rowSet = ReviewsRoom.getRowSet();//获取session中集列
					if (rowSet == null) {
						out.print("没有任何查询记录");
						return;
					}
					rowSet.last();//游标到最后一列
					int totalRecord = rowSet.getRow();//获取列数
					out.print("全部记录数" + totalRecord + "<br>");
					int pageSize = ReviewsRoom.getPageSize();//获取每页显示记录数
					int totalPages = ReviewsRoom.getTotalPages();//获取分页后总页数
					//计算分页后页数
					if (totalPages % pageSize == 0) {
						totalPages = totalRecord / pageSize;
					} else {
						totalPages = totalRecord / pageSize + 1;
					}

					ReviewsRoom.setPageSize(pageSize);//每页显示记录数
					ReviewsRoom.setTotalPages(totalPages);//分页后总页数

					//页数大于1
					if (totalPages >= 1) {
						//页面循环
						if (ReviewsRoom.getCurrentPage() < 1) {
							ReviewsRoom.setCurrentPage(ReviewsRoom.getTotalPages());
						}
						if (ReviewsRoom.getCurrentPage() > ReviewsRoom.getTotalPages()) {
							ReviewsRoom.setCurrentPage(1);
						}
						//如果要显示第showPage页，那么游标应该移动到的position的值是：
						int index = (ReviewsRoom.getCurrentPage() - 1) * pageSize + 1;
						rowSet.absolute(index);//查询位置移动到currentPage业的起始位置
						boolean boo = true;//判断是否到底
						int bStr = 0;
						for (int i = 1; i <= pageSize && boo; i++) {
							//bStr = 0;
							String id = rowSet.getString(1);//评论id
							String userid = rowSet.getString(2);//用户id
							String gameid = rowSet.getString(3);//游戏id
							String text = rowSet.getString(4);//评论内容
				%>
				<div class="detail-comment-box">
					<div class="avatar-box">
						<img src="./image/avatar/linwene.png">
						<b><%=userid%></b>
					</div>
					<div class="content">
						<p>
							<%=text%>
						</p>
						<div class="time-box">
							<time>
							Date
							</time>
						</div>
					</div>
				</div>

				<%
					//out.print(id + "  名称=" + name + "  图片地址=" + cover + "<br>");
							boo = rowSet.next();
						}
					}
				%>

			</div>
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