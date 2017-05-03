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
		
		// Test
		if (ACTIVITY.equals("createUser")) {
			LOGGER.info("Current Activity Detected: " + ACTIVITY);
		} else if (ACTIVITY.equals("assignEngineer")) {
			LOGGER.info("Current Activity Detected: " + ACTIVITY);
		}
	}
}