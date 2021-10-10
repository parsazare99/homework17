package ir.maktab56.hw17.service.servlet;


import ir.maktab56.hw17.repository.imp.UserRepositoryImpl;
import ir.maktab56.hw17.service.imp.UserServiceImpl;
import ir.maktab56.hw17.util.HibernateUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/registerDone")
public class registerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

//        UserServiceImpl userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        servletResponse.setContentType("text/html");
        PrintWriter out = servletResponse.getWriter();

      //  String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");


        if (password.length() >= 8) {

            filterChain.doFilter(servletRequest, servletResponse);


        } else {

            out.println("<html><body bgcolor='red'>\n" +
                    "The password is short\n" +
                    "Password length must be 8 or more" +
                    "</body></html>\n");
        }


    }


}
