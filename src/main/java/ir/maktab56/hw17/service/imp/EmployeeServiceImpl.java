package ir.maktab56.hw17.service.imp;

import ir.maktab56.hw17.base.service.impl.BaseEntityServiceImpl;
import ir.maktab56.hw17.domain.Company;
import ir.maktab56.hw17.domain.Employee;
import ir.maktab56.hw17.repository.EmployeeRepository;
import ir.maktab56.hw17.repository.imp.CompanyRepositoryImpl;
import ir.maktab56.hw17.repository.imp.UserRepositoryImpl;
import ir.maktab56.hw17.service.EmployeeService;
import ir.maktab56.hw17.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class EmployeeServiceImpl extends BaseEntityServiceImpl<Employee, Integer, EmployeeRepository>
        implements EmployeeService {


    private CompanyServiceImpl companyService;
    private UserServiceImpl userService;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        super(repository);
        companyService = new CompanyServiceImpl(new CompanyRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));
        userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtil.getEntityMangerFactory().createEntityManager()));

    }

    @Override
    public boolean existByUsername(String username) {
        return repository.existByUsername(username);
    }

    @Override
    public boolean existByPassword(String username, String password) {
        return repository.existByPassword(username,password);
    }

    @Override
    public Employee findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username,password);
    }

    @Override
    public Employee findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Employee registerEmployee() {
        Scanner input = new Scanner(System.in);
        Employee employee = new Employee();
        System.out.println("Enter your First Name :");
        employee.setFirstname(input.next());

        System.out.println("Enter your Last Name :");
        employee.setLastname(input.next());
        while (true) {
            System.out.println("Enter your UserName :");
            String username = input.next();
            if (!repository.existByUsername(username)) {
                employee.setUsername(username);
                break;
            } else {
                System.out.println("sorry, this username is already taken ");
            }

        }

        while (true) {
            System.out.println("Enter your passWord :");
            System.out.println("Password length must be 8 or more ");
            String password = new Scanner(System.in).next();
            if (password.length() >= 8) {
                employee.setPassword(password);
                break;
            }
            System.out.println("The length of the password is shor\nplease try againe");
        }

        System.out.println("Enter your age :");
        employee.setAge(new Scanner(System.in).nextInt());


        companyService.showCompanyInfo();
        System.out.println("Enter the Company ID in which you want to be employed");
        int companyId = new Scanner(System.in).nextInt();

        while (true) {
            if (companyService.existsById(companyId)) {
                Company byId = companyService.findById(companyId);
                employee.setCompany(byId);
                break;
            } else {
                System.out.println("wrong bank id !!!");
            }
        }


        System.out.println("Wellcome to company\n");
        System.out.println("Your registration has been completed successfully.\n" +
                " Please wait for administrator approval to activate your account");
        save(employee);
        return employee;
    }

    @Override
    public Employee logInEmployee() {
        int answer = 0;
        int wrongPassword = 0;
        int wrongUsername = 0;
        Employee employee;
        String username;
        String password;
        while (true) {
            System.out.println("Enter your Username :");
            username = new Scanner(System.in).next();
            if (repository.existByUsername(username)) {
                employee = repository.findByUsername(username);

                if (employee.isBlocked()) {
                    System.out.println("Your account is blocked\n" +
                            "and you can not use the service\n\n" +
                            "Do you want to leave a message for company manager to unblock your account?\n" +
                            "1 : YES\n" +
                            "2 : NO");
                    answer = new Scanner(System.in).nextInt();
                    if (answer == 1) {
                        System.out.println("Enter your message");
                        String massage = new Scanner(System.in).nextLine();
                        employee.setMassage(massage);
                        save(employee);
                    }
                    return employee;
                } else if (!employee.isActive()) {
                    System.out.println("Your account not active \n" +
                            "and you can not use the service\n\n" +
                            "Do you want to leave a message for company manager to active your account?\n" +
                            "1 : YES\n" +
                            "2 : NO");
                    answer = new Scanner(System.in).nextInt();
                    if (answer == 1) {
                        System.out.println("Enter your message...");
                        String massage = new Scanner(System.in).nextLine();
                        employee.setMassage(massage);
                        save(employee);
                    }
                    return employee;
                } else {

                    while (true) {
                        System.out.println("please Enter your password : ");
                        password = new Scanner(System.in).next();
                        if (password.equals(employee.getPassword())) {
                            System.out.println("The log in was successful !");
                            return employee;
                        } else {
                            wrongPassword++;
                            if (wrongPassword == 3) {
                                employee.setBlocked(true);
                                save(employee);
                                System.out.println("Your account has been blocked\n" +
                                        "due to incorrect password entry");
                                System.out.println("Do you want to leave a message for company employees to unblock your account?\n" +
                                        "1 : YES\n" +
                                        "2 : NO");
                                answer = new Scanner(System.in).nextInt();
                                if (answer == 1) {
                                    System.out.println("Enter your message");
                                    employee.setMassage(new Scanner(System.in).nextLine());
                                    save(employee);
                                }
                                return null;

                            }
                        }

                        System.out.println("wrong password!!\n" +
                                "please try again");
                    }

                }

            } else {
                wrongUsername++;
                if (wrongUsername == 3) break;
                System.out.println("This username is not available!!!\n" +
                        "please try again");

            }

        }

        return null;
    }

    @Override
    public void addDefaultManager() {

        if (findAll().size() == 0) {
            List<Company> companies = companyService.addDefaultBranch();

            Employee employee1 = new Employee("parsa", "zare", "parsazare", "9909999099", true, true, 22);
            Employee employee2 = new Employee("mahdi", "alemi", "mahdialemi", "12345678", true, true, 34);
            Employee employee3 = new Employee("reza", "akbari", "rezaakbari", "12345678", true, true, 45);
            Employee employee4 = new Employee("ehsan", "alava", "ehsanalavi", "12345678", true, true, 30);
            Employee employee5 = new Employee("hossein", "karshemnas", "hossein", "12345678", true, true, 46);
            Employee employee6 = new Employee("mehran", "rezaie", "mehran", "12345678", true, true, 41);
            employee1.setCompany(companies.get(0));
            employee2.setCompany(companies.get(1));
            employee3.setCompany(companies.get(2));
            employee4.setCompany(companies.get(3));
            employee4.setCompany(companies.get(4));
            employee4.setCompany(companies.get(5));
            save(employee1);
            save(employee2);
            save(employee3);
            save(employee4);
            save(employee5);
            save(employee6);

            List<Employee> employeeList = findAll();
            String s = "";
            int k = 0;
            Company company;
            for (int i = 1; i < 7; i++) {
                s = employeeList.get(k).getFirstname() + " " + employeeList.get(k).getLastname();
                company = companyService.findById(i);
                company.setManagername(s);
                companyService.save(company);
                k++;
            }
        }

    }

    @Override
    public void managerService(Employee employee) {
        Scanner scanner = new Scanner(System.in);

        List<Employee> employeeList = employee.getCompany().getEmployeeList();
        showAllEmployeesForManager(employee);
        System.out.println("Enter the ID of the employee you want to Active or DeActive");
        int ans = scanner.nextInt();

        if (existsById(ans)) {

            Employee byId = findById(ans);
            if (byId.isActive()) {
                System.out.println("do you want DeActive this employee ?\n" +
                        "1 : YES\n" +
                        "2 : NO");
                ans = scanner.nextInt();
                if (ans == 1) {
                    byId.setActive(false);
                }


            } else if (byId.isActive() == false) {

                System.out.println("do you want active this employee ?\n" +
                        "1 : YES\n" +
                        "2 : NO");
                ans = scanner.nextInt();
                if (ans == 1) {
                    byId.setActive(true);
                }

            }

            save(byId);
        } else {
            System.out.println("wrong ID !!!!");
        }


    }

    @Override
    public void showAllEmployeesForManager(Employee employee) {

        List<Employee> employeeList = employee.getCompany().getEmployeeList();
        if (employeeList.size() < 2 || employeeList == null) {

            System.out.println("There are no employees in this Company");

        } else {

            for (int i = 0; i < employeeList.size(); i++) {
                if (!employeeList.get(i).isManager()) {
                    System.out.println(employeeList.get(i).toString());
                }

            }

        }

    }

}
