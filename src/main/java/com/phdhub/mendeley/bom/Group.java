package com.phdhub.mendeley.bom;

public class Group {
	private Integer id;
	private String name;
	private String owner;
	private Disciplines disciplines;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Disciplines getDisciplines() {
		return disciplines;
	}
	public void setDisciplines(Disciplines disciplines) {
		this.disciplines = disciplines;
	}
}
