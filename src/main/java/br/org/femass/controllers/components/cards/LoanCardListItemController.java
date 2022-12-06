package br.org.femass.controllers.components.cards;

import br.org.femass.models.read.LoanCardModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class LoanCardListItemController implements Initializable {
    @FXML
    public Label readerNameLabel;

    @FXML
    public Label expectedReturnDateLabel;

    @FXML
    public Label returnDateLabel;

    @FXML
    public Label idLabel;

    @FXML
    public Label readerTypeLabel;

    @FXML
    public AnchorPane cardContainer;

    @FXML
    public Label loanDateLabel;

    @FXML
    public Label loanedBooksCount;

    @FXML
    public Label statusLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setCardData(LoanCardModel loan) {

        idLabel.setText(loan.getId().toString());
        readerNameLabel.setText(loan.getReaderName());
        readerTypeLabel.setText(loan.getReaderType());
        loanDateLabel.setText(loan.getLoanDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        expectedReturnDateLabel.setText(loan.getExpectedReturnDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        loanedBooksCount.setText(loan.getBoredBooksCount().toString());
        statusLabel.setText(loan.getLoanStatus());

        if(loan.getReturnDate() != null) {
            returnDateLabel.setText(loan.getReturnDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }


    }
}
