package br.org.femass.daos;

import br.org.femass.models.Employee;
import br.org.femass.utils.queires.EmployeeQueries;

import java.util.List;

public class EmployeeDao extends Dao <Employee> {

    public List<Employee> getAll() {
        return em.createQuery(EmployeeQueries.GET_ALL_EMPLOYEES).getResultList();
    }


    public Employee validateUser(String email, String password) {
        var query = em.createQuery(EmployeeQueries.GET_EMPLOYEE_BY_USER_ACCOUNT);

        query.setParameter("email", email);
        query.setParameter("password", password);

        List<Employee> employees = query.getResultList();

        if(employees.isEmpty()) {
            return null;
        }

        Employee employeeUser = employees.get(0);
        return employeeUser;
    }
}
