package servlet.control;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import utils.*;
import bean.data.*;

public class Register_Servlet extends universal_Servlet {

	/**
	 * doPost �����¼�¼�
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ���ݳ�ʼ����
		String Jid = request.getParameter("id").trim();// ��ȡҳ��id
		String Jpw = request.getParameter("pwd").trim();// ��ȡҳ��pw
		String Jpw2 = request.getParameter("pwd2").trim();// ��ȡҳ��pw2
		String Jem = request.getParameter("em").trim();// ��ȡҳ��em
		// ����ģ�ʹ���
		Register_Bean user_Register = new Register_Bean();// ����User�û�����
//		request.setAttribute("register", user_Register);// ��user���浽session��
		user_Register.setUserID(Jid);
		user_Register.setUserPassword(Jpw);
		user_Register.setUserEmil(Jem);
		try {
			// ���ݿ����ӣ�����ȡ
			Sql_Utils s = new Sql_Utils();// �����������ݿ����
			s.lineDatabase("JSP");// �������ݿ�
			// ���ݼ��
			if (Jid.equals("") || Jpw.equals("") || Jpw2.equals("")|| Jem.equals("")) {
				//skipPage(request, response, "/register.jsp");
				windows(request, response,"���벻��Ϊ��!","home.jsp");//�½�����
				return;
			} else {
				ResultSet jrs = s.getTable("Users");// ��ȡ���
				s.setWrite();//���ñ�񼯿�д��
				while (jrs.next()) {// ������֤
					if(Jid.equals(jrs.getString(1))){//�˺��Ƿ��ظ�
						jrs.close();// �ر����ݿ���Դ
						request.setAttribute("register", user_Register);// ��user���浽session��
						//skipPage(request, response, "/register.jsp");
						windows(request, response,"�˺��Ѵ��ڣ�������������","home.jsp");//�½�����
						//newPage(request, response,"<script type='text/javascript'>alert('�˺Ż��������');window.location.href='home.jsp';javascript:showDialog();</script>");
						return;
					}
				}
					// ����ȷ��
					if (Jpw2.equals(Jpw)) {
						/**
						 * ���ݿ�¼��
						 */
						jrs.moveToInsertRow();// �ƶ���������
						jrs.updateString(1, Jid);
						jrs.updateString(2, Jpw);
						jrs.updateFloat(3, 0);
						jrs.updateString(4, Jem);
						jrs.insertRow();// ������һ�м�¼
						jrs.close();// �ر����ݿ���Դ
						//request.setAttribute("register", user_Register);// ��user���浽session��
						//skipPage(request, response, "/home.jsp");
						windows(request, response,"ע��ɹ�","home.jsp");//�½�����
						return;
					} else {
						jrs.close();// �ر����ݿ���Դ
						request.setAttribute("register", user_Register);// ��user���浽session��
						windows(request, response,"�����������벻����","home.jsp");//�½�����
						//skipPage(request, response, "/register.jsp");
						return;
					}
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			windows(request, response,"��������ע��ʧ��","home.jsp");//�½�����
		}
	}
}
