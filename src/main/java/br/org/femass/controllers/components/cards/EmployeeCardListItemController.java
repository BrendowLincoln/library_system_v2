package br.org.femass.controllers.components.cards;

import br.org.femass.models.read.AuthorCardModel;
import br.org.femass.models.read.EmployeeCardModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeCardListItemController implements Initializable {

    @FXML
    public Label idLabel;

    @FXML
    public Label employeeRoleLabel;

    @FXML
    public Label employeeNameLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setCardData(EmployeeCardModel employee) {
        idLabel.setText(employee.getId().toString());
        employeeNameLabel.setText(employee.getName());
        employeeRoleLabel.setText(employee.getRole().toString());
    }

}
