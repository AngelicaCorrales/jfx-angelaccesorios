package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.EmailException;
import exceptions.SameIDException;
import exceptions.SameUserNameException;
import exceptions.SpaceException;

public class AngelaccesoriosTest {
	Angelaccesorios angelaccesorios;

	//Scenarios

	public void setupScenary1() {
		angelaccesorios=new Angelaccesorios();
	}

	//All the scenarios related with User
	
	public void setupScenary2() throws IOException, EmailException, SpaceException {
		angelaccesorios=new Angelaccesorios();

		angelaccesorios.createUserAdmin("1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", "angelaccesorios@gmail.com");

	}

	public void setupScenary3() throws IOException, EmailException, SpaceException, SameIDException, SameUserNameException {
		angelaccesorios=new Angelaccesorios();

		angelaccesorios.createUserAdmin("1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", "angelaccesorios@gmail.com");
		angelaccesorios.createUser("16348023", "PARK", "JIMIN", "jimin", "lachim0lala");
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
	
	//Method: CreateUserAdmin
	@Test
	public void testCreateUserAdmin1() throws IOException, EmailException, SpaceException {
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
	}

	@Test
	public void testCreateUserAdmin2() throws IOException, EmailException, SpaceException {
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
	public void testCreateUserAdmin3() throws IOException, EmailException, SpaceException {
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
	public void testCreateUser1() throws IOException, SpaceException, EmailException, SameIDException, SameUserNameException {
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
	public void testCreateUser2() throws IOException, SpaceException, EmailException, SameIDException, SameUserNameException {
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
	public void testCreateUser3() throws IOException, SpaceException, EmailException, SameIDException, SameUserNameException {
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
	public void testCreateUser4() throws IOException, SpaceException, EmailException, SameIDException, SameUserNameException {
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
	public void testCreateUser5() throws IOException, SpaceException, EmailException, SameIDException, SameUserNameException {
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
	public void testSearchUser1() throws IOException, EmailException, SpaceException {
		setupScenary2();
		User u=angelaccesorios.searchUser("1007793567");
		assertFalse(u==null);
	}

	@Test
	public void testSearchUser2() throws IOException, EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.searchUser("16348023");
		assertFalse(u==null);
	}

	@Test
	public void testSearchUser3() throws IOException, EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.searchUser("31689300");
		assertEquals(u,null);
	}
	
	//Method: searchUserName
	@Test
	public void testSearchUserName1() throws IOException, EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.searchUserName("jimin");
		
		assertFalse(u==null);
	}
	
	@Test
	public void testSearchUserName2() throws IOException, EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.searchUserName("alvinsch");
		assertEquals(u,null);
	}


	//Method: deleteUser
	@Test
	public void testdeleteUser1() throws IOException, EmailException, SpaceException {
		setupScenary2();
		User u=angelaccesorios.getFirstUser();
		boolean deleted=angelaccesorios.deleteUser(u);
		assertFalse(deleted);
		assertEquals(u.getNext(),null);
		assertEquals(u.getPrev(),null);
	}

	@Test
	public void testdeleteUser2() throws IOException, EmailException, SpaceException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.getFirstUser().getNext();
		boolean deleted=angelaccesorios.deleteUser(u);
		assertTrue(deleted);
		assertEquals(angelaccesorios.getFirstUser().getNext(),null);
		
	}
	
	//Method: updateUser
	@Test
	public void testUpdateUser1() throws IOException, SpaceException, EmailException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.getFirstUser().getNext();
		
		String newName="PEDRO";
		boolean enabled=false;
		try {
			
			angelaccesorios.updateUser(u, "16348023", newName, "JIMIN", "jimin", "lachim0lala", enabled);
			assertEquals(u.getName(),newName);
			assertEquals(u.isEnabled(),enabled);
			
		}catch(SpaceException se) {
			fail("SpaceException not expected");
		}catch(SameIDException side) {
			fail("SameIDException not expected");
		}catch(SameUserNameException sune) {
			fail("SameUserNameException not expected");
		}
	}
	
	@Test
	public void testUpdateUser2() throws IOException, SpaceException, EmailException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.getFirstUser().getNext();
		
		String newId="1007793567";
		try {
					
			angelaccesorios.updateUser(u, newId, "PARK", "JIMIN", "jimin", "lachim0lala", true);
			
			fail("SameIDException expected");
		}catch(SpaceException se) {
			fail("SameIDException not expected");
		}catch(SameIDException side) {
			assertFalse(u.getId().equals(newId));
		}catch(SameUserNameException sune) {
			fail("SameIDException not expected");
		}
	}
	
	@Test
	public void testUpdateUser3() throws IOException, SpaceException, EmailException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.getFirstUser().getNext();
		
		String newUserName="angelaccesorios";
		try {
					
			angelaccesorios.updateUser(u, "16348023", "PARK", "JIMIN", newUserName, "lachim0lala", true);
			
			fail("SameUserNameException expected");
		}catch(SpaceException se) {
			fail("SameUserNameException expected");
		}catch(SameIDException side) {
			fail("SameUserNameException expected");
			
		}catch(SameUserNameException sune) {
			assertFalse(u.getUserName().equals(newUserName));
		}
	}
	
