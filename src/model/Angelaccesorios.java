package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import exceptions.EmailException;
import exceptions.ExcessQuantityException;
import exceptions.ExcessValueException;
import exceptions.HigherDateAndHourException;
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

public class Angelaccesorios implements Serializable{
	private static final long serialVersionUID = 1;
	private User firstUser;
	private User lastUser;
	private User loggedUser;

	public final static int PROGRAM=0;
	public final static int TEST=1;
	public static String ANGELACCESORIOS_SAVE_PATH_FILE;
	public final static String SEPARATOR = ";";
	private ArrayList<Brand> brands;
	private ArrayList<Product> products;
	private ArrayList<Receipt> receipts;
	private TypeOfProduct typePRoot;
	private Supplier supplierRoot;
	private List<Client> clients;


	private ArrayList<Product> receiptProducts;
	private ArrayList<Integer> receiptQuantitiesProducts;

	private double numProgress;

	
	public Angelaccesorios(int num) {
		if(num==PROGRAM) {
			ANGELACCESORIOS_SAVE_PATH_FILE = "data/angelaccesorios.ackl";
		}
		if(num==TEST) {
			ANGELACCESORIOS_SAVE_PATH_FILE ="data/testFiles/angelaccesoriosTest.ackl";
		}
		brands = new ArrayList<Brand>();
		products = new ArrayList<Product>();
		clients=new ArrayList<Client>();
		receipts = new ArrayList<Receipt>(); 

		receiptProducts= new ArrayList<Product>();
		receiptQuantitiesProducts= new ArrayList<Integer>();
		
		numProgress=0;
	}
	
	public double getNumProgress() {
		return numProgress;
	}
	public void setNumProgress(double numProgress) {
		this.numProgress = numProgress;
	}
	public void resetReceiptProductsAndQuantities() {
		receiptProducts= new ArrayList<Product>();
		receiptQuantitiesProducts= new ArrayList<Integer>();
	}
	public ArrayList<Product> getReceiptProducts() {
		return receiptProducts;
	}
	public void setReceiptProducts(ArrayList<Product> receiptProducts) {
		this.receiptProducts = receiptProducts;
	}
	public ArrayList<Integer> getReceiptQuantitiesProducts() {
		return receiptQuantitiesProducts;
	}
	public void setReceiptQuantitiesProducts(ArrayList<Integer> receiptQuantitiesProducts) {
		this.receiptQuantitiesProducts = receiptQuantitiesProducts;
	}

	public ArrayList<Product> returnEnabledProducts(){
		ArrayList<Product> list= new ArrayList<Product>();
		for(int i=0;i<products.size();i++) {
			if(products.get(i).isEnabled()) {
				list.add(products.get(i));
			}
		}
		return list;
	}

	public ArrayList<Client> returnEnabledClients(){
		ArrayList<Client> list= new ArrayList<Client>();
		for(int i=0;i<clients.size();i++) {
			if(clients.get(i).isEnabled()) {
				list.add(clients.get(i));
			}
		}
		return list;
	}

	public List<Client> getClients() {
		return clients;	
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User u) {
		loggedUser=u;
	}

	public User getFirstUser() {
		return firstUser;
	}

	public User getLastUser() {
		return lastUser;
	}



	public void createUser(String id, String name, String lastName,String userName, String password) throws SpaceException, SameIDException, SameUserNameException, IOException {

		userName=userName.trim();
		String parts[]=userName.split(" ");
		if(parts.length>1) {
			throw new SpaceException();
		}

		User user= searchUser(id);
		if(user!=null) {
			throw new SameIDException();
		}
		User user2=searchUserName(userName);
		if(user2!=null) {
			throw new SameUserNameException();
		}



		user= new User(name,lastName,id, userName,password);

		lastUser.setNext(user);
		user.setPrev(lastUser);
		lastUser=user;


		saveDataAngelaccesorios();


	}


	public void createUserAdmin(String id, String name, String lastName,String userName, String password,String email) throws EmailException, SpaceException, IOException {

		email=email.trim();
		String parts[]=email.split("@");
		if(parts.length>2 ||parts.length<=1) {
			throw new EmailException();
		}

		parts=email.split(" ");
		if(parts.length>1) {
			throw new SpaceException();
		}

		userName=userName.trim();
		parts=userName.split(" ");
		if(parts.length>1) {
			throw new SpaceException();
		}

		Admin admin= new Admin(name,lastName,id, userName,password, email);

		firstUser=admin;

		lastUser=admin;


		saveDataAngelaccesorios();

	}

	public User searchUser(String id) {

		return searchUser( firstUser, id);
	}

	private User searchUser(User current, String id) {
		User u=null;
		if(current!=null && u==null) {
			if(current.getId().equals(id)) {
				u=current;
			}else {
				current=current.getNext();
				u=searchUser(current, id);
			}
		}
		return u;
	}

	public User searchUserName(String userName) {

		return searchUserName( firstUser, userName);
	}

	private User searchUserName(User current, String userName) {
		User u=null;
		if(current!=null && u==null) {
			if(current.getUserName().equals(userName)) {
				u=current;
			}else {
				current=current.getNext();
				u=searchUserName(current, userName);
			}
		}
		return u;
	}

	public boolean deleteUser(User user) throws IOException {
		boolean deleted=false;

		if(firstUser.getId()!=user.getId()) {
			User prev=user.getPrev();
			User next=user.getNext();

			user.setNext(null);
			user.setPrev(null);
			prev.setNext(next);
			if(next!=null) {
				next.setPrev(prev);
			}
			deleted=true;
		}
		saveDataAngelaccesorios();
		return deleted;

	}


	public void updateUser(User user,String id, String name, String lastName, String userName, String password, boolean enabled, String email) throws SameIDException, SameUserNameException, SpaceException, EmailException, IOException {
		userName=userName.trim();
		String[] parts=userName.split(" ");
		if(parts.length>1) {
			throw new SpaceException();
		}		

		User u1=searchUserName(userName);
		if(user!=u1) {
			if(u1!=null) {
				throw new SameUserNameException();
			}
		}

		User u=searchUser(id);
		if(user!=u) {
			if(u!=null) {
				throw new SameIDException();
			}
		}
		if(u instanceof Admin) {
			email=email.trim();
			parts=email.split("@");
			if(parts.length>2 ||parts.length<=1) {
				throw new EmailException();

			}
			((Admin)user).setEmail(email);
		}

		user.setName(name);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setPassword(password);
		user.setEnabled(enabled);
		user.setId(id);


		saveDataAngelaccesorios();

	}

	public boolean logInUser(String userName, String password) {
		boolean logIn=false;
		String parts[]=userName.split("@");
		if(parts.length==2) {
			if(((Admin)firstUser).getEmail().equals(userName)) {
				if(firstUser.getPassword().equals(password)) {
					loggedUser=firstUser;
					logIn=true;
				}
			}
		}else {
			User user=searchUserName(userName);
			if(user!=null) { 

				if(user.getPassword().equals(password) && user.isEnabled()) {
					loggedUser=user;
					logIn=true;
				}
			}
		}

		return logIn;
	}

	public void createClient(String name, String lastName, String id, String typeId, String address, String phone) throws SameIDException, IOException {

		Client client= searchClient(id);
		if(client!=null) {
			throw new SameIDException();
		}

		client= new Client( name,  lastName,  id,  TypeId.valueOf(typeId),  address,  phone);
		addSortedClient(client);

		saveDataAngelaccesorios();

	}

	public void addSortedClient(Client client) {
		Comparator<Client> clientLastNameAndNameComparator=new ClientLastNameAndNameComparator();

		if(clients.isEmpty()) {
			clients.add(client);
		}
		else {
			int i=0;
			while(i<clients.size() && clientLastNameAndNameComparator.compare(clients.get(i),client)>0) {
				i++;
			}
			clients.add(i, client);
		}

	}

	public Client searchClient(String clientId) {
		boolean found=false;

		Client client=null;
		for(int i=0; i<clients.size() && !found;i++ ) {
			if(clients.get(i).getId().equals(clientId)) {
				client=clients.get(i);
				found=true;						
			}
		}
		return client;

	}

	public boolean deleteClient(Client client) throws IOException {
		boolean deleted=false;
		Receipt receipt=searchClientInReceipt(client);
		if(receipt==null) {

			int i=clients.indexOf(client);
			clients.remove(i);
			deleted=true;
			saveDataAngelaccesorios();


		}

		return deleted;

	}

	public Receipt searchClientInReceipt(Client client) {
		Receipt receipt=null;
		boolean found=false;
		for(int i=0; i<receipts.size() && !found;i++) {
			if(receipts.get(i).getBuyer().getId().equals(client.getId()) && receipts.get(i).isInForce()) {
				found=true;
				receipt=receipts.get(i);
			}

		}

		return receipt;
	}

