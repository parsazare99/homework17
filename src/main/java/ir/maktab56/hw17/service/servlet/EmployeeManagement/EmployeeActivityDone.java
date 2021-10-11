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

@WebServlet("/employeeActivityDone")
public class EmployeeActivityDone extends HttpServlet {
    TicketServiceImpl ticketService = new TicketServiceImpl(new TicketRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));
    EmployeeServiceImpl employeeService = new EmployeeServiceImpl(new EmployeeRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        // Employee employee = (Employee) session.getAttribute("employee");

        String employeeId = request.getParameter("employeeId");
        int id = Integer.parseInt(employeeId);
        Employee byId = employeeService.findById(id);

        if (byId.isActive()) {
            byId.setActive(false);

        } else {
            byId.setActive(true);

        }
        employeeService.save(byId);
        out.println("<br><br>" + byId.toString() + "<br><br><br>");


        out.println("<html><head></head><center>\n" +
                "  <br><br><br>\n" +
                "  <a href=\"showManagerMenu\"> Back To Home </a><br><br>\n" +
                "</center></body></html>");

    }


}

