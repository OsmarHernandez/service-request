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
			addDataToDB(ACTIVITY, execution);
			break;
		case "createUser":
			addDataToDB(ACTIVITY, execution);
			break;
		case "assignEngineer":
			updateDataFromDB(ACTIVITY, execution);
			break;
		case "identifyMaterials":
			// Write some code here
			break;
		case "productCuantity":
			// Write some code here
			break;
		case "estimatedTime":
			updateDataFromDB(ACTIVITY, execution);
			break;
		case "calculateCost":
			// Write some code here
			break;
		case "searchTemplate":
			getDataFromDB(ACTIVITY, execution);
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
			disconnectFromDB();
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
	
	private static void addDataToDB(String activityId, DelegateExecution exec) { 
		String insertQueryStatement;
		try {
			int i = 1;
			String values[];
			switch (activityId) {
			case "startEvent":
				values = new String[] {"cotizacionId", "clienteId"};
				insertQueryStatement = "INSERT INTO Cotizaciones VALUES (?,?,?, null, null)";
				
				preparedStatement = connection.prepareStatement(insertQueryStatement);
				
				for(String value : values) {
					preparedStatement.setInt(i, (int) exec.getVariable(value));
					i++;
				}
				preparedStatement.setString(3, (String) exec.getVariable("fechaRealizada"));
				break;
			case "createUser":
				values =  new String[] {"nombre", "correo", "telefono", "domicilio", "rfc", "contacto",
									"razonSocial", "departamento"};
				insertQueryStatement = "INSERT INTO Clientes VALUES (?,?,?,?,?,?,?,?,?)";
				
				preparedStatement = connection.prepareStatement(insertQueryStatement);
				
				for(String value : values) {
					preparedStatement.setString(i, (String) exec.getVariable(value));
					i++;
				}
				preparedStatement.setInt(i, (int) exec.getVariable("id"));
				break;
			default:
				break;
			}
 
			// execute insert SQL statement
			preparedStatement.executeUpdate();
			LOGGER.info("****** Added successfully ******");
		} catch (
 
		SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void updateDataFromDB(String activityId, DelegateExecution exec) {
		String insertQueryStatement;
		try {
			switch (activityId) { 
			case "assignEngineer":
				insertQueryStatement = "UPDATE Cotizaciones SET ingeniero_asignado_fk = ? WHERE cotizacion_id = ?";
				preparedStatement = connection.prepareStatement(insertQueryStatement);
				preparedStatement.setInt(1, (int) exec.getVariable("ingenieroId"));
				preparedStatement.setInt(2, (int) exec.getVariable("cotizacionId"));
				break;
			case "estimatedTime":
				insertQueryStatement = "UPDATE Cotizaciones SET ingeniero_asignado_fk = ? WHERE cotizacion_id = ?";
				preparedStatement = connection.prepareStatement(insertQueryStatement);
				preparedStatement.setString(1, (String) exec.getVariable("tiempoEstimado"));
				preparedStatement.setInt(2, (int) exec.getVariable("cotizacionId"));
				break;
			default:
				break;
			}
			// execute update SQL statement
			preparedStatement.executeUpdate();
			LOGGER.info("****** Updated successfully ******");
		} catch (
 
		SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void getDataFromDB(String activityId, DelegateExecution exec) {
		String getQueryStatement;
		try {
			switch (activityId) {
			case "searchTemplate":
				getQueryStatement = "Select nombre From Ingenieros where ingeniero_id=?";
				preparedStatement = connection.prepareStatement(getQueryStatement);
				preparedStatement.setInt(1, (int) exec.getVariable("ingenieroId"));
				ResultSet resultSet = preparedStatement.executeQuery();
				// Let's iterate through the java ResultSet
				while (resultSet.next()) {
					LOGGER.info("****** " + resultSet.getString("nombre") + " ******");
					exec.setVariable("nombre", "ingenieroNombre");
				}
				break;
			default:
				break;
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
			LOGGER.info("****** Disconnected ******");
		}
	}
}