	public void updateClient(Client client,String name, String lastName, String id, String typeId, String address, String phone, boolean enabled) throws SameIDException, IOException {

		Client client2= searchClient(id);

		if(client!=client2) {
			if(client2!=null) {
				throw new SameIDException();
			}
		}

		boolean sortList=false;
		if(!client.getLastName().equals(lastName) || !client.getName().equals(name) ) {
			sortList= true;
		}

		client.setName(name);
		client.setLastName(lastName);

		client.setEnabled(enabled);
		client.setId(id);
		client.setAddress(address);
		client.setPhone(phone);
		client.setTypeId(TypeId.valueOf(typeId));


		if(sortList) {
			Comparator<Client> clientLastNameAndNameComparator=new ClientLastNameAndNameComparator();

			Collections.sort(clients,Collections.reverseOrder(clientLastNameAndNameComparator));
		}
		saveDataAngelaccesorios();

	}

	public int binarySearchClient(String clientNames, String clientLastNames) {

		Client client= new Client(clientNames,clientLastNames,null,null,null,null);
		Comparator<Client> clientLastNameAndNameComparator=new ClientLastNameAndNameComparator();

		int pos = -1;
		int i=0;
		int j=clients.size()-1;

		while(i<=j && pos<0){
			int m= (i+j)/2;

			if(clientLastNameAndNameComparator.compare(clients.get(m),client)==0){
				pos =m;
			}else if(clientLastNameAndNameComparator.compare(clients.get(m),client)<0){
				j=m-1;
			}else{
				i=m+1;
			}
		}

		return pos;
	}

	public List<Client> searchClientByName(String clientNames, String clientLastNames){
		Comparator<Client> clientLastNameAndNameComparator=new ClientLastNameAndNameComparator();
		List<Client> clientsByName=new ArrayList<Client>();
		int pos;

		pos=binarySearchClient(clientNames,clientLastNames);
		int sameUp=1;
		int sameDown=1;
		if(pos>=0) {
			if(clients.get(pos).isEnabled()) {
				clientsByName.add(clients.get(pos));
			}

			boolean same=false;
			do {
				same=false;
				if((pos-sameDown)>=0 && clientLastNameAndNameComparator.compare(clients.get(pos), clients.get(pos-sameDown))==0) {
					if(clients.get(pos-sameDown).isEnabled()) {
						clientsByName.add(clients.get(pos-sameDown));
						sameDown++;
						same=true;
					}
				}

				if((pos+sameUp)<=clients.size()-1 && clientLastNameAndNameComparator.compare(clients.get(pos), clients.get(pos+sameUp))==0) {
					if(clients.get(pos+sameUp).isEnabled()) {
						clientsByName.add(clients.get(pos+sameUp));
						sameUp++;
						same=true;
					}
				}
			}while(same);

		}

		return clientsByName;
	}

	//All related with Receipt

	public ArrayList<Receipt> getReceipts() {
		return receipts;
	}

	public void setReceipts(ArrayList<Receipt> receipts) {
		this.receipts = receipts;
	}

	public ArrayList<Receipt> returnCashReceipts(){
		ArrayList<Receipt> list=new ArrayList<Receipt>();
		for(int i=0;i<receipts.size();i++) {
			if(!(receipts.get(i) instanceof SeparateReceipt)) {
				list.add(receipts.get(i));
			}
		}
		return list;
	}

	public ArrayList<SeparateReceipt> returnSeparateReceipts(){
		ArrayList<SeparateReceipt> list=new ArrayList<SeparateReceipt>();
		for(int i=0;i<receipts.size();i++) {
			if(receipts.get(i) instanceof SeparateReceipt) {
				list.add((SeparateReceipt) receipts.get(i));
			}
		}
		return list;
	}

	public void createCashReceipt(ArrayList<Product> listProd,ArrayList<Integer> listQ,Client buyer, String observations, String paymentMethod) throws NoProductsAddedException, UnderAgeException, IOException {
		if(listProd.isEmpty()) {
			throw new NoProductsAddedException();
		}

		if(buyer.getTypeId().name().equals("TI") && findElectronicEquipmentProductOnReceiptToBeCreated(listProd)) {
			throw new UnderAgeException();
		}

		Receipt receipt= new Receipt(listProd, listQ, buyer, loggedUser, observations, paymentMethod);
		receipts.add(receipt);
		receipt.restUnitsToAddedProducts();
		int codeNum = ThreadLocalRandom.current().nextInt(10000, 100000);
		String code = codeNum+"-"+receipts.size();
		receipt.setCode(code);

		loggedUser.setSumTotalReceipts(loggedUser.getSumTotalReceipts()+receipt.getTotal());
		loggedUser.setNumberReceipts(loggedUser.getNumberReceipts()+1);


		resetReceiptProductsAndQuantities();
		saveDataAngelaccesorios();
	}

	private boolean findElectronicEquipmentProductOnReceiptToBeCreated(ArrayList<Product> listProd) {
		boolean found=false;
		for(int i=0; i<listProd.size() && !found;i++) {
			if(listProd.get(i).getType() instanceof ElectronicEquipment) {
				found=true;
			}
		}
		return found;

	}


	public void createSeparateReceipt(ArrayList<Product> listProd,ArrayList<Integer> listQ,Client buyer, String paymentMethod, double valuePayment) throws NoProductsAddedException, UnderAgeException, NoPriceException, NegativePriceException, IOException, ExcessValueException {
		if(valuePayment==0) {
			throw new NoPriceException(valuePayment); 
		}
		if(valuePayment<0) {
			throw new NegativePriceException(valuePayment);
		}
		if(listProd.isEmpty()) {
			throw new NoProductsAddedException();
		}

		if(buyer.getTypeId().name().equals("TI") && findElectronicEquipmentProductOnReceiptToBeCreated(listProd)) {
			throw new UnderAgeException();
		}

		Receipt receipt= new SeparateReceipt(listProd, listQ, buyer, loggedUser, paymentMethod, valuePayment);
		if(((SeparateReceipt)receipt).calculateUnpaidPrice()<=0) {
			receipt=null;
			throw new ExcessValueException();
		}

		receipts.add(receipt);
		int codeNum = ThreadLocalRandom.current().nextInt(10000, 100000);
		String code = codeNum+"-"+receipts.size();
		receipt.setCode(code);

		loggedUser.setSumTotalReceipts(loggedUser.getSumTotalReceipts()+valuePayment);
		loggedUser.setNumberReceipts(loggedUser.getNumberReceipts()+1);
		resetReceiptProductsAndQuantities();
		saveDataAngelaccesorios();


	}

	public void addProductToAReceipt(Product prod, int quantity,ArrayList<Product> listProd,ArrayList<Integer> listQ) throws SameProductException, NoQuantityException, NegativeQuantityException, ExcessQuantityException {
		if(quantity==0) {
			throw new NoQuantityException(quantity); 
		}
		if(quantity<0) {
			throw new NegativeQuantityException(quantity);
		}

		if(quantity>prod.getUnits()) {
			throw new ExcessQuantityException();
		}
		boolean found=false;
		for(int i=0;i<listProd.size() && !found;i++) {
			if(listProd.get(i)==prod) {
				found=true;
			}
		}

		if(found) {
			throw new SameProductException();
		}

		listProd.add(prod);
		listQ.add(quantity);
	}

	public void deleteProductFromAReceipt(Product prod, ArrayList<Product> listProd,ArrayList<Integer> listQ) {
		int i=listProd.indexOf(prod);
		listProd.remove(i);
		listQ.remove(i);
	}

	public void updateSeparateReceipt(Receipt receipt, String paymentMethod, double valuePayable) throws NoPriceException, NegativePriceException, IOException, ExcessValueException {
		if(valuePayable==0) {
			throw new NoPriceException(valuePayable); 
		}
		if(valuePayable<0) {
			throw new NegativePriceException(valuePayable);
		}
		if(((SeparateReceipt)receipt).calculateUnpaidPrice()<valuePayable) {
			receipt=null;
			throw new ExcessValueException();
		}

		((SeparateReceipt)receipt).addPayment(valuePayable, paymentMethod, loggedUser);
		loggedUser.setSumTotalReceipts(loggedUser.getSumTotalReceipts()+valuePayable);
		loggedUser.setNumberReceipts(loggedUser.getNumberReceipts()+1);
		saveDataAngelaccesorios();
	}

	public boolean deleteReceipt(Receipt receipt) throws IOException {
		boolean deleted=false;
		if(receipt.findElectronicEquipmentProduct()) {
			if(!receipt.isInForce()) {
				int i=receipts.indexOf(receipt);
				receipts.remove(i);
				deleted=true;
			}
		}else {
			int i=receipts.indexOf(receipt);
			receipts.remove(i);
			deleted=true;
		}

		saveDataAngelaccesorios();
		return deleted;
	}

	public Receipt searchReceipt(String code) {
		Receipt receipt=null;
		for(int i=0;i<receipts.size() && receipt==null;i++) {
			if(receipts.get(i).getCode().equalsIgnoreCase(code)) {
				receipt=receipts.get(i);
			}

		}
		return receipt;
	}





















































































































































































	//All related with Brand

