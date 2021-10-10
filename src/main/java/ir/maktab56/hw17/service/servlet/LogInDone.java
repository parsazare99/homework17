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

@WebServlet("/loginDone")
public class LogInDone extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(new EmployeeRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isCustomer = userService.existByUsername(username);
        boolean isEmployee = employeeService.existByUsername(username);

        if (isCustomer) {
            if (userService.existByPassword(username, password)) {
                User user = userService.findByUsernameAndPassword(username, password);
                System.out.println(" is user");
                out.println("<html><head></head><center>\n" +
                        "  <br><br><br>\n" +
                        "  <a href=\"buyTicket\"> Buy Ticket </a><br><br>\n" +
                        "  <a href=\"search\"> Search Ticket </a><br><br>\n" +
                        "  <a href=\"showProfile\"> Show My profile</a><br><br>\n" +
                        "  <a href=\"editProfile\"> Edit My profile </a><br><br>\n" +
                        "  <a href=\"showTickets\"> Show my Tickets </a><br><br>\n" +
                        "  <a href=\"refund\"> Refund Ticket </a><br><br>\n" +
                        "</center></body></html>");
            } else {

                out.println("<html><body bgcolor='red'>\n" +
                        "Username or Password is Wrong!!!<br><br>\n" +
                        "Please try Agine ....." +
                        "</body></html>\n");
            }


        } else if (isEmployee) {

            if (employeeService.existByPassword(username, password)) {
                Employee employee = employeeService.findByUsernameAndPassword(username, password);
                System.out.println("is employeee");
            } else {
                out.println("<html><body bgcolor='red'>\n" +
                        "Username or Password is Wrong!!!\n<br><br>" +
                        "Please try Agine ....." +
                        "</body></html>\n");
            }


        } else {
            out.println("<html><body bgcolor='red'>\n" +
                    "Username or Password is Wrong!!!<br><br>\n" +
                    "Please try Agine ....." +
                    "</body></html>\n");
        }


    }
}
