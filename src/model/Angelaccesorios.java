package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import exceptions.EmailException;
import exceptions.SameIDException;
import exceptions.SameUserNameException;
import exceptions.SpaceException;

public class Angelaccesorios {
	private User firstUser;
	private User lastUser;
	private User loggedUser;

	public final static String BRANDS_SAVE_PATH_FILE = "data/brands.ackl";

	public final static String SEPARATOR = ";";
	private ArrayList<Brand> brands;
	private ArrayList<Product> products;

	public Angelaccesorios() {
		brands = new ArrayList<Brand>();
		products = new ArrayList<Product>();
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














































	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//Todo lo relacionado con marca

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
		Brand b = searchBrand(brand.getName());
		if(!searchBrandInProducts(b)){
			int i = brands.indexOf(b);
			brands.remove(i);
			deleted = true;
			saveDataBrands();
		}
		return deleted;
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

	
	
	//Todo lo relacionado con producto
	
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}	

}
