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


@WebServlet("/editProfile")
public class EditProfile extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("Customer");

        out.println("<form align=\"center\" action=\"editProfileDone\">\n" +
                "<br>" +
                "<br><br> Firstname = " + user.getFirstname() + "----------------->" + "    Enter your Firstname :    <input type=\"text\" name=\"firstName\"><br><br>\n" +
                "<br><br> Lastname = " + user.getLastname() + "----------------->" + "    Enter your Lastname : <input type=\"text\" name=\"lastName\"><br><br>\n" +
                "<br><br> Username = " + user.getUsername() + "----------------->" + "    Enter your Username :    <input type=\"text\" name=\"username\"><br><br>\n" +
                "<br><br> Password = " + user.getPassword() + "----------------->" + "    Enter your Password :    <input type=\"text\" name=\"password\"><br><br>\n" +
                "<br><br> Age= " + user.getAge() + "----------------->" + "    Enter your Age :    <input type=\"text\" name=\"age\"><br><br>\n" +
                "<br><br> National Code = " + user.getNationalCode() + "----------------->" + "    Enter your NationalCode :    <input type=\"text\" name=\"nationalCode\"><br><br>\n" +
                "<br><br> Phone number = " + user.getPhonenumber() + "----------------->" + "    Enter your Phone Number :    <input type=\"text\" name=\"phoneNumber\"><br><br>\n" +
                "<br><br> massage = " + user.getMassage() + "----------------->" + "    Enter your Massage :    <input type=\"text\" name=\"massage\"><br><br>\n" +
                "<input type=\"submit\" name=\"submit\">\n" +
                "</form>");


    }
}
