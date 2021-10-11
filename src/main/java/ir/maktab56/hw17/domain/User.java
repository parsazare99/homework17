package ir.maktab56.hw17.domain;

import javax.persistence.Entity;
import java.sql.Date;
import java.time.LocalDate;


@Entity
public class User extends Profile {

    public User() {
        setRegisterDate(Date.valueOf(LocalDate.now()));
    }

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
        setRegisterDate(Date.valueOf(LocalDate.now()));
    }

    @Override
    public String toString() {
        return
                "<br><br> ID = '" + getId() + '\'' +
                        "<br><br> Firstname = '" + getFirstname() + '\'' +
                        "<br><br> Lastname = '" + getLastname() + '\'' +
                        "<br><br> Username = '" + getUsername() + '\'' +
                        "<br><br> Password = '" + getPassword() + '\'' +
                        "<br><br> Age= '" + getAge() + '\'' +
                        "<br><br> National Code = '" + getNationalCode() + '\'' +
                        "<br><br> Phone number = '" + getPhonenumber() + '\'' +
                        "<br><br> isBlocked = " + isBlocked() +
                        "<br><br> massage = '" + getMassage() + '\'';
    }
}
