package ni.edu.uam.casopractico.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {

    private Cliente cliente;
    private String tipoSolicitud;
}
