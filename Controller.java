package sample;

import javafx.scene.control.Alert;

public class Controller {
    public void alertMessage(String a, String b) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(a);
        alert.setHeaderText(null);
        alert.setContentText(b);
        alert.showAndWait();
    }
}
