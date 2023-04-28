package com.example.projetcle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class adminController {
    @FXML
    private TextField login;
    @FXML
    private TextField mdp;
    @FXML
    private ComboBox droit;

    @FXML
    protected void onAddUserClick() throws IOException {

        Stage newWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 308, 246);
        newWindow.setScene(scene);
        // Specifies the modality for new window.
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.show();
    }
}
