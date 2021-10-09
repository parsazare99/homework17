package ir.maktab56.hw17.domain;

import ir.maktab56.hw17.base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company extends BaseEntity<Integer> {
    public Company() {
    }

    public Company(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @Column(name = "COMPANY_NAME")
    private String name;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;


    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "MANAGER_NAME")
    private String managername;


    @OneToMany(mappedBy = "company")
    private List<Ticket> ticketList = new ArrayList<>();


    @OneToMany(mappedBy = "company")
    private List<Employee> employeeList = new ArrayList<>();

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManagername() {
        return managername;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public void setManagername(String managername) {
        this.managername = managername;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Company{" +
                " Id ='" + getId() + '\'' +
                " name ='" + name + '\'' +
                ", phoneNumber ='" + phoneNumber + '\'' +
                ", address ='" + address + '\'' +
                ", managername ='" + managername + '\'' +
                '}';
    }
}
