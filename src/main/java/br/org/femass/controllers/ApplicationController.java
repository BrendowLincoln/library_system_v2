package br.org.femass.controllers;

import br.org.femass.models.Employee;
import br.org.femass.utils.router.Router;
import br.org.femass.utils.router.RouterService;
import br.org.femass.utils.shared.UserProvider;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController extends ControllerBase implements Initializable {

    private final RouterService routerService = RouterService.getInstance();
    private Employee user;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = UserProvider.getUserData();
        System.out.println(user);
    }


    public void backToSignInPage(MouseEvent mouseEvent) throws IOException {
       routerService.navigateTo(Router.LOGIN_PAGE, stage);
    }
}
