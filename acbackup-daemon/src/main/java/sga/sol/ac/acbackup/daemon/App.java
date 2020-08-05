package sga.sol.ac.acbackup.daemon;

import org.apache.commons.daemon.DaemonInitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sga.sol.ac.acbackup.daemon.handler.DaemonHandler;

public class App {
	private final static Logger logger = LoggerFactory.getLogger(App.class);
	private static Object lock = new Object();
	
	private static DaemonHandler daemon;
	
	public static void main(String[] args) throws Exception {
		if(null == daemon) {
			daemon = new DaemonHandler();
			
			try {
				daemon.init(null);
				
			} catch (DaemonInitException e) {
				logger.error(e.getMessage(), e);
				throw e;
			}
		}
		
		try {
			String command = "start";
			
			if (args != null && args.length > 0) {
				command = args[0];
			}
			
			if (command.equals("start")) {
				
				daemon.start();
				
				logger.info("start: before lock...");
				synchronized (lock) {
					lock.wait();
				}
				logger.info("start: after lock...");
			} else if (command.equals("stop")) {
				
				logger.info("stop: before lock...");
				synchronized (lock) {
					lock.notify();
				}
				logger.info("stop: after lock...");
				
				daemon.stop();
				
				logger.info("stop: after stop...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error("server: start/stop fail");
			throw e;
		}
	}
}
