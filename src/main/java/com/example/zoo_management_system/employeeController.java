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

public class employeeController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<Employee, Integer> emp;

    @FXML
    private TableColumn<Employee, String> name;

    @FXML
    private TableColumn<Employee, Double> salary;

    @FXML
    private TableColumn<Employee, Date> dob;
    @FXML
    private TableColumn<Employee, Integer> age;

    @FXML
    private TableColumn<Employee, String> job;

    @FXML
    private TableColumn<Employee, Integer> enc;
    @FXML
    private TextField fremove;
    @FXML
    private TextField fname;
    @FXML
    private TextField fsalary;
    @FXML
    private DatePicker fdate;
    @FXML
    private ChoiceBox<String> fjob;
    @FXML
    private ChoiceBox<String> fenc;

    @FXML
    private Button addbtn;

    @FXML
    private Button terminatebtn;

    @FXML
    private Button exitbtn;

    private ObservableList<Employee> data;

    @FXML
    private TableView<Employee> table;
    private int id = -1;

    public void select() {
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("SELECT * FROM employee");
            while(rs.next())
                employees.add(new Employee(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDate(4), rs.getInt(5), rs.getString(6), rs.getInt(7)));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        data = FXCollections.observableArrayList(employees);
        table.setItems(data);

        emp.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("emp_id"));
        name.setCellValueFactory(new PropertyValueFactory<Employee, String>("emp_name"));
        salary.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
        dob.setCellValueFactory(new PropertyValueFactory<Employee, Date>("dob"));
        age.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("age"));
        job.setCellValueFactory(new PropertyValueFactory<Employee, String>("job"));
        enc.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("enc_id"));


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

    public void remove(ActionEvent event) throws IOException {

        int id = Integer.parseInt(fremove.getText());
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
            Statement stmt = con.createStatement();
            stmt.executeUpdate
                    ("DELETE FROM employee WHERE emp_id = " + id);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        select();

    }

    public void add(ActionEvent event) throws IOException {


        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("SELECT * FROM enclosure WHERE enc_type = '" + fenc.getValue() + "'");
            if (rs.next())
            {
                id = rs.getInt(1);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        if (id != -1)
        {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
                Statement stmt = con.createStatement();
                stmt.executeUpdate
                        ("INSERT INTO employee\n" +
                                "VALUES(" + 0 + ",'" + fname.getText() + "'," + Double.valueOf(fsalary.getText()) + ",'" + Date.valueOf(fdate.getValue()) + "'," + (LocalDate.now().getYear() - fdate.getValue().getYear()) + ",'" + fjob.getValue() + "'," + id + ")");
                con.close();
            } catch (Exception e) {
                System.out.println(e);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Error adding Employee!");
                alert.setContentText("Please try again.");
                alert.show();
            }
            id = -1;
        }
        else
        {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
                Statement stmt = con.createStatement();
                stmt.executeUpdate
                        ("INSERT INTO employee\n" +
                                "VALUES(" + 0 + ",'" + fname.getText() + "'," + Double.valueOf(fsalary.getText()) + ",'" + Date.valueOf(fdate.getValue()) + "'," + (LocalDate.now().getYear() - fdate.getValue().getYear()) + ",'" + fjob.getValue() + "'," + null + ")");
                con.close();
            } catch (Exception e) {
                System.out.println(e);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Error adding Employee!");
                alert.setContentText("Please try again.");
                alert.show();
            }
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
            fenc.getItems().add(enclosure.get(i));
        }
        fenc.getSelectionModel().selectFirst();
        fenc.getItems().add("None");

        fjob.getItems().add("Keeper");
        fjob.getItems().add("Vet");
        fjob.getItems().add("Manager");
        fjob.getSelectionModel().selectFirst();

        select();

    }
}