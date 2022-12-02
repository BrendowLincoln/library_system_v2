package br.org.femass.controllers.components;

import br.org.femass.daos.AuthorDao;
import br.org.femass.models.Author;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class BookFormController implements Initializable {

    @FXML
    public ImageView closeButton;
    @FXML
    public Button cancelButton;
    @FXML
    public Button saveButton;
    @FXML
    public Button deleteButton;
    @FXML
    public Label headerTitle;
    @FXML
    public TextField codeInput;
    @FXML
    public TextField bookTitleInput;
    @FXML
    public ComboBox<Author> authorOption;
    @FXML
    public DatePicker dateAcquisitionDateInput;
    @FXML
    public Button addAuthorButton;
    @FXML
    public Button removeAuthorButton;
    @FXML
    public Button addCopyButton;
    @FXML
    public Button removeCopyButton;
    @FXML
    public TableView<Author> authorsTable;
    @FXML
    public TableView<Object> copiesTable;

    private Stage _stage;
    private Author _author;
    private Boolean _isNew;

    private final AuthorDao _dao = new AuthorDao();


    public BookFormController() { }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateNationalityOption();
    }

    public void setStage(Stage stage) {
        this._stage = stage;
    }

    public void setAuthor(Author author) {
        this._author = author;
        loadData(this._author);
    }

    public void closeDialog(MouseEvent mouseEvent) {
        this._stage.hide();
    }

    public void cancel(MouseEvent mouseEvent) {
        this._stage.hide();
    }

    public void save(MouseEvent mouseEvent) {
        /*this._author = new Author(
                codeInput.getText().isEmpty() ? null : Long.parseLong(codeInput.getText()),
                nameInput.getText(),
                secondNameInput.getText(),
                nationalityOption.getValue()
        );

        if(isValid(_author)) {
            if(this._author.getId() == null) {
                create(this._author);
            } else {
                change(this._author);
            }
        } else {
            return;
        }*/
    }

    public void delete(MouseEvent mouseEvent) {
        this._dao.delete(this._author);
        this._stage.hide();
    }


    public boolean isValid(Author author) {
        if(author.getFirstName().isEmpty() || author.getFirstName() == null) {
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
        //nationalityOption.getItems().setAll(Nationality.values());
    }

    private void create(Author author) {

        this._dao.save(author);
        this._stage.hide();
    }

    private void change(Author author) {
        this._dao.change(author);
        this._stage.hide();
    }

    private void loadData(Author author) {
        if(author == null) {
            headerTitle.setText("NOVO AUTOR");
            deleteButton.setVisible(false);


        } else {

            headerTitle.setText("EDITAR AUTOR");
            deleteButton.setVisible(true);

            codeInput.setText(author.getId().toString());
            //nameInput.setText(author.getFirstName());
            //secondNameInput.setText(author.getSecondName());
            //nationalityOption.setValue(author.getNationality());
        }
    }
}
