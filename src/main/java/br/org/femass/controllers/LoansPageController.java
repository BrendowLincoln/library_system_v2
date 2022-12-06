package br.org.femass.controllers;

import br.org.femass.controllers.components.LoanFormController;
import br.org.femass.controllers.components.cards.LoanCardListItemController;
import br.org.femass.daos.LoanDao;
import br.org.femass.models.Loan;
import br.org.femass.models.read.LoanCardModel;
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
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class LoansPageController extends ControllerBase implements Initializable {

    @FXML
    public VBox cardList;
    @FXML
    public ScrollPane scrollContainer;
    @FXML
    public TextField searchInput;
    @FXML
    public Button searchButton;

    private final LoanDao _dao = new LoanDao();

    private List<Loan> _loans = new ArrayList<>();

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
    public void openForm(Loan loan) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Stage formStage = new Stage();

        URL location = getClass().getResource("/fxml/components/LoanForm.fxml");
        loader.setLocation(location);
        loader.setBuilderFactory(new JavaFXBuilderFactory());


        Parent root = loader.load(location.openStream());
        LoanFormController controller = loader.getController();


        Scene scene = new Scene(root);
        formStage.setTitle("Cadastro de empréstimo");
        formStage.setScene(scene);
        controller.setStage(formStage);
        formStage.initStyle(StageStyle.UNDECORATED);
        formStage.setResizable(false);
        formStage.getScene().setFill(Color.TRANSPARENT);
        formStage.setOnShown((x) -> {
            controller.setLoan(loan);
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
                this._loans = this._dao.getAll();
            } else {
                this._loans = this._dao.getByFilter(filter);
            }

            List<LoanCardModel> loanCard = _loans.stream().map((loan) -> {

                String readerType = loan.getReader().getDeadlineForReturn() == 15 ? "Aluno" : "Professor";

                return new LoanCardModel(
                        loan.getId(),
                        loan.getLoanDate(),
                        loan.getExpectedReturnDate(),
                        loan.getReturnDate(),
                        loan.getCopies().size(),
                        readerType,
                        loan.getReader().getName(),
                        loan.loanStatus()
                );
            }).collect(Collectors.toList());

            if(loanCard.isEmpty()) {
                Label emptyResultLabel = new Label("Nenhum empréstimo encontrado");
                emptyResultLabel.getStyleClass().set(0, "authors-group-empty-message");
                cardList.getChildren().clear();
                cardList.getChildren().add(emptyResultLabel);
                cardList.setAlignment(Pos.CENTER);
            }


            for (int i = 0; i < loanCard.size(); i++) {

                if(i == 0) {
                    cardList.setAlignment(Pos.TOP_CENTER);
                    cardList.getChildren().clear();
                }

                FXMLLoader loader = new FXMLLoader();

                URL location = getClass().getResource("/fxml/components/cards/LoanCardListItem.fxml");
                loader.setLocation(location);
                loader.setBuilderFactory(new JavaFXBuilderFactory());
                Node lastEmployeeCard= loader.load(location.openStream());

                LoanCardListItemController controller = loader.getController();
                controller.setCardData(loanCard.get(i));

                Loan clickedLoan = _loans.get(i);

                lastEmployeeCard.setOnMouseClicked((x) -> {
                    try {
                        openForm(clickedLoan);
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
