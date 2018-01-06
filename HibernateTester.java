package edu.iastate.cs561.hw2;
/**
 * @author Deepti Chevvuri (chevvuri@iastate.edu)
 */
import org.hibernate.*;
import org.hibernate.boot.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.*;

/**
 * Class Used to test the Hibernate Program written, contains Main method which asks and takes user inputs and according performs various operations on database.
 */
@SuppressWarnings("unused")
public class HibernateTester 
{
	/**
	*Main Method-Creates a new HibernateSession object, gets the session factory using the HibernateSession object,
	*@param args 
	*takes user inputs through Input dialogue box and performs the following operations, before preforming these operations we begin a new transaction
	*If “1” is entered, the program asks the user to enter a student name. It then prints sid, name, age, gender, major, and a total number of sports that the student has played.
    *If “2” is entered, for each male student, the program prints name, age, sport name that the student has played, his skill level of that sport, and his total hours spent on that sport. The result must be ordered by the student name.
    *If “3” is entered, the program prints all the names of the games that Seth has played along with the total number of hours he spent on each game.
    *If “4” is entered, the program prints the name and major of every student who has played WOW for less than 300 hours.
	*after each transaction is successfully completed the transaction is committed
	*if an exception appears, the error is shown on console the transaction is rolled back  
	*/
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) 
	{
		//Creates a new HibernateSession
		HibernateSession hibernateSession = new HibernateSession();
		// SessionFactory and Session variables declared
		SessionFactory sessionFactory;
		Session session= null;
		String option = "";
		//Instruction string shown to the user 
		String instruction = "Enter 1: To find details about a particular student." + "\n" +
                "Enter 2: Find details of all male students." + "\n" +
	             "Enter 3: Prints all the names of the games that Seth has played along with the total number of hours he spent on each game" + "\n" +
                "Enter 4: Prints the name and major of every student who has played WOW for less than 300 hours" + "\n" +
	             "Enter 5: Quit Program";
		try
		{
			while(true)
			{
				//Receives user input and stores it in option
				option = JOptionPane.showInputDialog(instruction);
				//setting up a new hibernate session
				hibernateSession.setUp();
				sessionFactory = hibernateSession.getSessionFactory();
				session = sessionFactory.openSession();
				//begin the transaction
				session.beginTransaction();
				// If input by user=1
				if(option.equals("1"))
				{
					// String to get user input
					String enterStudentId="Enter a student name";
					String name = JOptionPane.showInputDialog(enterStudentId);
					// HQL query that prints sid, name, age, gender, major, and a total number of sports that the student has played
					List results = session.createQuery("select s.sid,s.sname,s.sage,s.sgender,s.smajor,count(ss) from Student s left outer join s.play_sportSet ss where s.sname= :sname group by s").setParameter("sname", name).getResultList();
					String toShow = "Student Details:\n";
					if (results.size()>0){
						for(int i=0;i<results.size();i++)
					{
							Object[] o = (Object[])results.get(i);
							toShow += "Sid- " + o[0].toString()+ "\n" +"Name- "  + o[1].toString() +"\n"+"Age- "  + o[2].toString() +"\n"+"Gender- "  + o[3].toString() +"\n"+"Major- "  + o[4].toString() +"\n"+"Number of Sports- "  + o[5].toString() +"\n";
					}
				}
				else
					toShow +="No student with such name exists.\n";// if no student with the entered name exists show message
					JOptionPane.showMessageDialog(null, toShow);
				}
				//if input by user=2
				else if(option.equals("2"))
				{
					//HQL query that prints name, age, sport name that the student has played, his skill level of that sport, and his total hours spent on that sport of all male students ordered by there name
					List results = session.createQuery("select s.sname,s.sage,ss.primaryKey.sport.spname,ss.level,ss.totalhour from Student s  join s.play_sportSet ss where s.sgender= :sgender order by s.sname").setParameter("sgender", "M").getResultList();
					String toShow = "Student Details:\n";
				
						for(int i=0;i<results.size();i++)
						{
							Object[] o = (Object[])results.get(i);
							toShow += "Name- " + o[0].toString()+ "\n"+"Age- "  + o[1].toString() +"\n"+"Sport- "  + o[2].toString()+"\n"+"Skill Level- "  + o[3].toString() +"\n"+"Hours Spent- "  + o[4].toString() +"\n\n"  ;
				}
					JOptionPane.showMessageDialog(null, toShow);
				}
				//if input by user=3
				else if(option.equals("3"))
				{
					//HQL query that prints all the names of the games that Seth has played along with the total number of hours he spent on each game
					List results = session.createQuery("select gs.primaryKey.game.gname,gs.totalhour from Student s inner join s.play_gameSet gs  where s.sname= :sname").setParameter("sname", "Seth").getResultList();
					String toShow = "Student Details:\n";
				
						for(int i=0;i<results.size();i++)
						{
					
							Object[] o = (Object[])results.get(i);
							toShow += "Game Name- " + o[0].toString()+ "\n"+"Number of hours spent- "  + o[1].toString() +"\n";//+"Sport- "  + o[2].toString()+"\n"+"Skill Level- "  + o[3].toString() +"\n"+"Hours Spent- "  + o[4].toString() +"\n\n"  ;
					}
					JOptionPane.showMessageDialog(null, toShow);
				}
				//if input by user=4
				else if(option.equals("4"))
				{
					//HQL query that prints the name and major of every student who has played WOW for less than 300 hours
					List results = session.createQuery("select gs.primaryKey.student.sname,s.smajor from Student s inner join s.play_gameSet gs where (gs.totalhour<300 and gs.primaryKey.game.gname=:gname)").setParameter("gname", "WOW").getResultList();
					String toShow = "Student Details:\n";
				
						for(int i=0;i<results.size();i++)
						{
						
							Object[] o = (Object[])results.get(i);
							toShow += "Name- " + o[0].toString()+ "\n"+"Major- "  + o[1].toString() +"\n";//+"Sport- "  + o[2].toString()+"\n"+"Skill Level- "  + o[3].toString() +"\n"+"Hours Spent- "  + o[4].toString() +"\n\n"  ;
					}
					JOptionPane.showMessageDialog(null, toShow);
				}
				else
				{
					break;
				}
				
				// Commit transaction
				session.getTransaction().commit();
				//Close Hibernate Session
				session.close();
				
			}
			
			JOptionPane.showMessageDialog(null, "Good Bye");
		}
		// Roll back the transaction if any exception accurs, Catch all exceptions and print them out on console
		catch(Exception e)
		{
			if(session != null)
			session.getTransaction().rollback();
			System.out.println("There is an error:");
			System.out.println(e.toString());
		}
	}

}
