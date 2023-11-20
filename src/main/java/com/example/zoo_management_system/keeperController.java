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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class keeperController implements Initializable {

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
    private TableColumn<Animal, Integer> last;

    @FXML
    private ChoiceBox<String> anim;

    @FXML
    private Label lastCleaned;

    @FXML
    private Button cleanbtn;

    @FXML
    private Button feedbtn;

    @FXML
    private Button exitbtn;

    private ObservableList<Animal> data;

    @FXML
    private TableView<Animal> table;

    public void select() {
        ArrayList<Animal> animals = new ArrayList<>();
        String enc_type = anim.getValue();
        int id = 1000;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("SELECT * FROM enclosure WHERE enc_type = '" + enc_type + "'");
            rs.next();
            id = rs.getInt(1);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("SELECT * FROM animal WHERE enc_id = " + id);
            while (rs.next()) {
                animals.add(new Animal(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6)));
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
        last.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("enc_id"));

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
            anim.getItems().add(enclosure.get(i));
        }
        anim.getSelectionModel().selectFirst();
        select();


    }
}