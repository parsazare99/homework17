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


//    @ManyToMany(cascade = CascadeType.ALL )
//    private List<Ticket> ticketList = new ArrayList<>();

//    public List<Ticket> getTicketList() {
//        return ticketList;
//    }
//
//    public void setTicketList(List<Ticket> ticketList) {
//        this.ticketList = ticketList;
//    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", isBlocked=" + isBlocked() +
                ", massage='" + getMassage() + '\'' +
                '}';
    }
}
