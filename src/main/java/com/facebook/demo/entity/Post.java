package com.facebook.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
@Entity
public class Post {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id ;
	
	@Column(name="content" , length=50)
	private String content;
	@Lob
	private String imgUrl;
	
	
	
	

	
}