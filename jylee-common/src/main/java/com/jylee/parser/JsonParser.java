package com.jylee.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.jylee.data.ArrayData;
import com.jylee.data.IData;
import com.jylee.data.NodeData;
import com.jylee.data.SimpleData;



public class JsonParser {
    
    Log logger = LogFactory.getLog(getClass());
    
    public JsonParser() {
    }
    
    public IData parseFrom(Object rawdata) throws Exception {
        try {
            return this.parseToIData((String)rawdata);
        } catch (Exception e) {
            logger.error("error "+e.getMessage(), e);
            throw e;
        }
    }
    
    public IData parseFrom(InputStream inputStream) throws Exception {
        try {
            return this.parseToIData(inputStream);
        } catch (Exception e) {
            logger.error("error "+e.getMessage(), e);
            throw e;
        }
    }
    
    public IData parseFrom(Reader reader) throws Exception {
        try {
            return this.parseToIData(reader);
        } catch (Exception e) {
            logger.error("error "+e.getMessage(), e);
            throw e;
        }
    }
    
    public Object decode(IData obj) {
        return obj.toRawString();
    }
    
    private IData parseToIData(InputStream  in) throws UnsupportedEncodingException {
        IData data = null;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
            data = this.parseToIData(inputStreamReader);
        } catch ( UnsupportedEncodingException uee) {
            logger.error(""+uee.getMessage(), uee);
            throw uee;
        }
        return data;
    }
    
    private IData parseToIData(Reader reader) {
        IData data = null;
        try {
            byte[] b = IOUtils.toByteArray(reader);
            String instr = new String(b, "UTF-8");
            data = this.parseToIData(instr);
        } catch (IOException ie) {
            logger.error(""+ie.getMessage(), ie);
        }
        return data;
    }
    
    private IData parseToIData(String str) {
        IData data = null;
        
        Object obj=JSONValue.parse(str);
        
        if (obj == null) {
            return null;
        }
        else if (obj instanceof JSONObject) {
            //logger.debug("parseToIData - JSONObject instance");
            data = new NodeData();
            JSONObject jsonObject = (JSONObject) obj;
            // logger.debug("original obj :\n"+jsonObject.toJSONString());
            parseJSONObject(data, jsonObject);
        }
        else if (obj instanceof JSONArray) {
            //logger.debug("parseToIData - JSONArray instance");
            data = new ArrayData();
            JSONArray jsonArr = (JSONArray)obj;
            // logger.debug("original obj :\n"+jsonArr.toJSONString());
            parseJSONArray(data, jsonArr);
        }
        
        return data;
    }
    
    private void parseJSONObject(IData udata, JSONObject jsonObj) {
        Set<String> keyset = jsonObj.keySet();
        String[] keys = keyset.toArray(new String[0]);
        
        IData data = null;
        
        for (int i=0; i < keys.length; i++) {
            String key = keys[i];
            // logger.debug("key : "+key);
            Object value = jsonObj.get(key);
            // logger.debug("value : "+value.toString());
            Object obj = null;
            if(value == null)
                obj=JSONValue.parse("null");
            else
//              obj=JSONValue.parse(value.toString());
                obj=value;
            
            if (obj == null) {
                // logger.debug("object is null - object last --> value : "+value);
                data = new SimpleData(value);
                // logger.debug("object is null - object last --> value : "+value+", saved value :"+data.toString());
                udata.put(key, data);
                continue ;
            }
            else if (obj instanceof JSONObject) {
                //logger.debug("parseJSONObject jsonObject");
                data = new NodeData();
                JSONObject jsonObject = (JSONObject) obj;
                parseJSONObject(data, jsonObject);
                udata.put(key, data);
            }
            else if (obj instanceof JSONArray) {
                // logger.debug("parseJSONObject JSONArray---------");
                data = new ArrayData();
                JSONArray jsonArr = (JSONArray)obj;
                parseJSONArray(data, jsonArr);
                udata.put(key, data);
            }
            else if (obj instanceof java.lang.String) {
                // logger.debug("parseJSONObject String");
                data = new SimpleData(obj);
                udata.put(key, data);
            }
            else if (obj instanceof java.lang.Number) {
                // logger.debug("parseJSONObject Number");
                data = new SimpleData(obj);
                udata.put(key, data);
            } 
            else if (obj instanceof java.lang.Boolean) {
                // logger.debug("parseJSONObject Boolean");
                data = new SimpleData(obj);
                udata.put(key, data);
            }
        }
        
    }
    
    private void parseJSONArray(IData udata, JSONArray jsonArr) {
        
        IData data = null;

        for (int i=0; i < jsonArr.size(); i++) {
            Object value = jsonArr.get(i);
            Object obj=JSONValue.parse(value.toString());
            
            if (value == null) {
                //logger.debug("object is null - array last");
                data = new SimpleData(obj);
                udata.put(null, data);
                continue ;
            }
            else if (value instanceof JSONObject) {
                // logger.debug("parseJSONArray jsonObject");
                data = new NodeData();
                JSONObject jsonObject = (JSONObject) obj;
                parseJSONObject(data, jsonObject);
                udata.put(null, data);
            }
            else if (value instanceof JSONArray) {
                //logger.debug("parseJSONArray JSONArray");
                data = new ArrayData();
                JSONArray jsonSubArr = (JSONArray)obj;
                parseJSONArray(data, jsonSubArr);
                udata.put(null, data);
            }
            else if (value instanceof java.lang.String) {
                // logger.debug("parseJSONArray String "+obj);
                data = new SimpleData(value);
                udata.put(null, data);
            }
            else if (value instanceof java.lang.Number) {
                // logger.debug("parseJSONArray Number "+obj);
                data = new SimpleData(value);
                udata.put(null, data);
            } 
            else if (value instanceof java.lang.Boolean) {
                // logger.debug("parseJSONArray Boolean "+obj);
                data = new SimpleData(value);
                udata.put(null, data);
            }
        }
        
    }
    
    
}
