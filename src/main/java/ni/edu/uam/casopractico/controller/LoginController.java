package ni.edu.uam.casopractico.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    TextField txtUsername;
    @FXML
    PasswordField pssPassword;

    @FXML
    private void iniciarSesion() {
        String user = txtUsername.getText().trim();
        String pass = pssPassword.getText().trim();
        if (user.isEmpty() || pssPassword.getText().isEmpty()) {

        }
    }

}
