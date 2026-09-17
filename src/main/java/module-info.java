module ni.edu.uam.casopractico {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    exports ni.edu.uam.casopractico.application to javafx.graphics, javafx.base, javafx.fxml;
    opens ni.edu.uam.casopractico to javafx.fxml;
    exports ni.edu.uam.casopractico;
}