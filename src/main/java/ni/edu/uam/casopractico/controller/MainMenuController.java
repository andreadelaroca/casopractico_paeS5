package ni.edu.uam.casopractico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import static ni.edu.uam.casopractico.util.NavigationManager.*;

public class MainMenuController {

    @FXML private MenuItem menuCerrarSesion;
    @FXML private MenuItem menuSalir;
    @FXML private MenuItem menuRegistrarCliente;
    @FXML private MenuItem menuConsultarClientes;
    @FXML private MenuItem menuAcercaDe;

    @FXML private Button btnTbRegistro;
    @FXML private Button btnTbConsulta;
    @FXML private Button btnCardRegistro;
    @FXML private Button btnCardConsulta;

    @FXML private Label lblEstado;

    @FXML
    public void initialize() {
        lblEstado.setText("Estado: Sesión activa");
    }

    @FXML
    private void handleNavRegistro(ActionEvent event) {
        lblEstado.setText("Estado: Cargando pantalla de registro...");
        abrirVentana("ni/edu/uam/view/ClienteRegistroView.fxml", "Registro de Clientes");
        mostrarAlerta(Alert.AlertType.INFORMATION, "Navegación", "Módulo de Registro de Clientes");
    }

    @FXML
    private void handleNavConsulta(ActionEvent event) {
        lblEstado.setText("Estado: Cargando pantalla de consulta...");
        abrirVentana("ni/edu/uam/view/ClienteConsultaView.fxml", "Registro de Clientes");
        mostrarAlerta(Alert.AlertType.INFORMATION, "Navegación", "Módulo de Consulta de Clientes");
    }

    @FXML
    private void handleCerrarSesion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea cerrar la sesión actual?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Cerrar Sesión");
        alert.setHeaderText(null);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                lblEstado.setText("Estado: Sesión cerrada");
                abrirVentana("ni/edu/uam/view/LoginView.fxml", "Registro de Clientes");
            }
        });
    }

    @FXML
    private void handleSalir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea salir de la aplicación?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmar salida");
        alert.setHeaderText(null);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                System.exit(0);
            }
        });
    }

    @FXML
    private void handleAcercaDe(ActionEvent event) {
        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Acerca del Sistema",
                "Sistema de Gestión de Solicitudes v1.0\nUniversidad Americana (UAM)"
        );
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}