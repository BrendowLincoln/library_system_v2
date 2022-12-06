package br.org.femass.models.read;


import br.org.femass.utils.enums.Role;

public class EmployeeCardModel {

    private Long id;
    private String name;
    private Role role;


    public EmployeeCardModel(Long id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return this.name;
    }

    public Role getRole() {
        return this.role;
    }

    public Long getId() {
        return this.id;
    }
}
