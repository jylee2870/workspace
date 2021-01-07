package com.jylee.data;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SimpleData implements IData, Cloneable {
	Log logger = LogFactory.getLog(getClass());
	
	private Object obj = null;
	public static int dataType = IData.DATATYPE_SIMPLE;
	
	public SimpleData() {
	}
	
	public SimpleData(Object obj) {
		this.obj = obj;
	}
	
	public int getDataType() {
		return this.dataType;
	}
	
	public void put(String key, IData data) {
		if (key == null)
			obj = data.getObject();
	}
	
	public IData get(String key) {
		return new SimpleData(obj);
	}
	
	public IData getAt(int index) {
		return new SimpleData(obj);
	}
	
	public void setObject(Object obj) {
		this.obj = obj;
	}
	
	public Object getObject() {
		return this.obj;
	}
	
	public void remove(String key) {
		if (key == null)
			obj = null;
	}
	
	public int size() {
		return 0;
	}
	
	public IData clone() {
		SimpleData data = new SimpleData();
		
		Object obj = this.getObject();		
		data.setObject(obj);

		return data;
	}
	
	public String[] getKeyNames() {
		return null;
	}
	
	public void prettyPrint() {
		if (logger.isDebugEnabled()) {
			logger.debug("prettyPrint\n"+this.toPrettyString());
		}
	}
	
	public String toRawString() {
		if(obj == null)
			return null;
		else 
			return obj.toString();
	}
	
	public String toString() {
		if(obj == null)
			return null;
		else if(obj instanceof String)
			//return "\""+ obj.toString()+"\"";
			//2013.10.25 IL YUN  수정 json 스트링데이터에 특수기호 있을시 처리
			return "\""+ obj.toString().replace("\\", "\\\\")
					                   .replace("\"", "\\\"")
					                   .replace("\t", "\\t")
					                   .replace("\r\n", "\\r\\n")
					                   .replace("\n", "\\n")+"\"";
		else 
			return obj.toString();
	}
	
	public String toString(int depth) {
		return this.toString();
	}
	
	public String toPrettyString() {
		return this.toString(0);
	}
	
	public String getValueByKey(String key) {
		if(obj !=null) {
			// logger.debug(".. : "+obj.toString());
//			return obj.toString().replace("\"", "");
			return obj.toString();
		} else 
			return null;
	}
}
