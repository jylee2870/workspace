package sga.sol.ac.acbackup.daemon.server;

public class ServerProperty {
	private String cronExpression;
	private String saveFilePath;
	private String saveFileName;
	private String encoding;
	private String term;
	
	private String tableName;
	private String tableDateColumn;

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getSaveFilePath() {
		return saveFilePath;
	}

	public void setSaveFilePath(String saveFilePath) {
		this.saveFilePath = saveFilePath;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableDateColumn() {
		return tableDateColumn;
	}

	public void setTableDateColumn(String tableDateColumn) {
		this.tableDateColumn = tableDateColumn;
	}
	
}