package edu.iastate.cs561.hw2;
/**
 * @author Deepti Chevvuri (chevvuri@iastate.edu)
 */
import java.sql.*;

import javax.swing.*;
/**
 * Class Used to test the JDBC Program written, contains Main method which asks and takes user inputs and according performs various operations on database.
 */
public class JDBCTest
{
	/**
	*Main Method-Creates a new JDBC object, gets the new connection object,
	*@param args 
	*takes user inputs through Input dialogue box and performs the following operations, before preforming these operations we begin a new transaction
	*If “1” is entered, the program asks the user to enter a student name. It then prints sid, name, age, gender, major, and a total number of sports that the student has played.
    *If “2” is entered, for each male student, the program prints name, age, sport name that the student has played, his skill level of that sport, and his total hours spent on that sport. The result must be ordered by the student name.
    *If “3” is entered, the program prints all the names of the games that Seth has played along with the total number of hours he spent on each game.
    *If “4” is entered, the program prints the name and major of every student who has played WOW for less than 300 hours.
	*after each transaction is successfully completed the transaction is committed 
	*if an exception appears, the error is shown on console and the transaction is rolled back  
	*/
	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
		//Server url
		String dbServer = "jdbc:mysql://localhost/assignment";
		//Server username
		String userName = "root";
		//Server password
		String password = "deeps";
		//New connection object
		Connection conn= null;
		Statement stmt;
		
		try
		{
			ResultSet rs;
			//Result metadata object
			ResultSetMetaData rsMetaData;
			// Calling jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			//establishing connecion with sqlserver
			conn = DriverManager.getConnection(dbServer,userName,password);
			stmt = conn.createStatement();
			String sqlQuery = "";
			String toShow = "";
			String option = "";
			//Instruction string shown to the user 
			String instruction = "Enter 1: To find details about a particular student." + "\n" +
                    "Enter 2: Find details of all male students." + "\n" +
		             "Enter 3: Prints all the names of the games that Seth has played along with the total number of hours he spent on each game" + "\n" +
                    "Enter 4: Prints the name and major of every student who has played WOW for less than 300 hours" + "\n" +
		             "Enter 5: Quit Program";
			
			while(true)
			{
				//set auto commit on connection to false
				conn.setAutoCommit(false);
				//Receives user input and stores it in option
				option = JOptionPane.showInputDialog(instruction);
				// If input by user=1
				if(option.equals("1"))
				{
					// String to get user input
					String name=JOptionPane.showInputDialog("Enter student name");
					// SQL query that prints sid, name, age, gender, major, and a total number of sports that the student has played
					sqlQuery = "select s.sid,s.sname,s.sage,s.sgender,s.smajor,count(ss.spid) from Student s left outer join play_sport ss on ss.sid=s.sid where s.sname='"+name+"' group by s.sid";
					//executing the sql query
					rs = stmt.executeQuery(sqlQuery);
					rsMetaData = rs.getMetaData();
					System.out.println(sqlQuery);
					// if no student with the entered name exists show this message
					toShow = "No student with the given name available";
					//looping through each result and displaying required values
					while(rs.next())
					{
						toShow ="";
							toShow += "Sid- " + rs.getString(1)+ "\n";
							toShow += "Name- " + rs.getString(2)+ "\n";
							toShow += "Age- " + rs.getString(3)+ "\n";
							toShow += "Gender- " + rs.getString(4)+ "\n";
							toShow += "Major- " + rs.getString(5)+ "\n";
							toShow += "Number of Sports- " + rs.getString(6)+ "\n";
						
						toShow += "\n";
					}
					
					JOptionPane.showMessageDialog(null, toShow);
				}
				// If input by user=2
				else if(option.equalsIgnoreCase("2"))
				{
					//SQL query that prints name, age, sport name that the student has played, his skill level of that sport, and his total hours spent on that sport of all male students ordered by there name
					sqlQuery = "select s.sname,s.sage,sp.spname,ss.level,ss.totalhour from Student s  join play_sport ss on s.sid=ss.sid join sport sp on ss.spid=sp.spid where s.sgender='M' order by s.sname";
					rs = stmt.executeQuery(sqlQuery);
					//executing the sql query
					rsMetaData = rs.getMetaData();
					System.out.println(sqlQuery);
					toShow = "";
					//looping through each result and displaying required values
					while(rs.next())
					{
						toShow +="Name-" +rs.getString(1) + ", ";
						toShow +="Age-" +rs.getString(2) + ", ";
						toShow +="Sport-" +rs.getString(3) + ", ";
						toShow +="Level-" +rs.getString(4) + ", ";
						toShow +="Totalhour-" +rs.getString(5) + ", ";
						
						toShow += "\n";
					}
					JOptionPane.showMessageDialog(null, toShow);
				}
				// If input by user=3
				else if(option.equals("3"))
				{
					//SQL query that prints all the names of the games that Seth has played along with the total number of hours he spent on each game
					sqlQuery = "select g.gname,gs.totalhour from Student s inner join play_game gs on s.sid=gs.sid inner join game g on gs.gid=g.gid where s.sname='Seth'";
					//executing the sql query
					rs = stmt.executeQuery(sqlQuery);
					rsMetaData = rs.getMetaData();
					System.out.println(sqlQuery);
					toShow = "";
					//looping through each result and displaying required values
					while(rs.next())
					{
						
							toShow +="Game-" +rs.getString(1) + ", ";
							toShow +="Total Hours-" +rs.getString(2) + ", ";
						toShow += "\n";
					}
					JOptionPane.showMessageDialog(null, toShow);
				}
				//if input by user=4
				else if(option.equals("4"))
				{
					//SQL query that prints the name and major of every student who has played WOW for less than 300 hours
					sqlQuery = "select s.sname,s.smajor from Student s inner join play_game gs on gs.sid=s.sid inner join game g on gs.gid=g.gid where gs.totalhour<300 and g.gname='WOW'";
					//executing the sql query
					rs = stmt.executeQuery(sqlQuery);
					rsMetaData = rs.getMetaData();
					System.out.println(sqlQuery);
					toShow = "";
					//looping through each result and displaying required values
					while(rs.next())
					{
						toShow +="Name-" +rs.getString(1) + ", ";
						toShow +="Major-" +rs.getString(2) + ", ";
					toShow += "\n";
					}
					JOptionPane.showMessageDialog(null, toShow);
				}
				else
				{
					break;
				}
			}
			//Commit transaction
			conn.commit();
			//Reset auto commin to true
		    conn.setAutoCommit(true);
		    //close sql statement and connection
			stmt.close();
			conn.close(); 
			JOptionPane.showMessageDialog(null, "Good Bye");
		}
		catch(SQLException sqle)
		{		
	            try {
	            	//roll back the transaction if any SQL issues or SQL exception appears 
	            	System.out.println("Transaction is being rolled back");
	            	if(conn!=null)
	                conn.rollback();
	            } catch(SQLException excep) {
	            	//Print out the exception on Console
	            	System.out.println(excep);
	            }
	        
		}
		catch(Exception e)
		{
			//Print out the exception on Console
			System.out.println("There is an error:");
			e.printStackTrace();
		}
		
		
	}

}
