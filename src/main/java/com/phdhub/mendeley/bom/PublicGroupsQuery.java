package com.phdhub.mendeley.bom;

import java.util.ArrayList;

public class PublicGroupsQuery {

	private Integer total_results;
	private Integer total_pages;
	private Integer current_page;
	private Integer items_per_page;
	private ArrayList<Group> groups;
	
	public Integer getTotal_results() {
		return total_results;
	}
	public void setTotal_results(Integer total_results) {
		this.total_results = total_results;
	}
	public Integer getTotal_pages() {
		return total_pages;
	}
	public void setTotal_pages(Integer total_pages) {
		this.total_pages = total_pages;
	}
	public Integer getCurrent_page() {
		return current_page;
	}
	public void setCurrent_page(Integer current_page) {
		this.current_page = current_page;
	}
	public Integer getItems_per_page() {
		return items_per_page;
	}
	public void setItems_per_page(Integer items_per_page) {
		this.items_per_page = items_per_page;
	}
	public ArrayList<Group> getGroups() {
		return groups;
	}
	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}
}
