package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

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

public class AngelaccesoriosGUI {

	private Angelaccesorios angelaccesorios;

	@FXML
	private BorderPane mainPane;

	@FXML
	private TextField txtUserName;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Label lbUserNameMenu;

	@FXML
	private Label lbUserIdMenu;

	@FXML
	private Label lbUserName;

	@FXML
	private Label lbUserId;

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


	@FXML
	public void logIn(ActionEvent event) throws IOException {
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
		//initializeTableViewOfBrands();
		//lbUserName.setText(lbUserNameMenu.getText());
		//lbUserId.setText(lbUserIdMenu.getText());  
	}

	@FXML
	private void initializeTableViewOfBrands() {
		ObservableList<Brand> observableList;
		observableList = FXCollections.observableArrayList(angelaccesorios.returnEnabledBrands());
		tvOfBrands.setItems(observableList);
		colNameBrand.setCellValueFactory(new PropertyValueFactory<Brand, String>("Name"));
		colStateBrand.setCellValueFactory(new PropertyValueFactory<Brand, String>("State"));
		tvOfBrands.setVisible(true);
		tvOfBrands.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}

	@FXML
	public void clickOnTableViewOfBrands(MouseEvent event) {
		if (tvOfBrands.getSelectionModel().getSelectedItem()!=null) {
			btDelete.setDisable(false);
			btAdd.setDisable(true);
			btUpdate.setDisable(false);
			Brand selectedBrand= tvOfBrands.getSelectionModel().getSelectedItem();
			txtBrandName.setText(selectedBrand.getName());
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
			tvOfBrands.refresh();
		}else {
			showValidationErrorAlert();
		}
	}

	@FXML
	public void deleteBrand(ActionEvent event) throws IOException {
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
		alert1.setTitle("Confirmacion de proceso");
		alert1.setHeaderText(null);
		alert1.setContentText("¿Esta seguro de que quiere eliminar esta marca?");
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
			tvOfBrands.refresh();
			disableButtons();
		} 
	}

	@FXML
	public void updateBrand(ActionEvent event) {

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
		//lbUserName.setText(lbUserNameMenu.getText());
		//lbUserId.setText(lbUserIdMenu.getText()); 
	}

	@FXML
	public void listProducts(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("list-products.fxml"));
		fxmlLoader.setController(this);

		Parent clientPane = fxmlLoader.load();

		mainPane.getChildren().clear();

		mainPane.setCenter(clientPane);
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
		//lbUserName.setText(lbUserNameMenu.getText());
		//lbUserId.setText(lbUserIdMenu.getText()); 
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
		loadLogIn(null);
	}

	@FXML
	public void manageUser(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manage-user.fxml"));
		fxmlLoader.setController(this);

		Parent clientPane = fxmlLoader.load();

		mainPane.getChildren().clear();

		mainPane.setCenter(clientPane);

		//mainPane.setStyle("-fx-background-image: url(/ui/fondo2.jpg)");
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
		//lbUserName.setText(lbUserNameMenu.getText());
		//lbUserId.setText(lbUserIdMenu.getText()); 
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
		//lbUserName.setText(lbUserNameMenu.getText());
		//lbUserId.setText(lbUserIdMenu.getText()); 
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
		//lbUserName.setText(lbUserNameMenu.getText());
		//lbUserId.setText(lbUserIdMenu.getText()); 
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













}
