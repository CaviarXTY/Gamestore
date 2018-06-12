package utils;

import java.sql.*;

public class Sql_Utils {
	public Connection con;// ���ݿ�Link
	public Statement sql;// ���ݿ�send
	public ResultSet rs;// ���ݿ�back

	/**
	 * 0����jdbc����
	 */
	public Sql_Utils() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");// ����SQLServer����JDBC
		} catch (Exception e) {
			System.out.println("����jdbc������" + e);
			e.printStackTrace();
		}
	}

	/**
	 * 0�������ݿ�
	 * 
	 * @param DatabaseName
	 *            ���ݿ���
	 */
	public void lineDatabase(String DatabaseName) {
		try {
			String uri = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName="
					+ DatabaseName;// ���ݿ�
			String user = "sa";// ���ݿ��½�˺�
			String password = "123123";// ���ݿ��½����
			con = DriverManager.getConnection(uri, user, password);
			sql = con.createStatement();// �������ݿ�
			sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE
					// ,ResultSet.CONCUR_READ_ONLY
			);
		} catch (SQLException e) {
			System.out.println("�������ݿ⣺" + e);
			e.printStackTrace();
		}
	}

	/**
	 * ��ñ�
	 * 
	 * @param tabelName
	 *            ����
	 * @return �����
	 */
	public ResultSet getTable(String tabelName) {
		try {
			rs = sql.executeQuery("SELECT * FROM " + tabelName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("��ȡ��" + tabelName + "��" + e);
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * ���ñ�д��
	 */
	public void setWrite(){
		try {
			sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * ��ѯ�ֶ�
	 * @param tabelName
	 * @param limitName ��ѯ����
	 * @param limit ��ѯ����
	 * @param islike �Ƿ�ȷ(true-"like",flase-"=")
	 * @return
	 */
	public ResultSet getData(String tabelName, String limitName, String limit,boolean islike) {
		String s = "LIKE ";
		if(islike==false)
		s="=";
		try {
			rs = sql.executeQuery("SELECT * FROM " + tabelName+" WHERE "+limitName+" "+s+" "+limit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("��ȡ����" + tabelName + "��" + e);
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * �ر���Դ
	 */
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
