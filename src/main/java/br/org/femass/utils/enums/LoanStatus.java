package br.org.femass.utils.enums;

public enum LoanStatus {
    LOANED("Emprestado"),
    RETURNED("Devolvido"),
    OVERDUE("Atrasado");

    LoanStatus(String label) {
        this.label = label;
    }

    private String label;

    @Override
    public String toString() {
        return this.label;
    }
}
