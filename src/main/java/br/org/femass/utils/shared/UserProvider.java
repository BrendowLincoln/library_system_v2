package br.org.femass.utils.shared;

import br.org.femass.models.Employee;

public class UserProvider {
    private static Employee userData = null;

    public static Employee getUserData() {
        return userData;
    }

    public static void setUserData(Employee data) {
        userData = data;
    }
}
