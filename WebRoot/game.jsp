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
		<!-- ����ѡ�б�ɫ��jquery���� -->
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

		<!-- ��Ϸ���� -->
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
									<span>������</span>
								</div>
								<div class="cont">
									<span><jsp:getProperty name="Game" property="gameName" /></span>
								</div>
							</li>
							<li>
								<div class="tit">
									<span>������</span>
								</div>
								<div class="cont">
									<span><jsp:getProperty name="Game"
											property="gamePublisher" /></span>
								</div>
							</li>
							<li>
								<div class="tit">
									<span>������</span>
								</div>
								<div class="cont">
									<span><jsp:getProperty name="Game"
											property="gamePublisher" /></span>
								</div>
							</li>
							<li>
								<div class="tit">
									<span>��������</span>
								</div>
								<div class="cont">
									<span><jsp:getProperty name="Game"
											property="releaseDate" /></span>
								</div>
							</li>
						</ul>
					</div>
					<div class="game-label">
						<a href="">��������</a>
						<a href="">ɳ��</a>
						<a href="">��Ұͽ��</a>
						<a href="">����</a>
					</div>
				</div>
				<!-- ����ť -->
				<div class="game-pay">
					<%
						if (Game.isOwn() == true) {
					%>
					<em class="tx-em">��ӵ��</em>
					<%
						} else {
					%>
					<em class="tx-em">��<jsp:getProperty name="Game"
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
							<a href="game.exe"><span onclick="">����</span>
							</a>
							<%
								} else {
							%>
							<span onclick="javascript: document: buy_form.submit();">��������</span>
							<%
								}
							%>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- ���۱��� -->
		<div class="comment-label">
			<h2 class="title">
				�û�����
			</h2>
			<p class="subtit">
				��������������������Ҫ
			</p>
		</div>
		<!-- ���� -->
		<div class="comment">
			<!-- ���ۿ� -->
			<div class="comment-box">
				<!-- ����� -->
				<div class="input-box">
					<form action="reviews" id="send_from" method=post>
						<textarea name="text" placeholder="��˵˵��Ժ����淨����Ϸ���棬��������ȷ���Ŀ�����..."></textarea>
						<!-- ���Ͱ�ť -->
						<div class="btn-send">
							<i class="notice-ico"></i> �������۶���Ϸ����Ҫ���������á����ܻᱻɾ����
							<span onclick="javascript:document:send_from.submit();">����</span>
						</div>
					</form>
				</div>
			</div>

			<!-- �������� -->
			<div class="all-comment">
				<!-- ���۱�ǩ -->
				<div class="discuss-label">
					��������
				</div>
				<!-- ��ϸ���� -->
				<%
					//��ȡ��
					CachedRowSetImpl rowSet = ReviewsRoom.getRowSet();//��ȡsession�м���
					if (rowSet == null) {
						out.print("û���κβ�ѯ��¼");
						return;
					}
					rowSet.last();//�α굽���һ��
					int totalRecord = rowSet.getRow();//��ȡ����
					out.print("ȫ����¼��" + totalRecord + "<br>");
					int pageSize = ReviewsRoom.getPageSize();//��ȡÿҳ��ʾ��¼��
					int totalPages = ReviewsRoom.getTotalPages();//��ȡ��ҳ����ҳ��
					//�����ҳ��ҳ��
					if (totalPages % pageSize == 0) {
						totalPages = totalRecord / pageSize;
					} else {
						totalPages = totalRecord / pageSize + 1;
					}

					ReviewsRoom.setPageSize(pageSize);//ÿҳ��ʾ��¼��
					ReviewsRoom.setTotalPages(totalPages);//��ҳ����ҳ��

					//ҳ������1
					if (totalPages >= 1) {
						//ҳ��ѭ��
						if (ReviewsRoom.getCurrentPage() < 1) {
							ReviewsRoom.setCurrentPage(ReviewsRoom.getTotalPages());
						}
						if (ReviewsRoom.getCurrentPage() > ReviewsRoom.getTotalPages()) {
							ReviewsRoom.setCurrentPage(1);
						}
						//���Ҫ��ʾ��showPageҳ����ô�α�Ӧ���ƶ�����position��ֵ�ǣ�
						int index = (ReviewsRoom.getCurrentPage() - 1) * pageSize + 1;
						rowSet.absolute(index);//��ѯλ���ƶ���currentPageҵ����ʼλ��
						boolean boo = true;//�ж��Ƿ񵽵�
						int bStr = 0;
						for (int i = 1; i <= pageSize && boo; i++) {
							//bStr = 0;
							String id = rowSet.getString(1);//����id
							String userid = rowSet.getString(2);//�û�id
							String gameid = rowSet.getString(3);//��Ϸid
							String text = rowSet.getString(4);//��������
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
					//out.print(id + "  ����=" + name + "  ͼƬ��ַ=" + cover + "<br>");
							boo = rowSet.next();
						}
					}
				%>

			</div>
		</div>
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