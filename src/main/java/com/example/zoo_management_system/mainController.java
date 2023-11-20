package com.example.zoo_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.util.ArrayList;

public class mainController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label welcomeText;

    @FXML
    private TextField fuser;

    @FXML
    private TextField fpass;

    @FXML
    private Button btn1;

    public void initScene(ActionEvent event) throws IOException
    {
        //ArrayList<Employee> employees = new ArrayList<>();
        //employees.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDate(5),rs.getInt(6), rs.getString(7), rs.getInt(8)));
        String emp_name = "B";
        int emp_id = 0;
        boolean proceed = false;

        try {
            emp_name = fuser.getText();
            emp_id = Integer.parseInt(fpass.getText());
            proceed = true;
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Login Error");
            alert.setContentText("Please try again bitch.");
            alert.show();
        }

        boolean login = false;
        String job = null;

        if (proceed)
        {
            try
            {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db","root","12345");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery
                        ("SELECT * FROM employee WHERE emp_name = '" + emp_name + "' AND emp_id = '" + emp_id + "'");
                if (rs.next())
                {
                    job = rs.getString(6);
                    login = true;
                }

                con.close();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }

            if (login)
            {
                if (job.equals("Keeper"))
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("keeper.fxml"));
                    loader.setControllerFactory(controllerClass -> new keeperController());
                    root = loader.load();
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }

                if (job.equals("Cashier"))
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("cashier.fxml"));
                    //loader.setControllerFactory(controllerClass -> new cashierController());
                    root = loader.load();
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Incorrect Username or Password!");
                alert.setContentText("Please try again bitch.");
                alert.show();
            }
        }

    }
}