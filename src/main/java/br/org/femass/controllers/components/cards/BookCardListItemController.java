package br.org.femass.controllers.components.cards;

import br.org.femass.models.Author;
import br.org.femass.models.read.AuthorCardModel;
import br.org.femass.models.read.BookCardModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookCardListItemController implements Initializable {

    @FXML
    public Label authorsLabel;

    @FXML
    public Label titleLabel;

    @FXML
    public Label registeredCopiesLabel;

    @FXML
    public Label idLabel;

    @FXML
    public AnchorPane cardContainer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setCardData(BookCardModel book) {
        idLabel.setText(book.getId().toString());
        titleLabel.setText(book.getTitle());
        authorsLabel.setText(buildAuthorsLabel(book.getAuthors()));
        registeredCopiesLabel.setText(book.getRegisteredCopiesCount().toString());
    }

    private String buildAuthorsLabel(List<Author> authors) {
        String label = "por ";

        for (int i = 0; i < authors.size(); i++) {

            if(i != (authors.size()-1)){
                label += authors.get(i).toString() + ", ";
            } else {
                label += authors.get(i).toString();
            }
        }
        return  label;
    }
}
