package ir.maktab56.hw17.service.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/register")
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.print("<center><br><br><h1>REGISTER PAGE</h1></center>");
        out.println("<form align=\"center\" action=\"registerDone\">\n" +
                "\n" + "<br><br><br>" +
                "    Enter your username :    <input type=\"text\" name=\"username\"><br><br>\n" +
                "    Enter your password : <input type=\"text\" name=\"password\"><br><br>\n" +
                " <h3> Register as a :</h3>\n" +
                "  <input id=\"customer\" name=\"userType\" type=\"radio\" value=\"customer\">\n" +
                "  <label for=\"customer\">Customer</label>\n<br><br>" +

                "  <input id=\"employee\" name=\"userType\" type=\"radio\" value=\"employee\">" +
                "  <label for=\"employee\">Emoloyee</label>\n<br><br>" +

                "    <input type=\"submit\" name=\"register\">\n" +
                "</form>");

    }
}
