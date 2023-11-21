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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class animalController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableColumn<Animal, Integer> anim_id;
    @FXML
    private TableColumn<Animal, String> name;
    @FXML
    private TableColumn<Animal, String> type;
    @FXML
    private TableColumn<Animal, Integer> age;
    @FXML
    private TableColumn<Animal, String> diet;
    @FXML
    private TableColumn<Animal, Integer> enc;
    @FXML
    private TableColumn<Animal, String> notes;
    @FXML
    private ChoiceBox<Integer> fenc;
    @FXML
    private TextField fremove;
    @FXML
    private TextField fname;
    @FXML
    private TextField ftype;
    @FXML
    private TextField fage;
    @FXML
    private TextField fdiet;
    @FXML
    private Button exitbtn;
    @FXML
    private Button addbtn;
    @FXML
    private Button terminatebtn;
    private ObservableList<Animal> data;
    @FXML
    private TableView<Animal> table;
    private int id;

    public void select() {
        ArrayList<Animal> animals = new ArrayList<>();


        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("SELECT * FROM animal");
            while (rs.next()) {
                animals.add(new Animal(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDate(6), rs.getDate(7), rs.getString(9), rs.getInt(8)));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        data = FXCollections.observableArrayList(animals);
        table.setItems(data);

        anim_id.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("anim_id"));
        name.setCellValueFactory(new PropertyValueFactory<Animal, String>("anim_name"));
        type.setCellValueFactory(new PropertyValueFactory<Animal, String>("anim_type"));
        age.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("age"));
        diet.setCellValueFactory(new PropertyValueFactory<Animal, String>("diet"));
        enc.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("enc_id"));
        notes.setCellValueFactory(new PropertyValueFactory<Animal, String>("notes"));

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("SELECT * FROM enclosure");
            while (rs.next())
                fenc.getItems().add(rs.getInt(1));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public void add(ActionEvent event) throws IOException {


        try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
                Statement stmt = con.createStatement();
                stmt.executeUpdate
                        ("INSERT INTO animal\n" +
                                "VALUES(" + 0 + ",'" + fname.getText() + "','" + ftype.getText() + "'," + Integer.parseInt(fage.getText()) + ",'" + fdiet.getText() + "','" + Date.valueOf(LocalDate.now()) + "','" + Date.valueOf(LocalDate.now()) + "','" + fenc.getValue() + "','" + "Nothing to report" + "')");
                con.close();
        } catch (Exception e) {
                System.out.println(e);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Error adding Animal!");
                alert.setContentText("Please try again.");
                alert.show();
        }

        select();

    }

    public void remove(ActionEvent event) throws IOException {

        int id = Integer.parseInt(fremove.getText());
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
            Statement stmt = con.createStatement();
            stmt.executeUpdate
                    ("DELETE FROM animal WHERE anim_id = " + id);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        select();

    }
    public void exit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("manager.fxml"));
        loader.setControllerFactory(controllerClass -> new managerController());
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        select();

    }
}