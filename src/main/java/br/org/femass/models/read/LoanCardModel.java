package br.org.femass.models.read;

import br.org.femass.models.Copy;
import br.org.femass.models.Reader;

import java.time.LocalDate;
import java.util.List;

public class LoanCardModel {
    private Long id;
    private LocalDate loanDate;
    private LocalDate expectedReturnDate;
    private LocalDate returnDate;
    private Integer boredBooksCount;
    private String readerType;
    private String readerName;
    private String loanStatus;

    public LoanCardModel(Long id, LocalDate loanDate, LocalDate expectedReturnDate, LocalDate returnDate, Integer boredBooksCount, String readerType, String readerName, String loanStatus) {
        this.id = id;
        this.loanDate = loanDate;
        this.expectedReturnDate = expectedReturnDate;
        this.returnDate = returnDate;
        this.boredBooksCount = boredBooksCount;
        this.readerType = readerType;
        this.readerName = readerName;
        this.loanStatus = loanStatus;
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

    public Integer getBoredBooksCount() {
        return boredBooksCount;
    }

    public String getReaderType() {
        return readerType;
    }

    public String getReaderName() {
        return readerName;
    }

    public String getLoanStatus() {
        return loanStatus;
    }
}
