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

@WebServlet("/showEmployeeMenu")
public class ShowEmployeerMenu extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // HttpSession session = request.getSession();

        out.println("<html><head></head><center>\n" +
                "  <br><br><br>\n" +
                "  <a href=\"createTicket\"> Create Ticket </a><br><br>\n" +//done
                "  <a href=\"deleteTicket\"> Delete Ticket </a><br><br>\n" +//done
                "  <a href=\"index.jsp\"> EXIT </a><br><br>\n" +
                "</center></body></html>");

    }


}
