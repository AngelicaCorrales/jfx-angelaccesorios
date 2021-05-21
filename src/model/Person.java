package model;

public abstract class Person {
	private String name;
	private String lastName;
	private String id;
	private boolean enabled;
	
	public Person(String name, String lastName, String id) {
		this.name = name;
		this.lastName = lastName;
		this.id = id;
		this.enabled = true;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getStatus() {
		String status = "";
		if(enabled) {
			status = "Habilitado";
		}else {
			status = "Deshabilitado";
		}
		return status;
	}
	
	public String toString() {
		return name+" "+lastName+"|"+id;
	}
}
