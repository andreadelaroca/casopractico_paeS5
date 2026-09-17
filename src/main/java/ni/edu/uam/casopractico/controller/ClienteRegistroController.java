package ni.edu.uam.casopractico.controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ni.edu.uam.casopractico.model.Cliente;
import ni.edu.uam.casopractico.service.ClienteRepository;
import ni.edu.uam.casopractico.util.AlertHelper;

import java.io.File;
import java.time.LocalDate;

public class ClienteRegistroController {

    @FXML
    private TextField txtNombres;

    @FXML
    private TextField txtApellidos;

    @FXML
    private ComboBox<String> cmbTipoCliente;

    @FXML
    private ComboBox<String> cmbCiudad;

    @FXML
    private DatePicker dpFechaNacimiento;

    @FXML
    private RadioButton rbConsultoria;

    @FXML
    private RadioButton rbImplementacion;

    @FXML
    private RadioButton rbSoporte;

    @FXML
    private CheckBox chkAsesoria;

    @FXML
    private CheckBox chkInstalacion;

    @FXML
    private CheckBox chkCapacitacion;

    @FXML
    private ImageView imgFotografia;

    @FXML
    private Button btnSeleccionarFoto;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    private final ClienteRepository repository = ClienteRepository.getInstance();

    private final ToggleGroup grupoSolicitud = new ToggleGroup();

    private String rutaFoto;

    @FXML
    public void initialize() {

        // Configurar RadioButton
        rbConsultoria.setToggleGroup(grupoSolicitud);
        rbImplementacion.setToggleGroup(grupoSolicitud);
        rbSoporte.setToggleGroup(grupoSolicitud);

        // Opciones del ComboBox Tipo de Cliente
        cmbTipoCliente.getItems().addAll(
                "Regular",
                "Frecuente",
                "VIP"
        );

        // Opciones del ComboBox Ciudad
        cmbCiudad.getItems().addAll(
                "Managua",
                "León",
                "Granada",
                "Masaya",
                "Matagalpa",
                "Estelí"
        );

        // Seleccionar Consultoría por defecto
        rbConsultoria.setSelected(true);
    }

    @FXML
    private void handleSeleccionarFoto(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Seleccionar fotografía");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Imágenes",
                        "*.png",
                        "*.jpg",
                        "*.jpeg"
                )
        );

        Stage stage = (Stage) btnSeleccionarFoto.getScene().getWindow();

        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {

            rutaFoto = archivo.getAbsolutePath();

            Image imagen = new Image(archivo.toURI().toString());

            imgFotografia.setImage(imagen);
        }
    }

    @FXML
    private void handleGuardar(ActionEvent event) {

        if (!validarFormulario()) {
            return;
        }

        String nombres = txtNombres.getText().trim();

        String apellidos = txtApellidos.getText().trim();

        String tipoCliente = cmbTipoCliente.getValue();

        String ciudad = cmbCiudad.getValue();

        LocalDate fechaNacimiento = dpFechaNacimiento.getValue();

        String tipoSolicitud = obtenerTipoSolicitud();

        boolean servicioInternet = chkAsesoria.isSelected();

        boolean servicioTelefonia = chkInstalacion.isSelected();

        boolean servicioSoporte = chkCapacitacion.isSelected();

        Cliente cliente = new Cliente(
                nombres,
                apellidos,
                tipoCliente,
                ciudad,
                fechaNacimiento,
                tipoSolicitud,
                servicioInternet,
                servicioTelefonia,
                servicioSoporte,
                rutaFoto
        );

        repository.agregarCliente(cliente);

        AlertHelper.informacion(
                "Cliente registrado",
                "El cliente " + cliente.getNombreCompleto()
                        + " ha sido registrado correctamente."
        );

        limpiarFormulario();
    }

    private boolean validarFormulario() {

        if (txtNombres.getText().trim().isEmpty()) {

            AlertHelper.advertencia(
                    "Campo requerido",
                    "Debe ingresar los nombres del cliente."
            );

            txtNombres.requestFocus();

            return false;
        }

        if (txtApellidos.getText().trim().isEmpty()) {

            AlertHelper.advertencia(
                    "Campo requerido",
                    "Debe ingresar los apellidos del cliente."
            );

            txtApellidos.requestFocus();

            return false;
        }

        if (cmbTipoCliente.getValue() == null) {

            AlertHelper.advertencia(
                    "Campo requerido",
                    "Debe seleccionar el tipo de cliente."
            );

            cmbTipoCliente.requestFocus();

            return false;
        }

        if (cmbCiudad.getValue() == null) {

            AlertHelper.advertencia(
                    "Campo requerido",
                    "Debe seleccionar la ciudad."
            );

            cmbCiudad.requestFocus();

            return false;
        }

        if (dpFechaNacimiento.getValue() == null) {

            AlertHelper.advertencia(
                    "Campo requerido",
                    "Debe seleccionar la fecha de nacimiento."
            );

            dpFechaNacimiento.requestFocus();

            return false;
        }

        return true;
    }

    private String obtenerTipoSolicitud() {

        if (rbImplementacion.isSelected()) {
            return "Implementación";
        }

        if (rbSoporte.isSelected()) {
            return "Soporte Técnico";
        }

        return "Consultoría";
    }

    @FXML
    private void handleLimpiar(ActionEvent event) {
        limpiarFormulario();
    }

    private void limpiarFormulario() {

        txtNombres.clear();

        txtApellidos.clear();

        cmbTipoCliente.getSelectionModel().clearSelection();

        cmbCiudad.getSelectionModel().clearSelection();

        dpFechaNacimiento.setValue(null);

        rbConsultoria.setSelected(true);

        chkAsesoria.setSelected(false);

        chkInstalacion.setSelected(false);

        chkCapacitacion.setSelected(false);

        imgFotografia.setImage(null);

        rutaFoto = null;

        txtNombres.requestFocus();
    }

    @FXML
    private void handleCancelar(ActionEvent event) {

        boolean confirmar = AlertHelper.confirmar(
                "Cancelar registro",
                "¿Está seguro de cancelar el registro del cliente?"
        );

        if (confirmar) {

            Stage stage = (Stage) btnCancelar.getScene().getWindow();

            stage.close();
        }
    }

    private void cerrarVentana() {
        Stage stage = (Stage) txtNombres.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            cerrarVentana();
        }
    }


}

