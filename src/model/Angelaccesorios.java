package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import exceptions.EmailException;
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

	public final static String ANGELACCESORIOS_SAVE_PATH_FILE = "data/angelaccesorios.ackl";
	public final static String SEPARATOR = ";";
	private ArrayList<Brand> brands;
	private ArrayList<Product> products;
	private ArrayList<Receipt> receipts;
	private TypeOfProduct typePRoot;
	private Supplier supplierRoot;
	private List<Client> clients;

	public Angelaccesorios() {
		brands = new ArrayList<Brand>();
		products = new ArrayList<Product>();
		clients=new ArrayList<Client>();
		receipts = new ArrayList<Receipt>(); 
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



	public void createUser(String id, String name, String lastName,String userName, String password) throws SpaceException, SameIDException, SameUserNameException {

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


		//saveDataAngelaccesorios();


	}


	public void createUserAdmin(String id, String name, String lastName,String userName, String password,String email) throws EmailException, SpaceException {

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


		//saveDataAngelaccesorios();

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

	public boolean deleteUser(User user) {
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
		//saveDataAngelaccesorios();
		return deleted;

	}

	//CORREOOO
	public void updateUser(User user,String id, String name, String lastName, String userName, String password, boolean enabled, String email) throws SameIDException, SameUserNameException, SpaceException, EmailException {
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


		//saveDataAngelaccesorios();

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

	public void createClient(String name, String lastName, String id, String typeId, String address, String phone) throws SameIDException {

		Client client= searchClient(id);
		if(client!=null) {
			throw new SameIDException();
		}

		client= new Client( name,  lastName,  id,  TypeId.valueOf(typeId),  address,  phone);
		addSortedClient(client);

		//saveDataAngelaccesorios();

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

	public boolean deleteClient(Client client) {
		boolean deleted=false;
		if(searchClientInReceipt(client)==null) {
			int i=clients.indexOf(client);
			clients.remove(i);
			deleted=true;
			//saveDataAngelaccesorios();


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

	public void updateClient(Client client,String name, String lastName, String id, String typeId, String address, String phone, boolean enabled) throws SameIDException {

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
		//saveDataAngelaccesorios();

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


	public void createCashReceipt(ArrayList<Product> listProd,ArrayList<Integer> listQ,Client buyer, String observations, String paymentMethod) throws NoProductsAddedException, UnderAgeException {
		if(listProd.isEmpty()) {
			throw new NoProductsAddedException();
		}
		
		if(buyer.getTypeId().name().equals("TI") && findElectronicEquipmentProductOnReceiptToBeCreated(listProd)) {
			throw new UnderAgeException();
		}
		
		Receipt receipt= new Receipt(listProd, listQ, buyer, loggedUser, observations, paymentMethod);
		receipts.add(receipt);
		
		loggedUser.setSumTotalReceipts(loggedUser.getSumTotalReceipts()+receipt.calculateTotalPrice());
		loggedUser.setNumberReceipts(loggedUser.getNumberReceipts()+1);
		//saveDataAngelaccesorios();
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

	
	public void createSeparateReceipt(ArrayList<Product> listProd,ArrayList<Integer> listQ,Client buyer, String paymentMethod, double valuePayment) throws NoProductsAddedException, UnderAgeException, NoPriceException, NegativePriceException {
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
		receipts.add(receipt);
		
		loggedUser.setSumTotalReceipts(loggedUser.getSumTotalReceipts()+valuePayment);
		loggedUser.setNumberReceipts(loggedUser.getNumberReceipts()+1);
		//saveDataAngelaccesorios();


	}

	public void addProductToAReceipt(Product prod, int quantity,ArrayList<Product> listProd,ArrayList<Integer> listQ) throws SameProductException, NoQuantityException, NegativeQuantityException {
		if(quantity==0) {
			throw new NoQuantityException(quantity); 
		}
		if(quantity<0) {
			throw new NegativeQuantityException(quantity);
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

	public void updateSeparateReceipt(Receipt receipt, String paymentMethod, double valuePayable) throws NoPriceException, NegativePriceException {
		if(valuePayable==0) {
			throw new NoPriceException(valuePayable); 
		}
		if(valuePayable<0) {
			throw new NegativePriceException(valuePayable);
		}
		((SeparateReceipt)receipt).addPayment(valuePayable, paymentMethod, loggedUser);
		loggedUser.setSumTotalReceipts(loggedUser.getSumTotalReceipts()+valuePayable);
		loggedUser.setNumberReceipts(loggedUser.getNumberReceipts()+1);
		//saveDataAngelaccesorios();
	}

	public void generateReceipt(Receipt receipt) {
		
	}

	public boolean deleteReceipt(Receipt receipt) {
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
		
		//saveDataAngelaccesorios();
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

	public boolean deleteBrand(Brand brand) throws IOException {
		boolean deleted = false;
		if(!searchBrandInProducts(brand)){
			brands.remove(brands.indexOf(brand));
			deleted = true;
			saveDataAngelaccesorios();
		}
		return deleted;
	}

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

	private boolean searchBrandInProducts(Brand b) {
		boolean found = false;
		for(int i=0; i<products.size() && !found;i++) {
			if(products.get(i).getBrand().getName().equals(b.getName())) {
				found = true;
			}
		}
		return found;
	}

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

	public boolean deleteSupplier(Supplier s) throws IOException {
		boolean deleted = false;
		if(!searchSupplierInTypesOfProducts(typePRoot, s)){
			removeSupplier(s);
			deleted = true;
			saveDataAngelaccesorios();
		}
		return deleted;
	}

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

	private Supplier min(Supplier current){
		if(current.getLeft()!=null){
			return min(current.getLeft());
		}else{
			return current;
		}
	}

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

	public void deleteSupplierOfAnEQE(ElectronicEquipment tp, Supplier sp) throws IOException {
		tp.getSuppliers().remove(tp.getSuppliers().indexOf(sp));
		saveDataAngelaccesorios();
	}

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

	private TypeOfProduct min(TypeOfProduct current){
		if(current.getLeft()!=null){
			return min(current.getLeft());
		}else{
			return current;
		}
	}

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

	public boolean loadDataAngelaccesorios(Angelaccesorios ang) throws IOException, ClassNotFoundException{
		File f = new File(ANGELACCESORIOS_SAVE_PATH_FILE);
		boolean loaded = false;
		if(f.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			ang = (Angelaccesorios)ois.readObject();
			ois.close();
			loaded = true;
		}
		return loaded;
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

	private ArrayList<Receipt> sortByDateAndTime() {
		ArrayList<Receipt> copyOfReceipts = new ArrayList<Receipt>(receipts);
		Collections.sort(copyOfReceipts);
		return copyOfReceipts;
	}

	public ArrayList<Receipt> selectedReceipts(String initialTime, String finalTime) throws ParseException{
		boolean correct = false;
		ArrayList<Receipt> selectedReceipts = new ArrayList<Receipt>();
		ArrayList<Receipt> sortingReceipts = sortByDateAndTime();
		for(int k=0; k<sortingReceipts.size();k++) {
			correct = compareWithInitialAndFinalDate(sortingReceipts.get(k),initialTime,finalTime);
			if(correct==true){
				selectedReceipts.add(sortingReceipts.get(k));
			}
		}
		return selectedReceipts;
	}

	public boolean compareWithInitialAndFinalDate(Receipt receipt, String initialTime, String finalTime) throws ParseException {
		boolean correct = false;
		Date date1 = null;
		Date date2 = null;
		Date dateOrder = null;
		String strFormat = "yyyy-MM-dd HH:mm";
		SimpleDateFormat formato = new SimpleDateFormat(strFormat);
		date1 = formato.parse(initialTime);
		date2 = formato.parse(finalTime);
		dateOrder = formato.parse(receipt.getDateAndHour());
		int result1 = dateOrder.compareTo(date1);
		int result2 = dateOrder.compareTo(date2);
		if((result1>0 || result1==0)&&(result2<0||result2==0)) {
			correct = true;
		}
		return correct;
	}

	//AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII

	public void exportOrdersReport(String fn, String initialTime, String finalTime, String separator) throws FileNotFoundException {
		/*ArrayList<Receipt> receiptsS = selectedReceipts(initialTime,finalTime);
		PrintWriter pw = new PrintWriter(fn);
		String info ="";
		String nameColumns = "Código"+separator+"Estado"+separator+"Fecha y hora"+separator+"Observaciones"+separator+"Nombre del cliente"+separator+"Tipo de identificación"+separator+"Numero de identificación"+separator+"Direccion del cliente"+separator+"Telefono del cliente"+separator+"Nombre del Empleado"+separator+"Identificacion del empleado"+separator+"Producto(s): Nombre, cantidad y valor unitario";
		for(int i=0;i<receiptsS.size();i++){
			Receipt objReceipt = receiptsS.get(i);
			info+=objReceipt.getCode()+separator+objReceipt.ge.name()+separator+objOrder.getDateAndHour()+separator+objOrder.getObservations()+separator+objOrder.getClientName()+separator+objOrder.getBuyer().getAddress()+separator+objOrder.getBuyer().getPhone()+separator+objOrder.getEmployeeName()+separator;
			for(int k=0;k<objReceipt.getListOfProducts().size();k++) {
				info += objReceipt.getListOfProducts().get(k).getName()+separator;
				info += objReceipt.getListOfQuantity().get(k)+separator;
				info += objReceipt.getListOfSizes().get(k).getName()+separator; 
				info += objReceipt.getListOfSizes().get(k).getPrice(); 
				int listSize = objOrder.getListOfProducts().size();
				if(k<listSize) {
					info+=separator;
				}
			}
			if(i!=ordersS.size()-1) {
				info+="\n";
			}
		}
		pw.println(nameColumns);
		pw.print(info);
		pw.close();*/
	}
	
	public void exportUsersReport(String fn, String initialTime, String finalTime) throws FileNotFoundException {
		/*int totalOrders=0;
		int totalMoney=0;
		List<Order> ordersS = selectDeliveredOrders(initialTime,finalTime);
		
		PrintWriter pw = new PrintWriter(fn);
		String nameColumns = "Empleado"+SEPARATOR+"Identificacion"+SEPARATOR+"Número de ordenes entregadas"+SEPARATOR+"Precio total de las ordenes entregadas";
		pw.println(nameColumns);
		
		for(int k=0;k<ordersS.size();k++) {
			ordersS.get(k).getDeliverer().setCont(0);
		}
		for(int i=0;i<ordersS.size();i++){
			Order objOrder = ordersS.get(i);
			objOrder.getDeliverer().setCont((objOrder.getDeliverer().getCont())+1);

			if(objOrder.getDeliverer().getCont()==1) {
				pw.println(objOrder.getDeliverer().getName()+SEPARATOR+objOrder.getDeliverer().getId()+SEPARATOR+objOrder.getDeliverer().getNumberOrders()+SEPARATOR+objOrder.getDeliverer().getSumTotalOrders());
				totalOrders+=objOrder.getDeliverer().getNumberOrders();
				totalMoney+=objOrder.getDeliverer().getSumTotalOrders();
			}
		}
		pw.println(SEPARATOR+"Total"+SEPARATOR+totalOrders+SEPARATOR+totalMoney);

		pw.close();
		
		*/
	}
	
	public void exportProductsReport(String fn, String initialTime, String finalTime) throws FileNotFoundException {
		/*int totalOrders=0;
		int totalMoney=0;
		List<Order> ordersS = selectDeliveredOrders(initialTime,finalTime);
		PrintWriter pw = new PrintWriter(fn);
		String nameColumns = "Nombre del producto"+SEPARATOR+"Numero total de veces que fue pedido"+SEPARATOR+"Cantidad de total de dinero recaudado";
		pw.println(nameColumns);
		for(int i=0;i<ordersS.size();i++){
			Order objOrder = ordersS.get(i);
			for(int k=0;k<objOrder.getListOfProducts().size();k++) {
				Product pd = objOrder.getListOfProducts().get(k);
				pd.setCont((pd.getCont())+1);
				if(pd.getCont()==1) {
					pw.println(pd.getName()+SEPARATOR+pd.getNumTimesAddedOrders()+SEPARATOR+pd.getTotalPriceAddedOrders());
					totalOrders+=pd.getNumTimesAddedOrders();
					totalMoney+=pd.getTotalPriceAddedOrders();
				}
			}
		}
		pw.println("Total"+SEPARATOR+totalOrders+SEPARATOR+totalMoney);

		pw.close();
		for(int j=0;j<ordersS.size();j++) {
			Order ord = ordersS.get(j);
			for(int k=0;k<ord.getListOfProducts().size();k++) {
				Product p = ord.getListOfProducts().get(k);
				p.setNumTimesAddedOrders(0);
				p.setTotalPriceAddedOrders(0);
				p.setCont(0);
			}
		} */
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
		/*BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		while(line!=null){
			String[] parts = line.split(";");
			if(!parts[0].equals("id")) {

				createClient( parts[0],  parts[1].toUpperCase(),  parts[2].toUpperCase(),  parts[3],  parts[4],  parts[5],  "");
			}

			line = br.readLine();
		}
		br.close();
		*/
	}


	public void importProductsData(String fileName) throws IOException{
		/*BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		String creator="";
		while(line!=null){
			String[] parts = line.split(";");
			if(!parts[0].equals("productName")) {
				addTypeOfProduct(parts[1],creator);
				TypeOfProduct top=searchTypeOfProductByName(parts[1]);
				Product prod=new Product(parts[0], null, top, idProduct);
				this.products.add(prod);
				idProduct++;
				String[] ingredients=parts[2].split("-");

				for(int i=0; i<ingredients.length;i++) {
					Ingredient ing= new Ingredient(ingredients[i], null, idIngredient);
					this.ingredients.add(ing);
					idIngredient++;

					addIngredientToAProduct( prod,  ing,  creator);

				}
				double price= Double.parseDouble(parts[4]);
				addSizeOfAProduct( prod,  parts[3],  price,creator); 

			}

			line = br.readLine();
		}
		br.close();
		saveDataIngredients();
	*/
	}

}
