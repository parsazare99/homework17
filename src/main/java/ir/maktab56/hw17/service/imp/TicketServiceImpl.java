package ir.maktab56.hw17.service.imp;

import ir.maktab56.hw17.base.service.impl.BaseEntityServiceImpl;
import ir.maktab56.hw17.domain.Employee;
import ir.maktab56.hw17.domain.Ticket;
import ir.maktab56.hw17.domain.User;
import ir.maktab56.hw17.repository.TicketRepository;
import ir.maktab56.hw17.service.TicketService;
import ir.maktab56.hw17.service.imp.sort.*;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TicketServiceImpl extends BaseEntityServiceImpl<Ticket, Integer, TicketRepository>
        implements TicketService {


    public TicketServiceImpl(TicketRepository repository) {
        super(repository);
    }


    @Override
    public List<Ticket> findByOriginAndDestination(String s1, String s2) {
        return repository.findByOriginAndDestination(s1, s2);
    }

    @Override
    public Ticket createTicket(Employee employee) {
        Scanner s = new Scanner(System.in);
        Scanner i = new Scanner(System.in);

        Ticket ticket = new Ticket();
        ticket.setSold(0);
        ticket.setCompany(employee.getCompany());

        System.out.println("Enter Origin...");
        ticket.setOrigin(s.nextLine());


        System.out.println("Enter Destination...");
        ticket.setDestination(s.nextLine());

        System.out.println("Enter Departure Date...");
        ticket.setDepartureDate(Date.valueOf(s.nextLine()));

        System.out.println("Enter  Moving time...");
        ticket.setTime(s.nextLine());

        System.out.println("Enter Price...");
        ticket.setPrice(i.nextInt());

        System.out.println("Enter Capacity...");
        ticket.setCapacity(i.nextInt());

        save(ticket);

        return ticket;
    }

    @Override
    public Ticket editTicket(Employee employee) {
        Scanner s = new Scanner(System.in);
        showAllCompanyTicketForEmployee(employee);
        System.out.println("enter id of ticket that you want to edit :  ");
        int id = s.nextInt();
        if (existsById(id)) {
            Ticket ticket = findById(id);
            System.out.println("1 :  Edit origin\n" +
                    "2 :  Edit Destination\n " +
                    "3 :  Edit DepartureDate\n " +
                    "4 :  Edit time\n " +
                    "5 :  Edit price\n " +
                    "6 :  Edit capacity\n");

            int ans = new Scanner(System.in).nextInt();
            if (ans == 1) {
                System.out.println("Enter Origin...");
                ticket.setOrigin(s.nextLine());
            } else if (ans == 2) {
                System.out.println("Enter Destination...");
                ticket.setDestination(s.nextLine());
            } else if (ans == 3) {
                System.out.println("Enter Departure Date...");
                ticket.setDepartureDate(Date.valueOf(s.nextLine()));
            } else if (ans == 4) {
                System.out.println("Enter  Moving time...");
                ticket.setTime(s.nextLine());
            } else if (ans == 5) {
                System.out.println("Enter Price...");
                ticket.setPrice(s.nextInt());
            } else if (ans == 6) {
                System.out.println("Enter Capacity...");
                ticket.setCapacity(s.nextInt());
            }
            save(ticket);
            return ticket;


        } else {
            System.out.println("wrong id !!!");
        }
        return null;
    }

    @Override
    public void deleteTicket(Employee employee) {
        Scanner s = new Scanner(System.in);
        showAllCompanyTicketForEmployee(employee);
        System.out.println("enter id of ticket that you want to delete :  ");
        int id = s.nextInt();
        if (existsById(id)) {
            delete(findById(id));
        } else {
            System.out.println("wrong id !!!");
        }


    }

    @Override
    public void showAllCompanyTicketForEmployee(Employee employee) {
        List<Ticket> ticketList = employee.getCompany().getTicketList();
        for (Ticket ticket : ticketList) {
            System.out.println(ticket.toString());
        }

    }


//---------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------


    @Override
    public void showAllTickets(String s1, String s2) {
        List<Ticket> ticketList = getTicketByOriginAndDestination(s1, s2);
        if (ticketList.isEmpty()) {
            System.out.println("ticket not found ... ");
        } else {
            for (Ticket ticket : ticketList) {
                System.out.println(ticket.toString());
            }
        }
    }

    @Override
    public List<Ticket> getTicketByOriginAndDestination(String s1, String s2) {
        return repository.findByOriginAndDestination(s1, s2);
    }

    @Override
    public void buyTickets(int id, User user) {
        if (existsById(id)) {
            Ticket byId = findById(id);

            if (byId.getSold() + 1 <= byId.getCapacity()) {
                byId.setSold(byId.getSold() + 1);
                byId.getUserList().add(user);
                save(byId);
                System.out.println("The ticket purchase was successful");
                System.out.println(byId.toString());
            } else {
                System.out.println("All tickets are sold out!");
            }

        } else {
            System.out.println("this ticket  not found!! ");
        }

    }

    @Override
    public void buyTicketByOriginAndDestination(User user, String s1, String s2) {

        List<Ticket> ticketList = repository.findByOriginAndDestination(s1, s2);
        if (ticketList.size() == 0 || ticketList == null) {
            System.out.println("No ticket found...");
        } else {
            for (Ticket ticket : ticketList) {
                System.out.println(ticket.toString());
            }
            System.out.println("Enter ticket id you want to buy :");
            int id = new Scanner(System.in).nextInt();
            buyTickets(id, user);
        }

    }

    @Override
    public void refundTicket(User user) {
        Scanner s = new Scanner(System.in);
//        List<Ticket> ticketList = user.getTicketList();
//        for (Ticket ticket : ticketList) {
//            System.out.println(ticket.toString());
//        }
        showUserTickets(user);
        System.out.println("Enter the ID of the ticket you want to refund :");
        int id = s.nextInt();
        if (existsById(id)) {
            Ticket byId = findById(id);
            byId.setSold(byId.getSold() - 1);
            for (int i = 0; i < byId.getUserList().size(); i++) {
                if (byId.getUserList().get(i).getUsername().equals(user.getUsername())) {

                    byId.getUserList().remove(i);

                }

            }


            System.out.println("refund succesful ..");
            save(byId);
        } else {
            System.out.println("this ticket not found ");
        }
    }

    @Override
    public void showSortedTickets(String s1, String s2) {
        Scanner in = new Scanner(System.in);
        List<Ticket> ticketList = getTicketByOriginAndDestination(s1, s2);

        if (ticketList.isEmpty() || ticketList == null) {
            System.out.println("List is Emety\n");
        } else {

            System.out.println("1 : Order By Price \n" +
                    "2 : Order By Company Name\n " +
                    "3 : Order By Date ");
            int answer = in.nextInt();
            if (answer == 1) {
                System.out.println("1 : Ascending Sort\n " +
                        "2 : Descending Sort ");
                answer = in.nextInt();
                if (answer == 1) {
                    Collections.sort(ticketList, new PriceSortAscending());
                    for (Ticket a : ticketList) {
                        System.out.println(a);
                    }
                } else {
                    Collections.sort(ticketList, new PriceSortDescending());
                    for (Ticket a : ticketList) {
                        System.out.println(a);
                    }
                }
            } else if (answer == 2) {
                System.out.println("1 : Ascending Sort \n" +
                        "2 : Descending Sort ");
                answer = in.nextInt();
                if (answer == 1) {
                    Collections.sort(ticketList, new CompanySortAscending());
                    for (Ticket a : ticketList) {
                        System.out.println(a);
                    }
                } else {
                    Collections.sort(ticketList, new CompanySortDescending());
                    for (Ticket a : ticketList) {
                        System.out.println(a);
                    }
                }
            } else if (answer == 3) {
                System.out.println("1 : Ascending Sort\n" +
                        "2 : Descending Sort ");
                answer = in.nextInt();
                if (answer == 1) {
                    Collections.sort(ticketList, new DateSortAscending());
                    for (Ticket a : ticketList) {
                        System.out.println(a);
                    }
                } else {
                    Collections.sort(ticketList, new DateSortDescending());
                    for (Ticket a : ticketList) {
                        System.out.println(a);
                    }
                }
            }
            System.out.println("*********************************************");
        }


    }

    @Override
    public void showUserTickets(User user) {
        int k = 0;
//        List<Ticket> ticketList = user.getTicketList();
//        System.out.println(ticketList.size());
        List<Ticket> ticketList = findAll();
        if (ticketList.isEmpty()) {
            System.out.println("ticket list is empty!");
        } else {
            // System.out.println("ticket number " + ticketList.size());
            for (Ticket ticket : ticketList) {

                for (int i = 0; i < ticket.getUserList().size(); i++) {
                    if (ticket.getUserList().get(i).getUsername().equals(user.getUsername())) {
                        System.out.println(ticket.toString());
                        k++;
                    }

                }
            }
        }
        if (k == 0) {
            System.out.println("ticket list is empty!");
        }

    }
}
