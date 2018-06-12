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
		<!-- ����ѡ�б�ɫ��jquery���� -->
		<script>
		$(document).ready(function(){
			$("ul li").click(function(){
				$(this).addClass("cur").siblings().removeClass("cur")
			});
			// �����ҳ��ť
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

		<!-- ռλ�� -->
		<div class="placeholder"></div>
		<div class="placeholder"></div>
		<div class="placeholder"></div>
		<div class="placeholder"></div>
		<div class="placeholder"></div>


		<!-- ������Ϸ -->
		<div class="hot-game">


			<!-- ��Ϸ���� -->
			<%
				//��ȡ��
				CachedRowSetImpl rowSet = GameRoom.getRowSet();//��ȡsession�м���
				if (rowSet == null) {
					out.print("û���κβ�ѯ��¼");
					return;
				}
				rowSet.last();//�α굽���һ��
				int totalRecord = rowSet.getRow();//��ȡ����
				//out.print("<br>ȫ����¼��" + totalRecord + "<br>");
				int pageSize = GameRoom.getPageSize();//��ȡÿҳ��ʾ��¼��
				int totalPages = GameRoom.getTotalPages();//��ȡ��ҳ����ҳ��
				//�����ҳ��ҳ��
				if (totalPages % pageSize == 0) {
					totalPages = totalRecord / pageSize;
				} else {
					totalPages = totalRecord / pageSize + 1;
				}

				GameRoom.setPageSize(pageSize);//ÿҳ��ʾ��¼��
				GameRoom.setTotalPages(totalPages);//��ҳ����ҳ��
			%>

			<!-- �б���� -->
			<div class="game-list-title">
				<span class="subtit">������Ϸ&nbsp;<span>��<span
						id="gameNum"><%=totalRecord %></span>��</span> </span>
				<!-- ���ű�ǩ -->
				<div class="hot-label">
					<ul class="gameListSort">
						<table>
							<%
								String[] Typename = {"����", "����", "ð��", "ģ��", "��ɫ����"};
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
				//ҳ������1
				if (totalPages >= 1) {
					//ҳ��ѭ��
					if (GameRoom.getCurrentPage() < 1) {
						GameRoom.setCurrentPage(GameRoom.getTotalPages());
					}
					if (GameRoom.getCurrentPage() > GameRoom.getTotalPages()) {
						GameRoom.setCurrentPage(1);
					}
					//���Ҫ��ʾ��showPageҳ����ô�α�Ӧ���ƶ�����position��ֵ�ǣ�
					int index = (GameRoom.getCurrentPage() - 1) * pageSize + 1;
					rowSet.absolute(index);//��ѯλ���ƶ���currentPageҵ����ʼλ��
					boolean boo = true;//�ж��Ƿ񵽵�
					int bStr = 0;
					for (int i = 1; i <= pageSize && boo; i++) {
						//bStr = 0;
						String id = rowSet.getString(1);//��Ϸid
						String name = rowSet.getString(2);//��Ϸ����
						String cover = rowSet.getString(3);//��Ϸ��ͼƬ��ַ
						String price = rowSet.getString(4);//��Ϸ����
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
								<span class="uptime">2017-10-27 ��쭲��޺�</span>
								<div class="game-label">
									<a href="javascript:document:g_1.submit();">����</a>
									<a href="javascript:document:g_2.submit();">����</a>
									<a href="javascript:document:g_3.submit();">ð��</a>
									<a href="javascript:document:g_4.submit();">ģ��</a>
								</div>
							</div>
							<div class="gameprice">
								<h5>
									��<%=price %>
								</h5>
							</div>
						</div>
					</div> </a>
			</form>
			<%
				//out.print(id + "  ����=" + name + "  ͼƬ��ַ=" + cover + "<br>");
						boo = rowSet.next();
					}
				}
			%>
		</div>


		<!-- ռλ�� -->
		<div class="placeholder"></div>


		<!-- ��ҳ -->
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
							//����pageCount��ֵ��ʾÿһҳ�����ֲ���������Ӧ�ĳ�����
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
						<span class="page-go"> ��<%=GameRoom.getCurrentPage()%>ҳ������<input
								type="num" name="currentPage">ҳ </span>
						<button onclick="javascript:document:finally.submit();"
							class="btn-confirm">
							ȷ��
						</button>
					</form></td>
				</tr>
			</table>
			<!-- ��ת�� -->

		</div>

		<!-- ռλ�� -->
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