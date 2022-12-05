package br.org.femass.utils.queries;

public class BookQueries {
    public static final String GET_ALL_BOOKS = "SELECT b FROM Book b ORDER BY b.id";
    public static final String GET_BOOKS_BY_FILTER = "SELECT b FROM Book b WHERE LOWER(b.title) LIKE :filter ORDER BY b.id";
}
