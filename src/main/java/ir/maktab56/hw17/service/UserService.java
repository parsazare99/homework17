package ir.maktab56.hw17.service;


import ir.maktab56.hw17.base.service.BaseEntityService;
import ir.maktab56.hw17.domain.User;

public interface UserService extends BaseEntityService<User, Integer> {

    User registerUser();

    User logInUser();

    User editProfileUser(User user);

    void showProfileUser(User user);

    void deleteAccount(User user);

    boolean existByUsername(String username);

    boolean existByPassword(String username, String password);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}