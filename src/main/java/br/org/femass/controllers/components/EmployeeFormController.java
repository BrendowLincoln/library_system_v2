package br.org.femass.controllers.components;

import br.org.femass.daos.EmployeeDao;
import br.org.femass.models.Employee;
import br.org.femass.models.User;
import br.org.femass.utils.enums.Role;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

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
    public TextField emailInput;
    @FXML
    public TextField passwordInput;
    @FXML
    public ComboBox<Role> roleOption;

    private Stage _stage;
    private Employee _employee;
    private Boolean _isNew;

    private final EmployeeDao _dao = new EmployeeDao();


    public EmployeeFormController() { }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        codeInput.setDisable(true);
        populateRoleOption();
    }

    public void setStage(Stage stage) {
        this._stage = stage;
    }

    public void setEmployee(Employee employee) {
        this._employee = employee;
        loadData(this._employee);
    }

    public void closeDialog(MouseEvent mouseEvent) {
        this._stage.hide();
    }

    public void cancel(MouseEvent mouseEvent) {
        this._stage.hide();
    }

    public void save(MouseEvent mouseEvent) {
        this._employee = new Employee(
                codeInput.getText().isEmpty() ? null : Long.parseLong(codeInput.getText()),
                nameInput.getText(),
                new User(
                        emailInput.getText(),
                        passwordInput.getText(),
                        roleOption.getValue()
                )
        );

        if(isValid(_employee)) {
            if(this._employee.getId() == null) {
                create(this._employee);
            } else {
                change(this._employee);
            }
        } else {
            return;
        }
    }

    public void delete(MouseEvent mouseEvent) {
        this._dao.delete(this._employee);
        this._stage.hide();
    }


    public boolean isValid(Employee employee) {
        if(employee.getName().isEmpty() || employee.getName().isBlank() || employee.getName() == null) {
            JOptionPane.showMessageDialog(null, "Nome não preenchido");
            return false;
        }

        if(employee.getUser().getEmail().isEmpty() || employee.getUser().getEmail().isBlank() || employee.getUser().getEmail() == null) {
            JOptionPane.showMessageDialog(null, "Email não preenchido");
            return false;
        }

        if(employee.getUser().getPassword().isEmpty() || employee.getUser().getPassword().isBlank() || employee.getUser().getPassword() == null) {
            JOptionPane.showMessageDialog(null, "Senha não preenchida");
            return false;
        }

        if(employee.getUser().getRole() == null) {
            JOptionPane.showMessageDialog(null, "Função não preenchido");
            return false;
        }

        return true;
    }


    private void populateRoleOption() {
        roleOption.getItems().setAll(Role.values());
    }

    private void create(Employee employee) {

        this._dao.save(employee);
        this._stage.hide();
    }

    private void change(Employee employee) {
        this._dao.change(employee);
        this._stage.hide();
    }

    private void loadData(Employee employee) {
        if(employee == null) {
            headerTitle.setText("NOVO FUNCIONARIO");
            deleteButton.setVisible(false);


        } else {

            headerTitle.setText("EDITAR FUNCIONARIO");
            deleteButton.setVisible(true);

            codeInput.setText(employee.getId().toString());
            nameInput.setText(employee.getName());
            emailInput.setText(employee.getUser().getEmail());
            passwordInput.setText(employee.getUser().getPassword());
            roleOption.setValue(employee.getUser().getRole());

        }
    }

}
