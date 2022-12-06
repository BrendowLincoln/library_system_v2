package br.org.femass.controllers;

import br.org.femass.controllers.components.ReaderFormController;
import br.org.femass.controllers.components.cards.BookCardListItemController;
import br.org.femass.controllers.components.cards.ReaderCardListItemController;
import br.org.femass.daos.ReaderDao;
import br.org.femass.models.Reader;
import br.org.femass.models.Student;
import br.org.femass.models.Teacher;
import br.org.femass.models.read.ReaderCardModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ReadersPageController extends ControllerBase implements Initializable {

    @FXML
    public VBox cardList;
    @FXML
    public ScrollPane scrollContainer;
    @FXML
    public TextField searchInput;
    @FXML
    public Button searchButton;

    private final ReaderDao _dao = new ReaderDao();

    private List<Reader> _readers = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureUserData();
        updateList("");
    }

    //region EVENT METHODS
    public void searchKeyPressed(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
           updateList(this.searchInput.getText());
        }
    }

    public void searchBookClick(MouseEvent mouseEvent) throws IOException {
        updateList(this.searchInput.getText());
    }

    public void add(MouseEvent mouseEvent) {
        try {
            openForm(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion EVENT METHODS

    //region PUBLIC METHODS
    public void openForm(Reader reader) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Stage formStage = new Stage();

        URL location = getClass().getResource("/fxml/components/ReaderForm.fxml");
        loader.setLocation(location);
        loader.setBuilderFactory(new JavaFXBuilderFactory());


        Parent root = loader.load(location.openStream());
        ReaderFormController controller = loader.getController();


        Scene scene = new Scene(root);
        formStage.setTitle("Cadastro de Autores");
        formStage.setScene(scene);
        controller.setStage(formStage);
        formStage.initStyle(StageStyle.UNDECORATED);
        formStage.setResizable(false);
        formStage.getScene().setFill(Color.TRANSPARENT);
        formStage.setOnShown((x) -> {
            controller.setReader(reader);
        });
        formStage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                formStage.hide();
            }
        });
        formStage.setOnHidden((event) -> updateList(""));
        formStage.show();
    }
    //endregion PUBLIC METHODS

    //region PRIVATE METHODS
    private void updateList(String filter) {
        try {
            this._readers = new ArrayList<>();

            if(filter.isEmpty() || filter.isBlank()) {
                this._readers.addAll(this._dao.getAllTeachers());
                this._readers.addAll(this._dao.getAllStudents());
            } else {
                this._readers.addAll(this._dao.getStudentsByFilter(filter));
                this._readers.addAll(this._dao.getTeachersByFilter(filter));
            }

            this._readers.sort(new Comparator<Reader>() {
                @Override
                public int compare(Reader r1, Reader r2) {

                    if (r1.getId() > r2.getId()) {
                        return 1;
                    }

                    if (r2.getId() > r1.getId()) {
                        return -1;
                    }
                    return 0;
                }
            });

            List<ReaderCardModel> readerCards = _readers.stream().map((reader) -> {
                if(reader.getDeadlineForReturn() == 15) {
                    return new ReaderCardModel(
                            reader.getId(),
                            reader.getName(),
                            reader.getAddress().toString(),
                            reader.getTelephone().toString(),
                            ((Student) reader).getRegister(),
                            null,
                            1,
                            "Aluno"
                    );
                }

                return new ReaderCardModel(
                        reader.getId(),
                        reader.getName(),
                        reader.getAddress().toString(),
                        reader.getTelephone().toString(),
                        null,
                        ((Teacher) reader).getSubject(),
                        1,
                        "Professor"
                );
            }).collect(Collectors.toList());

            if(readerCards.isEmpty()) {
                Label emptyResultLabel = new Label("Nenhum leitor encontrado");
                emptyResultLabel.getStyleClass().set(0, "group-empty-message");
                cardList.getChildren().clear();
                cardList.getChildren().add(emptyResultLabel);
                cardList.setAlignment(Pos.CENTER);
            }


            for (int i = 0; i < readerCards.size(); i++) {

                if(i == 0) {
                    cardList.setAlignment(Pos.TOP_CENTER);
                    cardList.getChildren().clear();
                }

                FXMLLoader loader = new FXMLLoader();

                URL location = getClass().getResource("/fxml/components/cards/ReaderCardListItem.fxml");
                loader.setLocation(location);
                loader.setBuilderFactory(new JavaFXBuilderFactory());
                Node lastReaderCard= loader.load(location.openStream());

                ReaderCardListItemController controller = loader.getController();
                controller.setCardData(readerCards.get(i));

                Reader clickedReader = _readers.get(i);

                lastReaderCard.setOnMouseClicked((x) -> {
                    try {
                        openForm(clickedReader);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                cardList.getChildren().add(lastReaderCard);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //endregion PRIVATE METHODS
}
