package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

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

public class AngelaccesoriosTest {
	Angelaccesorios angelaccesorios;

	//Scenarios

	public void setupScenary1() {
		angelaccesorios=new Angelaccesorios();
	}

	//All the scenarios related with User

	public void setupScenary2() throws  EmailException, SpaceException {
		angelaccesorios=new Angelaccesorios();

		angelaccesorios.createUserAdmin("1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", "angelaccesorios@gmail.com");

	}

	public void setupScenary3() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		angelaccesorios=new Angelaccesorios();

		angelaccesorios.createUserAdmin("1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", "angelaccesorios@gmail.com");
		angelaccesorios.createUser("16348023", "PARK", "JIMIN", "jimin", "lachim0lala");
	}

	public void setupScenary7() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		angelaccesorios=new Angelaccesorios();

		angelaccesorios.createUserAdmin("1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", "angelaccesorios@gmail.com");
		angelaccesorios.createUser("16348023", "PARK", "JIMIN", "jimin", "lachim0lala");
		angelaccesorios.getLastUser().setEnabled(false);
	}

	//All the scenarios related with Brand

	public void setupScenary4() throws IOException {
		angelaccesorios=new Angelaccesorios();
		angelaccesorios.addBrand("Samsung");
		angelaccesorios.addBrand("Motorola");
		angelaccesorios.addBrand("Apple");
		angelaccesorios.addBrand("Xiaomi");
	}

	public void setupScenary5() throws IOException {
		angelaccesorios=new Angelaccesorios();
		angelaccesorios.addBrand("Samsung");
		angelaccesorios.addBrand("Motorola");
		angelaccesorios.addBrand("Apple");
		angelaccesorios.addBrand("Xiaomi");
		angelaccesorios.addBrand("Alcatel");
		angelaccesorios.updateBrand(angelaccesorios.getBrands().get(0), "Samsung", false);
		angelaccesorios.updateBrand(angelaccesorios.getBrands().get(3), "Xiaomi", false);
	}

	//All the scenarios related with Client
	public void setupScenary6() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		angelaccesorios=new Angelaccesorios();
		angelaccesorios.createClient("RINGO", "STARR", "16357453", "CC", "819 McCullough Lights Suite 240", "3183345631");
		angelaccesorios.createClient("WILLOW", "SMITH", "1005234865", "TI", "57265 Vernon Mission Apt. 527", "4394578");
		angelaccesorios.createClient("PAUL", "MCCARTNEY", "16377753", "CE", "58030 Nitzsche Circles Apt. 311", "3172456368");
		angelaccesorios.createClient("PAUL", "MCCARTNEY", "16388853", "PP", "45021 Strosin Roads1", "3119034678");
		angelaccesorios.createClient("PAUL", "MCCARTNEY", "16399953", "CC", "2713 Adah Drive", "2883456");

	}

	//All the scenarios related with Supplier
	public void setupScenary8() throws IOException{
		angelaccesorios=new Angelaccesorios();
		angelaccesorios.addSupplier("AntaresTech", "3118925687");
		angelaccesorios.addSupplier("MundoDigital", "3145678222");
		angelaccesorios.addSupplier("TecnoPunto", "3112563351");
		angelaccesorios.addSupplier("DigiStore", "3045431190");
	}

	//All the scenarios related with TypeOfProduct
	public void setupScenary9() throws IOException{
		angelaccesorios=new Angelaccesorios();
		angelaccesorios.addTypeOfProduct("Estuche", "Accesorio");
		angelaccesorios.addTypeOfProduct("Celular", "Equipo electronico");
		angelaccesorios.addTypeOfProduct("Audifonos", "Accesorio");
		angelaccesorios.addTypeOfProduct("Tablet", "Equipo electronico");
	}

	public void setupScenary10() throws IOException {
		angelaccesorios=new Angelaccesorios();
		angelaccesorios.addTypeOfProduct("Celular", "Equipo electronico");
		angelaccesorios.addSupplier("AntaresTech", "3118925687");
		angelaccesorios.addSupplier("MundoDigital", "3145678222");
		angelaccesorios.addSupplier("DigiStore", "3045431190");
		((ElectronicEquipment)angelaccesorios.getTypePRoot()).getSuppliers().add(angelaccesorios.getSupplierRoot());
		((ElectronicEquipment)angelaccesorios.getTypePRoot()).getSuppliers().add(angelaccesorios.getSupplierRoot().getLeft().getRight());
	}

	//All the scenarios related with Product

	public void setupScenary11() throws IOException {
		angelaccesorios=new Angelaccesorios();
		angelaccesorios.addTypeOfProduct("Tablet", "Equipo electronico");
		angelaccesorios.addBrand("Samsung");
		angelaccesorios.addTypeOfProduct("Audifonos", "Accesorio");
		angelaccesorios.addBrand("Huawei");
	}

	public void setupScenary12() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		angelaccesorios=new Angelaccesorios();
		angelaccesorios.addTypeOfProduct("Estuche", "Accesorio");
		angelaccesorios.addTypeOfProduct("Celular", "Equipo electronico");
		angelaccesorios.addBrand("Apple");
		angelaccesorios.addBrand("Xiaomi");
		angelaccesorios.addProduct(angelaccesorios.getTypePRoot(), angelaccesorios.getBrands().get(0), "iPhone XS", 10, 20000, false);
		angelaccesorios.addProduct(angelaccesorios.getTypePRoot().getRight(), angelaccesorios.getBrands().get(0), "iPhone XS", 5, 2800000, true);
		angelaccesorios.addProduct(angelaccesorios.getTypePRoot(), angelaccesorios.getBrands().get(0), "iPhone 8", 14, 20000, false);
		angelaccesorios.getProducts().get(0).setCode("ACC3202");
		angelaccesorios.getProducts().get(1).setCode("EQE4503");
		angelaccesorios.getProducts().get(2).setCode("ACC8904");
	}

	//Method: CreateUserAdmin
	@Test
	public void testCreateUserAdmin1() throws EmailException, SpaceException {
		setupScenary1();
		String id ="1007793567";
		String name= "ANGELA";
		String lastName="LOPEZ";
		String userName="angelaccesorios";
		String password="4ng3laACC";
		String email="angelaccesorios@gmail.com";
		try {
			angelaccesorios.createUserAdmin(id, name, lastName, userName, password, email);
		}catch(EmailException ee) {
			fail("EmailException not expected");
		}catch(SpaceException se) {
			fail("SpaceException not expected");
		}
		assertFalse(angelaccesorios.getFirstUser()==null);
		assertFalse(angelaccesorios.getLastUser()==null);
		assertTrue(angelaccesorios.getLastUser()==angelaccesorios.getFirstUser());
		assertTrue(angelaccesorios.getFirstUser().getNext()==null);

		assertEquals(angelaccesorios.getFirstUser().getName(),name);
		assertEquals(angelaccesorios.getFirstUser().getLastName(),lastName);
		assertEquals(angelaccesorios.getFirstUser().getId(),id);
		assertEquals(angelaccesorios.getFirstUser().getUserName(),userName);
		assertEquals(angelaccesorios.getFirstUser().getPassword(),password);
		assertEquals(((Admin) angelaccesorios.getFirstUser()).getEmail(),email);
	}

	@Test
	public void testCreateUserAdmin2() throws EmailException, SpaceException {
		setupScenary1();
		String id ="1007793567";
		String name= "ANGELA";
		String lastName="LOPEZ";
		String userName="angelaccesorios";
		String password="4ng3laACC";
		String email="angelaccesorios.com";
		try {
			angelaccesorios.createUserAdmin(id, name, lastName, userName, password, email);
			fail("EmailException expected");
		}catch(EmailException ee) {
			assertTrue(angelaccesorios.getFirstUser()==null);
			assertTrue(angelaccesorios.getLastUser()==null);
		}catch(SpaceException se) {
			fail("EmailException not expected");
		}


	}

	@Test
	public void testCreateUserAdmin3() throws EmailException, SpaceException {
		setupScenary1();
		String id ="1007793567";
		String name= "ANGELA";
		String lastName="LOPEZ";
		String userName="angela accesorios ";
		String password="4ng3laACC";
		String email="angelaccesorios@gmail.com";
		try {
			angelaccesorios.createUserAdmin(id, name, lastName, userName, password, email);
			fail("SpaceException expected");
		}catch(EmailException ee) {
			fail("EmailException not expected");

		}catch(SpaceException se) {
			assertTrue(angelaccesorios.getFirstUser()==null);
			assertTrue(angelaccesorios.getLastUser()==null);
		}


	}


	//Method: CreateUser
	@Test
	public void testCreateUser1() throws SpaceException, EmailException, SameIDException, SameUserNameException {
		setupScenary2();
		String id ="16704327";
		String name= "GEORGE";
		String lastName="HARRISON";
		String userName="gharrison";
		String password="brrrr4ck3ts";

		try {
			angelaccesorios.createUser(id, name, lastName, userName, password);
			User u= angelaccesorios.getLastUser();
			assertEquals(u.getId(),id);
			assertEquals(u.getName(),name);
			assertEquals(u.getLastName(),lastName);
			assertEquals(u.getUserName(),userName);
			assertEquals(u.getPassword(),password);

			assertTrue(u.getNext()==null);
			assertTrue(u.getPrev()==angelaccesorios.getFirstUser());

		}catch(SpaceException se) {
			fail("SpaceException not expected");
		}catch(SameIDException side) {
			fail("SameIDException not expected");
		}catch(SameUserNameException sune) {
			fail("SameUserNameException not expected");
		}


	}


	@Test
	public void testCreateUser2() throws SpaceException, EmailException, SameIDException, SameUserNameException {
		setupScenary2();
		String id ="16704327";
		String name= "GEORGE";
		String lastName="HARRISON";
		String userName="george harrison7";
		String password="brrrr4ck3ts";

		try {
			angelaccesorios.createUser(id, name, lastName, userName, password);

			fail("SpaceException expected");
		}catch(SpaceException se) {
			assertTrue(angelaccesorios.getFirstUser().getNext()==null);
		}catch(SameIDException side) {
			fail("SpaceException expected");
		}catch(SameUserNameException sune) {
			fail("SpaceException expected");
		}


	}

	@Test
	public void testCreateUser3() throws SpaceException, EmailException, SameIDException, SameUserNameException {
		setupScenary3();
		String id ="31689300";
		String name= "MITSKI";
		String lastName="MIYAWAKI";
		String userName="mitski";
		String password="w3r3d0ingb3tt3r";

		try {
			angelaccesorios.createUser(id, name, lastName, userName, password);
			User u= angelaccesorios.getLastUser();
			assertEquals(u.getId(),id);
			assertEquals(u.getName(),name);
			assertEquals(u.getLastName(),lastName);
			assertEquals(u.getUserName(),userName);
			assertEquals(u.getPassword(),password);
			assertTrue(u.getNext()==null);
			assertTrue(u.getPrev()==angelaccesorios.getFirstUser().getNext());
		}catch(SpaceException se) {
			fail("SpaceException not expected");
		}catch(SameIDException side) {
			fail("SameIDException not expected");
		}catch(SameUserNameException sune) {
			fail("SameUserNameException not expected");
		}


	}

	@Test
	public void testCreateUser4() throws SpaceException, EmailException, SameIDException, SameUserNameException {
		setupScenary3();
		String id ="16348023";
		String name= "MITSKI";
		String lastName="MIYAWAKI";
		String userName="mitski";
		String password="w3r3d0ingb3tt3r";

		try {
			angelaccesorios.createUser(id, name, lastName, userName, password);

			fail("SameIDException expected");
		}catch(SpaceException se) {
			fail("SameIDException expected");
		}catch(SameIDException side) {
			assertEquals(angelaccesorios.getFirstUser().getNext().getNext(), null);
		}catch(SameUserNameException sune) {
			fail("SameIDException expected");
		}
	}

	@Test
	public void testCreateUser5() throws SpaceException, EmailException, SameIDException, SameUserNameException {
		setupScenary3();
		String id ="31689300";
		String name= "MITSKI";
		String lastName="MIYAWAKI";
		String userName="angelaccesorios";
		String password="w3r3d0ingb3tt3r";

		try {
			angelaccesorios.createUser(id, name, lastName, userName, password);

			fail("SameUserNameException expected");
		}catch(SpaceException se) {
			fail("SameUserNameException expected");
		}catch(SameIDException side) {
			fail("SameUserNameException expected");

		}catch(SameUserNameException sune) {
			assertEquals(angelaccesorios.getFirstUser().getNext().getNext(), null);
		}
	}

	//Method: SearchUser

	@Test
	public void testSearchUser1() throws EmailException, SpaceException {
		setupScenary2();
		User u=angelaccesorios.searchUser("1007793567");
		assertFalse(u==null);
	}

	@Test
	public void testSearchUser2() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.searchUser("16348023");
		assertFalse(u==null);
	}

	@Test
	public void testSearchUser3() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.searchUser("31689300");
		assertEquals(u,null);
	}

	//Method: searchUserName
	@Test
	public void testSearchUserName1() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.searchUserName("jimin");

		assertFalse(u==null);
	}

	@Test
	public void testSearchUserName2() throws  EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.searchUserName("alvinsch");
		assertEquals(u,null);
	}


	//Method: deleteUser
	@Test
	public void testdeleteUser1() throws  EmailException, SpaceException {
		setupScenary2();
		User u=angelaccesorios.getFirstUser();
		boolean deleted=angelaccesorios.deleteUser(u);
		assertFalse(deleted);
		assertEquals(u,angelaccesorios.getFirstUser());

	}

	@Test
	public void testdeleteUser2() throws  EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.getFirstUser().getNext();
		boolean deleted=angelaccesorios.deleteUser(u);
		assertTrue(deleted);
		assertEquals(angelaccesorios.getFirstUser().getNext(),null);

	}

	//Method: updateUser
	@Test
	public void testUpdateUser1() throws SpaceException, EmailException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.getFirstUser().getNext();

		String newName="PEDRO";
		boolean enabled=false;
		try {

			angelaccesorios.updateUser(u, "16348023", newName, "JIMIN", "jimin", "lachim0lala", enabled,"");
			assertEquals(u.getName(),newName);
			assertEquals(u.isEnabled(),enabled);

		}catch(SpaceException se) {
			fail("SpaceException not expected");
		}catch(SameIDException side) {
			fail("SameIDException not expected");
		}catch(SameUserNameException sune) {
			fail("SameUserNameException not expected");
		}catch(EmailException ee) {
			fail("SameUserNameException not expected");
		}
	}

	@Test
	public void testUpdateUser2() throws SpaceException, EmailException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.getFirstUser().getNext();

		String newId="1007793567";
		try {

			angelaccesorios.updateUser(u, newId, "PARK", "JIMIN", "jimin", "lachim0lala", true,"");

			fail("SameIDException expected");
		}catch(SpaceException se) {
			fail("SameIDException not expected");
		}catch(SameIDException side) {
			assertFalse(u.getId().equals(newId));
		}catch(SameUserNameException sune) {
			fail("SameIDException not expected");
		}catch(EmailException ee) {
			fail("SameIDException not expected");
		}
	}

	@Test
	public void testUpdateUser3() throws SpaceException, EmailException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.getFirstUser().getNext();

		String newUserName="angelaccesorios";
		try {

			angelaccesorios.updateUser(u, "16348023", "PARK", "JIMIN", newUserName, "lachim0lala", true,"");

			fail("SameUserNameException expected");
		}catch(SpaceException se) {
			fail("SameUserNameException expected");
		}catch(SameIDException side) {
			fail("SameUserNameException expected");

		}catch(SameUserNameException sune) {
			assertFalse(u.getUserName().equals(newUserName));
		}catch(EmailException ee) {
			fail("SameUserNameException expected");

		}
	}

	@Test
	public void testUpdateUser4() throws SpaceException, EmailException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.getFirstUser().getNext();

		String newUserName="ji min";
		try {

			angelaccesorios.updateUser(u, "16348023", "PARK", "JIMIN", newUserName, "lachim0lala", true,"");

			fail("SpaceException expected");
		}catch(SpaceException se) {
			assertFalse(u.getUserName().equals(newUserName));
		}catch(SameIDException side) {
			fail("SpaceException expected");

		}catch(SameUserNameException sune) {
			fail("SpaceException expected");
		}catch(EmailException ee) {
			fail("SpaceException expected");
		}
	}

	@Test
	public void testUpdateUser5() throws SpaceException, EmailException, SameIDException, SameUserNameException {
		setupScenary2();
		User u=angelaccesorios.getFirstUser();

		String newEmail="angelaccesorios@hotmail.com";

		try {

			angelaccesorios.updateUser(u, "1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", true,newEmail);
			assertEquals(((Admin) u).getEmail(),newEmail);


		}catch(SpaceException se) {
			fail("SpaceException not expected");
		}catch(SameIDException side) {
			fail("SameIDException not expected");
		}catch(SameUserNameException sune) {
			fail("SameUserNameException not expected");
		}catch(EmailException ee) {
			fail("SameUserNameException not expected");
		}
	}

	@Test
	public void testUpdateUser6() throws SpaceException, EmailException, SameIDException, SameUserNameException {
		setupScenary2();
		User u=angelaccesorios.getFirstUser();

		String newEmail="angelaccesorios.com";

		try {

			angelaccesorios.updateUser(u, "1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", true,newEmail);

			fail("EmailException expected");

		}catch(SpaceException se) {
			fail("EmailException expected");
		}catch(SameIDException side) {
			fail("EmailException expected");
		}catch(SameUserNameException sune) {
			fail("EmailException expected");
		}catch(EmailException ee) {
			assertFalse(((Admin) u).getEmail().equals(newEmail));
		}
	}

	//Method: logInUser
	@Test
	public void testLogInUser1() throws EmailException, SpaceException {
		setupScenary2();

		boolean logIn=angelaccesorios.logInUser("alvinsch","h4yunc0d1g0qu3n0est4nb1nari0");
		assertEquals(angelaccesorios.getLoggedUser(),null);
		assertFalse(logIn);
	}

	@Test
	public void testLogInUser2() throws EmailException, SpaceException {
		setupScenary2();

		boolean logIn=angelaccesorios.logInUser("angelaccesorios", "4ng3laACC");
		assertTrue(angelaccesorios.getLoggedUser()!=null);
		assertTrue(logIn);
	}

	@Test
	public void testLogInUser3() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary3();

		boolean logIn=angelaccesorios.logInUser("jimin", "LACHIM0LALA");
		assertEquals(angelaccesorios.getLoggedUser(),null);
		assertFalse(logIn);
	}

	@Test
	public void testLogInUser4() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary3();

		boolean logIn=angelaccesorios.logInUser("angelacesorios@gmail.com", "4ng3laACC");
		assertEquals(angelaccesorios.getLoggedUser(),null);
		assertFalse(logIn);
	}

	@Test
	public void testLogInUser5() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary3();

		boolean logIn=angelaccesorios.logInUser("angelaccesorios@gmail.com", "4ng3laacc");
		assertEquals(angelaccesorios.getLoggedUser(),null);
		assertFalse(logIn);
	}

	@Test
	public void testLogInUser6() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary3();

		boolean logIn=angelaccesorios.logInUser("angelaccesorios@gmail.com", "4ng3laACC");
		assertTrue(angelaccesorios.getLoggedUser()!=null);
		assertTrue(logIn);
	}

	@Test
	public void testLogInUser7() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary3();

		boolean logIn=angelaccesorios.logInUser("jimin", "lachim0lala");
		assertTrue(angelaccesorios.getLoggedUser()!=null);
		assertTrue(logIn);
	}

	@Test
	public void testLogInUser8() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary7();

		boolean logIn=angelaccesorios.logInUser("jimin", "lachim0lala");
		assertEquals(angelaccesorios.getLoggedUser(),null);
		assertFalse(logIn);
	}

	//Method: createClient
	@Test
	public void testCreateClient1() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary6();

		String name= "KIM";
		String lastName="TAEHYUNG";
		String id ="1005783564";
		String typeId="TI";
		String address="919 Sauer Avenue";
		String phone= "3157356473";
		try {
			angelaccesorios.createClient(name, lastName, id, typeId, address, phone);
			assertEquals(angelaccesorios.getClients().size(),6);
			assertEquals(angelaccesorios.getClients().get(0).getName(),name);
			assertEquals(angelaccesorios.getClients().get(0).getLastName(),lastName);
			assertEquals(angelaccesorios.getClients().get(0).getId(),id);
			assertEquals(angelaccesorios.getClients().get(0).getTypeId().name(),typeId);
			assertEquals(angelaccesorios.getClients().get(0).getAddress(),address);
			assertEquals(angelaccesorios.getClients().get(0).getPhone(),phone);

		}catch(SameIDException side) {
			fail("SameIDException not expected");
		}

	}

	@Test
	public void testCreateClient2() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary6();

		String name= "JOHN";
		String lastName="LENNON";
		String id ="16357453";
		String typeId="CE";
		String address="3972 Nova Springs Suite 224";
		String phone= "3107869999";
		try {
			angelaccesorios.createClient(name, lastName, id, typeId, address, phone);
			fail("SameIDException expected");

		}catch(SameIDException side) {
			assertEquals(angelaccesorios.getClients().size(),5);
		}

	}

	@Test
	public void testAddSortedClient1() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary6();

		String name= "JOHN";
		String lastName="OLENNON";
		String id ="16357999";
		String typeId="CE";
		String address="3972 Nova Springs Suite 224";
		String phone= "3107869999";
		Client client= new Client(name, lastName, id,  TypeId.valueOf(typeId), address, phone);

		angelaccesorios.addSortedClient(client);

		assertEquals(angelaccesorios.getClients().size(),6);
		assertEquals(angelaccesorios.getClients().indexOf(client),2);

	}

	@Test
	public void testSearchClient1() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary6();
		Client c=angelaccesorios.searchClient("16377753");

		assertFalse(c==null);
	}

	@Test
	public void testSearchClient2() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary6();
		Client c=angelaccesorios.searchClient("16370053");

		assertTrue(c==null);
	}

	@Test
	public void testDeleteClient1() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary6();
		Client c=angelaccesorios.getClients().get(3);
		boolean deleted=angelaccesorios.deleteClient(c);
		assertTrue(deleted);
		assertEquals(angelaccesorios.getClients().size(),4);

	}

	@Test
	public void testDeleteClient2() throws EmailException, SpaceException, SameIDException, SameUserNameException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, NoProductsAddedException, UnderAgeException, SameProductException {
		
		setupScenary13();
		Client c=angelaccesorios.getClients().get(0);
		boolean deleted=angelaccesorios.deleteClient(c);
		assertFalse(deleted);
		assertEquals(angelaccesorios.getClients().size(),1);

	}

	@Test
	public void testUpdateClient1() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary6();

		Client c=angelaccesorios.getClients().get(3);
		String newName= "KIM";
		String newLastName="TAEHYUNG";
		String newId ="1005783564";
		String newTypeId="TI";
		String newAddress="919 Sauer Avenue";
		String newPhone= "3157356473";
		boolean enabled=false;
		try {
			angelaccesorios.updateClient(c,newName, newLastName, newId, newTypeId, newAddress, newPhone,enabled);
			assertEquals(angelaccesorios.getClients().size(),5);
			assertEquals(angelaccesorios.getClients().get(0).getName(),newName);
			assertEquals(angelaccesorios.getClients().get(0).getLastName(),newLastName);
			assertEquals(angelaccesorios.getClients().get(0).getId(),newId);
			assertEquals(angelaccesorios.getClients().get(0).getTypeId().name(),newTypeId);
			assertEquals(angelaccesorios.getClients().get(0).getAddress(),newAddress);
			assertEquals(angelaccesorios.getClients().get(0).getPhone(),newPhone);
			assertFalse(angelaccesorios.getClients().get(0).isEnabled());

		}catch(SameIDException side) {
			fail("SameIDException not expected");
		}

	}

	@Test
	public void testUpdateClient2() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary6();

		Client c=angelaccesorios.getClients().get(3);

		String newId ="16357453";
		try {
			angelaccesorios.updateClient(c,"PAUL", "MCCARTNEY", newId, "CE", "58030 Nitzsche Circles Apt. 311", "3172456368",true);
			fail("SameIDException expected");

		}catch(SameIDException side) {
			assertEquals(angelaccesorios.getClients().size(),5);
			assertFalse(c.getId().equals(newId));
		}

	}

	@Test
	public void testBinarySearchClient1() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary6();
		int pos=angelaccesorios.binarySearchClient("KIM", "NAMJOON");

		assertTrue(pos==-1);
	}

	@Test
	public void testBinarySearchClient2() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary6();
		int pos=angelaccesorios.binarySearchClient("PAUL", "MCCARTNEY");

		assertTrue(pos==2);
	}

	@Test
	public void testBinarySearchClient3() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary6();
		int pos=angelaccesorios.binarySearchClient("WILLOW", "SMITH");

		assertTrue(pos==1);
	}

	@Test
	public void testSearchClientByName1() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary6();
		List<Client> list=angelaccesorios.searchClientByName("TOWA", "BIRD");

		assertTrue(list.isEmpty());
	}

	@Test
	public void testSearchClientByName2() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary6();
		List<Client> list=angelaccesorios.searchClientByName("WILLOW", "SMITH");

		assertEquals(list.size(),1);
		assertEquals(angelaccesorios.getClients().get(1),list.get(0));
	}

	@Test
	public void testSearchClientByName3() throws EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary6();
		List<Client> list=angelaccesorios.searchClientByName("PAUL", "MCCARTNEY");

		assertEquals(list.size(),3);

	}

	//All the test cases related with Brand

	//Method: addBrand

	@Test
	public void testAddBrand1() throws IOException{
		setupScenary1();
		String nameBrand = "Samsung";
		boolean added = angelaccesorios.addBrand(nameBrand);
		assertTrue(added);
		assertEquals(1, angelaccesorios.getBrands().size());
		assertEquals(nameBrand, angelaccesorios.getBrands().get(0).getName());
		assertTrue(angelaccesorios.getBrands().get(0).isEnabled());
	}

	@Test
	public void testAddBrand2() throws IOException {
		setupScenary4();
		String nameBrand = "Huawei";
		boolean added = angelaccesorios.addBrand(nameBrand);
		assertTrue(added);
		assertEquals(5, angelaccesorios.getBrands().size());
		assertEquals(nameBrand, angelaccesorios.getBrands().get(4).getName());
		assertTrue(angelaccesorios.getBrands().get(4).isEnabled());
	}

	@Test
	public void testAddBrand3() throws IOException {
		setupScenary4();
		String nameBrand = "Samsung";
		boolean added = angelaccesorios.addBrand(nameBrand);
		assertFalse(added);
		assertEquals(4, angelaccesorios.getBrands().size());
	}

	@Test
	public void testAddBrand4() throws IOException {
		setupScenary4();
		String nameBrand = "SAMSUNG";
		boolean added = angelaccesorios.addBrand(nameBrand);
		assertFalse(added);
		assertEquals(4, angelaccesorios.getBrands().size());
	}

	//Method: deleteBrand
	@Test
	public void testDeleteBrand1() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		boolean added = angelaccesorios.deleteBrand(angelaccesorios.getBrands().get(1));
		assertTrue(added);
		assertEquals(1, angelaccesorios.getBrands().size());
	}

	@Test
	public void testDeleteBrand2() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		boolean added = angelaccesorios.deleteBrand(angelaccesorios.getBrands().get(0));
		assertFalse(added);
		assertEquals(2, angelaccesorios.getBrands().size());
	}

	//Method: updateBrand

	@Test
	public void testUpdateBrand1() throws IOException {
		setupScenary4();
		Brand b = angelaccesorios.getBrands().get(0);
		String newName = "Motorolaa";
		boolean enabled = false;
		boolean updated = angelaccesorios.updateBrand(b, newName, enabled);
		assertTrue(updated);
		assertEquals(newName, angelaccesorios.getBrands().get(0).getName());
		assertFalse(angelaccesorios.getBrands().get(0).isEnabled());
	}

	@Test
	public void testUpdateBrand2() throws IOException {
		setupScenary4();
		Brand b = angelaccesorios.getBrands().get(2);
		String newName = "Apple";
		boolean enabled = false;
		boolean updated = angelaccesorios.updateBrand(b, newName, enabled);
		assertTrue(updated);
		assertEquals(newName, angelaccesorios.getBrands().get(2).getName());
		assertFalse(angelaccesorios.getBrands().get(2).isEnabled());
	}

	@Test
	public void testUpdateBrand3() throws IOException {
		setupScenary4();
		Brand b = angelaccesorios.getBrands().get(2);
		String newName = "Applee";
		boolean enabled = true;
		boolean updated = angelaccesorios.updateBrand(b, newName, enabled);
		assertTrue(updated);
		assertEquals(newName, angelaccesorios.getBrands().get(2).getName());
		assertTrue(angelaccesorios.getBrands().get(2).isEnabled());
	}

	@Test
	public void testUpdateBrand4() throws IOException {
		setupScenary4();
		Brand b = angelaccesorios.getBrands().get(3);
		String newName = "Motorola";
		boolean enabled = false; 
		boolean updated = angelaccesorios.updateBrand(b, newName, enabled);
		assertFalse(updated);
		assertEquals("Xiaomi", angelaccesorios.getBrands().get(3).getName());
		assertTrue(angelaccesorios.getBrands().get(3).isEnabled());
	}

	@Test
	public void testUpdateBrand5() throws IOException {
		setupScenary4();
		Brand b = angelaccesorios.getBrands().get(3);
		String newName = "MOTOROLA";	
		boolean updated = angelaccesorios.updateBrand(b, newName, false);
		assertFalse(updated);
		assertEquals("Xiaomi", angelaccesorios.getBrands().get(3).getName());
		assertTrue(angelaccesorios.getBrands().get(3).isEnabled());
	}

	@Test
	public void testUpdateBrand6() throws IOException {
		setupScenary4();
		Brand b = angelaccesorios.getBrands().get(3);
		b.setEnabled(false);
		String newName = "Xiaomi";
		boolean enabled = true;
		boolean updated = angelaccesorios.updateBrand(b, newName, enabled);
		assertTrue(updated);
		assertEquals(newName, angelaccesorios.getBrands().get(3).getName());
		assertTrue(angelaccesorios.getBrands().get(3).isEnabled());
	}

	//Method: searchBrand

	@Test
	public void testSearchBrand1() throws IOException {
		setupScenary1();
		String nameBrand = "Motorola";
		Brand b = angelaccesorios.searchBrand(nameBrand);
		assertEquals(b, null);
	}

	@Test
	public void testSearchBrand2() throws IOException {
		setupScenary4();
		String nameBrand = "Samsung";
		Brand b = angelaccesorios.searchBrand(nameBrand);
		assertEquals(nameBrand, b.getName());
	}

	@Test
	public void testSearchBrand3() throws IOException {
		setupScenary4();
		String nameBrand = "XIAOMI";
		Brand b = angelaccesorios.searchBrand(nameBrand);
		assertEquals("Xiaomi", b.getName());
	}

	@Test
	public void testSearchBrand4() throws IOException {
		setupScenary4();
		String nameBrand = "Alcatel";
		Brand b = angelaccesorios.searchBrand(nameBrand);
		assertEquals(b, null);
	}

	//Method: returnEnabledBrands

	@Test
	public void testReturnEnabledBrands1() {
		setupScenary1();
		ArrayList<Brand> list = angelaccesorios.returnEnabledBrands();
		assertTrue(list.isEmpty());
	}

	@Test
	public void testReturnEnabledBrands2() throws IOException {
		setupScenary4();
		ArrayList<Brand> list = angelaccesorios.returnEnabledBrands();
		assertEquals(4, list.size());
	}

	@Test
	public void testReturnEnabledBrands3() throws IOException {
		setupScenary5();
		ArrayList<Brand> list = angelaccesorios.returnEnabledBrands();
		assertEquals(3, list.size());
	}

	//All the test cases related with Supplier

	//Method: addSupplier

	@Test
	public void testAddSupplier1() throws IOException {
		setupScenary1();
		String nameSupplier = "MovilShop";
		String phone = "3118925687";
		boolean added = angelaccesorios.addSupplier(nameSupplier, phone);
		assertTrue(added);
		assertTrue(angelaccesorios.getSupplierRoot().getName()!=null);
		assertEquals(nameSupplier, angelaccesorios.getSupplierRoot().getName());
	}

	@Test
	public void testAddSupplier2() throws IOException {
		setupScenary8();
		String nameSupplier = "Acmovil";
		String phone = "3228935117";
		boolean added = angelaccesorios.addSupplier(nameSupplier, phone);
		assertTrue(added);
		assertEquals(nameSupplier, angelaccesorios.getSupplierRoot().getRight().getName());
		int numSuppliers = 0;
		if(angelaccesorios.getSupplierRoot()!=null) {
			numSuppliers+=1;
		}
		if(angelaccesorios.getSupplierRoot().getRight()!=null) {
			numSuppliers+=1;
		}
		if(angelaccesorios.getSupplierRoot().getLeft()!=null) {
			numSuppliers+=1;
		}
		if(angelaccesorios.getSupplierRoot().getLeft().getLeft()!=null) {
			numSuppliers+=1;
		}
		if(angelaccesorios.getSupplierRoot().getLeft().getRight()!=null) {
			numSuppliers+=1;
		}
		assertEquals(5, numSuppliers);
	}

	@Test
	public void testAddSupplier3() throws IOException {
		setupScenary8();
		String nameSupplier = "TecnoPunto";
		String phone = "3112563351";
		boolean added = angelaccesorios.addSupplier(nameSupplier, phone);
		assertFalse(added);
		int numSuppliers = 0;
		if(angelaccesorios.getSupplierRoot()!=null) {
			numSuppliers+=1;
		}
		if(angelaccesorios.getSupplierRoot().getLeft()!=null) {
			numSuppliers+=1;
		}
		if(angelaccesorios.getSupplierRoot().getLeft().getLeft()!=null) {
			numSuppliers+=1;
		}
		if(angelaccesorios.getSupplierRoot().getLeft().getRight()!=null) {
			numSuppliers+=1;
		}
		assertEquals(4, numSuppliers);
	}

	@Test
	public void testAddSupplier4() throws IOException {
		setupScenary8();
		String nameSupplier = "TECNOPUNTO";
		String phone = "3112563351";
		boolean added = angelaccesorios.addSupplier(nameSupplier, phone);
		assertFalse(added);
		int numSuppliers = 0;
		if(angelaccesorios.getSupplierRoot()!=null) {
			numSuppliers+=1;
		}
		if(angelaccesorios.getSupplierRoot().getLeft()!=null) {
			numSuppliers+=1;
		}
		if(angelaccesorios.getSupplierRoot().getLeft().getLeft()!=null) {
			numSuppliers+=1;
		}
		if(angelaccesorios.getSupplierRoot().getLeft().getRight()!=null) {
			numSuppliers+=1;
		}
		assertEquals(4, numSuppliers);
	}

	//Method: deleteSupplier

	@Test
	public void testDeleteSupplier1() throws IOException {
		setupScenary10();
		boolean deleted = angelaccesorios.deleteSupplier(angelaccesorios.getSupplierRoot().getLeft());
		assertTrue(deleted);
		assertEquals("DigiStore", angelaccesorios.getSupplierRoot().getLeft().getName());
	}

	@Test
	public void testDeleteSupplier2() throws IOException {
		setupScenary10();
		boolean deleted = angelaccesorios.deleteSupplier(angelaccesorios.getSupplierRoot());
		assertFalse(deleted);
		assertTrue(angelaccesorios.getSupplierRoot()!=null);
		assertEquals("AntaresTech", angelaccesorios.getSupplierRoot().getName());
	}

	//Method: updateSupplier

	@Test
	public void testUpdateSupplier1() throws IOException {
		setupScenary8();
		String newName = "AntenasTech";
		String newPhone = "3128507928";
		boolean updated = angelaccesorios.updateSupplier(angelaccesorios.getSupplierRoot(), newName, newPhone);
		assertTrue(updated);
		assertEquals(newName, angelaccesorios.getSupplierRoot().getName());
		assertEquals(newPhone, angelaccesorios.getSupplierRoot().getPhoneNumber());
	}

	@Test
	public void testUpdateSupplier2() throws IOException {
		setupScenary8();
		String newName = "AntenasTech";
		String newPhone = "3118925687";
		boolean updated = angelaccesorios.updateSupplier(angelaccesorios.getSupplierRoot(), newName, newPhone);
		assertTrue(updated);
		assertEquals(newName, angelaccesorios.getSupplierRoot().getName());
		assertEquals(newPhone, angelaccesorios.getSupplierRoot().getPhoneNumber());
	}

	@Test
	public void testUpdateSupplier3() throws IOException {
		setupScenary8();
		String newName = "AntaresTech";
		String newPhone = "3114221090";
		boolean updated = angelaccesorios.updateSupplier(angelaccesorios.getSupplierRoot(), newName, newPhone);
		assertTrue(updated);
		assertEquals(newName, angelaccesorios.getSupplierRoot().getName());
		assertEquals(newPhone, angelaccesorios.getSupplierRoot().getPhoneNumber());
	}

	@Test
	public void testUpdateSupplier4() throws IOException {
		setupScenary8();
		String newName = "MundoDigital";
		String newPhone = "3045431190";
		boolean updated = angelaccesorios.updateSupplier(angelaccesorios.getSupplierRoot().getLeft().getRight(), newName, newPhone);
		assertFalse(updated);
		assertEquals("DigiStore", angelaccesorios.getSupplierRoot().getLeft().getRight().getName());
		assertEquals("3045431190", angelaccesorios.getSupplierRoot().getLeft().getRight().getPhoneNumber());
	}

	@Test
	public void testUpdateSupplier5() throws IOException {
		setupScenary8();
		String newName = "MUNDODIGITAL";
		String newPhone = "3045121098";
		boolean updated = angelaccesorios.updateSupplier(angelaccesorios.getSupplierRoot().getLeft().getRight(), newName, newPhone);
		assertFalse(updated);
		assertEquals("DigiStore", angelaccesorios.getSupplierRoot().getLeft().getRight().getName());
		assertEquals("3045431190", angelaccesorios.getSupplierRoot().getLeft().getRight().getPhoneNumber());
	}

	//Method: searchSupplier

	@Test
	public void testSearchSupplier1() throws IOException {
		setupScenary1();
		String nameSupplier = "TecnoMovil";
		Supplier s = angelaccesorios.searchSupplier(null, nameSupplier);
		assertEquals(null, s);
	}

	@Test
	public void testSearchSupplier2() throws IOException {
		setupScenary8();
		String nameSupplier = "DigiStore";
		Supplier s = angelaccesorios.searchSupplier(angelaccesorios.getSupplierRoot(), nameSupplier);
		assertTrue(s!=null);
		assertEquals(nameSupplier, s.getName());
	}

	@Test
	public void testSearchSupplier3() throws IOException {
		setupScenary8();
		String nameSupplier = "MUNDODIGITAL";
		Supplier s = angelaccesorios.searchSupplier(angelaccesorios.getSupplierRoot(), nameSupplier);
		assertTrue(s!=null);
		assertEquals("MundoDigital", s.getName());
	}

	@Test
	public void testSearchSupplier4() throws IOException {
		setupScenary8();
		String nameSupplier = "CeluPunto";
		Supplier s = angelaccesorios.searchSupplier(angelaccesorios.getSupplierRoot(), nameSupplier);
		assertEquals(null, s);
	}

	//All the test cases related with TypeOfProduct

	//Method: addTypeOfProduct

	@Test
	public void testAddTypeOfProduct1() throws IOException {
		setupScenary1();
		String nameType = "Celular";
		String category = "Equipo electronico";
		boolean added = angelaccesorios.addTypeOfProduct(nameType, category);
		assertTrue(added);
		assertEquals(nameType, angelaccesorios.getTypePRoot().getName());
		assertEquals("EQE", ((ElectronicEquipment)angelaccesorios.getTypePRoot()).getCode());
	}

	@Test
	public void testAddTypeOfProduct2() throws IOException {
		setupScenary9();
		String nameType = "Cargador";
		String category = "Accesorio";
		boolean added = angelaccesorios.addTypeOfProduct(nameType, category);
		assertTrue(added);
		assertEquals(nameType, angelaccesorios.getTypePRoot().getRight().getRight().getLeft().getName());
		assertEquals("ACC", ((Accessory)angelaccesorios.getTypePRoot().getRight().getRight().getLeft()).getCode());
		int numTp = 0;
		if(angelaccesorios.getTypePRoot()!=null) {
			numTp+=1;
		}
		if(angelaccesorios.getTypePRoot().getRight()!=null) {
			numTp+=1;
		}
		if(angelaccesorios.getTypePRoot().getLeft()!=null) {
			numTp+=1;
		}
		if(angelaccesorios.getTypePRoot().getRight().getRight()!=null) {
			numTp+=1;
		}
		if(angelaccesorios.getTypePRoot().getRight().getRight().getLeft()!=null) {
			numTp+=1;
		}
		assertEquals(5, numTp);
	}

	@Test
	public void testAddTypeOfProduct3() throws IOException {
		setupScenary9();
		String nameType = "Tablet";
		String category = "Equipo electronico";
		boolean added = angelaccesorios.addTypeOfProduct(nameType, category);
		assertFalse(added);
		int numTp = 0;
		if(angelaccesorios.getTypePRoot()!=null) {
			numTp+=1;
		}
		if(angelaccesorios.getTypePRoot().getRight()!=null) {
			numTp+=1;
		}
		if(angelaccesorios.getTypePRoot().getLeft()!=null) {
			numTp+=1;
		}
		if(angelaccesorios.getTypePRoot().getRight().getRight()!=null) {
			numTp+=1;
		}
		assertEquals(4, numTp);
	}

	@Test
	public void testAddTypeOfProduct4() throws IOException {
		setupScenary9();
		String nameType = "Tablet";
		String category = "Accesorio";
		boolean added = angelaccesorios.addTypeOfProduct(nameType, category);
		assertFalse(added);
		int numTp = 0;
		if(angelaccesorios.getTypePRoot()!=null) {
			numTp+=1;
		}
		if(angelaccesorios.getTypePRoot().getRight()!=null) {
			numTp+=1;
		}
		if(angelaccesorios.getTypePRoot().getLeft()!=null) {
			numTp+=1;
		}
		if(angelaccesorios.getTypePRoot().getRight().getRight()!=null) {
			numTp+=1;
		}
		assertEquals(4, numTp);
	}

	@Test
	public void testAddTypeOfProduct5() throws IOException {
		setupScenary9();
		String nameType = "TABLET";
		String category = "Equipo electronico";
		boolean added = angelaccesorios.addTypeOfProduct(nameType, category);
		assertFalse(added);
		int numTp = 0;
		if(angelaccesorios.getTypePRoot()!=null) {
			numTp+=1;
		}
		if(angelaccesorios.getTypePRoot().getRight()!=null) {
			numTp+=1;
		}
		if(angelaccesorios.getTypePRoot().getLeft()!=null) {
			numTp+=1;
		}
		if(angelaccesorios.getTypePRoot().getRight().getRight()!=null) {
			numTp+=1;
		}
		assertEquals(4, numTp);
	}

	//Method: addSupplierToEQE

	@Test
	public void testAddSupplierToEQE1() throws IOException {
		setupScenary10();
		boolean added = angelaccesorios.addSupplierToEQE(((ElectronicEquipment)angelaccesorios.getTypePRoot()), angelaccesorios.getSupplierRoot().getLeft());
		assertTrue(added);
		assertEquals(angelaccesorios.getSupplierRoot().getLeft().getName(), ((ElectronicEquipment)angelaccesorios.getTypePRoot()).getSuppliers().get(2).getName());
		assertEquals(3, ((ElectronicEquipment)angelaccesorios.getTypePRoot()).getSuppliers().size());
	}

	@Test
	public void testAddSupplierToEQE2() throws IOException {
		setupScenary10();
		boolean added = angelaccesorios.addSupplierToEQE(((ElectronicEquipment)angelaccesorios.getTypePRoot()), angelaccesorios.getSupplierRoot());
		assertFalse(added);
		assertEquals(2, ((ElectronicEquipment)angelaccesorios.getTypePRoot()).getSuppliers().size());
	}

	//Method: deleteSupplierOfAnEQE

	@Test
	public void testDeleteSupplierToEQE() throws IOException {
		setupScenary10();
		angelaccesorios.deleteSupplierOfAnEQE(((ElectronicEquipment)angelaccesorios.getTypePRoot()), angelaccesorios.getSupplierRoot().getLeft().getRight());
		assertEquals(1, ((ElectronicEquipment)angelaccesorios.getTypePRoot()).getSuppliers().size());
	}

	//Method: deleteTypeOfProduct

	@Test
	public void testDeleteTypeOfProduct1() throws IOException {
		setupScenary11();
		boolean deleted = angelaccesorios.deleteTypeOfProduct(angelaccesorios.getTypePRoot().getRight());
		assertTrue(deleted);
		assertEquals(null, angelaccesorios.getTypePRoot().getRight());
		assertTrue(angelaccesorios.getTypePRoot()!=null);
	}

	@Test
	public void testDeleteTypeOfProduct2() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		boolean deleted = angelaccesorios.deleteTypeOfProduct(angelaccesorios.getTypePRoot().getRight());
		assertFalse(deleted);
		assertTrue(angelaccesorios.getTypePRoot()!=null);
		assertTrue(angelaccesorios.getTypePRoot().getRight()!=null);
		assertEquals("Celular", angelaccesorios.getTypePRoot().getRight().getName());
	}

	//Method: updateTypeOfProduct

	@Test
	public void testUpdateTypeOfProduct1() throws IOException {
		setupScenary9();
		String newName = "Equipo de sonido";
		boolean enabled = false;
		boolean updated = angelaccesorios.updateTypeOfProduct(angelaccesorios.getTypePRoot().getLeft(), newName, enabled);
		assertTrue(updated);
		assertEquals(newName, angelaccesorios.getTypePRoot().getLeft().getName());
		assertFalse(angelaccesorios.getTypePRoot().getLeft().isEnabled());
	}

	@Test
	public void testUpdateTypeOfProduct2() throws IOException {
		setupScenary9();
		String newName = "Estuche";
		boolean enabled = false;
		boolean updated = angelaccesorios.updateTypeOfProduct(angelaccesorios.getTypePRoot(), newName, enabled);
		assertTrue(updated);
		assertEquals(newName, angelaccesorios.getTypePRoot().getName());
		assertFalse(angelaccesorios.getTypePRoot().isEnabled());
	}

	@Test
	public void testUpdateTypeOfProduct3() throws IOException {
		setupScenary9();
		String newName = "Estuchee";
		boolean enabled = true;
		boolean updated = angelaccesorios.updateTypeOfProduct(angelaccesorios.getTypePRoot(), newName, enabled);
		assertTrue(updated);
		assertEquals(newName, angelaccesorios.getTypePRoot().getName());
		assertTrue(angelaccesorios.getTypePRoot().isEnabled());
	}

	@Test
	public void testUpdateTypeOfProduct4() throws IOException {
		setupScenary9();
		String newName = "Celular";
		boolean enabled = false;
		boolean updated = angelaccesorios.updateTypeOfProduct(angelaccesorios.getTypePRoot().getLeft(), newName, enabled);
		assertFalse(updated);
		assertEquals("Tablet", angelaccesorios.getTypePRoot().getLeft().getName());
		assertTrue(angelaccesorios.getTypePRoot().getLeft().isEnabled());
	}

	@Test
	public void testUpdateTypeOfProduct5() throws IOException {
		setupScenary9();
		String newName = "CELULAR";
		boolean enabled = false;
		boolean updated = angelaccesorios.updateTypeOfProduct(angelaccesorios.getTypePRoot().getLeft(), newName, enabled);
		assertFalse(updated);
		assertEquals("Tablet", angelaccesorios.getTypePRoot().getLeft().getName());
		assertTrue(angelaccesorios.getTypePRoot().getLeft().isEnabled());
	}

	@Test
	public void testUpdateTypeOfProduct6() throws IOException {
		setupScenary9();
		angelaccesorios.getTypePRoot().setEnabled(false);
		String newName = "Estuche";
		boolean enabled = true;
		boolean updated = angelaccesorios.updateTypeOfProduct(angelaccesorios.getTypePRoot(), newName, enabled);
		assertTrue(updated);
		assertEquals(newName, angelaccesorios.getTypePRoot().getName());
		assertTrue(angelaccesorios.getTypePRoot().isEnabled());
	}

	//Method: searchTypeOfProduct

	@Test
	public void testSearchTypeOfProduct1() throws IOException {
		setupScenary1();
		String nameTp = "Secador";
		TypeOfProduct ty = angelaccesorios.searchTypeOfProduct(null, nameTp);
		assertEquals(null, ty);
	}

	@Test
	public void testSearchTypeOfProduct2() throws IOException {
		setupScenary9();
		String nameTp = "Audifonos";
		TypeOfProduct ty = angelaccesorios.searchTypeOfProduct(angelaccesorios.getTypePRoot(), nameTp);
		assertTrue(ty!=null);
		assertEquals(nameTp, ty.getName());
	}

	@Test
	public void testSearchTypeOfProduct3() throws IOException {
		setupScenary9();
		String nameTp = "AUDIFONOS";
		TypeOfProduct ty = angelaccesorios.searchTypeOfProduct(angelaccesorios.getTypePRoot(), nameTp);
		assertTrue(ty!=null);
		assertEquals("Audifonos", ty.getName());
	}

	@Test
	public void testSearchTypeOfProduct4() throws IOException {
		setupScenary9();
		String nameTp = "Equipos de sonido";
		TypeOfProduct ty = angelaccesorios.searchTypeOfProduct(angelaccesorios.getTypePRoot(), nameTp);
		assertEquals(null, ty);

	}

	//All the test cases related with Product

	//Method: addProduct

	@Test
	public void testAddProduct1() throws IOException {
		setupScenary11();
		String model = "Galaxy Tab S4";
		int units = 8;
		double price = 980000;
		boolean guarantee = true;
		try {
			angelaccesorios.addProduct(angelaccesorios.getTypePRoot(), angelaccesorios.getBrands().get(0), model, units, price, guarantee);
			assertEquals("EQE",((ElectronicEquipment)angelaccesorios.getProducts().get(0).getType()).getCode());
			assertEquals("Tablet", angelaccesorios.getProducts().get(0).getTypeName());
			assertEquals("Samsung", angelaccesorios.getProducts().get(0).getBrandName());
			assertEquals(model, angelaccesorios.getProducts().get(0).getModel());
			assertEquals(units, angelaccesorios.getProducts().get(0).getUnits());
			assertEquals(price, angelaccesorios.getProducts().get(0).getPrice());
			assertTrue(angelaccesorios.getProducts().get(0).hasGuarantee());
			assertEquals(1, angelaccesorios.getProducts().size());
		}catch(NoQuantityException q) {
			fail("NoQuantityException not expected");
		}catch(NegativeQuantityException nq) {
			fail("NegativeQuantityException not expected");
		}catch(NoPriceException p) {
			fail("NoPriceException not expected");
		}catch(NegativePriceException np) {
			fail("NegativePriceException not expected");
		}catch(SameProductException s) {
			fail("SameProductException not expected");
		}
	}

	//@Test
	public void testAddProduct2() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		String model = "iPhone XS";
		int units = 5;
		double price = 20000;
		boolean guarantee = false;
		try {
			angelaccesorios.addProduct(angelaccesorios.getTypePRoot(), angelaccesorios.getBrands().get(0), model, units, price, guarantee);
			fail("SameProductException expected");
		}catch(NoQuantityException q) {
			fail("NoQuantityException not expected");
		}catch(NegativeQuantityException nq) {
			fail("NegativeQuantityException not expected");
		}catch(NoPriceException p) {
			fail("NoPriceException not expected");
		}catch(NegativePriceException np) {
			fail("NegativePriceException not expected");
		}catch(SameProductException s) {
			assertEquals(3, angelaccesorios.getProducts().size());
		}
	}

	@Test
	public void testAddProduct3() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		String model = "iPhone 11";
		int units = 5;
		double price = 3100000;
		boolean guarantee = true;
		try {
			angelaccesorios.addProduct(angelaccesorios.getTypePRoot().getRight(), angelaccesorios.getBrands().get(0), model, units, price, guarantee);
			assertEquals("EQE",((ElectronicEquipment)angelaccesorios.getProducts().get(3).getType()).getCode());
			assertEquals("Celular", angelaccesorios.getProducts().get(3).getTypeName());
			assertEquals("Apple", angelaccesorios.getProducts().get(3).getBrandName());
			assertEquals(model, angelaccesorios.getProducts().get(3).getModel());
			assertEquals(units, angelaccesorios.getProducts().get(3).getUnits());
			assertEquals(price, angelaccesorios.getProducts().get(3).getPrice());
			assertTrue(angelaccesorios.getProducts().get(3).hasGuarantee());
			assertEquals(4, angelaccesorios.getProducts().size());
		}catch(NoQuantityException q) {
			fail("NoQuantityException not expected");
		}catch(NegativeQuantityException nq) {
			fail("NegativeQuantityException not expected");
		}catch(NoPriceException p) {
			fail("NoPriceException not expected");
		}catch(NegativePriceException np) {
			fail("NegativePriceException not expected");
		}catch(SameProductException s) {
			fail("SameProductException not expected");
		}
	}

	@Test
	public void testAddProduct4() throws IOException {
		setupScenary11();
		String model = "FreeBuds 4i";
		int units = 0;
		double price = 200000;
		boolean guarantee = true;
		try {
			angelaccesorios.addProduct(angelaccesorios.getTypePRoot().getRight(), angelaccesorios.getBrands().get(1), model, units, price, guarantee);	
			fail("NoQuantityException expected");
		}catch(NoQuantityException q) {
			assertTrue(angelaccesorios.getProducts().isEmpty());
		}catch(NegativeQuantityException nq) {
			fail("NegativeQuantityException not expected");
		}catch(NoPriceException p) {
			fail("NoPriceException not expected");
		}catch(NegativePriceException np) {
			fail("NegativePriceException not expected");
		}catch(SameProductException s) {
			fail("SameProductException not expected");
		}
	}

	@Test
	public void testAddProduct5() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		String model = "Redmi Note 9";
		int units = -3;
		double price = 2000000;
		boolean guarantee = true;
		try {
			angelaccesorios.addProduct(angelaccesorios.getTypePRoot().getRight(), angelaccesorios.getBrands().get(1), model, units, price, guarantee);	
			fail("NegativeQuantityException expected");
		}catch(NoQuantityException q) {
			fail("NoQuantityException not expected");
		}catch(NegativeQuantityException nq) {
			assertEquals(3, angelaccesorios.getProducts().size());
		}catch(NoPriceException p) {
			fail("NoPriceException not expected");
		}catch(NegativePriceException np) {
			fail("NegativePriceException not expected");
		}catch(SameProductException s) {
			fail("SameProductException not expected");
		}
	}

	@Test
	public void testAddProduct6() throws IOException{
		setupScenary11();
		String model = "FreeBuds 4i";
		int units = 5;
		double price = 0;
		boolean guarantee = true;
		try {
			angelaccesorios.addProduct(angelaccesorios.getTypePRoot().getRight(), angelaccesorios.getBrands().get(1), model, units, price, guarantee);	
			fail("NoPriceException expected");
		}catch(NoQuantityException q) {
			fail("NoQuantityException not expected");
		}catch(NegativeQuantityException nq) {
			fail("NegativeQuantityException not expected");
		}catch(NoPriceException p) {
			assertTrue(angelaccesorios.getProducts().isEmpty());
		}catch(NegativePriceException np) {
			fail("NegativePriceException not expected");
		}catch(SameProductException s) {
			fail("SameProductException not expected");
		}
	}

	@Test
	public void testAddProduct7() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		String model = "Redmi Note 9";
		int units = 5;
		double price = -2000000;
		boolean guarantee = true;
		try {
			angelaccesorios.addProduct(angelaccesorios.getTypePRoot().getRight(), angelaccesorios.getBrands().get(1), model, units, price, guarantee);	
			fail("NegativePriceException expected");
		}catch(NoQuantityException q) {
			fail("NoQuantityException not expected");
		}catch(NegativeQuantityException nq) {
			fail("NegativeQuantityException not expected");
		}catch(NoPriceException p) {
			fail("NoPriceException not expected");
		}catch(NegativePriceException np) {
			assertEquals(3, angelaccesorios.getProducts().size());
		}catch(SameProductException s) {
			fail("SameProductException not expected");
		}
	}

	//	Method: deleteProduct

	@Test
	public void testDeleteProduct1() {

	}

	public void testDeleteProduct2() {

	}

	//Method: updateProduct

	@Test
	public void testUpdateProduct1() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		String newModel = "Redmi Note 9";
		int units = 5;
		double price = 23000;
		boolean guarantee = true;
		boolean enabled  = false;
		try {
			angelaccesorios.updateProduct(angelaccesorios.getProducts().get(0), angelaccesorios.getBrands().get(1), newModel, units, price, guarantee, enabled);	
			assertEquals(angelaccesorios.getProducts().get(0).getBrandName(), angelaccesorios.getBrands().get(1).getName());
			assertEquals(newModel, angelaccesorios.getProducts().get(0).getModel());
			assertEquals(units, angelaccesorios.getProducts().get(0).getUnits());
			assertEquals(price, angelaccesorios.getProducts().get(0).getPrice());
			assertTrue(angelaccesorios.getProducts().get(0).hasGuarantee());
			assertFalse(angelaccesorios.getProducts().get(0).isEnabled());
		}catch(NoQuantityException q) {
			fail("NoQuantityException not expected");
		}catch(NegativeQuantityException nq) {
			fail("NegativeQuantityException not expected");
		}catch(NoPriceException p) {
			fail("NoPriceException not expected");
		}catch(NegativePriceException np) {
			fail("NegativePriceException not expected");
		}catch(SameProductException s) {
			fail("SameProductException not expected");
		}
	}

	@Test
	public void testUpdateProduct2() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		String newModel = "iPhone 8";
		int units = 10;
		double price = 20000;
		boolean guarantee = false;
		boolean enabled  = true;
		try {
			angelaccesorios.updateProduct(angelaccesorios.getProducts().get(0), angelaccesorios.getBrands().get(0), newModel, units, price, guarantee, enabled);	
			fail("SameProductException expected");
		}catch(NoQuantityException q) {
			fail("NoQuantityException not expected");
		}catch(NegativeQuantityException nq) {
			fail("NegativeQuantityException not expected");
		}catch(NoPriceException p) {
			fail("NoPriceException not expected");
		}catch(NegativePriceException np) {
			fail("NegativePriceException not expected");
		}catch(SameProductException s) {
			assertEquals(angelaccesorios.getProducts().get(0).getBrandName(), angelaccesorios.getBrands().get(0).getName());
			assertEquals("iPhone XS", angelaccesorios.getProducts().get(0).getModel());
			assertEquals(10, angelaccesorios.getProducts().get(0).getUnits());
			assertEquals(20000.0, angelaccesorios.getProducts().get(0).getPrice());
			assertFalse(angelaccesorios.getProducts().get(0).hasGuarantee());
			assertTrue(angelaccesorios.getProducts().get(0).isEnabled());
		}
	}

	@Test
	public void testUpdateProduct3() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		String newModel = "iPhone 11";
		int units = 0;
		double price = 19000;
		boolean guarantee = false;
		boolean enabled = true;
		try {
			angelaccesorios.updateProduct(angelaccesorios.getProducts().get(1), angelaccesorios.getBrands().get(0), newModel, units, price, guarantee, enabled);	
			fail("NoQuantityException expected");
		}catch(NoQuantityException q) {
			assertEquals(angelaccesorios.getProducts().get(1).getBrandName(), angelaccesorios.getBrands().get(0).getName());
			assertEquals("iPhone XS", angelaccesorios.getProducts().get(1).getModel());
			assertEquals(5, angelaccesorios.getProducts().get(1).getUnits());
			assertEquals(2800000.0, angelaccesorios.getProducts().get(1).getPrice());
			assertTrue(angelaccesorios.getProducts().get(1).hasGuarantee());
			assertTrue(angelaccesorios.getProducts().get(1).isEnabled());
		}catch(NegativeQuantityException nq) {
			fail("NegativeQuantityException not expected");
		}catch(NoPriceException p) {
			fail("NoPriceException not expected");
		}catch(NegativePriceException np) {
			fail("NegativePriceException not expected");
		}catch(SameProductException s) {
			fail("SameProductException not expected");
		}
	}

	@Test
	public void testUpdateProduct4() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		String newModel = "iPhone 11";
		int units = -3;
		double price = 19000;
		boolean guarantee = false;
		boolean enabled = true;
		try {
			angelaccesorios.updateProduct(angelaccesorios.getProducts().get(1), angelaccesorios.getBrands().get(0), newModel, units, price, guarantee, enabled);	
			fail("NegativeQuantityException expected");
		}catch(NoQuantityException q) {
			fail("NoQuantityException not expected");
		}catch(NegativeQuantityException nq) {
			assertEquals(angelaccesorios.getProducts().get(1).getBrandName(), angelaccesorios.getBrands().get(0).getName());
			assertEquals("iPhone XS", angelaccesorios.getProducts().get(1).getModel());
			assertEquals(5, angelaccesorios.getProducts().get(1).getUnits());
			assertEquals(2800000.0, angelaccesorios.getProducts().get(1).getPrice());
			assertTrue(angelaccesorios.getProducts().get(1).hasGuarantee());
			assertTrue(angelaccesorios.getProducts().get(1).isEnabled());
		}catch(NoPriceException p) {
			fail("NoPriceException not expected");
		}catch(NegativePriceException np) {
			fail("NegativePriceException not expected");
		}catch(SameProductException s) {
			fail("SameProductException not expected");
		}
	}

	@Test
	public void testUpdateProduct5() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		String newModel = "iPhone 11";
		int units = 4;
		double price = 0;
		boolean guarantee = false;
		boolean enabled = true;
		try {
			angelaccesorios.updateProduct(angelaccesorios.getProducts().get(1), angelaccesorios.getBrands().get(0), newModel, units, price, guarantee, enabled);	
			fail("NoPriceException expected");
		}catch(NoQuantityException q) {
			fail("NoQuantityException not expected");
		}catch(NegativeQuantityException nq) {
			fail("NegativeQuantityException not expected");
		}catch(NoPriceException p) {
			assertEquals(angelaccesorios.getProducts().get(1).getBrandName(), angelaccesorios.getBrands().get(0).getName());
			assertEquals("iPhone XS", angelaccesorios.getProducts().get(1).getModel());
			assertEquals(5, angelaccesorios.getProducts().get(1).getUnits());
			assertEquals(2800000.0, angelaccesorios.getProducts().get(1).getPrice());
			assertTrue(angelaccesorios.getProducts().get(1).hasGuarantee());
			assertTrue(angelaccesorios.getProducts().get(1).isEnabled());
		}catch(NegativePriceException np) {
			fail("NegativePriceException not expected");
		}catch(SameProductException s) {
			fail("SameProductException not expected");
		}
	}

	@Test
	public void testUpdateProduct6() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		String newModel = "iPhone 11";
		int units = 5;
		double price = -19000;
		boolean guarantee = false;
		boolean enabled = true;
		try {
			angelaccesorios.updateProduct(angelaccesorios.getProducts().get(1), angelaccesorios.getBrands().get(0), newModel, units, price, guarantee, enabled);	
			fail("NegativePriceException expected");
		}catch(NoQuantityException q) {
			fail("NoQuantityException not expected");
		}catch(NegativeQuantityException nq) {
			fail("NegativeQuantityException not expected");
		}catch(NoPriceException p) {
			fail("NoPriceException not expected");
		}catch(NegativePriceException np) {
			assertEquals(angelaccesorios.getProducts().get(1).getBrandName(), angelaccesorios.getBrands().get(0).getName());
			assertEquals("iPhone XS", angelaccesorios.getProducts().get(1).getModel());
			assertEquals(5, angelaccesorios.getProducts().get(1).getUnits());
			assertEquals(2800000.0, angelaccesorios.getProducts().get(1).getPrice());
			assertTrue(angelaccesorios.getProducts().get(1).hasGuarantee());
			assertTrue(angelaccesorios.getProducts().get(1).isEnabled());
		}catch(SameProductException s) {
			fail("SameProductException not expected");
		}
	}

	//Method: searchProduct

	@Test
	public void testSearchProduct1() {
		setupScenary1();
		TypeOfProduct ty = null;
		Brand b = null;
		String model = "Galaxy A51";
		Product p = angelaccesorios.searchProduct(ty, b, model);
		assertEquals(null, p);
	}

	@Test
	public void testSearchProduct2() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		TypeOfProduct ty = angelaccesorios.getTypePRoot();
		Brand b = angelaccesorios.getBrands().get(0);
		String model = "iPhone XS";
		Product p = angelaccesorios.searchProduct(ty, b, model);
		assertTrue(p!=null);
		assertEquals(ty.getName(), p.getTypeName());
		assertEquals(b.getName(), p.getBrandName());
		assertEquals(model, p.getModel());
	}

	@Test
	public void testSearchProduct3() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		TypeOfProduct ty = angelaccesorios.getTypePRoot();
		Brand b = angelaccesorios.getBrands().get(0);
		String model = "IPHONE XS";
		Product p = angelaccesorios.searchProduct(ty, b, model);
		assertTrue(p!=null);
		assertEquals(ty.getName(), p.getTypeName());
		assertEquals(b.getName(), p.getBrandName());
		assertEquals("iPhone XS", p.getModel());
	}

	@Test
	public void testSearchProduct4() throws IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameProductException {
		setupScenary12();
		TypeOfProduct ty = angelaccesorios.getTypePRoot().getRight();
		Brand b = angelaccesorios.getBrands().get(0);
		String model = "iPhone 11";
		Product p = angelaccesorios.searchProduct(ty, b, model);
		assertEquals(null, p);
	}	




































































































	public void setupScenary13() throws EmailException, SpaceException, SameIDException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, NoProductsAddedException, UnderAgeException, SameProductException {
		angelaccesorios=new Angelaccesorios();

		angelaccesorios.createUserAdmin("1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", "angelaccesorios@gmail.com");
		angelaccesorios.createClient("PAUL", "MCCARTNEY", "16399953", "CE", "2713 Adah Drive", "2883456");
		angelaccesorios.addBrand("Apple");
		angelaccesorios.addSupplier("MundoDigital", "3145678222");
		angelaccesorios.addTypeOfProduct("Celular", "Equipo electronico");
		angelaccesorios.addTypeOfProduct("Estuche", "Accesorio");
		((ElectronicEquipment)angelaccesorios.getTypePRoot()).getSuppliers().add(angelaccesorios.getSupplierRoot());
		angelaccesorios.addProduct(angelaccesorios.getTypePRoot().getLeft(), angelaccesorios.getBrands().get(0), "iPhone XS", 10, 20000, false);
		angelaccesorios.addProduct(angelaccesorios.getTypePRoot(), angelaccesorios.getBrands().get(0), "iPhone XS", 5, 2800000, true);
		angelaccesorios.getProducts().get(0).setCode("ACC3202");
		angelaccesorios.getProducts().get(1).setCode("EQE4503");
		
		ArrayList<Product> listProd=new ArrayList<Product>();
		ArrayList<Integer> listQ=new ArrayList<Integer>();
		
		listProd.add(angelaccesorios.getProducts().get(1));
		listQ.add(1);
		angelaccesorios.setLoggedUser(angelaccesorios.getFirstUser());
		angelaccesorios.createCashReceipt(listProd, listQ, angelaccesorios.getClients().get(0), "IMEI: 016385802093028", "Efectivo");
		angelaccesorios.getReceipts().get(0).setCode("23567-1");
		String s="1622235078971";
		long d=Long.parseLong(s);
		//Fri May 28 15:51:18 COT 2021
		Date f=new Date(d);
		angelaccesorios.getReceipts().get(0).setDateAndTime(f);
		
		
		
	}

	public void setupScenary14() throws EmailException, SpaceException, SameIDException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, NoProductsAddedException, UnderAgeException, SameProductException {
		angelaccesorios=new Angelaccesorios();

		angelaccesorios.createUserAdmin("1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", "angelaccesorios@gmail.com");
		angelaccesorios.createClient("WILLOW", "SMITH", "1005234865", "CC", "57265 Vernon Mission Apt. 527", "4394578");
		angelaccesorios.addBrand("Apple");
		angelaccesorios.addSupplier("MundoDigital", "3145678222");
		angelaccesorios.addTypeOfProduct("Celular", "Equipo electronico");
		angelaccesorios.addTypeOfProduct("Estuche", "Accesorio");
		((ElectronicEquipment)angelaccesorios.getTypePRoot()).getSuppliers().add(angelaccesorios.getSupplierRoot());
		angelaccesorios.addProduct(angelaccesorios.getTypePRoot().getLeft(), angelaccesorios.getBrands().get(0), "iPhone XS", 10, 20000, false);
		angelaccesorios.addProduct(angelaccesorios.getTypePRoot(), angelaccesorios.getBrands().get(0), "iPhone XS", 5, 2800000, true);
		angelaccesorios.getProducts().get(0).setCode("ACC3202");
		angelaccesorios.getProducts().get(1).setCode("EQE4503");
		
		ArrayList<Product> listProd=new ArrayList<Product>();
		ArrayList<Integer> listQ=new ArrayList<Integer>();
		
		listProd.add(angelaccesorios.getProducts().get(1));
		listQ.add(1);
		angelaccesorios.setLoggedUser(angelaccesorios.getFirstUser());
		angelaccesorios.createCashReceipt(listProd, listQ, angelaccesorios.getClients().get(0), "IMEI: 016385802093028", "Tarjeta de debito");
		angelaccesorios.getReceipts().get(0).setCode("53567-1");
		String s="1522235078971";
		long d=Long.parseLong(s);
		//Wed Mar 28 06:04:38 COT 2018
		Date f=new Date(d);
		angelaccesorios.getReceipts().get(0).setDateAndTime(f);
	}

	public void setupScenary15() throws EmailException, SpaceException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameIDException, NoProductsAddedException, UnderAgeException, SameProductException {
		angelaccesorios=new Angelaccesorios();

		angelaccesorios.createUserAdmin("1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", "angelaccesorios@gmail.com");
		angelaccesorios.createClient("WILLOW", "SMITH", "1005234865", "TI", "57265 Vernon Mission Apt. 527", "4394578");
		angelaccesorios.createClient("TOWA", "BIRD", "314567764", "CC", "2713 Adah Drive", "3186547722");
		angelaccesorios.addBrand("Apple");
		angelaccesorios.addSupplier("MundoDigital", "3145678222");
		angelaccesorios.addTypeOfProduct("Celular", "Equipo electronico");
		angelaccesorios.addTypeOfProduct("Estuche", "Accesorio");
		((ElectronicEquipment)angelaccesorios.getTypePRoot()).getSuppliers().add(angelaccesorios.getSupplierRoot());
		angelaccesorios.addProduct(angelaccesorios.getTypePRoot().getLeft(), angelaccesorios.getBrands().get(0), "iPhone XS", 10, 20000, false);
		angelaccesorios.addProduct(angelaccesorios.getTypePRoot(), angelaccesorios.getBrands().get(0), "iPhone XS", 5, 2800000, true);
		angelaccesorios.getProducts().get(0).setCode("ACC3202");
		angelaccesorios.getProducts().get(1).setCode("EQE4503");
		
		ArrayList<Product> listProd=new ArrayList<Product>();
		ArrayList<Integer> listQ=new ArrayList<Integer>();
		
		listProd.add(angelaccesorios.getProducts().get(0));
		listQ.add(3);
		
		angelaccesorios.setLoggedUser(angelaccesorios.getFirstUser());
		angelaccesorios.createCashReceipt(listProd, listQ, angelaccesorios.getClients().get(0), "Color: aqua", "Tarjeta de credito");
		angelaccesorios.getReceipts().get(0).setCode("14547-1");
		String s="1622235078971";
		long d=Long.parseLong(s);
		//Fri May 28 15:51:18 COT 2021
		Date f=new Date(d);
		angelaccesorios.getReceipts().get(0).setDateAndTime(f);
	}

	public void setupScenary16() throws EmailException, SpaceException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameIDException, NoProductsAddedException, UnderAgeException, SameProductException {
		angelaccesorios=new Angelaccesorios();

		angelaccesorios.createUserAdmin("1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", "angelaccesorios@gmail.com");
		angelaccesorios.createClient("PAUL", "MCCARTNEY", "16399953", "CE", "2713 Adah Drive", "2883456");
		angelaccesorios.addBrand("Apple");
		angelaccesorios.addSupplier("MundoDigital", "3145678222");
		angelaccesorios.addTypeOfProduct("Celular", "Equipo electronico");
		angelaccesorios.addTypeOfProduct("Estuche", "Accesorio");
		((ElectronicEquipment)angelaccesorios.getTypePRoot()).getSuppliers().add(angelaccesorios.getSupplierRoot());
		angelaccesorios.addProduct(angelaccesorios.getTypePRoot().getLeft(), angelaccesorios.getBrands().get(0), "iPhone XS", 10, 20000, false);
		angelaccesorios.addProduct(angelaccesorios.getTypePRoot(), angelaccesorios.getBrands().get(0), "iPhone XS", 5, 2800000, true);
		angelaccesorios.getProducts().get(0).setCode("ACC3202");
		angelaccesorios.getProducts().get(1).setCode("EQE4503");
		
		ArrayList<Product> listProd=new ArrayList<Product>();
		ArrayList<Integer> listQ=new ArrayList<Integer>();
		
		listProd.add(angelaccesorios.getProducts().get(1));
		listQ.add(1);
		
		angelaccesorios.setLoggedUser(angelaccesorios.getFirstUser());
		angelaccesorios.createSeparateReceipt(listProd, listQ, angelaccesorios.getClients().get(0), "Tarjeta de credito",100000);
		angelaccesorios.getReceipts().get(0).setCode("23567-1");
		String s="1622235078971";
		long d=Long.parseLong(s);
		//Fri May 28 15:51:18 COT 2021
		Date f=new Date(d);
		angelaccesorios.getReceipts().get(0).setDateAndTime(f);
	}

	public void setupScenary17() throws EmailException, SpaceException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameIDException, NoProductsAddedException, UnderAgeException, SameProductException {
		angelaccesorios=new Angelaccesorios();

		angelaccesorios.createUserAdmin("1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", "angelaccesorios@gmail.com");
		angelaccesorios.createClient("WILLOW", "SMITH", "1005234865", "CC", "57265 Vernon Mission Apt. 527", "4394578");
		angelaccesorios.addBrand("Apple");
		angelaccesorios.addSupplier("MundoDigital", "3145678222");
		angelaccesorios.addTypeOfProduct("Celular", "Equipo electronico");
		angelaccesorios.addTypeOfProduct("Estuche", "Accesorio");
		((ElectronicEquipment)angelaccesorios.getTypePRoot()).getSuppliers().add(angelaccesorios.getSupplierRoot());
		angelaccesorios.addProduct(angelaccesorios.getTypePRoot().getLeft(), angelaccesorios.getBrands().get(0), "iPhone XS", 10, 20000, false);
		angelaccesorios.addProduct(angelaccesorios.getTypePRoot(), angelaccesorios.getBrands().get(0), "iPhone XS", 5, 2800000, true);
		angelaccesorios.getProducts().get(0).setCode("ACC3202");
		angelaccesorios.getProducts().get(1).setCode("EQE4503");
		
		ArrayList<Product> listProd=new ArrayList<Product>();
		ArrayList<Integer> listQ=new ArrayList<Integer>();
		
		listProd.add(angelaccesorios.getProducts().get(1));
		listQ.add(1);
		
		angelaccesorios.setLoggedUser(angelaccesorios.getFirstUser());
		angelaccesorios.createSeparateReceipt(listProd, listQ, angelaccesorios.getClients().get(0), "Tarjeta de debito",1000000);
		angelaccesorios.getReceipts().get(0).setCode("53567-1");
		String s="1522235078971";
		long d=Long.parseLong(s);
		//Wed Mar 28 06:04:38 COT 2018
		Date f=new Date(d);
		angelaccesorios.getReceipts().get(0).setDateAndTime(f);
		((SeparateReceipt)angelaccesorios.getReceipts().get(0)).getFirstPayment().setDateAndTime(f);
		
		String s2="1523775078971";
		long d2=Long.parseLong(s2);
		//Sun Apr 15 01:51:18 COT 2018
		Date f2=new Date(d2);
		((SeparateReceipt)angelaccesorios.getReceipts().get(0)).addPayment(1800000,"Tarjeta de debito",angelaccesorios.getLoggedUser());
		((SeparateReceipt)angelaccesorios.getReceipts().get(0)).getLastPayment().setDateAndTime(f2);
		
	}

	@Test
	public void testSearchClientInReceipt1() {

	}

	@Test
	public void testSearchClientInReceipt2() {

	}

	@Test
	public void testSearchClientInReceipt3() {

	}

	@Test
	public void testCreateCashReceipt1() throws EmailException, SpaceException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameIDException, NoProductsAddedException, UnderAgeException, SameProductException  {
		setupScenary15();
		
		ArrayList<Product> listProd=new ArrayList<Product>();
		ArrayList<Integer> listQ=new ArrayList<Integer>();
		
		listProd.add(angelaccesorios.getProducts().get(0));
		listQ.add(1);
		listProd.add(angelaccesorios.getProducts().get(1));
		listQ.add(1);
		
		Client buyer= angelaccesorios.getClients().get(0);
		String observations="IMEI: 997298087485129";
		String paymentMethod= "Transferencia bancaria";
	
		try {
			angelaccesorios.createCashReceipt(listProd, listQ, buyer, observations, paymentMethod);
			fail("UnderAgeException expected");
		} catch (NoProductsAddedException npae) {
			fail("UnderAgeException expected");
		} catch (UnderAgeException uae) {
			assertEquals(angelaccesorios.getReceipts().size(),1);
		}


	}

	@Test
	public void testCreateCashReceipt2() throws EmailException, SpaceException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameIDException, NoProductsAddedException, UnderAgeException, SameProductException {
		setupScenary16();
		
		ArrayList<Product> listProd=new ArrayList<Product>();
		ArrayList<Integer> listQ=new ArrayList<Integer>();
		
		listProd.add(angelaccesorios.getProducts().get(0));
		listQ.add(1);
		listProd.add(angelaccesorios.getProducts().get(1));
		listQ.add(1);
		
		Client buyer= angelaccesorios.getClients().get(0);
		String observations="IMEI: 997298087485129";
		String paymentMethod= "Tarjeta de credito";
	
		try {
			angelaccesorios.createCashReceipt(listProd, listQ, buyer, observations, paymentMethod);
			assertEquals(angelaccesorios.getReceipts().size(),2);
			assertTrue(buyer==angelaccesorios.getReceipts().get(1).getBuyer());
			assertTrue(angelaccesorios.getLoggedUser()==angelaccesorios.getReceipts().get(1).getCreator());
			assertEquals(observations,angelaccesorios.getReceipts().get(1).getObservations());
			assertEquals("TARJETA_DE_CREDITO",angelaccesorios.getReceipts().get(1).getPaymentMethod().name());
			assertEquals(angelaccesorios.getReceipts().get(1).getListOfProducts().size(),2);
			assertTrue(angelaccesorios.getReceipts().get(1).getListOfProducts()==listProd);
			assertEquals(angelaccesorios.getReceipts().get(1).getListOfQuantity().size(),2);
			assertTrue(angelaccesorios.getReceipts().get(1).getListOfQuantity()==listQ);
			
		} catch (NoProductsAddedException npae) {
			fail("NoProductsAddedException not expected");
		} catch (UnderAgeException uae) {
			fail("UnderAgeException not expected");
		}

	}

	@Test
	public void testCreateCashReceipt3() throws EmailException, SpaceException, SameIDException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, NoProductsAddedException, UnderAgeException, SameProductException {
		setupScenary13();
		
		ArrayList<Product> listProd=new ArrayList<Product>();
		ArrayList<Integer> listQ=new ArrayList<Integer>();
		
		Client buyer= angelaccesorios.getClients().get(0);
		String observations="IMEI: 997298087485129";
		String paymentMethod= "Tarjeta de debito";
	
		try {
			angelaccesorios.createCashReceipt(listProd, listQ, buyer, observations, paymentMethod);
			fail("NoProductsAddedException expected");
		} catch (NoProductsAddedException npae) {
			assertEquals(angelaccesorios.getReceipts().size(),1);
		} catch (UnderAgeException uae) {
			
			fail("NoProductsAddedException expected");
		}
	}


	@Test
	public void testCreateSeparateReceipt1() throws EmailException, SpaceException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameIDException, NoProductsAddedException, UnderAgeException, SameProductException {
		setupScenary15();
		

		ArrayList<Product> listProd=new ArrayList<Product>();
		ArrayList<Integer> listQ=new ArrayList<Integer>();
		
		listProd.add(angelaccesorios.getProducts().get(0));
		listQ.add(1);
		listProd.add(angelaccesorios.getProducts().get(1));
		listQ.add(1);
		
		Client buyer= angelaccesorios.getClients().get(0);
		String paymentMethod= "Tarjeta de debito";
		double valuePayment=150000;
	
		try {
			angelaccesorios.createSeparateReceipt(listProd, listQ, buyer, paymentMethod,valuePayment);

			fail("UnderAgeException expected");
		} catch (NoProductsAddedException npae) {
			fail("UnderAgeException expected");
		} catch (UnderAgeException uae) {
			assertEquals(angelaccesorios.getReceipts().size(),1);
		}
	}

	@Test
	public void testCreateSeparateReceipt2() throws EmailException, SpaceException, SameIDException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, NoProductsAddedException, UnderAgeException, SameProductException {
		setupScenary13();
		
		ArrayList<Product> listProd=new ArrayList<Product>();
		ArrayList<Integer> listQ=new ArrayList<Integer>();
		
		listProd.add(angelaccesorios.getProducts().get(1));
		listQ.add(1);
		
		Client buyer= angelaccesorios.getClients().get(0);
		String paymentMethod= "Tarjeta de debito";
		double valuePayment=150000;
		
		try {
			angelaccesorios.createSeparateReceipt(listProd, listQ, buyer, paymentMethod,valuePayment);
			assertEquals(angelaccesorios.getReceipts().size(),2);
			assertTrue(buyer==angelaccesorios.getReceipts().get(1).getBuyer());
			assertTrue(angelaccesorios.getLoggedUser()==angelaccesorios.getReceipts().get(1).getCreator());
			assertEquals("",angelaccesorios.getReceipts().get(1).getObservations());
			assertEquals("TARJETA_DE_DEBITO",angelaccesorios.getReceipts().get(1).getPaymentMethod().name());
			assertEquals(angelaccesorios.getReceipts().get(1).getListOfProducts().size(),1);
			assertTrue(angelaccesorios.getReceipts().get(1).getListOfProducts()==listProd);
			assertEquals(angelaccesorios.getReceipts().get(1).getListOfQuantity().size(),1);
			assertTrue(angelaccesorios.getReceipts().get(1).getListOfQuantity()==listQ);
			assertTrue(((SeparateReceipt)angelaccesorios.getReceipts().get(1)).getFirstPayment()!=null);
			assertEquals(valuePayment,((SeparateReceipt)angelaccesorios.getReceipts().get(1)).getFirstPayment().getAmount());
			assertEquals("TARJETA_DE_DEBITO",((SeparateReceipt)angelaccesorios.getReceipts().get(1)).getFirstPayment().getPaymentMethod().name());
			assertTrue(angelaccesorios.getLoggedUser()==((SeparateReceipt)angelaccesorios.getReceipts().get(1)).getFirstPayment().getCreator());
			
		} catch (NoProductsAddedException npae) {
			fail("NoProductsAddedException not expected");
		} catch (UnderAgeException uae) {
			fail("UnderAgeException not expected");
		}
	}
	@Test
	public void testCreateSeparateReceipt3() throws EmailException, SpaceException, SameIDException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, NoProductsAddedException, UnderAgeException, SameProductException {
		setupScenary14();

		ArrayList<Product> listProd=new ArrayList<Product>();
		ArrayList<Integer> listQ=new ArrayList<Integer>();
		
		Client buyer= angelaccesorios.getClients().get(0);
		String paymentMethod= "Efectivo";
		double valuePayment=150000;
	
		try {
			angelaccesorios.createSeparateReceipt(listProd, listQ, buyer, paymentMethod,valuePayment);
			fail("NoProductsAddedException expected");
		} catch (NoProductsAddedException npae) {
			assertEquals(angelaccesorios.getReceipts().size(),1);
		} catch (UnderAgeException uae) {
			
			fail("NoProductsAddedException expected");
		}
	}

	@Test
	public void testAddProductToAReceipt1() throws EmailException, SpaceException, SameIDException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, NoProductsAddedException, UnderAgeException, SameProductException {
		setupScenary14();
		
		ArrayList<Product> listProd=new ArrayList<Product>();
		ArrayList<Integer> listQ=new ArrayList<Integer>();
		
		listProd.add(angelaccesorios.getProducts().get(0));
		listQ.add(1);
		listProd.add(angelaccesorios.getProducts().get(1));
		listQ.add(1);
		
		Product productToBeAdded=angelaccesorios.getProducts().get(0);
		int quantity=2;
		
		try {
			angelaccesorios.addProductToAReceipt(productToBeAdded, quantity, listProd, listQ);
			fail("SameProductException expected");
		} catch (SameProductException spe) {
			assertEquals(listProd.size(),2);
			assertEquals(listQ.size(),2);
		}
	}

	@Test
	public void testAddProductToAReceipt2() throws EmailException, SpaceException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameIDException, NoProductsAddedException, UnderAgeException, SameProductException  {
		setupScenary16();
		
		ArrayList<Product> listProd=new ArrayList<Product>();
		ArrayList<Integer> listQ=new ArrayList<Integer>();
	
		listProd.add(angelaccesorios.getProducts().get(1));
		listQ.add(1);
		Product productToBeAdded=angelaccesorios.getProducts().get(0);
		int quantity=1;
		
		try {
			angelaccesorios.addProductToAReceipt(productToBeAdded, quantity, listProd, listQ);
			assertEquals(listProd.size(),2);
			assertEquals(listQ.size(),2);
		} catch (SameProductException spe) {
			
			fail("SameProductException not expected");
		}
		
	}

	@Test
	public void testDeleteProductFromAReceipt1() throws EmailException, SpaceException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameIDException, NoProductsAddedException, UnderAgeException, SameProductException {
		setupScenary17();
		ArrayList<Product> listProd=new ArrayList<Product>();
		ArrayList<Integer> listQ=new ArrayList<Integer>();
		
		listProd.add(angelaccesorios.getProducts().get(0));
		listQ.add(1);
		listProd.add(angelaccesorios.getProducts().get(1));
		listQ.add(1);
		
		Product productToBeDeleted=angelaccesorios.getProducts().get(0);
		
		angelaccesorios.deleteProductFromAReceipt(productToBeDeleted, listProd, listQ);
		assertEquals(listProd.size(),1);
		assertEquals(listQ.size(),1);
		assertEquals(listProd.get(0).getCode(),"EQE4503");
		
	}

	@Test

	public void testUpdateSeparateReceipt1() throws EmailException, SpaceException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameIDException, NoProductsAddedException, UnderAgeException, SameProductException {
		setupScenary16();
		SeparateReceipt sr=(SeparateReceipt)angelaccesorios.getReceipts().get(0);
		String paymentMethod= "Efectivo";
		double valuePayable=60000;
		
		angelaccesorios.updateSeparateReceipt(sr, paymentMethod, valuePayable);
		assertTrue(sr.getFirstPayment().getNext()!=null);
		assertEquals(sr.getFirstPayment().getNext().getAmount(),valuePayable);
		assertEquals(sr.calculatePaymentTotal(),160000);
		assertEquals(angelaccesorios.getLoggedUser().getSumTotalReceipts(),160000);
		assertEquals(angelaccesorios.getLoggedUser().getNumberReceipts(),2);
		assertEquals(sr.getState().name(),"NO_ENTREGADO");
		
	}

	@Test
	public void testUpdateSeparateReceipt2() throws EmailException, SpaceException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameIDException, NoProductsAddedException, UnderAgeException, SameProductException {
		setupScenary16();
		SeparateReceipt sr=(SeparateReceipt)angelaccesorios.getReceipts().get(0);
		String paymentMethod= "Tarjeta de debito";
		double valuePayable=2700000;
		
		angelaccesorios.updateSeparateReceipt(sr, paymentMethod, valuePayable);
		assertTrue(sr.getFirstPayment().getNext()!=null);
		assertEquals(sr.getFirstPayment().getNext().getAmount(),valuePayable);
		assertEquals(sr.calculatePaymentTotal(),2800000);
		assertEquals(angelaccesorios.getLoggedUser().getSumTotalReceipts(),2800000);
		assertEquals(angelaccesorios.getLoggedUser().getNumberReceipts(),2);
		assertEquals(sr.getState().name(),"ENTREGADO");
	}

	@Test
	public void testGenerateReceipt1() {

	}

	@Test
	public void testGenerateReceipt2() {

	}

	@Test
	public void testDeleteReceipt1() throws EmailException, SpaceException, SameIDException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, NoProductsAddedException, UnderAgeException, SameProductException {
		setupScenary14();
		Receipt toBeDeleted=angelaccesorios.getReceipts().get(0);
		boolean deleted=angelaccesorios.deleteReceipt(toBeDeleted);
		assertTrue(deleted);
		assertEquals(0,angelaccesorios.getReceipts().size());
	}

	@Test
	public void testDeleteReceipt2() throws EmailException, SpaceException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, SameIDException, NoProductsAddedException, UnderAgeException, SameProductException {
		setupScenary17();
		Receipt toBeDeleted=angelaccesorios.getReceipts().get(0);
		boolean deleted=angelaccesorios.deleteReceipt(toBeDeleted);
		assertTrue(deleted);
		assertEquals(0,angelaccesorios.getReceipts().size());
	}

	@Test
	public void testDeleteReceipt3() throws EmailException, SpaceException, SameIDException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, NoProductsAddedException, UnderAgeException, SameProductException {
		setupScenary13();
		Receipt toBeDeleted=angelaccesorios.getReceipts().get(0);
		boolean deleted=angelaccesorios.deleteReceipt(toBeDeleted);
		assertFalse(deleted);
		assertEquals(1,angelaccesorios.getReceipts().size());
	}

	@Test
	public void testSearchReceipt1() throws EmailException, SpaceException, SameIDException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, NoProductsAddedException, UnderAgeException, SameProductException {
		setupScenary14();
		String code="53567-1";
		Receipt receipt=angelaccesorios.searchReceipt(code);
		assertTrue(receipt!=null);
	}

	@Test
	public void testSearchReceipt2() throws EmailException, SpaceException, SameIDException, IOException, NoQuantityException, NegativeQuantityException, NoPriceException, NegativePriceException, NoProductsAddedException, UnderAgeException, SameProductException {
		setupScenary16();
		String code="76543-2";
		Receipt receipt=angelaccesorios.searchReceipt(code);
		assertTrue(receipt==null);
	}

	@Test
	public void testSaveDataAngelaccesorios1() {

	}


}	
