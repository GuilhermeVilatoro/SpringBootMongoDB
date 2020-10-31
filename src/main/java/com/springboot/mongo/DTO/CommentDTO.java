package com.springboot.mongo.DTO;

import java.io.Serializable;
import java.util.Date;

public class CommentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String text;
	private Date date;

	public CommentDTO() {
	}

	public CommentDTO(String id, String text, Date date) {
		this.id = id;
		this.text = text;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