	/**
	* This method adds a brand to the systems's list of brands.<br>
	* <b>name</b>: addBrand <br>
	* <b>pre</b>: The variable name is already initialized. <br>
	* <b>post</b>: The brand was added successfully or not. <br>
	* @param name Is a String variable that contains the name of a brand. name!="" and name!=null.<br>
	* @throws IOException <br>
	* 		thrown if...
	* 		1. A local file that was no longer available is being read.<br>
    *       2. Any process closed the stream while a stream is being used to read data.<br>
    *       3. The disk space was no longer available while trying to write to a file.<br>
    * @return a <code> boolean </code> specifying added, a variable that indicates if the brand was added successfully or not.  
	*/
	public boolean addBrand(String name) throws IOException {
		Brand b = searchBrand(name);
		boolean added = false;
		if(b==null) {
			b = new Brand(name);
			brands.add(b);
			added = true;
			saveDataAngelaccesorios();
		}
		return added;
	}

	/**
	* This method deletes a brand from the system's list of brands.<br>
	* <b>name</b>: deleteBrand <br>
	* <b>post</b>: The brand was deleted successfully or not. <br>
	* @param brand Is a Brand object that represents a brand of the system. brand!=null.<br>
	* @throws IOException <br>
	* 		thrown if...
	* 		1. A local file that was no longer available is being read.<br>
    *       2. Any process closed the stream while a stream is being used to read data.<br>
    *       3. The disk space was no longer available while trying to write to a file.<br>
    * @return a <code> boolean </code> specifying deleted, a variable that indicates if the brand was deleted successfully or not.  
	*/
	public boolean deleteBrand(Brand brand) throws IOException {
		boolean deleted = false;
		if(!searchBrandInProducts(brand)){
			brands.remove(brands.indexOf(brand));
			deleted = true;
			saveDataAngelaccesorios();
		}
		return deleted;
	}

	/**
	* This method updates a brand from the system's list of brands.<br>
	* <b>name</b>: updateBrand <br>
	* <b>post</b>: The brand was updated successfully or not. <br>
	* @param brand Is a Brand object that represents a brand of the system. brand!=null.<br>
	* @param newName Is a String variable that contains the new name of a brand. newName!=null and newName!="".<br>
	* @param enabled Is a boolean variable that represents the new state of a brand.<br>
	* @throws IOException <br>
	* 		thrown if...
	* 		1. A local file that was no longer available is being read.<br>
    *       2. Any process closed the stream while a stream is being used to read data.<br>
    *       3. The disk space was no longer available while trying to write to a file.<br>
    * @return a <code> boolean </code> specifying updated, a variable that indicates if the brand was updated successfully or not.  
	*/
	public boolean updateBrand(Brand b, String newName, boolean enabled) throws IOException {
		Brand brand = searchBrand(newName);
		boolean updated=false;
		boolean findBrand = false;
		if(b!=brand) {
			if(brand!=null) {
				findBrand =true;
			}
		}
		if(!findBrand) {
			b.setName(newName);
			b.setEnabled(enabled);
			saveDataAngelaccesorios();
			updated=true;
		}
		return updated;
	}

	/**
	* This method searches for a specific brand within the system's list of products. <br>
	* <b>name</b>: searchBrandInProducts <br>
	* <b>post</b>: True or false was returned depending of the search result. <br>
	* @param b Is a Brand object that represents a brand of the system. brand!=null.<br>
	* @return a <code> boolean </code> specifying found, a variable that indicates if a certain brand is related with a product of the system. 
	*/
	private boolean searchBrandInProducts(Brand b) {
		boolean found = false;
		for(int i=0; i<products.size() && !found;i++) {
			if(products.get(i).getBrand().getName().equals(b.getName())) {
				found = true;
			}
		}
		return found;
	}

	/**
	* This method searches for a specific brand within the system's list of brands. <br>
	* <b>name</b>: searchBrand <br>
	* <b>pre</b>: the variable brandName is already initialized.   <br>
	* <b>post</b>: the searched brand could have been found. <br>
	* @param brandName Is a String variable that contains the name of a brand.  brandName!=null and brandName!="".<br>
	* @return a <code> Brand </code> object specifying b, that might correspond to the searched brand. 
	*/
	public Brand searchBrand(String brandName) {
		boolean found = false;
		Brand b = null;
		for(int i=0; i<brands.size() && !found;i++ ) {
			if(brands.get(i).getName().equalsIgnoreCase(brandName)) {
				b = brands.get(i);
				found = true;						
			}
		}
		return b;
	}

	/**
	* This method returns a list of brands that have their state as "enabled". <br>
	* <b>name</b>: returnEnabledBrands <br>
	* <b>post</b>: A list with the enabled brands of the system was returned. <br>
	* @return an ArrayList of Brand <code> list </code> that contains all the brands that are enabled in the system. 
	*/
	public ArrayList<Brand> returnEnabledBrands(){
		ArrayList<Brand> list = new ArrayList<Brand>();
		for(int k=0; k<brands.size();k++) {
			if(brands.get(k).isEnabled()) {
				list.add(brands.get(k));
			}
		}
		return list;
	}

	public ArrayList<Brand> getBrands() {
		return brands;
	}

	public void setBrands(ArrayList<Brand> brands) {
		this.brands = brands;
	}

	//All related with Supplier

	/**
	* This method adds a supplier to the system.<br>
	* <b>name</b>: addSupplier <br>
	* <b>pre</b>: The variables name and phoneN are already initialized. <br>
	* <b>post</b>: The supplier was added successfully or not. <br>
	*@param name Is a String variable that contains the name of a supplier. name!=null and name!="".<br>
	*@param phoneN Is a String variable that contains the phone number of a supplier. phoneN!=null and phoneN!="".<br>
	* @throws IOException <br>
	* 		thrown if...
	* 		1. A local file that was no longer available is being read.<br>
    *       2. Any process closed the stream while a stream is being used to read data.<br>
    *       3. The disk space was no longer available while trying to write to a file.<br>
    * @return a <code> boolean </code> specifying added, a variable that indicates if the supplier was added successfully or not.
	*/
	public boolean addSupplier(String name, String phoneN) throws IOException {
		Supplier s = searchSupplier(supplierRoot, name);
		boolean added = false;
		if(s==null) {
			s = new Supplier(name, phoneN);
			if(supplierRoot==null) {
				setSupplierRoot(s);
				saveDataAngelaccesorios();
			}else {
				addSupplier(supplierRoot, s);
				saveDataAngelaccesorios();
			}
			added = true;
		}
		return added;
	}

	/**
	* This method adds a supplier to the systems's binary tree of suppliers in case its root has a value assigned to it.<br>
	* <b>name</b>: addSupplier <br>
	* <b>pre</b>: The objects current and newSupplier are already initialized. <br>
	* <b>post</b>: The supplier was added. <br>
	* @param current Is a Supplier object that represents the root of the binary tree of winners. current!=null.<br>
	* @param newWinner Is a Supplier object that represents the new supplier that is going to be added to the binary tree. newSupplier!=null.<br>
	*/
	private void addSupplier(Supplier current, Supplier newSupplier) {
		if(newSupplier.getName().compareTo(current.getName())<0){
			if(current.getRight()==null){
				current.setRight(newSupplier);
				newSupplier.setParent(current);
			}else{
				addSupplier(current.getRight(),newSupplier);
			}
		}else{
			if(current.getLeft()==null){
				current.setLeft(newSupplier);
				newSupplier.setParent(current);
			}else{
				addSupplier(current.getLeft(),newSupplier);
			}	

		}	
	}

	/**
	* This method deletes a supplier of the system.<br>
	* <b>name</b>: deleteSupplier <br>
	* <b>post</b>: The supplier was deleted successfully or not. <br>
	* @param s Is a Supplier object that represents a supplier of the system. s!=null.<br>
	* @throws IOException <br>
	* 		thrown if...
	* 		1. A local file that was no longer available is being read.<br>
    *       2. Any process closed the stream while a stream is being used to read data.<br>
    *       3. The disk space was no longer available while trying to write to a file.<br>
    * @return a <code> boolean </code> specifying deleted, a variable that indicates if the supplier was deleted successfully or not.  
	*/
	public boolean deleteSupplier(Supplier s) throws IOException {
		boolean deleted = false;
		if(!searchSupplierInTypesOfProducts(typePRoot, s)){
			removeSupplier(s);
			deleted = true;
			saveDataAngelaccesorios();
		}
		return deleted;
	}

	/**
	* This method deletes a supplier from the system's binary tree of suppliers.<br>
	* <b>name</b>: removeSupplier <br>
	* <b>pre</b>: The object s is already initialized. <br>
	* <b>post</b>: The supplier was removed from the binary tree. <br>
	* @param s Is a Supplier object that represents a supplier of the system. s!=null.<br>
	*/
	private void removeSupplier(Supplier s) {
		if(s.getLeft()==null && s.getRight()==null){
			if(s==supplierRoot){
				supplierRoot=null;
			}else if(s == s.getParent().getLeft()){
				s.getParent().setLeft(null);

			}else{
				s.getParent().setRight(null);
			}
			s.setParent(null);

		}else if(s.getLeft()==null || s.getRight()==null){
			Supplier onlyChild;
			if(s.getLeft()!=null){
				onlyChild = s.getLeft();
				s.setLeft(null);
			}else{
				onlyChild = s.getRight();
				s.setRight(null);
			}
			onlyChild.setParent(s.getParent());
			if(s==supplierRoot){
				supplierRoot=onlyChild;
			}else if(s==s.getParent().getLeft()){
				s.getParent().setLeft(onlyChild);

			}else{
				s.getParent().setRight(onlyChild);
			}
			s.setParent(null);

		}else{ 
			Supplier successor =min(s.getRight());
			s.setName(successor.getName());
			removeSupplier(successor);	
		}
	}

