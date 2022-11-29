package br.org.femass.controllers;

import br.org.femass.controllers.components.AuthorFormController;
import br.org.femass.models.Author;
import br.org.femass.models.Employee;
import br.org.femass.utils.router.Router;
import br.org.femass.utils.shared.DataProvider;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthorsController extends ControllerBase implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureUserData();
    }

    public void searchBookKeyPressed(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {

        }
    }

    public void searchBookClick(MouseEvent mouseEvent) throws IOException {

    }

    public void add(MouseEvent mouseEvent) {
        try {
            openForm(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openForm(Author author) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Stage formStage = new Stage();

        URL location = getClass().getResource("/fxml/components/AuthorForm.fxml");
        loader.setLocation(location);
        loader.setBuilderFactory(new JavaFXBuilderFactory());

        Parent root = loader.load(location.openStream());
        AuthorFormController controller = loader.getController();
        controller.setStage(stage);

        Scene scene = new Scene(root);
        formStage.setTitle("Cadastro de Autores");
        formStage.setScene(scene);
        controller.setStage(formStage);
        formStage.initStyle(StageStyle.UNDECORATED);
        formStage.setResizable(false);

        formStage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                formStage.hide();
            }
        });
        formStage.show();
    }
}
