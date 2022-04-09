package com.facebook.demo.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity 
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;

  private String name;

  private String email;
  private String phone;
  @Lob
  private   byte[]  photoUrl;
  @Lob 
  private byte[] image;
  public byte[] getImage() {
	return image;
}


public void setImage(byte[] image) {
	this.image = image;
}


public long getId() {
    return id;
  }
  

  public byte[] getPhotoUrl() {
	return photoUrl;
}

	public void setPhotoUrl(byte[] in) {
		this.photoUrl = in;
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



@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + "+ ]" ;
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
   