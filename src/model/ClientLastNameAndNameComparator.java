package model;

import java.util.Comparator;

public class ClientLastNameAndNameComparator implements Comparator<Client> {
	@Override
	public int compare(Client client1, Client client2) {
		int comparing=client1.getLastName().compareTo(client2.getLastName());
		if(comparing==0) {
			comparing=client1.getName().compareTo(client2.getName());
		}

		
		return comparing;
	}
}
