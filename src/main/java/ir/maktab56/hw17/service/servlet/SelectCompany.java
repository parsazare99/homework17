package ir.maktab56.hw17.service.servlet;

import ir.maktab56.hw17.domain.Company;
import ir.maktab56.hw17.domain.Employee;
import ir.maktab56.hw17.repository.imp.CompanyRepositoryImpl;
import ir.maktab56.hw17.repository.imp.EmployeeRepositoryImpl;
import ir.maktab56.hw17.repository.imp.TicketRepositoryImpl;
import ir.maktab56.hw17.service.imp.CompanyServiceImpl;
import ir.maktab56.hw17.service.imp.EmployeeServiceImpl;
import ir.maktab56.hw17.service.imp.TicketServiceImpl;
import ir.maktab56.hw17.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/selectCompany")
public class SelectCompany extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CompanyServiceImpl companyService = new CompanyServiceImpl(new CompanyRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(new EmployeeRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        Employee employee = (Employee) request.getSession().getAttribute("employee");

        String[] companies = request.getParameterValues("company");
        String comp = companies[0];


        List<Company> all = companyService.findAll();

        for (Company c : all) {
            if (c.getName().equals(comp)) {
                employee.setCompany(c);
                System.out.println("**Done**");
                break;
            }
        }
        employeeService.save(employee);
        out.println("<html><body bgcolor='green'>" +
                "Your registration was successful<br><br>" +
                "Your account is not active.<br><br>" +
                " Wait for your account to be approved by the administrator<br><br>" +
                "</body></html>");

        out.println("<html><head></head><center>\n" +
                "  <br><br><br>\n" +
                "  <a href=\"index.jsp\"> Back To Home </a><br><br>\n" +
                "</center></body></html>");
    }
}
