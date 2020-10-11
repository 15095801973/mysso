package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.cj.jdbc.Driver;

import domains.Mapping;
import domains.User;

//Data Access Object
//CRUD，Create Read Update Delete
public class UserDao {
	private static final String driverClass="com.mysql.cj.jdbc.Driver";
	private static final String jdbcURL="jdbc:mysql://localhost/sso?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";
	private static final String user="root";
	private static final String pwd="lingrui02@";
	//获取数据库连接
	private static Connection getConnection() throws Exception
	{
		Class.forName(driverClass);
		System.out.println("db link...");
		Connection conn=DriverManager.getConnection(jdbcURL, user, pwd);
		System.out.println("linked");
		return conn;
	}

	private Connection conn;

	public void close() throws SQLException{
		conn.close();
	}

	public UserDao() throws Exception {
		super();
		// TODO Auto-generated constructor stub

		conn=getConnection();
	}
	// 添加
//	@Transaction
	public Boolean add(final User user) throws Exception {
//		Connection conn=getConnection();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO user (pwd, id) VALUES (?, ?);");
		ps.setString(1, user.getPwd());
		ps.setString(2, user.getId());

		ps.execute();
		return true;
	}

	// 获取
//	@Query
	public User get(final String id) throws Exception {

//		Connection conn=getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from user where id=?");
		ps.setString(1, id);
		ps.execute();
		ResultSet rs = ps.getResultSet();
		User user = null;
		if (rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setPwd(rs.getString("pwd"));

		}
		return user;

	}

	// 获取全部
//	@Query
	public List<User> getAll() throws Exception {

//		Connection conn=getConnection();
		List<User> Users = new ArrayList<User>();
		PreparedStatement ps = conn.prepareStatement("select * from user");
		ps.execute();
		ResultSet rs = ps.getResultSet();
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setPwd(rs.getString("pwd"));
			Users.add(user);
		}
		return Users;

	}

	// 修改
//	@Transaction
	public Boolean update(final User user, final String oldId) throws Exception {
//		Connection conn=getConnection();
		PreparedStatement ps = conn.prepareStatement("update user set id=?,pwd=? where id=?");
		ps.setString(1, user.getId());
		ps.setString(2, user.getPwd());
		ps.setString(3, user.getId());
		ps.execute();
		return true;
	}

	// 删除

	public Boolean delete(final String id) throws Exception {
//		Connection conn=getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from user where id=?");
		ps.setString(1, id);
		ps.execute();
		return true;
	}

}
