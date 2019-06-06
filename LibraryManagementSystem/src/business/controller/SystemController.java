package business.controller;

import business.domain.*;
import business.utils.FileUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SystemController {

    public Button book;
    public BorderPane borderPane;
    public Button librarian;
    public Button checkout;
    public Button logout;
    public Label role;
    TableView<Member> tableView = new TableView();
    TableView<Book> bookTableView = new TableView();
    TableView checkoutTable = new TableView();


    TextField memberIdInput,firstNameInput, lastNamenput, mobNumInput, streetInput, cityInput, zipInput,stateInput;
    TextField bookISBNInput, bookTitleInput, bookMaxCheckoutLengthInput;
    TextField afirstnameInput,alastNameInput,aphoneInput,astateInput,acityInput,azipInput,astreeetInput;
    public static List<Member> memberList = new ArrayList<>();
    public static List<Book> bookList = new ArrayList<>();

    SystemController systemController;
    public void setController(SystemController systemController){
        this.systemController = systemController;
        systemController.role.setText(User.userSessionRole);

//        checkout.setVisible(false);
    }
    public void initialize() {
        checkoutTable.getColumns().addAll(memIdCol,bookIsbnCol,checkoutDateCol,dueDateCol,overdueCol);

        librarian.setOnAction((ActionEvent event) -> {

            //setup tableview
            TableColumn<Member,String> memberIdCol = new TableColumn<>("Member ID");
            memberIdCol.setCellValueFactory(new PropertyValueFactory<>("memberid"));

            TableColumn<Member,String> firstNameColumn = new TableColumn<>("First Name");
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

            TableColumn<Member,String> lastNameColumn = new TableColumn<>("Last Name");
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

            TableColumn<Member,String> phoneNumColumn = new TableColumn<>("Phone Number");
            phoneNumColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));


            TableColumn<Member,String> stateCol = new TableColumn<>("State");
            stateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress().getState()));


            TableColumn<Member,String > cityCol = new TableColumn<>("City");
            cityCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress().getCity()));

            TableColumn<Member,String > zipCol = new TableColumn<>("Zip");
            zipCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress().getZip()));

            TableColumn<Member,String > streetCol = new TableColumn<>("Street");
            streetCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress().getStreet()));



            memberList = FileUtils.getObjectFromFile(Member.class);
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

            TextField searchField = new TextField();
            searchField.setPromptText("memeber id");
            Label searchlvl = new Label();
            Button searchButton = new Button("Search");

            HBox searchHbox = new HBox();
            searchHbox.getChildren().addAll(searchButton,searchField,searchlvl);
            searchHbox.setSpacing(2);
            searchHbox.setPadding(new Insets(2,2,2,2));
            searchButton.setStyle("-fx-text-fill: #1E33A9");


            HBox hBox = new HBox();
            HBox hBox1 = new HBox();
            hBox1.getChildren().addAll(addButton,deleteButton);

            hBox.setPadding(new Insets(2,2,2,2));
            hBox.setSpacing(2);
            hBox.getChildren().addAll(memberIdInput,firstNameInput, lastNamenput, mobNumInput,stateInput,streetInput,cityInput,zipInput);


            VBox vBox = new VBox();
//            vBox.getChildren().addAll(tableView, hBox,hBox1);
            if (User.userSessionRole.equals(Role.ADMIN)){
                vBox.getChildren().addAll(tableView, hBox,hBox1,searchHbox);
                systemController.checkout.setVisible(false);
            }else if ((User.userSessionRole.equals(Role.LIBRARIAN))){
                systemController.checkout.setVisible(true);
                vBox.getChildren().addAll(tableView,searchHbox);
            }
            borderPane.setCenter(vBox);


            /** edit and save*/
            tableView.setEditable(true);
            tableView.getSelectionModel().cellSelectionEnabledProperty().set(true);

