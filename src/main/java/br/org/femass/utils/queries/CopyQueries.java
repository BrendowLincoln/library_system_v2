package br.org.femass.utils.queries;

public class CopyQueries {
    public static final String GET_ALL_COPIES = "SELECT c FROM Copy c";
    public static final String GET_AVAILABLE_COPIES = "SELECT c FROM Copy c WHERE c.isLoaned = false";
}
