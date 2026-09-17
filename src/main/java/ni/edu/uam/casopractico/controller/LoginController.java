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
    TextField txtUsername;
    @FXML
    PasswordField pssPassword;
    @FXML
    Button btnLogin;
    Stage stage = (Stage) btnLogin.getScene().getWindow();

    @FXML
    private void iniciarSesion() {
        String user = txtUsername.getText().trim();
        String pass = pssPassword.getText().trim();
        int intentos = 0;

        while (intentos < 3) {
            if (user.isEmpty() || pssPassword.getText().isEmpty()) {
                advertencia("Campos incompletos", "Debe completar todos los campos.");
                limpiar();
                intentos++;
                return;
            } else if (user.equals("admin") && pssPassword.equals("1234")) {
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
        txtUsername.clear();
        pssPassword.clear();
        txtUsername.requestFocus();
    }
}
