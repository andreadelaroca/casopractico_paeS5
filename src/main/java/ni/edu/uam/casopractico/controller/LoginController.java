package ni.edu.uam.casopractico.controller;

import javafx.application.Platform;
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

    Stage stage = (Stage) iniciarSesionButton.getScene().getWindow();

    @FXML
    private void iniciarSesion() {
        String user = usuarioTextField.getText().trim();
        String pass = pass.getText().trim();
        int intentos = 0;

        while (intentos < 3) {
            if (user.isEmpty() || pass.getText().isEmpty()) {
                advertencia("Campos incompletos", "Debe completar todos los campos.");
                limpiar();
                intentos++;
                return;
            } else if (user.equals("admin") && pass.equals("1234")) {
                informacion("Operación exitosa", "Ha iniciado sesión exitosamente.");
                abrirMenu();
            }
            else {
                error("Credenciales incorrectas", "Credenciales incorrectas, vuelva a intentarlo.");
                limpiar();
                intentos++;
            }
        }
        if (intentos == 3) {
            informacion("Intentos sobrepasados", "Ha intentado entrar al sistema 3 veces. Cerrando ventana.");
            stage.close();
        }
    }

    private void abrirMenu() {
        abrirVentana("/ni/edu/uam/view/MainMenuView.fxml", "Menú Principal");
        stage.close();
    }

    private void limpiar() {
        usuarioTextField.clear();
        passwordField.clear();
        usuarioTextField.requestFocus();
    }
}
