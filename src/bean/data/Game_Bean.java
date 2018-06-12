package bean.data;

import java.sql.Blob;
import java.util.Date;

public class Game_Bean {
	
	private String gameID;// 游戏编号1
	private String gameName;// 游戏名称2
	private String gameCover;//游戏封面图片3
	private float gamePrice;//游戏价格4
	private String gameType;//游戏类型5
	private String gamePublisher;//游戏发行商6
	private String gameSystemRequirements;//游戏环境类型7
	private String gameText;//游戏文字描述8
	private String releaseDate;//发行时间9
	private boolean own = false;
	//private float gameDiscounts;//优惠价格
	
	public String getGameID() {
		return gameID;
	}
	public void setGameID(String gameID) {
		this.gameID = gameID;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getGameCover() {
		return gameCover;
	}
	public void setGameCover(String gameCover) {
		this.gameCover = gameCover;
	}
	public float getGamePrice() {
		return gamePrice;
	}
	public void setGamePrice(float gamePrice) {
		this.gamePrice = gamePrice;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public String getGamePublisher() {
		return gamePublisher;
	}
	public void setGamePublisher(String gamePublisher) {
		this.gamePublisher = gamePublisher;
	}
	public String getGameSystemRequirements() {
		return gameSystemRequirements;
	}
	public void setGameSystemRequirements(String gameSystemRequirements) {
		this.gameSystemRequirements = gameSystemRequirements;
	}
	public String getGameText() {
		return gameText;
	}
	public void setGameText(String gameText) {
		this.gameText = gameText;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public boolean isOwn() {
		return own;
	}
	public void setOwn(boolean own) {
		this.own = own;
	}
	
}

