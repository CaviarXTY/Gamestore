package bean.data;

import java.util.*;

public class User_Bean {

	private String userID;//�û�id1
	private String userPassword;//�û�����2
	private float userMoney;//�û����3
	private String userEmil;//�û�����4
	private String userPicture;//��Ϸͷ��5
	private String userOwn;//�����¼6
	private int ownNum;//��Ϸӵ����
	private LinkedList<String> userBought=null;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public float getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(float userMoney) {
		this.userMoney = userMoney;
	}
	public String getUserEmil() {
		return userEmil;
	}
	public void setUserEmil(String userEmil) {
		this.userEmil = userEmil;
	}
	public String getUserPicture() {
		return userPicture;
	}
	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}
	public String getOwn() {
		return userOwn;
	}
	public void setOwn(String own) {
		this.userOwn = own;
	}
	public int getOwnNum() {
		return ownNum;
	}
	public void setOwnNum(int ownNum) {
		this.ownNum = ownNum;
	}
	public LinkedList<String> getUserBought() {
		return userBought;
	}
	public void setUserBought(LinkedList<String> string) {
		this.userBought = string;
	}
	
}
