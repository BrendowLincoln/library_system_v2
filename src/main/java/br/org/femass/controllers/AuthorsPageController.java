package br.org.femass.controllers;

import br.org.femass.controllers.components.AuthorFormController;
import br.org.femass.controllers.components.cards.AuthorCardListItemController;
import br.org.femass.daos.AuthorDao;
import br.org.femass.models.Author;
import br.org.femass.models.read.AuthorCardModel;
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
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AuthorsPageController extends ControllerBase implements Initializable {

    @FXML
    public VBox cardList;
    @FXML
    public ScrollPane scrollContainer;
    @FXML
    public TextField searchInput;
    @FXML
    public Button searchButton;

    private final AuthorDao _dao = new AuthorDao();

    private List<Author> _authors = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureUserData();
        updateList("");
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
    public void openForm(Author author) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Stage formStage = new Stage();

        URL location = getClass().getResource("/fxml/components/AuthorForm.fxml");
        loader.setLocation(location);
        loader.setBuilderFactory(new JavaFXBuilderFactory());


        Parent root = loader.load(location.openStream());
        AuthorFormController controller = loader.getController();


        Scene scene = new Scene(root);
        formStage.setTitle("Cadastro de Autores");
        formStage.setScene(scene);
        controller.setStage(formStage);
        formStage.initStyle(StageStyle.UNDECORATED);
        formStage.setResizable(false);
        formStage.getScene().setFill(Color.TRANSPARENT);
        formStage.setOnShown((x) -> {
            controller.setAuthor(author);
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
                this._authors = this._dao.getAll();
            } else {
                this._authors = this._dao.getByFilter(filter);
            }

            List<AuthorCardModel> authorsCard = _authors.stream().map((author) -> {
                Integer registeredBooksCount = _dao.getRegisteredBooksByAuthorId(author.getId());

                return new AuthorCardModel(
                        author.getId(),
                        author.getFirstName(),
                        author.getSecondName(),
                        author.getNationality(),
                        registeredBooksCount
                );
            }).collect(Collectors.toList());

            if(authorsCard.isEmpty()) {
                Label emptyResultLabel = new Label("Nenhum autor encontrado");
                emptyResultLabel.getStyleClass().set(0, "authors-group-empty-message");
                cardList.getChildren().clear();
                cardList.getChildren().add(emptyResultLabel);
                cardList.setAlignment(Pos.CENTER);
            }


            for (int i = 0; i < authorsCard.size(); i++) {

                if(i == 0) {
                    cardList.setAlignment(Pos.TOP_CENTER);
                    cardList.getChildren().clear();
                }

                FXMLLoader loader = new FXMLLoader();

                URL location = getClass().getResource("/fxml/components/cards/AuthorCardListItem.fxml");
                loader.setLocation(location);
                loader.setBuilderFactory(new JavaFXBuilderFactory());
                Node lastEmployeeCard= loader.load(location.openStream());

                AuthorCardListItemController controller = loader.getController();
                controller.setCardData(authorsCard.get(i));

                Author clickedAuthor = _authors.get(i);

                lastEmployeeCard.setOnMouseClicked((x) -> {
                    try {
                        openForm(clickedAuthor);
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
