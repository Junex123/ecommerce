package coms.model.extra;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contactus {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String name;
  private String email;
  private String subject;
  private String message;
public Contactus() {
	super();
}
public Contactus(int id, String name, String email, String subject, String message) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.subject = subject;
	this.message = message;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
@Override
public String toString() {
	return "Contactus [id=" + id + ", name=" + name + ", email=" + email + ", subject=" + subject + ", message="
			+ message + "]";
}
  
  
  
  
}
