package web.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Repository;
import web.model.User;
import web.config.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(25), lastname VARCHAR(25)," +
                " age TINYINT, PRIMARY KEY (id))";

        try (EntityManager entityManager = Util.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();

            entityManager.createNativeQuery(sql).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (EntityManager entityManager = Util.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();

            entityManager.createNativeQuery(sql).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(String name, String lastName, byte age) {
        EntityManager entityManager = Util.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            User user = new User(name, lastName, age);
            entityManager.persist(user);
            transaction.commit();
            System.out.println("User с именем — " + name + " добавлен в базу данных");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        EntityManager entityManager = Util.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            try {
                User user = entityManager.find(User.class, id);
                if (user != null) {
                    entityManager.remove(user);
                transaction.commit();
                }
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUserById(long id, String name, String lastName, byte age) {
        EntityManager entityManager = Util.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            User user = entityManager.find(User.class, id);
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            entityManager.persist(user);
            transaction.commit();
            System.out.println("User с именем — " + name + " изменён в базе данных");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

    }

    @Override
    public List<User> getUserById(long id) {
        EntityManager entityManager = Util.getEntityManager();
        List<User> user = new ArrayList<>();
        user.add(entityManager.find(User.class, id));
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (EntityManager entityManager = Util.getEntityManager()) {
            users = entityManager.createQuery("FROM User", User.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM users";

        try (EntityManager entityManager = Util.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();

            entityManager.createNativeQuery(sql).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
