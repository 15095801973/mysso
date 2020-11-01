package database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.MapDao;
import dao.STDao;
import dao.UserDao;
import domains.Mapping;
import domains.ServiceTicket;
import domains.SessionStorage;
import domains.User;
import cas.server.Constants;

public class DB {
	private static Set<ServiceTicket> serviceTickets = new HashSet<>();
//	private static Set<User> users = new HashSet<>();
	private static Set<SessionStorage> sessionStorages = new HashSet<>();
//	private static Set<Mapping> mappings = new HashSet<>();
//	static {
//		User u1 = addUser("01", "0");
//		User u2 = addUser("02", "0");
//		addMapping(1L, u1, "app1u1", "localhost", "/app1");
//		addMapping(2L, u1, "app2u1", "localhost", "/app2");
//		addMapping(1L, u2, "app1u2", "localhost", "/app1");
//		addMapping(2L, u2, "app2u2", "localhost", "/app2");
//	}

	public static User addUser(String id, String pwd) {
		User u = new User();
		u.setId(id);
		u.setPwd(pwd);
//		users.add(u);
		UserDao uDao;
		try {
			uDao = new UserDao();
			uDao.add(u);
			System.out.println("add..");
			addMapping(u, "app1/" + id, Constants.localhost, Constants.myapp1);
			addMapping(u, "app2/" + id, Constants.localhost, Constants.myapp2);
			uDao.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	public static ServiceTicket findServiceTicketbySt(String st) {

//		STDao stDao;
//		ServiceTicket sTicket = new ServiceTicket();
//		try {
//			stDao = new STDao();
//			sTicket = stDao.get(st);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return sTicket;
		for (ServiceTicket s : serviceTickets) {
			if (s.getSt().equals(st)) {
				return s;
			}
		}
		return null;
	}

	public static User findUser(String id, String pwd) {
		List<User> users = null;
		UserDao uDao;
		try {
			uDao = new UserDao();
			users = uDao.getAll();
			uDao.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (User u : users) {
			if (u.getId().equals(id) && u.getPwd().equals(pwd)) {
				return u;
			}
		}
		return null;
	}

	public static void addServiceTicket(User user, String st) {
//		STDao stDao;
//		try {
//			ServiceTicket serviceTicket = new ServiceTicket();
//			stDao = new STDao();
//			serviceTicket.setUser(user);
//			serviceTicket.setSt(st);
//			stDao.add(serviceTicket);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ServiceTicket serviceTicket = new ServiceTicket();
		serviceTicket.setUser(user);
		serviceTicket.setSt(st);
		serviceTickets.add(serviceTicket);
	}

	public static void addSessionStorage(String LOCAL_SERVICE, String CAS_ST, String sessionId) {
		SessionStorage sessionStorage = new SessionStorage();
		sessionStorage.setLocalService(LOCAL_SERVICE);
		sessionStorage.setSt(CAS_ST);
		sessionStorage.setSessionId(sessionId);
		sessionStorages.add(sessionStorage);

	}

	public static void addMapping(User casUser, String localUser, String host, String app) throws Exception {
		Mapping m = new Mapping();
		m.setCasUser(casUser);
		m.setLocalUser(localUser);
		m.setHost(host);
		m.setApp(app);
//		mappings.add(m);
		System.out.println("add map...");
		MapDao mapDao = new MapDao();
		mapDao.add(m);
	}

	public static Mapping findMappingByHostAndAppAndCasUser(String host, String app, User user) {
		Mapping map = new Mapping();
		MapDao mapDao;
		try {
			mapDao = new MapDao();

			System.out.println("get map...");
			map = mapDao.findMappingByHostAndAppAndCasUser(host, app, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	public static List<SessionStorage> findSessionStorage(String CAS_ST) {
		List<SessionStorage> list = new ArrayList<>();
		for (SessionStorage s : sessionStorages) {
			if (s.getSt().equals(CAS_ST)) {
				list.add(s);
			}
		}
		return list;
	}

	public static void deleteSessionStorage(String CAS_ST) {
		sessionStorages.removeAll(findSessionStorage(CAS_ST));

	}

	public static List<ServiceTicket> findServiceTicket(String CAS_ST) {

//		STDao stDao = new STDao();
//		return stDao.finAll(CAS_ST);
		List<ServiceTicket> list = new ArrayList<>();
		for (ServiceTicket s : serviceTickets) {
			if (s.getSt().equals(CAS_ST)) {
				list.add(s);
			}
		}
		return list;
	}

	public static void deleteServiceTicket(String CAS_ST) {
//		STDao stDao;
//		try {
//			stDao = new STDao();
//			stDao.delete(CAS_ST);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
		serviceTickets.removeAll(findServiceTicket(CAS_ST));
	}
}
