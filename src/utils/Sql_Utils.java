package utils;

import java.sql.*;

public class Sql_Utils {
	public Connection con;// 数据库Link
	public Statement sql;// 数据库send
	public ResultSet rs;// 数据库back

	/**
	 * 0加载jdbc驱动
	 */
	public Sql_Utils() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");// 加载SQLServer驱动JDBC
		} catch (Exception e) {
			System.out.println("加载jdbc驱动：" + e);
			e.printStackTrace();
		}
	}

	/**
	 * 0连接数据库
	 * 
	 * @param DatabaseName
	 *            数据库名
	 */
	public void lineDatabase(String DatabaseName) {
		try {
			String uri = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName="
					+ DatabaseName;// 数据库
			String user = "sa";// 数据库登陆账号
			String password = "123123";// 数据库登陆密码
			con = DriverManager.getConnection(uri, user, password);
			sql = con.createStatement();// 连接数据库
			sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE
					// ,ResultSet.CONCUR_READ_ONLY
			);
		} catch (SQLException e) {
			System.out.println("连接数据库：" + e);
			e.printStackTrace();
		}
	}

	/**
	 * 获得表集
	 * 
	 * @param tabelName
	 *            表名
	 * @return 结果集
	 */
	public ResultSet getTable(String tabelName) {
		try {
			rs = sql.executeQuery("SELECT * FROM " + tabelName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("获取表集" + tabelName + "：" + e);
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 设置表集写入
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
	 * 查询字段
	 * @param tabelName
	 * @param limitName 查询列名
	 * @param limit 查询条件
	 * @param islike 是否精确(true-"like",flase-"=")
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
			System.out.println("获取数据" + tabelName + "：" + e);
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 关闭资源
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
