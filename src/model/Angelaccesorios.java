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

	public final static String SEPARATOR = ";";
	private ArrayList<Brand> brands;
	private ArrayList<Product> products;
	private ArrayList<TypeOfProduct> typesOfProducts;

	public Angelaccesorios() {
		brands = new ArrayList<Brand>();
		products = new ArrayList<Product>();
		typesOfProducts = new ArrayList<TypeOfProduct>();
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public User getFirstUser() {
		return firstUser;
	}

	public User getLastUser() {
		return lastUser;
	}



	public boolean createUser(String id, String name, String lastName,String userName, String password) throws IOException, SpaceException, SameIDException, SameUserNameException {

		boolean created=false;
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

		if(user==null && user2==null) {

			user= new User(name,lastName,id, userName,password);

			lastUser.setNext(user);
			user.setPrev(lastUser);
			lastUser=user;

			created=true;
			//saveDataUsers();

		}
		return created;
	}


	public boolean createUserAdmin(String id, String name, String lastName,String userName, String password,String email) throws IOException, EmailException, SpaceException {

		boolean created=false;
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


		created=true;
		//saveDataUsers();


		return created;
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

	public boolean updateUser(User user,String id, String name, String lastName, String userName, String password, boolean enabled) throws IOException, SameIDException, SameUserNameException, SpaceException {
		//CORREOOO
		boolean updated=false;
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

		updated=true;
		//saveDataUsers();
		return updated;
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
	
	public boolean searchBrandInProducts(Brand b) {
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
	
	//All related with TypeOfProduct
	
	public boolean addTypeOfProduct(String name, String category) throws IOException {
		TypeOfProduct ty = searchTypeOfProduct(name);
		boolean added = false;
		if(ty==null) {
			if(category.equals("Accesorio")) {
				ty = new Accessory(name);
			}else {
				ty = new ElectronicEquipment(name);
			}
			typesOfProducts.add(ty);
			added = true;
			saveDataTypesOfProducts();
		}
		return added;
	}
	
	public boolean deleteTypeOfProduct(TypeOfProduct type) throws IOException {
		boolean deleted = false;
		if(!searchTypeOfProductInProducts(type)){
			typesOfProducts.remove(typesOfProducts.indexOf(type));
			deleted = true;
			saveDataTypesOfProducts();
		}
		return deleted;
	}
	
	public boolean updateTypeOfProduct(TypeOfProduct ty, String newName, String category, boolean enabled) throws IOException {
		TypeOfProduct type = searchTypeOfProduct(newName);
		boolean updated=false;
		boolean findType = false;
		if(ty!= type) {
			if(type!=null) {
				findType =true;
			}
		}
		if(!findType) {
			b.setName(newName);
			b.setEnabled(enabled);
			saveDataBrands();
			updated=true;
		}
		return updated;
	}
	
	public boolean searchTypeOfProductInProducts(TypeOfProduct ty) {
		boolean found = false;
		for(int i=0; i<typesOfProducts.size() && !found;i++) {
			found = products.get(i).getType().getName().equals(ty.getName());	
		}
		return found;
	}
	
	public TypeOfProduct searchTypeOfProduct(String name) {
		boolean found = false;
		TypeOfProduct ty = null;
		for(int i=0; i<typesOfProducts.size() && !found;i++ ) {
			if(typesOfProducts.get(i).getName().equalsIgnoreCase(name)) {
				ty = typesOfProducts.get(i);
				found = true;						
			}
		}
		return ty;
	}
	
	public ArrayList<TypeOfProduct> getTypesOfProducts() {
		return typesOfProducts;
	}

	public void setTypesOfProducts(ArrayList<TypeOfProduct> typesOfProducts) {
		this.typesOfProducts = typesOfProducts;
	}
	
	public void saveDataTypesOfProducts() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TYPESOFPRODUCTS_SAVE_PATH_FILE));
		oos.writeObject(typesOfProducts);
		oos.close();
	}

	@SuppressWarnings("unchecked")
	public boolean loadDataTypesOfProducts() throws IOException, ClassNotFoundException{
		File f = new File(TYPESOFPRODUCTS_SAVE_PATH_FILE);
		boolean loaded = false;
		if(f.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			typesOfProducts = (ArrayList<TypeOfProduct>)ois.readObject();
			ois.close();
			loaded = true;
		}
		return loaded;
	}
	
	//All related with Product
	
	public boolean addProduct(TypeOfProduct type, Brand b, String model, int units, double price, boolean guarantee) throws IOException {
		Product p = searchProduct(type, b, model);
		boolean added = false;
		if(p==null) {
			p = new Product(type, b, units, guarantee, model, price);
			products.add(p);
			added = true;
			saveDataProducts();
		}
		return added;
	}
	
	public boolean deleteProduct(Product p) throws IOException {
		boolean deleted = false;
		if(!searchProductInReceipts(p)){
			products.remove(products.indexOf(p));
			deleted = true;
			saveDataProducts();
		}
		return deleted;
	}
	
	public boolean updateProduct(Product p, TypeOfProduct type, Brand b, String model, int units, double price, boolean guarantee, boolean enabled) throws IOException {
		Product product = searchProduct(type, b, model);
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
			p.setType(type);
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

	
}