	@Test
	public void testUpdateUser4() throws IOException, SpaceException, EmailException, SameIDException, SameUserNameException {
		setupScenary3();
		User u=angelaccesorios.getFirstUser().getNext();
		
		String newUserName="ji min";
		try {
					
			angelaccesorios.updateUser(u, "16348023", "PARK", "JIMIN", newUserName, "lachim0lala", true);
			
			fail("SpaceException expected");
		}catch(SpaceException se) {
			assertFalse(u.getUserName().equals(newUserName));
		}catch(SameIDException side) {
			fail("SpaceException expected");
			
		}catch(SameUserNameException sune) {
			fail("SpaceException expected");
		}
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
	
	//Method: updateBrand
	
	@Test
	public void testUpdateBrand1() throws IOException {
		setupScenary4();
		Brand b = angelaccesorios.getBrands().get(0);
		String newName = "Motorolaa";
		boolean updated = angelaccesorios.updateBrand(b, newName, false);
		assertTrue(updated);
		assertEquals(newName, angelaccesorios.getBrands().get(0).getName());
		assertFalse(angelaccesorios.getBrands().get(0).isEnabled());
	}
	
	@Test
	public void testUpdateBrand2() throws IOException {
		setupScenary4();
		Brand b = angelaccesorios.getBrands().get(2);
		String newName = "Apple";
		boolean updated = angelaccesorios.updateBrand(b, newName, false);
		assertTrue(updated);
		assertEquals(newName, angelaccesorios.getBrands().get(2).getName());
		assertFalse(angelaccesorios.getBrands().get(2).isEnabled());
	}
	
	@Test
	public void testUpdateBrand3() throws IOException {
		setupScenary4();
		Brand b = angelaccesorios.getBrands().get(2);
		String newName = "Applee";
		boolean updated = angelaccesorios.updateBrand(b, newName, true);
		assertTrue(updated);
		assertEquals(newName, angelaccesorios.getBrands().get(2).getName());
		assertTrue(angelaccesorios.getBrands().get(2).isEnabled());
	}
	
	@Test
	public void testUpdateBrand4() throws IOException {
		setupScenary4();
		Brand b = angelaccesorios.getBrands().get(3);
		String newName = "Motorola";
		boolean updated = angelaccesorios.updateBrand(b, newName, false);
		assertFalse(updated);
		assertEquals("Xiaomi", angelaccesorios.getBrands().get(3).getName());
		assertTrue(angelaccesorios.getBrands().get(3).isEnabled());
	}
	
	//Method: searchBrand
	
	@Test
	public void testSearchBrand1() throws IOException {
		setupScenary1();
		Brand b = angelaccesorios.searchBrand("Motorola");
		assertEquals(b, null);
	}
	
	@Test
	public void testSearchBrand2() throws IOException {
		setupScenary4();
		Brand b = angelaccesorios.searchBrand("Samsung");
		assertEquals("Samsung", b.getName());
	}
	
	@Test
	public void testSearchBrand3() throws IOException {
		setupScenary4();
		Brand b = angelaccesorios.searchBrand("XIAOMI");
		assertEquals("Xiaomi", b.getName());
	}
	
	@Test
	public void testSearchBrand4() throws IOException {
		setupScenary4();
		Brand b = angelaccesorios.searchBrand("Alcatel");
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
}
