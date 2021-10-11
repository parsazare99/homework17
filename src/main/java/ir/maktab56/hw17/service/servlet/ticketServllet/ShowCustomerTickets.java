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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/showMyTickets")
public class ShowCustomerTickets extends HttpServlet {
    TicketServiceImpl ticketService = new TicketServiceImpl(new TicketRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // UserServiceImpl userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("Customer");
        List<Ticket> ticketList = showUserTickets(user);
        if (ticketList.isEmpty()) {
            out.println("<h2>Ticket List Is Empty</h2>");
        } else {
            for (Ticket ticket : ticketList) {
                out.println("<br>" + ticket.toString() + "<br><br>");
            }
        }

        out.println("<html><head></head><center>\n" +
                "  <br><br><br>\n" +
                "  <a href=\"showCustomerMenu\"> Back To Home </a><br><br>\n" +
                "</center></body></html>");

    }

    public List<Ticket> showUserTickets(User user) {
        List<Ticket> ticketList = ticketService.findAll();
        for (Ticket ticket : ticketList) {

            for (int i = 0; i < ticket.getUserList().size(); i++) {
                if (ticket.getUserList().get(i).getUsername().equals(user.getUsername())) {
                    ticketList.add(ticket);
                }

            }
        }

        return ticketList;
    }


}
