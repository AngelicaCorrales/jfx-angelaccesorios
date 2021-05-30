package ui;

import java.io.IOException;
import java.time.LocalDate;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Admin;
import model.Angelaccesorios;
import model.Brand;
import model.Client;
import model.Product;
import model.Receipt;
import model.Supplier;
import model.TypeOfProduct;
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

	@FXML
	private Button btManageBrand;

	@FXML
	private Button btManageTypeProd;

	@FXML
	private Button btManageProd;

	@FXML
	private Button btManageUser;

	@FXML
	private Button btManageSupplier;

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
	private ComboBox<String> cmbxCategory;

	@FXML
	private TableView<TypeOfProduct> tvOfTypeOfProducts;

	@FXML
	private TableColumn<TypeOfProduct, String> colCategoryTypeOfProduct;

	@FXML
	private TableColumn<TypeOfProduct, String> colCodeTypeOfProduct;

	@FXML
	private TableColumn<TypeOfProduct, String> colNameTypeOfProduct;

	@FXML
	private TableColumn<TypeOfProduct, String> colStatusTypeOfProduct;

	@FXML
	private TextField txtSupplier;

	@FXML
	private Button btDeleteSupplierTP;

	@FXML
	private Button btAddSupplierTP;

	@FXML
	private Button btAddSupplierToTypeOfProduct;

	@FXML
	private TableView<TypeOfProduct> tvTypeOfProducts;

	@FXML
	private TableView<Supplier> tvAddedSuppliers;

	@FXML
	private TableColumn<Supplier, String> colNameAddedSuppliers;

	@FXML
	private TableView<Supplier> tvSuppliersInATypeOfProduct;

	@FXML
	private TableColumn<Supplier, String> colNameSuppliersInATypeOfProduct;

	@FXML
	private VBox supplierForm;

	@FXML
	private VBox typeOfProdForm;

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
	private ComboBox<String> cmbxIdType;

	@FXML
	private TableView<Client> tvListClients;

	@FXML
	private TableColumn<Client, String> colNameClient;

	@FXML
	private TableColumn<Client, String> colLastNameClient;

	@FXML
	private TableColumn<Client, String> colIdTypeClient;

	@FXML
	private TableColumn<Client, String> colIdClient;

	@FXML
	private TableColumn<Client, String> colAddressClient;

	@FXML
	private TableColumn<Client, String> colPhoneClient;

	@FXML
	private TableColumn<Client, String> colEnabledClient;

	//Products-------
	@FXML
	private TextField txtTypeOfProduct;

	@FXML
	private TextField txtBrand;

	@FXML
	private TableColumn<Product, String> colCodeProduct;

	@FXML
	private TableColumn<Product, String> colTypeProduct;

	@FXML
	private TableColumn<Product, String> colBrandProduct;

	@FXML
	private TableColumn<Product, String> colModelProduct;

	@FXML
	private Label lbManageProduct;

	@FXML
	private TableView<Product> tvOfProducts;

	@FXML
	private TableColumn<Product, String> colStatusProduct;

	@FXML
	private TableColumn<Product, Integer> colUnitsProduct;

	@FXML
	private TableColumn<Product, Double> colPriceProduct;

	@FXML
	private TableColumn<Product, String> colWarrantyProduct;

	@FXML
	private Button btSortProductPrices;

	@FXML
	private VBox createProductForm;

	@FXML
	private ComboBox<Brand> cmbxBrand;

	@FXML
	private ComboBox<TypeOfProduct> cmbxTypeOfProduct;

	@FXML
	private TextField txtModel;

	@FXML
	private TextField txtUnits;

	@FXML
	private TextField txtPrice;

	@FXML
	private RadioButton rbYes;

	@FXML
	private ToggleGroup tgWarranty;

	@FXML
	private RadioButton rbNo;


	//Reports------------
	@FXML
	private DatePicker dtPickerInitialDate;

	@FXML
	private DatePicker dtPickerFinalDate;

	@FXML
	private ComboBox<String> cmbxInitialHour;

	@FXML
	private ComboBox<String> cmbxInitialMinute;

	@FXML
	private ComboBox<String> cmbxFinalHour;

	@FXML
	private ComboBox<String> cmbxFinalMinute;

	//User--------
	@FXML
	private Label lbEmail;

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

	//Receipts-------

	@FXML
	private TextField txtCode;

	@FXML
	private Label lbWindow;

	@FXML
	private Label lbManageOrder;

	@FXML
	private VBox createSeparateReceipt;

	@FXML
	private ComboBox<Client> cmbxClients;

	@FXML
	private ComboBox<String> cbPaymentMethod;

	@FXML
	private TextField txtClientSearchedName;

	@FXML
	private TextField txtClientSearchedLastName;

	@FXML
	private TextField txtPaymentValue;

	@FXML
	private ScrollPane scrollPaneTableviews;

	@FXML
	private TableView<Receipt> tvOfCountedReceipts;

	@FXML
	private TableColumn<Receipt, String> colCodeC;

	@FXML
	private TableColumn<Receipt, String> colDateandTimeC;

	@FXML
	private TableColumn<Receipt, String> colClientC;

	@FXML
	private TableColumn<Receipt, String> colUserC;

	@FXML
	private TableColumn<Receipt, String> colProductsInCR;

	@FXML
	private TableColumn<Receipt, Double> colSubtotalPriceC;

	@FXML
	private TableColumn<Receipt, Double> colIVAC;

	@FXML
	private TableColumn<Receipt, Double> colTotalValueC;

	@FXML
	private TableColumn<Receipt, String> colPaymentMC;

	@FXML
	private TableColumn<Receipt, String> colObservationsC;

	@FXML
	private TableView<Product> tvOfAddedProducts;

	@FXML
	private TableColumn<Product, String> colNameAddedProduct;

	@FXML
	private TableView<Product> tvOfReceiptProducts;

	@FXML
	private TableColumn<Product, String> colNameRProduct;

	@FXML
	private TableView<Integer> tvOfQuantities;

	@FXML
	private TableColumn<Integer, Integer> colQuantityRProduct;

	@FXML
	private TableView<Receipt> tvOfSeparateReceipts;

	@FXML
	private TableColumn<Receipt, String> colCodeS;

	@FXML
	private TableColumn<Receipt, String> colDateandTimeS;

	@FXML
	private TableColumn<Receipt, String> colClientS;

	@FXML
	private TableColumn<Receipt, String> colUserS;

	@FXML
	private TableColumn<Receipt, String> colProductsInSR;

	@FXML
	private TableColumn<Receipt, Double> colSubtotalPriceS;

	@FXML
	private TableColumn<Receipt, Double> colIVAS;

	@FXML
	private TableColumn<Receipt, Double> colTotalValueS;

	@FXML
	private TableColumn<Receipt, String> colPayments;

	@FXML
	private TableColumn<Receipt, Double> colToPay;

	@FXML
	private TableColumn<Receipt, String> colObservationsS;

	@FXML
	private VBox addPaymentForm;

	@FXML
	private TextField txtNewPayment;

	@FXML
	private VBox addObservationsForm;

	@FXML
	private TextArea separateReceiptObs;

	@FXML
	private VBox createCountedReceipt;

	@FXML
	private TextArea txtObsevations;

	@FXML
	private VBox addProductsToAReceipt;

	@FXML
	private TextField txtNameProduct;

	@FXML
	private TextField txtQuantityProduct;

	@FXML
	private Button btDeleteProductR;

	@FXML
	private Button btAddProductR;

	@FXML
	private Button btGenerateR;

	@FXML
	private VBox receiptMenu;

	//Suppliers-----
	@FXML
	private TextField txtSupplierName;

	@FXML
	private TextField txtSupplierPhone;

	@FXML
	private TableView<Supplier> tvSuppliers;

	@FXML
	private TableColumn<Supplier, String> colNameSupplier;

	@FXML
	private TableColumn<Supplier, String> colPhoneSupplier;

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
				alert.setTitle("No se pudo iniciar sesión");
				alert.setContentText("El usuario o la contraseña son incorrectos. Intente nuevamente");

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
		initializeTableViewOfTypesOfProducts(); 
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());
		typeOfProdForm.setVisible(true);

	}

	private void initializeTableViewOfTypesOfProducts() {

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
	public void addSupplierToTypeOfProduct(ActionEvent event) {

	}

	@FXML
	public void clickOnTableViewOfAddedSuppliers(MouseEvent event) {

	}

	@FXML
	public void clickOnTableViewOfSuppliersInAProduct(MouseEvent event) {

	}


	@FXML
	public void deleteSupplierFromTypeOfProduct(ActionEvent event) {

	}


	@FXML
	public void loadAddSupplierToTypeOfProduct(ActionEvent event) {

	}

	@FXML
	public void returnToManageTypeOfProduct(ActionEvent event) {

	}

	@FXML
	public void manageSupplier(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manage-supplier.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPanel.setStyle("-fx-background-image: url(/ui/.jpg)");
		initializeTableViewOfSuppliers(); 
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());

	}

	private void initializeTableViewOfSuppliers() {

	}

	@FXML
	public void addSupplier(ActionEvent event) {

	}

	@FXML
	public void clickOnTableViewOfSuppliers(MouseEvent event) {

	}

	@FXML
	public void deleteSupplier(ActionEvent event) {

	}


	@FXML
	public void updateSupplier(ActionEvent event) {

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
		initializeTableViewOfProducts();
		//showComboBoxOfTypesOfProducts();
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());

	}

	private void initializeTableViewOfProducts() {

	}

	@FXML
	public void addProduct(ActionEvent event) {

	}

	@FXML
	public void clickOnTableViewOfProducts(MouseEvent event) {

	}

	@FXML
	public void deleteProduct(ActionEvent event) {

	}

	@FXML
	public void updateProduct(ActionEvent event) {

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
	public void manageReceipt(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manage-receipt.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPane.setStyle("-fx-background-image: url(/ui/.jpg)");

		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());
		receiptMenu.setVisible(true);
	}

	@FXML
	public void addPaymentToAReceipt(ActionEvent event) {

	}

	@FXML
	public void addProductToAReceipt(ActionEvent event) {

	}

	@FXML
	public void addReceipt(ActionEvent event) {

	}

	@FXML
	public void addSeparateReceiptObs(ActionEvent event) {

	}

	@FXML
	public void chooseProductsOfAReceipt(ActionEvent event) {
		btGenerateR.setVisible(false);
		btDelete.setVisible(false);
		createCountedReceipt.setVisible(false);
		createSeparateReceipt.setVisible(false);
		tvOfSeparateReceipts.setVisible(false);
		tvOfCountedReceipts.setVisible(false);
		addProductsToAReceipt.setVisible(true);
		tvOfAddedProducts.setVisible(true);
		tvOfReceiptProducts.setVisible(true);
		tvOfQuantities.setVisible(true);
	}

	@FXML
	public void clickOnTableViewOfAddedProducts(MouseEvent event) {

	}

	@FXML
	public void clickOnTableViewOfOrderProducts(MouseEvent event) {

	}

	@FXML
	public void clickOnTableViewOfOrders(MouseEvent event) {

	}

	@FXML
	public void deleteProductOfAReceipt(ActionEvent event) {

	}

	@FXML
	public void deleteReceipt(ActionEvent event) {

	}

	@FXML
	public void generateReceipt(ActionEvent event) {

	}

	@FXML
	public void manageCountedReceipt(ActionEvent event) {
		createCountedReceipt.setVisible(true);
		receiptMenu.setVisible(false);
		scrollPaneTableviews.setVisible(true);
		tvOfCountedReceipts.setVisible(true);
		lbWindow.setText("C");
		btGenerateR.setVisible(true);
		btGenerateR.setDisable(true);
		btDelete.setVisible(true);
		btDelete.setDisable(true);
		initializeTableViewOfCountedReceipts();
	}

	private void initializeTableViewOfCountedReceipts() {

	}

	@FXML
	public void manageSeparateReceipt(ActionEvent event) {
		createSeparateReceipt.setVisible(true);
		receiptMenu.setVisible(false);
		scrollPaneTableviews.setVisible(true);
		tvOfSeparateReceipts.setVisible(true);
		lbWindow.setText("S");
		btGenerateR.setVisible(true);
		btGenerateR.setDisable(true);
		btDelete.setVisible(true);
		btDelete.setDisable(true);
		initializeTableViewOfSeparateReceipts();
	}

	private void initializeTableViewOfSeparateReceipts() {

	}

	@FXML
	public void returnToReceiptForm(ActionEvent event) {
		addProductsToAReceipt.setVisible(false);
		tvOfAddedProducts.setVisible(false);
		tvOfReceiptProducts.setVisible(false);
		tvOfQuantities.setVisible(false);
		if(lbWindow.getText().equals("C")) {
			manageCountedReceipt(null);
		}else {
			manageSeparateReceipt(null);
		}
	}

	@FXML
	public void returnToReceiptMenu(ActionEvent event) {
		createCountedReceipt.setVisible(false);
		createSeparateReceipt.setVisible(false);
		scrollPaneTableviews.setVisible(false);
		tvOfCountedReceipts.setVisible(false);
		tvOfSeparateReceipts.setVisible(false);
		btGenerateR.setVisible(false);
		btDelete.setVisible(false);
		receiptMenu.setVisible(true);
	}

	@FXML
	public void searchClientByName(ActionEvent event) {

	}

	@FXML
	public void searchReceiptByCode(ActionEvent event) {

	}

	@FXML
	public void updateReceipt(ActionEvent event) {

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
		angelaccesorios.setLoggedUser(null);
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
		if(angelaccesorios.getLoggedUser() instanceof Admin) {

			btManageBrand.setDisable(false);
			btManageTypeProd.setDisable(false);
			btManageProd.setDisable(false);
			btManageUser.setDisable(false);
			btManageSupplier.setDisable(false);
		}


	}

	@FXML
	public void manageClient(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manage-client.fxml"));
		fxmlLoader.setController(this);

		Parent clientPane = fxmlLoader.load();

		mainPane.getChildren().clear();

		mainPane.setCenter(clientPane);

		//mainPane.setStyle("-fx-background-image: url(/ui/fondo2.jpg)");
		initializeTableViewClients();

		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());



	}

	private void initializeTableViewClients() {

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
				alert2.setContentText("No se pudo registrar el usuario, correo no válido");
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
			if(selectedUser instanceof Admin) {
				lbEmail.setVisible(true);
				txtEmail.setVisible(true);
				txtEmail.setText(((Admin)selectedUser).getEmail());
				ckbxDisable.setDisable(true);
			}
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
				txtUserName.clear();
				passwordField.clear();
				initializeTableViewUsers();
			} catch (SpaceException e) {
				alert2.setContentText("No se pudo registrar el usuario, el nombre de usuario no puede llevar espacios");
				alert2.showAndWait();
			} catch (SameIDException e) {
				alert2.setContentText("No se pudo registrar el usuario, el numero de identificación es igual al de otro usuario");
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
		alert1.setContentText("¿Esta seguro de que quiere eliminar el empleado escogido?");
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
		boolean update=true;
		if(tvListUsers.getSelectionModel().getSelectedItem() instanceof Admin && txtEmail.getText().isEmpty()) {
			update=false;
		}
		if (update && !txtName.getText().isEmpty() && !txtLastName.getText().isEmpty() && !txtId.getText().isEmpty() && !txtUserName.getText().isEmpty() && !passwordField.getText().isEmpty()) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de validacion");
			alert2.setHeaderText(null);		

			try {

				angelaccesorios.updateUser(tvListUsers.getSelectionModel().getSelectedItem(),txtId.getText() ,txtName.getText().toUpperCase(),txtLastName.getText().toUpperCase(),txtUserName.getText().toLowerCase(),passwordField.getText(), !ckbxDisable.isSelected(),txtEmail.getText());

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
				txtEmail.clear();

				txtEmail.setVisible(false);
				lbEmail.setVisible(false);
				disableButtons();
				tvListUsers.getItems().clear();

				initializeTableViewUsers();

			} catch (SameIDException e) {
				alert2.setContentText("No se pudo actualizar el usuario, el numero de identificación es igual al de otro usuario");
				alert2.showAndWait();
			} catch (SameUserNameException e) {
				alert2.setContentText("No se pudo actualizar el usuario, el nombre de usuario es igual al de otro usuario");
				alert2.showAndWait();
			} catch (SpaceException e) {
				alert2.setContentText("No se pudo actualizar el usuario, el nombre de usuario no puede llevar espacios");
				alert2.showAndWait();
			}catch (EmailException e) {
				alert2.setContentText("No se pudo actualizar el usuario, correo no válido");
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

	private void initializeComboBoxOfHours() {

	}

	private void initializeComboBoxOfMinutes() {

	}

	@FXML
	public void exportUsersReport(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exportUsersReport.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPane.setStyle("-fx-background-image: url(/ui/.jpg)");
		initializeComboBoxOfHours();
		initializeComboBoxOfMinutes();
		dtPickerInitialDate.setValue(LocalDate.now());
		dtPickerFinalDate.setValue(LocalDate.now());
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName()); 
	}


	@FXML
	public void generateUsersReport(ActionEvent event) {

	}

	@FXML
	public void exportReceiptsReport(ActionEvent event) throws IOException {
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
	public void generateReceiptsReport(ActionEvent event) {

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
	public void generateProductsReport(ActionEvent event) {

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
