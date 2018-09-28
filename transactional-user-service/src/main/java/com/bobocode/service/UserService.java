package com.bobocode.service;

import com.bobocode.dao.UserDao;
import com.bobocode.model.jpa.Role;
import com.bobocode.model.jpa.RoleType;
import com.bobocode.model.jpa.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * This class proovides {@link User} related service logic.
 * <p>
 * todo: 1. Configure {@link UserService} bean as spring service
 * todo: 2. Inject {@link UserDao} using constructor-based injection
 * todo: 3. Enable transaction management on class level
 * todo: 4. Configure {@link UserService#getAll()} as read-only method
 * todo: 4. Configure {@link UserService#getAllAdmins()} as read-only method
 */
public class UserService {
    private UserDao userDao;

    public void save(User user) {
        userDao.save(user);
    }

    public List<User> getAll() {
        return userDao.findAll();
    }

    public List<User> getAllAdmins() {
        return userDao.findAll().stream()
                .filter(user -> user.getRoles().stream()
                        .map(Role::getRoleType)
                        .anyMatch(roleType -> roleType.equals(RoleType.ADMIN)))
                .collect(toList());
    }

    public void addRole(Long userId, RoleType roleType) {
        User user = userDao.findById(userId);
        Role role = Role.valueOf(roleType);
        user.addRole(role);
    }
}
