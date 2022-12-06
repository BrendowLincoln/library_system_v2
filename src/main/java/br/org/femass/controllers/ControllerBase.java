package br.org.femass.controllers;

import br.org.femass.models.Employee;
import br.org.femass.utils.router.Router;
import br.org.femass.utils.router.RouterService;
import br.org.femass.utils.services.UserProvider;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerBase {
    @FXML
    public Label userRoleLabel;
    @FXML
    public Button readersButton;
    @FXML
    public Button loansButton;
    @FXML
    public ImageView userAvatar;
    @FXML
    public Button authorsButton;
    @FXML
    public Label userNameLabel;
    @FXML
    public Button employeesButton;
    @FXML
    public Button logoutButton;
    @FXML
    public Button booksButton;
    @FXML
    public Button homeButton;

    protected Stage stage;

    protected Employee employee = UserProvider.getUserData();

    protected final RouterService routerService = RouterService.getInstance();

    public void setStage(Stage stage) {
        this.stage = stage;
        this.configureStage(stage);
    }

    public void configureUserData() {
        this.userNameLabel.setText(employee.getName());
        this.userRoleLabel.setText(employee.getUser().getRole().toString());
    }

    public void homePage(MouseEvent mouseEvent) throws IOException {
        routerService.navigateTo(Router.HOME_PAGE, stage);
    }

    public void booksPage(MouseEvent mouseEvent) throws IOException {
        routerService.navigateTo(Router.BOOKS_PAGE, stage);
    }

    public void authorsPage(MouseEvent mouseEvent) throws IOException {
        routerService.navigateTo(Router.AUTHORS_PAGE, stage);
    }

    public void readersPage(MouseEvent mouseEvent) throws IOException {
        routerService.navigateTo(Router.READERS_PAGE, stage);
    }

    public void loansPage(MouseEvent mouseEvent) throws IOException {
        routerService.navigateTo(Router.LOANS_PAGE, stage);
    }

    public void employeesPage(MouseEvent mouseEvent) throws IOException {
        routerService.navigateTo(Router.EMPLOYEES_PAGE, stage);
    }

    public void signInPage(MouseEvent mouseEvent) throws IOException {
        UserProvider.setUserData(null);
        routerService.navigateTo(Router.LOGIN_PAGE, stage);
    }

    private void configureStage(Stage stage) {

        stage.getIcons().add((new Image(getClass().getResourceAsStream("/assets/images/icon-window.png"))));
        stage.setTitle("FeMASS - Biblioteca Universitaria");
        stage.setWidth(1366);
        stage.setHeight(768);
        stage.setResizable(false);
    }

}
