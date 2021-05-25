package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exceptions.EmailException;
import exceptions.SameIDException;
import exceptions.SameUserNameException;
import exceptions.SpaceException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.Angelaccesorios;
import model.Brand;
import model.User;

public class AngelaccesoriosGUI {

	private Angelaccesorios angelaccesorios;

	@FXML
	private BorderPane mainPane;

	@FXML
	private TextField txtUserName;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Label lbUserName;

	@FXML
	private TextField txtEmail;

	//Brand------------

	@FXML
	private TextField txtBrandName;

	@FXML
	private Button btUpdate;

	@FXML
	private CheckBox ckbxDisable;

	@FXML
	private Button btDelete;

	@FXML
	private Button btAdd;

	@FXML
	private TableView<Brand> tvOfBrands;

	@FXML
	private TableColumn<Brand, String> colNameBrand;

	@FXML
	private TableColumn<Brand, String> colStateBrand;

	//Type of product -------
	@FXML
	private TextField txtTypeOfProductName;

	@FXML
	private ComboBox<?> cmbxCategory;

	@FXML
	private TableView<?> tvOfTypeOfProducts;

	@FXML
	private TableColumn<?, ?> colCategoryTypeOfProduct;

	@FXML
	private TableColumn<?, ?> colCodeTypeOfProduct;

	@FXML
	private TableColumn<?, ?> colNameTypeOfProduct;

	@FXML
	private TableColumn<?, ?> colStatusTypeOfProduct;

	//Client -----
	@FXML
	private TextField txtName;

	@FXML
	private Button btReturnToMenu;

	@FXML
	private TextField txtLastName;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtAddress;

	@FXML
	private TextField txtPhone;

	@FXML
	private ComboBox<?> cmbxIdType;

	@FXML
	private TableView<?> tvListClients;

	@FXML
	private TableColumn<?, ?> colNameClient;

	@FXML
	private TableColumn<?, ?> colLastNameClient;

	@FXML
	private TableColumn<?, ?> colIdTypeClient;

	@FXML
	private TableColumn<?, ?> colIdClient;

	@FXML
	private TableColumn<?, ?> colAddressClient;

	@FXML
	private TableColumn<?, ?> colPhoneClient;

	@FXML
	private TableColumn<?, ?> colEnabledClient;

	//Products-------
	@FXML
	private TextField txtTypeOfProduct;

	@FXML
	private TextField txtBrand;

	@FXML
	private TableColumn<?, ?> colCodeProduct;

	@FXML
	private TableColumn<?, ?> colTypeProduct;

	@FXML
	private TableColumn<?, ?> colBrandProduct;

	@FXML
	private TableColumn<?, ?> colModelProduct;

	//Reports------------
	@FXML
	private DatePicker dtPickerInitialDate;

	@FXML
	private DatePicker dtPickerFinalDate;

	@FXML
	private ComboBox<?> cmbxInitialHour;

	@FXML
	private ComboBox<?> cmbxInitialMinute;

	@FXML
	private ComboBox<?> cmbxFinalHour;

	@FXML
	private ComboBox<?> cmbxFinalMinute;

	//User--------
	@FXML
	private TableView<User> tvListUsers;

	@FXML
	private TableColumn<User, String> colNameUser;

	@FXML
	private TableColumn<User, String> colLastNameUser;

	@FXML
	private TableColumn<User, String> colIdUser;

	@FXML
	private TableColumn<User, String> colEnabledUser;

	@FXML
	private TableColumn<User, String> colUserName;

	@FXML
	private TableColumn<User, String> colPassword;


	@FXML
	public void logIn(ActionEvent event) throws IOException {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);

		if(txtUserName.getText().isEmpty() && passwordField.getText().isEmpty()) {
			showValidationErrorAlert();

		}
		else {
			boolean logIn=angelaccesorios.logInUser(txtUserName.getText(), passwordField.getText());
			if(logIn){
				returnToMenu(null);	

			}
			else {
				alert.setTitle("No se pudo iniciar sesi�n");
				alert.setContentText("El usuario o la contrase�a son incorrectos. Intente nuevamente");

				alert.showAndWait();
			}
		}

