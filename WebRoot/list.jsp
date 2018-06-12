<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="com.sun.rowset.*"%>
<%@ page import="bean.data.*"%>

<!DOCTYPE html>
<html>
	<%! String jsp="list";%>
	<!-- session -->
	<jsp:useBean id="GameRoom" class="bean.data.DataByPage_Bean"
		scope="session" />
	<jsp:useBean id="Login" class="bean.data.Login_Bean" scope="session" />

	<head>
		<link rel="stylesheet" type="text/css" href="./css/list.css" />
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
		$(document).ready(function(){
			$("ul li").click(function(){
				$(this).addClass("cur").siblings().removeClass("cur")
			});
			// 点击分页按钮
			$("button").click(function(){
				$(this).addClass("cur").siblings().removeClass("cur")
			});			
		});

	</script>
	</head>

	<body class="store-bg">
		<jsp:setProperty name="GameRoom" property="pageSize" param="PageSize" />
		<jsp:setProperty name="GameRoom" property="currentPage"
			param="currentPage" />

		<%@ include file="head.txt"%>

		<section>

		<!-- 占位符 -->
		<div class="placeholder"></div>
		<div class="placeholder"></div>
		<div class="placeholder"></div>
		<div class="placeholder"></div>
		<div class="placeholder"></div>


		<!-- 热门游戏 -->
		<div class="hot-game">


			<!-- 游戏盒子 -->
			<%
				//获取表集
				CachedRowSetImpl rowSet = GameRoom.getRowSet();//获取session中集列
				if (rowSet == null) {
					out.print("没有任何查询记录");
					return;
				}
				rowSet.last();//游标到最后一列
				int totalRecord = rowSet.getRow();//获取列数
				//out.print("<br>全部记录数" + totalRecord + "<br>");
				int pageSize = GameRoom.getPageSize();//获取每页显示记录数
				int totalPages = GameRoom.getTotalPages();//获取分页后总页数
				//计算分页后页数
				if (totalPages % pageSize == 0) {
					totalPages = totalRecord / pageSize;
				} else {
					totalPages = totalRecord / pageSize + 1;
				}

				GameRoom.setPageSize(pageSize);//每页显示记录数
				GameRoom.setTotalPages(totalPages);//分页后总页数
			%>

			<!-- 列表标题 -->
			<div class="game-list-title">
				<span class="subtit">所有游戏&nbsp;<span>共<span
						id="gameNum"><%=totalRecord %></span>款</span> </span>
				<!-- 热门标签 -->
				<div class="hot-label">
					<ul class="gameListSort">
						<table>
							<%
								String[] Typename = {"竞技", "动作", "冒险", "模拟", "角色扮演"};
								String[] Typeid = {"00", "01", "02", "03", "04"};
								for (int i = 0; i < 5; i++) {
							%>
							<td>
								<li>
									<form action="search" id="g_<%=i%>" Method='post'>
										<input type=hidden name="keywords" value="<%=Typeid[i]%>" />
										<input type=hidden name="type" value="1" />
										<a href="javascript:document:g_<%=i%>.submit();"><%=Typename[i]%></a>
									</form>
								</li>
								<%
									}
								%>
							
						</table>
					</ul>
				</div>
			</div>



			<%
				//页数大于1
				if (totalPages >= 1) {
					//页面循环
					if (GameRoom.getCurrentPage() < 1) {
						GameRoom.setCurrentPage(GameRoom.getTotalPages());
					}
					if (GameRoom.getCurrentPage() > GameRoom.getTotalPages()) {
						GameRoom.setCurrentPage(1);
					}
					//如果要显示第showPage页，那么游标应该移动到的position的值是：
					int index = (GameRoom.getCurrentPage() - 1) * pageSize + 1;
					rowSet.absolute(index);//查询位置移动到currentPage业的起始位置
					boolean boo = true;//判断是否到底
					int bStr = 0;
					for (int i = 1; i <= pageSize && boo; i++) {
						//bStr = 0;
						String id = rowSet.getString(1);//游戏id
						String name = rowSet.getString(2);//游戏名称
						String cover = rowSet.getString(3);//游戏团图片地址
						String price = rowSet.getString(4);//游戏类型
		%>

			<form action=game id="game_from_<%=id%>" method=post>
				<input type=hidden name="gameid" value="<%=id%>" />
				<a href="javascript:document:game_from_<%=id%>.submit();">
					<div class="game-box">
						<div class="gamelist">
							<div class="gameImage">
								<img src=".<%=cover%>/00.jpg" alt="">
							</div>
							<div class="gameword">
								<h3>
									<%=name%>
								</h3>
								<span class="uptime">2017-10-27 狂飙不限号</span>
								<div class="game-label">
									<a href="javascript:document:g_1.submit();">竞技</a>
									<a href="javascript:document:g_2.submit();">动作</a>
									<a href="javascript:document:g_3.submit();">冒险</a>
									<a href="javascript:document:g_4.submit();">模拟</a>
								</div>
							</div>
							<div class="gameprice">
								<h5>
									￥<%=price %>
								</h5>
							</div>
						</div>
					</div> </a>
			</form>
			<%
				//out.print(id + "  名称=" + name + "  图片地址=" + cover + "<br>");
						boo = rowSet.next();
					}
				}
			%>
		</div>


		<!-- 占位符 -->
		<div class="placeholder"></div>


		<!-- 分页 -->
		<div class="paging">
			<table>
				<tr>
					<td>
						<form action="" id="last" method=post>
							<input type="hidden" name="currentPage"
								value="<%=GameRoom.getCurrentPage() - 1%>" />
							<button onclick="javascript:document:last.submit();"
								class="btn-prev" id="turnPrev">
								&lt;&nbsp;
							</button>
						</form>
					</td>

					<%
							//根据pageCount的值显示每一页的数字并附加上相应的超链接
							for (int i = 1; i <= totalPages; i++) {
						%>

					<td>
						<form action="" id="p<%=i%>" method=post>
							<input type=hidden name='currentPage' value='<%=i%>' />
							<div class="page-num"
								onclick="javascript:document:p<%=i%>.submit();">
								&nbsp;&nbsp;&nbsp;<%=i%>&nbsp;&nbsp;&nbsp;
							</div>
						</form>
					</td>

					<%
								}
							%>

					<td>
						<form action="" id="next" method="post">
							<input type="hidden" name="currentPage"
								value="<%=GameRoom.getCurrentPage() + 1%>" />
							<button class="btn-next"
								onclick="javascript:document:next.submit();" id="turnNext">
								&gt;
							</button>
						</form>
					</td>

					<td><form action="" id="finally" method=post>
						<span class="page-go"> 第<%=GameRoom.getCurrentPage()%>页，到第<input
								type="num" name="currentPage">页 </span>
						<button onclick="javascript:document:finally.submit();"
							class="btn-confirm">
							确定
						</button>
					</form></td>
				</tr>
			</table>
			<!-- 跳转框 -->

		</div>

		<!-- 占位符 -->
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