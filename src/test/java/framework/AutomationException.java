package framework;

import org.apache.log4j.Logger;

public class AutomationException extends RuntimeException{
	
	/**
	 * reference variable for logger
	 */
	private static final Logger log = Logger.getLogger("ecomm");
	
	public AutomationException(String errorDescription) {
		log.fatal("Automation was interrupted due to: " + errorDescription);
		
	}
	

}
