package kz.kaitanov.setronica.dao;

import kz.kaitanov.setronica.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> getByUsername(String username) {
        try {
            return Optional.ofNullable(
                    entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.role WHERE u.username = :username", User.class)
                            .setParameter("username", username)
                            .getSingleResult()
            );
        } catch (NoResultException ignore) {
        }
        return Optional.empty();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

}