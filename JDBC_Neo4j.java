package edu.iastate.cs561.hw2;
/**
 * @author Deepti Chevvuri (chevvuri@iastate.edu)
 */
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**
 * Class Used to test the JDBC Program written to query Neo4j database, contains Main method which contains queries lists below and  according performs various operations on database.
*/
public class JDBC_Neo4j {
	/**
	*Main Method-Creates a new JDBC object, gets the new connection object,
	*@param args 
 * 1) Show snames of suppliers who supply every green parts.
 *2) Show distinct sids and snames of suppliers who supply a red part or a green part.
 *3) Show distinct sids and snames of suppliers who supply a red part and a green part.
 *4) Show the pids, pnames, and the maximum cost for that part among all the suppliers.
 *Contains Main Method to load the org.neo4j.jdbc.Driver driver , collects the properties required in any, creates the connection to the neo4j db and then performs above mentioned operations
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Make sure Neo4j Driver is registered
		try {
			Class.forName("org.neo4j.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		@SuppressWarnings("unused")
		Properties properties = new Properties();
		//properties.put("user", "neo4j");
		//properties.put("password", "xx1122"); // Do not required this,  set password requirement to false
		// Connect
		java.sql.Connection con = null;
		
		try {
			
			//con = DriverManager.getConnection("jdbc:neo4j:http://localhost:7474/", properties);
			con = DriverManager.getConnection("jdbc:neo4j:http://localhost:7474/");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Querying
		try(Statement stmt = con.createStatement())
		{
		    ResultSet rs = stmt.executeQuery("MATCH (p:Parts) MATCH (s:Suppliers)-[r1:supplies]->(parts:Parts) WHERE p.color='green' AND parts.color='green' WITH  s,count(DISTINCT parts) AS c, count(DISTINCT p) AS d MATCH (s)  WHERE d=c return s.sname AS SupplierName;");
		    System.out.println("snames of suppliers who supply every green parts");
		    while(rs.next()) {
		        System.out.println(rs.getString("SupplierName"));
		    }
		    rs = stmt.executeQuery("MATCH (s:Suppliers)-[r1:supplies]->(p:Parts) WHERE p.color='green' OR p.color='red' return DISTINCT s.sid AS SupplierId,s.sname AS SupplierName;");
		    System.out.println("distinct sids and snames of suppliers who supply a red part or a green part");
		    System.out.print("Supplier ID");
	        System.out.print("-");
	        System.out.print("Supplier Name");
	        System.out.println();
		    while(rs.next()) {
		        System.out.print(rs.getString("SupplierId"));
		        System.out.print("-");
		        System.out.print(rs.getString("SupplierName"));
		        System.out.println();
		    }
		    rs = stmt.executeQuery("MATCH (s:Suppliers)-[r1:supplies]->(p:Parts) WITH s, p MATCH (s)-[r1:supplies]->(parts:Parts) WHERE p.color='green' AND parts.color='red' return DISTINCT s.sid AS SupplierId,s.sname AS SupplierName;");
		    System.out.println("distinct sids and snames of suppliers who supply a red part and a green part");
		    System.out.print("Supplier ID");
	        System.out.print("-");
	        System.out.print("Supplier Name");
	        System.out.println();
		    while(rs.next()) {
		        System.out.print(rs.getString("SupplierId"));
		        System.out.print("-");
		        System.out.print(rs.getString("SupplierName"));
		        System.out.println();
		    }
		    rs = stmt.executeQuery("MATCH (s:Suppliers)-[r1:supplies]->(p:Parts) return p.pid AS ProductId,p.pname AS ProductName,MAX(toint(r1.cost)) AS MaximumPrice;");
		    System.out.println("pids, pnames, and the maximum cost for that part among all the supplie");
		    System.out.print("Product ID");
	        System.out.print("-");
	        System.out.print("Product Name");
	        System.out.println();
	        System.out.print("Maximum Price");
	        System.out.println();
		    while(rs.next()) {
		        System.out.print(rs.getString("ProductId"));
		        System.out.print("-");
		        System.out.print(rs.getString("ProductName"));
		        System.out.print("-");
		        System.out.print(rs.getString("MaximumPrice"));
		        System.out.println();
		    }
		    con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
