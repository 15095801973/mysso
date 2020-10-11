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

import domains.ServiceTicket;

//Data Access Object
//CRUD，Create Read Update Delete
public class STDao {
	private static final String driverClass = "com.mysql.cj.jdbc.Driver";
	private static final String jdbcURL = "jdbc:mysql://localhost/sso?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";
	private static final String serviceticket = "root";
	private static final String pwd = "lingrui02@";

	// 获取数据库连接
	private static Connection getConnection() throws Exception {
		Class.forName(driverClass);
		System.out.println("db link...");
		Connection conn = DriverManager.getConnection(jdbcURL, serviceticket, pwd);
		System.out.println("linked");
		return conn;
	}

	private Connection conn;

	public void close() throws SQLException {
		conn.close();
	}

	public STDao() throws Exception {
		super();
		// TODO Auto-generated constructor stub

		conn = getConnection();
	}

	// 添加
//	@Transaction
	public Boolean add(final ServiceTicket serviceticket) throws Exception {
//		Connection conn=getConnection();
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO serviceticket (st, user, timestamp) VALUES (?, ?,?);");
		ps.setString(1, serviceticket.getSt());
		ps.setString(2, serviceticket.getUser().getId());
		ps.setTimestamp(3, serviceticket.getCreated());

		ps.execute();
		return true;
	}

	// 获取
//	@Query
	public ServiceTicket get(final String st) throws Exception {

//		Connection conn=getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from serviceticket where st=?");
		ps.setString(1, st);
		ps.execute();
		ResultSet rs = ps.getResultSet();
		ServiceTicket serviceticket = null;

		UserDao uDao = new UserDao();
		if (rs.next()) {
			serviceticket = new ServiceTicket();
			serviceticket.setSt(rs.getString("st"));

			serviceticket.setUser(uDao.get(rs.getString("user")));
			serviceticket.setCreated(rs.getTimestamp("timestamp"));

		}
		return serviceticket;
	}
	public List<ServiceTicket> finAll(final String st) throws Exception {

//		Connection conn=getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from serviceticket where st=?");
		ps.setString(1, st);
		ps.execute();
		ResultSet rs = ps.getResultSet();
		ServiceTicket serviceticket = null;
		List<ServiceTicket> serviceTickets= new ArrayList<ServiceTicket>();
		UserDao uDao = new UserDao();
		if (rs.next()) {
			serviceticket = new ServiceTicket();
			serviceticket.setSt(rs.getString("st"));

			serviceticket.setUser(uDao.get(rs.getString("user")));
			serviceticket.setCreated(rs.getTimestamp("timestamp"));
			serviceTickets.add(serviceticket);

		}
		return serviceTickets;
	}

	// 获取全部
//	@Query
	public List<ServiceTicket> getAll() throws Exception {

//		Connection conn=getConnection();
		List<ServiceTicket> servicetickets = new ArrayList<ServiceTicket>();
		PreparedStatement ps = conn.prepareStatement("select * from serviceticket");
		ps.execute();
		ResultSet rs = ps.getResultSet();

		UserDao uDao = new UserDao();
		while (rs.next()) {
			ServiceTicket serviceticket = new ServiceTicket();
			serviceticket.setSt(rs.getString("st"));

			serviceticket.setUser(uDao.get(rs.getString("user")));
			serviceticket.setCreated(rs.getTimestamp("timestamp"));
			servicetickets.add(serviceticket);
		}
		return servicetickets;

	}

	// 修改
////	@Transaction
//	public Boolean update(final ServiceTicket serviceticket, final String oldId) throws Exception {
////		Connection conn=getConnection();
//		PreparedStatement ps = conn.prepareStatement("update serviceticket set st=?,user=?, timestamp=? where st=?");
//		ps.setString(1, serviceticket.getId());
//		ps.setString(2, serviceticket.getPwd());
//		ps.setString(3, serviceticket.getId());
//		ps.execute();
//		return true;
//	}

	// 删除

	public Boolean delete(final String st) throws Exception {
//		Connection conn=getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from serviceticket where st=?");
		ps.setString(1, st);
		ps.execute();
		return true;
	}

}
