package servlet.control;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.sun.rowset.CachedRowSetImpl;

import utils.*;
import bean.data.*;

public class Reviews_Servlet extends universal_Servlet {

	/**
	 * doPost ��������
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ���ݳ�ʼ����
		// String nid = request.getParameter("nid").trim();// ��ȡ�û�id
		//String gid = request.getParameter("gid").trim();// ��ȡ��Ϸid
		String text = new String(request.getParameter("text").getBytes("ISO8859-1"),"gb2312").trim();// ��ȡ����

		HttpSession session = request.getSession(true);// ����session����
		Login_Bean login = (Login_Bean) session.getAttribute("Login");// ��ȡҳ��
		Game_Bean game = (Game_Bean) session.getAttribute("Game");// ��ȡ��¼״̬
		//DataByPage_Bean reviews = (DataByPage_Bean) session.getAttribute("ReviewsRoom");// ��ȡ��¼״̬
		
		// ��¼���
		if (login.getLogin_case() == false) {
			windows(request, response, "���¼", "game.jsp");// ҳ��ת��
			return;
		}
		// ����û��Ƿ�ӵ�и���Ϸ
		if(game.isOwn()==false){
			windows(request, response, "��δ�������Ϸ!", "game.jsp");// �½�����
			return;
		}
		//���ݼ��
		if (text.equals("")) {
			// skipPage(request, response, "/register.jsp");
			windows(request, response, "���벻��Ϊ��!", "game.jsp");// �½�����
			return;
		}
		try {
			// ���ݿ����ӣ�����ȡ
			Sql_Utils s = new Sql_Utils();// �����������ݿ����
			s.lineDatabase("JSP");// �������ݿ�
			ResultSet jrs;

			// ��ȡ���۱�
			jrs = s.getTable("Reviews");// ��ȡ���
			jrs.last();
			int totalRecord = jrs.getRow();// ��ȡ����
			s.setWrite();// ���ñ�񼯿�д��
			/**
			 * ���ݿ�¼��
			 */
			jrs.moveToInsertRow();// �ƶ���������
			jrs.updateString(1, totalRecord + "");
			jrs.updateString(2, login.getLogin_name());
			jrs.updateString(3, game.getGameID());
			jrs.updateString(4, text);
			jrs.insertRow();// ������һ�м�¼
			
			jrs = s.getData("Reviews", "gameid", "'%"
					+ game.getGameID() + "%'", true);// ���ݿ�ģ������-����
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
			session.setAttribute("ReviewsRoom", ReviewsRoom);//���ۼ�����ˢ��
			
			s.close();// �ر����ݿ���Դ
			// request.setAttribute("register", user_Register);//
			// ��user���浽session��
			// skipPage(request, response, "/home.jsp");
			windows(request, response, "����ɹ�", "game.jsp");// �½�����

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			windows(request, response, "�������󣬷���ʧ��", "home.jsp");// �½�����
		}

	}
}
