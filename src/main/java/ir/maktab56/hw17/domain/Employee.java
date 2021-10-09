package ir.maktab56.hw17.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Employee extends Profile {


    public Employee() {
        setRegisterDate(Date.valueOf(LocalDate.now()));

    }

    public Employee(String firstname, String lastname, String username, String password, boolean isActive, boolean isManager, int age) {
        setFirstname(firstname);
        setLastname(lastname);
        setUsername(username);
        setPassword(password);
        this.isActive = isActive;
        this.isManager = isManager;
        setAge(age);
        setRegisterDate(Date.valueOf(LocalDate.now()));

    }

    @Column(name = "IS_ACTIVE")
    private boolean isActive = false;


    @Column(name = "IS_MANAGER")
    private boolean isManager = false;

    @ManyToOne
    @JoinColumn(name = "COMPANY")
    private Company company;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Employee{" + super.toString() +
                " , isActive=" + isActive +
                " , company=" + company.getName() +
                "} ";
    }
}
