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

@WebServlet("/editProfileDone")
public class EditProfileDone extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("Customer");


        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        String nationalCode = request.getParameter("nationalCode");
        String phoneNumber = request.getParameter("phoneNumber");
        String massage = request.getParameter("massage");

        System.out.println(age + "**" + age.length());
        if (firstName.length() > 0) {
            user.setFirstname(firstName);
        }
        if (lastName.length() > 0) {
            user.setLastname(lastName);
        }
        if (username.length() > 0) {
            user.setUsername(username);
        }
        if (password.length() > 0) {
            user.setPassword(password);
        }
        int a = 0;
        if (age.length() > 0) {
            a = Integer.parseInt(age);
            if (a > 0) {

                user.setAge(a);
            }

        }
        if (nationalCode.length() > 0) {
            user.setNationalCode(nationalCode);
        }
        if (phoneNumber.length() > 0) {
            user.setPhonenumber(phoneNumber);
        }
        if (massage.length() > 0) {
            user.setMassage(massage);
        }

        System.out.println(a + "***");
        userService.save(user);
        out.println("<br><h1>Changes saved successfully</h1><br><br>");
        out.println(user);
        out.println("<html><head></head><center>\n" +
                "  <br><br><br>\n" +
                "  <a href=\"showCustomerMenu\"> Back To Home </a><br><br>\n" +
                "</center></body></html>");

    }
}
