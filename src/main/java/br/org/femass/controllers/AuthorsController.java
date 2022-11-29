package br.org.femass.controllers;

import br.org.femass.controllers.components.AuthorFormController;
import br.org.femass.controllers.components.cards.AuthorCardListItemController;
import br.org.femass.controllers.components.cards.SmallCardListItemController;
import br.org.femass.daos.AuthorDao;
import br.org.femass.models.Author;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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

public class AuthorsController extends ControllerBase implements Initializable {

    @FXML
    public VBox cardList;
    @FXML
    public ScrollPane scrollContainer;

    private final AuthorDao _dao = new AuthorDao();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureUserData();
        updateList();

        try {

            List<Object> cards = new ArrayList<>();

            for (int i = 0; i < 30; i++) {

                if(i == 0) {
                    cardList.getChildren().remove(0);
                }

                FXMLLoader loader = new FXMLLoader();

                URL location = getClass().getResource("/fxml/components/cards/AuthorCardListItem.fxml");
                loader.setLocation(location);
                loader.setBuilderFactory(new JavaFXBuilderFactory());
                Node lastEmployeeCard= loader.load(location.openStream());

                AuthorCardListItemController controller = loader.getController();

                cardList.getChildren().add(lastEmployeeCard);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        formStage.getScene().setFill(Color.TRANSPARENT);
        formStage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                formStage.hide();
            }
        });
        formStage.setOnHidden((event) -> updateList());
        formStage.show();
    }

    private void updateList() {
        List<Author> authors = this._dao.getAll();

        System.out.println(authors);

    }

}