	/**
	* This method searches the minimum value in the left child of a node.<br>
	* <b>name</b>: min <br>
	* <b>pre</b>: The object current is already initialized. <br>
	* <b>post</b>: The minimum value of the left child of a node was returned. <br>
	* @param current Is a Supplier object that represents a node of the binary tree of suppliers. current!=null.<br>
	*/
	private Supplier min(Supplier current){
		if(current.getLeft()!=null){
			return min(current.getLeft());
		}else{
			return current;
		}
	}

	/**
	* This method updates a supplier from the system's binary tree of suppliers.<br>
	* <b>name</b>: updateSupplier <br>
	* <b>post</b>: The supplier was updated successfully or not. <br>
	* @param s Is a Supplier object that represents a supplier of the system. s!=null.<br>
	* @param newName Is a String variable that contains the new name of a supplier. newName!=null and newName!="".<br>
	* @param newPhone Is a String variable that contains the new phone of a supplier. newPhone!=null and newPhone!="".<br>
	* @throws IOException <br>
	* 		thrown if...
	* 		1. A local file that was no longer available is being read.<br>
    *       2. Any process closed the stream while a stream is being used to read data.<br>
    *       3. The disk space was no longer available while trying to write to a file.<br>
    * @return a <code> boolean </code> specifying updated, a variable that indicates if the supplier was updated successfully or not.  
	*/
	public boolean updateSupplier(Supplier s, String newName, String newPhone) throws IOException {
		Supplier supplier = searchSupplier(supplierRoot, newName);
		boolean updated=false;
		boolean findSupplier = false;
		if(s!=supplier) {
			if(supplier!=null) {
				findSupplier =true;
			}
		}
		if(!findSupplier) {
			s.setName(newName);
			s.setPhoneNumber(newPhone);
			saveDataAngelaccesorios();
			updated=true;
		}
		return updated;
	}

	/**
	* This method searches for a specific supplier within the system's binary tree of suppliers. <br>
	* <b>name</b>: searchSupplier <br>
	* <b>pre</b>: the object current and the variable name are already initialized. <br>
	* <b>post</b>: the searched supplier could have been found. <br>
	* @param current Is a Supplier object that represents the root of the binary tree of suppliers. current!=null.<br>
	* @param name Is a String variable that contains the name of a supplier. name!=null and name!="".<br>
	* @return a <code> Supplier </code> object that might correspond to the searched supplier. 
	*/
	public Supplier searchSupplier(Supplier current, String name) {
		if(current==null || current.getName().equalsIgnoreCase(name)) {
			return current;
		}else {
			if(current.getName().compareTo(name)<0) {
				return searchSupplier(current.getLeft(), name);
			}else {
				return searchSupplier(current.getRight(), name);
			}
		}
	}

	/**
	* This method searches for a specific supplier within the system's binary tree of types of products. <br>
	* <b>name</b>: searchSupplierInTypesOfProducts <br>
	* <b>post</b>: True or false was returned depending of the search result. <br>
	* @param current Is a TypeOfProduct object that represents the root of the binary tree of types of products. current!=null.<br>
	* @param s Is a Supplier object that represents a supplier of the system. s!=null.<br>
	* @return a <code> boolean </code> specifying found, a variable that indicates if a certain supplier is related with a type of product of the system. 
	*/
	private boolean searchSupplierInTypesOfProducts(TypeOfProduct current, Supplier s) {
		boolean found = false;
		if(current!=null && current instanceof ElectronicEquipment) {
			if(((ElectronicEquipment)current).findSupplier(s.getName())==true) {
				found = true;
				return found;
			}else {
				if(current.getLeft()!=null && current.getName().compareTo(current.getLeft().getName())<0) {
					return searchSupplierInTypesOfProducts(current.getLeft(), s);	
				}else if(current.getRight()!=null){
					return searchSupplierInTypesOfProducts(current.getRight(), s);
				}else {
					return found;
				}
			}
		}else if(current==null){
			return found;
		}else{
			if(current.getLeft()!=null && current.getName().compareTo(current.getLeft().getName())<0) {
				return searchSupplierInTypesOfProducts(current.getLeft(), s);	
			}else if(current.getRight()!=null){
				return searchSupplierInTypesOfProducts(current.getRight(), s);
			}else {
				return found;
			}
		}
	}

	public Supplier getSupplierRoot() {
		return supplierRoot;
	}

	public void setSupplierRoot(Supplier supplierRoot) {
		this.supplierRoot = supplierRoot;
	}

	//All related with TypeOfProduct

	/**
	* This method adds a type of product to the system.<br>
	* <b>name</b>: addTypeOfProduct <br>
	* <b>pre</b>: The variables name and category are already initialized. <br>
	* <b>post</b>: The type of product was added successfully or not. <br>
	*@param name Is a String variable that contains the name of a type of product. name!=null and name!="".<br>
	*@param category Is a String variable that contains the category of a type of product (accessory or electronic equipment). category!=null and category!="".<br>
	* @throws IOException <br>
	* 		thrown if...
	* 		1. A local file that was no longer available is being read.<br>
    *       2. Any process closed the stream while a stream is being used to read data.<br>
    *       3. The disk space was no longer available while trying to write to a file.<br>
    * @return a <code> boolean </code> specifying added, a variable that indicates if the type of product was added successfully or not.
	*/
	public boolean addTypeOfProduct(String name, String category) throws IOException {
		TypeOfProduct ty = searchTypeOfProduct(typePRoot, name);
		boolean added = false;
		if(ty==null) {
			if(category.equals("Accesorio")) {
				ty = new Accessory(name);
			}else {
				ty = new ElectronicEquipment(name);
			}
			if(typePRoot==null) {
				setTypePRoot(ty);
				saveDataAngelaccesorios();
			}else {
				addTypeOfProduct(typePRoot, ty);
				saveDataAngelaccesorios();
			}
			added = true;
		}
		return added;
	}

	/**
	* This method adds a type of product to the systems's binary tree of types of products in case its root has a value assigned to it.<br>
	* <b>name</b>: addTypeOfProduct <br>
	* <b>pre</b>: The objects current and newType are already initialized. <br>
	* <b>post</b>: The type of product was added. <br>
	* @param current Is a TypeOfProduct object that represents the root of the binary tree of types of products. current!=null.<br>
	* @param newType Is a TypeOfProduct object that represents the new type of product that is going to be added to the binary tree. newType!=null.<br>
	*/
	private void addTypeOfProduct(TypeOfProduct current, TypeOfProduct newType) {
		if(newType.getName().compareTo(current.getName())<0){
			if(current.getRight()==null){
				current.setRight(newType);
				newType.setParent(current);
			}else{
				addTypeOfProduct(current.getRight(),newType);
			}
		}else{
			if(current.getLeft()==null){
				current.setLeft(newType);
				newType.setParent(current);
			}else{
				addTypeOfProduct(current.getLeft(),newType);
			}	
		}	
	}

	/**
	* This method adds a supplier to an ElectronicEquipment type of product of the system.<br>
	* <b>name</b>: addSupplierToEQE <br>
	* <b>pre</b>: The objects tp and sp are already initialized. <br>
	* <b>post</b>: The supplier was added successfully or not to an ElectronicEquipment type of product. <br>
	*@param tp Is an ElectronicEquipment object that represents a system's type of product. tp!=null.<br>
	*@param sp Is a Supplier object that represents a supplier of the system. sp!=null.<br>
	* @throws IOException <br>
	* 		thrown if...
	* 		1. A local file that was no longer available is being read.<br>
    *       2. Any process closed the stream while a stream is being used to read data.<br>
    *       3. The disk space was no longer available while trying to write to a file.<br>
    * @return a <code> boolean </code> specifying added, a variable that indicates if the supplier was added successfully or not to a type of product.
	*/
	public boolean addSupplierToEQE(ElectronicEquipment tp, Supplier sp) throws IOException{
		boolean find = tp.findSupplier(sp.getName());
		boolean added = false;
		if(find==false) {
			tp.getSuppliers().add(sp);
			added = true;
			saveDataAngelaccesorios();
		}
		return added;
	}

	/**
	* This method deletes a supplier of an ElectronicEquipment type of product.<br>
	* <b>name</b>: deleteSupplierOfAnEQE <br>
	* <b>pre</b>: The objects tp and sp are already initialized. <br>
	* <b>post</b>: The supplier was deleted successfully or not of an ElectronicEquipment type of product. <br>
	*@param tp Is an ElectronicEquipment object that represents a system's type of product. tp!=null.<br>
	*@param sp Is a Supplier object that represents a supplier of the system. sp!=null.<br>
	* @throws IOException <br>
	* 		thrown if...
	* 		1. A local file that was no longer available is being read.<br>
    *       2. Any process closed the stream while a stream is being used to read data.<br>
    *       3. The disk space was no longer available while trying to write to a file.<br>
	*/
	public void deleteSupplierOfAnEQE(ElectronicEquipment tp, Supplier sp) throws IOException {
		tp.getSuppliers().remove(tp.getSuppliers().indexOf(sp));
		saveDataAngelaccesorios();
	}

