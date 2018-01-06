package edu.iastate.cs561.hw2;
/**
 * @author Deepti Chevvuri (chevvuri@iastate.edu)
 */

import java.util.*;

import javax.persistence.*;
/**
 *Class constructor mapped to table 'Sport'
 *@Entity- Marks a class as a Hibernate Entity (Mapped class)
 *@Table-Maps this class with a database table specified by name modifier. If name is not supplied it maps the class with a table having same name as the class
 */
@Entity
@Table(name="SPORT")
public class Sport {
	private int spid;
	private String spname;
	private String sptype;
	private Set<Play_sport> play_sportSet = new HashSet<Play_sport>();
	/**
	 * Class constructor with
	 * @param  spname (Sport Name)
	 * @param  sptype (Sport Type)
	 * @ID-Marks this class field as a primary key column
 * @GeneratedValue-Instructs database to generate a value for this field automatically
 * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
	public Sport(String spname, String sptype) {
		this.setSpname(spname);
		this.setSptype(sptype);
	}
	/**
	 *Empty constructor is required by Hibernate
	 */
public  Sport() {
	//Empty constructor is required by Hibernate
}
/**
 * Get Method to get
 * @return  spid (Sport ID)
 * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
@Id
@GeneratedValue
@Column(name = "SPID")
public int getSpid() {
	return spid;
}
/**
 * Set Method to set
 * @param  spid (Sport Id)
 */
public void setSpid(int spid) {
	this.spid = spid;
}
/**
 * Get Method to get
 * @return  spname (Sport Name)
  * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
@Column(name = "SPNAME")
public String getSpname() {
	return spname;
}
/**
 * Set Method to set
 * @param  spname (Sport Name)
 */
public void setSpname(String spname) {
	this.spname = spname;
}
/**
 * Set Method to set
 * @param  sptype (Sport Type)
 * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
@Column(name = "SPTYPE")
public String getSptype() {
	return sptype;
}
/**
 * Get Method to get
 * @return  sptype (Sport Type)
 */
public void setSptype(String sptype) {
	this.sptype = sptype;
}

/**
 * Get Method to get
 * @return  play_sportSet (Set<Play_sport>)
 * @OneToMany-Marks this field as the owning side of the one-to-many relationship and 
 * cascade modifier specifies which operations should cascade to the inverse side of relationship
 * mappedBy-modifier holds the field which specifies the inverse side of the relationship
 */
@OneToMany(mappedBy = "primaryKey.sport", cascade = CascadeType.ALL)
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

