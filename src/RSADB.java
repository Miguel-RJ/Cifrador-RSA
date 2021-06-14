import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RSADB {

	public RSADB() throws ClassNotFoundException {
		// TODO Auto-generated constructor stub
		
	}
	
	public static void LogIn(String User, String Pwrd) throws ClassNotFoundException
	{
		// load the sqlite-JDBC driver using the current class loader
	      Class.forName("org.sqlite.JDBC");
	      String Message = "Usuario o Contraseña incorrectos";

	      Connection connection = null;
	      try
	      {
	         // create a database connection
	         connection = DriverManager.getConnection("jdbc:sqlite:sqlite/RSA.db");

	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(30);  // set timeout to 30 sec.
	         ResultSet resultSet = statement.executeQuery("SELECT * from Users WHERE UserId = '" + User + "' AND Password = '"+ Pwrd + "';");
	         while(resultSet.next())
	         {
	            // iterate & read the result set
	        	 Menu menu = new Menu();
	        	 menu.setVisible(true);
	        	 Message = "Bienvenido al Cifrado RSA";
	        	 break;
	         }
	           
             JOptionPane.showMessageDialog(null, Message);
	           
	       }

	      catch(SQLException e)
	      {  
	    	 System.err.println(e.getMessage()); 
	      }       
	      finally 
	      {         
	        try 
	        {
	        	if(connection != null)
	                 connection.close();
	        }
	        catch(SQLException e)
	        {  // Use SQLException class instead.          
	           System.err.println(e); 
	        }
	      }
	}

}
