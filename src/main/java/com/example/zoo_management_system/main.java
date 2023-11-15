package com.example.zoo_management_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        loader.setControllerFactory(controllerClass -> new mainController());
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("Zoo Management System 1");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoa_db","root","12345");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM regions");
            while(rs.next())
                System.out.println(rs.getString(1));
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }



    }
    public static void main(String[] args) {
        launch();
    }
}