	/**
	* This method deletes a type of product of the system.<br>
	* <b>name</b>: deleteTypeOfProduct <br>
	* <b>post</b>: The type of product was deleted successfully or not. <br>
	* @param type Is a TypeOfProduct object that represents a type of product of the system. type!=null.<br>
	* @throws IOException <br>
	* 		thrown if...
	* 		1. A local file that was no longer available is being read.<br>
    *       2. Any process closed the stream while a stream is being used to read data.<br>
    *       3. The disk space was no longer available while trying to write to a file.<br>
    * @return a <code> boolean </code> specifying deleted, a variable that indicates if the type of product was deleted successfully or not.  
	*/
	public boolean deleteTypeOfProduct(TypeOfProduct type) throws IOException {
		boolean deleted = false;
		boolean found = false;
		for(int i=0; i<products.size() && !found;i++) {
			if(products.get(i).getType().getName().equals(type.getName())) {
				found = true;
			}
		}
		if(found==false){
			removeType(type);
			deleted = true;
			saveDataAngelaccesorios();
		}
		return deleted;
	}

	/**
	* This method deletes a type of product from the system's binary tree of types of products.<br>
	* <b>name</b>: removeType <br>
	* <b>pre</b>: The object t is already initialized. <br>
	* <b>post</b>: The type of product was removed from the binary tree. <br>
	* @param t Is a TypeOfProduct object that represents a system's type of product. t!=null.<br>
	*/
	private void removeType(TypeOfProduct t) {
		if(t.getLeft()==null && t.getRight()==null){
			if(t==typePRoot){
				typePRoot=null;
			}else if(t == t.getParent().getLeft()){
				t.getParent().setLeft(null);

			}else{
				t.getParent().setRight(null);
			}
			t.setParent(null);

		}else if(t.getLeft()==null || t.getRight()==null){
			TypeOfProduct onlyChild;
			if(t.getLeft()!=null){
				onlyChild = t.getLeft();
				t.setLeft(null);
			}else{
				onlyChild = t.getRight();
				t.setRight(null);
			}
			onlyChild.setParent(t.getParent());
			if(t==typePRoot){
				typePRoot=onlyChild;
			}else if(t==t.getParent().getLeft()){
				t.getParent().setLeft(onlyChild);

			}else{
				t.getParent().setRight(onlyChild);
			}
			t.setParent(null);

		}else{ 
			TypeOfProduct successor =min(t.getRight());
			t.setName(successor.getName());
			removeType(successor);	
		}
	}

	/**
	* This method searches the minimum value in the left child of a node.<br>
	* <b>name</b>: min <br>
	* <b>pre</b>: The object current is already initialized. <br>
	* <b>post</b>: The minimum value of the left child of a node was returned. <br>
	* @param current Is a TypeOfProduct object that represents a node of the binary tree of types of products. current!=null.<br>
	*/
	private TypeOfProduct min(TypeOfProduct current){
		if(current.getLeft()!=null){
			return min(current.getLeft());
		}else{
			return current;
		}
	}

	/**
	* This method updates a type of product from the system's binary tree of types of products.<br>
	* <b>name</b>: updateTypeOfProduct <br>
	* <b>post</b>: The type of product was updated successfully or not. <br>
	* @param ty Is a TypeOfProduct object that represents a type of product of the system. ty!=null.<br>
	* @param newName Is a String variable that contains the new name of a type of product. newName!=null and newName!="".<br>
	* @param enabled Is a boolean variable that represents the new state of a type of product.<br>
	* @throws IOException <br>
	* 		thrown if...
	* 		1. A local file that was no longer available is being read.<br>
    *       2. Any process closed the stream while a stream is being used to read data.<br>
    *       3. The disk space was no longer available while trying to write to a file.<br>
    * @return a <code> boolean </code> specifying updated, a variable that indicates if the type of product was updated successfully or not.  
	*/
	public boolean updateTypeOfProduct(TypeOfProduct ty, String newName, boolean enabled) throws IOException {
		TypeOfProduct type = searchTypeOfProduct(typePRoot, newName);
		boolean updated=false;
		boolean findType = false;
		if(ty!= type) {
			if(type!=null) {
				findType =true;
			}
		}
		if(!findType) {
			ty.setName(newName);
			ty.setEnabled(enabled);
			saveDataAngelaccesorios();
			updated=true;
		}
		return updated;
	}

	/**
	* This method searches for a specific type of product within the system's binary tree of types of products. <br>
	* <b>name</b>: searchTypeOfProduct <br>
	* <b>pre</b>: the object current and the variable name are already initialized. <br>
	* <b>post</b>: the searched type of product could have been found. <br>
	* @param current Is a TypeOfProduct object that represents the root of the binary tree of types of products. current!=null.<br>
	* @param name Is a String variable that contains the name of a type of product. name!=null and name!="".<br>
	* @return a <code> TypeOfProduct </code> object that might correspond to the searched type of product. 
	*/
	public TypeOfProduct searchTypeOfProduct(TypeOfProduct current, String name) {
		if(current==null || current.getName().equalsIgnoreCase(name)) {
			return current;
		}else {
			if(current.getName().compareTo(name)<0) {
				return searchTypeOfProduct(current.getLeft(), name);
			}else {
				return searchTypeOfProduct(current.getRight(), name);
			}
		}
	}

	public TypeOfProduct getTypePRoot() {
		return typePRoot;
	}

	public void setTypePRoot(TypeOfProduct typePRoot) {
		this.typePRoot = typePRoot;
	}

	//All related with Product

