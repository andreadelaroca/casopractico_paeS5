package ni.edu.uam.casopractico.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private String nombres;
    private String apellidos;
    private String tipoCliente;
    private String ciudad;
    private LocalDate fechaNacimiento;
    private String tipoSolicitud;
    private boolean servicioInternet;
    private boolean servicioTelefonia;
    private boolean servicioSoporte;
    private String rutaFoto;

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}
