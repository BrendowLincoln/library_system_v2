package br.org.femass.controllers;

import br.org.femass.utils.shared.DataProvider;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthorsController extends ControllerBase implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureUserData();

        System.out.println(DataProvider.getDataByKey("bookSearchHome"));
    }
}
