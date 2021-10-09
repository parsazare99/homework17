package ir.maktab56.hw17.service;

import ir.maktab56.hw17.base.service.BaseEntityService;
import ir.maktab56.hw17.domain.Employee;

public interface EmployeeService extends BaseEntityService<Employee, Integer> {


    Employee registerEmployee();

    Employee logInEmployee();

    void addDefaultManager();

    void managerService(Employee employee);

    void showAllEmployeesForManager(Employee employee);
}
