package br.org.femass.utils.enums;

public enum Role {
    ADMIN("Administrador"),
    LIBRARIAN("Bibliotecario"),
    ATTENDANT("Atendente");

    Role(String label) {
        this.label = label;
    }

    private String label;

    @Override
    public String toString() {
        return this.label;
    }
}
