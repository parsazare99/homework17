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
import java.util.List;
import java.util.Scanner;

@WebServlet("/doBuyTicket")
public class DoBuyTicket extends HttpServlet {
    TicketServiceImpl ticketService = new TicketServiceImpl(new TicketRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("Customer");
        String ticketId = request.getParameter("ticketId");
        int id = Integer.parseInt(ticketId);
        buyTickets(id, user, out);
        out.println("<html><head></head><center>\n" +
                "  <br><br><br>\n" +
                "  <a href=\"showCustomerMenu\"> Back To Home </a><br><br>\n" +
                "</center></body></html>");
    }

    public void buyTickets(int id, User user, PrintWriter out) {

        if (ticketService.existsById(id)) {
            Ticket byId = ticketService.findById(id);

            if (byId.getSold() + 1 <= byId.getCapacity()) {
                byId.setSold(byId.getSold() + 1);
                byId.getUserList().add(user);
                ticketService.save(byId);
                out.println("<h1>The ticket purchase was successful</h1>");
                System.out.println(byId.toString());
            } else {
                out.println("<h1>All tickets are sold out!!!</h1>");
            }

        } else {
            System.out.println("this ticket  not found!! ");
        }

    }


}

