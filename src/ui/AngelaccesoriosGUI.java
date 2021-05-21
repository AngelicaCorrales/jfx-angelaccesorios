package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.Angelaccesorios;

public class AngelaccesoriosGUI {
	
	private Angelaccesorios angelaccesorios;
	
	@FXML
	private BorderPane mainPane;
	
	@FXML
    private Label lbObjectId;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label lbUserNameMenu;

    @FXML
    private Label lbUserIdMenu;


    @FXML
    void logIn(ActionEvent event) throws IOException {
    	showMenu();
    }

	public AngelaccesoriosGUI(Angelaccesorios ac) {
		angelaccesorios=ac;
	}
	
	public void showMenu() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPane.setStyle("-fx-background-image: url(/ui/.jpg)");
	}
	
	@FXML
    public void manageTypeOfProduct(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manage-type-of-product.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPanel.setStyle("-fx-background-image: url(/ui/.jpg)");
		//initializeTableViewOfTypesOfProducts(); 
		//lbUserName.setText(lbUserNameMenu.getText());
    	//lbUserId.setText(lbUserIdMenu.getText());  
    }
	
	@FXML
    public void manageBrand(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manage-brand.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPanel.setStyle("-fx-background-image: url(/ui/.jpg)");
		//initializeTableViewOfBrands();
		//lbUserName.setText(lbUserNameMenu.getText());
    	//lbUserId.setText(lbUserIdMenu.getText());  
    }

	@FXML
	void manageProduct(ActionEvent event) {

	}

	@FXML
	void manageInvoice(ActionEvent event) {

	}

	@FXML
    public void showAboutCreators(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Angelaccesorios");
	    alert.setHeaderText("Créditos");
	    alert.setContentText("Keren López Cordoba y Angélica Corrales Quevedo\nEstudiantes de Ingeniería de Sistemas\nAlgoritmos y programación II, Universidad Icesi.\n2021");
	    alert.showAndWait();
    }
	
	@FXML
	public void loadLogIn(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userLogin.fxml"));

		fxmlLoader.setController(this);
		Parent login= fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(login);
		//mainPane.setStyle("-fx-background-image: url(/ui/.jpg)");

	}
	
	@FXML
    public void signOutOfSystem(ActionEvent event) throws IOException {
		loadLogIn(null);

    }
	
	@FXML
	public void returnToMenu(ActionEvent event) throws IOException {
		//lbObjectId.setText("");
		showMenu(); 
		//lbUserNameMenu.setText(lbUserName.getText());
		//lbUserIdMenu.setText(lbUserId.getText());
	}
	
	@FXML
    public void manageClient(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manage-client.fxml"));
    	fxmlLoader.setController(this);

    	Parent clientPane = fxmlLoader.load();

    	mainPane.getChildren().clear();

    	mainPane.setCenter(clientPane);

    	//mainPane.setStyle("-fx-background-image: url(/ui/fondo2.jpg)");
    	//initializeTableViewClients();

    	//lbUserName.setText(lbUserNameMenu.getText());

    	//lbUserId.setText(lbUserIdMenu.getText());  	

    }
	
	  
    @FXML
    void manageUser(ActionEvent event) {

    }
    
    @FXML
    void listProducts(ActionEvent event) {

    }
    
    @FXML
    void importClientsData(ActionEvent event) {

    }

    @FXML
    void importProductsData(ActionEvent event) {

    }
    
    @FXML
    void exportEmployeesReport(ActionEvent event) {

    }

    @FXML
    void exportOrdersReport(ActionEvent event) {

    }

    @FXML
    void exportProductsReport(ActionEvent event) {

    }

   
    
}
