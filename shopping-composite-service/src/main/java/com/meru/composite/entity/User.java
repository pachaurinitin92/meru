package com.meru.composite.entity;


import java.util.List;


public class User {

	private Long id;

    private String firstName;
	
    private String lastName;
    
    private String email;
	
	private String password;
	
    private String phoneNumber;

	private List<Address> addressList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public void addAddress(Address address) {
		addressList.add(address);
		address.setUser(this);
    }
 
    public void removeComment(Address address) {
    	addressList.remove(address);
    	address.setUser(null);
    }
}
