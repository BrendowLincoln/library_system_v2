package br.org.femass.utils.queries;

public class EmployeeQueries {
    public static final String GET_ALL_EMPLOYEES = "SELECT e FROM Employee e ORDER BY e.id";
    public static final String GET_EMPLOYEES_BY_FILTER = "SELECT e FROM Employee e WHERE  LOWER(e.name) LIKE :filter ORDER BY e.id";

    public static final String GET_EMPLOYEE_BY_USER_ACCOUNT = "SELECT e FROM Employee e" +
            " INNER JOIN User u ON e.user.id = u.id" +
            " WHERE u.email = :email AND" +
            " u.password = :password";
}
