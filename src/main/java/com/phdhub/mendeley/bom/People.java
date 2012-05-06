package com.phdhub.mendeley.bom;

import java.util.ArrayList;

public class People {
	private ArrayList<Users> admins;
	private ArrayList<Users> followers;
	private ArrayList<Users> members;
	private Users owner;
	
	public ArrayList<Users> getAdmins() {
		return admins;
	}
	public void setAdmins(ArrayList<Users> admins) {
		this.admins = admins;
	}
	public ArrayList<Users> getFollowers() {
		return followers;
	}
	public void setFollowers(ArrayList<Users> followers) {
		this.followers = followers;
	}
	public ArrayList<Users> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<Users> members) {
		this.members = members;
	}
	public Users getOwner() {
		return owner;
	}
	public void setOwner(Users owner) {
		this.owner = owner;
	}
}
