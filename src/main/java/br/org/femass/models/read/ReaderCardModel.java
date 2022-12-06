package br.org.femass.models.read;

public class ReaderCardModel {
    public Long id;
    public String name;
    public String readerType;
    public String address;
    public String telephone;
    public String register;
    public String subject;
    public Integer boredBooksCount;

    public ReaderCardModel(Long id, String name, String address, String telephone, String register, String subject, Integer boredBooksCount, String readerType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.register = register;
        this.subject = subject;
        this.boredBooksCount = boredBooksCount;
        this.readerType = readerType;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReaderType() {
        return readerType;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getRegister() {
        return register;
    }

    public String getSubject() {
        return subject;
    }

    public Integer getBoredBooksCount() {
        return boredBooksCount;
    }
}
