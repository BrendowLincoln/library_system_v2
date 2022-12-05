package br.org.femass.controllers;

import br.org.femass.controllers.components.AuthorFormController;
import br.org.femass.controllers.components.BookFormController;
import br.org.femass.controllers.components.cards.AuthorCardListItemController;
import br.org.femass.controllers.components.cards.BookCardListItemController;
import br.org.femass.daos.BookDao;
import br.org.femass.models.Author;
import br.org.femass.models.Book;
import br.org.femass.models.read.AuthorCardModel;
import br.org.femass.models.read.BookCardModel;
import br.org.femass.utils.shared.DataProvider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class BooksPageController extends ControllerBase implements Initializable {

    @FXML
    public VBox cardList;
    @FXML
    public ScrollPane scrollContainer;
    @FXML
    public TextField searchInput;
    @FXML
    public Button searchButton;


    private final BookDao _dao = new BookDao();

    private List<Book> _books = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureUserData();

        String searchFilter = (String) DataProvider.getDataByKey("bookSearchHome");

        if(searchFilter != null) {
            searchInput.setText(searchFilter);
        }

        updateList(Objects.requireNonNullElse(searchFilter, ""));
    }

    //region EVENT METHODS
    public void searchKeyPressed(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            updateList(this.searchInput.getText());
        }
    }

    public void searchBookClick(MouseEvent mouseEvent) throws IOException {
        updateList(this.searchInput.getText());
    }

    public void add(MouseEvent mouseEvent) {
        try {
            openForm(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion EVENT METHODS

    //region PUBLIC METHODS
    public void openForm(Book book) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Stage formStage = new Stage();

        URL location = getClass().getResource("/fxml/components/BookForm.fxml");
        loader.setLocation(location);
        loader.setBuilderFactory(new JavaFXBuilderFactory());


        Parent root = loader.load(location.openStream());
        BookFormController controller = loader.getController();


        Scene scene = new Scene(root);
        formStage.setTitle("Cadastro de livros");
        formStage.setScene(scene);
        controller.setStage(formStage);
        formStage.initStyle(StageStyle.UNDECORATED);
        formStage.setResizable(false);
        formStage.getScene().setFill(Color.TRANSPARENT);
        formStage.setOnShown((x) -> {
            controller.setBook(book);
        });
        formStage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                formStage.hide();
            }
        });
        formStage.setOnHidden((event) -> updateList(""));
        formStage.show();
    }
    //endregion PUBLIC METHODS

    //region PRIVATE METHODS
    private void updateList(String filter) {
        try {

            if(filter.isEmpty() || filter.isBlank()) {
                this._books = this._dao.getAll();
            } else {
                this._books = this._dao.getByFilter(filter);
            }

            List<BookCardModel> booksCard = this._books.stream().map((book) -> {
                return new BookCardModel(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthors(),
                        book.getCopies().size()
                );
            }).collect(Collectors.toList());

            if(booksCard.isEmpty()) {
                Label emptyResultLabel = new Label("Nenhum livro encontrado");
                emptyResultLabel.getStyleClass().set(0, "authors-group-empty-message");
                cardList.getChildren().clear();
                cardList.getChildren().add(emptyResultLabel);
                cardList.setAlignment(Pos.CENTER);
            }


            for (int i = 0; i < booksCard.size(); i++) {

                if(i == 0) {
                    cardList.setAlignment(Pos.TOP_CENTER);
                    cardList.getChildren().clear();
                }

                FXMLLoader loader = new FXMLLoader();

                URL location = getClass().getResource("/fxml/components/cards/BookCardListItem.fxml");
                loader.setLocation(location);
                loader.setBuilderFactory(new JavaFXBuilderFactory());
                Node lastEmployeeCard= loader.load(location.openStream());

                BookCardListItemController controller = loader.getController();
                controller.setCardData(booksCard.get(i));

                Book clickedBook = this._books.get(i);

                lastEmployeeCard.setOnMouseClicked((x) -> {
                    try {
                        openForm(clickedBook);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                cardList.getChildren().add(lastEmployeeCard);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //endregion PRIVATE METHODS
}
