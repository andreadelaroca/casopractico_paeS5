package ni.edu.uam.casopractico.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ni.edu.uam.casopractico.model.Cliente;
import ni.edu.uam.casopractico.service.ClienteRepository;
import ni.edu.uam.casopractico.util.AlertHelper;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class ClienteConsultaController {

    @FXML
    private TextField txtBuscar;


    @FXML
    private TableColumn<Cliente, Void> colFotografia;

    @FXML
    private ComboBox<String> cmbFiltroCiudad;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnLimpiarFiltros;

    @FXML
    private TableView<Cliente> tblClientes;

    @FXML
    private TableColumn<Cliente, String> colNombreCompleto;

    @FXML
    private TableColumn<Cliente, String> colTipoCliente;

    @FXML
    private TableColumn<Cliente, String> colCiudad;

    @FXML
    private TableColumn<Cliente, LocalDate> colFechaNacimiento;

    @FXML
    private TableColumn<Cliente, String> colTipoSolicitud;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnEditar;

    private final ClienteRepository repository =
            ClienteRepository.getInstance();

    private final ObservableList<Cliente> clientes =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        configurarColumnas();
        cargarClientes();
        cargarCiudades();

        tblClientes.setFocusTraversable(true);
        tblClientes.requestFocus();
    }

    private void configurarColumnas() {

        colNombreCompleto.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getNombreCompleto()
                )
        );

        colTipoCliente.setCellValueFactory(
                new PropertyValueFactory<>("tipoCliente")
        );

        colCiudad.setCellValueFactory(
                new PropertyValueFactory<>("ciudad")
        );

        colFechaNacimiento.setCellValueFactory(
                new PropertyValueFactory<>("fechaNacimiento")
        );

        colTipoSolicitud.setCellValueFactory(
                new PropertyValueFactory<>("tipoSolicitud")
        );


        colFotografia.setCellFactory(column -> new TableCell<Cliente, Void>() {

            private final ImageView imageView = new ImageView();

            {
                imageView.setFitWidth(60);
                imageView.setFitHeight(60);
                imageView.setPreserveRatio(true);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {

                    Cliente cliente = getTableView()
                            .getItems()
                            .get(getIndex());

                    String rutaFoto = cliente.getRutaFoto();

                    if (rutaFoto != null && !rutaFoto.isEmpty()) {

                        Image image = new Image(
                                new java.io.File(rutaFoto)
                                        .toURI()
                                        .toString(),
                                60,
                                60,
                                true,
                                true
                        );

                        imageView.setImage(image);
                        setGraphic(imageView);

                    } else {
                        setGraphic(null);
                    }
                }
            }
        });


    }

    private void cargarClientes() {

        clientes.setAll(repository.getClientes());

        tblClientes.setItems(clientes);
    }

    private void cargarCiudades() {

        ObservableList<String> ciudades =
                FXCollections.observableArrayList();

        ciudades.addAll(
                repository.getClientes()
                        .stream()
                        .map(Cliente::getCiudad)
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList())
        );

        cmbFiltroCiudad.setItems(ciudades);
    }

    @FXML
    private void handleBuscar() {

        String textoBusqueda = txtBuscar.getText()
                .trim()
                .toLowerCase();

        String ciudadSeleccionada =
                cmbFiltroCiudad.getValue();

        ObservableList<Cliente> resultados =
                FXCollections.observableArrayList();

        for (Cliente cliente : repository.getClientes()) {

            boolean coincideNombre = true;
            boolean coincideCiudad = true;

            if (!textoBusqueda.isEmpty()) {

                coincideNombre =
                        cliente.getNombreCompleto()
                                .toLowerCase()
                                .contains(textoBusqueda);
            }

            if (ciudadSeleccionada != null) {

                coincideCiudad =
                        cliente.getCiudad()
                                .equals(ciudadSeleccionada);
            }

            if (coincideNombre && coincideCiudad) {

                resultados.add(cliente);
            }
        }

        tblClientes.setItems(resultados);
    }

    @FXML
    private void handleLimpiarFiltros() {

        txtBuscar.clear();

        cmbFiltroCiudad.getSelectionModel()
                .clearSelection();

        cargarClientes();
    }

    @FXML
    private void handleTablaClick(MouseEvent event) {

        if (event.getClickCount() == 2) {

            Cliente clienteSeleccionado =
                    tblClientes.getSelectionModel()
                            .getSelectedItem();

            if (clienteSeleccionado != null) {

                abrirDetalle(clienteSeleccionado);
            }
        }
    }


    @FXML
    private void handleVerDetalle() {

        Cliente seleccionado =
                tblClientes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            AlertHelper.advertencia(
                    "Cliente no seleccionado",
                    "Seleccione un cliente para ver su detalle."
            );
            return;
        }

        abrirDetalle(seleccionado);
    }

    private void abrirDetalle(Cliente cliente) {

        String servicios = "";

        if (cliente.isServicioInternet()) {
            servicios += "• Asesoría\n";
        }

        if (cliente.isServicioTelefonia()) {
            servicios += "• Instalación\n";
        }

        if (cliente.isServicioSoporte()) {
            servicios += "• Capacitación\n";
        }

        if (servicios.isEmpty()) {
            servicios = "Ninguno";
        }

        String detalle =
                "Nombre completo: " + cliente.getNombreCompleto() + "\n" +
                        "Tipo de cliente: " + cliente.getTipoCliente() + "\n" +
                        "Ciudad: " + cliente.getCiudad() + "\n" +
                        "Fecha de nacimiento: " + cliente.getFechaNacimiento() + "\n" +
                        "Tipo de solicitud: " + cliente.getTipoSolicitud() + "\n\n" +
                        "Servicios seleccionados:\n" +
                        servicios;

        AlertHelper.detalleCliente(
                "Detalle del Cliente",
                cliente.getNombreCompleto(),
                detalle
        );
    }

    @FXML
    private void handleVolver() {

        Stage stage =
                (Stage) btnVolver.getScene().getWindow();

        stage.close();
    }
    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            handleVolver();
        }
    }

}

