package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.mysql.cj.jdbc.Driver;

import domains.Mapping;
import domains.User;

//Data Access Object
//CRUD，Create Read Update Delete
public class MapDao {
	private static final String driverClass = "com.mysql.cj.jdbc.Driver";
	private static final String jdbcURL = "jdbc:mysql://localhost/sso?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";
	private static final String name = "root";
	private static final String pwd = "lingrui02@";

	// 获取数据库连接
	private static Connection getConnection() throws Exception {
		Class.forName(driverClass);
		System.out.println("db link...");
		Connection conn = DriverManager.getConnection(jdbcURL, name, pwd);
		System.out.println("linked");
		return conn;
	}

	private Connection conn;

	public void close() throws SQLException {
		conn.close();
	}

	public MapDao() throws Exception {
		super();
		// TODO Auto-generated constructor stub

		conn = getConnection();
	}

	// 添加
//	@Transaction
	public Boolean add(final Mapping mapping) throws Exception {
//		Connection conn=getConnection();
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO mapping (localUser, host, app, casUser) VALUES (?, ?, ?,?);");
		ps.setString(1, mapping.getLocalUser());
		ps.setString(2, mapping.getHost());
		ps.setString(3, mapping.getApp());
		ps.setString(4, mapping.getCasUser().getId());

		ps.execute();
		return true;
	}

	// 获取
//	@Query
	public Mapping get(final String id) throws Exception {

//		Connection conn=getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from mapping where id=?");
		ps.setString(1, id);
		ps.execute();
		ResultSet rs = ps.getResultSet();
		Mapping mapping = null;

		UserDao uDao = new UserDao();
		if (rs.next()) {
			mapping = new Mapping();
			mapping.setId(rs.getLong("id"));
			User u1 = uDao.get(rs.getString("casUser"));
			mapping.setCasUser(u1);
			mapping.setLocalUser(rs.getString("localUser"));
			mapping.setHost(rs.getString("host"));
			mapping.setApp(rs.getString("app"));

		}
		return mapping;
	}

	public Mapping findMappingByHostAndAppAndCasUser(String host, String app, User user) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("select * from mapping where host=? and app=? and casuser =?;");
		ps.setString(1, host);
		ps.setString(2, app);
		ps.setString(3, user.getId());
		ps.execute();
		ResultSet rs = ps.getResultSet();
		Mapping mapping = null;

		UserDao uDao;
		try {
			uDao = new UserDao();

			if (rs.next()) {
				mapping = new Mapping();
				mapping.setId(rs.getLong("id"));
				User u1 = uDao.get(rs.getString("casUser"));
				mapping.setCasUser(u1);
				mapping.setLocalUser(rs.getString("localUser"));
				mapping.setHost(rs.getString("host"));
				mapping.setApp(rs.getString("app"));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping;
	}

	public List<Mapping> finAll(final String id) throws Exception {

//		Connection conn=getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from mapping where id=?");
		ps.setString(1, id);
		ps.execute();
		ResultSet rs = ps.getResultSet();
		Mapping mapping = null;
		List<Mapping> mappings = new ArrayList<Mapping>();
		UserDao uDao = new UserDao();
		if (rs.next()) {
			mapping = new Mapping();
			mapping.setId(rs.getLong("id"));
			User u1 = uDao.get(rs.getString("casUser"));
			mapping.setCasUser(u1);
			mapping.setLocalUser(rs.getString("localUser"));
			mapping.setHost(rs.getString("host"));
			mapping.setApp(rs.getString("app"));
			mappings.add(mapping);

		}
		return mappings;
	}

	// 获取全部
//	@Query
	public List<Mapping> getAll() throws Exception {

//		Connection conn=getConnection();
		List<Mapping> mappings = new ArrayList<Mapping>();
		PreparedStatement ps = conn.prepareStatement("select * from mapping");
		ps.execute();
		ResultSet rs = ps.getResultSet();

		UserDao uDao = new UserDao();
		while (rs.next()) {
			Mapping mapping = new Mapping();
			mapping.setId(rs.getLong("id"));
			User u1 = uDao.get(rs.getString("casUser"));
			mapping.setCasUser(u1);
			mapping.setLocalUser(rs.getString("localUser"));
			mapping.setHost(rs.getString("host"));
			mapping.setApp(rs.getString("app"));
			mappings.add(mapping);
		}
		return mappings;

	}

	// 修改
////	@Transaction
//	public Boolean update(final mapping mapping, final idring oldId) throws Exception {
////		Connection conn=getConnection();
//		Preparedidatement ps = conn.prepareidatement("update mapping set id=?,user=?, timeidamp=? where id=?");
//		ps.setidring(1, mapping.getId());
//		ps.setidring(2, mapping.getPwd());
//		ps.setidring(3, mapping.getId());
//		ps.execute();
//		return true;
//	}

	// 删除

	public Boolean delete(final String id) throws Exception {
//		Connection conn=getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from mapping where id=?");
		ps.setString(1, id);
		ps.execute();
		return true;
	}

}
