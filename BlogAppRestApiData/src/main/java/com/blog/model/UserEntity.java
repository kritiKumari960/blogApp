package com.blog.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {
	
	
	@Id
	@Column(name = "userId")
//	@GeneratedValue(generator = "system-uuid")
	private String userId;
	
	@Transient
	private String password;
	
	@Column(name = "pwd")
	//@JsonIgnore
	private String encodedPassword;
	
	@Column(name = "fname")
	private String fname;
	
	@Column(name = "lname")
	private String lname;
	
	@Column(name = "email")
	@Email
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_uid", referencedColumnName = "userId")
	private List<Post> post;
	
	public UserEntity() { }

	public UserEntity(String userId, String password, String encodedPassword, String fname, String lname,
			@Email String email, String phone) {
		super();
		this.userId = userId;
		this.password = password;
		this.encodedPassword = encodedPassword;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
