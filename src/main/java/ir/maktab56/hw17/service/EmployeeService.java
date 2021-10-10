package ir.maktab56.hw17.service;

import ir.maktab56.hw17.base.service.BaseEntityService;
import ir.maktab56.hw17.domain.Employee;

public interface EmployeeService extends BaseEntityService<Employee, Integer> {

    boolean existByUsername(String username);

    boolean existByPassword(String username, String password);

    Employee findByUsernameAndPassword(String username, String password);

    Employee findByUsername(String username);

    Employee registerEmployee();

    Employee logInEmployee();

    void addDefaultManager();

    void managerService(Employee employee);

    void showAllEmployeesForManager(Employee employee);
}
