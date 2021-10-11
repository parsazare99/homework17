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

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //   HttpSession session = request.getSession();

        out.println("<html><head></head><center>\n" +
                "  <br><br><br>\n" +
                "  <a href=\"buyTicket\"> Buy Ticket </a><br><br>\n" +//done
                "  <a href=\"increaceBalance\"> Increace Account Balance </a><br><br>\n"+//done
                "  <a href=\"showProfile\"> Show My profile</a><br><br>\n" +//done
                "  <a href=\"editProfile\"> Edit My profile </a><br><br>\n" +//done
                "  <a href=\"showMyTickets\"> Show my Tickets </a><br><br>\n"+//done
                "  <a href=\"refund\"> Refund Ticket </a><br><br>\n" +//done
                "</center></body></html>");

    }


}
