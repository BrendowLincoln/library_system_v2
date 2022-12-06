package br.org.femass.controllers;

import br.org.femass.controllers.components.EmployeeFormController;
import br.org.femass.controllers.components.cards.EmployeeCardListItemController;
import br.org.femass.daos.EmployeeDao;
import br.org.femass.models.Employee;
import br.org.femass.models.read.EmployeeCardModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EmployeePageController extends ControllerBase implements Initializable {

    @FXML
    public TextField searchInput;
    @FXML
    public VBox cardList;



    private final EmployeeDao _dao = new EmployeeDao();

    private List<Employee> _employees = new ArrayList<>();


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
    public void openForm(Employee employee) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Stage formStage = new Stage();

        URL location = getClass().getResource("/fxml/components/EmployeeForm.fxml");
        loader.setLocation(location);
        loader.setBuilderFactory(new JavaFXBuilderFactory());


        Parent root = loader.load(location.openStream());
        EmployeeFormController controller = loader.getController();


        Scene scene = new Scene(root);
        formStage.setTitle("Cadastro de funcionários");
        formStage.setScene(scene);
        controller.setStage(formStage);
        formStage.initStyle(StageStyle.UNDECORATED);
        formStage.setResizable(false);
        formStage.getScene().setFill(Color.TRANSPARENT);
        formStage.setOnShown((x) -> {
            controller.setEmployee(employee);
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

            if(filter.isEmpty() || filter.isBlank()) {
                this._employees = this._dao.getAll();
            } else {
                this._employees = this._dao.getByFilter(filter);
            }

            List<EmployeeCardModel> employeeCards = _employees.stream().map((employee) -> {

                return new EmployeeCardModel(
                        employee.getId(),
                        employee.getName(),
                        employee.getUser().getRole()
                );
            }).collect(Collectors.toList());

            if(employeeCards.isEmpty()) {
                Label emptyResultLabel = new Label("Nenhum autor encontrado");
                emptyResultLabel.getStyleClass().set(0, "authors-group-empty-message");
                cardList.getChildren().clear();
                cardList.getChildren().add(emptyResultLabel);
                cardList.setAlignment(Pos.CENTER);
            }


            for (int i = 0; i < employeeCards.size(); i++) {

                if(i == 0) {
                    cardList.setAlignment(Pos.TOP_CENTER);
                    cardList.getChildren().clear();
                }

                FXMLLoader loader = new FXMLLoader();

                URL location = getClass().getResource("/fxml/components/cards/EmployeeCardListItem.fxml");
                loader.setLocation(location);
                loader.setBuilderFactory(new JavaFXBuilderFactory());
                Node lastEmployeeCard= loader.load(location.openStream());

                EmployeeCardListItemController controller = loader.getController();
                controller.setCardData(employeeCards.get(i));

                Employee clickedAuthor = _employees.get(i);

                lastEmployeeCard.setOnMouseClicked((x) -> {
                    try {
                        openForm(clickedAuthor);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                cardList.getChildren().add(lastEmployeeCard);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //endregion PRIVATE METHODS
}
