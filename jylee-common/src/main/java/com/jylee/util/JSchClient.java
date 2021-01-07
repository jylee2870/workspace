package com.jylee.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;



public class JSchClient {
	
	Logger logger = Logger.getLogger(JSchClient.class);
	
	private String hostname = null;
	private int sshport = 22;
	private String username = null;
	private String password = null;
	
	// private Session session = null;
	// private Channel gChannel = null;
	
	
	private JSchClient() {
	}
	
	public JSchClient(String hostname, String username, String password) {
		this(hostname, 22, username, password);
	}
	
	public JSchClient(String hostname, int sshport, String username, String password) {
		this.hostname = hostname;
		this.sshport = sshport;
		this.username = username;
		this.password = password;
	}
	
	public Session getSession() throws JSchException {
		JSch jsch=new JSch();
		Session session = jsch.getSession(username, hostname, sshport);
		session.setConfig("StrictHostKeyChecking", "no");
		if (password!=null) 
			session.setPassword(password);
		session.connect();
		logger.info(CommonUtils.getDateStr(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss.SSS", false)+" Session connectd : "+session.isConnected());
		return session;
	}
	
	public ChannelSftp getSftpChannel(Session session) throws JSchException {
	    Channel channel = session.openChannel("sftp");
        channel.connect();
        return (ChannelSftp) channel;
	}
	
	
	public void releaseSession(Session session) {
		session.disconnect();
	}
	
	public boolean isSessionConnected(Session session) {
		if(session!=null && session.isConnected())
			return true;
		return false;
	}
	
	public String exec(Session session, String command) {
		List<String> ret = exec(session, new String[] {command});
		if(ret!=null && ret.size()>0)
			return ret.get(0);
		return null;
	}
	
	public List<String> exec(Session session, List<String> commands) {
		return exec(session, commands.toArray(new String[]{}));
	}
	
	public List<String> exec(Session session, String[] commands) {
		List<String> ret = new ArrayList<String>();
		try {
			// Session session = getSession();
			// session.connect();
			
			// System.out.println(CommonUtils.getDateStr(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss.SSS", false)+" Session connectd : "+session.isConnected());
			
			int i =1;
			for (String command : commands) {
				// System.out.println("i-->"+i+" command-->"+command);
				Channel channel = session.openChannel("exec");
				/**
				 * you shouldn't allocate a pty to the command for the command to run in the background and not be attached to a terminal
				 * 백그라운드로 명령을 실행하려면 커멘드를 Pseudo-Terminal에 지정하면 않된다. 
				 * 명령을 실행한 의사터미널이 종료되면 해당 프로세스가 같이 종료되기 때문이다. 
				 */
				// ((ChannelExec)channel).setPty(true);
				((ChannelExec)channel).setPty(false);
				// System.out.println("command : "+command);
				((ChannelExec)channel).setCommand(command);
				channel.setInputStream(null);
				((ChannelExec)channel).setErrStream(System.err);
				
				InputStream in = channel.getInputStream();
				InputStream err = ((ChannelExec)channel).getErrStream();
				channel.connect(3000);
				
				// logger.info(CommonUtils.getDateStr(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss.SSS", false)+" Channel connectd : "+channel.isConnected());
				// System.out.print("stdout : ");
				
				String output = "";
				byte[] buf = new byte[1024];
				int length;
				while ((length = in.read(buf)) != -1) {
					String str = new String(buf, 0, length);
					// System.out.println(" str ******* "+str);
					output += str;
					// System.out.print(new String(buf,0,length));
				}
				// System.out.println("\nerr : "+IOUtils.toString(err));
				ret.add(StringUtils.chop(output));
				channel.disconnect();
				i++;
			}
			// session.disconnect();
		} catch (Exception e) {
			logger.error("***** error : "+e.getMessage(), e);
		}
		return ret;
	}
	
	
	/* 2015/08/12 YONGSANG */
	//주어진 파일을 remote호스트에 전송한다.
	public static void fileTransfer (String hostname, int sshport, String username, String password, String localfilepath, String destfilepath, String filename) {
		JSchClient sclient = new JSchClient(hostname, sshport, username, password);
		
		Session session;
		try {
			session = sclient.getSession();
			
			if(!sclient.isSessionConnected(session)){
				sclient.releaseSession(session);
			}
			
			ChannelSftp sftpCh = sclient.getSftpChannel(session);

			sclient.exec(session, "mkdir -p "+ destfilepath);
			sftpCh.cd(destfilepath);
			File f = new File(localfilepath+"/"+filename); 
			sftpCh.put(new FileInputStream(f), f.getName());
			
			if(sclient.isSessionConnected(session)){
				sclient.releaseSession(session);
			}
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//cmd로 주어진 명령을 remote호스트에서 실행한다.
	public static void executeCmd (String hostname, int sshport, String username, String password, String cmd) {
		JSchClient sclient = new JSchClient(hostname, sshport, username, password);
		
		Session session;
		try {
			session = sclient.getSession();
			
			if(!sclient.isSessionConnected(session)){
				sclient.releaseSession(session);
			}
			sclient.exec(session, cmd);
			
			if(sclient.isSessionConnected(session)){
				sclient.releaseSession(session);
			}
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	/* 2015/08/12 YONGSANG */
	
	public static void main(String args[]) {
		try {
			
			// System.out.println(args[0]);
			System.out.println(CommonUtils.getDateStr(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss.SSS", false)+" STARTED.!!");
			JSchClient sclient = new JSchClient("192.168.100.100", 22, "freesia", "#freesia4321!");
			
			Session session = sclient.getSession();
			
			if(!sclient.isSessionConnected(session)){
				sclient.releaseSession(session);
			}
			
			
//			List<String> commands = new ArrayList<String>();
//			// commands.add("top -n 3 -bi -d 0.30");
//			// commands.add("free -m");
//			commands.add("df -Pm | grep -v Filesystem | awk '{ print $6 \" \" $2 \" \" $4 }'");
//			
//			List<String> execrtns = sclient.exec(session, commands);
//			
//			for(String rtn:execrtns) {
//				System.out.println(rtn);
//			}
			
			// String rtn = sclient.exec(session, "cd /home/freesia/c_framework/cfagent_20; /opt/java/jdk1.7.0_71/bin/java -Xms2048m -Xmx2048m -Dcom.sun.management.jmxremote.port=42020 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dzk.master.servers=localhost:2182 -Dflume.root.logger=INFO,LOGFILE -cp '/home/freesia/c_framework/cfagent_20/conf:/home/freesia/c_framework/cfagent_20/lib/*:/home/freesia/c_framework/cfagent_20/plugins.d/freesia/lib/*:/home/freesia/c_framework/cfagent_20/plugins.d/freesia/libext/*:/lib/*' -Djava.library.path= org.apache.flume.node.Application -f conf/cfagent_20.conf -n ag1 42020");
			// String rtn = sclient.exec(session, "CFAgentCmd.sh restart cfagent_20");
			String rtn = sclient.exec(session, "ls");
			System.out.println("==================================================================");
			System.out.println(rtn);
			System.out.println("==================================================================");
			
			rtn = sclient.exec(session, "/home/freesia/scripts/CFAgentCmd.sh start cfagent_22 ");
            System.out.println("==================================================================");
            System.out.println(rtn);
            System.out.println("==================================================================");
			
//			if(!sclient.isSessionConnected(session)) {
//				System.out.println("--------------------session is invalid so reconnect...");
//				session = sclient.getSession();
//			}
//			System.out.println(sclient.exec(session, "df -Pm | grep -v Filesystem | awk '{ print $6 \" \" $4 }'"));
			
//			ChannelSftp sftpCh = sclient.getSftpChannel(session);
//			
//			
//			sftpCh.cd("/home/freesia/exlsy");
//			File f = new File("D:\\temp\\[s_c_1_1_ag_65]Cardlog_20150730.txt");
//			sftpCh.put(new FileInputStream(f), f.getName());
			
			 
			if(sclient.isSessionConnected(session)){
				sclient.releaseSession(session);
			}
			System.out.println(CommonUtils.getDateStr(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss.SSS", false)+" ENDED.!!");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

