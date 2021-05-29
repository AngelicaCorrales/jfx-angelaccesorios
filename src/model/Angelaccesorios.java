package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
		//if(searchClientInReceipt(client)==null) {
			int i=clients.indexOf(client);
			clients.remove(i);
			deleted=true;
			//saveDataAngelaccesorios();

		//}
				
		return deleted;

	}
	
	public Receipt searchClientInReceipt(Client client) {
		Receipt receipt=null;
		/*boolean found=false;
		for(int i=0; i<receipts.size() && !found;i++) {
			if(receipts.get(i).getBuyer().getId().equals(client.getId())) {
				found=true;
				receipt=receipts.get(i);
			}

		}
		*/
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

//	public Receipt(ArrayList<Product> listProd,ArrayList<Integer> listQ,Client b, User c, Date d, String obs, String pm) {

	public void createCashReceipt(ArrayList<Product> listProd,ArrayList<Integer> listQ,Client buyer, String observations, String paymentMethod) {
		
	}
	
	public void createSeparateReceipt(ArrayList<Product> listProd,ArrayList<Integer> listQ,Client buyer, String observations, String paymentMethod, double valuePayment) {
			
	}
	
	public void addProductToAReceipt(Product prod, ArrayList<Product> listProd) {
		
	}

	public void deleteProductFromAReceipt(Product prod, ArrayList<Product> listProd) {

	}

	public void updateSeparateReceipt(Receipt receipt, String paymentMethod, double valuePayable) {
		
	}
	
	public void generateReceipt(Receipt receipt) {

	}
	
	public void deleteReceipt(Receipt receipt) {

	}
	
	public void searchReceipt(String code) {

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
			found = products.get(i).getType().getName().equals(type.getName());	
		}
		if(found){
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
			added = true;
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
		return added;
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

	public boolean updateProduct(Product p, Brand b, String model, int units, double price, boolean guarantee, boolean enabled) throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException {
		Product product = searchProduct(p.getType(), b, model);
		boolean updated=false;
		boolean findProduct = false;
		if(p!= product) {
			if(product!=null) {
				findProduct =true;
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
			updated=true;
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

	//All related with Receipt

	public ArrayList<Receipt> getReceipts() {
		return receipts;
	}

	public void setReceipts(ArrayList<Receipt> receipts) {
		this.receipts = receipts;
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

}
