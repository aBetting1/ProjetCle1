package com.example.projetcle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class usersController implements Initializable {
    @FXML
    private TextField numCle;
    @FXML
    private TextField coulCle;
    @FXML
    private TextField useCle;


    @FXML
    private ListView<Cle> afficherlist;

    final ObservableList<Cle> listItems =
            FXCollections.observableArrayList();


    public usersController() throws SQLException {




    }


    public void onAjoutCleClick() throws IOException{
        int num;
        String  coul, use;
        num = Integer.parseInt(numCle.getText());
        coul = coulCle.getText();
        use = useCle.getText();


        if(num != 0 && coul != "" && use != "") {
            Cle instance = new Cle();
            instance.Ajouter(num,coul, use);
        }
        else{
            System.out.println("Veuillez remplir tout les champs pour ajouter une clé");
        }
    }
    public void onSupCleClick() throws IOException{
        String  coul, use;
        coul = coulCle.getSelectedText().split(" - ")[0];
        use = useCle.getSelectedText().split(" - ")[1];


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String URL = "jdbc:mysql://172.19.0.3:3306/ProjetCle";
        String LOGIN = "phpmyadmin";
        String PASSWORD = "0550002D";

        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            Statement stmt = connexion.createStatement();

            ResultSet req1 = stmt.executeQuery("SELECT * FROM Cle;");

            while(req1.next()) {

                // Construit un objet clé !
                Cle laCle = new Cle();
                laCle.setNumero(req1.getInt(1));
                laCle.setCouleur(req1.getString(2));
                laCle.setOuverture(req1.getString(3));

                // J'ajoute la clé dans la liste
                listItems.add(laCle);

            }
            afficherlist.setItems(listItems);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}