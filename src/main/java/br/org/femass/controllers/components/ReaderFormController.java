package br.org.femass.controllers.components;

import br.org.femass.daos.ReaderDao;
import br.org.femass.models.*;
import br.org.femass.models.read.ReaderCardModel;
import br.org.femass.utils.enums.Country;
import br.org.femass.utils.enums.Nationality;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ReaderFormController implements Initializable {

    @FXML
    public ImageView closeButton;
    @FXML
    public Button cancelButton;
    @FXML
    public Button saveButton;
    @FXML
    public Button deleteButton;
    @FXML
    public Label headerTitle;
    @FXML
    public TextField codeInput;
    @FXML
    public TextField nameInput;
    @FXML
    public TextField streetNameInput;
    @FXML
    public TextField addressNumberInput;
    @FXML
    public TextField neighborhoodInput;
    @FXML
    public TextField cityInput;
    @FXML
    public TextField stateInput;
    @FXML
    public ComboBox<Country> countryOption;
    @FXML
    public TextField areaCodeInput;
    @FXML
    public TextField telephoneNumberInput;
    @FXML
    public TextField registerInput;
    @FXML
    public TextField subjectInput;
    @FXML
    public RadioButton teacherRadio;
    @FXML
    public RadioButton studentRadio;
    @FXML
    public HBox registerContainer;
    @FXML
    public HBox subjectContainer;

    private Stage _stage;
    private Reader _reader;
    private Boolean _isNew;

    private final ReaderDao _dao = new ReaderDao();


    public ReaderFormController() { }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        codeInput.setDisable(true);
        populateCountryOption();
    }

    public void setStage(Stage stage) {
        this._stage = stage;
    }

    public void setReader(Reader reader) {
        this._reader = reader;
        loadData(this._reader);
    }

    public void selectTeacherRadio(MouseEvent mouseEvent) {
        studentRadio.setSelected(false);
        registerContainer.setVisible(false);
        subjectContainer.setVisible(true);
    }

    public void selectStudentRadio(MouseEvent mouseEvent) {
        teacherRadio.setSelected(false);
        subjectContainer.setVisible(false);
        registerContainer.setVisible(true);
    }

    public void closeDialog(MouseEvent mouseEvent) {
        this._stage.hide();
    }

    public void cancel(MouseEvent mouseEvent) {
        this._stage.hide();
    }

    public void save(MouseEvent mouseEvent) {

        Address address = new Address(
                streetNameInput.getText(),
                Long.parseLong(addressNumberInput.getText()),
                neighborhoodInput.getText(),
                cityInput.getText(),
                stateInput.getText(),
                countryOption.getSelectionModel().getSelectedItem()
        );

        Telephone telephone = new Telephone(
                Integer.parseInt(areaCodeInput.getText()),
                Long.parseLong(telephoneNumberInput.getText())
        );

        if(teacherRadio.isSelected()) {
            if(codeInput.getText().isEmpty() || codeInput.getText().isBlank() || codeInput.getText() == null) {
                this._reader = new Teacher(
                        nameInput.getText(),
                        address,
                        telephone,
                        subjectInput.getText()
                );
            } else {
                this._reader = new Teacher(
                        Long.parseLong(codeInput.getText()),
                        nameInput.getText(),
                        address,
                        telephone,
                        subjectInput.getText()
                );
            }
        } else {

            if(codeInput.getText().isEmpty() || codeInput.getText().isBlank() || codeInput.getText() == null) {
                this._reader = new Student(
                        nameInput.getText(),
                        address,
                        telephone,
                        registerInput.getText()
                );
            } else {
                this._reader  = new Student(
                        Long.parseLong(codeInput.getText()),
                        nameInput.getText(),
                        address,
                        telephone,
                        registerInput.getText()
                );
            }

        }

        if(isValid(this._reader)) {
            if(this._reader.getId() == null) {
                create(this._reader);
            } else {
                change(this._reader);
            }
        } else {
            return;
        }
    }

    public void delete(MouseEvent mouseEvent) {
        this._dao.delete(this._reader);
        this._stage.hide();
    }


    public boolean isValid(Reader reader) {
        if(reader.getName().isEmpty() || reader.getName().isBlank() || reader.getName() == null) {
            JOptionPane.showMessageDialog(null, "Nome não preenchido");
            return false;
        }

        if(reader.getAddress().getStreetName().isEmpty() || reader.getAddress().getStreetName().isBlank() || reader.getAddress().getStreetName() == null) {
            JOptionPane.showMessageDialog(null, "Rua não preenchida");
            return false;
        }

        if(reader.getAddress().getNumber() == null) {
            JOptionPane.showMessageDialog(null, "Número do endereço não preenchido");
            return false;
        }

        if((reader.getAddress().getNeighborhood().isEmpty() || reader.getAddress().getNeighborhood().isBlank() || reader.getAddress().getStreetName() == null)) {
            JOptionPane.showMessageDialog(null, "Bairro não preenchido");
            return false;
        }

        if((reader.getAddress().getCity().isEmpty() || reader.getAddress().getCity().isBlank() || reader.getAddress().getStreetName() == null)) {
            JOptionPane.showMessageDialog(null, "Cidade não preenchida");
            return false;
        }

        if((reader.getAddress().getState().isEmpty() || reader.getAddress().getState().isBlank() || reader.getAddress().getState() == null)) {
            JOptionPane.showMessageDialog(null, "Estado não preenchido");
            return false;
        }

        if((reader.getAddress().getCountry() == Country.EMPTY || reader.getAddress().getCountry() == null)) {
            JOptionPane.showMessageDialog(null, "Pais não preenchido");
            return false;
        }

        if((reader.getTelephone().getAreaCode() == null)) {
            JOptionPane.showMessageDialog(null, "DDD não preenchido");
            return false;
        }

        if((reader.getTelephone().getNumber() == null)) {
            JOptionPane.showMessageDialog(null, "Telefone não preenchido");
            return false;
        }

       if(teacherRadio.isSelected()) {
           Teacher teacher = (Teacher) reader;
           if(teacher.getSubject().isEmpty() || teacher.getSubject().isBlank() || teacher.getSubject() == null) {
               JOptionPane.showMessageDialog(null, "Disciplina não preenchida");
               return false;
           }
       }

        if(studentRadio.isSelected()) {
            Student teacher = (Student) reader;
            if(teacher.getRegister().isEmpty() || teacher.getRegister().isBlank() || teacher.getRegister() == null) {
                JOptionPane.showMessageDialog(null, "Matrícula não preenchida");
                return false;
            }
        }

        return true;
    }


    private void populateCountryOption() {
        countryOption.getItems().setAll(Country.values());
    }

    private void create(Reader reader) {

        this._dao.save(reader);
        this._stage.hide();
    }

    private void change(Reader reader) {
        this._dao.change(reader);
        this._stage.hide();
    }

    private void loadData(Reader reader) {
        if(reader == null) {
            headerTitle.setText("NOVO LEITOR");
            deleteButton.setVisible(false);


        } else {

            headerTitle.setText("EDITAR LEITOR");
            deleteButton.setVisible(true);
            codeInput.setText(reader.getId().toString());
            nameInput.setText(reader.getName());
            streetNameInput.setText(reader.getAddress().getStreetName());
            addressNumberInput.setText(reader.getAddress().getNumber().toString());
            neighborhoodInput.setText(reader.getAddress().getNeighborhood());
            cityInput.setText(reader.getAddress().getCity());
            stateInput.setText(reader.getAddress().getState());
            countryOption.setValue(reader.getAddress().getCountry());
            areaCodeInput.setText(reader.getTelephone().getAreaCode().toString());
            telephoneNumberInput.setText(reader.getTelephone().getNumber().toString());

            studentRadio.setDisable(true);
            teacherRadio.setDisable(true);

            if(reader.getDeadlineForReturn() == 15) {
                studentRadio.setSelected(true);
                teacherRadio.setSelected(false);

                registerContainer.setVisible(true);
                subjectContainer.setVisible(false);

                registerInput.setText(((Student) reader).getRegister());
            } else {
                studentRadio.setSelected(false);
                teacherRadio.setSelected(true);

                registerContainer.setVisible(false);
                subjectContainer.setVisible(true);

                subjectInput.setText(((Teacher) reader).getSubject());
            }

        }
    }
}
