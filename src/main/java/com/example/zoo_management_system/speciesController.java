package com.example.zoo_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class speciesController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableColumn<Species, String > species;
    @FXML
    private TableColumn<Species, Integer> count;
    @FXML
    private TableColumn<Species, Integer> min;
    @FXML
    private TableColumn<Species, Integer> ave;
    @FXML
    private TableColumn<Species, Integer> max;
    private ObservableList<Species> data;
    @FXML
    private TableView<Species> table;

    public void select() {
        ArrayList<Species> animals = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("");

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        data = FXCollections.observableArrayList(animals);
        table.setItems(data);

        species.setCellValueFactory(new PropertyValueFactory<Species, String>("species"));
        count.setCellValueFactory(new PropertyValueFactory<Species, Integer>("count"));
        min.setCellValueFactory(new PropertyValueFactory<Species, Integer>("min"));
        ave.setCellValueFactory(new PropertyValueFactory<Species, Integer>("ave"));
        max.setCellValueFactory(new PropertyValueFactory<Species, Integer>("max"));

    }
    public void exit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        loader.setControllerFactory(controllerClass -> new mainController());
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
}