package web;

import web.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.addUser("Name1", "LasName1", (byte) 11);
        userDao.addUser("Name2", "LasName2", (byte) 12);
        userDao.addUser("Name3", "LasName3", (byte) 13);
        userDao.addUser("Name4", "LasName4", (byte) 14);
        userDao.addUser("Name5", "LasName5", (byte) 15);
        userDao.addUser("Name6", "LasName6", (byte) 16);
    }
}
