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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Scanner;


@WebServlet("/createTicket")
public class CreateTicket extends HttpServlet {
    TicketServiceImpl ticketService = new TicketServiceImpl(new TicketRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<form align=\"center\" action=\"createTicketDone\">\n" +
                " Enter Origin :    <input type=\"text\" name=\"origin\"><br><br>\n" +
                " Enter Destination : <input type=\"text\" name=\"destination\"><br><br>\n" +
                " Enter Date :    <input type=\"text\" name=\"date\"><br><br>\n" +
                " Enter Time :    <input type=\"text\" name=\"time\"><br><br>\n" +
                " Enter Price :    <input type=\"text\" name=\"price\"><br><br>\n" +
                " Enter capacity :    <input type=\"text\" name=\"capacity\"><br><br>\n" +
                "<input type=\"submit\" name=\"submit\">\n" +
                "</form>");
    }


}
