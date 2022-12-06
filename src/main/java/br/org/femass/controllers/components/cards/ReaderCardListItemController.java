package br.org.femass.controllers.components.cards;

import br.org.femass.models.read.AuthorCardModel;
import br.org.femass.models.read.ReaderCardModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ReaderCardListItemController implements Initializable {

    @FXML
    public Label idLabel;
    @FXML
    public Label boredBooksCountLabel;
    @FXML
    public Label readerNameLabel;
    @FXML
    public Label readerTypeLabel;
    @FXML
    public Label fieldBasedInReaderTypeLabel;
    @FXML
    public Label addressLabel;
    @FXML
    public Label telephoneLabel;
    @FXML
    public AnchorPane cardContainer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setCardData(ReaderCardModel reader) {
        idLabel.setText(reader.getId().toString());
        readerNameLabel.setText(reader.getName());
        readerTypeLabel.setText(reader.getReaderType());
        addressLabel.setText(reader.getAddress());
        telephoneLabel.setText(reader.getTelephone());
        boredBooksCountLabel.setText(reader.boredBooksCount.toString());

        if(reader.register != null) {
            fieldBasedInReaderTypeLabel.setText(reader.getRegister());
        } else {
            fieldBasedInReaderTypeLabel.setText(reader.getSubject());
        }

    }

}
