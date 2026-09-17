package ni.edu.uam.casopractico.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static ni.edu.uam.casopractico.util.AlertHelper.*;
import static ni.edu.uam.casopractico.util.NavigationManager.*;

public class LoginController {

    @FXML
    private TextField usuarioTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button iniciarSesionButton;
    @FXML
    private Button salirButton;
    private int intentos = 0;

    @FXML
    private void handleIniciarSesion() {
        String user = usuarioTextField.getText().trim();
        String pass = passwordField.getText().trim();

        while (intentos < 3) {
            if (user.isEmpty() || pass.isEmpty()) {
                advertencia("Campos incompletos", "Debe completar todos los campos.");
                limpiar();
                return;
            }
            if (user.equals("admin") && pass.equals("1234")) {
                informacion("Operación exitosa", "Ha iniciado sesión exitosamente.");
                abrirMenu();
            }
            else {
                intentos++;
                if (intentos >= 3) {
                    informacion("Intentos sobrepasados", "Ha intentado entrar al sistema 3 veces. Cerrando ventana.");
                    cerrarVentana();
                }
                else {
                    error("Credenciales incorrectas", "Credenciales incorrectas, vuelva a intentarlo.");
                    limpiar();
                }
            }
        }
    }


    @FXML
    private void handleSalir(ActionEvent event) {
        cerrarVentana();
    }

    private void abrirMenu() {
        abrirVentana("/ni/edu/uam/view/MainMenuView.fxml", "Menú Principal");
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) iniciarSesionButton.getScene().getWindow();
        stage.close();
    }
    private void limpiar() {
        usuarioTextField.clear();
        passwordField.clear();
        usuarioTextField.requestFocus();
    }
}
