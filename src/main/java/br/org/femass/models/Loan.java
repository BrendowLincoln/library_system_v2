package br.org.femass.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate loanDate;
    private LocalDate expectedReturnDate;
    private LocalDate returnDate;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Copy> copies;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Reader reader;

    public Loan() { }

    public Loan(Long id, LocalDate loanDate, LocalDate expectedReturnDate, LocalDate returnDate, List<Copy> copies, Reader reader) {
        this.id = id;
        this.loanDate = loanDate;
        this.expectedReturnDate = expectedReturnDate;
        this.returnDate = returnDate;
        this.copies = copies;
        this.reader = reader;
    }

    public Loan(LocalDate loanDate, LocalDate expectedReturnDate, LocalDate returnDate, List<Copy> copies, Reader reader) {
        this.loanDate = loanDate;
        this.expectedReturnDate = expectedReturnDate;
        this.returnDate = returnDate;
        this.copies = copies;
        this.reader = reader;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public Reader getReader() {
        return reader;
    }


    public Boolean isOverdue() {

        return this.returnDate == null && this.expectedReturnDate.isBefore(LocalDate.now());
    }

    public Boolean isReturned() {
        return this.returnDate != null;
    }

    public String loanStatus() {

        if(isReturned()) {
            return "Devolvido";
        } else if(isOverdue()) {
            return "Atrasado";
        }

        return "Emprestado";
    }
}
