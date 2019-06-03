package application.controller;

import application.domain.Address;
import application.domain.Member;
import application.utils.FileUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SystemController {

    public Button book;
    public Button liberian;
    public Label roleTitle;
    public BorderPane borderPane;
    TableView tableView = new TableView();
    public static final String OUTPUT_DIR = System.getProperty("user.dir")
            +"/src/dataaccess/storage/";

    TextField firstNameInput, lastNamenput, mobNumInput, memberNumInput,roleInput, streetInput, cityInput, zipInput,stateInput;


    public void initialize() {
        liberian.setOnAction((ActionEvent event) -> {

            TableColumn<String, Member> firstNameColumn = new TableColumn<>("First Name");
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

            TableColumn<String, Member> lastNameColumn = new TableColumn<>("Last Name");
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

            TableColumn<String, Member> phoneNum = new TableColumn<>("Phone Number");
            phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));

            TableColumn<String, Member> memberNum = new TableColumn<>("Member Number");
            memberNum.setCellValueFactory(new PropertyValueFactory<>("memberNum"));

            TableColumn<String, Member> roleColumn = new TableColumn<>("Role");
            roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

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
            tableView.getColumns().setAll(firstNameColumn, lastNameColumn, phoneNum, memberNum, state, city, zip, street);
            tableView.setItems(data);


            firstNameInput = new TextField();
            firstNameInput.setPromptText("First Name");
            firstNameInput.setMinWidth(20);

            lastNamenput = new TextField();
            lastNamenput.setPromptText("Last Name");
            lastNamenput.setMinWidth(20);

            mobNumInput = new TextField();
            mobNumInput.setPromptText("Mobile Number");
            mobNumInput.setMinWidth(20);

            memberNumInput = new TextField();
            memberNumInput.setPromptText("Member Number");
            memberNumInput.setMinWidth(20);

            roleInput = new TextField();
            roleInput.setPromptText("Role");
            roleInput.setMinWidth(20);

            stateInput = new TextField();
            stateInput.setPromptText("State");
            stateInput.setMinWidth(20);

            streetInput = new TextField();
            streetInput.setPromptText("Street");
            streetInput.setMinWidth(20);

            cityInput = new TextField();
            cityInput.setPromptText("City");
            cityInput.setMinWidth(20);

            zipInput = new TextField();
            zipInput.setPromptText("Zip");
            zipInput.setMinWidth(20);

            //Button
            Button addButton = new Button("Add");
            addButton.setOnAction(e -> addButtonClicked());
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(e -> deleteButtonClicked());

            HBox hBox = new HBox();
            HBox hBox1 = new HBox();
            hBox1.getChildren().addAll(addButton,deleteButton);

            hBox.setPadding(new Insets(2,2,2,2));
            hBox.setSpacing(2);
            hBox.getChildren().addAll(firstNameInput, lastNamenput, mobNumInput,memberNumInput,roleInput,stateInput,streetInput,cityInput,zipInput);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(tableView, hBox,hBox1);
            borderPane.setCenter(vBox);
        });
    }

    //Add button clicked
    public void addButtonClicked(){
        Member member = new Member(firstNameInput.getText(),
                                    lastNamenput.getText(),
                mobNumInput.getText(),
                memberNumInput.getText(),
                roleInput.getText(),
                new Address(stateInput.getText(),cityInput.getText(),zipInput.getText(),streetInput.getText()));


        List<Member> memberList = new ArrayList<>();
        memberList.add(member);

        FileUtils.writeObjectToFile(memberList,OUTPUT_DIR+"member.txt");

        List<Member> memList = FileUtils.getObjectFromFile(Member.class);
//        tableView.getItems().clear();
        tableView.getItems().addAll(memList);


        firstNameInput.clear();
        lastNamenput.clear();
        mobNumInput.clear();
        memberNumInput.clear();

        stateInput.clear();
        cityInput.clear();
        zipInput.clear();
        streetInput.clear();
    }

    //Delete button clicked
    public void deleteButtonClicked(){
        ObservableList<Member> memberSelected, allMembers;
        allMembers = tableView.getItems();
        memberSelected = tableView.getSelectionModel().getSelectedItems();

        memberSelected.forEach(allMembers::remove);
    }

}
