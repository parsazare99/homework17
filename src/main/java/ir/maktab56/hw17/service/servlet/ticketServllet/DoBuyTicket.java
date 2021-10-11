package ir.maktab56.hw17.service.servlet.ticketServllet;

import ir.maktab56.hw17.domain.Ticket;
import ir.maktab56.hw17.domain.User;
import ir.maktab56.hw17.repository.imp.TicketRepositoryImpl;
import ir.maktab56.hw17.repository.imp.UserRepositoryImpl;
import ir.maktab56.hw17.service.imp.TicketServiceImpl;
import ir.maktab56.hw17.service.imp.UserServiceImpl;
import ir.maktab56.hw17.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

@WebServlet("/doBuyTicket")
public class DoBuyTicket extends HttpServlet {
    TicketServiceImpl ticketService = new TicketServiceImpl(new TicketRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));
    UserServiceImpl userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("Customer");
        String ticketId = request.getParameter("ticketId");
        int id = Integer.parseInt(ticketId);
        boolean isFree = false;
        int ticketCount = ticketService.getTicketCount(user);
        if (ticketCount == 10) {
            isFree = true;
        }

        buyTickets(id, user, out, isFree);

        out.println("<html><head></head><center>\n" +
                "  <br><br><br>\n" +
                "  <a href=\"showCustomerMenu\"> Back To Home </a><br><br>\n" +
                "</center></body></html>");
    }

    public void buyTickets(int id, User user, PrintWriter out, boolean isFree) {
        if (isFree) {
            if (ticketService.existsById(id)) {
                Ticket byId = ticketService.findById(id);
                if (byId.getSold() + 1 <= byId.getCapacity()) {

                            byId.setSold(byId.getSold() + 1);
                            byId.getUserList().add(user);
                            ticketService.save(byId);
                            out.println("<h1>The ticket purchase was successful .</h1><br>" +
                                    "<h1>And the ticket was provided for you for free</h1>");
                            System.out.println(byId.toString());

                } else {
                    out.println("<h1>All tickets are sold out!!!</h1>");
                }

            } else {
                System.out.println("this ticket  not found!! ");
            }

        } else {
            boolean sale = false;
            if (ticketService.existsById(id)) {
                Ticket byId = ticketService.findById(id);
//********************************************************************************************
                Date date1 = byId.getDepartureDate();
                Date date2 = Date.valueOf(LocalDate.now());
                if (date1.compareTo(date2) == 0) {
                    String time1 = LocalTime.now().toString();
                    double d1 = Double.parseDouble(time1.substring(0, 2));
                    String time2 = byId.getTime();
                    double d2 = Double.parseDouble(time2.substring(0, 2));
                    if (d2 - d1 <= 1 && d2 - d1 >= 0) {
                        sale = true;
                    }
                }
//**********************************************************************************************
                if (byId.getSold() + 1 <= byId.getCapacity()) {

                    if (sale) {

                        if (user.getAccountBalance() - (byId.getPrice() / 2) > 0) {

                            byId.setSold(byId.getSold() + 1);
                            byId.getUserList().add(user);
                            ticketService.save(byId);
                            user.setAccountBalance(user.getAccountBalance() - (byId.getPrice() / 2));
                            out.println("<h1>The ticket purchase was successful</h1>");
                            System.out.println(byId.toString());
                            userService.save(user);

                        } else {
                            out.println("<h1>Your Account Balance not Enough</h1>");

                        }

                    } else {
                        if (user.getAccountBalance() - byId.getPrice() > 0) {

                            byId.setSold(byId.getSold() + 1);
                            byId.getUserList().add(user);
                            ticketService.save(byId);
                            user.setAccountBalance(user.getAccountBalance() - byId.getPrice());
                            out.println("<h1>The ticket purchase was successful</h1>");
                            userService.save(user);
                            System.out.println(byId.toString());

                        } else {
                            out.println("<h1>Your Account Balance not Enough</h1>");

                        }
                    }

                } else {
                    out.println("<h1>All tickets are sold out!!!</h1>");
                }

            } else {
                System.out.println("this ticket  not found!! ");
            }
        }
    }


}

