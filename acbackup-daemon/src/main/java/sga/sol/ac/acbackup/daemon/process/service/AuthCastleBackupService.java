package sga.sol.ac.acbackup.daemon.process.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

import sga.sol.ac.acbackup.daemon.process.dao.AuthCastleBackupDao;
import sga.sol.ac.acbackup.daemon.server.ServerProperty;

@Service
public class AuthCastleBackupService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AuthCastleBackupDao dao;
	
	@Autowired
	private ServerProperty serverProperty;
	
	public List<Map<String, Object>> selectAuthLog(Map<String, String> param){
		return dao.selectAuthLog(param);
	}
	
	@org.springframework.scheduling.annotation.Scheduled(cron="${server.cronexpression}")
	public void authcastleAuthLogScheduler(){
		logger.info("*****************************************************************************");
		logger.info("********************** AuthCastle BackUp ************************************");
		logger.info("*****************************************************************************");
		
		CsvListWriter writer = null;
		
		String filePath = serverProperty.getSaveFilePath();
		String fileName = serverProperty.getSaveFileName();
		String encoding = serverProperty.getEncoding();
		
		String tableName = serverProperty.getTableName();
		String tableDateColumn = serverProperty.getTableDateColumn();
		
		String termStr = serverProperty.getTerm();
		
		File filePathDirectory = new File(filePath);
		
		if(!filePathDirectory.isDirectory()){
			logger.error("server.properties ERROR: savefilepath is null or savefilepath is not Directory");
		}
		else if(fileName == null || fileName.equals("")){
			logger.error("server.properties ERROR: savefilename is null");
		}
		else if(encoding == null || encoding.equals("")){
			logger.error("server.properties ERROR: encoding is null");
		}
		else if(termStr == null || termStr.equals("")){
			logger.error("server.properties ERROR: term is null");
		}
		else if(tableName == null || tableName.equals("")){
			logger.error("server.properties ERROR: tableName is null");
		}
		else{
			int term = Integer.parseInt(termStr);
			term = -term;
			
			SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
			Calendar time = Calendar.getInstance();
			String endDt = format.format(time.getTime());
			time.add(Calendar.DATE, term);
			String startDt = format.format(time.getTime());
			
			Map<String, String> param = new HashMap<String, String>();
			param.put("startDt", startDt);
			param.put("endDt", endDt);
			param.put("tableName", tableName);
			param.put("tableDateColumn", tableDateColumn);
			List<Map<String, Object>> list = selectAuthLog(param);
			
			logger.info("********************** log tableName: "+tableName+" Date Column: "+tableDateColumn+" ************************************");
			logger.info("********************** log startDt: "+startDt+" endDt: "+endDt+" ************************************");
			
			try {
				
				String fullfilePath = filePath+"/"+fileName+"_"+startDt+"-"+endDt+"_"+time.getTimeInMillis()+".csv";
				
				logger.info("********************** Log File Create Path: "+fullfilePath+" ************************************");
				
				writer = new CsvListWriter(new OutputStreamWriter(new FileOutputStream(fullfilePath), encoding), CsvPreference.STANDARD_PREFERENCE);
				
				if(list.size() > 0){
					for(Map<String, Object> log : list){
						
						String [] row = new String[log.size()];
						
						int cnt = 0;
						for(String key : log.keySet()){
							row[cnt] = log.get(key)+"";
							cnt++;
						}
						
						writer.write(row);
					}
				}

				
				writer.close();
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		logger.info("********************** AuthCastle BackUp END ************************************");
		
	}
	
}
