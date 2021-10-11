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


@WebServlet("/createTicketDone")
public class CreateTicketDone extends HttpServlet {

    TicketServiceImpl ticketService = new TicketServiceImpl(new TicketRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        Ticket ticket = new Ticket();
        ticket.setSold(0);
        ticket.setCompany(employee.getCompany());
        ticket.setOrigin(request.getParameter("origin"));
        ticket.setDestination(request.getParameter("destination"));
        ticket.setDepartureDate(Date.valueOf(request.getParameter("date")));
        ticket.setTime(request.getParameter("time"));
        ticket.setPrice(Integer.parseInt(request.getParameter("price")));
        ticket.setCapacity(Integer.parseInt(request.getParameter("capacity")));
        ticketService.save(ticket);
        out.println("<br><br><h2>Tickets successfully saved and published</h2>");
        if (employee.isManager()) {
            out.println("<html><head></head><center>\n" +
                    "  <br><br><br>\n" +
                    "  <a href=\"showManagerMenu\"> Back To Home </a><br><br>\n" +
                    "</center></body></html>");
        } else {
            out.println("<html><head></head><center>\n" +
                    "  <br><br><br>\n" +
                    "  <a href=\"showEmployeeMenu\"> Back To Home </a><br><br>\n" +
                    "</center></body></html>");
        }


    }


}
