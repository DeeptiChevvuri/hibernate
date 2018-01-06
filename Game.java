package edu.iastate.cs561.hw2;
/**
 * @author Deepti Chevvuri (chevvuri@iastate.edu)
 */
import java.util.*;
import javax.persistence.*;

/**
 *Class constructor mapped to table 'Game'
 *@Entity- Marks a class as a Hibernate Entity (Mapped class)
 *@Table-Maps this class with a database table specified by name modifier. If name is not supplied it maps the class with a table having same name as the class
 */
@Entity
@Table(name="GAME")
public class Game {
	private int gid;
	private String gname;
	private String ggenre;
	
	private Set<Play_game> play_gameSet = new HashSet<Play_game>();
	/**
	 * Class constructor with
	 * @param  gname (Game Name)
	 * @param  ggenre (Game Genre) 
	 */
	public Game(String gname, String ggenre)
	{
		this.setGname(gname);
		this.setGgenre(ggenre);
	}
	/**
	 *Empty constructor is required by Hibernate
	 */
public Game() {
	//Empty constructor is required by Hibernate
}
/**
 * Get Method to get
 * @return  gid (Game ID)
 * @ID-Marks this class field as a primary key column
 * @GeneratedValue-Instructs database to generate a value for this field automatically
 * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
@Id
@GeneratedValue
@Column(name = "GID")
public int getGid() {
	return gid;
}
/**
 * Set Method to set
 * @param  gid (int)
 */
public void setGid(int gid) {
	this.gid = gid;
}
/**
 * Get Method to get
 * @return  gname (Game Name)
 * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
@Column(name = "GNAME")
public String getGname() {
	return gname;
}
/**
 * Set Method to set
 * @param  gname (Game Name)
 */
public void setGname(String gname) {
	this.gname = gname;
}
/**
 * Get Method to get
 * @return  ggenre (Game Genre)
 * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
@Column(name = "GGENRE")
public String getGgenre() {
	return ggenre;
}
/**
 * Set Method to set
 * @param  ggenre (Game genre)
 */
public void setGgenre(String ggenre) {
	this.ggenre = ggenre;
}
/**
 * Get Method to get
 * @return  play_gameSet (Set<Play_game>)
 * @OneToMany-Marks this field as the owning side of the one-to-many relationship and 
 * cascade modifier specifies which operations should cascade to the inverse side of relationship
 * mappedBy-modifier holds the field which specifies the inverse side of the relationship
 */
@OneToMany(mappedBy = "primaryKey.game", cascade = CascadeType.ALL)
public Set<Play_game> getPlay_gameSet() {
	return play_gameSet;
}
/**
 * Set Method to set
 * @param  play_gameSet (Set<Play_game>)
 */
public void setPlay_gameSet(Set<Play_game> play_gameSet) {
	this.play_gameSet = play_gameSet;
}








}
