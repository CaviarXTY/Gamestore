package servlet.control;

import java.io.*;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;


import utils.Sql_Utils;

//import utils.*;
import bean.data.*;

public class Close_Servlet extends universal_Servlet {

	/**
	 * doPost
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���ݳ�ʼ����
		String Jid = request.getParameter("id").trim();// ��ȡҳ��id
		
		HttpSession session = request.getSession(true);// ����session����
		Login_Bean login = (Login_Bean) session.getAttribute("Login");// ��ȡ��¼״̬

		if(login.getLogin_case()==false)
			windows(request, response,Jid+"��¼�ѳ�ʱ","home.jsp");//ҳ��ת��
		else if(Jid.equals(login.getLogin_name())){
			Login_Bean close = new Login_Bean();
			close.setLogin_name(Jid);//��¼��½�û�
			close.setLogin_case(false);//��¼��½״̬
			session.setAttribute("Login", close);//��user���浽session��
			//session.removeAttribute("Login");
			windows(request, response,Jid+"���˳���¼��","home.jsp");//ҳ��ת��
		}
	}

}
