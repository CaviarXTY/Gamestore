package bean.data;

/**
 *����½״̬��¼��session 
 *login_name ��¼��½�� 
 *login_case ��¼��½״̬ 
 *(0-δ��½ 1-��½�� 2-��½�������� 3-������� 4-���˳�)
 * @author Caviar
 */
public class Login_Bean {

	private String login_name = "";// ��¼��½��
	private boolean login_case = false;// ��¼��½״̬
	
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

//	private boolean login_case = false;// ��¼��½״̬
//	private String login_name = null;// ��¼��½��
//	private String login_Nametip = "";//�û���ʾ
//	private String login_Pwdtip = "";//������ʾ
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
//		login_Nametip = "�û�������";
//	}
//	
//	public String getLogin_Pwdtip() {
//		return login_Pwdtip;
//	}
//	
//	public void setLogin_Pwdtip() {
//		login_Nametip = "";
//		login_Pwdtip = "�������";
//	}

}
