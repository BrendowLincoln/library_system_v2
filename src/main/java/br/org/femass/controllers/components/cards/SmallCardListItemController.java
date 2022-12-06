package br.org.femass.controllers.components.cards;

import br.org.femass.models.read.LoanCardModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.image.Image;
import java.awt.*;
import java.net.URL;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SmallCardListItemController implements Initializable {
    @FXML
    public Label readerLoanLabel;
    @FXML
    public Label loanDataLabel;
    @FXML
    public Label readerRoleLoanLabel;
    @FXML
    public Label returnForecastDataLabel;
    @FXML
    public Label returnDataLabel;
    @FXML
    public Label statusLoanBadge;
    @FXML
    public Label numberOfBooksLabel;
    @FXML
    public ImageView bookImage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setCardData(LoanCardModel loan) {

        readerLoanLabel.setText(loan.getReaderName());
        readerRoleLoanLabel.setText(loan.getReaderType());
        statusLoanBadge.setText(loan.getLoanStatus());
        loanDataLabel.setText(loan.getLoanDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        returnForecastDataLabel.setText(loan.getExpectedReturnDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        returnDataLabel.setText(loan.getReturnDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        numberOfBooksLabel.setText(loan.getBoredBooksCount().toString());

    }
}
