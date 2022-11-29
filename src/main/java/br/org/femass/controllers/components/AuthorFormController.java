package br.org.femass.controllers.components;

import br.org.femass.models.Author;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthorFormController implements Initializable {
    
    @FXML
    public ImageView closeButton;
    @FXML
    public Button cancelButton;
    @FXML
    public Button saveButton;
    @FXML
    public Label headerTitle;
    @FXML
    public TextField codeInput;
    @FXML
    public TextField nameInput;
    @FXML
    public TextField secondNameInput;
    @FXML
    public TextField nationalityInput;

    private Stage stage;
    private Author author;
    

    public AuthorFormController() { }

    public AuthorFormController(Author author) {
        this.author = author;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(author == null) {
            headerTitle.setText("NOVO AUTOR");
            codeInput.setDisable(true);
        } else {
            headerTitle.setText("EDITAR AUTOR");
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void closeDialog(MouseEvent mouseEvent) {
        this.stage.hide();
    }

    public void cancel(MouseEvent mouseEvent) {
        this.stage.hide();
    }

    public void save(MouseEvent mouseEvent) {

        this.stage.hide();
    }
}
