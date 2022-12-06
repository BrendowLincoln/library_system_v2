package br.org.femass.utils.queries;

public class ReaderQueries {
    public static final String GET_ALL_STUDENTS = "SELECT s FROM Student s ORDER BY s.id";
    public static final String GET_ALL_TEACHERS = "SELECT t FROM Teacher t ORDER BY t.id";
    public static final String GET_STUDENTS_BY_FILTER = "SELECT s FROM Student s WHERE LOWER(s.name) LIKE :filter";
    public static final String GET_TEACHERS_BY_FILTER = "SELECT t FROM Teacher t WHERE LOWER(t.name) LIKE :filter";
    public static final String GET_BORROWED_BOOKS_COUNT = "SELECT COUNT(lc.*) FROM reader r INNER JOIN loan l ON r.id = l.reader_id INNER JOIN loan_copy lc on lc.loan_id = l.id where r.id = :readerId and l.returndate is null";
    public static final String GET_OVERDUE_READERS = "SELECT r FROM Reader r INNER JOIN Loan l ON r.id = l.reader.id WHERE expectedreturndate < now() AND returndate IS NULL";
}