package dao;
import java.awt.List;

import domains.User;

class test{
	public static void main(String[] args) throws Exception {
		UserDao uDao=new UserDao();
		User u1=uDao.get("3");
		System.out.println(u1.getPwd());
		User u2=new User();
		u2.setId("ad");
		u2.setPwd("ad");
		System.out.println(uDao.add(u2));
		uDao.delete("ad");
		User u3=new User();
		u3.setId("new");
		u3.setPwd("new");
		System.out.println(uDao.update(u3, "add"));
		System.out.println(uDao.delete("new"));
		java.util.List<User> lis= uDao.getAll();
		for (int i = 0; i < lis.size(); i++) {
			System.out.println(lis.get(i).getId());
		}
		uDao.close();
	}
}