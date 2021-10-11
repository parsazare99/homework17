package ir.maktab56.hw17.service.servlet.EmployeeManagement;

import ir.maktab56.hw17.domain.Employee;
import ir.maktab56.hw17.domain.Ticket;
import ir.maktab56.hw17.domain.User;
import ir.maktab56.hw17.repository.imp.EmployeeRepositoryImpl;
import ir.maktab56.hw17.repository.imp.TicketRepositoryImpl;
import ir.maktab56.hw17.repository.imp.UserRepositoryImpl;
import ir.maktab56.hw17.service.imp.EmployeeServiceImpl;
import ir.maktab56.hw17.service.imp.TicketServiceImpl;
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
import java.util.List;
import java.util.Scanner;

@WebServlet("/employeeActivity")
public class EmployeeActivity extends HttpServlet {
    TicketServiceImpl ticketService = new TicketServiceImpl(new TicketRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));
    EmployeeServiceImpl employeeService = new EmployeeServiceImpl(new EmployeeRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        List<Employee> employeeList = employee.getCompany().getEmployeeList();
        if (employeeList.size() < 2 || employeeList == null) {
            out.println("<h2>There are no employees in this Company</h2>");
        } else {
            for (int i = 0; i < employeeList.size(); i++) {
                if (!employeeList.get(i).isManager()) {
                    out.println(employeeList.get(i).toString() + "<br><br><br>");
                }
            }
        }

        out.println("<form  action=\"employeeActivityDone\">\n" +
                "<br><br><br>" +
                "Employee ID :  <input type=\"text\" name=\"employeeId\"><br><br>\n" +
                "<input type=\"submit\" name=\"submit\">\n" +
                "</form>");










//        out.println("<html><head></head><center>\n" +
//                "  <br><br><br>\n" +
//                "  <a href=\"showManagerMenu\"> Back To Home </a><br><br>\n" +
//                "</center></body></html>");

    }


}

