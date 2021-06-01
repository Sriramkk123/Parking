package com.sriram.parking.users;

//Each user has to have a account, Administrator and Attendant extend this class
public abstract class Account {
	private String username;
	private String password;
	private Person person;
	
	public Account(String username, String password,Person person)
	{
		this.username = username;
		this.password = password;
		this.person = person;
	}
}
