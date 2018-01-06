package edu.iastate.cs561.hw2;
/**
 * @author Deepti Chevvuri (chevvuri@iastate.edu)
 */
import java.util.*;

import javax.persistence.*;
/**
 *Class constructor mapped to table 'Student'
 *@Entity- Marks a class as a Hibernate Entity (Mapped class)
 *@Table-Maps this class with a database table specified by name modifier. If name is not supplied it maps the class with a table having same name as the class
 */
@Entity
@Table(name="STUDENT")
public class Student {
	private int sid;
	private String sname;
	private String sgender;
	private String sage;
	private String smajor;
	private Set<Play_game> play_gameSet = new HashSet<Play_game>();
	private Set<Play_sport> play_sportSet = new HashSet<Play_sport>();
	/**
	 * Class constructor with
	 * @param  sname (Student Name)
	 * @param  sgender (Student Gender)
	 * @param  sage (Student Age)
	 * @param  smajor (Student Major)
	 */
	public Student(String sname, String sgender, String sage, String smajor) {
		this.setSname(sname);
		this.setSgender(sgender);
		this.setSage(sage);
		this.setSmajor(smajor);
	}
	/**
	 *Empty constructor is required by Hibernate
	 */
public  Student() {
	//Empty constructor is required by Hibernate
}
/**
 * Get Method to get
 * @return  sid (Student Id)
  * @ID-Marks this class field as a primary key column
 * @GeneratedValue-Instructs database to generate a value for this field automatically
 * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
@Id
@GeneratedValue
@Column(name = "SID")
public int getSid() {
	return sid;
}
/**
 * Set Method to set
 * @param  sid (Student Id)
 */
public void setSid(int sid) {
	this.sid = sid;
}
/**
 * Get Method to get
 * @return  sname (Student Name)
  * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
@Column(name = "SNAME")
public String getSname() {
	return sname;
}
/**
 * Set Method to set
 * @param  gname (Student Name)
 */
public void setSname(String sname) {
	this.sname = sname;
}
/**
 * Get Method to get
 * @return  sgender (Student Gender)
 * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
@Column(name = "SGENDER")
public String getSgender() {
	return sgender;
}
/**
 * Set Method to set
 * @param  sgender (Student Gender)
 */
public void setSgender(String sgender) {
	this.sgender = sgender;
}
/**
 * Get Method to get
 * @return  sage (Student Age)
  * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
@Column(name = "SAGE")
public String getSage() {
	return sage;
}
/**
 * Set Method to set
 * @param  sage (Student Age)
 */
public void setSage(String sage) {
	this.sage = sage;
}
/**
 * Get Method to get
 * @return  smajor (Student Major)
  * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
@Column(name = "SMAJOR")
public String getSmajor() {
	return smajor;
}
/**
 * Set Method to set
 * @param  smajor (Student Major)
 */
public void setSmajor(String smajor) {
	this.smajor = smajor;
}
/**
 * Get Method to get
 * @return  play_gameSet (Set<Play_game>)
  * @OneToMany-Marks this field as the owning side of the one-to-many relationship and 
 * cascade modifier specifies which operations should cascade to the inverse side of relationship
 * mappedBy-modifier holds the field which specifies the inverse side of the relationship
 */
@OneToMany(mappedBy = "primaryKey.student", cascade = CascadeType.ALL)
public Set<Play_game> getPlay_gameSet() {
	return play_gameSet;
}
public void setPlay_gameSet(Set<Play_game> play_gameSet) {
	this.play_gameSet = play_gameSet;
}
/**
 * Get Method to get
 * @return  play_sportSet (Set<Play_sport>)
  * @OneToMany-Marks this field as the owning side of the one-to-many relationship and 
 * cascade modifier specifies which operations should cascade to the inverse side of relationship
 * mappedBy-modifier holds the field which specifies the inverse side of the relationship
 */
@OneToMany(mappedBy = "primaryKey.student", cascade = CascadeType.ALL)
public Set<Play_sport> getPlay_sportSet() {
	return play_sportSet;
}
/**
 * Set Method to set
 * @param  play_sportSet (Set<Play_sport>)
 */
public void setPlay_sportSet(Set<Play_sport> play_sportSet) {
	this.play_sportSet = play_sportSet;
}






}




