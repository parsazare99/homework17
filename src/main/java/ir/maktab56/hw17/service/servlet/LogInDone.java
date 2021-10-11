package ir.maktab56.hw17.service.servlet;


import ir.maktab56.hw17.domain.Employee;
import ir.maktab56.hw17.domain.Profile;
import ir.maktab56.hw17.domain.User;
import ir.maktab56.hw17.repository.imp.EmployeeRepositoryImpl;
import ir.maktab56.hw17.repository.imp.UserRepositoryImpl;
import ir.maktab56.hw17.service.imp.EmployeeServiceImpl;
import ir.maktab56.hw17.service.imp.UserServiceImpl;
import ir.maktab56.hw17.util.HibernateUtil;

import javax.servlet.RequestDispatcher;
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
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isCustomer = userService.existByUsername(username);
        boolean isEmployee = employeeService.existByUsername(username);

        if (isCustomer) {
            if (userService.existByPassword(username, password)) {
                User user = userService.findByUsernameAndPassword(username, password);
                session.setAttribute("Customer", user);
                System.out.println(" is user");

                RequestDispatcher dispatcher = request.getRequestDispatcher("showCustomerMenu");
                dispatcher.forward(request, response);
 //               response.sendRedirect("showCustomerMenu");

            } else {

                out.println("<html><body bgcolor='red'>\n" +
                        "Username or Password is Wrong!!!<br><br>\n" +
                        "Please try Agine ....." +
                        "</body></html>\n");
            }


        } else if (isEmployee) {

            if (employeeService.existByPassword(username, password)) {
                Employee employee = employeeService.findByUsernameAndPassword(username, password);
                session.setAttribute("employee", employee);
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
