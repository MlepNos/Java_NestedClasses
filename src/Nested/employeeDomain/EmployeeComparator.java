package Nested.employeeDomain;

import java.util.Comparator;

public class EmployeeComparator <T extends Employee> implements Comparator<Employee> {


    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getName().compareTo(o2.getName());
        //we can not access the members "employeeId" and "yearStarted" directly because they are private in the employee
    }
}
