package br.org.femass.utils.queries;

public class LoanQueries {
    public static final String GET_ALL_LOANS = "SELECT l FROM Loan l ORDER BY l.id";
    public static final String GET_LOANS_BY_FILTER = "SELECT l FROM Loan l WHERE LOWER(l.author.name) LIKE :filter";
    public static final String GET_THREE_MOST_RECENTLY = "SELECT * FROM loan l ORDER BY loandate DESC LIMIT 3";
}

