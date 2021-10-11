package ir.maktab56.hw17.service.servlet.ticketServllet;


import ir.maktab56.hw17.domain.Ticket;
import ir.maktab56.hw17.domain.User;
import ir.maktab56.hw17.repository.imp.TicketRepositoryImpl;
import ir.maktab56.hw17.service.imp.TicketServiceImpl;
import ir.maktab56.hw17.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@WebServlet("/refundTicketDone")
public class RefundTicketDone extends HttpServlet {
    TicketServiceImpl ticketService = new TicketServiceImpl(new TicketRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("Customer");

        String ticketId = request.getParameter("ticketId");
        int id = Integer.parseInt(ticketId);
        refundTicket(user, id, out);
        out.println("<html><head></head><center>\n" +
                "  <br><br><br>\n" +
                "  <a href=\"showCustomerMenu\"> Back To Home </a><br><br>\n" +
                "</center></body></html>");


    }

    public void refundTicket(User user, int id, PrintWriter out) {

        if (ticketService.existsById(id)) {
            Ticket byId = ticketService.findById(id);

            for (int i = 0; i < byId.getUserList().size(); i++) {
                if (byId.getUserList().get(i).getUsername().equals(user.getUsername())) {
                    byId.getUserList().remove(i);
                }
            }
            if (byId.getSold() - 1 >= 0) {
                byId.setSold(byId.getSold() - 1);
            }
            ticketService.save(byId);
            out.println("<h2>refund succesful ..</h2>");

        } else {
            out.println("<h2>this ticket not found!!!</h2> ");
        }
    }


}

