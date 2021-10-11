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

@WebServlet("/buyTicket")
public class BuyTicket extends HttpServlet {
    TicketServiceImpl ticketService = new TicketServiceImpl(new TicketRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("Customer");

        out.print("<center><br><br><h1>BUY TICKET</h1></center>");


        out.println("<form align=\"center\" action=\"showTickets\">\n" +
                "\n" + "<br><br><br>" +
                "    ORIGIN :    <input type=\"text\" name=\"origin\"><br><br>\n" +
                "    DESTINATION : <input type=\"text\" name=\"destination\"><br><br>\n" +
                "    <input type=\"submit\" name=\"Search\">\n" +
                "</form>");



    }


}

