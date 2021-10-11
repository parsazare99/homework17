package ir.maktab56.hw17.service.servlet.ticketServllet.TicketManagement;

import ir.maktab56.hw17.domain.Employee;
import ir.maktab56.hw17.domain.Ticket;
import ir.maktab56.hw17.repository.imp.TicketRepositoryImpl;
import ir.maktab56.hw17.service.imp.TicketServiceImpl;
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

@WebServlet("/deleteTicketDone")
public class DeleteTicketDone extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TicketServiceImpl ticketService = new TicketServiceImpl(new TicketRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String ticketId = request.getParameter("ticketId");


        int id = Integer.parseInt(ticketId);

        if (ticketService.existsById(id)) {
            ticketService.delete(ticketService.findById(id));
            out.println("<br><br><h2>Tickets successfully Deleted</h2>");
        } else {
            out.println("<br><br><h2>Ticket Not Found !!!</h2>");
        }

        out.println("<html><head></head><center>\n" +
                "  <br><br><br>\n" +
                "  <a href=\"showManagerMenu\"> Back To Home </a><br><br>\n" +
                "</center></body></html>");

    }


}
