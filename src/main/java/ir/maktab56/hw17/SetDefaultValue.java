package ir.maktab56.hw17;

import ir.maktab56.hw17.domain.Company;
import ir.maktab56.hw17.domain.Ticket;
import ir.maktab56.hw17.repository.imp.CompanyRepositoryImpl;
import ir.maktab56.hw17.repository.imp.EmployeeRepositoryImpl;
import ir.maktab56.hw17.repository.imp.TicketRepositoryImpl;
import ir.maktab56.hw17.service.imp.CompanyServiceImpl;
import ir.maktab56.hw17.service.imp.EmployeeServiceImpl;
import ir.maktab56.hw17.service.imp.TicketServiceImpl;
import ir.maktab56.hw17.service.imp.sort.PriceSortDescending;
import ir.maktab56.hw17.util.HibernateUtil;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SetDefaultValue {
    public static void main(String[] args) {
        new EmployeeServiceImpl(new EmployeeRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager())).addDefaultManager();
        CompanyServiceImpl companyService = new CompanyServiceImpl(new CompanyRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));
        TicketServiceImpl ticketService = new TicketServiceImpl(new TicketRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

        List<Company> all = companyService.findAll();
        Random random = new Random();
        Ticket ticket;
        List<String> city = Arrays.asList("Esfahan", "Amol", "Sari", "Tehran", "Mashhad", "Yazd", "Ahvaz", "Abadan"
                , "Tabriz", "Babol", "Boshehr", "Shiraz");

        for (int i = 0; i < 50; i++) {
            StringBuilder builder = new StringBuilder().append(2021).append("-").append(random.nextInt(3) +10).append("-").append(random.nextInt(20) + 11);
            System.out.println(builder);
            StringBuilder time = new StringBuilder().append(random.nextInt(24)).append(":").append(random.nextInt(60));

            ticket = new Ticket(
                    city.get(random.nextInt(11))
                    , city.get(random.nextInt(11)),
                    Date.valueOf(String.valueOf(builder)),
                    all.get(random.nextInt(6)), random.nextInt(100) + 50,
                    random.nextInt(20) + 30, String.valueOf(time)
            );
            ticketService.save(ticket);
        }

        List<Ticket> tickets = ticketService.findAll();
        for (Ticket ticket1 : tickets) {
            System.out.println(ticket1.toString());
        }
        Collections.sort(tickets, new PriceSortDescending());
        System.out.println();
        System.out.println();
        for (Ticket ticket1 : tickets) {
            System.out.println(ticket1.toString());
        }
    }


}
