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
		//数据初始处理
		String Jid = request.getParameter("id").trim();// 获取页面id
		String Jpw = request.getParameter("pw").trim();// 获取页面pw
		//数据模型处理
		HttpSession session = request.getSession(true);//创建session对象
		Login_Bean login = new Login_Bean();// 创建数据模型
		//request.setAttribute("Login_Bean", login);//将user储存到session中
		login.setLogin_name(Jid);//记录登陆用户

		//数据库连接，表格获取
		Sql_Utils s = new Sql_Utils();// 建立连接数据库对象
		s.lineDatabase("JSP");// 连接数据库
		ResultSet jrs = s.getTable("Users");//获取表格
		
		/**
		 * 登陆验证
		 */
		try {
			while (jrs.next()) {// 遍历验证
				// 验证账号是否存在
				if (Jid.equals(jrs.getString(1))) {
					// 验证密码
					if (Jpw.equals(jrs.getString(2))) {
					    //验证成功
						s.close();// 关闭数据库资源		
						login.setLogin_case(true);//记录登陆状态
						session.setAttribute("Login", login);//将user储存到session中
						skipPage(request, response,"/home.jsp");//页面转发
						return;
					}
//					else {
//						//密码错误
//						request.setAttribute("Login", login);//将user储存到session中
//						skipPage(request, response,"/home.jsp");//页面转发
//						return;
//						//测试用
//						tip(request, response, "测试：密码" + Jpw + "错误,正确为"
//								+ jrs.getString(2)+user.getLogin_case());// jsp输出错误
//					}
				}
			}
			s.close();// 关闭数据库资源	
			windows(request, response,"账号或密码错误!","home.jsp");//新建弹窗
//			newPage(request, response,"<script type='text/javascript'>alert('账号或密码错误');window.location.href='home.jsp';showDialog();</script>");
//			response.setContentType("text/html;charset=gb2312");
//			PrintWriter out=response.getWriter();
//			out.print("<script type='text/javascript'>alert('账号或密码错误');window.location.href='home.jsp';</script>");
			//skipPage(request, response,"/home.jsp");//页面转发
			return;
			//测试用
//			tip(request, response, "账号不存在！");
			
			//////////////cookie使用
//			if (Jid.equals("123")) {// 验证账号是否存在
//				if (Jpw.equals("123")) {// 验证密码
//					//添加cookie
//					Cookie c_set = new Cookie("username", Jid);// 记录登陆状态
//					// c.setPath(request.getContextPath()); //设置其生效的路径
//					c_set.setMaxAge(3600); // 设置过期时间
//					response.addCookie(c_set); // 将此cookie发送给客户端保存
//					System.out.println("发送成功");
//					
//					//遍历服务起cookie
//					Cookie[] cookies = request.getCookies();
//					for (Cookie c : cookies) {
//						//提取cookie
//						System.out.println("遍历中..."+c);
//						if (c.getName().equals("username")) {
//							String s = URLDecoder.decode(c.getValue(), "utf-8");
//							//tip(request, response, s);
//							RequestDispatcher dispatcher = request
//							.getRequestDispatcher("/test.jsp");
//					dispatcher.forward(request, response);//转发
//							return;
//						}
//				        //修改cookie  
//				        if(c.getName().equals("city")){  
//				            c.setValue("Shenzhen");//通过cookie.setValue()修改cookie中的内容  
//				            res.addCookie(c);  
//				        }
//					}
//	  
//				} else {
//					tip(request, response, "测试：密码" + Jpw + "错误");// jsp输出错误
//					
//				}			
//			}else{
//				// 返回原页面
//				tip(request, response, "账号不存在！");
//			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			windows(request, response,"请重新登录","home.jsp");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			windows(request, response,"请重新登录","home.jsp");
		}
	}

}
