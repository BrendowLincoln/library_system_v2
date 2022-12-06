package br.org.femass.controllers;

import br.org.femass.daos.EmployeeDao;
import br.org.femass.models.Employee;
import br.org.femass.utils.router.Router;
import br.org.femass.utils.router.RouterService;
import br.org.femass.utils.services.UserProvider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends ControllerBase implements Initializable {

    private RouterService routerService = RouterService.getInstance();
    private EmployeeDao dao = new EmployeeDao();

    @FXML
    public TextField emailInput;

    @FXML
    public TextField passwordInput;

    @FXML
    public Button signInInput;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void signIn(MouseEvent mouseEvent) throws IOException {

        Employee employeeUser = dao.getEmployeeByUserVerification(emailInput.getText(), passwordInput.getText());

        if(employeeUser == null) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado");
            return;
        }

        UserProvider.setUserData(employeeUser);
        routerService.navigateTo(Router.HOME_PAGE, stage);
    }



}
