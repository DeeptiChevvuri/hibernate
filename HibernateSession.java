package edu.iastate.cs561.hw2;
/**
 * @author Deepti Chevvuri (chevvuri@iastate.edu)
 */
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
/**
 *Class constructor to create and start a hibernate session from hibernate.cfg.xml and creates a Configuration object to obtain a SessionFactory object
 */
public class HibernateSession 
{
	private SessionFactory sessionFactory;
	// A SessionFactory is set up once for an application!
	
	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
					.configure() // configures settings from hibernate.cfg.xml
					.build();
	/**
	 *Empty class constructor
	 */
	public HibernateSession()
	{
		
	}
	/**
	 *Method used to setup a new the session factory object
	 */
	protected void setUp() throws Exception 
	{
		try 
		{
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) 
		{
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
			System.out.println(e.toString());
		}
	}
	/**
	 *Method used to access the session factory object
	 */
	public SessionFactory getSessionFactory()
	{
		return this.sessionFactory;
	}
}
