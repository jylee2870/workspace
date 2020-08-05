package sga.sol.ac.acbackup.daemon.handler;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sga.sol.ac.acbackup.daemon.server.backupServer;

public class DaemonHandler implements Daemon {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	backupServer server;

	@Override
	public void init(DaemonContext arg0) throws DaemonInitException, Exception {
		server = new backupServer();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void start() throws Exception {
		server.start();
	}

	@Override
	public void stop() throws Exception {
		
	}

}
