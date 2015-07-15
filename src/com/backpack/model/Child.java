package com.backpack.model;

public class Child {
	private int childId;
	private String firstName;
	private String lastName;
	private int backpack;
	private int healthCheck;
	private int haircut;
	public int getChildId() {
		return childId;
	}
	public void setChildId(int childId) {
		this.childId = childId;
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
	public int getBackpack() {
		return backpack;
	}
	public void setBackpack(int backpack) {
		this.backpack = backpack;
	}
	public int getHealthCheck() {
		return healthCheck;
	}
	public void setHealthCheck(int healthCheck) {
		this.healthCheck = healthCheck;
	}
	public int getHaircut() {
		return haircut;
	}
	public void setHaircut(int haircut) {
		this.haircut = haircut;
	}
	@Override
	public String toString() {
		String strBackpack = getBackpack() > 0?"1":"0";
		String strHealthCheck = getHealthCheck() > 0?"1":"0";
		String strHaircut = getHaircut() > 0?"1":"0";
		return String.valueOf(childId) + "," + getFirstName() + "," + getLastName() + ","
				+ strBackpack + "," + strHealthCheck + "," + strHaircut;
	}
}
