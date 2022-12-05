package br.org.femass.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate acquisitionDate;
    private Boolean isLoaned;

    public Copy() { }

    public Copy(LocalDate acquisitionDate, Boolean isLoaned) {
        this.acquisitionDate = acquisitionDate;
        this.isLoaned = isLoaned;
    }

    public Long getId() {
        return this.id;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public Boolean getIsLoaned() {
        return isLoaned;
    }
}
