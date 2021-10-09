package ir.maktab56.hw17.util;

import ir.maktab56.hw17.repository.CompanyRepository;
import ir.maktab56.hw17.repository.EmployeeRepository;
import ir.maktab56.hw17.repository.TicketRepository;
import ir.maktab56.hw17.repository.UserRepository;
import ir.maktab56.hw17.repository.imp.CompanyRepositoryImpl;
import ir.maktab56.hw17.repository.imp.EmployeeRepositoryImpl;
import ir.maktab56.hw17.repository.imp.TicketRepositoryImpl;
import ir.maktab56.hw17.repository.imp.UserRepositoryImpl;
import ir.maktab56.hw17.service.CompanyService;
import ir.maktab56.hw17.service.EmployeeService;
import ir.maktab56.hw17.service.TicketService;
import ir.maktab56.hw17.service.UserService;
import ir.maktab56.hw17.service.imp.CompanyServiceImpl;
import ir.maktab56.hw17.service.imp.EmployeeServiceImpl;
import ir.maktab56.hw17.service.imp.TicketServiceImpl;
import ir.maktab56.hw17.service.imp.UserServiceImpl;

import javax.persistence.EntityManager;

public class ApplicationContext {

    private static final UserRepository userRepository;
    private static final EmployeeRepository employeeRepository;
    private static final TicketRepository ticketRepository;
    private static final CompanyRepository companyRepository;

    private static final UserService userService;
    private static final EmployeeService employeeService;
    private static final TicketService ticketService;
    private static final CompanyService companyService;


    private ApplicationContext() {
    }

    static {
        EntityManager entityManager = HibernateUtil.getEntityMangerFactory().createEntityManager();
        userRepository = new UserRepositoryImpl(entityManager);
        userService = new UserServiceImpl(userRepository);

        employeeRepository = new EmployeeRepositoryImpl(entityManager);
        employeeService = new EmployeeServiceImpl(employeeRepository);


        ticketRepository = new TicketRepositoryImpl(entityManager);
        ticketService = new TicketServiceImpl(ticketRepository);

        companyRepository = new CompanyRepositoryImpl(entityManager);
        companyService = new CompanyServiceImpl(companyRepository);
    }

    public static UserService getUserService() {
        return userService;
    }

    public static EmployeeService getEmployeeService() {
        return employeeService;
    }

    public static CompanyService getCompanyService() {
        return companyService;
    }

    public static TicketService getTicketService() {
        return ticketService;
    }
}