		txtUserName.clear();
		passwordField.clear();

	}

	public AngelaccesoriosGUI(Angelaccesorios ac) {
		angelaccesorios=ac;
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
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());

	}

	@FXML
	public void addTypeOfProduct(ActionEvent event) {

	}

	@FXML
	public void clickOnTableViewOfTypeOfProducts(MouseEvent event) {

	}

	@FXML
	public void deleteTypeOfProduct(ActionEvent event) {

	}

	@FXML
	public void updateTypeOfProduct(ActionEvent event) {

	}


	@FXML
	public void manageBrand(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manage-brand.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPanel.setStyle("-fx-background-image: url(/ui/.jpg)");
		initializeTableViewOfBrands();
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());

	}

	@FXML
	private void initializeTableViewOfBrands() {
		ObservableList<Brand> observableList;
		observableList = FXCollections.observableArrayList(angelaccesorios.getBrands());
		tvOfBrands.setItems(observableList);
		colNameBrand.setCellValueFactory(new PropertyValueFactory<Brand, String>("Name"));
		colStateBrand.setCellValueFactory(new PropertyValueFactory<Brand, String>("State"));
		tvOfBrands.setVisible(true);
		tvOfBrands.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}

	@FXML
	public void clickOnTableViewOfBrands(MouseEvent event) {
		Brand selectedBrand= tvOfBrands.getSelectionModel().getSelectedItem();
		if (selectedBrand!=null) {
			btDelete.setDisable(false);
			btAdd.setDisable(true);
			btUpdate.setDisable(false);
			txtBrandName.setText(selectedBrand.getName());
			ckbxDisable.setDisable(false);
			ckbxDisable.setSelected(!selectedBrand.isEnabled());
		}
	}

	@FXML
	public void addBrand(ActionEvent event) throws IOException {
		if (!txtBrandName.getText().equals("")) {
			String newBrand = txtBrandName.getText();
			boolean added = angelaccesorios.addBrand(newBrand);
			if(added==false) {
				Alert alert1 = new Alert(AlertType.ERROR);
				alert1.setTitle("Error de validacion");
				alert1.setHeaderText(null);
				alert1.setContentText("Ya existe una marca agregada con dicho nombre, intentelo nuevamente");
				alert1.showAndWait();
			}else {
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Informacion");
				alert2.setHeaderText(null);
				alert2.setContentText("La marca ha sido agregada exitosamente");
				alert2.showAndWait();
			}
			txtBrandName.clear();
			tvOfBrands.getItems().clear();
			initializeTableViewOfBrands();
		}else {
			showValidationErrorAlert();
		}
	}

	@FXML
	public void deleteBrand(ActionEvent event) throws IOException {
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
		alert1.setTitle("Confirmacion de proceso");
		alert1.setHeaderText(null);
		alert1.setContentText("�Esta seguro de que quiere eliminar esta marca?");
		Optional<ButtonType> result = alert1.showAndWait();
		if (result.get() == ButtonType.OK){
			boolean deleted = angelaccesorios.deleteBrand(tvOfBrands.getSelectionModel().getSelectedItem());
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setTitle("Informacion");
			alert2.setHeaderText(null);
			if(deleted==true) {
				alert2.setContentText("La marca ha sida eliminada exitosamente");
				alert2.showAndWait();
			}else {
				alert2.setContentText("La marca no pudo ser eliminada debido a que se encuentra relacionada con un producto");
				alert2.showAndWait();
			}
			txtBrandName.clear();
			ckbxDisable.setSelected(false);
			tvOfBrands.getItems().clear();
			initializeTableViewOfBrands();
			disableButtons();
		} 
	}

	@FXML
	public void updateBrand(ActionEvent event) throws IOException {
		if (!txtBrandName.getText().equals("")) {
			String newName = txtBrandName.getText();
			boolean enabled = true;
			if(ckbxDisable.isSelected()) {
				enabled = false;
			}
			boolean updated = angelaccesorios.updateBrand(tvOfBrands.getSelectionModel().getSelectedItem(), newName, enabled);
			if(updated==false) {
				Alert alert1 = new Alert(AlertType.ERROR);
				alert1.setTitle("Error de validacion");
				alert1.setHeaderText(null);
				alert1.setContentText("Ya existe una marca agregada con dicho nombre, intentelo nuevamente");
				alert1.showAndWait();
			}else {
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Informacion");
				alert2.setHeaderText(null);
				alert2.setContentText("La marca elegida ha sido actualizada exitosamente");
				alert2.showAndWait();
			}
			txtBrandName.clear();
			ckbxDisable.setSelected(false);
			tvOfBrands.getItems().clear();
			initializeTableViewOfBrands();
		}else {
			showValidationErrorAlert();
		}
	}

	@FXML
	public void sortListBrands(ActionEvent event) {

	}

	@FXML
	public void manageProduct(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manage-product.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPane.setStyle("-fx-background-image: url(/ui/fondo2.jpg)");
		//createProductForm.setVisible(true);
		//initializeTableViewOfProducts();
		//showComboBoxOfTypesOfProducts();
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());

	}

	@FXML
	public void listProducts(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("list-products.fxml"));
		fxmlLoader.setController(this);

		Parent clientPane = fxmlLoader.load();

		mainPane.getChildren().clear();

		mainPane.setCenter(clientPane);
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());
	}

	@FXML
	public void searchProductByTypeAndBrand(ActionEvent event) {

	}

	@FXML
	public void sortingPricesOfProducts(ActionEvent event) {

	}


	@FXML
	public void manageInvoice(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bill.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPane.setStyle("-fx-background-image: url(/ui/.jpg)");
		//initializeComboBoxOfClients();
		//initializeComboBoxEmployees();
		//initializeTableViewOfOrders();
		//createOrderForm.setVisible(true);
		//btAddProductsOrder.setDisable(true);
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());

	}

	@FXML
	public void showAboutCreators(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Angelaccesorios");
		alert.setHeaderText("Cr�ditos");
		alert.setContentText("Keren L�pez Cordoba y Ang�lica Corrales Quevedo\nEstudiantes de Ingenier�a de Sistemas\nAlgoritmos y programaci�n II, Universidad Icesi.\n2021");
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

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPane.setStyle("-fx-background-image: url(/ui/.jpg)");

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

		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());



	}

	@FXML
	public void clickOnTableViewClients(MouseEvent event) {

	}

	@FXML
	public void createClient(ActionEvent event) {

	}

	@FXML
	public void deleteClient(ActionEvent event) {

	}

	@FXML
	public void updateClient(ActionEvent event) {

	}

	public void loadRegisterAdmin() throws IOException	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register-admin.fxml"));
		fxmlLoader.setController(this);

		Parent clientPane = fxmlLoader.load();

		mainPane.getChildren().clear();

		mainPane.setCenter(clientPane);

		//mainPane.setStyle("-fx-background-image: url(/ui/fondo2.jpg)");
	}

	@FXML
	public void registerAdmin(ActionEvent event) throws IOException {
		if (!txtName.getText().isEmpty() && !txtLastName.getText().isEmpty() && !txtId.getText().isEmpty() && !txtUserName.getText().isEmpty() && !passwordField.getText().isEmpty() && !txtEmail.getText().isEmpty()) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de validacion");
			alert2.setHeaderText(null);

			try {
				angelaccesorios.createUserAdmin(txtId.getText(),txtName.getText().toUpperCase(),txtLastName.getText().toUpperCase(), txtUserName.getText().toLowerCase(),passwordField.getText(),txtEmail.getText());
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setTitle("Informacion");
				alert1.setHeaderText(null);
				alert1.setContentText("El usuario administrador ha sido registrado exitosamente!");
				alert1.showAndWait();

				txtName.clear();
				txtLastName.clear();
				txtId.clear();

				loadLogIn(null);
			} catch (EmailException ee) {
				alert2.setContentText("No se pudo registrar el usuario, correo no v�lido");
				alert2.showAndWait();

			} catch (SpaceException se) {
				alert2.setContentText("No se pudo registrar el usuario, el nombre de usuario no puede llevar espacios");
				alert2.showAndWait();
			}
		}else {
			showValidationErrorAlert();
		}


	}

	@FXML
	public void manageUser(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manage-user.fxml"));
		fxmlLoader.setController(this);

		Parent clientPane = fxmlLoader.load();

		mainPane.getChildren().clear();

		mainPane.setCenter(clientPane);

		//mainPane.setStyle("-fx-background-image: url(/ui/fondo2.jpg)");
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());
		initializeTableViewUsers(); 
	}
	
	private void initializeTableViewUsers() {
		
		ObservableList<User> observableList=FXCollections.observableArrayList();
		User u=angelaccesorios.getFirstUser();
		while(u!=null) {
			observableList.add(u);
			u=u.getNext();
		}
		
		tvListUsers.setItems(observableList);
		colNameUser.setCellValueFactory(new PropertyValueFactory<User, String>("Name"));
    	colLastNameUser.setCellValueFactory(new PropertyValueFactory<User, String>("LastName"));
    	colIdUser.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
    	colEnabledUser.setCellValueFactory(new PropertyValueFactory<User, String>("status"));
    	colUserName.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
    	colPassword.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
    	tvListUsers.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	
	
	@FXML
	public void clickOnTableViewUsers(MouseEvent event) {
		if (tvListUsers.getSelectionModel().getSelectedItem() != null) {
			enableButtons();
			User selectedUser = tvListUsers.getSelectionModel().getSelectedItem();
			txtUserName.setText(selectedUser.getUserName());
    		passwordField.setText(selectedUser.getPassword());
    		txtName.setText(selectedUser.getName());
    		txtLastName.setText(selectedUser.getLastName());
    		txtId.setText(selectedUser.getId());
   		
    		ckbxDisable.setSelected(!selectedUser.isEnabled());
		}
		
	}

	@FXML
	public void createUser(ActionEvent event) {
		if (!txtName.getText().isEmpty() && !txtLastName.getText().isEmpty() && !txtId.getText().isEmpty() && !txtUserName.getText().isEmpty() && !passwordField.getText().isEmpty()) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de validacion");
			alert2.setHeaderText(null);

			try {
				angelaccesorios.createUser(txtId.getText(),txtName.getText().toUpperCase(),txtLastName.getText().toUpperCase(), txtUserName.getText().toLowerCase(),passwordField.getText());
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setTitle("Informacion");
				alert1.setHeaderText(null);
				alert1.setContentText("El usuario ha sido creado exitosamente!");
				alert1.showAndWait();

				txtName.clear();
				txtLastName.clear();
				txtId.clear();
			} catch (SpaceException e) {
				alert2.setContentText("No se pudo registrar el usuario, el nombre de usuario no puede llevar espacios");
				alert2.showAndWait();
			} catch (SameIDException e) {
				alert2.setContentText("No se pudo registrar el usuario, el numero de identificaci�n es igual al de otro usuario");
				alert2.showAndWait();
			} catch (SameUserNameException e) {
				alert2.setContentText("No se pudo registrar el usuario, el nombre de usuario es igual al de otro usuario");
				alert2.showAndWait();
			}

		}else {
			showValidationErrorAlert();
		}

	}

	@FXML
	public void deleteUser(ActionEvent event) {
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
    	alert1.setTitle("Confirmacion de proceso");
    	alert1.setHeaderText(null);
    	alert1.setContentText("�Esta seguro de que quiere eliminar el empleado escogido?");
    	Optional<ButtonType> result = alert1.showAndWait();
    	if (result.get() == ButtonType.OK){
        	
    		boolean deleted= angelaccesorios.deleteUser(tvListUsers.getSelectionModel().getSelectedItem());
        	Alert alert2 = new Alert(AlertType.INFORMATION);
        	alert2.setTitle("Informacion");
        	alert2.setHeaderText(null);
        	
        	if(deleted) {
        		alert2.setContentText("El usuario ha sido eliminado exitosamente");
        		           	           	
            	initializeTableViewUsers();
            	
        	}else {
        		alert2.setContentText("El usuario no se pudo eliminar");
        		
        	}
        	alert2.showAndWait();
    		txtName.clear();
    		txtLastName.clear();
        	txtId.clear();
        	txtUserName.clear();
			passwordField.clear();
        	disableButtons();
        	
    	}
	}


	@FXML
	public void updateUser(ActionEvent event) {
		if (!txtName.getText().isEmpty() && !txtLastName.getText().isEmpty() && !txtId.getText().isEmpty() && !txtUserName.getText().isEmpty() && !passwordField.getText().isEmpty()) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de validacion");
			alert2.setHeaderText(null);
			try {
				angelaccesorios.updateUser(tvListUsers.getSelectionModel().getSelectedItem(),txtId.getText() ,txtName.getText().toUpperCase(),txtLastName.getText().toUpperCase(),txtUserName.getText().toLowerCase(),passwordField.getText(), !ckbxDisable.isSelected());
				
				Alert alert1 = new Alert(AlertType.INFORMATION);
        		alert1.setTitle("Informacion");
        		alert1.setHeaderText(null);
        		alert1.setContentText("El usuario ha sido actualizado exitosamente!");
        		alert1.showAndWait();
        		
        		txtName.clear();
        		txtLastName.clear();
            	txtId.clear();
            	txtUserName.clear();
    			passwordField.clear();

            	disableButtons();
            	tvListUsers.getItems().clear();

            	initializeTableViewUsers();
			
			} catch (SameIDException e) {
				alert2.setContentText("No se pudo actualizar el usuario, el numero de identificaci�n es igual al de otro usuario");
				alert2.showAndWait();
			} catch (SameUserNameException e) {
				alert2.setContentText("No se pudo actualizar el usuario, el nombre de usuario es igual al de otro usuario");
				alert2.showAndWait();
			} catch (SpaceException e) {
				alert2.setContentText("No se pudo actualizat el usuario, el nombre de usuario no puede llevar espacios");
				alert2.showAndWait();
			}
    		
    	}else {
    		showValidationErrorAlert();
    	}
	}



	@FXML
	public void importClientsData(ActionEvent event) {

	}

	@FXML
	public void importProductsData(ActionEvent event) {

	}

	@FXML
	public void exportUsersReport(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exportUsersReport.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPane.setStyle("-fx-background-image: url(/ui/.jpg)");
		//initializeComboBoxOfHours();
		//initializeComboBoxOfMinutes();
		dtPickerInitialDate.setValue(LocalDate.now());
		dtPickerFinalDate.setValue(LocalDate.now());
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName()); 
	}


	@FXML
	void generateUsersReport(ActionEvent event) {

	}

	@FXML
	public void exportInvoiceReport(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exportBillsReport.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPane.setStyle("-fx-background-image: url(/ui/fondo2.jpg)");
		//initializeComboBoxOfHours();
		//initializeComboBoxOfMinutes();
		dtPickerInitialDate.setValue(LocalDate.now());
		dtPickerFinalDate.setValue(LocalDate.now());
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());
	}

	@FXML
	void generateInvoicesReport(ActionEvent event) {

	}

	@FXML
	public void exportProductsReport(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exportProductsReport.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPane.setStyle("-fx-background-image: url(/ui/.jpg)");
		//initializeComboBoxOfHours();
		//initializeComboBoxOfMinutes();
		dtPickerInitialDate.setValue(LocalDate.now());
		dtPickerFinalDate.setValue(LocalDate.now());
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());
	}

	@FXML
	void generateProductsReport(ActionEvent event) {

	}

	public void showValidationErrorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error de validacion");
		alert.setHeaderText(null);
		alert.setContentText("Recuerde diligenciar cada uno de los campos");
		alert.showAndWait();
	}

	public void disableButtons() {
		btDelete.setDisable(true);
		btUpdate.setDisable(true);
		ckbxDisable.setDisable(true);
		btAdd.setDisable(false);
	}
	
	 public void enableButtons() {
	    	btDelete.setDisable(false);
			btUpdate.setDisable(false);
			ckbxDisable.setDisable(false);
			btAdd.setDisable(true);
	    }




	












}
