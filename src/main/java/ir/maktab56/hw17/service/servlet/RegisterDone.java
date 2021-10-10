package ir.maktab56.hw17.service.servlet;


import ir.maktab56.hw17.domain.Employee;
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] userTypes = request.getParameterValues("userType");

        out.println("wellcome " + username);


        if (userTypes[0].equals("customer")) {

            User user = new User(username, password);

            userService.save(user);


        } else {

            Employee employee = new Employee(username, password, false, false);
            employeeService.save(employee);


        }


//        HttpSession session = request.getSession();
//
//        session.setAttribute("username", request.getParameter("uname"));
//        session.setAttribute("password", request.getParameter("pword"));


        // String username = request.getParameter("username");
        // String password = request.getParameter("password");

        //request.setAttribute("pass", username);

//        out.println("welcome , " + name);
//
//        HttpSession session = request.getSession();
//        session.setAttribute("username", name);
//        out.println("<a href='profile'>visit your pofile</a>");

    }
}