	/**
	* This method adds a product to the system's list of products.<br>
	* <b>name</b>: addProduct <br>
	* <b>pre</b>: The objects type, brand, and the variables such as model, units, price and guarantee are already initialized. <br>
	* <b>post</b>: The product was added successfully or not. <br>
	*@param type Is a TypeOfProduct object that represents a type of product of the system. type!=null.<br>
	*@param b Is a Brand object that represents a brand of the system. b!=null.<br>
	*@param units Is an integer variable that contains the number of available units of a product.<br>
	*@param guarantee Is a boolean variable that indicates if a product has guarantee or not.<br>
	*@param model Is a String variable that contains the model of a product. model!=null and model!="".<br>
	*@param price Is a double variable that contains the price of a product.<br>
	* @throws IOException <br>
	* 		thrown if...
	* 		1. A local file that was no longer available is being read.<br>
    *       2. Any process closed the stream while a stream is being used to read data.<br>
    *       3. The disk space was no longer available while trying to write to a file.<br>
    * @throws NoQuantityException <br>
    *		thrown if units==0. <br> 
    * @throws NegativeQuantityException <br>
    *		thrown if units<0. <br> 
    * @throws NoPriceException <br>
    *		thrown if price==0. <br> 
    * @throws NegativePriceException <br>
    *		thrown if price<0. <br> 
    * @throws SameProductException <br>
    *		thrown if there is another product in the system with the same type, brand and model entered. <br> 
	*/
	public void addProduct(TypeOfProduct type, Brand b, String model, int units, double price, boolean guarantee) throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		Product p = searchProduct(type, b, model);
		if(p==null && units>0 && price>0) {
			boolean found = false;
			String code;
			do {
				int num = ThreadLocalRandom.current().nextInt(1000, 10000);
				code = "";
				if(type instanceof ElectronicEquipment) {
					code = ((ElectronicEquipment)type).getCode()+num;
				}else {
					code = ((Accessory)type).getCode()+num;
				}
				boolean stop = false;
				for(int i=0; i<products.size() && !stop;i++ ) {
					if(products.get(i).getCode().equals(code)) {
						found = true;
						stop = true;
					}
				}
			}while(found);
			p = new Product(type, b, units, guarantee, model, price, code);
			products.add(p);
			saveDataAngelaccesorios();
		}else {
			if(p!=null) {
				throw new SameProductException();
			}
			if(units==0) {
				throw new NoQuantityException(units); 
			}
			if(units<0) {
				throw new NegativeQuantityException(units);
			}
			if(price==0) {
				throw new NoPriceException(price);
			}
			if(price<0) {
				throw new NegativePriceException(price);
			}
		}
	}

	/**
	* This method deletes a product of the system.<br>
	* <b>name</b>: deleteProduct <br>
	* <b>post</b>: The product was deleted successfully or not. <br>
	* @param p Is a Product object that represents product of the system. p!=null.<br>
	* @throws IOException <br>
	* 		thrown if...
	* 		1. A local file that was no longer available is being read.<br>
    *       2. Any process closed the stream while a stream is being used to read data.<br>
    *       3. The disk space was no longer available while trying to write to a file.<br>
    * @return a <code> boolean </code> specifying deleted, a variable that indicates if the product was deleted successfully or not.  
	*/
	public boolean deleteProduct(Product p) throws IOException {
		boolean deleted = false; 
		boolean find = false;
		for(int k=0; k<receipts.size() && !find;k++) {
			if(receipts.get(k) instanceof SeparateReceipt && ((SeparateReceipt)receipts.get(k)).getState().equals(State.NO_ENTREGADO)) {
				find = receipts.get(k).findProduct(p.getCode());	
			}
		}
		if(!find){
			products.remove(products.indexOf(p));
			deleted = true;
			saveDataAngelaccesorios();
		}
		return deleted;
	}

	/**
	* This method updates a product of the system's list of products.<br>
	* <b>name</b>: updateProduct <br>
	* <b>pre</b>: The objects p, brand, and the variables such as model, units, price, guarantee and enabled are already initialized. <br>
	* <b>post</b>: The product was updated successfully or not. <br>
	*@param p Is a Product object that represents a product of the system. p!=null.<br>
	*@param b Is a Brand object that represents a brand of the system. b!=null.<br>
	*@param units Is an integer variable that contains the number of available units of a product.<br>
	*@param guarantee Is a boolean variable that indicates if a product has guarantee or not.<br>
	*@param model Is a String variable that contains the model of a product. model!=null and model!="".<br>
	*@param price Is a double variable that contains the price of a product.<br>
	*@param enabled Is a boolean variable that represents the state of a product.<br>
	* @throws IOException <br>
	* 		thrown if...
	* 		1. A local file that was no longer available is being read.<br>
    *       2. Any process closed the stream while a stream is being used to read data.<br>
    *       3. The disk space was no longer available while trying to write to a file.<br>
    * @throws NoQuantityException <br>
    *		thrown if units==0. <br> 
    * @throws NegativeQuantityException <br>
    *		thrown if units<0. <br> 
    * @throws NoPriceException <br>
    *		thrown if price==0. <br> 
    * @throws NegativePriceException <br>
    *		thrown if price<0. <br> 
    * @throws SameProductException <br>
    *		thrown if there is another product in the system with the same type, brand and model entered. <br> 
	*/
	public void updateProduct(Product p, Brand b, String model, int units, double price, boolean guarantee, boolean enabled) throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		Product product = searchProduct(p.getType(), b, model);
		boolean findProduct = false;
		if(p!= product) {
			if(product!=null) {
				findProduct =true;
				throw new SameProductException();
			}
		}
		if(!findProduct && units>0 && price>0) {
			p.setBrand(b);
			p.setGuarantee(guarantee);
			p.setModel(model);
			p.setPrice(price);
			p.setUnits(units);
			p.setEnabled(enabled);
			saveDataAngelaccesorios();
		}else {
			if(units==0) {
				throw new NoQuantityException(units); 
			}
			if(units<0) {
				throw new NegativeQuantityException(units);
			}
			if(price==0) {
				throw new NoPriceException(price);
			}
			if(price<0) {
				throw new NegativePriceException(price);
			}
		}
	}

	/**
	* This method searches for a specific product within the system's list of products. <br>
	* <b>name</b>: searchProduct <br>
	* <b>pre</b>: the objects ty, brand and the variable model are already initialized. <br>
	* <b>post</b>: the searched product could have been found. <br>
	* @param ty Is a TypeOfProduct object that represents a type of product of the system. ty!=null.<br>
	* @param b Is a Brand object that represents a brand of the system. b!=null.<br>
	* @param model Is a String variable that contains the model of a product. model!=null and model!="".<br>
	* @return a <code> Product </code> object specifying p, that might correspond to the searched product. 
	*/
	public Product searchProduct(TypeOfProduct ty, Brand b, String model) {
		boolean found = false;
		Product p = null;
		for(int i=0; i<products.size() && !found;i++ ) {
			if(products.get(i).getType().getName().equals(ty.getName()) && products.get(i).getBrand().getName().equals(b.getName()) && products.get(i).getModel().equalsIgnoreCase(model)) {
				p = products.get(i);
				found = true;						
			}
		}
		return p;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	//Searches products in the list of products of the system from a type and brand name
	public ArrayList<Product> returnFoundProducts(String type, String brand){
		ArrayList<Product> found = new ArrayList<Product>();
		for(int k = 0; k<products.size() ; k++) {
			if(products.get(k).getType().getName().equalsIgnoreCase(type) && products.get(k).getBrand().getName().equalsIgnoreCase(brand)) {
				found.add(products.get(k));
			}
		}
		return found;
	}

	//Serializable Methods

	public void saveDataAngelaccesorios() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ANGELACCESORIOS_SAVE_PATH_FILE));
		oos.writeObject(this);
		oos.close();
	}

	public Angelaccesorios loadDataAngelaccesorios(Angelaccesorios ang) throws IOException, ClassNotFoundException{
		File f = new File(ANGELACCESORIOS_SAVE_PATH_FILE);
		if(f.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			ang = (Angelaccesorios)ois.readObject();
			ois.close();

		}
		return ang;
	}

	//All related with the reports of the system

	public List<String> getHours(){
		List<String> allHours = new ArrayList<String>();
		String[] hours = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
		allHours = Arrays.asList(hours);
		return allHours;
	}

	public List<String> getMinutes(){
		List<String> allMinutes = new ArrayList<String>();
		String[] minutes = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
		allMinutes = Arrays.asList(minutes);
		return allMinutes;
	}

	public ArrayList<Receipt> selectGeneratedReceipts(Date initialDate, Date finalDate, int numReport) throws ParseException{
		int result1 = 0;
		int result2 = 0;
		ArrayList<Receipt> selectedReceipts = new ArrayList<Receipt>();
		ArrayList<Receipt> sortingReceipts = new ArrayList<Receipt>(receipts);
		Collections.sort(sortingReceipts);
		for(int k=0; k<sortingReceipts.size();k++) {
			Receipt r = sortingReceipts.get(k);
			result1 = r.getDateAndTime().compareTo(initialDate);
			result2 = r.getDateAndTime().compareTo(finalDate);
			if((result1>0 || result1==0)&&(result2<0||result2==0)) {
				if(numReport==2) {
					if(!(r instanceof SeparateReceipt)){
						for(int j=0;j<r.getListOfProducts().size();j++) {
							Product p = r.getListOfProducts().get(j);
							p.setNumTimesAddedOrders((p.getNumTimesAddedOrders())+(r.getListOfQuantity().get(j)));
							p.setTotalPriceAddedOrders((p.getTotalPriceAddedOrders())+(r.getListOfQuantity().get(j)*p.getPrice()));
						}
					}	
				}
				selectedReceipts.add(r);
			}
		}
		return selectedReceipts;
	}

	public void exportUsersReport(String fn, String initialTime, String finalTime) throws FileNotFoundException, ParseException, HigherDateAndHourException {
		String strFormat = "yyyy-MM-dd HH:mm";
		SimpleDateFormat formato = new SimpleDateFormat(strFormat);
		Date date1 = formato.parse(initialTime);
		Date date2 = formato.parse(finalTime);
		if(date1.after(date2)) {
			throw new HigherDateAndHourException();
		}else {
			int totalReceipts=0;
			int totalMoney=0;
			ArrayList<Receipt> receiptsS = selectGeneratedReceipts(date1,date2, 1);
			PrintWriter pw = new PrintWriter(fn);
			String nameColumns = "Usuario"+SEPARATOR+"Identificacion"+SEPARATOR+"Número de facturas generadas"+SEPARATOR+"Valor total de las facturas generadas";
			pw.println(nameColumns);
			for(int k=0;k<receiptsS.size();k++) {
				receiptsS.get(k).getCreator().setCont(0);
			}
			for(int i=0;i<receiptsS.size();i++){
				Receipt objR = receiptsS.get(i);
				objR.getCreator().setCont((objR.getCreator().getCont())+1);
				if(objR.getCreator().getCont()==1) {
					pw.println(objR.getCreator().getName()+SEPARATOR+objR.getCreator().getId()+SEPARATOR+objR.getCreator().getNumberReceipts()+SEPARATOR+objR.getCreator().getSumTotalReceipts());
					totalReceipts+=objR.getCreator().getNumberReceipts();
					totalMoney+=objR.getCreator().getSumTotalReceipts();
				}
			}
			pw.println(SEPARATOR+"Total"+SEPARATOR+totalReceipts+SEPARATOR+totalMoney);
			pw.close();
		}
	}

	public void exportProductsReport(String fn, String initialTime, String finalTime) throws FileNotFoundException, ParseException, HigherDateAndHourException {
		String strFormat = "yyyy-MM-dd HH:mm";
		SimpleDateFormat formato = new SimpleDateFormat(strFormat);
		Date date1 = formato.parse(initialTime);
		Date date2 = formato.parse(finalTime);
		if(date1.after(date2)) {
			throw new HigherDateAndHourException();
		}else {
			int totalOrders=0;
			int totalMoney=0;
			ArrayList<Receipt> receiptsS = selectGeneratedReceipts(date1,date2, 2);
			PrintWriter pw = new PrintWriter(fn);
			String nameColumns = "Tipo de producto"+SEPARATOR+"Marca"+SEPARATOR+"Modelo"+SEPARATOR+"Numero total de veces que fue incluido en una factura"+SEPARATOR+"Cantidad de total de dinero recaudado";
			pw.println(nameColumns);
			for(int i=0;i<receiptsS.size();i++){
				Receipt objR = receiptsS.get(i);
				for(int k=0;k<objR.getListOfProducts().size();k++) {
					Product pd = objR.getListOfProducts().get(k);
					pd.setCont((pd.getCont())+1);
					if(pd.getCont()==1) {
						pw.println(pd.getTypeName()+SEPARATOR+pd.getBrandName()+SEPARATOR+pd.getModel()+SEPARATOR+pd.getNumTimesAddedOrders()+SEPARATOR+pd.getTotalPriceAddedOrders());
						totalOrders+=pd.getNumTimesAddedOrders();
						totalMoney+=pd.getTotalPriceAddedOrders();
					}
				}
			}
			pw.println(""+SEPARATOR+""+SEPARATOR+"Total"+SEPARATOR+totalOrders+SEPARATOR+totalMoney);
			pw.close();
			for(int j=0;j<receiptsS.size();j++) {
				Receipt r = receiptsS.get(j);
				for(int k=0;k<r.getListOfProducts().size();k++) {
					Product p = r.getListOfProducts().get(k);
					p.setNumTimesAddedOrders(0);
					p.setTotalPriceAddedOrders(0);
					p.setCont(0);
				}
			}	
		}
	}

	public void generatePDFCountedReceipt(OutputStream txt, Receipt r, String type) throws DocumentException {
		Document doc = new Document(PageSize.LETTER);
		PdfWriter.getInstance(doc, txt);
		doc.open();
		Font negrilla = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
		Font normal = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
		PdfPTable tbl_client = new PdfPTable(2);
		Paragraph texto = null;
		PdfPCell celda = null;

		texto = new Paragraph(type, negrilla);
		texto.setAlignment(Element.ALIGN_CENTER);
		texto.add(new Phrase(Chunk.NEWLINE));
		texto.add(new Phrase(Chunk.NEWLINE));
		doc.add(texto);

		texto = new Paragraph("Fecha: ", negrilla);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		tbl_client.addCell(celda);

		texto = new Paragraph(r.getDateAndHour(), normal);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		celda.setColspan(3);
		tbl_client.addCell(celda);

		texto = new Paragraph("Cliente: ", negrilla);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		tbl_client.addCell(celda);

		texto = new Paragraph(r.getBuyer().getNameAndLastName(), normal);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		celda.setColspan(3);
		tbl_client.addCell(celda);

		texto = new Paragraph("Documento de identidad: ", negrilla);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		tbl_client.addCell(celda);

		texto = new Paragraph(r.getBuyer().getTypeId().name()+". "+r.getBuyer().getId(), normal);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		celda.setColspan(3);
		tbl_client.addCell(celda);

		texto = new Paragraph("Dirección: ", negrilla);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		tbl_client.addCell(celda);

		texto = new Paragraph(r.getBuyer().getAddress(), normal);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		celda.setColspan(3);
		tbl_client.addCell(celda);

		texto = new Paragraph("Telefono: ", negrilla);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		tbl_client.addCell(celda);

		texto = new Paragraph(r.getBuyer().getPhone(), normal);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		tbl_client.addCell(celda);

		texto = new Paragraph("Medio de pago: ", negrilla);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		tbl_client.addCell(celda);

		texto = new Paragraph(r.getPaymentMString(), normal);
		texto.add(new Phrase(Chunk.NEWLINE));
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		tbl_client.addCell(celda);

		doc.add(tbl_client);
		texto = new Paragraph();
		texto.add(new Phrase(Chunk.NEWLINE));
		texto.add(new Phrase(Chunk.NEWLINE));
		doc.add(texto);

		texto = new Paragraph("Listado de productos", negrilla);
		texto.setAlignment(Element.ALIGN_CENTER);
		texto.add(new Phrase(Chunk.NEWLINE));
		texto.add(new Phrase(Chunk.NEWLINE));
		doc.add(texto);

		PdfPTable tbl_products = new PdfPTable(5);
		tbl_products.setWidths(new int[]{1, 1, 2, 1, 1});
		tbl_products.addCell(createCell("Codigo", negrilla, 2, 1, Element.ALIGN_LEFT));
		tbl_products.addCell(createCell("Cantidad", negrilla, 2, 1, Element.ALIGN_LEFT));
		tbl_products.addCell(createCell("Descripcion", negrilla, 2, 1, Element.ALIGN_LEFT));
		tbl_products.addCell(createCell("Valor unitario", negrilla, 2, 1, Element.ALIGN_LEFT));
		tbl_products.addCell(createCell("Importe", negrilla, 2, 1, Element.ALIGN_LEFT));
		String[][] data = new String[r.getListOfProducts().size()][5];
		for (int x=0; x < data.length; x++) {
			Product p = r.getListOfProducts().get(x);
			for (int y=0; y < data[x].length; y++) {
				if(y==0) {
					data[x][y] = p.getCode();
				}else if(y==1) {
					data[x][y] = ""+r.getListOfQuantity().get(x);
				}else if(y==2) {
					data[x][y] = p.getTypeName()+" "+p.getBrandName()+" "+p.getModel();
				}else if(y==3) {
					data[x][y] = ""+p.getPrice();
				}else {
					data[x][y] = ""+(p.getPrice()*r.getListOfQuantity().get(x));
				}
			}
		}
		for (String[] row : data) {
			tbl_products.addCell(createCell(row[0], normal, 1, 1, Element.ALIGN_LEFT));
			tbl_products.addCell(createCell(row[1], normal, 1, 1, Element.ALIGN_LEFT));
			tbl_products.addCell(createCell(row[2], normal, 1, 1, Element.ALIGN_RIGHT));
			tbl_products.addCell(createCell(row[3], normal, 1, 1, Element.ALIGN_RIGHT));
			tbl_products.addCell(createCell(row[4], normal, 1, 1, Element.ALIGN_RIGHT));
		}
		tbl_products.addCell(createCell("Subtotal", negrilla, 2, 4, Element.ALIGN_LEFT));
		tbl_products.addCell(createCell(""+r.getSubtotal(), normal, 2, 1, Element.ALIGN_RIGHT));
		tbl_products.addCell(createCell("IVA", negrilla, 2, 4, Element.ALIGN_LEFT));
		tbl_products.addCell(createCell(""+r.getIVA(), normal, 2, 1, Element.ALIGN_RIGHT));
		tbl_products.addCell(createCell("Total", negrilla, 2, 4, Element.ALIGN_LEFT));
		tbl_products.addCell(createCell(""+r.getTotal(), normal, 2, 1, Element.ALIGN_RIGHT));
		tbl_products.addCell(createCell("Entregado por: "+r.getCreator().getName()+" "+r.getCreator().getLastName(), normal, 1, 5, Element.ALIGN_LEFT));
		tbl_products.addCell(createCell("Observaciones:\n"+r.getObservations(), normal, 1, 5, Element.ALIGN_LEFT));
		doc.add(tbl_products);
		doc.close();
	}

	public void generatePDFSeparateReceipt(OutputStream txt, Receipt r, String type) throws DocumentException {
		Document doc = new Document(PageSize.LETTER);
		PdfWriter.getInstance(doc, txt);
		doc.open();
		Font negrilla = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
		Font normal = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
		PdfPTable tbl_client = new PdfPTable(2);
		Paragraph texto = null;
		PdfPCell celda = null;

		texto = new Paragraph(type, negrilla);
		texto.setAlignment(Element.ALIGN_CENTER);
		texto.add(new Phrase(Chunk.NEWLINE));
		texto.add(new Phrase(Chunk.NEWLINE));
		doc.add(texto);

		texto = new Paragraph("Cliente: ", negrilla);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		tbl_client.addCell(celda);

		texto = new Paragraph(r.getBuyer().getNameAndLastName(), normal);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		celda.setColspan(3);
		tbl_client.addCell(celda);

		texto = new Paragraph("Documento de identidad: ", negrilla);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		tbl_client.addCell(celda);

		texto = new Paragraph(r.getBuyer().getTypeId().name()+". "+r.getBuyer().getId(), normal);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		celda.setColspan(3);
		tbl_client.addCell(celda);

		texto = new Paragraph("Dirección: ", negrilla);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		tbl_client.addCell(celda);

		texto = new Paragraph(r.getBuyer().getAddress(), normal);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		celda.setColspan(3);
		tbl_client.addCell(celda);

		texto = new Paragraph("Telefono: ", negrilla);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		tbl_client.addCell(celda);

		texto = new Paragraph(r.getBuyer().getPhone(), normal);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		tbl_client.addCell(celda);

		texto = new Paragraph("Estado de la factura: ", negrilla);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		tbl_client.addCell(celda);

		texto = new Paragraph(((SeparateReceipt)r).getStateString(), normal);
		celda = new PdfPCell(texto);
		celda.setBorder(Rectangle.NO_BORDER);
		tbl_client.addCell(celda);

		doc.add(tbl_client);
		texto = new Paragraph();
		texto.add(new Phrase(Chunk.NEWLINE));
		texto.add(new Phrase(Chunk.NEWLINE));
		doc.add(texto);

		texto = new Paragraph("Listado de Abonos", negrilla);
		texto.setAlignment(Element.ALIGN_CENTER);
		texto.add(new Phrase(Chunk.NEWLINE));
		texto.add(new Phrase(Chunk.NEWLINE));
		doc.add(texto);

		PdfPTable tbl_payments = new PdfPTable(4);
		tbl_payments.setWidths(new int[]{2, 1, 2, 2});
		tbl_payments.addCell(createCell("Fecha", negrilla, 2, 1, Element.ALIGN_LEFT));
		tbl_payments.addCell(createCell("Valor", negrilla, 2, 1, Element.ALIGN_LEFT));
		tbl_payments.addCell(createCell("Medio de pago", negrilla, 2, 1, Element.ALIGN_LEFT));
		tbl_payments.addCell(createCell("Recibido por", negrilla, 2, 1, Element.ALIGN_LEFT));
		String[][] dataPay = new String[((SeparateReceipt)r).getNumPayments()][4];
		System.out.println(((SeparateReceipt)r).getNumPayments());
		Payment py = ((SeparateReceipt)r).getFirstPayment();
		for (int x=0; x < dataPay.length; x++) {
			for (int y=0; y < dataPay[x].length && py!=null; y++) {
				if(y==0) {
					dataPay[x][y] = py.getDateAndHour();
				}else if(y==1) {
					dataPay[x][y] = ""+py.getAmount();
				}else if(y==2) {
					dataPay[x][y] = py.getPaymentMString();
				}else {
					dataPay[x][y] = py.getCreator().getName()+" "+py.getCreator().getLastName();
				}
			}
			py = py.getNext();
		}
		for (String[] row : dataPay) {
			tbl_payments.addCell(createCell(row[0], normal, 1, 1, Element.ALIGN_LEFT));
			tbl_payments.addCell(createCell(row[1], normal, 1, 1, Element.ALIGN_LEFT));
			tbl_payments.addCell(createCell(row[2], normal, 1, 1, Element.ALIGN_RIGHT));
			tbl_payments.addCell(createCell(row[3], normal, 1, 1, Element.ALIGN_RIGHT));
		}
		doc.add(tbl_payments);

		texto = new Paragraph();
		texto.add(new Phrase(Chunk.NEWLINE));
		texto.add(new Phrase(Chunk.NEWLINE));
		doc.add(texto);

		texto = new Paragraph("Listado de productos", negrilla);
		texto.setAlignment(Element.ALIGN_CENTER);
		texto.add(new Phrase(Chunk.NEWLINE));
		texto.add(new Phrase(Chunk.NEWLINE));
		doc.add(texto);

		PdfPTable tbl_products = new PdfPTable(5);
		tbl_products.setWidths(new int[]{1, 1, 2, 1, 1});
		tbl_products.addCell(createCell("Codigo", negrilla, 2, 1, Element.ALIGN_LEFT));
		tbl_products.addCell(createCell("Cantidad", negrilla, 2, 1, Element.ALIGN_LEFT));
		tbl_products.addCell(createCell("Descripcion", negrilla, 2, 1, Element.ALIGN_LEFT));
		tbl_products.addCell(createCell("Valor unitario", negrilla, 2, 1, Element.ALIGN_LEFT));
		tbl_products.addCell(createCell("Importe", negrilla, 2, 1, Element.ALIGN_LEFT));
		String[][] data = new String[r.getListOfProducts().size()][5];
		for (int x=0; x < data.length; x++) {
			Product p = r.getListOfProducts().get(x);
			for (int y=0; y < data[x].length; y++) {
				if(y==0) {
					data[x][y] = p.getCode();
				}else if(y==1) {
					data[x][y] = ""+r.getListOfQuantity().get(x);
				}else if(y==2) {
					data[x][y] = p.getTypeName()+" "+p.getBrandName()+" "+p.getModel();
				}else if(y==3) {
					data[x][y] = ""+p.getPrice();
				}else {
					data[x][y] = ""+(p.getPrice()*r.getListOfQuantity().get(x));
				}
			}
		}
		for (String[] row : data) {
			tbl_products.addCell(createCell(row[0], normal, 1, 1, Element.ALIGN_LEFT));
			tbl_products.addCell(createCell(row[1], normal, 1, 1, Element.ALIGN_LEFT));
			tbl_products.addCell(createCell(row[2], normal, 1, 1, Element.ALIGN_RIGHT));
			tbl_products.addCell(createCell(row[3], normal, 1, 1, Element.ALIGN_RIGHT));
			tbl_products.addCell(createCell(row[4], normal, 1, 1, Element.ALIGN_RIGHT));
		}
		tbl_products.addCell(createCell("Subtotal", negrilla, 2, 4, Element.ALIGN_LEFT));
		tbl_products.addCell(createCell(""+r.getSubtotal(), normal, 2, 1, Element.ALIGN_RIGHT));
		tbl_products.addCell(createCell("IVA", negrilla, 2, 4, Element.ALIGN_LEFT));
		tbl_products.addCell(createCell(""+r.getIVA(), normal, 2, 1, Element.ALIGN_RIGHT));
		tbl_products.addCell(createCell("Total", negrilla, 2, 4, Element.ALIGN_LEFT));
		tbl_products.addCell(createCell(""+r.getTotal(), normal, 2, 1, Element.ALIGN_RIGHT));
		if(((SeparateReceipt)r).getState()==State.ENTREGADO) {
			tbl_products.addCell(createCell("Entregado por: "+((SeparateReceipt)r).getLastPayment().getCreator().getName()+" "+((SeparateReceipt)r).getLastPayment().getCreator().getLastName(), normal, 1, 5, Element.ALIGN_LEFT));
			tbl_products.addCell(createCell("Observaciones:\n"+r.getObservations(), normal, 1, 5, Element.ALIGN_LEFT));	
		}else {
			tbl_products.addCell(createCell("Por pagar", negrilla, 2, 4, Element.ALIGN_LEFT));
			tbl_products.addCell(createCell(""+((SeparateReceipt)r).calculateUnpaidPrice(), normal, 2, 1, Element.ALIGN_RIGHT));
		}
		doc.add(tbl_products);
		doc.close();
	}

	public PdfPCell createCell(String content, Font f, float borderWidth, int colspan, int alignment) {
		PdfPCell cell = new PdfPCell(new Phrase(content, f));
		cell.setBorderWidth(borderWidth);
		cell.setColspan(colspan);
		cell.setHorizontalAlignment(alignment);
		return cell;
	}

	//Sort products by ascending price

	//Bubble sorting
	public ArrayList<Product> sortingPricesOfProducts() {
		ArrayList<Product> copyOfProducts = new ArrayList<Product>(products);
		for(int i=1;i<copyOfProducts.size();i++) {
			for(int j=0;j<copyOfProducts.size()-i;j++) {
				if(copyOfProducts.get(j).getPrice()>copyOfProducts.get(j+1).getPrice()) {
					Product temp = copyOfProducts.get(j);
					copyOfProducts.set(j, copyOfProducts.get(j+1));
					copyOfProducts.set(j+1, temp);
				}
			}
		}
		return copyOfProducts;
	}

	//Sort brands in descending alphabetical order

	//Insertion sorting
	public ArrayList<Brand> sortingBrandNames() {
		ArrayList<Brand> listSorted=new ArrayList<Brand>(brands);
		for(int i=1;i<listSorted.size();i++) {
			for(int j=i;j>0 && listSorted.get(j-1).getName().compareTo(listSorted.get(j).getName())<0;j--) {
				Brand temp=listSorted.get(j);
				listSorted.set(j, listSorted.get(j-1));
				listSorted.set(j-1, temp);
			}
		}
		return listSorted;
	}

	//Import data from csv files

	public void importClientsData(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		while(line!=null){
			String[] parts = line.split(SEPARATOR);
			if(parts.length!=6) {
				br.close();
				throw new IOException();
			}
			if(!parts[0].equals("Nombres")) {

				try {
					createClient(parts[0].toUpperCase(),  parts[1].toUpperCase(),  parts[2],  parts[3],  parts[4], parts[5]);
				} catch (SameIDException e) {
				} 
			}
			numProgress++;
			
			line = br.readLine();
		}
		br.close();
		 saveDataAngelaccesorios();
	}
	
	public int numberOfLinesOfFile(String fileName) throws IOException {
		int num=0;
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		while(line!=null){
			num++;
			line = br.readLine();
		}
		br.close();
		return num;
	}

	public void importProductsData(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		while(line!=null){
			String[] parts = line.split(SEPARATOR);
			if(parts.length!=7) {
				br.close();
				throw new IOException();
			}
			if(!parts[0].equals("Modelo")) {
				addTypeOfProduct(parts[1],parts[2]);
				TypeOfProduct top=searchTypeOfProduct(typePRoot,parts[1]);
				
				addBrand(parts[3]);
				Brand brand= searchBrand(parts[3]);
				boolean warranty=false;
				if(parts[6].equalsIgnoreCase("si")) {
					warranty=true;
				}
				try {
					int units=Integer.parseInt(parts[4]);
					double price= Double.parseDouble(parts[5]);
					addProduct(top, brand, parts[0], units, price, warranty);
				} catch (NoQuantityException |NegativeQuantityException | NoPriceException | NegativePriceException | SameProductException |NumberFormatException e) {
				}
			}
			numProgress++;
			line = br.readLine();
		}
		br.close();
		saveDataAngelaccesorios();
		 
	}

}
