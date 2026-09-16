package ni.edu.uam.casopractico.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationManager {

    public static void abrirVentana(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    NavigationManager.class.getResource(fxml)
            );

            Scene scene = new Scene(loader.load());

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            AlertHelper.error(
                    "Error",
                    "No se pudo abrir la ventana."
            );
        }
    }
}
