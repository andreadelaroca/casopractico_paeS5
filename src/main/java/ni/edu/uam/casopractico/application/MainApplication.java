package ni.edu.uam.casopractico.application;

import javafx.application.Application;
import javafx.stage.Stage;

import static ni.edu.uam.casopractico.util.NavigationManager.*;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        abrirVentana("/ni/edu/uam/view/LoginView.fxml", "Inicio de sesión");
    }
}
