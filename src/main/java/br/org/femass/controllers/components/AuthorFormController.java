package br.org.femass.controllers.components;

import br.org.femass.daos.AuthorDao;
import br.org.femass.models.Author;
import br.org.femass.utils.enums.Nationality;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.List;
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
    public ComboBox<Nationality> nationalityOption;

    private Stage stage;
    private Author author;

    private final AuthorDao _dao = new AuthorDao();
    

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
        populateNationalityOption();
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

        this.author = new Author(
                nameInput.getText(),
                secondNameInput.getText(),
                nationalityOption.getValue()
        );

        if(this.author != null) {
            create(this.author);
        }
    }


    public boolean isValid(Author author) {
        if(author.getName().isEmpty() || author.getName() == null) {
            JOptionPane.showMessageDialog(null, "Nome não preenchido");
            return false;
        }

        if(author.getSecondName().isEmpty() || author.getSecondName() == null) {
            JOptionPane.showMessageDialog(null, "Sobrenom não preenchido");
            return false;
        }

        if(author.getNationality() == null) {
            JOptionPane.showMessageDialog(null, "Nacionalidade não preenchido");
            return false;
        }

        return true;
    }


    private void populateNationalityOption() {
        nationalityOption.getItems().setAll(Nationality.values());
    }

    private void create(Author author) {
        if (!isValid(author)) {
            return;
        }

        this._dao.save(author);
        this.stage.hide();
    }

}
