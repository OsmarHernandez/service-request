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

	private final static Logger LOGGER = Logger.getLogger("SERVICE-REQUEST");
	
	public void notify(DelegateExecution execution) throws Exception {
		
		/* *
		 * Testing program values
		 * the ACTIVITY String constant refers to the actual User Task Id
		 * 
		 * The User Task Id must be assigned in the "service-request.bpmn"
		 * 				UserTask > General > Id
		 * */
		
		final String ACTIVITY = execution.getCurrentActivityId();
		
		LOGGER.info("****** Testing program variables ******");
		
		switch (ACTIVITY) {
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
		default:
			break;
		}
	}
}