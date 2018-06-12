<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="bean.data.*"%>
<%@ page import="com.sun.rowset.*"%>
<%!Login_Bean user;%>

<html>
	<jsp:useBean id="GameRoom" class="bean.data.DataByPage_Bean"
		scope="session" />
	<jsp:useBean id="Login" class="bean.data.Login_Bean" scope="request" /><!-- session -->
	<head>
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<script src='css/jquery.js'></script>
		<script type="text/javascript" src="js/windows.js"></script>
		<script type="text/javascript" src="js/loginwindows.js"></script>
		<title>Store</title>
	</head>

	<body>
		<div id="container">
			<!-- 头部栏 -->
			
			<jsp:setProperty name="GameRoom" property="pageSize" param="PageSize" />
			<jsp:setProperty name="GameRoom" property="currentPage"
				param="currentPage" />

			<div class="main">
				<div class="gameboxs">
					<%
						CachedRowSetImpl rowSet = GameRoom.getRowSet();//获取session中集列
						if (rowSet == null) {
							out.print("没有任何查询记录");
							return;
						}
						rowSet.last();
						int totalRecord = rowSet.getRow();
						out.print("全部记录数" + totalRecord + "<br>");
						int pageSize = GameRoom.getPageSize();
						int totalPages = GameRoom.getTotalPages();
						if (totalPages % pageSize == 0) {
							totalPages = totalRecord / pageSize;
						} else {
							totalPages = totalRecord / pageSize + 1;
						}
						GameRoom.setPageSize(pageSize);//每页显示记录数
						GameRoom.setTotalPages(totalPages);//分页后总页数

						if (totalPages >= 1) {
							if (GameRoom.getCurrentPage() < 1) {
								GameRoom.setCurrentPage(GameRoom.getTotalPages());
							}
							if (GameRoom.getCurrentPage() > GameRoom.getTotalPages()) {
								GameRoom.setCurrentPage(1);
							}
							int index = (GameRoom.getCurrentPage() - 1) * pageSize + 1; //如果要显示第showPage页，那么游标应该移动到的position的值是：
							rowSet.absolute(index);//查询位置移动到currentPage业的起始位置
							boolean boo = true;
							for (int i = 1; i <= pageSize && boo; i++) {
								String id = rowSet.getString(1);
								String name = rowSet.getString(2);
								String cover = rowSet.getString(3);
					%>
					<div class="gamelist">
						<div class="gameImage">
							<img src=<%="'" + cover + "'"%> alt="">s

						</div>
						<div class="gameword">
							<h4>
								<%=name%>
							</h4>
							<h5>
								分类：竞速
							</h5>
						</div>
						<div class="gameprice">
							<h5>
								免费
							</h5>
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

			<br>
			每页最多显示<jsp:getProperty name="GameRoom" property="pageSize" />
			<br>
			当前显示第<jsp:getProperty name="GameRoom" property="currentPage" />页， 共有<jsp:getProperty
				name="GameRoom" property="totalPages" />页。

			<table>
				<tr>
					<td>
						<form action="" id="first" method=post>
							<input type=hidden name='currentPage' value='<%=1%>' />
							<a href="javascript:document:first.submit();">首页</a>
						</form>


						<td>
							<form action="" id="last" method=post>
								<input type=hidden name='currentPage'
									value='<%=GameRoom.getCurrentPage() - 1%>' />
								<a href="javascript:document:last.submit();">上一页</a>
							</form>

							<%
								//根据pageCount的值显示每一页的数字并附加上相应的超链接
								for (int i = 1; i <= totalPages; i++) {
							%>

							<td>
								<form action="" id="p<%=i%>" method=post>
									<input type=hidden name='currentPage' value='<%=i%>' />
									<a href="javascript:document:p<%=i%>.submit();"><%=i%></a>
								</form>

								<%
									}
								%>


								<td>
									<form action="" id="next" method=post>
										<input type=hidden name='currentPage'
											value='<%=GameRoom.getCurrentPage() + 1%>' />
										<a href="javascript:document:next.submit();">下一页</a>
									</form>

									<td>
										<form action="" id="finally" method=post>
											<input type=hidden name='currentPage' value='<%=totalPages%>' />
											<a href="javascript:document:finally.submit();">末页</a>
										</form>
				</tr>
			</table>


	</body>
</html>