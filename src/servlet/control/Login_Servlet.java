package servlet.control;

import java.io.*;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;


import utils.Sql_Utils;

//import utils.*;
import bean.data.*;

public class Login_Servlet extends universal_Servlet {

	/**
	 * doPost
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���ݳ�ʼ����
		String Jid = request.getParameter("id").trim();// ��ȡҳ��id
		String Jpw = request.getParameter("pw").trim();// ��ȡҳ��pw
		//����ģ�ʹ���
		HttpSession session = request.getSession(true);//����session����
		Login_Bean login = new Login_Bean();// ��������ģ��
		//request.setAttribute("Login_Bean", login);//��user���浽session��
		login.setLogin_name(Jid);//��¼��½�û�

		//���ݿ����ӣ�����ȡ
		Sql_Utils s = new Sql_Utils();// �����������ݿ����
		s.lineDatabase("JSP");// �������ݿ�
		ResultSet jrs = s.getTable("Users");//��ȡ���
		
		/**
		 * ��½��֤
		 */
		try {
			while (jrs.next()) {// ������֤
				// ��֤�˺��Ƿ����
				if (Jid.equals(jrs.getString(1))) {
					// ��֤����
					if (Jpw.equals(jrs.getString(2))) {
					    //��֤�ɹ�
						s.close();// �ر����ݿ���Դ		
						login.setLogin_case(true);//��¼��½״̬
						session.setAttribute("Login", login);//��user���浽session��
						skipPage(request, response,"/home.jsp");//ҳ��ת��
						return;
					}
//					else {
//						//�������
//						request.setAttribute("Login", login);//��user���浽session��
//						skipPage(request, response,"/home.jsp");//ҳ��ת��
//						return;
//						//������
//						tip(request, response, "���ԣ�����" + Jpw + "����,��ȷΪ"
//								+ jrs.getString(2)+user.getLogin_case());// jsp�������
//					}
				}
			}
			s.close();// �ر����ݿ���Դ	
			windows(request, response,"�˺Ż��������!","home.jsp");//�½�����
//			newPage(request, response,"<script type='text/javascript'>alert('�˺Ż��������');window.location.href='home.jsp';showDialog();</script>");
//			response.setContentType("text/html;charset=gb2312");
//			PrintWriter out=response.getWriter();
//			out.print("<script type='text/javascript'>alert('�˺Ż��������');window.location.href='home.jsp';</script>");
			//skipPage(request, response,"/home.jsp");//ҳ��ת��
			return;
			//������
//			tip(request, response, "�˺Ų����ڣ�");
			
			//////////////cookieʹ��
//			if (Jid.equals("123")) {// ��֤�˺��Ƿ����
//				if (Jpw.equals("123")) {// ��֤����
//					//���cookie
//					Cookie c_set = new Cookie("username", Jid);// ��¼��½״̬
//					// c.setPath(request.getContextPath()); //��������Ч��·��
//					c_set.setMaxAge(3600); // ���ù���ʱ��
//					response.addCookie(c_set); // ����cookie���͸��ͻ��˱���
//					System.out.println("���ͳɹ�");
//					
//					//����������cookie
//					Cookie[] cookies = request.getCookies();
//					for (Cookie c : cookies) {
//						//��ȡcookie
//						System.out.println("������..."+c);
//						if (c.getName().equals("username")) {
//							String s = URLDecoder.decode(c.getValue(), "utf-8");
//							//tip(request, response, s);
//							RequestDispatcher dispatcher = request
//							.getRequestDispatcher("/test.jsp");
//					dispatcher.forward(request, response);//ת��
//							return;
//						}
//				        //�޸�cookie  
//				        if(c.getName().equals("city")){  
//				            c.setValue("Shenzhen");//ͨ��cookie.setValue()�޸�cookie�е�����  
//				            res.addCookie(c);  
//				        }
//					}
//	  
//				} else {
//					tip(request, response, "���ԣ�����" + Jpw + "����");// jsp�������
//					
//				}			
//			}else{
//				// ����ԭҳ��
//				tip(request, response, "�˺Ų����ڣ�");
//			}
			
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
