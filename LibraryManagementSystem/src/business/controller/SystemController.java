package business.controller;

import business.domain.*;
import business.utils.FileUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SystemController {

    public Button book;
    public BorderPane borderPane;
    public Button librarian;
    TableView<LibraryMember> tableView = new TableView();
    TableView<Book> bookTableView = new TableView();
    public static final String OUTPUT_DIR = System.getProperty("user.dir")
            +"/src/dataaccess/storage/";

    TextField memberIdInput,firstNameInput, lastNamenput, mobNumInput, streetInput, cityInput, zipInput,stateInput;
    TextField bookISBNInput, bookTitleInput, bookMaxCheckoutLengthInput,authorsInput,copyNumInput;
    public static List<LibraryMember> memberList = new ArrayList<>();
    public static List<Book> bookList = new ArrayList<>();

    public void initialize() {
        librarian.setOnAction((ActionEvent event) -> {

            //setup tableview
            TableColumn<LibraryMember,String> memberIdCol = new TableColumn<>("Member ID");
            memberIdCol.setCellValueFactory(new PropertyValueFactory<>("memberid"));

            TableColumn<LibraryMember,String> firstNameColumn = new TableColumn<>("First Name");
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

            TableColumn<LibraryMember,String> lastNameColumn = new TableColumn<>("Last Name");
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

            TableColumn<LibraryMember,String> phoneNumColumn = new TableColumn<>("Phone Number");
            phoneNumColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));


            TableColumn<LibraryMember,String> stateCol = new TableColumn<>("State");
            stateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress().getState()));


            TableColumn<LibraryMember,String > cityCol = new TableColumn<>("City");
            cityCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress().getCity()));

            TableColumn<LibraryMember,String > zipCol = new TableColumn<>("Zip");
            zipCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress().getZip()));

            TableColumn<LibraryMember,String > streetCol = new TableColumn<>("Street");
            streetCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress().getStreet()));



            memberList = FileUtils.getObjectFromFile(LibraryMember.class);
            ObservableList data = FXCollections.observableList(memberList);
            tableView.getColumns().setAll(memberIdCol,firstNameColumn, lastNameColumn, phoneNumColumn,stateCol,cityCol,zipCol,streetCol);

            //First clear the table's previous data if any
            tableView.getItems().clear();
            tableView.getItems().addAll(data);

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


            /** edit and save*/
            tableView.setEditable(true);
            tableView.getSelectionModel().cellSelectionEnabledProperty().set(true);

