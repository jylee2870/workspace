package com.jylee.data;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class NodeData implements IData, Cloneable {
	Log logger = LogFactory.getLog(getClass());
	
	private JSONObject obj = new JSONObject();
	public static int dataType = IData.DATATYPE_NODE;
	
	
	public int getDataType() {
		return this.dataType;
	}
	
	public void put(String key, IData data) {
		obj.put(key, data);
	}
	 
	public IData get(String key) {
		Object ob = obj.get(key);
		if(ob instanceof NodeData)
			return (NodeData)ob;
		else if(ob instanceof ArrayData)
			return (ArrayData)ob;
		return (SimpleData)ob;
	}
	
	public IData getAt(int index) {
		String[] keys = this.getKeyNames();
		if ( index < 0 || index >= keys.length ) return null;
		return (IData)obj.get(keys[index]);
	}
	
	public void setObject(Object obj) {
		this.obj = (JSONObject)obj;
	}
	
	public Object getObject() {
		return this.obj;
	}
	
	public void remove(String key) {
		obj.remove(key);
	}
	
	public int size() {
		return obj.size();
	}
	
	public IData clone() {
		
		NodeData data = new NodeData();
		String[] keys = this.getKeyNames();
		
		for (int i=0; i < keys.length; i++) {
			IData d = this.get(keys[i]);
			if(d != null)
				data.put(keys[i], d.clone());
		}
		return data;
	}
	
	public String[] getKeyNames() {
		Set<String> keyset = obj.keySet();
		return keyset.toArray(new String[0]);
	}
	
	public void prettyPrint() {
		if (logger.isDebugEnabled()) {
			logger.debug("prettyPrint\n"+this.toPrettyString());
		}
	}
	
	public String toRawString() {
		return obj.toJSONString();
	}
	
	public String toString() {
		return obj.toJSONString();
	}
	
//	public String toString(int depth) {
//		String key = null;
//		String depth1 = null;
//		String depth2 = null;
//		StringBuffer debuf = new StringBuffer();
//		for(int j=0;j<depth;j++)
//			debuf.append("\t");
//		depth1=debuf.toString();
//		depth2=depth1+"\t";
//		StringBuffer sbuf = new StringBuffer();
//		sbuf.append("{\n");
//		
//		if(this.obj != null) {
//			String[] keys = this.getKeyNames();
//
//			for(int i=0; i<keys.length; i++) {
//				key =keys[i];
//				// logger.debug("depth:"+depth+" key:"+key);
//				if(i!=0)
//					sbuf.append(",\n");
//
//				sbuf.append(depth2).append("\""+key+"\" : ");
//				IData bizData = this.get(key);
//				if(bizData!= null) {
//					// logger.debug("depth:"+depth+" bizData is not null!");
//					sbuf.append(bizData.toString(depth+1));
// 				} 
//			}
//			sbuf.append("\n").append(depth1).append("}");
//		}
//		return sbuf.toString();
//	}
	
    public String toString(int depth) {
        boolean simpleFlag = false;
        String key = null;
        String depth1 = null;
        String depth2 = null;
        StringBuffer debuf = new StringBuffer();
        for(int j=0;j<depth;j++)
            debuf.append("\t");
        depth1=debuf.toString();
        depth2=depth1+"\t";
        StringBuffer sbuf = new StringBuffer();
        sbuf.append("{\n");
        
        if(this.obj != null) {
            String[] keys = this.getKeyNames();
            int simpleCnt = 0;

            for(int i=0; i<keys.length; i++) {
                key =keys[i];
                // logger.debug("depth:"+depth+" key:"+key);
                if(i!=0) {
                    sbuf.append(", ");
                }
                IData bizData = this.get(key);
                if(bizData!= null) {
                    if(bizData instanceof SimpleData) {
                        String strdata = bizData.toString(depth+1);
                        if(i==0)
                            sbuf.append(depth2);
                        else if(strdata!=null && strdata.length()>100) {
                            sbuf.append("\n").append(depth2);
                            simpleCnt = 0;
                        } else if(!simpleFlag) {
                            sbuf.append("\n").append(depth2);
                            simpleCnt = 0;
                        }
                        if(simpleCnt>=5) {
                            sbuf.append("\n").append(depth2);
                            simpleCnt = 0;
                        }
                        sbuf.append("\""+key+"\":");
                        sbuf.append(strdata);
                        simpleFlag = true;
                        simpleCnt++;
                    } else {
                        if(i!=0)
                            sbuf.append("\n");
                        sbuf.append(depth2).append("\""+key+"\" : ");
                        sbuf.append(bizData.toString(depth+1));
                        simpleFlag = false;
                        simpleCnt = 0;
                    }
                } 
            }
            sbuf.append("\n").append(depth1).append("}");
        }
        return sbuf.toString();
    }
	
	public String toPrettyString() {
		return this.toString(0);
		// return JSONPrettyPrint.toJSONString(obj);
	}
	
	public String getValueByKey(String keyName) {
		String key = null;
		IData data = null;
		String subRtn = null;
		
		if(this.obj != null) {
			String[] keys = this.getKeyNames();
			for(int i=0; i<keys.length; i++) {
				key = keys[i];
				data = this.get(key);
				if(key.equals(keyName)) {
					if(data instanceof SimpleData ) {
						// logger.debug("** :"+keyName +", "+data.getValueByKey(keyName));
						return data.getValueByKey(keyName);
					}
					else if(data instanceof ArrayData)
						return "::ArrayData";
					else if(data instanceof NodeData)
						return "::NodeData";
				} else {
					if(data instanceof ArrayData || data instanceof NodeData) {
						subRtn = data.getValueByKey(keyName);
						if(subRtn !=null)
							return subRtn;
					}
				}
			}
		}
		return null;
	}
	
	
}