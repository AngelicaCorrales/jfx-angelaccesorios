package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import exceptions.EmailException;
import exceptions.NegativePriceException;
import exceptions.NegativeQuantityException;
import exceptions.NoPriceException;
import exceptions.NoQuantityException;
import exceptions.SameIDException;
import exceptions.SameUserNameException;
import exceptions.SpaceException;

public class Angelaccesorios {
	private User firstUser;
	private User lastUser;
	private User loggedUser;

	public final static String BRANDS_SAVE_PATH_FILE = "data/brands.ackl";
	public final static String TYPESOFPRODUCTS_SAVE_PATH_FILE = "data/typesOfProducts.ackl";
	public final static String PRODUCTS_SAVE_PATH_FILE = "data/products.ackl";
	public final static String SUPPLIERS_SAVE_PATH_FILE = "data/suppliers.ackl";

	public final static String SEPARATOR = ";";
	private ArrayList<Brand> brands;
	private ArrayList<Product> products;
	private ArrayList<Receipt> receipts;
	private TypeOfProduct typePRoot;
	private Supplier supplierRoot;

	public Angelaccesorios() {
		brands = new ArrayList<Brand>();
		products = new ArrayList<Product>();
		receipts = new ArrayList<Receipt>(); 
	}

	public User getLoggedUser() {
		return loggedUser;
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


		//saveDataUsers();


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


		//saveDataUsers();

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
		//saveDataUsers();
		return deleted;

	}

