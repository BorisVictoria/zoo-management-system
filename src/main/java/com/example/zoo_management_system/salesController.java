package com.example.zoo_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class salesController implements Initializable {

    @FXML
    private TableView<Sales> table;
    @FXML
    private TableColumn<Sales, String> month_year;
    @FXML
    private TableColumn<Sales, String> enc;
    @FXML
    private TableColumn<Sales, Integer> adults;
    @FXML
    private TableColumn<Sales, Integer> children;
    @FXML
    private TableColumn<Sales, Integer> count;
    @FXML
    private TableColumn<Sales, Double> sales;
    private ObservableList<Sales> data;



    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Sales> salesList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_db", "root", "12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("SELECT year(t.p_date), month(t.p_date), e.enc_type, COUNT(CASE WHEN t.pass_type = 'Child' THEN 1 END), COUNT(CASE WHEN t.pass_type = 'Adult' THEN 1 END), COUNT(t.tick_id), SUM(t.price)\n" +
                            "FROM ticket t INNER JOIN enclosure e ON t.enc_id = e.enc_id\n" +
                            "GROUP BY year(t.p_date), month(t.p_date), t.enc_id\n");
            while (rs.next()) {
               salesList.add(new Sales(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getDouble(7)));
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        data = FXCollections.observableArrayList(salesList);
        table.setItems(data);

        month_year.setCellValueFactory(new PropertyValueFactory<Sales, String>("month_year"));
        enc.setCellValueFactory(new PropertyValueFactory<Sales, String>("enclosure"));
        adults.setCellValueFactory(new PropertyValueFactory<Sales, Integer>("adult"));
        children.setCellValueFactory(new PropertyValueFactory<Sales, Integer>("child"));
        count.setCellValueFactory(new PropertyValueFactory<Sales, Integer>("ticketsSold"));
        sales.setCellValueFactory(new PropertyValueFactory<Sales, Double>("totalSold"));

    }
}
