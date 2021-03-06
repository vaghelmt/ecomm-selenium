package framework;

import org.apache.log4j.Logger;

public class AutomationException extends RuntimeException {

	/**
	 * reference variable for logger
	 */
	private static final Logger log = Logger.getLogger(AutomationException.class);
	private String errorMessage;

	public AutomationException(String errorDescription) {
		this.errorMessage = errorDescription;
		log.fatal("Automation was interrupted due to: " + errorDescription);

	}

	public String getErrorMessage() {
		log.info("getErrorMessage() is invoked ");
		return errorMessage;

	}

}
