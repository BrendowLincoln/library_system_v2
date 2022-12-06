package br.org.femass.controllers.components;

import br.org.femass.daos.AuthorDao;
import br.org.femass.daos.ReaderDao;
import br.org.femass.models.Author;
import br.org.femass.models.Reader;
import br.org.femass.utils.enums.Nationality;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OverdueBookDialogController implements Initializable {

    @FXML
    public ImageView closeButton;
    @FXML
    public Button confirmButton;
    @FXML
    public Label headerTitle;
    @FXML
    public ListView<Reader> overdueReadersList;

    private Stage _stage;
    private List<Reader> _overdueReaders;

    private final ReaderDao _dao = new ReaderDao();


    public OverdueBookDialogController() { }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this._overdueReaders = _dao.getOverdueReaders();

        ObservableList<Reader> observableList = FXCollections.observableArrayList(this._overdueReaders);
        overdueReadersList.setItems(observableList);
    }

    public void confirm(MouseEvent mouseEvent) {

        this._stage.hide();
    }

    public void setStage(Stage stage) {
        this._stage = stage;
    }

    public void closeDialog(MouseEvent mouseEvent) {
        this._stage.hide();
    }

}
