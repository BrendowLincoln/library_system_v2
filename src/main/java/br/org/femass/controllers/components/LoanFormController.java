package br.org.femass.controllers.components;

import br.org.femass.daos.CopyDao;
import br.org.femass.daos.LoanDao;
import br.org.femass.daos.ReaderDao;
import br.org.femass.models.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class LoanFormController implements Initializable {

    @FXML
    public ImageView closeButton;
    @FXML
    public Button cancelButton;
    @FXML
    public Button saveButton;
    @FXML
    public Button returnLoanButton;
    @FXML
    public Label headerTitle;
    @FXML
    public TextField codeInput;
    @FXML
    public DatePicker loanDateInput;
    @FXML
    public DatePicker expectedReturnDateInput;
    @FXML
    public DatePicker returnDateInput;
    @FXML
    public ComboBox<Reader> readerOption;
    @FXML
    public Button removeAuthorButton;
    @FXML
    public Button addCopyButton;
    @FXML
    public Button removeCopyButton;
    @FXML

    public ListView<Copy> copiesList;
    @FXML
    public ComboBox<Copy> copiesOption;

    private Stage _stage;
    private Loan _loan;
    private Boolean _isNew;

    private final ReaderDao _readerDao = new ReaderDao();
    private final CopyDao _copyDao = new CopyDao();
    private final LoanDao _loanDao = new LoanDao();


    public LoanFormController() { }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        readerOption.valueProperty().addListener(new ChangeListener<Reader>() {
            @Override
            public void changed(ObservableValue<? extends Reader> observableValue, Reader reader, Reader newReader) {
                expectedReturnDateInput.setValue(LocalDate.now().plusDays(newReader.getDeadlineForReturn()));
            }
        });


        codeInput.setDisable(true);
        populateAuthorsOption();
        populateCopiesOption();
    }

    public void setStage(Stage stage) {
        this._stage = stage;
    }

    public void setLoan(Loan loan) {
        this._loan = loan;
        loadData(this._loan);
    }

    public void closeDialog(MouseEvent mouseEvent) {
        this._stage.hide();
    }

    public void cancel(MouseEvent mouseEvent) {
        this._stage.hide();
    }

    public void save(MouseEvent mouseEvent) {
        this._loan = new Loan(
                codeInput.getText().isEmpty() ? null : Long.parseLong(codeInput.getText()),
                loanDateInput.getValue(),
                expectedReturnDateInput.getValue(),
                returnDateInput.getValue(),
                copiesList.getItems(),
                readerOption.getValue()
        );

        if(isValid(this._loan)) {
            create(this._loan);
        } else {
            return;
        }
    }

    public boolean isValid(Loan loan) {
        if(loan.getReader() == null) {
            JOptionPane.showMessageDialog(null, "Necessário selecionar um leitor");
            return false;
        }

        if(loan.getCopies().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Necessário adicionar pelo menos uma cópia");
            return false;
        }

        return true;
    }


    private void populateAuthorsOption() {
        List<Reader> readers = new ArrayList<>();
        readers.addAll(_readerDao.getAllStudents());
        readers.addAll(_readerDao.getAllTeachers());
        ObservableList<Reader> observableAuthorsList = FXCollections.observableArrayList(readers);
        readerOption.setItems(observableAuthorsList);
    }

    private void populateCopiesOption() {
        List<Copy> availableCopies = _copyDao.getAvailableCopies();
        ObservableList<Copy> copyObservableList = FXCollections.observableArrayList(availableCopies);
        copiesOption.setItems(copyObservableList);
    }

    private void create(Loan loan) {
        loan.getCopies().forEach(copy -> copy.setIsLoaned(true));
        this._loanDao.save(loan);
        this._stage.hide();
    }

    private void returnLoan(Loan loan) {
        List<Copy> copies = _copyDao.getAll();
        loan.getCopies().forEach(copy -> {
            copy.setIsLoaned(false);
            Copy updatedCopy =  copies.stream().filter(x -> x.getId() == copy.getId()).findFirst().get();
            updatedCopy.setIsLoaned(false);
            _copyDao.change(updatedCopy);
        });

        this._loanDao.change(loan);
        this._stage.hide();
    }

    private void loadData(Loan loan) {
        if(loan == null) {
            headerTitle.setText("NOVO EMPRÉSTIMO");
            returnLoanButton.setVisible(false);
            removeCopyButton.setVisible(false);



            loanDateInput.setValue(LocalDate.now());
        } else {
            headerTitle.setText("VER EMPRÉSTIMO");
            returnLoanButton.setVisible(true);

            codeInput.setText(loan.getId().toString());
            loanDateInput.setValue(loan.getLoanDate());
            expectedReturnDateInput.setValue(loan.getExpectedReturnDate());
            returnDateInput.setValue(loan.getReturnDate());
            readerOption.setValue(loan.getReader());


            populateCopiesList(loan.getCopies());

            copiesOption.setDisable(true);
            removeCopyButton.setVisible(false);
            addCopyButton.setVisible(false);
            saveButton.setVisible(false);

            if(loan.isReturned()) {
                returnLoanButton.setVisible(false);
            }
        }


    }

    public void addCopy(MouseEvent mouseEvent) {
        Copy selectedCopy = copiesOption.getValue();
        if(selectedCopy == null) {
            return;
        }
        boolean alreadyAdded = copiesList.getItems().stream().map(x -> x.getId()).collect(Collectors.toList()).contains(selectedCopy.getId());

        if(!alreadyAdded) {
            copiesList.getItems().add(selectedCopy);
            copiesOption.getSelectionModel().select(-1);
        }
        removeCopyButton.setVisible(!copiesList.getItems().isEmpty());
    }

    public void removeCopy(MouseEvent mouseEvent) {
        Copy selectedCopy = copiesOption.getSelectionModel().getSelectedItem();
        if(selectedCopy == null) {
            return;
        }
        copiesList.getItems().remove(selectedCopy);
        copiesOption.getSelectionModel().select(-1);
        removeAuthorButton.setVisible(!copiesList.getItems().isEmpty());
    }

    private void populateCopiesList(List<Copy> copies) {
        ObservableList observableList = FXCollections.observableArrayList(copies);
        copiesList.setItems(observableList);
    }

    public void returnLoan(MouseEvent mouseEvent) {
        returnDateInput.setValue(LocalDate.now());

        this._loan = new Loan(
                codeInput.getText().isEmpty() ? null : Long.parseLong(codeInput.getText()),
                loanDateInput.getValue(),
                expectedReturnDateInput.getValue(),
                returnDateInput.getValue(),
                copiesList.getItems(),
                readerOption.getValue()
        );

        returnLoan(this._loan);

        returnLoanButton.setVisible(false);
    }
}
