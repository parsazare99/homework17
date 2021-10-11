package ir.maktab56.hw17.service.servlet.profileServlet;

import ir.maktab56.hw17.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/increaceBalance")
public class IncreaceBalance extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("Customer");

        out.println("<form align=\"center\" action=\"increaceBalanceDone\">\n" +
                "Enter the amount you want to charge : <input type=\"text\" name=\"balance\"><br><br>\n" +
                "<input type=\"submit\" name=\"submit\">\n" +
                "</form>");


    }
}
