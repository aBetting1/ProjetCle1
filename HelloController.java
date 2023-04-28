package com.example.projetcle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    @FXML
    private TextField monPseudo;
    @FXML
    private PasswordField monMDP;
    @FXML
    protected void onConnexionClick() throws IOException, SQLException {
        String pseudo, mdp;
        pseudo = monPseudo.getText();
        mdp = monMDP.getText();

        String URL = "jdbc:mysql://172.19.0.3:3306/ProjetCle";
        String LOGIN = "phpmyadmin";
        String PASSWORD = "0550002D";

        Connection connexion = DriverManager.getConnection(URL, LOGIN, PASSWORD);

        String req = "SELECT Pseudo, Mdp, Droit FROM Utilisateur;";
        Statement stmt = connexion.createStatement();
        ResultSet resultat = stmt.executeQuery(req);

        Hasher hasher = Hashing.sha256().newHasher();

        hasher.putString(mdp, Charsets.UTF_8);
        HashCode sha256 = hasher.hash();

        stmt = connexion.createStatement();

        ResultSet req1 = stmt.executeQuery("SELECT count(*) AS co FROM Utilisateur WHERE login = '"+ pseudo+"' AND Mdp = '"+sha256+"';");

        while(resultat.next()) {
            if() {

            }
            if (pseudo.equals(resultat.getString("Pseudo")) && sha257.toString().equals(resultat.getString("Mdp")))) {
                if (resultat.getString("Droit").equals("admin")){
                    Stage newWindow = new Stage();
                    FXMLLoader fxmlLoader = new
                            FXMLLoader(HelloApplication.class.getResource("admin-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 477, 364);
                    newWindow.setScene(scene);
                    // Specifies the modality for new window.
                    newWindow.initModality(Modality.APPLICATION_MODAL);
                    newWindow.show();
                }
                else{
                    Stage newWindow = new Stage();
                    FXMLLoader fxmlLoader = new
                            FXMLLoader(HelloApplication.class.getResource("users_view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 416, 362);
                    newWindow.setScene(scene);
                    // Specifies the modality for new window.
                    newWindow.initModality(Modality.APPLICATION_MODAL);
                    newWindow.show();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur de connexion");
                alert.setHeaderText("Connexion");
                alert.setContentText("utilisateur non reconnu. Réessayez !");
                alert.showAndWait();
            }

        }
    }
}