//            memberIdCol.setCellFactory(TextFieldTableCell.forTableColumn());
//            memberIdCol.setOnEditCommit(t -> {
//                Member member = t.getTableView().getItems().get(t.getTablePosition().getRow());
//                member.setMemberid(t.getNewValue());
//                System.out.println("new row ==="+t.getTableView().getItems().get(t.getTablePosition().getRow()));
//                updateDataFile(member);
//            });

            firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            firstNameColumn.setOnEditCommit(t -> {
                Member member = t.getTableView().getItems().get(t.getTablePosition().getRow());
                member.setFirstName(t.getNewValue());
                updateDataFile(member);
            });

            lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            lastNameColumn.setOnEditCommit(t -> {
                Member member = t.getTableView().getItems().get(t.getTablePosition().getRow());
                member.setLastName(t.getNewValue());
                updateDataFile(member);
            });
            phoneNumColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            phoneNumColumn.setOnEditCommit(t ->{
                Member member = t.getTableView().getItems().get(t.getTablePosition().getRow());
                member.setPhone(t.getNewValue());
                updateDataFile(member);
            });

            stateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            stateCol.setOnEditCommit(t -> {
                Member member = t.getTableView().getItems().get(t.getTablePosition().getRow());
                member.getAddress().setState(t.getNewValue());
                updateDataFile(member);
            });

            cityCol.setCellFactory(TextFieldTableCell.forTableColumn());
            cityCol.setOnEditCommit(t ->  {
                Member member = t.getTableView().getItems().get(t.getTablePosition().getRow());
                member.getAddress().setCity(t.getNewValue());
                updateDataFile(member);
            });

            zipCol.setCellFactory(TextFieldTableCell.forTableColumn());
            zipCol.setOnEditCommit(t ->  {
                Member member = t.getTableView().getItems().get(t.getTablePosition().getRow());
                member.getAddress().setZip(t.getNewValue());
                updateDataFile(member);
            });

            streetCol.setCellFactory(TextFieldTableCell.forTableColumn());
            streetCol.setOnEditCommit(t ->  {
                Member member = t.getTableView().getItems().get(t.getTablePosition().getRow());
                member.getAddress().setStreet(t.getNewValue());
                updateDataFile(member);
            });

            searchButton.setOnAction(e -> {
                String mbr_id = searchField.getText();
                Member m = FileUtils.findMemberBId(mbr_id);
                if (m != null){
                    List<Member> members = new ArrayList<>();
                    members.add(m);
                    ObservableList<Member> memer_data = FXCollections.observableList(members);
                    tableView.getItems().clear();
                    tableView.getItems().addAll(memer_data);

                }else {
                    searchlvl.setText("Sorry record not found");
                    searchlvl.setTextFill(Paint.valueOf("#8B0000"));
                }
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
                            authorNames += "[" + a.getFirstName()+ ", "+ a.getLastName()+", " + a.getPhone() + "]";
                        }
                        return new SimpleStringProperty(authorNames);
                    }
            );

            TableColumn<Book,String> copyNumColumn = new TableColumn<>("Copy Count");
            copyNumColumn.setCellValueFactory(data -> {
                int copyNum = data.getValue().getCopiesNumber();
                List<Integer> copyNums = data.getValue().getCopyNums();
                return new SimpleStringProperty(String.valueOf(copyNum) + ":" + copyNums.toString());
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

            TextField searchField = new TextField();
            searchField.setPromptText("book isbn");
            Label searchlvl = new Label();
            Button searchButton = new Button("Search");
            HBox searchHbox = new HBox();
            searchHbox.getChildren().addAll(searchButton,searchField,searchlvl);
            searchHbox.setSpacing(2);
            searchHbox.setPadding(new Insets(2,2,2,2));
            searchButton.setStyle("-fx-text-fill: #1E33A9");


            /*copyNumInput = new TextField();
            copyNumInput.setPromptText("Copy Count");
            copyNumInput.setMinWidth(20);*/

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


            HBox hBox1 = new HBox();
            hBox1.getChildren().addAll(addButton,deleteButton);

            HBox hBox = new HBox();
            hBox.setPadding(new Insets(2,2,2,2));
            hBox.setSpacing(2);
            hBox.getChildren().addAll(bookISBNInput, bookTitleInput, bookMaxCheckoutLengthInput);

            HBox hBox2 = new HBox();
            afirstnameInput = new TextField();
            afirstnameInput.setPromptText("Author FirstName");
            afirstnameInput.setMinWidth(20);

            alastNameInput = new TextField();
            alastNameInput.setPromptText("Author LastName");
            alastNameInput.setMinWidth(20);

            aphoneInput = new TextField();
            aphoneInput.setPromptText("Author Phone");
            aphoneInput.setMinWidth(20);

            astateInput = new TextField();
            astateInput.setPromptText("Author State");
            astateInput.setMinWidth(20);

            acityInput = new TextField();
            acityInput.setPromptText("Author City");
            acityInput.setMinWidth(20);

            azipInput = new TextField();
            azipInput.setPromptText("Author Zip");
            azipInput.setMinWidth(20);

            astreeetInput = new TextField();
            astreeetInput.setPromptText("Author Street");
            astreeetInput.setMinWidth(20);

            hBox2.getChildren().addAll(afirstnameInput,alastNameInput,aphoneInput,astateInput,acityInput,azipInput,astreeetInput);
            hBox2.setPadding(new Insets(2,2,2,2));
            hBox2.setSpacing(2);

            VBox vBox = new VBox();
            if (User.userSessionRole.equals(Role.ADMIN)){
                vBox.getChildren().addAll(bookTableView, hBox,hBox2,hBox1,searchHbox);
            }else {
                vBox.getChildren().addAll(bookTableView,searchHbox);
            }
            borderPane.setCenter(vBox);

//            /** edit and save*/
//            bookTableView.setEditable(true);
//            bookTableView.getSelectionModel().cellSelectionEnabledProperty().set(true);
//
//
//            bookISBNColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//            bookISBNColumn.setOnEditCommit(t -> {
//                Book book = t.getTableView().getItems().get(t.getTablePosition().getRow());
//                book.setIsbn(t.getNewValue());
//                updateDataFile(book);
//            });
//
//
            bookTitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            bookTitleColumn.setOnEditCommit(t -> {
                Book book = t.getTableView().getItems().get(t.getTablePosition().getRow());
                book.setTitle(t.getNewValue());
                updateDataFile(book);
            });

            searchButton.setOnAction(e -> {
                String isbn = searchField.getText();
                Book b = FileUtils.findBookById(isbn);
                if (b != null){
                    List<Book> bookList = new ArrayList<>();
                    bookList.add(b);
                    ObservableList<Book> book_data = FXCollections.observableList(bookList);
                    bookTableView.getItems().clear();
                    bookTableView.getItems().addAll(book_data);

                }else {
                    searchlvl.setText("Sorry record not found");
                    searchlvl.setTextFill(Paint.valueOf("#8B0000"));
                }
            });

        });


    }

    public void updateDataFile(Object obj){
        /** first find the modified member in file memberList and remove previous one*/
        if (obj instanceof Member){
            Member newMember = (Member) obj;
            for (Member m: memberList){
                if (m.getMemberid().equalsIgnoreCase(newMember.getMemberid())){
                    memberList.remove(m);
                    break;
                }
             }
            memberList.add(newMember);
            FileUtils.writeObjectToFile(memberList);

        }else if (obj instanceof Book){
            Book newBook = (Book) obj;
            for (Book b: bookList){
                if (b.getIsbn().equalsIgnoreCase(newBook.getIsbn())){
                    memberList.remove(book);
                    break;
                }
            }
            bookList.add(newBook);
            FileUtils.writeObjectToFile(bookList);
        }
        /** add new member to file memberList*/

    }

    //Add button clicked
    public void addButtonClicked(){
        /** new member data*/
        Member member = new Member(
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
        ObservableList<Member> memberSelected, allMembers;
        allMembers = tableView.getItems();
        memberSelected = tableView.getSelectionModel().getSelectedItems();

        /** remove deleted member from fileMemberList also*/
        memberList.removeAll(memberSelected);

        System.out.println(memberSelected +" is deleted from file");
        memberSelected.forEach(allMembers::remove);

        FileUtils.writeObjectToFile(memberList);
        System.out.println("New memberList written to file");

    }

    //Add button clicked
    public void addBookButtonClicked(){

    	// check isbn for existing book
    	// if yes, increase the book copy numbers
    	boolean isExistingBook = false;
        for (int i=0; i< bookList.size(); i++ ) {
        	Book book = bookList.get(i);
            if (book.getIsbn().equals(bookISBNInput.getText())) {
            	book.addCopy();
            	isExistingBook = true;
            }
        }

    	// new book
        if (!isExistingBook) {
	        Book newbook = new Book(
	                bookISBNInput.getText(), bookTitleInput.getText(),
	                Integer.parseInt(bookMaxCheckoutLengthInput.getText()),
                    Arrays.asList(
                            new Author(
                                    new Person(afirstnameInput.getText(),alastNameInput.getText(),aphoneInput.getText()),
                                    "credentials1",
                            new Address(astateInput.getText(),acityInput.getText(),azipInput.getText(),astreeetInput.getText()))));

	        bookList.add(newbook);
        }

        afirstnameInput.clear();
        alastNameInput.clear();
        aphoneInput.clear();
        astateInput.clear();
        astreeetInput.clear();
        acityInput.clear();
        azipInput.clear();
        astreeetInput.clear();

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
        if (authLevel.equalsIgnoreCase(Role.ADMIN) ||
                authLevel.equalsIgnoreCase(Role.LIBRARIAN)||
                authLevel.equalsIgnoreCase(Role.BOTH)){
            return true;
        }
        return false;
    }


    TableColumn<Member,String> memIdCol = new TableColumn<>("Member ID");
    TableColumn<Member,String> bookIsbnCol = new TableColumn<>("ISBN");
    TableColumn<Member,String> checkoutDateCol = new TableColumn<>("Checkout Date");
    TableColumn<Member,String> dueDateCol = new TableColumn<>("Due Date");
    TableColumn<Member,String> overdueCol = new TableColumn<>("Overdue");

    public void check(ActionEvent actionEvent) {

        Label memberIdLbl = new Label("Member ID");
        Label isbnLbl = new Label("Book ISBN");
        isbnLbl.setPadding(new Insets(0,0,0,5));
        Button checkAvailable = new Button("Check");

        TextField idInput = new TextField();
        TextField isbnInput = new TextField();

        HBox hBox = new HBox();
        hBox.getChildren().addAll(memberIdLbl,idInput);

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(isbnLbl,isbnInput);

        hBox.setPadding(new Insets(10,20,20,300));
        hBox.setSpacing(20);

        hBox1.setPadding(new Insets(10,20,20,300));
        hBox1.setSpacing(20);

        Label checkInfo = new Label();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(checkInfo,hBox,hBox1,checkAvailable);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);

        List<Member> membersWithCheckOuts = new ArrayList<>();

        checkAvailable.setOnAction(event -> {
            String member_id = idInput.getText();
            String isbn = isbnInput.getText();

            Member member= FileUtils.findMemberBId(member_id);
            Book book = FileUtils.findBookById(isbn);


            if (member != null && book !=null && book.getCopiesNumber() >0){


                System.out.println("record found with member id:"+member_id + " and isbn :"+ isbn);
                checkInfo.setText("Book found");
                LocalDate checkoutDate = LocalDate.now();
                LocalDate dueDate = checkoutDate.plusDays(book.getMaxCheckoutLength());
                Book book1 = new Book();
                BookCopy selectedBookCopy = book1.getBookCopyFromBook(book);
                System.out.println("boo copy  num:"+ book.getCopiesNumber());
                book.reduceCopy(selectedBookCopy);

                //update the storage member file memeber object with this checout
                List<Book> tempBookList = new ArrayList<>();
                tempBookList.addAll(bookList);
                for (Book b:tempBookList) {
                    if (b.getIsbn().equals(book.getIsbn())){
                        bookList.remove(b);
                    }
                }
                bookList.add(book);
                FileUtils.writeObjectToFile(bookList);

//                book.reduceCopy(book);
                System.out.println("boo checked and copy num:"+ book.getCopiesNumber());
                CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry(checkoutDate,dueDate,selectedBookCopy);
                member.getCheckoutRecord().setCheckoutRecordEntry(checkoutRecordEntry);//set checkout record for respective member
                System.out.println("checkout record entry !!!!!!!!!!!!!!");
                System.out.println(checkoutRecordEntry);

                //update the storage member file memeber object with this checout
                List<Member> tempMemberList = new ArrayList<>();
                tempMemberList.addAll(memberList);
                for (Member m:tempMemberList) {
                    if (m.getMemberid().equals(member.getMemberid())){
                        memberList.remove(m);
                    }
                }
                memberList.add(member);
                membersWithCheckOuts.add(member);
                FileUtils.writeObjectToFile(memberList);

                memIdCol.setCellValueFactory(new PropertyValueFactory<>("memberid"));
                bookIsbnCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCheckoutRecord().getCheckoutRecordEntry().getBookCopy().getBook().getIsbn()));

                checkoutDateCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getCheckoutRecord().getCheckoutRecordEntry().getCheckoutDate())));
                dueDateCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getCheckoutRecord().getCheckoutRecordEntry().getDueDate())));

                overdueCol.setCellValueFactory(data -> {
                    LocalDate due_date = data.getValue().getCheckoutRecord().getCheckoutRecordEntry().getCheckoutDate();
                    if (LocalDate.now().compareTo(due_date)>0){
                        return new SimpleStringProperty("Over due and Fine required");
                    }
                    return new SimpleStringProperty("");
                });
//                memberList = FileUtils.getObjectFromFile(Member.class);
                ObservableList data = FXCollections.observableList(membersWithCheckOuts);
                System.out.println("Checkout member data ..........");
                for (Member m: membersWithCheckOuts) {
                    System.out.println(m);
                }
                checkoutTable.getItems().addAll(data);
                borderPane.setCenter(checkoutTable);

            }else {
                checkInfo.setText("Sorry no record not found with that information");
                checkInfo.setTextFill(Paint.valueOf("#8B0000"));
            }
        });


    }


    public void logout(ActionEvent actionEvent) {
        User.userSessionRole = "";//remove the user session id
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../ui/MainScreen.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
//                        Parent root = new ViewUtils().getRoot("../../ui/dashboard.fxml");
        Stage currentStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        currentStage.setScene(scene);

    }
}
