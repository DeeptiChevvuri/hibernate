package edu.iastate.cs561.hw2;
/**
 * @author Deepti Chevvuri (chevvuri@iastate.edu)
 */
import java.io.Serializable; 

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
/**
 *Serializable Class constructor to map composite key
 */
@SuppressWarnings("serial")
@Embeddable
public class Play_sportId implements Serializable {
	private Student student;
	private Sport sport;
	/**
	 * Get Method to get
	 * @return  student 
	 * @ManyToOne-Marks this field as the owning side of the many-to-one relationship and 
 * cascade modifier specifies which operations should cascade to the inverse side of relationship
 * mappedBy-modifier holds the field which specifies the inverse side of the relationship
 */
	@ManyToOne(cascade = CascadeType.ALL)
	public Student getStudent() {
		return student;
	}
	/**
	 * set Method to set
	 * @param  student 
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
	
	/**
	 * Get Method to get
	 * @return  sport 
		 * @ManyToOne-Marks this field as the owning side of the many-to-one relationship and 
 * cascade modifier specifies which operations should cascade to the inverse side of relationship
 * mappedBy-modifier holds the field which specifies the inverse side of the relationship*/
    @ManyToOne(cascade = CascadeType.ALL)
    public Sport getSport() {
		return sport;
	}
    /**
	 * set Method to set
	 * @param  sport 
	 */
    public void setSport(Sport sport) {
		this.sport = sport;
	}
	
}
