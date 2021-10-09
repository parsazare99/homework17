package ir.maktab56.hw17.service;

import ir.maktab56.hw17.base.service.BaseEntityService;
import ir.maktab56.hw17.domain.Employee;
import ir.maktab56.hw17.domain.Ticket;
import ir.maktab56.hw17.domain.User;

import java.util.List;

public interface TicketService extends BaseEntityService<Ticket, Integer> {

    Ticket createTicket(Employee employee);

    Ticket editTicket(Employee employee);

    void deleteTicket(Employee employee);

    void showAllCompanyTicketForEmployee(Employee employee);

    //-----------------------------------------------------

    void showUserTickets(User user);
    void showSortedTickets(String s1, String s2);

    void showAllTickets(String s1, String s2);

    List<Ticket> getTicketByOriginAndDestination(String s1, String s2);

    void buyTickets(int id, User user);

    void buyTicketByOriginAndDestination(User user, String s1, String s2);


    void refundTicket(User user);

}
