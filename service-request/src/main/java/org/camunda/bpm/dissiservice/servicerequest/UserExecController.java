package org.camunda.bpm.dissiservice.servicerequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

/* *
 * Execution Listener
 * Execution listeners allow you to execute external Java code or 
 * evaluate an expression when certain events occur during process execution. 
 * */
public class UserExecController implements ExecutionListener {

	/* *
	 * Test Variables
	 * */
	static Connection connection = null;
	static PreparedStatement preparedStatement = null;
	
	/* *
	 * Global Variables
	 * 	LOGGER: Used to log messages for a specific system or application component.
	 * */
	private final static Logger LOGGER = Logger.getLogger("SERVICE-REQUEST");
	
	public void notify(DelegateExecution execution) throws Exception {
		
		/* *
		 * Local Variables
		 * 	ACTIVITY: Tracks the current User Task Id
		 * */
		final String ACTIVITY = execution.getCurrentActivityId();
		
		/* *
		 * Testing program values
		 * The User Task Id must be assigned in the "service-request.bpmn"
		 * 				UserTask > General > Id
		 * */
		LOGGER.info("****** Testing program variables ******");
		
		switch (ACTIVITY) {
		case "startEvent":
			connectToDB();
			break;
		case "createUser":
			addDataToDB(2, "Osmar Hernandez", "656 108 1010");
			break;
		case "assignEngineer":
			getDataFromDB();
			break;
		case "identifyMaterials":
			// Write some code here
			break;
		case "productCuantity":
			// Write some code here
			break;
		case "estimatedTime":
			// Write some code here
			break;
		case "calculateCost":
			// Write some code here
			break;
		case "searchTemplate":
			// Write some code here
			break;
		case "generalData":
			// Write some code here
			break;
		case "validateService":
			// Write some code here
			break;
		case "saveQuote":
			// Write some code here
			break;
		case "saveRequest":
			// Write some code here
			break;
		case "endEvent":
			// Write some code here
			break;
		default:
			break;
		}
	}
	
	private static void connectToDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			LOGGER.info("****** MySQL JDBC Driver Registered ******");
		} catch (ClassNotFoundException e) {
			LOGGER.info("****** Couldn't found JDBC driver ******");
			return;
		}
 
		try {
			// DriverManager: The basic service for managing a set of JDBC drivers.
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Dissi", "username", "password");
			if (connection != null) {
				LOGGER.info("****** Connection Successful ******");
			} else {
				LOGGER.info("****** Failed to make connection ******");
			}
		} catch (SQLException e) {
			LOGGER.info("****** MySQL Connection Failed ******");
			return;
		}
	}
	
	private static void addDataToDB(int id, String nombre, String telefono) { 
		try {
			String insertQueryStatement = "INSERT INTO Ingenieros VALUES (?,?,?)";
 
			preparedStatement = connection.prepareStatement(insertQueryStatement);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, nombre);
			preparedStatement.setString(3, telefono);
 
			// execute insert SQL statement
			preparedStatement.executeUpdate();
			LOGGER.info("****** Added successfully ******");
		} catch (
 
		SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void getDataFromDB() {
		try {
			// MySQL Select Query Tutorial
			String getQueryStatement = "SELECT * FROM Ingenieros";
 
			preparedStatement = connection.prepareStatement(getQueryStatement);
 
			// Execute the Query, and get a java ResultSet
			ResultSet resultSet = preparedStatement.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (resultSet.next()) {
				LOGGER.info("****** " + resultSet.getString("nombre") + " ******");
			}
 
		} catch (
 
		SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void disconnectFromDB() throws SQLException {
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
}