package com.example.zoo_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class managerController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button sales_btn;

    @FXML
    private Button species_btn;

    public void sales(ActionEvent event) throws IOException {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("salesreport.fxml"));
            loader.setControllerFactory(controllerClass -> new salesController());
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

    }

    public void species(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("speciesreport.fxml"));
        loader.setControllerFactory(controllerClass -> new speciesController());
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void animal(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("animals.fxml"));
        loader.setControllerFactory(controllerClass -> new animalController());
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void employee(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("employees.fxml"));
        loader.setControllerFactory(controllerClass -> new employeeController());
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

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



}
