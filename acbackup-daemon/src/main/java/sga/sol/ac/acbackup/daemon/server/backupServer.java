package sga.sol.ac.acbackup.daemon.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.CannotGetJdbcConnectionException;


public class backupServer {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ApplicationContext applicationContext;
	
	public backupServer() {
		try {
			this.applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
		catch(BeanCreationException e) {
			//BeanCreationException BeanInstantiationException MyBatisSystemException PersistenceException CannotGetJdbcConnectionException
			Throwable cause = e.getCause();
			Throwable rootCause = e.getRootCause();
			
			//e.printStackTrace();
			logger.debug(e.getMessage(), e);
			if(e.contains(CannotGetJdbcConnectionException.class)) {
				logger.error("Database connection timeout.");
			}
			throw e;
		}
	}
	
	public synchronized void start(){
		ServerProperty serverProperty = applicationContext.getBean("serverProperty", ServerProperty.class);
		
	}
}







