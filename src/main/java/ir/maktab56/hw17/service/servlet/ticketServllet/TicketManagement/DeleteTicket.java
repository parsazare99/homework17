package ir.maktab56.hw17.service.servlet.ticketServllet.TicketManagement;

import ir.maktab56.hw17.domain.Employee;
import ir.maktab56.hw17.domain.Ticket;

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

@WebServlet("/deleteTicket")
public class DeleteTicket extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        List<Ticket> ticketList = employee.getCompany().getTicketList();
        if (ticketList.isEmpty()) {
            out.println("<h2>Ticket List Is Empty</h2>");
        } else {
            for (Ticket ticket : ticketList) {
                out.println("<br>" + ticket.toString() + "<br><br>");
            }
        }


        out.println("<form  action=\"deleteTicketDone\">\n" +
                "<br><br><br>" +
                "Ticket ID :  <input type=\"text\" name=\"ticketId\"><br><br>\n" +
                "<input type=\"submit\" name=\"submit\">\n" +
                "</form>");


        out.println("<html><head></head><center>\n" +
                "  <br><br><br>\n" +
                "  <a href=\"showManagerMenu\"> Back To Home </a><br><br>\n" +
                "</center></body></html>");

    }
}
