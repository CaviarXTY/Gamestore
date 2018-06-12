package servlet.control;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.sun.rowset.CachedRowSetImpl;

import utils.*;
import bean.data.*;

public class Game_Servlet extends universal_Servlet {
	public Sql_Utils s = new Sql_Utils();// �����������ݿ����
	public ResultSet jrs;// ���ݿ�back

	/**
	 * doPost
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// tip(request, response,"type="+request.getParameter("type"));//����

		// ���ݳ�ʼ����
		HttpSession session = request.getSession(true);// ����session����
		String number = request.getParameter("gameid").trim();// ��ȡ����
		Login_Bean login = (Login_Bean) session.getAttribute("Login");// ��ȡ��¼״̬

		Game_Bean game = new Game_Bean();// ��������ģ��
		// windows(request,response,number,"/home.jsp");
		try {
			// ���ݿ����ӣ�����ȡ
			Sql_Utils s = new Sql_Utils();// �����������ݿ����
			s.lineDatabase("JSP");// �������ݿ�

			// ����Ƿ���ӵ��
			if (login.getLogin_case() == true) {
				jrs = s.getData("Users", "own", "'%" + number + "%'", true);// ���ݿ�ģ������-��Ϸ
				while (jrs.next()) {
					if (login.getLogin_name().equals(jrs.getString(1)))
						game.setOwn(true);
					else
						game.setOwn(false);
				}
			}

			// ��Ϸ���ݴ���
			jrs = s.getTable("Games");// ��׼���ݲ�ѯ
			while (jrs.next()) {
				if (number.equals(jrs.getString(1))) {
					// windows(request,response,"name="+jrs.getString(2),"/home.jsp");
					game.setGameID(jrs.getString(1));// ��Ϸid
					game.setGameName(jrs.getString(2));// ��Ϸ����
					game.setGameCover(jrs.getString(3));// ͼƬ·��
					game.setGamePrice(jrs.getFloat(4));// ��Ϸ�۸�
					game.setGamePublisher(jrs.getString(6));// ��Ϸ������
					game.setGameSystemRequirements(jrs.getString(7));// ��Ϸ����
					game.setGameText(jrs.getString(8));// ��Ϸ��������
					game.setReleaseDate(jrs.getString(9));// ����ʱ��
					session.setAttribute("Game", game);// ��game���浽session��
					// skipPage(request, response,"/game.jsp");//ҳ��ת��
					// s.close();

					// �������ݴ���
					jrs = s.getData("Reviews", "gameid", "'%"
							+ game.getGameID() + "%'", true);// ���ݿ�ģ������-����
					/**
					 * ������ȡ
					 */
					//	response.sendRedirect("game.jsp");// �ݴ����
					// ResultSetMetaData metaData =jrs.getMetaData();
					// int columnNum = metaData.getColumnCount();
					CachedRowSetImpl rowSet = null;// �������ȫ����¼���м�����
					DataByPage_Bean ReviewsRoom = null;// ����Bean����
					try {
						ReviewsRoom = (DataByPage_Bean) session
								.getAttribute("ReviewsRoom");// ��ȡ���ݶ���
						if (ReviewsRoom == null) {
							ReviewsRoom = new DataByPage_Bean();// ����JavaBean����
							session.setAttribute("ReviewsRoom", ReviewsRoom);// ����ssionse
						}
					} catch (Exception exp) {
						ReviewsRoom = new DataByPage_Bean();// ����JavaBean����
						session.setAttribute("ReviewsRoom", ReviewsRoom);
					}
					rowSet = new CachedRowSetImpl();
					rowSet.populate(jrs);
					ReviewsRoom.setRowSet(rowSet);
					session.setAttribute("Game", game);// ��game���浽session��
					session.setAttribute("ReviewsRoom", ReviewsRoom);
					s.close();// �ر���Դ
					if (jrs == null)
						response.sendRedirect("game.jsp");// �ݴ����
					else
						skipPage(request, response, "game.jsp");// ҳ��ת��
					return;
				}
			}
			// jrs = s.getData("Games", "id", "'%" + number + "%'", true);//
			// ���ݿ�ģ������-����
			// game.setGameID(jrs.getString(1));// ��Ϸid
			// game.setGameName(jrs.getString(2));// ��Ϸ����
			// game.setGameCover(jrs.getString(3));// ͼƬ·��
			// game.setGamePrice(jrs.getFloat(4));// ��Ϸ�۸�
			// game.setGamePublisher(jrs.getString(6));// ��Ϸ������
			// game.setGameSystemRequirements(jrs.getString(7));// ��Ϸ����
			// game.setGameText(jrs.getString(8));// ��Ϸ��������
			// game.setReleaseDate(jrs.getString(9));// ����ʱ��
			// request.setAttribute("Game", game);// ��game���浽session��

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			windows(request, response, number, "home.jsp");// �½�����
			// windows(request, response, "����ʧ��", "home.jsp");// �½�����
		}catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			windows(request, response,"����ʧ��","home.jsp");
		}
	}
}