//            memberIdCol.setCellFactory(TextFieldTableCell.forTableColumn());
//            memberIdCol.setOnEditCommit(t -> {
//                LibraryMember member = t.getTableView().getItems().get(t.getTablePosition().getRow());
//                member.setMemberid(t.getNewValue());
//                System.out.println("new row ==="+t.getTableView().getItems().get(t.getTablePosition().getRow()));
//                updateDataFile(member);
//            });

            firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            firstNameColumn.setOnEditCommit(t -> {
                LibraryMember member = t.getTableView().getItems().get(t.getTablePosition().getRow());
                member.setFirstName(t.getNewValue());
                updateDataFile(member);
            });

            lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            lastNameColumn.setOnEditCommit(t -> {
                LibraryMember member = t.getTableView().getItems().get(t.getTablePosition().getRow());
                member.setLastName(t.getNewValue());
                updateDataFile(member);
            });
            phoneNumColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            phoneNumColumn.setOnEditCommit(t ->{
                LibraryMember member = t.getTableView().getItems().get(t.getTablePosition().getRow());
                member.setPhone(t.getNewValue());
                updateDataFile(member);
            });

            stateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            stateCol.setOnEditCommit(t -> {
                LibraryMember member = t.getTableView().getItems().get(t.getTablePosition().getRow());
                member.getAddress().setState(t.getNewValue());
                updateDataFile(member);
            });

            cityCol.setCellFactory(TextFieldTableCell.forTableColumn());
            cityCol.setOnEditCommit(t ->  {
                LibraryMember member = t.getTableView().getItems().get(t.getTablePosition().getRow());
                member.getAddress().setCity(t.getNewValue());
                updateDataFile(member);
            });

            zipCol.setCellFactory(TextFieldTableCell.forTableColumn());
            zipCol.setOnEditCommit(t ->  {
                LibraryMember member = t.getTableView().getItems().get(t.getTablePosition().getRow());
                member.getAddress().setZip(t.getNewValue());
                updateDataFile(member);
            });

            streetCol.setCellFactory(TextFieldTableCell.forTableColumn());
            streetCol.setOnEditCommit(t ->  {
                LibraryMember member = t.getTableView().getItems().get(t.getTablePosition().getRow());
                member.getAddress().setStreet(t.getNewValue());
                updateDataFile(member);
            });



        });

        book.setOnAction((ActionEvent event) -> {
            TableColumn<Book,String> bookISBNColumn = new TableColumn<>("ISBN");
            bookISBNColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

            TableColumn<Book,String> bookTitleColumn = new TableColumn<>("Title");
            bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

            TableColumn<Book,String> bookMaxCheckoutLengthColumn = new TableColumn<>("MaxCheckoutLength");
            bookMaxCheckoutLengthColumn.setCellValueFactory(new PropertyValueFactory<>("maxCheckoutLength"));


            TableColumn<Book,String> authorColumn = new TableColumn<>("Authors");
            authorColumn.setCellValueFactory(data -> {
                        List<Author> authorList = data.getValue().getAuthors();
                        String authorNames ="";
                        for (Author a: authorList) {
                            authorNames += a.getFirstName()+ " "+ a.getLastName()+", ";
                        }
                        return new SimpleStringProperty(authorNames);
                    }
            );

            TableColumn<Book,String> copyNumColumn = new TableColumn<>("Copy Count");
            copyNumColumn.setCellValueFactory(data -> {
                BookCopy[] bookCopyList = data.getValue().getCopies();
                int copySum =0;
                for (BookCopy bc : bookCopyList) {
                    copySum+=bc.getCopyNum();
                }
                return new SimpleStringProperty(String.valueOf(copySum));
            });



            bookISBNInput = new TextField();
            bookISBNInput.setPromptText("ISBN");
            bookISBNInput.setMinWidth(20);

            bookTitleInput = new TextField();
            bookTitleInput.setPromptText("Title");
            bookTitleInput.setMinWidth(20);

            bookMaxCheckoutLengthInput = new TextField();
            bookMaxCheckoutLengthInput.setPromptText("MaxCheckoutLengthInput");
            bookMaxCheckoutLengthInput.setMinWidth(20);

            authorsInput = new TextField();
            authorsInput.setPromptText("Authors");
            authorsInput.setMinWidth(20);

            copyNumInput = new TextField();
            copyNumInput.setPromptText("Copy Count");
            copyNumInput.setMinWidth(20);

            bookList = FileUtils.getObjectFromFile(Book.class);

            ObservableList<Book> books = FXCollections.observableList(bookList);
            bookTableView.getColumns().setAll(bookISBNColumn, bookTitleColumn, bookMaxCheckoutLengthColumn,authorColumn,copyNumColumn);
            bookTableView.getItems().clear();
            bookTableView.getItems().addAll(books);

            //Button
            Button addButton = new Button("Add");
            addButton.setOnAction(e -> addBookButtonClicked());
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(e -> deleteBookButtonClicked());

            HBox hBox = new HBox();
            HBox hBox1 = new HBox();
            hBox1.getChildren().addAll(addButton,deleteButton);

            hBox.setPadding(new Insets(2,2,2,2));
            hBox.setSpacing(2);
            hBox.getChildren().addAll(bookISBNInput, bookTitleInput, bookMaxCheckoutLengthInput,copyNumInput);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(bookTableView, hBox,hBox1);
            borderPane.setCenter(vBox);
        });
    }

    public void updateDataFile(LibraryMember member){
        /** first find the modified member in file memberList and remove previous one*/
        for (LibraryMember m: memberList){
            if (m.getMemberid().equalsIgnoreCase(member.getMemberid())){
                memberList.remove(m);
                break;
            }
        }
        /** add new member to file memberList*/
        memberList.add(member);
        FileUtils.writeObjectToFile(memberList);
    }

    //Add button clicked
    public void addButtonClicked(){
        /** new member data*/
        LibraryMember member = new LibraryMember(
                memberIdInput.getText(),
                new Person(firstNameInput.getText(),lastNamenput.getText(),mobNumInput.getText()),
                new Address(stateInput.getText(),cityInput.getText(),zipInput.getText(),streetInput.getText()));

        /**make a list of member to write to storage file */
        memberList.add(member);
        FileUtils.writeObjectToFile(memberList);

        tableView.getItems().clear();
        tableView.getItems().addAll(memberList);

        /** clear input field for new member*/
        memberIdInput.clear();
        firstNameInput.clear();
        lastNamenput.clear();
        mobNumInput.clear();

        stateInput.clear();
        cityInput.clear();
        zipInput.clear();
        streetInput.clear();
    }

    /**Delete button clicked*/
    public void deleteButtonClicked(){
        ObservableList<LibraryMember> memberSelected, allLibraryMembers;
        allLibraryMembers = tableView.getItems();
        memberSelected = tableView.getSelectionModel().getSelectedItems();

        /** remove deleted member from fileMemberList also*/
        memberList.removeAll(memberSelected);

        System.out.println(memberSelected +" is deleted from file");
        memberSelected.forEach(allLibraryMembers::remove);

        FileUtils.writeObjectToFile(memberList);
        System.out.println("New memberList written to file");

    }

    //Add button clicked
    public void addBookButtonClicked(){
        List<Author> authors = new ArrayList<>();
        Book newbook = new Book(
                bookISBNInput.getText(), bookTitleInput.getText(),
                Integer.parseInt(bookMaxCheckoutLengthInput.getText()),
                authors
        );

        //add new book data  storage file
        for (Book b: bookList) {
            if (b.getIsbn().equals(newbook.getIsbn())){

            }
        }
        for (int i=0;i< bookList.size();i++){
            if (bookList.get(i).getIsbn().equals(newbook.getIsbn())){
//                bookList.get(i).;
            }
        }
        bookList.add(newbook);
        FileUtils.writeObjectToFile(bookList);

        bookTableView.getItems().clear();
        bookTableView.getItems().addAll(bookList);

        bookISBNInput.clear();
        bookTitleInput.clear();
        bookMaxCheckoutLengthInput.clear();
    }

    //Delete button clicked
    public void deleteBookButtonClicked(){

        ObservableList<Book> bookSelected, allBooks;
        allBooks = bookTableView.getItems();
        bookSelected = bookTableView.getSelectionModel().getSelectedItems();

        bookList.removeAll(bookSelected);
        System.out.println(bookSelected +" is deleted from file");
        bookSelected.forEach(allBooks::remove);

        FileUtils.writeObjectToFile(bookList);
        System.out.println("New bookList written to file");

    }

    /**check authorisation*/
    public static boolean authorize(String authLevel){
        if (authLevel.equalsIgnoreCase("ADMIN") ||
                authLevel.equalsIgnoreCase("LIBRARIAN")||
                authLevel.equalsIgnoreCase("BOTH")){
            return true;
        }
        return false;
    }

}
