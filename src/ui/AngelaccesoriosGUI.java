package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JFileChooser;

import com.itextpdf.text.DocumentException;

import exceptions.EmailException;
import exceptions.HigherDateAndHour;
import exceptions.ExcessQuantityException;
import exceptions.NegativePriceException;
import exceptions.NegativeQuantityException;
import exceptions.NoPriceException;
import exceptions.NoProductsAddedException;
import exceptions.NoQuantityException;
import exceptions.SameIDException;
import exceptions.SameProductException;
import exceptions.SameUserNameException;
import exceptions.SpaceException;
import exceptions.UnderAgeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.ListView;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Admin;
import model.Angelaccesorios;
import model.Brand;
import model.Client;
import model.ElectronicEquipment;
import model.Product;
import model.Receipt;
import model.SeparateReceipt;
import model.Supplier;
import model.TypeOfProduct;
import model.User;
import thread.ClockThread;

public class AngelaccesoriosGUI {

	private Angelaccesorios angelaccesorios;
	
	@FXML
	private BorderPane mainPane;
	
	@FXML
    private Label lbClock;

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
	private ComboBox<Client> cmbxClientsSR;

	@FXML
	private ComboBox<String> cbPaymentMethodSR;

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
	private ListView<Integer> lvOfQuantities;


	@FXML
	private TableView<SeparateReceipt> tvOfSeparateReceipts;

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
	
	@FXML
	private Button btAddSR;
	
