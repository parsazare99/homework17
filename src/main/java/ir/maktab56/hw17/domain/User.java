package ir.maktab56.hw17.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;
import java.time.LocalDate;


@Entity
public class User extends Profile {

    public User() {
        setRegisterDate(Date.valueOf(LocalDate.now()));
        setAccountBalance(10000l);
    }

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
        setRegisterDate(Date.valueOf(LocalDate.now()));
        setAccountBalance(10000l);
    }

    @Column(name = "ACCOUNT_BALANCE")
    private long accountBalance;

    public long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(long accountBalance) {
        this.accountBalance = accountBalance;
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
