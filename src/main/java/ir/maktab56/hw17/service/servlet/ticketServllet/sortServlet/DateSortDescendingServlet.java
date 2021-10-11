package ir.maktab56.hw17.service.servlet.ticketServllet.sortServlet;


import ir.maktab56.hw17.domain.Ticket;
import ir.maktab56.hw17.service.imp.sort.DateSortDescending;
import ir.maktab56.hw17.service.imp.sort.PriceSortDescending;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@WebServlet("/descSortByDate")
public class DateSortDescendingServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        List<Ticket> ticketList = (List<Ticket>) session.getAttribute("ticketList");


        Collections.sort(ticketList, new DateSortDescending());

        for (Ticket t : ticketList) {
            out.println("<br>" + t.toString() + "<br>");
        }


        out.println("<form align=\"center\" action=\"doBuyTicket\">\n" +
                "<br><br><br>" +
                "Ticket ID :  <input type=\"text\" name=\"ticketId\"><br><br>\n" +
                "<input type=\"submit\" name=\"submit\">\n" +
                "</form>");

    }
}