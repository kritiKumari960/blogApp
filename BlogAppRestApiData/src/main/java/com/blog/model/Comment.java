package com.blog.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private Integer cid;

	@Column(name = "name", nullable = false)
	@NotBlank(message="name can not be blank")
    @Size(min=5,max=45,message="name must be in range of 5 to 25")
	private String name;
	
	@Column(name = "body", nullable = false)
	@NotBlank(message="comment body can not be blank")
    @Size(min=5,max=45,message="comment body must be in range of 10 to 75")
	private String body;
	
	
	@NotBlank(message="comment body can not be blank")
	@Column(name = "email", nullable = false)
	@Email(message="not a valid email Id")
	private String email;
	
	@Column(name="ctime", nullable=false)
	private String ctime;
	
	public Comment() {}
	
	public Comment(Integer cid,
			@NotBlank(message = "name can not be blank") @Size(min = 5, max = 45, message = "name must be in range of 5 to 25") String name,
			@NotBlank(message = "comment body can not be blank") @Size(min = 5, max = 45, message = "comment body must be in range of 10 to 75") String body,
			@NotBlank(message = "comment body can not be blank") @Email String email, String ctime) {
		super();
		this.cid = cid;
		this.name = name;
		this.body = body;
		this.email = email;
		this.ctime = ctime;
	}


	public Integer getCid() {
		return cid;
	}


	public void setCid(Integer cid) {
		this.cid = cid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	
	
}
