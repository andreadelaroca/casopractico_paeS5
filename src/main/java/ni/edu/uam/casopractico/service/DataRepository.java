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
                "Juan Antonio", "Pérez López", "VIP", "Managua",
                LocalDate.of(1990, 5, 15), "Consultoría",
                Arrays.asList("Asesoría Técnica", "Capacitación"), null
        ));

        listaClientes.add(new Cliente(
                "Maria Elena", "García Torres", "Frecuente", "León",
                LocalDate.of(1985, 8, 22), "Implementación",
                Arrays.asList("Instalación"), null
        ));

        listaClientes.add(new Cliente(
                "Carlos Alberto", "Mendoza Ruiz", "Regular", "Granada",
                LocalDate.of(1995, 12, 3), "Soporte Técnico",
                Arrays.asList("Asesoría Técnica", "Instalación"), null
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