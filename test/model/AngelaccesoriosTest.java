package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class AngelaccesoriosTest {
	Angelaccesorios angelaccesorios;
	
	public void setupScenary1() {
		angelaccesorios=new Angelaccesorios();
		
	}
	
	public void setupScenary2() throws IOException {
		angelaccesorios=new Angelaccesorios();
				
		angelaccesorios.createUserAdmin("1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", "angelaccesorios@gmail.com");
		
	}
	
	public void setupScenary3() throws IOException {
		angelaccesorios=new Angelaccesorios();
				
		angelaccesorios.createUserAdmin("1007793567", "ANGELA", "LOPEZ", "angelaccesorios", "4ng3laACC", "angelaccesorios@gmail.com");
		angelaccesorios.createUser("16348023", "PARK", "JIMIN", "jimin", "lachim0lala");
	}
	
	
	@Test
	public void testCreateUserAdmin1() throws IOException {
		setupScenary1();
		String id ="1007793567";
		String name= "ANGELA";
		String lastName="LOPEZ";
		String userName="angelaccesorios";
		String password="4ng3laACC";
		String email="angelaccesorios@gmail.com";
		
		angelaccesorios.createUserAdmin(id, name, lastName, userName, password, email);
		assertFalse(angelaccesorios.getFirstUser()==null);
		assertFalse(angelaccesorios.getLastUser()==null);
		assertTrue(angelaccesorios.getLastUser()==angelaccesorios.getFirstUser());
		assertTrue(angelaccesorios.getFirstUser().getNext()==null);
		
		
		
	}
	
	@Test
	public void testCreateUser1() throws IOException {
		setupScenary2();
		String id ="16704327";
		String name= "GEORGE";
		String lastName="HARRISON";
		String userName="gharrison";
		String password="brrrr4ck3ts";
		
		
		angelaccesorios.createUser(id, name, lastName, userName, password);
		User u= angelaccesorios.getLastUser();
		assertEquals(u.getId(),id);
		assertTrue(u.getNext()==null);
		assertTrue(u.getPrev()==angelaccesorios.getFirstUser());
		
	}
	
	@Test
	public void testCreateUser2() throws IOException {
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

	
}
