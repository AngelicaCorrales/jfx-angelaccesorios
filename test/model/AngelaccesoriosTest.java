package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import exceptions.EmailException;
import exceptions.SpaceException;

public class AngelaccesoriosTest {
	Angelaccesorios angelaccesorios;

	//Scenarios
	public void setupScenary1() {
		angelaccesorios=new Angelaccesorios();

	}

	public void setupScenary2() throws IOException, EmailException, SpaceException {
		angelaccesorios=new Angelaccesorios();

		angelaccesorios.createUserAdmin("1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", "angelaccesorios@gmail.com");

	}

	public void setupScenary3() throws IOException, EmailException, SpaceException {
		angelaccesorios=new Angelaccesorios();

		angelaccesorios.createUserAdmin("1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", "angelaccesorios@gmail.com");
		angelaccesorios.createUser("16348023", "PARK", "JIMIN", "jimin", "lachim0lala");
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
	public void testCreateUser1() throws IOException, SpaceException, EmailException {
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
		}


	}
	
	//Method: CreateUser
		@Test
		public void testCreateUser2() throws IOException, SpaceException, EmailException {
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
			}


		}

	@Test
	public void testCreateUser3() throws IOException, SpaceException, EmailException {
		setupScenary3();
		String id ="31689300";
		String name= "MITSKI";
		String lastName="MIYAWAKI";
		String userName="mitski";
		String password="w3r3d0ingb3tt3r";


		angelaccesorios.createUser(id, name, lastName, userName, password);
		User u= angelaccesorios.getLastUser();
		assertEquals(u.getId(),id);
		assertTrue(u.getNext()==null);
		assertTrue(u.getPrev()==angelaccesorios.getFirstUser().getNext());

	}

	//Method: SearchUser
	@Test
	public void testSearchUser1() {
		setupScenary1();
		User u=angelaccesorios.searchUser("1007793567");
		assertTrue(u==null);
	}
	
	@Test
	public void testSearchUser2() throws IOException, EmailException, SpaceException {
		setupScenary2();
		User u=angelaccesorios.searchUser("1007793567");
		assertFalse(u==null);
	}
	
	@Test
	public void testSearchUser3() throws IOException, EmailException, SpaceException {
		setupScenary3();
		User u=angelaccesorios.searchUser("16348023");
		assertFalse(u==null);
	}
	
	@Test
	public void testSearchUser4() throws IOException, EmailException, SpaceException {
		setupScenary3();
		User u=angelaccesorios.searchUser("31689300");
		assertEquals(u,null);
	}


}
