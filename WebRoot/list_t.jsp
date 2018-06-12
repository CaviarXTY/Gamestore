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
			<!-- ͷ���� -->
			
			<jsp:setProperty name="GameRoom" property="pageSize" param="PageSize" />
			<jsp:setProperty name="GameRoom" property="currentPage"
				param="currentPage" />

			<div class="main">
				<div class="gameboxs">
					<%
						CachedRowSetImpl rowSet = GameRoom.getRowSet();//��ȡsession�м���
						if (rowSet == null) {
							out.print("û���κβ�ѯ��¼");
							return;
						}
						rowSet.last();
						int totalRecord = rowSet.getRow();
						out.print("ȫ����¼��" + totalRecord + "<br>");
						int pageSize = GameRoom.getPageSize();
						int totalPages = GameRoom.getTotalPages();
						if (totalPages % pageSize == 0) {
							totalPages = totalRecord / pageSize;
						} else {
							totalPages = totalRecord / pageSize + 1;
						}
						GameRoom.setPageSize(pageSize);//ÿҳ��ʾ��¼��
						GameRoom.setTotalPages(totalPages);//��ҳ����ҳ��

						if (totalPages >= 1) {
							if (GameRoom.getCurrentPage() < 1) {
								GameRoom.setCurrentPage(GameRoom.getTotalPages());
							}
							if (GameRoom.getCurrentPage() > GameRoom.getTotalPages()) {
								GameRoom.setCurrentPage(1);
							}
							int index = (GameRoom.getCurrentPage() - 1) * pageSize + 1; //���Ҫ��ʾ��showPageҳ����ô�α�Ӧ���ƶ�����position��ֵ�ǣ�
							rowSet.absolute(index);//��ѯλ���ƶ���currentPageҵ����ʼλ��
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
								���ࣺ����
							</h5>
						</div>
						<div class="gameprice">
							<h5>
								���
							</h5>
						</div>
					</div>
					<%
						//out.print(id + "  ����=" + name + "  ͼƬ��ַ=" + cover + "<br>");
								boo = rowSet.next();
							}
						}
					%>
				</div>
			</div>

			<br>
			ÿҳ�����ʾ<jsp:getProperty name="GameRoom" property="pageSize" />
			<br>
			��ǰ��ʾ��<jsp:getProperty name="GameRoom" property="currentPage" />ҳ�� ����<jsp:getProperty
				name="GameRoom" property="totalPages" />ҳ��

			<table>
				<tr>
					<td>
						<form action="" id="first" method=post>
							<input type=hidden name='currentPage' value='<%=1%>' />
							<a href="javascript:document:first.submit();">��ҳ</a>
						</form>


						<td>
							<form action="" id="last" method=post>
								<input type=hidden name='currentPage'
									value='<%=GameRoom.getCurrentPage() - 1%>' />
								<a href="javascript:document:last.submit();">��һҳ</a>
							</form>

							<%
								//����pageCount��ֵ��ʾÿһҳ�����ֲ���������Ӧ�ĳ�����
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
										<a href="javascript:document:next.submit();">��һҳ</a>
									</form>

									<td>
										<form action="" id="finally" method=post>
											<input type=hidden name='currentPage' value='<%=totalPages%>' />
											<a href="javascript:document:finally.submit();">ĩҳ</a>
										</form>
				</tr>
			</table>


	</body>
</html>