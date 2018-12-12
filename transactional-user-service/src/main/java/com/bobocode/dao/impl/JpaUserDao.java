package com.bobocode.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import com.bobocode.dao.UserDao;
import com.bobocode.model.jpa.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements {@link UserDao} using JPA.
 * <p>
 * todo: 1. Configure {@link JpaUserDao} bean as Spring Repository with name "userDao"
 * todo: 2. Enable transaction management on class level
 * todo: 3. Inject persistence context into {@link EntityManager} field
 */
@Repository
@Transactional
public class JpaUserDao implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
}
