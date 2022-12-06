package br.org.femass.utils.queries;

public class ReaderQueries {
    public static final String GET_ALL_STUDENTS = "SELECT s FROM Student s ORDER BY s.id";
    public static final String GET_ALL_TEACHERS = "SELECT t FROM Teacher t ORDER BY t.id";
    public static final String GET_STUDENTS_BY_FILTER = "SELECT s FROM Student s WHERE LOWER(s.name) LIKE :filter";
    public static final String GET_TEACHERS_BY_FILTER = "SELECT t FROM Teacher t WHERE LOWER(t.name) LIKE :filter";

}