	public void updateUser(User user,String id, String name, String lastName, String userName, String password, boolean enabled) throws SameIDException, SameUserNameException, SpaceException {
		//CORREOOO

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

		user.setName(name);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setPassword(password);
		user.setEnabled(enabled);
		user.setId(id);


		//saveDataUsers();

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

				if(user.getPassword().equals(password)) {
					loggedUser=firstUser;
					logIn=true;
				}
			}
		}

		return logIn;
	}






















































































































































































	//All related with Brand

	public boolean addBrand(String name) throws IOException {
		Brand b = searchBrand(name);
		boolean added = false;
		if(b==null) {
			b = new Brand(name);
			brands.add(b);
			added = true;
			saveDataBrands();
		}
		return added;
	}

	public boolean deleteBrand(Brand brand) throws IOException {
		boolean deleted = false;
		if(!searchBrandInProducts(brand)){
			brands.remove(brands.indexOf(brand));
			deleted = true;
			saveDataBrands();
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
			saveDataBrands();
			updated=true;
		}
		return updated;
	}

	private boolean searchBrandInProducts(Brand b) {
		boolean found = false;
		for(int i=0; i<products.size() && !found;i++) {
			found = products.get(i).getBrand().getName().equals(b.getName());	
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

	public void saveDataBrands() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BRANDS_SAVE_PATH_FILE));
		oos.writeObject(brands);
		oos.close();
	}

	@SuppressWarnings("unchecked")
	public boolean loadDataBrands() throws IOException, ClassNotFoundException{
		File f = new File(BRANDS_SAVE_PATH_FILE);
		boolean loaded = false;
		if(f.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			brands = (ArrayList<Brand>)ois.readObject();
			ois.close();
			loaded = true;
		}
		return loaded;
	}

	//All related with Supplier

	public boolean addSupplier(String name, String phoneN) throws IOException {
		Supplier s = searchSupplier(supplierRoot, name);
		boolean added = false;
		if(s==null) {
			s = new Supplier(name, phoneN);
			if(supplierRoot==null) {
				setSupplierRoot(s);
				saveDataSuppliers();
			}else {
				addSupplier(supplierRoot, s);
				saveDataSuppliers();
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
			saveDataSuppliers();
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

	public boolean updateSupplier(Supplier s, String newName, String phone) throws IOException {
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
			s.setPhoneNumber(phone);
			saveDataSuppliers();
			updated=true;
		}
		return updated;
	}

	private Supplier searchSupplier(Supplier current, String name) {
		if(current==null || current.getName().equalsIgnoreCase(name)) {
			return current;
		}else {
			if(name.compareTo(current.getName())<0) {
				return searchSupplier(current.getLeft(), name);
			}else {
				return searchSupplier(current.getRight(), name);
			}
		}
	}

	public boolean searchSupplierInTypesOfProducts(TypeOfProduct current, Supplier s) {
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

	public void saveDataSuppliers() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SUPPLIERS_SAVE_PATH_FILE));
		oos.writeObject(supplierRoot);
		oos.close();
	}

	public boolean loadDataSuppliers() throws IOException, ClassNotFoundException{
		File f = new File(SUPPLIERS_SAVE_PATH_FILE);
		boolean loaded = false;
		if(f.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			supplierRoot = (Supplier)ois.readObject();
			ois.close();
			loaded = true;
		}
		return loaded;
	}

	//All related with TypeOfProduct

	public boolean createTypeOfProduct(String name, String category) throws IOException {
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
				saveDataTypesOfProducts();
			}else {
				addTypeOfProduct(typePRoot, ty);
				saveDataTypesOfProducts();
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

	public boolean addSupplierToATypeOfP(TypeOfProduct tp, Supplier sp) throws IOException{
		boolean find = ((ElectronicEquipment)tp).findSupplier(sp.getName());
		boolean added = false;
		if(find==false) {
			((ElectronicEquipment)tp).getSuppliers().add(sp);
			added = true;
			saveDataTypesOfProducts();
		}
		return added;
	}

	public void deleteASupplierOfATypeOfP(TypeOfProduct tp, Supplier sp) throws IOException {
		ElectronicEquipment eq = ((ElectronicEquipment) tp);
		eq.getSuppliers().remove(eq.getSuppliers().indexOf(sp));
		saveDataTypesOfProducts();
	}

	public boolean deleteTypeOfProduct(TypeOfProduct type) throws IOException {
		boolean deleted = false;
		boolean found = false;
		for(int i=0; i<products.size() && !found;i++) {
			found = products.get(i).getType().getName().equals(type.getName());	
		}
		if(found){
			removeType(type);
			deleted = true;
			saveDataTypesOfProducts();
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
			saveDataTypesOfProducts();
			updated=true;
		}
		return updated;
	}

	public TypeOfProduct searchTypeOfProduct(TypeOfProduct current, String name) {
		if(current==null || current.getName().equalsIgnoreCase(name)) {
			return current;
		}else {
			if(name.compareTo(current.getName())<0) {
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

	public void saveDataTypesOfProducts() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TYPESOFPRODUCTS_SAVE_PATH_FILE));
		oos.writeObject(typePRoot);
		oos.close();
	}

	public boolean loadDataTypesOfProducts() throws IOException, ClassNotFoundException{
		File f = new File(TYPESOFPRODUCTS_SAVE_PATH_FILE);
		boolean loaded = false;
		if(f.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			typePRoot = (TypeOfProduct)ois.readObject();
			ois.close();
			loaded = true;
		}
		return loaded;
	}

	//All related with Product

	public boolean addProduct(TypeOfProduct type, Brand b, String model, int units, double price, boolean guarantee) throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException {
		Product p = searchProduct(type, b, model);
		boolean added = false;
		if(p==null && units>0 && price>0) {
			int num = ThreadLocalRandom.current().nextInt(1000, 10000);
			String code = "";
			boolean found = false;
			do {
				if(type instanceof ElectronicEquipment) {
					code = ((ElectronicEquipment)type).getCode()+num;
				}else {
					code = ((Accessory)type).getCode()+num;
				}
				for(int i=0; i<products.size() && !found;i++ ) {
					if(products.get(i).getCode().equals(code)) {
						found = true;						
					}
				}
			}while(!found);
			p = new Product(type, b, units, guarantee, model, price, code);
			products.add(p);
			p.setCode(p.getCode()+"-"+products.indexOf(p));
			added = true;
			saveDataProducts();
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
		return added;
	}

	public boolean deleteProduct(Product p) throws IOException {
		boolean deleted = false; 
		boolean find = false;
		for(int k=0; k<receipts.size() && !find;k++) {
			if(receipts.get(k) instanceof SeparateReceipt && ((SeparateReceipt)receipts.get(k)).getState().equals(State.NO_ENTREGADO)) {
				find = ((SeparateReceipt)receipts.get(k)).findProduct(p.getCode());	
			}
		}
		if(!find){
			products.remove(products.indexOf(p));
			deleted = true;
			saveDataProducts();
		}
		return deleted;
	}

	public boolean updateProduct(Product p, Brand b, String model, int units, double price, boolean guarantee, boolean enabled) throws IOException {
		Product product = searchProduct(p.getType(), b, model);
		boolean updated=false;
		boolean findProduct = false;
		if(p!= product) {
			if(product!=null) {
				findProduct =true;
			}
		}
		if(!findProduct) {
			p.setBrand(b);
			p.setGuarantee(guarantee);
			p.setModel(model);
			p.setPrice(price);
			p.setUnits(units);
			p.setEnabled(enabled);
			saveDataProducts();
			updated=true;
		}
		return updated;
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

	public void saveDataProducts() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PRODUCTS_SAVE_PATH_FILE));
		oos.writeObject(products);
		oos.close();
	}

	@SuppressWarnings("unchecked")
	public boolean loadDataProducts() throws IOException, ClassNotFoundException{
		File f = new File(PRODUCTS_SAVE_PATH_FILE);
		boolean loaded = false;
		if(f.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			products = (ArrayList<Product>)ois.readObject();
			ois.close();
			loaded = true;
		}
		return loaded;
	}

	//All related with Receipt

	public ArrayList<Receipt> getReceipts() {
		return receipts;
	}

	public void setReceipts(ArrayList<Receipt> receipts) {
		this.receipts = receipts;
	}



}
