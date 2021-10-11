package ir.maktab56.hw17;


import ir.maktab56.hw17.domain.*;
import ir.maktab56.hw17.repository.imp.CompanyRepositoryImpl;
import ir.maktab56.hw17.repository.imp.EmployeeRepositoryImpl;
import ir.maktab56.hw17.repository.imp.TicketRepositoryImpl;
import ir.maktab56.hw17.service.imp.CompanyServiceImpl;
import ir.maktab56.hw17.service.imp.EmployeeServiceImpl;
import ir.maktab56.hw17.service.imp.TicketServiceImpl;
import ir.maktab56.hw17.service.imp.sort.*;
import ir.maktab56.hw17.util.ApplicationContext;
import ir.maktab56.hw17.util.HibernateUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;


public class MainApplication {
    public static void main(String[] args) {
        new EmployeeServiceImpl(new EmployeeRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager())).addDefaultManager();
        CompanyServiceImpl companyService = new CompanyServiceImpl(new CompanyRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));
        TicketServiceImpl ticketService = new TicketServiceImpl(new TicketRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

        Scanner input = new Scanner(System.in);

        System.out.println(Date.valueOf(LocalDate.now()));
        System.out.println("<><><><><><><><><> W E L C O M E <><><><><><><><><><>");
        while (true) {
            System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
            System.out.println("1 : To register on the system\n" +
                    "2 : Log in to system\n"
                    + "3 : Exit ");
            int answer = input.nextInt();
            if (answer == 1) {
                System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                System.out.println("1 : Register as a customer\n" +
                        "2 : Register as a  employee\n"
                        + "3 : Exit ");
                answer = input.nextInt();

                if (answer == 1) {
                    System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                    ApplicationContext.getUserService().registerUser();
                    System.out.println("******You must be logged in system to use servises******");

                } else if (answer == 2) {
                    System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                    ApplicationContext.getEmployeeService().registerEmployee();
                    System.out.println("******You must be logged in system to use servises******");
                } else break;


            } else if (answer == 2) {
                while (true) {
                    System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                    System.out.println("1 : Log In as a customer\n" +
                            "2 : Log In as a Company employee or Company manager\n"
                            + "3 : Exit ");
                    answer = input.nextInt();
                    if (answer == 1) {
                        User user = ApplicationContext.getUserService().logInUser();

                        if (user != null && !user.isBlocked()) {
                            while (true) {
                                System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                System.out.println("1 : Buy Tickets\n" +
                                        "2 : search Tickets\n" +
                                        "3 : Show My profile\n" +
                                        "4 : Edit My profile\n" +
                                        "5 : show my Tickets\n" +
                                        "6 : refund Ticket\n" +
                                        "7 : Exit ");

                                answer = input.nextInt();
                                if (answer == 1) {
                                    System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                    System.out.println("Enter Origin :    ");
                                    String s1 = new Scanner(System.in).nextLine();
                                    System.out.println("Enter Destination :   ");
                                    String s2 = new Scanner(System.in).nextLine();
                                    ApplicationContext.getTicketService().buyTicketByOriginAndDestination(user, s1, s2);

                                } else if (answer == 2) {
                                    System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                    System.out.println("Enter Origin : ");
                                    String s1 = new Scanner(System.in).nextLine();
                                    System.out.println("Enter Destination : ");
                                    String s2 = new Scanner(System.in).nextLine();
                                    while (true) {
                                        System.out.println("1 : Normal show \n" +
                                                "2 : View sorted\n" +
                                                "3 : Exit ");
                                        answer = input.nextInt();
                                        if (answer == 1) {
                                            System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                            ApplicationContext.getTicketService().showAllTickets(s1, s2);
                                        } else if (answer == 2) {
                                            System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                            ApplicationContext.getTicketService().showSortedTickets(s1, s2);
                                        } else break;
                                    }


                                } else if (answer == 3) {
                                    System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                    ApplicationContext.getUserService().showProfileUser(user);
                                } else if (answer == 4) {
                                    System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                    ApplicationContext.getUserService().editProfileUser(user);
                                } else if (answer == 5) {
                                    System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                    ApplicationContext.getTicketService().showUserTickets(user);
                                } else if (answer == 6) {

                                    System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                    ApplicationContext.getTicketService().refundTicket(user);

                                } else break;

                            }
                        } else {
                            if (user == null) {
                                System.out.println("log in error");
                            }

                            break;
                        }


                    } else if (answer == 2) {
                        Employee employee = ApplicationContext.getEmployeeService().logInEmployee();


                        if (employee != null) {
                            if (employee.isActive() && !employee.isBlocked()) {
                                while (true) {
                                    if (employee.isManager()) {
                                        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                        System.out.println("1 : active/de active employee\n" +
                                                "2 : add Ticket to system\n" +
                                                "3 : show All Employees\n" +
                                                "4 : Exit ");
                                        answer = input.nextInt();
                                        if (answer == 1) {
                                            System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                            ApplicationContext.getEmployeeService().managerService(employee);
                                        } else if (answer == 2) {
                                            System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                            ApplicationContext.getTicketService().createTicket(employee);
                                        } else if (answer == 3) {
                                            System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                            ApplicationContext.getEmployeeService().showAllEmployeesForManager(employee);
                                        } else break;


                                    } else {
                                        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                        System.out.println("1 : Create Ticket \n" +
                                                "2 : Delete Ticket\n" +
                                                "3 : Exit "
                                        );
                                        answer = input.nextInt();
                                        if (answer == 1) {
                                            System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                            ApplicationContext.getTicketService().createTicket(employee);
                                        } else if (answer == 2) {
                                            System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
                                            ApplicationContext.getTicketService().deleteTicket(employee);

                                        } else break;

                                    }
                                }

                            } else {

                                break;
                            }


                        } else {
                            System.out.println("log in error");
                        }


                    } else {
                        break;
                    }

                }
            } else break;
        }

    }

}

