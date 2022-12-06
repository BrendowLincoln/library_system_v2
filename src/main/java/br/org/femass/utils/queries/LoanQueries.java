package br.org.femass.utils.queries;

public class LoanQueries {
    public static final String GET_ALL_LOANS = "SELECT l FROM Loan l ORDER BY l.id";
    public static final String GET_LOANS_BY_FILTER = "SELECT l FROM Loan l INNER JOIN Reader r WHERE r.name LIKE :filter";

    public static final String GET_LOANS_BY_FILTER_NATIVE = "SELECT l.* FROM loan l INNER JOIN reader r ON l.reader_id = r.id  WHERE LOWER (r.name) LIKE :filter";
    public static final String GET_THREE_MOST_RECENTLY = "SELECT * FROM loan l ORDER BY loandate DESC LIMIT 3";
}

