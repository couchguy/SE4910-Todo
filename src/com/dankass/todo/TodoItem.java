package com.dankass.todo;

public class TodoItem {

	private long id;
	private String name;
	private String description;
	private String date;
	private String status;
	private String priority;
	
	public TodoItem(){
		
	}
	public TodoItem(String name, String description, String date, String priority, String status) {
		//this way the setters can do the error handleing.
		this.setName(name);
		this.setDescription(description);
		this.setDate(date);
		this.setPriority(priority);
		this.setStatus(status);
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		//if status doesn't equal allowed state then make it Not Stared
		if(!(status.equalsIgnoreCase("Completed") || 
				status.equalsIgnoreCase("Started") || 
				status.equalsIgnoreCase("Not Started"))){
			status = "Not Started";
		}
		this.status = status;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		if(!(priority.equalsIgnoreCase("low") || 
				priority.equalsIgnoreCase("medium") || 
				priority.equalsIgnoreCase("high"))){
			priority = "low";
		}
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return name + "/" + date + "/" + priority;
		
	}
	

}
