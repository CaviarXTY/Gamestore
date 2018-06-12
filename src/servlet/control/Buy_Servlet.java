package servlet.control;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import utils.*;
import bean.data.*;

public class Buy_Servlet extends universal_Servlet {
	/**
	 * doPost �������¼�
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ݳ�ʼ����
		String Gid = request.getParameter("id").trim();// ��ȡ��Ϸid
		HttpSession session = request.getSession(true);// ����session����
		Login_Bean login = (Login_Bean) session.getAttribute("Login");// ��ȡҳ��������
		Game_Bean game = (Game_Bean) session.getAttribute("Game");// ��ȡҳ��������
		
		if (login.getLogin_case()==false) {
			windows(request, response,"�����µ�¼","game.jsp");
		} else {
			try {
				// ���ݿ����ӣ�����ȡ
				Sql_Utils s = new Sql_Utils();// �����������ݿ����
				s.lineDatabase("JSP");// �������ݿ�
				ResultSet jrs = s.getTable("Users");// ��ȡ���
				s.setWrite();// ���ñ�񼯿�д��
				while (jrs.next()) {// ������֤
					if (login.getLogin_name().equals(jrs.getString(1))) {
						String own = jrs.getString(6);
						own = own + Gid + ";";
						jrs.updateString(6, own);
						jrs.updateRow();
						game.setOwn(true);
						session.setAttribute("Game", game);// ��game���浽session��
						windows(request, response,"����ɹ�","game.jsp");//ҳ��ת
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				windows(request, response,"�����µ�¼","game.jsp");
			}
		}

	}
}
