package br.org.femass.controllers;

import br.org.femass.controllers.components.cards.SmallCardListItemController;
import br.org.femass.utils.router.Router;
import br.org.femass.utils.services.DataProvider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController extends ControllerBase implements Initializable {
    @FXML
    public Button searchButton;
    @FXML
    public TextField searchInput;
    @FXML
    public HBox cardGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureUserData();

        try {

            List<Object> loansList = new ArrayList<>();

            Button seeMoreButton = new Button();
            seeMoreButton.setText("Ver mais >");
            seeMoreButton.getStyleClass().add("see-more-button");
            seeMoreButton.setId("seeMoreButton");
            seeMoreButton.setOnMouseClicked((MouseEvent mouseEvent) ->  {
                try {
                    loansPage(mouseEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            for (int i = 0; i < loansList.size(); i++) {

                if(i == 0) {
                    cardGroup.getChildren().remove(0);
                }

                FXMLLoader loader = new FXMLLoader();

                URL location = getClass().getResource("/fxml/components/cards/SmallCardListItem.fxml");
                loader.setLocation(location);
                loader.setBuilderFactory(new JavaFXBuilderFactory());
                Node lastEmployeeCard= loader.load(location.openStream());

                SmallCardListItemController controller = loader.getController();
                controller.setData();

                cardGroup.getChildren().add(lastEmployeeCard);

                if(i == 2) {
                    cardGroup.getChildren().add(seeMoreButton);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchBookKeyPressed(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            searchBook();
        }
    }

    public void searchBookClick(MouseEvent mouseEvent) throws IOException {
        searchBook();
    }


    private void searchBook() throws IOException {
        String searchText = searchInput.getText();
        DataProvider.setData("bookSearchHome", searchText);

        routerService.navigateTo(Router.BOOKS_PAGE, stage);
    }
}