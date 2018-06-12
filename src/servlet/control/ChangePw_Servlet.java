package servlet.control;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import utils.*;
import bean.data.*;

public class ChangePw_Servlet extends universal_Servlet {
	/**
	 * doPost
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ݳ�ʼ����
		HttpSession session = request.getSession(true);// ����session����
		Login_Bean login = (Login_Bean) session.getAttribute("Login");// ��ȡҳ��������
		
		String Old = request.getParameter("oldpw").trim();// ��ȡ������
		String New = request.getParameter("newpw").trim();// ��ȡ������
		String New2 = request.getParameter("newpw2").trim();// ȷ��������

		if (login == null) {
			windows(request, response, "�����µ�¼", "home.jsp");// �½�����
		} else {
			if(New.equals(New2)){
				try {
					// ���ݿ����ӣ�����ȡ
					Sql_Utils s = new Sql_Utils();// �����������ݿ����
					s.lineDatabase("JSP");// �������ݿ�
					ResultSet jrs = s.getTable("Users");// ��ȡ���
					s.setWrite();// ���ñ�񼯿�д��
					while (jrs.next()) {// ������֤
						if (login.getLogin_name().equals(jrs.getString(1))) {
							if(Old.equals(jrs.getString(2))){
								jrs.updateString(2, New);
								jrs.updateRow();
								windows(request, response, "�޸ĳɹ���", "user.jsp?javascript:document:user_form.submit();");// �½�����
							}else{
								windows(request, response, "���������", "user.jsp?javascript:document:user_form.submit();");// �½�����
							}

						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					windows(request, response,"�����µ�¼","home.jsp");
				}
			}else{
				windows(request, response, "���������벻ͬ��", "user.jsp?javascript:document:user_form.submit();");// �½�����
				return;
			}

		}

	}
}
