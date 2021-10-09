package ir.maktab56.hw17.repository.imp;


import ir.maktab56.hw17.base.repository.imp.BaseEntityRepositoryImpl;
import ir.maktab56.hw17.domain.User;
import ir.maktab56.hw17.repository.UserRepository;

import javax.persistence.EntityManager;

public class UserRepositoryImpl extends BaseEntityRepositoryImpl<User, Integer> implements UserRepository {

    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public boolean existByUsername(String username) {

        return entityManager.createQuery(
                "select count(id) from " + getEntityClass().getSimpleName() +
                        " where username = :username",
                Long.class
        ).setParameter("username", username)
                .getSingleResult() >= 1L;


    }

    @Override
    public boolean existByPassword(String username, String password) {

        return entityManager.createQuery(
                "select count(id) from " + getEntityClass().getSimpleName() +
                        " where username= :username and password= :password ",
                Long.class
        ).setParameter("username", username).setParameter("password", password)
                .getSingleResult() >= 1L;

    }


    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = entityManager.createQuery(
                "from " + getEntityClass().getSimpleName() +
                        " u where u.username = :username and u.password = :password"
                , this.getEntityClass()).setParameter("username", username).setParameter("password", password).getSingleResult();

        return user;


    }

    @Override
    public User findByUsername(String username) {
        User user = entityManager.createQuery(
                "from " + getEntityClass().getSimpleName() +
                        " u where u.username = :username"
                , this.getEntityClass()).setParameter("username", username).getSingleResult();

        return user;
    }
}
