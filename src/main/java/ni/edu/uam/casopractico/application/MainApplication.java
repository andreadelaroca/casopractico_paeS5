package ni.edu.uam.casopractico.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static ni.edu.uam.casopractico.util.NavigationManager.*;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/ni/edu/uam/view/LoginView.fxml"));
        Scene scene = new Scene(loader.load(), 700, 500);
        stage.setTitle("Inicio de Sesión");
        stage.setScene(scene);
        stage.show();
    }
}
