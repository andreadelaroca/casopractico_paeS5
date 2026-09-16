package ni.edu.uam.casopractico.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import static ni.edu.uam.casopractico.util.AlertHelper.*;
import static ni.edu.uam.casopractico.util.NavigationManager.*;

public class LoginController {

    @FXML
    TextField txtUsername;
    @FXML
    PasswordField pssPassword;

    @FXML
    private void iniciarSesion() {
        String user = txtUsername.getText().trim();
        String pass = pssPassword.getText().trim();
        int intentos = 0;

        while (intentos < 3) {
            if (user.isEmpty() || pssPassword.getText().isEmpty()) {
                advertencia("Completar los campos", "Debe completar todos los campos para entrar al sistema.");
                limpiar();
                intentos++;
            } else if (user == "admin" && pssPassword.getText() == "1234") {
                informacion("Operación exitosa", "Ha iniciado sesión exitosamente.");
                abrirVentana("/menu-view.fxml", "Menú principal");
            }
            else {
                error("Credenciales incorrectas", "Credenciales incorrectas, vuelva a intentarlo.");
                limpiar();
                intentos++;
            }
        }
        if (intentos == 3) {
            informacion("Intentos sobrepasados", "Ha intentado entrar al sistema 3 veces. Cerrando ventana.");
            cerrar();
        }
    }

    private void limpiar() {
        txtUsername.clear();
        pssPassword.clear();
        txtUsername.requestFocus();
    }

    private void cerrar() {
        Platform.exit();
    }
}
