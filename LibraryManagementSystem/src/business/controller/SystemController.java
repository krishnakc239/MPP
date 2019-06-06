package business.controller;

import business.domain.*;
import business.utils.FileUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SystemController {

    public Button book;
    public BorderPane borderPane;
    public Button librarian;
    public Button checkout;
    TableView<Member> tableView = new TableView();
    TableView<Book> bookTableView = new TableView();
    TableView<CheckoutRecordEntry> checkoutTable = new TableView();
    public static final String OUTPUT_DIR = System.getProperty("user.dir")
            +"/src/dataaccess/storage/";

    TextField memberIdInput,firstNameInput, lastNamenput, mobNumInput, streetInput, cityInput, zipInput,stateInput;

    TextField bookISBNInput, bookTitleInput, bookMaxCheckoutLengthInput;
    public static List<Member> memberList = new ArrayList<>();

    //TextField bookISBNInput, bookTitleInput, bookMaxCheckoutLengthInput,copyNumInput;
    public ComboBox<String> authorsInput;
    TextField authorFirstNameInput, authorLastNameInput, authorPhoneInput, authorCredentialsInput, authorBioInput, authorAdrressInput;

    public static List<Book> bookList = new ArrayList<>();
    public static List<Author> authorList = new ArrayList<>();

    public void initialize() {
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
            bookMaxCheckoutLengthInput.setPromptText("Max Checkout Length");
            bookMaxCheckoutLengthInput.setMinWidth(20);

            authorsInput = new ComboBox<String>();
            authorsInput.setPromptText("Authors");
            authorsInput.setMinWidth(20);

            /*copyNumInput = new TextField();
            copyNumInput.setPromptText("Copy Count");
            copyNumInput.setMinWidth(20);*/

            bookList = FileUtils.getObjectFromFile(Book.class);

            ObservableList<Book> books = FXCollections.observableList(bookList);
            bookTableView.getColumns().setAll(bookISBNColumn, bookTitleColumn, bookMaxCheckoutLengthColumn,authorColumn,copyNumColumn);
            bookTableView.getItems().clear();
            bookTableView.getItems().addAll(books);

            //Button
            Button addButton = new Button("Add Book");
            addButton.setOnAction(e -> addBookButtonClicked());
            Button deleteButton = new Button("Delete Book");
            deleteButton.setOnAction(e -> deleteBookButtonClicked());

            HBox hBox1 = new HBox();
            hBox1.getChildren().addAll(addButton,deleteButton);

            HBox hBox = new HBox();
            hBox.setPadding(new Insets(2,2,2,2));
            hBox.setSpacing(2);
            hBox.getChildren().addAll(bookISBNInput, bookTitleInput, bookMaxCheckoutLengthInput,authorsInput);
            
            // author box
            HBox hBoxAuthor = new HBox();
            hBoxAuthor.setPadding(new Insets(2,2,2,2));
            
            authorFirstNameInput = new TextField();
            authorFirstNameInput.setPromptText("First Name");
            authorFirstNameInput.setMinWidth(15);
            
            authorLastNameInput = new TextField();
            authorLastNameInput.setPromptText("Last Name");
            authorLastNameInput.setMinWidth(15);
            
            authorPhoneInput = new TextField();
            authorPhoneInput.setPromptText("Phone");
            authorPhoneInput.setMinWidth(15);
            
            authorCredentialsInput = new TextField();
            authorCredentialsInput.setPromptText("Credentials");
            authorCredentialsInput.setMinWidth(15);
            
            authorBioInput = new TextField();
            authorBioInput.setPromptText("Short Bio");
            authorBioInput.setMinWidth(15);
            
            authorAdrressInput = new TextField();
            authorAdrressInput.setPromptText("Adress");
            authorAdrressInput.setMinWidth(15);
            
            //Button
            Button addAuthorButton = new Button("Add Author");
            addAuthorButton.setOnAction(e -> addAuthorButtonClicked());
            
            hBoxAuthor.getChildren().addAll(new Label("Author:\t"), authorFirstNameInput, authorLastNameInput, authorPhoneInput, addAuthorButton);
            
            HBox hBoxAuthor2 = new HBox();
            hBoxAuthor2.setPadding(new Insets(2,2,2,2));
            hBoxAuthor2.getChildren().addAll(new Label("\t\t"), authorCredentialsInput, authorBioInput, authorAdrressInput);

            
            // Add, Delete box
            //HBox hBox1 = new HBox();
            //hBox1.getChildren().addAll(addButton,deleteButton);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(bookTableView, hBox, hBoxAuthor, hBoxAuthor2, hBox1);
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
//            bookTitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//            bookTitleColumn.setOnEditCommit(t -> {
//                Book book = t.getTableView().getItems().get(t.getTablePosition().getRow());
//                book.setTitle(t.getNewValue());
//                updateDataFile(book);
//            });
////
////            bookMaxCheckoutLengthColumn.setCellFactory(TextFieldTableCell.forTableColumn());
////            bookMaxCheckoutLengthColumn.setOnEditCommit(t -> {
////                Book book = t.getTableView().getItems().get(t.getTablePosition().getRow());
////                book.setMaxCheckoutLength(Integer.parseInt(t.getNewValue()));
////                updateDataFile(book);
////            });
////
////            authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
////            authorColumn.setOnEditCommit(t -> {
////                Book book = t.getTableView().getItems().get(t.getTablePosition().getRow());
////                String newAuthorNames = t.getNewValue();
////                if (newAuthorNames != null || !newAuthorNames.equals("")){
////                    String[] individualName = newAuthorNames.split(",");
////                    List<Author> authorList = book.getAuthors();
////                    for (int i =0; i< individualName.length;i++){
////                        authorList.get(i).setFirstName(individualName[i].split("\\s")[0]);// set every author first name by first part of splitted author name from table
////                        authorList.get(i).setLastName(individualName[i].split("\\s")[1]);
////
////                    }
////                    book.setAuthors(authorList);
////
////                }
////                updateDataFile(book);
////            });


//            checkout.setOnAction((ActionEvent event1) -> {
//                System.out.println("checkout clicked !!!!!!");
//
////                FXMLLoader loader = new FXMLLoader();
////                loader.setLocation(getClass().getResource("../../ui/checkBook.fxml"));
//
//                //BorderPane in CenterView1.fxml
//                try {
//                    AnchorPane centerView1 = FXMLLoader.load(getClass().getResource("../../ui/checkBook.fxml"));
//                    borderPane.setCenter(centerView1);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });

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

    	//System.out.println("[begin]\n" + bookList);

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
	        List<Author> authors = new ArrayList<>();
	        authors.addAll(authorList);

        	Book newbook = new Book(
	                bookISBNInput.getText(), bookTitleInput.getText(),
	                Integer.parseInt(bookMaxCheckoutLengthInput.getText()),
	                authors
	        );

	        bookList.add(newbook);
        }

        FileUtils.writeObjectToFile(bookList);

        bookTableView.getItems().clear();
        bookTableView.getItems().addAll(bookList);

        bookISBNInput.clear();
        bookTitleInput.clear();
        bookMaxCheckoutLengthInput.clear();
        
        authorList.clear();
        /*
        authorFirstNameInput.clear();
        authorLastNameInput.clear();
        authorPhoneInput.clear();
        authorCredentialsInput.clear();
        authorBioInput.clear();
        authorAdrressInput.clear();
        authorsInput.getItems().clear();
        */
        
        //System.out.println("[end]\n" + bookList);
    }
    
    //Add button clicked
    public void addAuthorButtonClicked() {
    	
    	// create input author
    	//String firstName, String lastName, String phone, String address
    	Person person = new Person(
    			authorFirstNameInput.getText(),
    			authorLastNameInput.getText(),
    			authorPhoneInput.getText(),
    			authorAdrressInput.getText());
    	Author inputAuthor = new Author(person, authorCredentialsInput.getText(), authorBioInput.getText());
    	
    	for (Author a : authorList) {
    		if (a.equals(inputAuthor)) {
    			return;
    		}
    	}
    	
    	authorsInput.getItems().add(inputAuthor.toString());
    	
    	authorList.add(inputAuthor);
    	//System.out.println(bookList);
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

        hBox.setPadding(new Insets(10,20,20,200));
        hBox.setSpacing(20);

        hBox1.setPadding(new Insets(10,20,20,200));
        hBox1.setSpacing(20);

        Label checkInfo = new Label();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(checkInfo,hBox,hBox1,checkAvailable);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);

        checkAvailable.setOnAction(event -> {
            System.out.println(" check clicked ");
            String member_id = idInput.getText();
            String isbn = isbnInput.getText();

            Member member= FileUtils.findMemberBId(member_id);
            Book book = FileUtils.findBookById(isbn);

            if (member != null && book !=null && book.getCopiesNumber() >0){

                checkInfo.setText("Book found");
                LocalDate checkoutDate = LocalDate.now();
                LocalDate dueDate = checkoutDate.plusDays(book.getMaxCheckoutLength());
                Book book1 = new Book();
                BookCopy selectedBookCopy = book1.getBookCopyFromBook(book);
                selectedBookCopy.changeAvailability();
                CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry(checkoutDate,dueDate,selectedBookCopy);
                System.out.println("checkout record entry !!!!!!!!!!!!!!");
                System.out.println(checkoutRecordEntry);


            }else {
                checkInfo.setText("Sorry book not found with that information");
            }
        });


    }
}