	@FXML
	private VBox vBoxListViewQ;


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
	private Stage window;
	private boolean runClock;
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
		runClock=true;
	}
	public void setStage(Stage st) {
		window=st;
	}
	
	public Label getLbClock() {
		return lbClock;
	}
	
	public boolean getRunClock() {
		return runClock;
	}
	
	public void initialize() {
		ClockThread clock=new ClockThread(this);
		clock.start();
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
					
					@Override
					public void handle(WindowEvent event) {
						runClock = false;
					}
				});
		
	}


	@FXML
	public void manageTypeOfProduct(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manage-type-of-product.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		
		mainPane.setCenter(menuPane);
		//mainPanel.setStyle("-fx-background-image: url(/ui/.jpg)");
		initializeTableViewOfTypesOfProducts(); 
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());
		typeOfProdForm.setVisible(true);
		tvTypeOfProducts.setVisible(true);
		initializeCmbxOfCategory();
	}
	
	private void initializeCmbxOfCategory() {
		ObservableList<String> categoryList = FXCollections.observableArrayList("Accesorio","Equipo electronico");
		cmbxCategory.setItems(categoryList);
		cmbxCategory.setValue(null);
		cmbxCategory.setPromptText("Elija una categoria");
	}

	private void initializeTableViewOfTypesOfProducts() {
		ObservableList<TypeOfProduct> observableList=FXCollections.observableArrayList();
		if(angelaccesorios.getTypePRoot()!=null) {
			listTypesPInorder(observableList, angelaccesorios.getTypePRoot(), angelaccesorios.getTypePRoot().getParent());	
		}
		tvTypeOfProducts.setItems(observableList);
		colCodeTypeOfProduct.setCellValueFactory(new PropertyValueFactory<TypeOfProduct, String>("Code"));
		colStatusTypeOfProduct.setCellValueFactory(new PropertyValueFactory<TypeOfProduct, String>("State"));
		colNameTypeOfProduct.setCellValueFactory(new PropertyValueFactory<TypeOfProduct, String>("Name"));
		tvTypeOfProducts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	private void listTypesPInorder(ObservableList<TypeOfProduct> list, TypeOfProduct current, TypeOfProduct parent) {
		if(current!=null) {
			if(current.getLeft()!=parent) {
				listTypesPInorder(list, current.getLeft(), current);
			}
			list.add(current);
			if(current.getRight()!=parent) {
				listTypesPInorder(list, current.getRight(), current);
			}
		}else {
			return;
		}
	}

	@FXML
	public void addTypeOfProduct(ActionEvent event) throws IOException {
		if (!txtTypeOfProductName.getText().equals("") && cmbxCategory.getValue()!=null) {
			String newType = txtTypeOfProductName.getText();
			String category = cmbxCategory.getValue().toString();
			boolean added = angelaccesorios.addTypeOfProduct(newType, category);
			if(added==false) {
				Alert alert1 = new Alert(AlertType.ERROR);
				alert1.setTitle("Error de validacion");
				alert1.setHeaderText(null);
				alert1.setContentText("Ya existe un tipo de producto agregado con dicho nombre, intentelo nuevamente");
				alert1.showAndWait();
			}else {
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Informacion");
				alert2.setHeaderText(null);
				alert2.setContentText("El tipo de producto ha sido agregado exitosamente");
				alert2.showAndWait();
			}
			txtTypeOfProductName.clear();
			initializeCmbxOfCategory();
			cmbxCategory.setDisable(false);
			tvTypeOfProducts.getItems().clear();
			initializeTableViewOfTypesOfProducts();
		}else {
			showValidationErrorAlert();
		}
	}

	@FXML
	public void clickOnTableViewOfTypeOfProducts(MouseEvent event) {
		TypeOfProduct selectedType= tvTypeOfProducts.getSelectionModel().getSelectedItem();
		if (selectedType!=null) {
			enableButtons();
			txtTypeOfProductName.setText(selectedType.getName());
			if(selectedType instanceof ElectronicEquipment) {
				cmbxCategory.setValue("Equipo electronico");
				cmbxCategory.setDisable(true);
				btAddSupplierToTypeOfProduct.setDisable(false);
			}else {
				cmbxCategory.setValue("Accesorio");
				cmbxCategory.setDisable(true);
			}
			ckbxDisable.setSelected(!selectedType.isEnabled());
		}
	}

	@FXML
	public void deleteTypeOfProduct(ActionEvent event) throws IOException {
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
		alert1.setTitle("Confirmacion de proceso");
		alert1.setHeaderText(null);
		alert1.setContentText("¿Esta seguro de que quiere eliminar este tipo de producto?");
		Optional<ButtonType> result = alert1.showAndWait();
		if (result.get() == ButtonType.OK){
			boolean deleted = angelaccesorios.deleteTypeOfProduct(tvTypeOfProducts.getSelectionModel().getSelectedItem());
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setTitle("Informacion");
			alert2.setHeaderText(null);
			if(deleted==true) {
				alert2.setContentText("El tipo de producto seleccionado ha sido eliminado exitosamente");
				alert2.showAndWait();
			}else {
				alert2.setContentText("El tipo de producto seleccionado no pudo ser eliminado debido a que se encuentra relacionado con un producto");
				alert2.showAndWait();
			}
			txtTypeOfProductName.clear();
			initializeCmbxOfCategory();
			cmbxCategory.setDisable(false);
			tvTypeOfProducts.getItems().clear();
			initializeTableViewOfTypesOfProducts();
			disableButtons();
			btAddSupplierToTypeOfProduct.setDisable(true);
		}
	}

	@FXML
	public void updateTypeOfProduct(ActionEvent event) throws IOException {
		if (!txtTypeOfProductName.getText().equals("")) {
			String newName = txtTypeOfProductName.getText();
			boolean enabled = true;
    		if(ckbxDisable.isSelected()) {
    			enabled = false;
    		} 
			boolean updated = angelaccesorios.updateTypeOfProduct(tvTypeOfProducts.getSelectionModel().getSelectedItem(), newName, enabled);
			if(updated==false) {
				Alert alert1 = new Alert(AlertType.ERROR);
				alert1.setTitle("Error de validacion");
				alert1.setHeaderText(null);
				alert1.setContentText("Ya existe un tipo de producto agregado con dicho nombre, intentelo nuevamente");
				alert1.showAndWait();
			}else {
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Informacion");
				alert2.setHeaderText(null);
				alert2.setContentText("El tipo de producto elegido ha sido actualizado exitosamente");
				alert2.showAndWait();
			}
			txtTypeOfProductName.clear();
			initializeCmbxOfCategory();
			cmbxCategory.setDisable(false);
			tvTypeOfProducts.getItems().clear();
			initializeTableViewOfTypesOfProducts();
			ckbxDisable.setSelected(false);
			disableButtons();
			btAddSupplierToTypeOfProduct.setDisable(true);
		}else {
			showValidationErrorAlert();
		}
	}


	@FXML
	public void addSupplierToTypeOfProduct(ActionEvent event) throws IOException {
		ElectronicEquipment tp =  ((ElectronicEquipment) tvTypeOfProducts.getSelectionModel().getSelectedItem());
    	Supplier selectedSupplier= tvAddedSuppliers.getSelectionModel().getSelectedItem();
    	if(selectedSupplier!=null) {
    		boolean added = angelaccesorios.addSupplierToEQE(tp, selectedSupplier);
    		if(added==false) {
    			Alert alert1 = new Alert(AlertType.ERROR);
    			alert1.setTitle("Error de validacion");
    			alert1.setHeaderText(null);
    			alert1.setContentText("El proveedor seleccionado ya se encuentra agregado en la lista de proveedores del tipo de producto, intentelo nuevamente");
    			alert1.showAndWait();
    		}else {
    			Alert alert2 = new Alert(AlertType.INFORMATION);
        		alert2.setTitle("Informacion");
        		alert2.setHeaderText(null);
        		alert2.setContentText("El proveedor ha sido agregado exitosamente a la lista de proveedores del tipo producto");
        		alert2.showAndWait();
    		}
    		txtSupplier.clear();
    		initializeTableViewOfSuppliersInAProduct();
    	}
	}
	
	private void initializeTableViewOfAddedSuppliers() {
		ObservableList<Supplier> observableList=FXCollections.observableArrayList();
		if(angelaccesorios.getSupplierRoot()!=null) {
			listSuppliersInorder(observableList, angelaccesorios.getSupplierRoot(), angelaccesorios.getSupplierRoot().getParent());
			
		}
		tvAddedSuppliers.setItems(observableList);
		colNameAddedSuppliers.setCellValueFactory(new PropertyValueFactory<Supplier, String>("Name"));
		tvAddedSuppliers.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}

	@FXML
	public void clickOnTableViewOfAddedSuppliers(MouseEvent event) {
		Supplier selectedSupplier= tvAddedSuppliers.getSelectionModel().getSelectedItem();
		if (selectedSupplier!=null) {
			btAddSupplierTP.setDisable(false);
			btDeleteSupplierTP.setDisable(true);
    		txtSupplier.setText(selectedSupplier.getName());
    	}
	}
	
	private void initializeTableViewOfSuppliersInAProduct() {
    	ObservableList<Supplier> observableList;
    	if(!((ElectronicEquipment) tvTypeOfProducts.getSelectionModel().getSelectedItem()).getSuppliers().isEmpty()) {
    		observableList = FXCollections.observableArrayList(((ElectronicEquipment) tvTypeOfProducts.getSelectionModel().getSelectedItem()).getSuppliers());
    		tvSuppliersInATypeOfProduct.setItems(observableList);
    		colNameSuppliersInATypeOfProduct.setCellValueFactory(new PropertyValueFactory<Supplier, String>("Name"));
    		tvSuppliersInATypeOfProduct.setVisible(true);
    		tvSuppliersInATypeOfProduct.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	}
	}

	@FXML
	public void clickOnTableViewOfSuppliersInAProduct(MouseEvent event) {
		Supplier selectedSupplier= tvSuppliersInATypeOfProduct.getSelectionModel().getSelectedItem();
		if (selectedSupplier!=null) {
			btAddSupplierTP.setDisable(true);
			btDeleteSupplierTP.setDisable(false);
    		txtSupplier.setText(selectedSupplier.getName());
    	}
	}


	@FXML
	public void deleteSupplierFromTypeOfProduct(ActionEvent event) throws IOException {
		ElectronicEquipment tp =  ((ElectronicEquipment) tvTypeOfProducts.getSelectionModel().getSelectedItem());
    	Supplier selectedSupplier = tvSuppliersInATypeOfProduct.getSelectionModel().getSelectedItem();
    	Alert alert1 = new Alert(AlertType.CONFIRMATION);
    	alert1.setTitle("Confirmacion de proceso");
    	alert1.setHeaderText(null);
    	alert1.setContentText("¿Esta seguro de que quiere eliminar este proveedor del tipo de producto seleccionado?");
    	Optional<ButtonType> result = alert1.showAndWait();
    	if (result.get() == ButtonType.OK && selectedSupplier !=null){
    		angelaccesorios.deleteSupplierOfAnEQE(tp, selectedSupplier);;
    		Alert alert2 = new Alert(AlertType.INFORMATION);
    		alert2.setTitle("Informacion");
    		alert2.setHeaderText(null);
    		alert2.setContentText("El proveedor ha sido eliminado exitosamente de la lista de proveedores del tipo de producto seleccionado");
    		alert2.showAndWait();
    	}
    	txtSupplier.clear();
    	tvSuppliersInATypeOfProduct.getItems().clear();
    	initializeTableViewOfSuppliersInAProduct();
    	btDeleteSupplierTP.setDisable(true);
    	btAddSupplierTP.setDisable(false);
	}


	@FXML
	public void loadAddSupplierToTypeOfProduct(ActionEvent event) {
		tvTypeOfProducts.setVisible(false);
		tvAddedSuppliers.setVisible(true);
		tvSuppliersInATypeOfProduct.setVisible(true);
		typeOfProdForm.setVisible(false);
		supplierForm.setVisible(true);
		initializeTableViewOfAddedSuppliers();
		initializeTableViewOfSuppliersInAProduct();
		txtSupplier.setEditable(false);
	}

	@FXML
	public void returnToManageTypeOfProduct(ActionEvent event) throws IOException {
		manageTypeOfProduct(null);
	}

	@FXML
	public void manageSupplier(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manage-supplier.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		
		mainPane.setCenter(menuPane);
		//mainPanel.setStyle("-fx-background-image: url(/ui/.jpg)");
		initializeTableViewOfSuppliers(); 
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());

	}

	private void initializeTableViewOfSuppliers() {
		ObservableList<Supplier> observableList=FXCollections.observableArrayList();
		if(angelaccesorios.getSupplierRoot()!=null) {
			listSuppliersInorder(observableList, angelaccesorios.getSupplierRoot(), angelaccesorios.getSupplierRoot().getParent());
			
		}
		tvSuppliers.setItems(observableList);
		colNameSupplier.setCellValueFactory(new PropertyValueFactory<Supplier, String>("Name"));
		colPhoneSupplier.setCellValueFactory(new PropertyValueFactory<Supplier, String>("PhoneNumber"));
		tvSuppliers.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	private void listSuppliersInorder(ObservableList<Supplier> list, Supplier current, Supplier parent) {
		if(current!=null) {
			if(current.getLeft()!=parent) {
				listSuppliersInorder(list, current.getLeft(), current);
			}
			list.add(current);
			if(current.getRight()!=parent) {
				listSuppliersInorder(list, current.getRight(), current);
			}
		}else {
			return;
		}
	}

	@FXML
	public void addSupplier(ActionEvent event) throws IOException {
		if (!txtSupplierName.getText().equals("") && !txtSupplierPhone.getText().equals("")) {
			String newSupplier = txtSupplierName.getText();
			String phone = txtSupplierPhone.getText();
			boolean added = angelaccesorios.addSupplier(newSupplier, phone);
			if(added==false) {
				Alert alert1 = new Alert(AlertType.ERROR);
				alert1.setTitle("Error de validacion");
				alert1.setHeaderText(null);
				alert1.setContentText("Ya existe un proveedor agregado con dicho nombre, intentelo nuevamente");
				alert1.showAndWait();
			}else {
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Informacion");
				alert2.setHeaderText(null);
				alert2.setContentText("El proveedor ha sido agregado exitosamente");
				alert2.showAndWait();
			}
			txtSupplierName.clear();
			txtSupplierPhone.clear();
			initializeTableViewOfSuppliers();
		}else {
			showValidationErrorAlert();
		}
	}

	@FXML
	public void clickOnTableViewOfSuppliers(MouseEvent event) {
		Supplier selectedSupplier= tvSuppliers.getSelectionModel().getSelectedItem();
		if (selectedSupplier!=null) {
			btDelete.setDisable(false);
			btUpdate.setDisable(false);
			btAdd.setDisable(true);
			txtSupplierName.setText(selectedSupplier.getName());
			txtSupplierPhone.setText(selectedSupplier.getPhoneNumber());
		}
	}

	@FXML
	public void deleteSupplier(ActionEvent event) throws IOException {
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
		alert1.setTitle("Confirmacion de proceso");
		alert1.setHeaderText(null);
		alert1.setContentText("¿Esta seguro de que quiere eliminar este proveedor?");
		Optional<ButtonType> result = alert1.showAndWait();
		if (result.get() == ButtonType.OK){
			boolean deleted = angelaccesorios.deleteSupplier(tvSuppliers.getSelectionModel().getSelectedItem());
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setTitle("Informacion");
			alert2.setHeaderText(null);
			if(deleted==true) {
				alert2.setContentText("El proveedor seleccionado ha sido eliminado exitosamente");
				alert2.showAndWait();
			}else {
				alert2.setContentText("El proveedor seleccionado no pudo ser eliminado debido a que se encuentra relacionado con un producto");
				alert2.showAndWait();
			}
			txtSupplierName.clear();
			txtSupplierPhone.clear();
			tvSuppliers.getItems().clear();
			initializeTableViewOfSuppliers();
			btDelete.setDisable(true);
			btUpdate.setDisable(true);
			btAdd.setDisable(false);
		} 
	}


	@FXML
	public void updateSupplier(ActionEvent event) throws IOException {
		if (!txtSupplierName.getText().equals("") && !txtSupplierPhone.getText().equals("")) {
			String newName = txtSupplierName.getText();
			String newPhone = txtSupplierPhone.getText();
			boolean updated = angelaccesorios.updateSupplier(tvSuppliers.getSelectionModel().getSelectedItem(), newName, newPhone);
			if(updated==false) {
				Alert alert1 = new Alert(AlertType.ERROR);
				alert1.setTitle("Error de validacion");
				alert1.setHeaderText(null);
				alert1.setContentText("Ya existe un proveedor agregado con dicho nombre, intentelo nuevamente");
				alert1.showAndWait();
			}else {
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Informacion");
				alert2.setHeaderText(null);
				alert2.setContentText("El proveedor elegido ha sido actualizado exitosamente");
				alert2.showAndWait();
			}
			txtSupplierName.clear();
			txtSupplierPhone.clear();
			tvSuppliers.getItems().clear();
			initializeTableViewOfSuppliers();
		}else {
			showValidationErrorAlert();
		}
	}



	@FXML
	public void manageBrand(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manage-brand.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		
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
			enableButtons();
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
		ObservableList<Brand> observableList;
    	observableList = FXCollections.observableArrayList(angelaccesorios.sortingBrandNames());
    	tvOfBrands.setItems(observableList);
	}

	@FXML
	public void manageProduct(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manage-product.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		
		mainPane.setCenter(menuPane);
		//mainPane.setStyle("-fx-background-image: url(/ui/fondo2.jpg)");
		//createProductForm.setVisible(true);
		initializeTableViewOfProducts(angelaccesorios.getProducts());
		//showComboBoxOfTypesOfProducts();
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());
		initializeCmbxTypeOfProduct();
		initializeCmbxBrand(); 
	}

	private void initializeTableViewOfProducts(ArrayList<Product> products) {
		ObservableList<Product> observableList;
    	observableList = FXCollections.observableArrayList(products);
    	tvOfProducts.setItems(observableList);
    	colCodeProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("Code"));
    	colTypeProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("TypeName"));
    	colBrandProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("BrandName"));
    	colModelProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("Model"));
    	colStatusProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("State"));
    	colUnitsProduct.setCellValueFactory(new PropertyValueFactory<Product, Integer>("Units"));
    	colPriceProduct.setCellValueFactory(new PropertyValueFactory<Product, Double>("Price"));
    	colWarrantyProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("Guarantee"));
    	tvOfProducts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	if(!angelaccesorios.getProducts().isEmpty()) {
    		btSortProductPrices.setDisable(false);
    	}
	}
	
	private void initializeCmbxTypeOfProduct() {
		ObservableList<TypeOfProduct> typeList = FXCollections.observableArrayList();
		if(angelaccesorios.getTypePRoot()!=null) {
			listTypesPInorder(typeList, angelaccesorios.getTypePRoot(), angelaccesorios.getTypePRoot().getParent());	
		}
		cmbxTypeOfProduct.setItems(typeList);
		cmbxTypeOfProduct.setValue(null);
		cmbxTypeOfProduct.setPromptText("Elija un tipo de producto");
	}
	
	private void initializeCmbxBrand() {
		ObservableList<Brand> brandList = FXCollections.observableArrayList(angelaccesorios.getBrands());
		cmbxBrand.setItems(brandList);
		cmbxBrand.setValue(null);
		cmbxBrand.setPromptText("Elija una marca");
	}

	@FXML
	public void addProduct(ActionEvent event) throws IOException {
		if (cmbxTypeOfProduct.getValue()!=null && cmbxBrand.getValue()!=null && !txtModel.getText().equals("") && !txtUnits.getText().equals("") && !txtPrice.getText().equals("") && (rbYes.isSelected()||rbNo.isSelected())) {
    		TypeOfProduct tp = cmbxTypeOfProduct.getValue();
    		Brand b = cmbxBrand.getValue();
    		String model = txtModel.getText();
    		double price = 0;
    		int units = 0;
    		boolean guarantee = true;
    		if(rbNo.isSelected()) {
    			guarantee = false;
    		}
    		boolean stop = false;
    		Alert alert1 = new Alert(AlertType.ERROR);
			alert1.setTitle("Error de validacion");
			alert1.setHeaderText(null);
			try {
				units = Integer.parseInt(txtUnits.getText());
			}catch(NumberFormatException n) {
				alert1.setContentText("Debe ingresar un valor numerico que represente las unidades del producto disponibles");
				alert1.showAndWait();
				stop = true;
			}
			try {
				price = Double.parseDouble(txtPrice.getText());
			}catch(NumberFormatException n) {
				alert1.setContentText("Debe ingresar un valor numerico que represente el precio del producto");
				alert1.showAndWait();
				stop = true;
			}
			if(stop==false) {
				try {
					angelaccesorios.addProduct(tp, b, model, units, price, guarantee);
					Alert alert2 = new Alert(AlertType.INFORMATION);
	        		alert2.setTitle("Informacion");
	        		alert2.setHeaderText(null);
	        		alert2.setContentText("El producto ha sido creado exitosamente");
	        		alert2.showAndWait();
				}catch(NoQuantityException nq) {
					alert1.setContentText(nq.getMessage());
	    			alert1.showAndWait();
				}catch(NegativeQuantityException ng) {
					alert1.setContentText(ng.getMessage());
	    			alert1.showAndWait();
				}catch(NoPriceException p) {
					alert1.setContentText(p.getMessage());
	    			alert1.showAndWait();
				}catch(NegativePriceException np) {
					alert1.setContentText(np.getMessage());
	    			alert1.showAndWait();
				}catch(SameProductException s) {
					alert1.setContentText(s.getMessage());
	    			alert1.showAndWait();
				}	
			}
			initializeCmbxTypeOfProduct();
    		initializeCmbxBrand();
    		txtModel.clear();
    		txtUnits.clear();
    		txtPrice.clear();
    		rbYes.setSelected(false);
    		rbNo.setSelected(false);
    		ckbxDisable.setSelected(false);
    		initializeTableViewOfProducts(angelaccesorios.getProducts());
    	}else {
    		showValidationErrorAlert();
    	}
	}
	
	@FXML
	public void clickOnTableViewOfProducts(MouseEvent event) {
		Product selectedProduct = tvOfProducts.getSelectionModel().getSelectedItem();
		if (selectedProduct!= null) {
    		enableButtons();
    		txtModel.setText(selectedProduct.getModel());
    		txtUnits.setText(""+selectedProduct.getUnits());
    		txtPrice.setText(""+selectedProduct.getPrice());
    		cmbxBrand.setValue(selectedProduct.getBrand());
    		cmbxTypeOfProduct.setValue(selectedProduct.getType());
    		cmbxTypeOfProduct.setDisable(true);
    		ckbxDisable.setSelected(!selectedProduct.isEnabled());
    		if(selectedProduct.hasGuarantee()) {
    			rbYes.setSelected(true);
    		}else {
    			rbNo.setSelected(true);	
    		}
    	}
	}

	@FXML
	public void deleteProduct(ActionEvent event) throws IOException {
		Product selectedProduct = tvOfProducts.getSelectionModel().getSelectedItem();
    	Alert alert1 = new Alert(AlertType.CONFIRMATION);
    	alert1.setTitle("Confirmacion de proceso");
    	alert1.setHeaderText(null);
    	alert1.setContentText("¿Esta seguro de que quiere eliminar este producto?");
    	Optional<ButtonType> result = alert1.showAndWait();
    	if (result.get() == ButtonType.OK && selectedProduct!=null){
        	boolean deleted = angelaccesorios.deleteProduct(selectedProduct);
        	Alert alert2 = new Alert(AlertType.INFORMATION);
    		alert2.setTitle("Informacion");
    		alert2.setHeaderText(null);
        	if(deleted==true) {
        		alert2.setContentText("El producto ha sido eliminado exitosamente");
        		alert2.showAndWait();
        	}else {
        		alert2.setContentText("El producto no pudo ser eliminado debido a que se encuentra dentro de una factura del sistema de separado con estado no entregado");
        		alert2.showAndWait();
        	}
        	initializeCmbxTypeOfProduct();
    		initializeCmbxBrand();
    		txtModel.clear();
    		txtUnits.clear();
    		txtPrice.clear();
    		rbYes.setSelected(false);
    		rbNo.setSelected(false);
    		ckbxDisable.setSelected(false);
    		tvOfProducts.getItems().clear();
    		initializeTableViewOfProducts(angelaccesorios.getProducts());
        	disableButtons();
    	} 
	}

	@FXML
	public void updateProduct(ActionEvent event) throws IOException {
		Product selectedProduct = tvOfProducts.getSelectionModel().getSelectedItem();
    	if (cmbxBrand.getValue()!=null && !txtModel.getText().equals("") && !txtUnits.getText().equals("") && !txtPrice.getText().equals("") && (rbYes.isSelected()||rbNo.isSelected())) {
    		Brand newBrand = cmbxBrand.getValue();
    		String newModel = txtModel.getText();
    		double newPrice = 0;
    		int newUnits = 0;
    		boolean guarantee = true;
    		boolean enabled = true;
    		if(ckbxDisable.isSelected()) {
    			enabled = false;
    		}
    		if(rbNo.isSelected()) {
    			guarantee = false;
    		}
    		boolean stop = false;
    		Alert alert1 = new Alert(AlertType.ERROR);
			alert1.setTitle("Error de validacion");
			alert1.setHeaderText(null);
			try {
				newUnits = Integer.parseInt(txtUnits.getText());
			}catch(NumberFormatException n) {
				alert1.setContentText("Debe ingresar un valor numerico que represente las unidades del producto disponibles");
				alert1.showAndWait();
				stop = true;
			}
			try {
				newPrice = Double.parseDouble(txtPrice.getText());
			}catch(NumberFormatException n) {
				alert1.setContentText("Debe ingresar un valor numerico que represente el precio del producto");
				alert1.showAndWait();
				stop = true;
			}
			if(stop==false) {
				try {
					angelaccesorios.updateProduct(selectedProduct, newBrand, newModel, newUnits, newPrice, guarantee, enabled);
					Alert alert2 = new Alert(AlertType.INFORMATION);
	        		alert2.setTitle("Informacion");
	        		alert2.setHeaderText(null);
	        		alert2.setContentText("El producto ha sido actualizado exitosamente");
	        		alert2.showAndWait();
				}catch(NoQuantityException nq) {
					alert1.setContentText(nq.getMessage());
	    			alert1.showAndWait();
				}catch(NegativeQuantityException ng) {
					alert1.setContentText(ng.getMessage());
	    			alert1.showAndWait();
				}catch(NoPriceException p) {
					alert1.setContentText(p.getMessage());
	    			alert1.showAndWait();
				}catch(NegativePriceException np) {
					alert1.setContentText(np.getMessage());
	    			alert1.showAndWait();
				}catch(SameProductException s) {
					alert1.setContentText(s.getMessage());
	    			alert1.showAndWait();
				}	
			}
			initializeCmbxTypeOfProduct();
    		initializeCmbxBrand();
    		txtModel.clear();
    		txtUnits.clear();
    		txtPrice.clear();
    		rbYes.setSelected(false);
    		rbNo.setSelected(false);
    		ckbxDisable.setSelected(false);
    		tvOfProducts.getItems().clear();
    		initializeTableViewOfProducts(angelaccesorios.getProducts());
    	}else {
    		showValidationErrorAlert();
    	}
	}


	@FXML
	public void listProducts(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("list-products.fxml"));
		fxmlLoader.setController(this);

		Parent clientPane = fxmlLoader.load();


		mainPane.setCenter(clientPane);
		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());
	}

	@FXML
	public void searchProductByTypeAndBrand(ActionEvent event) {
		String type = txtTypeOfProduct.getText();
		String brand = txtBrand.getText();
		if(!type.equals("") && !brand.equals("")) {
			ArrayList<Product> p = angelaccesorios.returnFoundProducts(type, brand);
			tvOfProducts.getItems().clear();
			initializeTableViewOfProducts(p);
		}else {
			showValidationErrorAlert();
		}
	}

	@FXML
	public void sortingPricesOfProducts(ActionEvent event) {
		ArrayList<Product> products = angelaccesorios.sortingPricesOfProducts();
		tvOfProducts.getItems().clear();
		initializeTableViewOfProducts(products);
	}

	@FXML
	public void manageReceipt(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manage-receipt.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		
		mainPane.setCenter(menuPane);
		//mainPane.setStyle("-fx-background-image: url(/ui/.jpg)");

		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());
		receiptMenu.setVisible(true);
		angelaccesorios.resetReceiptProductsAndQuantities();
		initializeComboBoxClients();
		initializeComboBoxPaymentMethods();
	}

	@FXML
	public void addPaymentToAReceipt(ActionEvent event) {

	}

	@FXML
	public void addProductToAReceipt(ActionEvent event) {
		Product selectedProduct= tvOfAddedProducts.getSelectionModel().getSelectedItem();
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
				
		try {
			if(!txtQuantityProduct.getText().isEmpty()) {
				int quantity=Integer.parseInt(txtQuantityProduct.getText());
				angelaccesorios.addProductToAReceipt(selectedProduct, quantity, angelaccesorios.getReceiptProducts(), angelaccesorios.getReceiptQuantitiesProducts());

				initializeTableViewOfReceiptProducts();
				initializeListViewOfQuantitiesProducts();
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setTitle("Información");
				alert1.setHeaderText(null);
				alert1.setContentText("Producto agregado");
				alert1.showAndWait();

				btAddProductR.setDisable(true);
				txtNameProduct.setText("");
				txtQuantityProduct.setText("");
				txtQuantityProduct.setEditable(false);
				btDeleteProductR.setDisable(true);
			}else {
				showValidationErrorAlert();
			}
		} catch (NumberFormatException e) {
				alert.setContentText("Digite la cantidad en formato numérico");
				alert.showAndWait();
			}catch (SameProductException e) {
				alert.setContentText("El producto ya había sido agregado");
				alert.showAndWait();
			} catch (NoQuantityException e) {
				alert.setContentText("La cantidad del producto no puede ser cero");
				alert.showAndWait();
			} catch (NegativeQuantityException e) {
				alert.setContentText("La cantidad del producto no puede ser negativa");
				alert.showAndWait();
			} catch (ExcessQuantityException e) {
				alert.setContentText("La cantidad del producto excede a las unidades disponibles");
				alert.showAndWait();
			}
		
	}

	@FXML
	public void addReceipt(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		try {
			if(cmbxClients.getValue()!=null && !txtObsevations.getText().isEmpty() && cbPaymentMethod.getValue()!=null) {
				angelaccesorios.createCashReceipt(angelaccesorios.getReceiptProducts(), angelaccesorios.getReceiptQuantitiesProducts(), cmbxClients.getValue(), txtObsevations.getText(), cbPaymentMethod.getValue());
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setTitle("Información");
				alert1.setHeaderText(null);
				alert1.setContentText("La factura se ha creado con exito");
				alert1.showAndWait();
				
				cmbxClients.setValue(null);
				cbPaymentMethod.setValue(null);
				txtObsevations.setText("");
				initializeTableViewOfCountedReceipts();
				
			}else {
				showValidationErrorAlert();
			}
		} catch (NoProductsAddedException e) {
			alert.setContentText("No se han añadido productos a la factura");
			alert.showAndWait();
		} catch (UnderAgeException e) {
			alert.setContentText("El cliente es menor de edad, no puede adquirir el equipo electrónico");
			alert.showAndWait();
		} 
	}
	
	@FXML
	public void addSeparateReceipt(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		try {
			if(cmbxClientsSR.getValue()!=null && !txtPaymentValue.getText().isEmpty() && cbPaymentMethodSR.getValue()!=null) {
				double value=Double.parseDouble(txtPaymentValue.getText());
				
				angelaccesorios.createSeparateReceipt(angelaccesorios.getReceiptProducts(), angelaccesorios.getReceiptQuantitiesProducts(), cmbxClientsSR.getValue(), cbPaymentMethodSR.getValue(),value);
				
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setTitle("Información");
				alert1.setHeaderText(null);
				alert1.setContentText("La factura se ha creado con exito");
				alert1.showAndWait();
				
				cmbxClientsSR.setValue(null);
				cbPaymentMethodSR.setValue(null);
				txtPaymentValue.setText("");
				initializeTableViewOfSeparateReceipts();
				
			}else {
				showValidationErrorAlert();
			}
		} catch (NoProductsAddedException e) {
			alert.setContentText("No se han añadido productos a la factura");
			alert.showAndWait();
		} catch (UnderAgeException e) {
			alert.setContentText("El cliente es menor de edad, no puede adquirir el equipo electrónico");
			alert.showAndWait();
		} catch (NoPriceException e) {
			alert.setContentText("El valor del abono no puede ser cero");
			alert.showAndWait();
		} catch (NegativePriceException e) {
			alert.setContentText("El valor del abono no puede ser negativo");
			alert.showAndWait();
		}catch (NumberFormatException e) {
			alert.setContentText("Digite el valor del abono en formato numérico");
			alert.showAndWait();
		}
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
		vBoxListViewQ.setVisible(true);
		initializeTableViewOfAddedProducts();
		initializeTableViewOfReceiptProducts();
		initializeListViewOfQuantitiesProducts();
		
	}
	
	private void initializeComboBoxClients() {
		ObservableList<Client> options = 
			    FXCollections.observableArrayList(angelaccesorios.returnEnabledClients());
		cmbxClients.setItems(options);
		cmbxClientsSR.setItems(options);
	}

	private void initializeComboBoxPaymentMethods() {
		ObservableList<String> options = 
			    FXCollections.observableArrayList("Efectivo","Tarjeta de debito","Tarjeta de credito","Transferencia bancaria");
		cbPaymentMethod.setItems(options);
		cbPaymentMethodSR.setItems(options);
	}

	private void initializeTableViewOfAddedProducts() {
		ObservableList<Product> observableList;
    	observableList = FXCollections.observableArrayList(angelaccesorios.returnEnabledProducts());
    	tvOfAddedProducts.setItems(observableList);
    	
    	colNameAddedProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("Info"));
  

    	tvOfAddedProducts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	private void initializeTableViewOfReceiptProducts() {
		ObservableList<Product> observableList;
    	observableList = FXCollections.observableArrayList(angelaccesorios.getReceiptProducts());
    	tvOfReceiptProducts.setItems(observableList);
    	
    	colNameRProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("Info"));
  

    	tvOfReceiptProducts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	private void initializeListViewOfQuantitiesProducts() {
		ObservableList<Integer> observableList;
    	observableList = FXCollections.observableArrayList(angelaccesorios.getReceiptQuantitiesProducts());
    	lvOfQuantities.setItems(observableList);
	}

	@FXML
	public void clickOnTableViewOfAddedProducts(MouseEvent event) {
		Product selectedProduct= tvOfAddedProducts.getSelectionModel().getSelectedItem();
		if (selectedProduct!=null) {
			txtNameProduct.setText(selectedProduct.getInfo());
			txtQuantityProduct.setText("");
			txtQuantityProduct.setEditable(true);
			btAddProductR.setDisable(false);
		}
	}

	@FXML
	public void clickOnTableViewOfReceiptProducts(MouseEvent event) {
		Product selectedProduct= tvOfReceiptProducts.getSelectionModel().getSelectedItem();
		if (selectedProduct!=null) {
			txtNameProduct.setText(selectedProduct.getInfo());
			int i=angelaccesorios.getReceiptProducts().indexOf(selectedProduct);
			txtQuantityProduct.setText(angelaccesorios.getReceiptQuantitiesProducts().get(i)+"");
			
			btAddProductR.setDisable(true);
			txtQuantityProduct.setEditable(false);
			btDeleteProductR.setDisable(false);
		}
	}

	@FXML
	public void clickOnTableViewOfReceipts(MouseEvent event) {
		Receipt selectedReceipt = tvOfCountedReceipts.getSelectionModel().getSelectedItem();
		if(selectedReceipt!=null) {
			btGenerateR.setDisable(false);
		}
	}
	@FXML
	public void clickOnTableViewOfSeparateReceipts(MouseEvent event) {

	}

	@FXML
	public void deleteProductOfAReceipt(ActionEvent event) {
		Product selectedProduct= tvOfReceiptProducts.getSelectionModel().getSelectedItem();
		angelaccesorios.deleteProductFromAReceipt(selectedProduct, angelaccesorios.getReceiptProducts(), angelaccesorios.getReceiptQuantitiesProducts());
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText(null);
		alert.setContentText("El producto ha sido eliminado de la lista");
		alert.showAndWait();
		
		btAddProductR.setDisable(true);
		txtNameProduct.setText("");
		txtQuantityProduct.setText("");
		btDeleteProductR.setDisable(true);
		
		initializeTableViewOfReceiptProducts();
		initializeListViewOfQuantitiesProducts();
	}

	@FXML
	public void deleteReceipt(ActionEvent event) {

	}

	@FXML
	public void generateReceipt(ActionEvent event) {
		Receipt r = tvOfCountedReceipts.getSelectionModel().getSelectedItem();
		SeparateReceipt sr = tvOfSeparateReceipts.getSelectionModel().getSelectedItem();
		if(r!=null || sr!=null) {
			JFileChooser fileChooser = new JFileChooser();
	    	fileChooser.setDialogTitle("Elija la carpeta en donde quiere guardar la factura a generar");
	    	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	fileChooser.setAcceptAllFileFilterUsed(false);
	    	if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("Generar factura");
			    try {
			    	if(r!=null) {
			    		OutputStream text_exit = new FileOutputStream(fileChooser.getSelectedFile()+"\\factura#"+r.getCode()+".pdf");
			    		angelaccesorios.generatePDFReceipt(text_exit, r);
			    	}else {
			    		OutputStream text_exit = new FileOutputStream(fileChooser.getSelectedFile()+"\\factura #"+sr.getCode()+".pdf");
			    		angelaccesorios.generatePDFReceipt(text_exit, sr);	
			    	}
				    alert.setHeaderText(null);
				    alert.setContentText("La factura ha sido exportada exitosamente");
				    alert.showAndWait();
				} catch (DocumentException | FileNotFoundException e) {
					alert.setHeaderText(null);
				    alert.setContentText("Lo sentimos, ha ocurrido un error en el proceso");
					e.printStackTrace();
				}
	    	}	
		}
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
		
		ObservableList<Receipt> observableList;
    	observableList = FXCollections.observableArrayList(angelaccesorios.returnCashReceipts());
    	tvOfCountedReceipts.setItems(observableList);
    	
    	colCodeC.setCellValueFactory(new PropertyValueFactory<Receipt, String>("Code"));
    	colDateandTimeC.setCellValueFactory(new PropertyValueFactory<Receipt, String>("DateAndHour"));
    	colClientC.setCellValueFactory(new PropertyValueFactory<Receipt, String>("Buyer"));
    	colUserC.setCellValueFactory(new PropertyValueFactory<Receipt, String>("UserName"));
    	colProductsInCR.setCellValueFactory(new PropertyValueFactory<Receipt, String>("AllProducts"));
    	//colSubtotalPriceC.setCellValueFactory(new PropertyValueFactory<Receipt, Double>("typeId"));
    	//colIVAC.setCellValueFactory(new PropertyValueFactory<Receipt, Double>("status"));
    	//colTotalValueC.setCellValueFactory(new PropertyValueFactory<Receipt, Double>("status"));
    	colPaymentMC.setCellValueFactory(new PropertyValueFactory<Receipt, String>("PaymentMethod"));
    	colObservationsC.setCellValueFactory(new PropertyValueFactory<Receipt, String>("Observations"));
    	

    	tvOfCountedReceipts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		 
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
		ObservableList<SeparateReceipt> observableList;
    	observableList = FXCollections.observableArrayList(angelaccesorios.returnSeparateReceipts());
    	tvOfSeparateReceipts.setItems(observableList);
    	
    	colCodeS.setCellValueFactory(new PropertyValueFactory<Receipt, String>("Code"));
    	colDateandTimeS.setCellValueFactory(new PropertyValueFactory<Receipt, String>("DateAndHour"));
    	colClientS.setCellValueFactory(new PropertyValueFactory<Receipt, String>("Buyer"));
    	colUserS.setCellValueFactory(new PropertyValueFactory<Receipt, String>("UserName"));
    	colProductsInSR.setCellValueFactory(new PropertyValueFactory<Receipt, String>("AllProducts"));
    	//colSubtotalPriceS.setCellValueFactory(new PropertyValueFactory<Receipt, Double>("typeId"));
    	//colIVAS.setCellValueFactory(new PropertyValueFactory<Receipt, Double>("status"));
    	//colTotalValueS.setCellValueFactory(new PropertyValueFactory<Receipt, Double>("status"));
    	colPayments.setCellValueFactory(new PropertyValueFactory<Receipt, String>("AllPayments"));
    	//colToPay.setCellValueFactory(new PropertyValueFactory<Receipt, Double>("PaymentMethod"));
    	colObservationsS.setCellValueFactory(new PropertyValueFactory<Receipt, String>("Observations"));
    	
    	tvOfSeparateReceipts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}

	@FXML
	public void returnToReceiptForm(ActionEvent event) {
		btAddProductR.setDisable(true);
		txtNameProduct.setText("");
		txtQuantityProduct.setText("");
		txtQuantityProduct.setEditable(false);
		btDeleteProductR.setDisable(true);
		addProductsToAReceipt.setVisible(false);
		tvOfAddedProducts.setVisible(false);
		tvOfReceiptProducts.setVisible(false);
		vBoxListViewQ.setVisible(false);
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
		angelaccesorios.resetReceiptProductsAndQuantities();
	}

	@FXML
	public void searchClientByName(ActionEvent event) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
    	if(!txtClientSearchedName.getText().isEmpty() && !txtClientSearchedLastName.getText().isEmpty()) {
    	   
    		ObservableList<Client> clientsList = FXCollections.observableArrayList(angelaccesorios.searchClientByName(txtClientSearchedName.getText().toUpperCase(),txtClientSearchedLastName.getText().toUpperCase()));
    		cmbxClients.setItems(clientsList);
    		if(clientsList.isEmpty()) {

    			alert.setContentText("No se encontró al cliente "+txtClientSearchedName.getText().toUpperCase()+" "+txtClientSearchedLastName.getText().toUpperCase());
        		alert.showAndWait();
        		initializeComboBoxClients();
        		txtClientSearchedName.clear();
        		txtClientSearchedLastName.clear();

    		}else {
    			Alert alert2 = new Alert(AlertType.INFORMATION);
    		    alert2.setTitle("Cliente(s) encontrado(s)");
    		    alert2.setHeaderText(null);
    		    alert2.setContentText("Puede desplegar la lista para seleccionar al cliente buscado");
    		    alert2.showAndWait();
    			txtClientSearchedName.clear();
        		txtClientSearchedLastName.clear();
    		}
    	}else {
    		
    		alert.setContentText("Debe ingresar nombre y apellido para buscar el cliente");
    		alert.showAndWait();
    		initializeComboBoxClients();

    	}
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

	

		mainPane.setCenter(clientPane);

		//mainPane.setStyle("-fx-background-image: url(/ui/fondo2.jpg)");
		initializeTableViewClients();

		lbUserName.setText(angelaccesorios.getLoggedUser().getUserName());
		
		initializeComboBoxIdType();

	}
	
	@FXML
	public void searchClientByNameInClient(ActionEvent event) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
    	if(!txtClientSearchedName.getText().isEmpty() && !txtClientSearchedLastName.getText().isEmpty()) {
    	   
    		ObservableList<Client> clientsList = FXCollections.observableArrayList(angelaccesorios.searchClientByName(txtClientSearchedName.getText().toUpperCase(),txtClientSearchedLastName.getText().toUpperCase()));
    		tvListClients.setItems(clientsList);
    		if(clientsList.isEmpty()) {

    			alert.setContentText("No se encontró al cliente "+txtClientSearchedName.getText().toUpperCase()+" "+txtClientSearchedLastName.getText().toUpperCase());
        		alert.showAndWait();
        		initializeTableViewClients();
    		}
    		
    		txtClientSearchedName.clear();
    		txtClientSearchedLastName.clear();
    	}else {
    		
    		alert.setContentText("Debe ingresar nombre y apellido para buscar el cliente");
    		alert.showAndWait();
    		initializeTableViewClients();

    	}
	}

	private void initializeComboBoxIdType() {
		ObservableList<String> options = 
			    FXCollections.observableArrayList("TI","CC","PP","CE");
		cmbxIdType.setItems(options);
	}
	

	private void initializeTableViewClients() {
		ObservableList<Client> observableList;
    	observableList = FXCollections.observableArrayList(angelaccesorios.getClients());
    	tvListClients.setItems(observableList);
    	
    	colNameClient.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
    	colLastNameClient.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
    	colIdClient.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
    	colAddressClient.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
    	colPhoneClient.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
    	colIdTypeClient.setCellValueFactory(new PropertyValueFactory<Client, String>("typeId"));
    	colEnabledClient.setCellValueFactory(new PropertyValueFactory<Client, String>("status"));
    

    	tvListClients.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}

	@FXML
	public void clickOnTableViewClients(MouseEvent event) {
		if (tvListClients.getSelectionModel().getSelectedItem() != null) {
    		enableButtons();
    		Client selectedClient = tvListClients.getSelectionModel().getSelectedItem();
    		txtName.setText(selectedClient.getName());
    		txtLastName.setText(selectedClient.getLastName());
    		txtId.setText(selectedClient.getId());
    		txtAddress.setText(selectedClient.getAddress());
    		txtPhone.setText(selectedClient.getPhone());
    		cmbxIdType.setValue(selectedClient.getTypeId().name());

   		
    		ckbxDisable.setSelected(!selectedClient.isEnabled());
    	}
	}

	@FXML
	public void createClient(ActionEvent event) throws IOException {
		if (!txtId.getText().isEmpty() && !txtName.getText().isEmpty() && !txtLastName.getText().isEmpty() && !txtAddress.getText().isEmpty() && !txtPhone.getText().isEmpty() && !cmbxIdType.getSelectionModel().getSelectedItem().isEmpty()) {
		
    		try {
				angelaccesorios.createClient(txtName.getText().toUpperCase(),txtLastName.getText().toUpperCase(),txtId.getText(),cmbxIdType.getSelectionModel().getSelectedItem(), txtAddress.getText().toUpperCase(),txtPhone.getText());
				
				Alert alert1 = new Alert(AlertType.INFORMATION);
    			alert1.setTitle("Informacion");
    			alert1.setHeaderText(null);
    			alert1.setContentText("El cliente ha sido creado exitosamente!");
    			alert1.showAndWait();

    			txtName.clear();
    			txtLastName.clear();
    			txtId.clear();
    			txtAddress.clear();
    			txtPhone.clear();
    			cmbxIdType.setValue(null);

    			initializeTableViewClients();
    		} catch (SameIDException e) {

    			Alert alert2 = new Alert(AlertType.ERROR);
    			alert2.setTitle("Error de validacion");
    			alert2.setHeaderText(null);
    			alert2.setContentText("No se pudo crear el cliente, ya existe uno con el mismo número de identificación");
    			alert2.showAndWait();
			}
			
    	}else {
    		showValidationErrorAlert();
    	}
	}

	@FXML
	public void deleteClient(ActionEvent event) throws IOException {
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
    	alert1.setTitle("Confirmacion de proceso");
    	alert1.setHeaderText(null);
    	alert1.setContentText("¿Esta seguro de que quiere eliminar el cliente "+tvListClients.getSelectionModel().getSelectedItem()+"?");
    	Optional<ButtonType> result = alert1.showAndWait();
    	if (result.get() == ButtonType.OK){
        	
    		boolean deleted= angelaccesorios.deleteClient(tvListClients.getSelectionModel().getSelectedItem() );
        	Alert alert2 = new Alert(AlertType.INFORMATION);
        	alert2.setTitle("Informacion");
        	alert2.setHeaderText(null);
        	
        	if(deleted) {
        		alert2.setContentText("El cliente ha sido eliminado exitosamente");
        		txtName.clear();
        		txtLastName.clear();
            	txtId.clear();
            	txtPhone.clear();
            	txtAddress.clear();
            	cmbxIdType.setValue(null);

            	disableButtons();

            	
            	initializeTableViewClients();
            	
            	           	
        	}else {
        		alert2.setContentText("El cliente no se pudo eliminar");

        	}
        	alert2.showAndWait();
        	
        	disableButtons();
    	}
	}

	@FXML
	public void updateClient(ActionEvent event) throws IOException {
		if (!txtId.getText().isEmpty() && !txtName.getText().isEmpty() && !txtLastName.getText().isEmpty() && !txtAddress.getText().isEmpty() && !txtPhone.getText().isEmpty() &&  !cmbxIdType.getSelectionModel().getSelectedItem().isEmpty()) {

    		try {
				angelaccesorios.updateClient(tvListClients.getSelectionModel().getSelectedItem(),txtName.getText().toUpperCase(),txtLastName.getText().toUpperCase(),txtId.getText(),cmbxIdType.getSelectionModel().getSelectedItem(), txtAddress.getText().toUpperCase(),txtPhone.getText(), !ckbxDisable.isSelected());
				Alert alert1 = new Alert(AlertType.INFORMATION);
        		alert1.setTitle("Informacion");
        		alert1.setHeaderText(null);
        		alert1.setContentText("El empleado ha sido actualizado exitosamente!");
        		alert1.showAndWait();
        		
        		txtName.clear();
        		txtLastName.clear();
            	txtId.clear();
            	txtPhone.clear();
            	txtAddress.clear();
            	cmbxIdType.setValue(null);

            	disableButtons();
            	tvListClients.getItems().clear();

            	initializeTableViewClients();
    		} catch (SameIDException e) {
    			Alert alert2 = new Alert(AlertType.ERROR);
    			alert2.setTitle("Error de validacion");
    			alert2.setHeaderText(null);
    			alert2.setContentText("No se pudo actualizar el empleado, intentelo nuevamente");
    			alert2.showAndWait();
    		
			}


    	}else {
    		showValidationErrorAlert();
    	}
	}

	public void loadRegisterAdmin() throws IOException	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register-admin.fxml"));
		fxmlLoader.setController(this);

		Parent clientPane = fxmlLoader.load();
	
		

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
	public void createUser(ActionEvent event) throws IOException {
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
	public void deleteUser(ActionEvent event) throws IOException {
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
	public void updateUser(ActionEvent event) throws IOException {
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
		ObservableList<String> hoursList = FXCollections.observableArrayList(angelaccesorios.getHours());
		cmbxInitialHour.setItems(hoursList);
		cmbxInitialHour.setValue("00");
		cmbxFinalHour.setItems(hoursList);
		cmbxFinalHour.setValue("23");
	}

	private void initializeComboBoxOfMinutes() {
		ObservableList<String> minutesList = FXCollections.observableArrayList(angelaccesorios.getMinutes());
		cmbxInitialMinute.setItems(minutesList);
		cmbxInitialMinute.setValue("00");
		cmbxFinalMinute.setItems(minutesList);
		cmbxFinalMinute.setValue("59");
	}

	@FXML
	public void exportUsersReport(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exportUsersReport.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		
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
		if(dtPickerInitialDate.getValue()!=null && dtPickerFinalDate.getValue()!=null && cmbxInitialHour.getValue()!=null && cmbxInitialMinute.getValue()!=null && cmbxFinalHour.getValue()!=null && cmbxFinalMinute.getValue()!=null) {
    		LocalDate initialDate = dtPickerInitialDate.getValue();
    		LocalDate finalDate = dtPickerFinalDate.getValue();
    		String iniDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(initialDate).toString();
    		String finDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(finalDate).toString();
    		String initialTime = iniDate+" "+cmbxInitialHour.getValue().toString()+":"+cmbxInitialMinute.getValue().toString();
    		String finalTime = finDate+" "+cmbxFinalHour.getValue().toString()+":"+cmbxFinalMinute.getValue().toString();
    		FileChooser fileChooser = new FileChooser();
        	fileChooser.setTitle("Elija el archivo en donde se va a guardar el reporte");
        	File fExp= fileChooser.showSaveDialog(mainPane.getScene().getWindow());
        	if(fExp!=null) {
        		Alert alert = new Alert(AlertType.INFORMATION);
    		    alert.setTitle("Exportar reporte sobre usuarios");
    		    try {
    				angelaccesorios.exportUsersReport(fExp.getAbsolutePath(),initialTime,finalTime);
    			    alert.setHeaderText(null);
    			    alert.setContentText("El reporte de usuarios ha sido exportado exitosamente");
    			    alert.showAndWait();
    			} catch (IOException e) {
    				alert.setHeaderText(null);
    			    alert.setContentText("Lo sentimos, ha ocurrido un error en el proceso\n"+e.getMessage());
    			    alert.showAndWait();
    			} catch (ParseException p) {
    				alert.setHeaderText(null);
    			    alert.setContentText("Lo sentimos, ha ocurrido un error en el proceso\n"+p.getMessage());
    			    alert.showAndWait();
				} catch (HigherDateAndHour h) {
					alert.setHeaderText(null);
    			    alert.setContentText(h.getMessage());
    			    alert.showAndWait();
				}
        	}
    	}else {
    		showValidationErrorAlert();
    	}
	}

	@FXML
	public void exportProductsReport(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exportProductsReport.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		
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
		if(dtPickerInitialDate.getValue()!=null && dtPickerFinalDate.getValue()!=null && cmbxInitialHour.getValue()!=null && cmbxInitialMinute.getValue()!=null && cmbxFinalHour.getValue()!=null && cmbxFinalMinute.getValue()!=null) {
    		LocalDate initialDate = dtPickerInitialDate.getValue();
    		LocalDate finalDate = dtPickerFinalDate.getValue();
    		String iniDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(initialDate).toString();
    		String finDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(finalDate).toString();
    		String initialTime = iniDate+" "+cmbxInitialHour.getValue().toString()+":"+cmbxInitialMinute.getValue().toString();
    		String finalTime = finDate+" "+cmbxFinalHour.getValue().toString()+":"+cmbxFinalMinute.getValue().toString();
    		FileChooser fileChooser = new FileChooser();
        	fileChooser.setTitle("Elija el archivo en donde se va a guardar el reporte");
        	File fExp= fileChooser.showSaveDialog(mainPane.getScene().getWindow());
        	if(fExp!=null) {
        		Alert alert = new Alert(AlertType.INFORMATION);
    		    alert.setTitle("Exportar reporte sobre productos");
    		    try {
    				angelaccesorios.exportProductsReport(fExp.getAbsolutePath(),initialTime,finalTime);
    			    alert.setHeaderText(null);
    			    alert.setContentText("El reporte de productos ha sido exportado exitosamente");
    			    alert.showAndWait();
    			} catch (IOException e) {
    				alert.setHeaderText(null);
    			    alert.setContentText("Lo sentimos, ha ocurrido un error en el proceso\n"+e.getMessage());
    			    alert.showAndWait();
    			} catch (ParseException p) {
    				alert.setHeaderText(null);
    			    alert.setContentText("Lo sentimos, ha ocurrido un error en el proceso\n"+p.getMessage());
    			    alert.showAndWait();
				} catch (HigherDateAndHour h) {
					alert.setHeaderText(null);
    			    alert.setContentText(h.getMessage());
    			    alert.showAndWait();
				}
        	}
    	}else {
    		showValidationErrorAlert();
    	}
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
