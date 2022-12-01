package br.org.femass.controllers.components.cards;

import br.org.femass.models.read.AuthorCardModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.ResourceBundle;

public class AuthorCardListItemController implements Initializable {

    @FXML
    private Label idLabel;

    @FXML
    private Label registeredBooksCountLabel;

    @FXML
    private Label authorNationalityLabel;

    @FXML
    private Label authorNameLabel;

    @FXML
    private AnchorPane cardContainer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setCardData(AuthorCardModel author) {
        idLabel.setText(author.getId().toString());
        authorNameLabel.setText(author.getFirstName() + " " + author.getSecondName());
        authorNationalityLabel.setText(author.getNationality().toString());
        registeredBooksCountLabel.setText(author.getRegisteredBooksCount().toString());
    }

}
