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

@WebServlet("/showTickets")
public class ShowTickets extends HttpServlet {
    TicketServiceImpl ticketService = new TicketServiceImpl(new TicketRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("Customer");


        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        List<Ticket> ticketList = ticketService.getTicketByOriginAndDestination(origin, destination);
        session.setAttribute("origin", origin);
        session.setAttribute("destination", destination);
        session.setAttribute("ticketList", ticketList);

        if (ticketList.size() > 0) {
            for (Ticket t : ticketList) {
                out.println("<br>" + t.toString() + "<br>");
            }
            out.println("<html><head></head><center>\n" +
                    "  <br><br><br>" +
                    "  <a href=\"acsSortByPrice\">Acs Sort By Price </a><br><br>\n" +
                    "  <a href=\"descSortByPrice\">Desc Sort By Price </a><br><br>\n" +
                    "  <a href=\"acsSortByDate\">Acs Sort By Date </a><br><br>\n" +
                    "  <a href=\"descSortByDate\">Desc Sort By Date </a><br><br>\n" +
                    "  <a href=\"acsSortByCompany\">Acs Sort By Company</a><br><br>\n" +
                    "  <a href=\"descSortByCompany\">Desc Sort By Company</a><br><br>\n" +
                    "</center></body></html>");

            out.println("<form  action=\"doBuyTicket\">\n" +
                    "<br><br><br>" +
                    "Ticket ID :  <input type=\"text\" name=\"ticketId\"><br><br>\n" +
                    "<input type=\"submit\" name=\"submit\">\n" +
                    "</form>");

        } else {
            out.println("<html><body bgcolor='red'>" +
                    "<br><br>this ticket  not found!!" +
                    "</body></html>");
        }


    }


}

