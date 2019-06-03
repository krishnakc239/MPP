package application.controller;

import application.domain.Address;
import application.domain.Member;
import application.utils.FileUtils;
import application.utils.ViewUtils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;

public class SystemController {

    public Button book;
    public Button liberian;
    public Label roleTitle;
    public BorderPane borderPane;

    public void initialize(){
        liberian.setOnAction((ActionEvent event) -> {
            TableView tableView = new TableView();
            TableColumn<String, Member> firstNameColumn = new TableColumn<>("First Name");
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

            TableColumn<String, Member> lastNameColumn = new TableColumn<>("Last Name");
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

            TableColumn<String, Member> phoneNum = new TableColumn<>("Phone Number");
            phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));

            TableColumn<String, Member> memberNum = new TableColumn<>("Member Number");
            memberNum.setCellValueFactory(new PropertyValueFactory<>("memberNum"));

            TableColumn<String, Address> state = new TableColumn<>("State");
            state.setCellValueFactory(new PropertyValueFactory<>("state"));


            TableColumn<String, Address> city = new TableColumn<>("City");
            city.setCellValueFactory(new PropertyValueFactory<>("city"));


            TableColumn<String, Address> zip = new TableColumn<>("Zip");
            zip.setCellValueFactory(new PropertyValueFactory<>("zip"));

            TableColumn<String, Address> street = new TableColumn<>("Street");
            street.setCellValueFactory(new PropertyValueFactory<>("street"));


            List<Member> memberList = FileUtils.getObjectFromFile(Member.class);
            ObservableList data = FXCollections.observableList(memberList);
            tableView.getColumns().setAll(firstNameColumn,lastNameColumn,phoneNum,memberNum,state,city,zip,street);
            tableView.setItems(data);
            borderPane.setCenter(tableView);
        });
    }
}
