package ir.maktab56.hw17.service.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/login")
public class LogIn extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        out.print("<center><br><br><h1>LOG IN PAGE</h1></center>");

        out.println("<form align=\"center\" action=\"loginDone\">\n" +
                "\n" + "<br><br><br>" +
                "    Enter your username :    <input type=\"text\" name=\"username\"><br><br>\n" +
                "    Enter your password : <input type=\"text\" name=\"password\"><br><br>\n" +
                "    <input type=\"submit\" name=\"login\">\n" +
                "\n" +
                "\n" +
                "</form>");

    }
}
