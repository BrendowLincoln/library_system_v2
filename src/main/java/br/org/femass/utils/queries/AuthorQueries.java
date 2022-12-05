package br.org.femass.utils.queries;

public class AuthorQueries {
    public static final String GET_ALL_AUTHORS = "SELECT a FROM Author a ORDER BY a.id";
    public static final String GET_AUTHORS_BY_FILTER = "SELECT a FROM Author a WHERE  LOWER(a.firstName) LIKE :filter OR LOWER(a.secondName) LIKE :filter";
    public static final String GET_REGISTERED_BOOKS_COUNT_BY_AUTHOR = "SELECT COUNT(ba.*) FROM book b INNER JOIN public.book_author ba ON b.id = ba.book_id WHERE ba.authors_id = :id";

}
