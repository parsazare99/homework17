package ir.maktab56.hw17.service.imp;


import ir.maktab56.hw17.base.service.impl.BaseEntityServiceImpl;
import ir.maktab56.hw17.domain.User;
import ir.maktab56.hw17.repository.UserRepository;
import ir.maktab56.hw17.service.UserService;

import java.util.Scanner;

public class UserServiceImpl extends BaseEntityServiceImpl<User, Integer, UserRepository>
        implements UserService {

    Scanner input = new Scanner(System.in);

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public User registerUser() {

        User user = new User();
        System.out.println("Enter your First Name :");
        user.setFirstname(new Scanner(System.in).next());
        System.out.println("Enter your Last Name :");
        user.setLastname(new Scanner(System.in).next());

        while (true) {
            System.out.println("Enter your UserName :");
            String username = new Scanner(System.in).next();
            if (!repository.existByUsername(username)) {
                user.setUsername(username);
                break;
            } else {
                System.out.println("sorry, this username is already taken ");
            }

        }

        while (true) {
            System.out.println("Enter your passWord :");
            System.out.println("Password length must be 8 or more ");
            String password = input.next();
            if (password.length() >= 8) {
                user.setPassword(password);
                break;
            }
            System.out.println("The length of the password is shor\nplease try againe");
        }

        System.out.println("Enter your National code :");
        user.setNationalCode(new Scanner(System.in).nextLine());

        System.out.println("Wellcome to system");
        save(user);
        return user;
    }

    @Override
    public User logInUser() {
        int answer = 0;
        int wrongPassword = 0;
        int wrongUsername = 0;
        User user;
        String username;
        String password;
        while (true) {
            System.out.println("Enter your Username :");
            username = input.next();
            if (repository.existByUsername(username)) {
                user = repository.findByUsername(username);

                if (user.isBlocked()) {
                    System.out.println("Your account is blocked\n" +
                            "and you can not use the service\n\n" +
                            "Do you want to leave a message to unblock your account?\n" +
                            "1 : YES\n" +
                            "2 : NO");
                    answer = input.nextInt();
                    if (answer == 1) {
                        System.out.println("Enter your message..");
                        String massage = new Scanner(System.in).nextLine();
                        user.setMassage(massage);
                        save(user);
                    }
                    return null;
                } else {

                    while (true) {
                        System.out.println("please Enter your password : ");
                        password = input.next();
                        if (password.equals(user.getPassword())) {
                            System.out.println("The log in was successful !");
                            return user;
                        } else {
                            wrongPassword++;
                            if (wrongPassword == 3) {
                                user.setBlocked(true);
                                save(user);
                                System.out.println("Your account has been blocked  \n" +
                                        "due to incorrect password entry");
                                System.out.println("Do you want to leave a message for employees to unblock your account?\n" +
                                        "1 : YES\n" +
                                        "2 : NO");
                                answer = input.nextInt();
                                if (answer == 1) {
                                    System.out.println("Enter your message...");
                                    user.setMassage(new Scanner(System.in).nextLine());
                                    save(user);
                                }
                                return null;

                            }
                        }
                        System.out.println("wrong password!!\n" +
                                "please try again");

                    }

                }

            } else {
                System.out.println("This username is not available!!!\n" +
                        "please try again");
                wrongUsername++;
                if (wrongUsername == 3) break;


            }

        }

        return null;
    }

    @Override
    public User editProfileUser(User user) {
        Scanner input = new Scanner(System.in);
        System.out.println(user.toString());

        System.out.println("1 : Change Username  \n" +
                "2 : Change Password\n" +
                "3 : Change Age \n" +
                "4 : Change Phone Number\n" +
                "5 : Change Address\n" +
                "6 : Post a message to employees\n" +
                "7 : Exit");

        int a = input.nextInt();
        if (a == 1) {
            System.out.println("Enter new username : ");
            user.setUsername(input.next());

        } else if (a == 2) {

            System.out.println("Enter new password : ");
            user.setPassword(input.next());

        } else if (a == 3) {
            System.out.println("Enter uour Age : ");
            user.setAge(input.nextInt());

        } else if (a == 4) {
            System.out.println("Enter your phone number : ");
            user.setPhonenumber(input.next());

        } else if (a == 5) {

            System.out.println("Enter your address : ");
            user.setPassword(input.nextLine());

        } else if (a == 6) {

            System.out.println("Enter your massage : ");
            user.setPassword(input.nextLine());

        } else {
            return user;
        }
        save(user);
        return user;
    }

    @Override
    public void showProfileUser(User user) {
        System.out.println(user.toString());
    }

    @Override
    public void deleteAccount(User user) {
        //Complete user deletion
        String password;
        while (true) {
            System.out.println("please Enter your password : ");
            password = input.next();
            if (repository.existByPassword(user.getUsername(), password)) {

                delete(user);
                System.out.println("Complete user deletion was successful ! ");
                break;

            } else {

                System.out.println("password is Wrong!!!!");
            }
        }

    }

    @Override
    public boolean existByUsername(String username) {
        return repository.existByUsername(username);
    }

    @Override
    public boolean existByPassword(String username, String password) {
        return repository.existByPassword(username, password);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }


}