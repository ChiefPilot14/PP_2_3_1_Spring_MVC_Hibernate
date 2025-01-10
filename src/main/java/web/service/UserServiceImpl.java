package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDaoHibernateImpl;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDaoHibernateImpl userDaoHibernateImpl;

    @Autowired
    public UserServiceImpl(UserDaoHibernateImpl userDaoHibernateImpl) {
        this.userDaoHibernateImpl = userDaoHibernateImpl;
    }

    @Override
    public void createUsersTable() {
        userDaoHibernateImpl.createUsersTable();

    }

    @Override
    public void dropUsersTable() {
        userDaoHibernateImpl.dropUsersTable();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernateImpl.addUser(name, lastName, age);

    }

    @Override
    public void removeUserById(long id) {
        userDaoHibernateImpl.removeUserById(id);

    }

    @Override
    public List<User> getAllUsers() {
        return userDaoHibernateImpl.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDaoHibernateImpl.cleanUsersTable();

    }
}
