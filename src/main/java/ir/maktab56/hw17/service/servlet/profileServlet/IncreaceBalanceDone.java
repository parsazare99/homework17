package ir.maktab56.hw17.service.servlet.profileServlet;

import ir.maktab56.hw17.domain.User;
import ir.maktab56.hw17.repository.imp.UserRepositoryImpl;
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


@WebServlet("/increaceBalanceDone")
public class IncreaceBalanceDone extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("Customer");

        long balance = Long.parseLong(request.getParameter("balance"));

        user.setAccountBalance(user.getAccountBalance() + balance);
        userService.save(user);
        out.println("<br><br><h2>Account charging completed successfully</h2>");

        out.println("<html><head></head><center>\n" +
                "  <br><br><br>\n" +
                "  <a href=\"showCustomerMenu\"> Back To Home </a><br><br>\n" +
                "</center></body></html>");

    }
}
