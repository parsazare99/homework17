package ir.maktab56.hw17.service.servlet;


import ir.maktab56.hw17.domain.Employee;
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


@WebServlet("/registerDone")
public class RegisterDone extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(new EmployeeRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] userTypes = request.getParameterValues("userType");

        out.println("wellcome " + username);

        if (userTypes[0].equals("customer")) {

            User user = new User(username, password);

            userService.save(user);

            RequestDispatcher dispatcher = request.getRequestDispatcher("showCustomerMenu");
            dispatcher.forward(request, response);

        } else {

            Employee employee = new Employee(username, password, false, false);
            employeeService.save(employee);
            session.setAttribute("employee",employee);

            out.print("<center><br><br><h1>Select One Company</h1></center>");
            out.println("<form align=\"center\" action=\"selectCompany\">\n" +
                    "  <input id=\"a\" name=\"company\" type=\"radio\" value=\"Ata\">\n" +
                    "  <label for=\"a\">Ata</label>\n<br><br>" +
                    "  <input id=\"b\" name=\"company\" type=\"radio\" value=\"Varesh\">" +
                    "  <label for=\"b\">Varesh</label>\n<br><br>" +
                    "  <input id=\"c\" name=\"company\" type=\"radio\" value=\"Aseman\">\n" +
                    "  <label for=\"c\">Aseman</label>\n<br><br>" +
                    "  <input id=\"d\" name=\"company\" type=\"radio\" value=\"Taban\">" +
                    "  <label for=\"d\">Taban</label>\n<br><br>" +
                    "  <input id=\"e\" name=\"company\" type=\"radio\" value=\"Saha\">\n" +
                    "  <label for=\"e\">Saha</label>\n<br><br>" +
                    "  <input id=\"f\" name=\"company\" type=\"radio\" value=\"Caspian\">" +
                    "  <label for=\"f\">Caspian</label>\n<br><br>" +
                    "    <input type=\"submit\" name=\"submit\">\n" +
                    "</form>");

        }

    }
}
