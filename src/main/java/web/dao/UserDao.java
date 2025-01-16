package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.id = :id")
    List<User> findUserById(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = :id")
    void deleteUserById(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :name, u.lastName = :lastName, u.age = :age WHERE u.id = :id")
    void updateUserById(@Param("id") long id, @Param("name") String name, @Param("lastName") String lastName, @Param("age") int age);

    @Modifying
    @Transactional
    @Query("DELETE FROM User")
    void cleanUsersTable();

    default void addUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        save(user);
    }

    default void saveUserById(long id, String name, String lastName, byte age) {
        Optional<User> optionalUser = findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            save(user);
        }
    }

}