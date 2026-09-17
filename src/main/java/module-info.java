module ni.edu.uam.casopractico {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ni.edu.uam.casopractico to javafx.fxml;
    exports ni.edu.uam.casopractico;
}