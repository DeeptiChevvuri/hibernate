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
 *Class constructor mapped torelation between Game and Student tables
 *@Entity- Marks a class as a Hibernate Entity (Mapped class)
 *@Table-Maps this class with a database table specified by name modifier. If name is not supplied it maps the class with a table having same name as the class
 */
@Entity
@Table(name="PLAY_GAME")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.game",
        joinColumns = @JoinColumn(name = "GID")),
    @AssociationOverride(name = "primaryKey.student",
        joinColumns = @JoinColumn(name = "SID")) })
public class Play_game {
	//Composite ID
	private Play_gameId primaryKey = new Play_gameId();
	
	private String platform;
	private String totalhour;
	/**
	 * Get Method to get
	 * @return  primaryKey 
	 */
@EmbeddedId
public Play_gameId getPrimaryKey() {
	return primaryKey;
}
/**
 * Set Method to set
 * @param  primaryKey 
 */
public void setPrimaryKey(Play_gameId primaryKey) {
	this.primaryKey = primaryKey;
}
/**
 * get Method to get
 * @return  primaryKey.game
 */
@Transient
	    public Game getGame() 
	    {
	        return getPrimaryKey().getGame();
	    }
/**
 * Set Method to set
 * @param  primaryKey.game
 */
	    public void setGame(Game game) 
	    {
	        getPrimaryKey().setGame(game);
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
	     * @return  platform(Game Platform)
	     * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
@Column(name = "PLATFORM")
public String getPlatform() {
	return platform;
}
/**
 * Set Method to set
 * @param  platform (Game Platform)
 */
public void setPlatform(String platform) {
	this.platform = platform;
}
/**
 * get Method to get
 * @return  totalhour(Total Hours)
 * @Column-	Maps this field with table column specified by name and uses the field name if name modifier is absent
 */
@Column(name = "TOTALHOUR")
public String getTotalhour() {
	return totalhour;
}
/**
 * Set Method to set
 * @param  totalhour (Total Hours)
 */
public void setTotalhour(String totalhour) {
	this.totalhour = totalhour;
}


}


