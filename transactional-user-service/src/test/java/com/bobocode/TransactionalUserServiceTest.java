package com.bobocode;

import com.bobocode.config.RootConfig;
import com.bobocode.dao.UserDao;
import com.bobocode.dao.impl.JpaUserDao;
import com.bobocode.model.jpa.Role;
import com.bobocode.model.jpa.RoleType;
import com.bobocode.model.jpa.User;
import com.bobocode.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringJUnitConfig(RootConfig.class)
@Transactional
class TransactionalUserServiceTest {
    @Configuration
    static class TestConfig {
        @Bean
        TestDataGenerator dataGenerator() {
            return new TestDataGenerator();
        }
    }

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TestDataGenerator dataGenerator;

    @Test
    void testTxManagerBeanName() {
        PlatformTransactionManager transactionManager = applicationContext.getBean(PlatformTransactionManager.class, "transactionManager");

        assertThat(transactionManager, notNullValue());
    }

    @Test
    void testUserDaoBeanName() {
        UserDao userDao = applicationContext.getBean(UserDao.class, "userDao");

        assertThat(userDao, notNullValue());
    }

    @Test
    void testEntityManagerFactoryBeanName() {
        EntityManagerFactory entityManagerFactory = applicationContext.getBean(EntityManagerFactory.class, "entityManagerFactory");

        assertThat(entityManagerFactory, notNullValue());
    }

    @Test
    void testUserServiceIsMarkedAsService() {
        Service service = UserService.class.getAnnotation(Service.class);

        assertThat(service, notNullValue());
    }

    @Test
    void testUserDaoIsMarkedAsRepository() {
        Repository repository = JpaUserDao.class.getAnnotation(Repository.class);

        assertThat(repository, notNullValue());
    }

    @Test
    void testUserServiceIsTransactional() {
        Transactional transactional = UserService.class.getAnnotation(Transactional.class);

        assertThat(transactional, notNullValue());
    }

    @Test
    void testUserServiceGetAllIsReadOnly() throws NoSuchMethodException {
        Transactional transactional = UserService.class.getDeclaredMethod("getAll").getAnnotation(Transactional.class);

        assertThat(transactional.readOnly(), is(true));
    }

    @Test
    void testUserServiceGetAllAdminsIsReadOnly() throws NoSuchMethodException {
        Transactional transactional = UserService.class.getDeclaredMethod("getAllAdmins").getAnnotation(Transactional.class);

        assertThat(transactional.readOnly(), is(true));
    }

    @Test
    void testUserDaoIsTransactional() {
        Transactional transactional = JpaUserDao.class.getAnnotation(Transactional.class);

        assertThat(transactional, notNullValue());
    }

    @Test
    void testSaveUser() {
        User user = dataGenerator.generateUser();
        userService.save(user);

        assertThat(userDao.findAll(), hasItem(user));
    }

    @Test
    void testGetAllUsers() {
        List<User> userList = generateUserList(10);
        userList.forEach(userService::save);

        List<User> users = userService.getAll();
        assertThat(users, containsInAnyOrder(userList.toArray()));
    }

    private List<User> generateUserList(int size) {
        return Stream.generate(dataGenerator::generateUser)
                .limit(size)
                .collect(toList());
    }

    @Test
    void testGetAllAdmins() {
        List<User> userList = generateUserList(20);
        userList.forEach(userService::save);

        List<User> admins = userService.getAllAdmins();

        assertThat(admins, containsInAnyOrder(findAdmins(userList).toArray()));
    }

    private List<User> findAdmins(List<User> users) {
        return users.stream()
                .filter(user -> user.getRoles().stream()
                        .map(Role::getRoleType)
                        .anyMatch(roleType -> roleType.equals(RoleType.ADMIN)))
                .collect(toList());

    }

    @Test
    void testAddNewRole() {
        User user = dataGenerator.generateUser(RoleType.USER);
        userService.save(user);

        userService.addRole(user.getId(), RoleType.ADMIN);

        User loadedUser = userDao.findById(user.getId());
        assertThat(loadedUser.getRoles(), contains(
                hasProperty("roleType", is(RoleType.USER)),
                hasProperty("roleType", is(RoleType.ADMIN)))
        );
    }
}
