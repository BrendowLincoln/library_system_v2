package br.org.femass.utils.enums;

public enum Page {
    LOGIN("Login"),
    HOME("Home");

    Page(String label) {
        this.label = label;
    }

    private String label;

    @Override
    public String toString() {
        return this.label;
    }}
