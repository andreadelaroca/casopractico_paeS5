package ni.edu.uam.casopractico.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ni.edu.uam.casopractico.model.Cliente;

import java.time.LocalDate;
import java.util.Arrays;

public class ClienteRepository {

    private static ClienteRepository instance;
    private final ObservableList<Cliente> listaClientes;

    private ClienteRepository() {
        this.listaClientes = FXCollections.observableArrayList();
        cargarDatosPrueba(); // Carga datos pre-hechos al instanciar
    }

    // Patrón Singleton para compartir la misma lista entre vistas
    public static ClienteRepository getInstance() {
        if (instance == null) {
            instance = new ClienteRepository();
        }
        return instance;
    }

    // Datos pre-hechos (Mock Data)
    private void cargarDatosPrueba() {
        listaClientes.add(new Cliente(
                "Jorge David",
                "Morales Osejo",
                "VIP",
                "Managua",
                LocalDate.of(1990, 5, 15),
                "Consultoría",
                true,
                false,
                true,
                null
        ));

        listaClientes.add(new Cliente(
                "Juan Antonio",
                "Pérez López",
                "VIP",
                "Managua",
                LocalDate.of(1990, 5, 15),
                "Consultoría",
                true,
                false,
                true,
                null
        ));

        listaClientes.add(new Cliente(
                "Maria Alejandra",
                "Pérez Ortega",
                "VIP",
                "Managua",
                LocalDate.of(1995, 4, 15),
                "Consultoría",
                true,
                false,
                true,
                null
        ));
    }

    // Métodos para gestionar la información
    public ObservableList<Cliente> getClientes() {
        return listaClientes;
    }

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void eliminarCliente(Cliente cliente) {
        listaClientes.remove(cliente);
    }
}