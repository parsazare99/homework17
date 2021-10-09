package ir.maktab56.hw17.repository;


import ir.maktab56.hw17.base.repository.BaseEntityRepository;
import ir.maktab56.hw17.domain.User;

public interface UserRepository extends BaseEntityRepository<User, Integer> {

    boolean existByUsername(String username);

    boolean existByPassword(String username, String password);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}