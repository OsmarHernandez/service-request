package org.camunda.bpm.dissiservice.servicerequest;

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
			// Write some code here
			break;
		case "createUser":
			// Write some code here
			break;
		case "assignEngineer":
			// Write some code here
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
}