package ir.maktab56.hw17.domain;

import ir.maktab56.hw17.base.domain.BaseEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Ticket extends BaseEntity<Integer> {

    public Ticket() {
        setSold(0);

    }

    public Ticket(String origin, String destination, Date departureDate, Company company, int price, int capacity, String time) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.company = company;
        this.price = price;
        this.capacity = capacity;
        this.time = time;
    }

    @Column(name = "ORIGIN")
    private String origin;


    @Column(name = "DESTINATION")
    private String destination;


    //tarikh harkat kardan
    @Column(name = "DEPARTURE_DATE")
    private Date departureDate;

    @Column(name = "TIME")
    private String time;

    @ManyToOne
    @JoinColumn(name = "COMPANY")
    private Company company;


    @Column(name = "PRICE")
    private int price;

    @Column(name = "SOLD")
    private int sold;


    @Column(name = "CAPACITY")
    private int capacity;


    @ManyToMany(cascade =CascadeType.ALL)
    private List<User> userList = new ArrayList<>();

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "  Id ='" + getId() + '\'' +
                "  origin ='" + origin + '\'' +
                " , Destination ='" + destination + '\'' +
                " , Date =" + departureDate +
                " , time ='" + time + '\'' +
                " , company =" + company.getName() +
                " , price =" + price +
                " , capacity =" + capacity +
                " , Sold =" + sold +
                '}';
    }
}
