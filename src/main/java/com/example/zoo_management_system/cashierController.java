package com.example.zoo_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class cashierController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ChoiceBox<String> enc_box;

    @FXML
    private RadioButton adult_btn;

    @FXML
    private RadioButton child_btn;

    @FXML
    private Label price_lbl;

    @FXML
    private Button sell_btn;

    private ToggleGroup toggleGroup;

    public void updatePrice() {

        String enclosureType = null;
        String ticketType = null;
        try {
            enclosureType = enc_box.getValue();
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            ticketType = selectedRadioButton.getText();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Missing Information Error");
            alert.setContentText("Please select an enclosure first.");
            alert.show();
        }

        float price = 0;
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db","root","12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("SELECT e.enc_price FROM enclosure e WHERE e.enc_type = '" + enclosureType + "'");
            if (rs.next())
            {
                price = rs.getFloat(1);
            }

            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        if(ticketType.equals("Child")) {
            price *= 0.8F;
        }

        price_lbl.setText("P" + price);

    }

    public void sellTicket() {
        String enclosureType = null;
        String ticketType = null;
        try {
            enclosureType = enc_box.getValue();
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            ticketType = selectedRadioButton.getText();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Missing Information Error");
            alert.setContentText("Please select an enclosure first.");
            alert.show();
        }

        float t_price = 0;
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db","root","12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("SELECT e.enc_price FROM enclosure e WHERE e.enc_type = '" + enclosureType + "'");
            if (rs.next())
            {
                t_price = rs.getFloat(1);
            }

            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        int t_enc_id = 0;

        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db","root","12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("SELECT e.enc_id FROM enclosure e WHERE e.enc_type = '" + enclosureType + "'");
            if (rs.next())
            {
                t_enc_id = rs.getInt(1);
            }

            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        if(ticketType.equals("Child")) {
            t_price *= 0.8F;
        }


        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db","root","12345");
            Statement stmt = con.createStatement();
            stmt.executeUpdate
                    ("INSERT INTO ticket(pass_type, price, p_date, enc_id) VALUES('" + enclosureType + "', '" + t_price + "', CURRENT_DATE , '" + t_enc_id + "')");


            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
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


    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        toggleGroup = new ToggleGroup();
        adult_btn.setToggleGroup(toggleGroup);
        child_btn.setToggleGroup(toggleGroup);

        ArrayList<String> enclosure = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("SELECT * FROM enclosure");
            while (rs.next())
                enclosure.add(rs.getString(2));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        for (int i = 0; i < enclosure.size(); i++)
        {
            enc_box.getItems().add(enclosure.get(i));
        }
        enc_box.getSelectionModel().selectFirst();
        updatePrice();
    }

}
