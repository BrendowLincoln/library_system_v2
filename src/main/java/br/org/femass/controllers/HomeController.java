package br.org.femass.controllers;

import br.org.femass.controllers.components.AuthorFormController;
import br.org.femass.controllers.components.OverdueBookDialogController;
import br.org.femass.controllers.components.cards.SmallCardListItemController;
import br.org.femass.daos.LoanDao;
import br.org.femass.models.Loan;
import br.org.femass.models.read.LoanCardModel;
import br.org.femass.utils.enums.Role;
import br.org.femass.utils.router.Router;
import br.org.femass.utils.services.DataProvider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HomeController extends ControllerBase implements Initializable {
    @FXML
    public Button searchButton;
    @FXML
    public TextField searchInput;
    @FXML
    public HBox cardGroup;
    @FXML
    public Button seeOverdueReadersButton;

    private final LoanDao _dao = new LoanDao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureUserData();

        if(employee.getUser().getRole() == Role.ATTENDANT) {
            seeOverdueReadersButton.setVisible(false);
            searchInput.promptTextProperty().setValue("Busqque o empréstimo pelo leitor que deseja");
        }

    }

    public void searchBookKeyPressed(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {

            if (employee.getUser().getRole() == Role.ATTENDANT) {
                searchLoan();
            } else {
                searchBook();
            }
        }
    }

    public void searchBookClick(MouseEvent mouseEvent) throws IOException {
        if (employee.getUser().getRole() == Role.ATTENDANT) {
            searchLoan();
        } else {
            searchBook();
        }
    }


    private void searchBook() throws IOException {
        String searchText = searchInput.getText();
        DataProvider.setData("bookSearchHome", searchText);

        routerService.navigateTo(Router.BOOKS_PAGE, stage);
    }

    private void searchLoan() throws IOException {
        String searchText = searchInput.getText();
        DataProvider.setData("loanSearchHome", searchText);

        routerService.navigateTo(Router.LOANS_PAGE, stage);
    }

    public void openOverdueReadersDialog(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Stage formStage = new Stage();

            URL location = getClass().getResource("/fxml/components/OverdueBookDialog.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());


            Parent root = loader.load(location.openStream());
            OverdueBookDialogController controller = loader.getController();


            Scene scene = new Scene(root);
            formStage.setTitle("Cadastro de Autores");
            formStage.setScene(scene);
            controller.setStage(formStage);
            formStage.initStyle(StageStyle.UNDECORATED);
            formStage.setResizable(false);
            formStage.getScene().setFill(Color.TRANSPARENT);
            formStage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (!isNowFocused) {
                    formStage.hide();
                }
            });
            formStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}