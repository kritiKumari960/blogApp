package com.blog.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private Integer pId;
	
	@Column(name = "title", nullable = false)
	@NotBlank(message="title can not be blank")
	private String title;
	
	@Column(name = "content", nullable = false)
	@NotBlank(message="Content can not be blank")
	private String content;
	
	@Column(name="createTime", nullable=false)
	private String createTime;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_pid", referencedColumnName = "pId")
	private List<Comment> comment;
	
	public Post() {
		
	}

	public Post(Integer pId, @NotBlank(message = "title can not be blank") String title,
			@NotBlank(message = "Content can not be blank") String content, String createTime) {
		super();
		this.pId = pId;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		  
		this.createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	
//	private void onCreate() {
//		createTime = new LocalDate();
//	}
	
}
