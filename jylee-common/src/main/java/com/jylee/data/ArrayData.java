package com.jylee.data;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.simple.JSONArray;


public class ArrayData implements IData, Cloneable {
	Log logger = LogFactory.getLog(getClass());
	
	private JSONArray arr = new JSONArray();
	public static int dataType = IData.DATATYPE_ARRAY;
	
	public int getDataType() {
		return this.dataType;
	}
	
	public void put(String key, IData data) {
		if (key == null) {
			arr.add(data);
		}
		else {
			int idx = -1;
			try {
				idx = Integer.valueOf(key);
			}
			catch (NumberFormatException e) { return ; }
			
			arr.set(idx, data);
		}
	}
	
	public IData get(String key) {
		int idx = -1;
		try {
			idx = Integer.valueOf(key);
		}
		catch (NumberFormatException e) { return null; }
		// return (IData)arr.get(idx);
		
		Object ob = arr.get(idx);
		if(ob instanceof NodeData)
			return (NodeData)ob;
		else if(ob instanceof ArrayData)
			return (ArrayData)ob;
		return (SimpleData)ob;
	}
	
	public IData getAt(int index) {
		// return (IData)arr.get(index);
		Object ob = arr.get(index);
		if(ob instanceof NodeData)
			return (NodeData)ob;
		else if(ob instanceof ArrayData)
			return (ArrayData)ob;
		return (SimpleData)ob;
	}
	
	public void setObject(Object obj) {
		this.arr = (JSONArray)obj;
	}
	
	public Object getObject() {
		return this.arr;
	}
	
	public void remove(String key) {
		if (key == null) {
			return;
		}
		else {
			int idx = -1;
			try {
				idx = Integer.valueOf(key);
			}
			catch (NumberFormatException e) { return ; }
			if (idx < this.size())
				arr.remove(idx);
		}
	}
	
	public int size() {
		return arr.size();
	}
	
	public IData clone() {
		ArrayData data = new ArrayData();
		
		int len = this.size();

		for (int i=0; i < len; i++) {
			IData d = this.getAt(i);
			data.put(null, d.clone());
		}
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
		return arr.toJSONString();
	}
	
	public String toString() {
		return arr.toJSONString();
	}
	
	public String toString(int depth) {
		String depth1 = null;
		String depth2 = null;
		StringBuffer debuf = new StringBuffer();
		for(int j=0;j<depth;j++)
			debuf.append("\t");
		depth1=debuf.toString();
		depth2=depth1+"\t";
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("[\n");
		
		if(this.arr != null) {
			for(int i=0; i<this.size(); i++) {
				if(i!=0)
					sbuf.append(",\n");

				IData bizData = this.getAt(i);
				if(bizData!= null)
					sbuf.append(depth2).append(bizData.toString(depth+1));
			}
			sbuf.append("\n").append(depth1).append("]");
		}
		return sbuf.toString();
	}
	
	public String toPrettyString() {
		return this.toString(0);
		// return JSONValue.toPrettyJSONString(arr);
	}
	
	public String getValueByKey(String key) {
		IData data = null;
		String subRtn = null;
		
		if(this.arr != null) {
			for(int i=0; i<this.size(); i++) {
				data = getAt(i);
				if(data instanceof ArrayData || data instanceof NodeData) {
					subRtn = data.getValueByKey(key);
					if(subRtn !=null)
						return subRtn;
				}
			}
		}
		return null;
	}
	
	
}
