package ir.maktab56.hw17.service.servlet;


import ir.maktab56.hw17.domain.Employee;
import ir.maktab56.hw17.domain.Profile;
import ir.maktab56.hw17.domain.User;
import ir.maktab56.hw17.repository.imp.EmployeeRepositoryImpl;
import ir.maktab56.hw17.repository.imp.UserRepositoryImpl;
import ir.maktab56.hw17.service.imp.EmployeeServiceImpl;
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

@WebServlet("/showCustomerMenu")
public class ShowCustomerMenu extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));
    EmployeeServiceImpl employeeService = new EmployeeServiceImpl(new EmployeeRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        out.println("<html><head></head><center>\n" +
                "  <br><br><br>\n" +
                "  <a href=\"buyTicket\"> Buy Ticket </a><br><br>\n" +
                "  <a href=\"search\"> Search Ticket </a><br><br>\n" +
                "  <a href=\"showProfile\"> Show My profile</a><br><br>\n" +
                "  <a href=\"editProfile\"> Edit My profile </a><br><br>\n" +
                "  <a href=\"showTickets\"> Show my Tickets </a><br><br>\n" +
                "  <a href=\"refund\"> Refund Ticket </a><br><br>\n" +
                "</center></body></html>");

    }


}
