package bean.data;

import java.util.*;

public class User_Bean {

	private String userID;//用户id1
	private String userPassword;//用户密码2
	private float userMoney;//用户余额3
	private String userEmil;//用户邮箱4
	private String userPicture;//游戏头像5
	private String userOwn;//购买记录6
	private int ownNum;//游戏拥有数
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
