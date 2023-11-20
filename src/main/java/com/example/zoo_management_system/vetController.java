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
import javafx.scene.text.Text;

public class vetController implements Initializable {

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
    private TableColumn<Animal, Date> last;

    @FXML
    private ChoiceBox<String> anim;

    @FXML
    private ChoiceBox<String> enc;

    @FXML
    private Label prevnotes;

    @FXML
    private TextField newnotes;

    @FXML
    private Button updatebtn;

    @FXML
    private Button exitbtn;

    private ObservableList<Animal> data;

    @FXML
    private TableView<Animal> table;

    private int id;

    public void select() {
        ArrayList<Animal> animals = new ArrayList<>();
        String enc_type = enc.getValue();

        String previous = null;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("SELECT * FROM enclosure WHERE enc_type = '" + enc_type + "'");
            if (rs.next())
            {
                id = rs.getInt(1);
            }

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
                animals.add(new Animal(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDate(6), rs.getDate(7), rs.getString(8), rs.getInt(9)));
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
        last.setCellValueFactory(new PropertyValueFactory<Animal, Date>("vetted"));

        ArrayList<String> enclosureAnimals = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("SELECT * FROM animal WHERE enc_id = " + id);
            int ctr = 0;
            while (rs.next())
            {
                enclosureAnimals.add(rs.getString(2));
                if (ctr == 0)
                {
                    previous = String.valueOf(rs.getString(8));
                    prevnotes.setText(previous);
                    ctr++;
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        anim.getItems().clear();

        for (int i = 0; i < enclosureAnimals.size(); i++)
        {
            anim.getItems().add(enclosureAnimals.get(i));
        }
        anim.getSelectionModel().selectFirst();


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
    public void update(ActionEvent event) throws IOException {

        int anim_id = 0;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("SELECT * FROM animal WHERE anim_name = '" + anim.getValue() + "'");
            if (rs.next())
            {
                anim_id = rs.getInt(1);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        Date now = Date.valueOf(LocalDate.now());
        String newnotesText = newnotes.getText();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
            Statement stmt = con.createStatement();
            stmt.executeUpdate
                    ("UPDATE animal SET notes = '" + newnotesText + "' WHERE anim_id = " + anim_id);
            stmt.executeUpdate
                    ("UPDATE animal SET vetted = '" + now + "' WHERE anim_id = " + anim_id);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        select();

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
            enc.getItems().add(enclosure.get(i));
        }
        enc.getSelectionModel().selectFirst();

    }
}