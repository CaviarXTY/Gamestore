package bean.data;

/**
 *将登陆状态记录于session 
 *login_name 记录登陆名 
 *login_case 记录登陆状态 
 *(0-未登陆 1-登陆中 2-登陆名不存在 3-密码错误 4-已退出)
 * @author Caviar
 */
public class Login_Bean {

	private String login_name = "";// 记录登陆名
	private boolean login_case = false;// 记录登陆状态
	
	public String getLogin_name() {
		return login_name;
	}
	
	public void setLogin_name(String loginName) {
		login_name = loginName;
	}
	
	public boolean getLogin_case() {
		return login_case;
	}
	
	public void setLogin_case(boolean loginCase) {
		login_case = loginCase;
	}

//	private boolean login_case = false;// 记录登陆状态
//	private String login_name = null;// 记录登陆名
//	private String login_Nametip = "";//用户提示
//	private String login_Pwdtip = "";//密码提示
//	
//	public boolean isLogin_case() {
//		return login_case;
//	}
//	
//	public void setLogin_case(boolean loginCase) {
//		login_case = loginCase;
//	}
//	
//	public String getLogin_name() {
//		return login_name;
//	}
//	
//	public void setLogin_name(String loginName) {
//		login_name = loginName;
//	}
//	
//	public String getLogin_Nametip() {
//		return login_Nametip;
//	}
//	
//	public void setLogin_Nametip() {
//		login_Pwdtip = "";
//		login_Nametip = "用户不存在";
//	}
//	
//	public String getLogin_Pwdtip() {
//		return login_Pwdtip;
//	}
//	
//	public void setLogin_Pwdtip() {
//		login_Nametip = "";
//		login_Pwdtip = "密码错误";
//	}

}
