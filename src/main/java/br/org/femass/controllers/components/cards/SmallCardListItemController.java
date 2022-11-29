package br.org.femass.controllers.components.cards;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.image.Image;
import java.awt.*;
import java.net.URL;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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

    public void setData() {

        readerLoanLabel.setText("Lucas Sales");
        readerRoleLoanLabel.setText("Aluno");
        statusLoanBadge.setText("Emprestado");
        loanDataLabel.setText("22/02/2022");
        returnForecastDataLabel.setText("22/03/2022");
        returnDataLabel.setText("20/03/2022");
        numberOfBooksLabel.setText("3");

    }
}
