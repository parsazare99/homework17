package ir.maktab56.hw17.domain;

import ir.maktab56.hw17.base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.sql.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Profile extends BaseEntity<Integer> {


    @Column(name = "FIRST_NAME")
    private String firstname;

    @Column(name = "LAST_NAME")
    private String lastname;

    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "REGISTER_DATE", nullable = false)
    private Date registerDate;

    @Column(name = "AGE", nullable = false)
    private int age;

    @Column(name = "PHONE_NUMBER")
    private String phonenumber;

    @Column(name = "NATIONAL_CODE")
    private String nationalCode;

    @Column(name = "MASSAGE")
    private String massage;


    @Column(name = "IS_BLOCKED")
    private boolean isBlocked = false;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public String toString() {
        return ", ID ='" + getId() + '\'' +
                "firstname ='" + firstname + '\'' +
                ", lastname ='" + lastname + '\'' +
                ", username ='" + username + '\'' +
                ", password ='" + password + '\'' +
                ", age =" + age +
                ", nationalCode ='" + nationalCode + '\'' +
                ", massage ='" + massage + '\'';
        // ", isBlocked =" + isBlocked;
    }
}
