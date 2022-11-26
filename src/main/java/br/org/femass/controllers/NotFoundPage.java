package br.org.femass.controllers;

import br.org.femass.utils.router.Router;
import br.org.femass.utils.router.RouterService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotFoundPage extends ControllerBase implements Initializable {
    @FXML
    public Button backToHomeButton;


    private RouterService routerService = RouterService.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void backToHomePage(MouseEvent mouseEvent) throws IOException {
        routerService.navigateTo(Router.HOME_PAGE, stage);
    }
}
