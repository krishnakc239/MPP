package business.controller;

import business.domain.Address;
import business.domain.LibraryMember;
import business.domain.Person;
import business.utils.FileUtils;
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
    public BorderPane borderPane;
    public Button librarian;
    TableView tableView = new TableView();
    public static final String OUTPUT_DIR = System.getProperty("user.dir")
            +"/src/dataaccess/storage/";

    TextField memberIdInput,firstNameInput, lastNamenput, mobNumInput, streetInput, cityInput, zipInput,stateInput;


    public void initialize() {
        librarian.setOnAction((ActionEvent event) -> {

            TableColumn<String, LibraryMember> memberId = new TableColumn<>("Member ID");
            memberId.setCellValueFactory(new PropertyValueFactory<>("memberid"));

            TableColumn<String, LibraryMember> firstNameColumn = new TableColumn<>("First Name");
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

            TableColumn<String, LibraryMember> lastNameColumn = new TableColumn<>("Last Name");
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

            TableColumn<String, Person> phoneNum = new TableColumn<>("Phone Number");
            phoneNum.setCellValueFactory(new PropertyValueFactory<>("phone"));


//            TableColumn<String, LibraryMember> roleColumn = new TableColumn<>("Role");
//            roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

            TableColumn<String, Address> state = new TableColumn<>("State");
            state.setCellValueFactory(new PropertyValueFactory<>("state"));


            TableColumn<String, Address> city = new TableColumn<>("City");
            city.setCellValueFactory(new PropertyValueFactory<>("city"));


            TableColumn<String, Address> zip = new TableColumn<>("Zip");
            zip.setCellValueFactory(new PropertyValueFactory<>("zip"));

            TableColumn<String, Address> street = new TableColumn<>("Street");
            street.setCellValueFactory(new PropertyValueFactory<>("street"));


            List<LibraryMember> memberList = FileUtils.getObjectFromFile(LibraryMember.class);
            ObservableList data = FXCollections.observableList(memberList);
            tableView.getColumns().setAll(memberId,firstNameColumn, lastNameColumn, phoneNum, state, city, zip, street);
            tableView.setItems(data);

            memberIdInput = new TextField();
            memberIdInput.setPromptText("Member ID");
            memberIdInput.setMinWidth(20);

            firstNameInput = new TextField();
            firstNameInput.setPromptText("First Name");
            firstNameInput.setMinWidth(20);

            lastNamenput = new TextField();
            lastNamenput.setPromptText("Last Name");
            lastNamenput.setMinWidth(20);

            mobNumInput = new TextField();
            mobNumInput.setPromptText("Mobile Number");
            mobNumInput.setMinWidth(20);

//            roleInput = new TextField();
//            roleInput.setPromptText("Role");
//            roleInput.setMinWidth(20);

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
            hBox.getChildren().addAll(memberIdInput,firstNameInput, lastNamenput, mobNumInput,stateInput,streetInput,cityInput,zipInput);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(tableView, hBox,hBox1);
            borderPane.setCenter(vBox);
        });
    }

    //Add button clicked
    public void addButtonClicked(){
        LibraryMember member = new LibraryMember(
                memberIdInput.getText(),
                new Person(firstNameInput.getText(),lastNamenput.getText(),mobNumInput.getText()),
                new Address(stateInput.getText(),cityInput.getText(),zipInput.getText(),streetInput.getText()));

        //make a list of member to write to storage file
        List<LibraryMember> memberList = new ArrayList<>();
        memberList.add(member);

        FileUtils.writeObjectToFile(memberList);

        List<LibraryMember> memList = FileUtils.getObjectFromFile(LibraryMember.class);
//        tableView.getItems().clear();
        tableView.getItems().addAll(memList);

        memberIdInput.clear();
        firstNameInput.clear();
        lastNamenput.clear();
        mobNumInput.clear();

        stateInput.clear();
        cityInput.clear();
        zipInput.clear();
        streetInput.clear();
    }

    //Delete button clicked
    public void deleteButtonClicked(){
        ObservableList<LibraryMember> memberSelected, allLibraryMembers;
        allLibraryMembers = tableView.getItems();
        memberSelected = tableView.getSelectionModel().getSelectedItems();

        memberSelected.forEach(allLibraryMembers::remove);
    }

    //check authorisation
    public static boolean authorize(String authLevel){
        if (authLevel.equalsIgnoreCase("ADMIN") ||
                authLevel.equalsIgnoreCase("LIBRARIAN")||
                authLevel.equalsIgnoreCase("BOTH")){
            return true;
        }
        return false;
    }

}
