package bean.data;

import java.sql.Blob;
import java.util.Date;

public class Game_Bean {
	
	private String gameID;// ��Ϸ���1
	private String gameName;// ��Ϸ����2
	private String gameCover;//��Ϸ����ͼƬ3
	private float gamePrice;//��Ϸ�۸�4
	private String gameType;//��Ϸ����5
	private String gamePublisher;//��Ϸ������6
	private String gameSystemRequirements;//��Ϸ��������7
	private String gameText;//��Ϸ��������8
	private String releaseDate;//����ʱ��9
	private boolean own = false;
	//private float gameDiscounts;//�Żݼ۸�
	
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

