package servlet.control;

import java.io.*;
import java.sql.*;
import java.util.LinkedList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.sun.rowset.*;

import utils.*;
import bean.data.*;

public class User_Servlet extends universal_Servlet {

	/**
	 * doPost
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ݳ�ʼ����
		HttpSession session = request.getSession(true);// ����session����
		Login_Bean login = (Login_Bean) session.getAttribute("Login");// ��ȡҳ��������
		// windows(request, response,login.getLogin_name(),"home.jsp");

		// //���ݼ��
		// if(login.getLogin_name()==""||login.getLogin_case()==false||login==null)
		// windows(request, response,"�����µ�¼��","home.jsp");//δ��¼

		try {
			// ���ݿ����ӣ�����ȡ
			Sql_Utils s = new Sql_Utils();// �����������ݿ����
			s.lineDatabase("JSP");// �������ݿ�
			ResultSet jrs = s.getTable("Users");// ��ȡ�û���
			while (jrs.next()) {
				if (login.getLogin_name().equals(jrs.getString(1))) {
					User_Bean user = new User_Bean();
					user.setUserID(jrs.getString(1));// �û�id1
					user.setUserMoney(jrs.getFloat(3));// �û����3
					user.setUserEmil(jrs.getString(4));// �û�����4
					user.setUserPicture(jrs.getString(5));// ��Ϸͷ��5
					user.setOwn(jrs.getString(6));// �����¼6
					user.setOwnNum(0);
					String own= user.getOwn();
					if(own==null){
						user.setOwn("û�й������Ϸ��");
					}else{
				        int bStr = own.length() / 5;
				        user.setOwnNum(bStr);
				        String str="";
				        LinkedList<String> ownlist = new LinkedList();
				        for (int i = 0; i < bStr; i++) {
				        	str=(String) own.subSequence(5 * i, 4 + 5 * i);
							System.out.print(str);
							ownlist.add(str);
						}
				        user.setUserBought(ownlist);
					}
					session.setAttribute("User", user);// ��user���浽session��
					skipPage(request, response, "user.jsp");
				}
			}
			// Iterator<String> car =
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			windows(request, response,"�����µ�¼","home.jsp");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			windows(request, response,"�����µ�¼","home.jsp");
		}

	}

}
