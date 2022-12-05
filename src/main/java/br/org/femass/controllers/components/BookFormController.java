package br.org.femass.controllers.components;

import br.org.femass.daos.AuthorDao;
import br.org.femass.daos.BookDao;
import br.org.femass.daos.CopyDao;
import br.org.femass.models.Author;
import br.org.femass.models.Book;
import br.org.femass.models.Copy;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
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
    public ListView<Author> authorsList;
    @FXML
    public TableView<Copy> copiesTable;
    @FXML
    public TableColumn<Copy, String> acquisitionDateTableColumn;
    @FXML
    public TableColumn<Copy, String> copyStatusTableColumn;

    private Stage _stage;
    private Book _book;
    private Boolean _isNew;

    private final BookDao _bookDao = new BookDao();
    private final AuthorDao _authorDao = new AuthorDao();
    private final CopyDao _copyDao = new CopyDao();


    public BookFormController() { }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        codeInput.setDisable(true);
        populateAuthorsOption();
        configureCopiesTable();
    }

    public void setStage(Stage stage) {
        this._stage = stage;
    }

    public void setBook(Book book) {
        this._book = book;
        loadData(this._book);
    }

    public void closeDialog(MouseEvent mouseEvent) {
        this._stage.hide();
    }

    public void cancel(MouseEvent mouseEvent) {
        this._stage.hide();
    }

    public void save(MouseEvent mouseEvent) {
        this._book = new Book(
                codeInput.getText().isEmpty() ? null : Long.parseLong(codeInput.getText()),
                bookTitleInput.getText(),
                authorsList.getItems(),
                copiesTable.getItems()
        );

        if(isValid(this._book)) {
            if(this._book.getId() == null) {
                create(this._book);
            } else {
                change(this._book);
            }
        } else {
            return;
        }
    }

    public void delete(MouseEvent mouseEvent) {
        this._bookDao.delete(this._book);
        this._stage.hide();
    }


    public boolean isValid(Book book) {
        if(book.getTitle().isEmpty() || book.getTitle().isBlank()) {
            JOptionPane.showMessageDialog(null, "Titulo não preenchido");
            return false;
        }

        if(book.getAuthors().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Necessário adicionar pelo menos um autor");
            return false;
        }

        if(book.getCopies().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Necessário adicionar pelo menos uma cópia");
            return false;
        }

        return true;
    }


    private void populateAuthorsOption() {
        List<Author> authors = _authorDao.getAll();
        ObservableList<Author> observableAuthorsList = FXCollections.observableArrayList(authors);
        authorOption.setItems(observableAuthorsList);
    }

    private void create(Book book) {

        this._bookDao.save(book);
        this._stage.hide();
    }

    private void change(Book book) {
        this._bookDao.change(book);
        this._stage.hide();
    }

    private void loadData(Book book) {
        if(book == null) {
            headerTitle.setText("NOVO LIVRO");
            deleteButton.setVisible(false);
            removeAuthorButton.setVisible(false);
            removeCopyButton.setVisible(false);
        } else {
            headerTitle.setText("EDITAR LIVRO");
            deleteButton.setVisible(true);

            codeInput.setText(book.getId().toString());
            bookTitleInput.setText(book.getTitle());
            populateCopiesTable(book.getCopies());
            populateAuthorsList(book.getAuthors());

            removeAuthorButton.setVisible(!authorsList.getItems().isEmpty());
            removeCopyButton.setVisible(!copiesTable.getItems().isEmpty());
        }


    }

    public void addAuthor(MouseEvent mouseEvent) {
        Author selectedAuthor = authorOption.getValue();
        if(selectedAuthor == null) {
            return;
        }
        boolean alreadyAdded = authorsList.getItems().contains(selectedAuthor);

        if(!alreadyAdded) {
            authorsList.getItems().add(selectedAuthor);
            authorOption.getSelectionModel().select(-1);
        }
        removeAuthorButton.setVisible(!authorsList.getItems().isEmpty());
    }

    public void removeAuthor(MouseEvent mouseEvent) {
        Author selectedAuthor = authorsList.getSelectionModel().getSelectedItem();
        if(selectedAuthor == null) {
            return;
        }
        authorsList.getItems().remove(selectedAuthor);
        authorOption.getSelectionModel().select(-1);
        removeAuthorButton.setVisible(!authorsList.getItems().isEmpty());
    }

    public void addCopy(MouseEvent mouseEvent) {
        LocalDate date = dateAcquisitionDateInput.getValue();
        if(date == null) {
            return;
        }

        if(date.isAfter(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "A data de aquisição não pode ser maior que o dia de hoje");
            return;
        }

        Copy newCopy = new Copy(date, false);
        copiesTable.getItems().add(newCopy);
        removeCopyButton.setVisible(!copiesTable.getItems().isEmpty());
    }

    public void removeCopy(MouseEvent mouseEvent) {
        Copy selectedCopy = copiesTable.getSelectionModel().getSelectedItem();
        if(selectedCopy == null) {
            return;
        }
        copiesTable.getItems().remove(selectedCopy);
        removeAuthorButton.setVisible(!copiesTable.getItems().isEmpty());
    }


    private void configureCopiesTable() {

        acquisitionDateTableColumn.setCellValueFactory((cellData) -> {
            String formattedDate = cellData.getValue().getAcquisitionDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return new ReadOnlyStringWrapper(formattedDate);
        });

        copyStatusTableColumn.setCellValueFactory((cellData) -> {
            Boolean isLoaned = cellData.getValue().getIsLoaned();
            String copyStatus = !isLoaned ? "Disponível" : "Emprestado";
            return new ReadOnlyStringWrapper(copyStatus);
        });
    }

    private void populateAuthorsList(List<Author> authors) {
        ObservableList observableList = FXCollections.observableArrayList(authors);
        authorsList.setItems(observableList);
    }

    private void populateCopiesTable(List<Copy> copies) {
        ObservableList observableList = FXCollections.observableArrayList(copies);
        copiesTable.setItems(observableList);
    }

}
