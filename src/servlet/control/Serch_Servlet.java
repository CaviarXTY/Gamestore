package servlet.control;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.sun.rowset.*;

import utils.*;
import bean.data.*;

public class Serch_Servlet extends universal_Servlet {

	/**
	 * doPost
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**type(0-ȫ����1-���࣬2-����)
		 * "ȫ��","����","ð��","ģ��","��ɫ����","����","����","����","���" 
		 * "00", "01", "02","03",  "04", "05", "06", "07","08"
		 */
		// ���ݳ�ʼ����
		String keywords = request.getParameter("keywords").trim();// ��ȡҳ��������
		String type = request.getParameter("type").trim();// ��ȡҳ���������(0-ȫ����1-���࣬2-����)
		
		// ���ݿ����ӣ�����ȡ
		Sql_Utils s = new Sql_Utils();// �����������ݿ����
		s.lineDatabase("JSP");// �������ݿ�
		ResultSet jrs = null;// ����ָ����

		/**
		 * ���ݼ��
		 */
		if (type.equals("0"))
			jrs = s.getTable("Games");// ���ݿ�����-����
		else if (type.equals("1"))
			jrs = s.getData("Games", "type", "'%" + keywords + "%'", true);// ���ݿ�ģ������-����
		else if (type.equals("2")) {
			if (keywords.equals(""))
				windows(request, response, "��������Ϊ��", "");// ������ʾ
			else
				jrs = s.getData("Games", "name", "'%" + keywords + "%'",
						true);// ���ݿ�ģ������-����
		} else {
			// type="0";
			jrs = s.getTable("Game");// ���ݿ�����-����
		}
		
		/**
		 * ������ȡ
		 */
		try {
			// ResultSetMetaData metaData =jrs.getMetaData();
			// int columnNum = metaData.getColumnCount();
			HttpSession session = request.getSession(true);//����session����
			CachedRowSetImpl rowSet = null;// �������ȫ����¼���м�����
			DataByPage_Bean GameRoom = null;//����Bean����
			try {
				GameRoom = (DataByPage_Bean) session.getAttribute("GameRoom");//��ȡ���ݶ���
				if (GameRoom == null) {
					GameRoom = new DataByPage_Bean();// ����JavaBean����
					session.setAttribute("GameRoom", GameRoom);//����ssionse
				}
			} catch (Exception exp) {
				GameRoom = new DataByPage_Bean();// ����JavaBean����
				session.setAttribute("GameRoom", GameRoom);
			}
			rowSet = new CachedRowSetImpl();
			rowSet.populate(jrs);
			GameRoom.setRowSet(rowSet);
			session.setAttribute("GameRoom", GameRoom);
			s.close();// �ر���Դ
			if (jrs == null)
				response.sendRedirect("home.jsp");//�ݴ����
			else
				response.sendRedirect("list.jsp");//ҳ����ת

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			windows(request, response, "����ʧ��", "home.jsp");// �½�����
		}
	}

}
