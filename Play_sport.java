package edu.iastate.cs561.hw2;
/**
 * @author Deepti Chevvuri (chevvuri@iastate.edu)
 */

 
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 *Class constructor mapped torelation between SPort and Student tables
  *@Entity- Marks a class as a Hibernate Entity (Mapped class)
 *@Table-Maps this class with a database table specified by name modifier. If name is not supplied it maps the class with a table having same name as the class
 */
@Entity
@Table(name="PLAY_SPORT")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.sport",
        joinColumns = @JoinColumn(name = "SPID")),
    @AssociationOverride(name = "primaryKey.student",
        joinColumns = @JoinColumn(name = "SID")) })
public class Play_sport {
private Play_sportId primaryKey=new Play_sportId();
	private String level;
	private String totalhour;
	/**
	 * Get Method to get
	 * @return  primaryKey 
	 */
	@EmbeddedId
	public Play_sportId getPrimaryKey() {
		return primaryKey;
	}
	/**
	 * Set Method to set
	 * @param  primaryKey 
	 */
	public void setPrimaryKey(Play_sportId primaryKey) {
		this.primaryKey = primaryKey;
	}
	

	/**
	 * get Method to get
	 * @return  primaryKey.sport
	 */
@Transient
public Sport getSport() 
{
    return getPrimaryKey().getSport();
}
/**
 * Set Method to set
 * @param  primaryKey.sport
 */
public void setSport(Sport sport) 
{
    getPrimaryKey().setSport(sport);
}
/**
 * get Method to get
 * @param  primaryKey.student
 */
@Transient
public Student getStudent()
{
    return getPrimaryKey().getStudent();
}
/**
 * Set Method to set
 * @param  primaryKey.student 
 */
public void setStudent(Student student) 
{
    getPrimaryKey().setStudent(student);
}
/**
 * get Method to get
 * @return  level (Sport Level)
  * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
@Column(name = "LEVEL")
public String getLevel() {
	return level;
}
/**
 * Set Method to set
 * @param  level(Sport Level)
 */
public void setLevel(String level) {
	this.level = level;
}
/**
 * get Method to get
 * @return  totalhour(Total hours played)
 * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
@Column(name = "TOTALHOUR")
public String getTotalhour() {
	return totalhour;
}
/**
 * Set Method to set
 * @param  totalhour 
 */
public void setTotalhour(String totalhour) {
	this.totalhour = totalhour;
}


}



