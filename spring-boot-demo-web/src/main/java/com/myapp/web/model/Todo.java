package com.myapp.web.model;

import java.util.Date;

import javax.validation.constraints.Size;

public class Todo {

	private int id;
	private String user;

	@Size(min = 10, message = "Please enter at least 10 characters.")
	private String desc;

	private Date targetDate;
	private boolean done;

	@Override
	public String toString() {
		return "id=" + id + " user=" + user + " desc=" + desc + " targetdate=" + targetDate + " isDone=" + done;
	}

	public Todo(int id, String user, String desc, Date targetDate, boolean isDone) {
		super();
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.targetDate = targetDate;
		this.done = isDone;
	}

	@Override
	public boolean equals(Object obj) {

		return (((Todo) obj).getId() == this.id);
	}

	public Todo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
