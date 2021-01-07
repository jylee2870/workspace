package com.jylee.data;


public interface IData {
	
	public static final int DATATYPE_SIMPLE = 0;
	public static final int DATATYPE_NODE = 1;
	public static final int DATATYPE_ARRAY = 2;
	
	public int getDataType();
	
	public void put(String key, IData data);
	
	public IData get(String key);
	
	public IData getAt(int index);
	
	public void setObject(Object obj);
	
	public Object getObject();
	
	public void remove(String key);
	
	public int size();
	
	public IData clone();
	
	public String[] getKeyNames();
	
	public void prettyPrint();
	
	public String toRawString();
	
	public String toString();
	
	public String toString(int depth);
	
	public String toPrettyString();
	
	/**
	 * 해당 key와 일치하는 SimpleData의 String 값을 반환하는 함수.
	 * 
	 *  {
	 *    "deptName":"연구개발팀",
	 *    "president":"남기하",
	 *    "address":{
	 *    	"city":"서울",
	 *      "street":"신용산동"
	 *    },
	 *    "data":[
	 *      "Labs",
	 *      "giha.nam",
	 *      ["Yoo", "Jin", "Whang"],
	 *      {
	 *        "city" : "seoul",
	 *        "street" : "yongsang-dong"
	 *      }
	 *    ]
	 *  }
	 *  위와 같은 경우..
	 *  getValuebyPropName("deptName") --> "연구개발팀"
	 *  getValuebyPropName("president") --> "남기하"
	 *  getValuebyPropName("city") --> "서"
	 *  getValuebyPropName("street") --> "신용산동"
	 *  
	 *  만약, BizColletionData과 BizNodeData를 포함한다면 
	 *  getValuebyPropName("address") --> "::NodeData"
	 *  getValuebyPropName("data") --> "::ArrayData"
	 */
	public String getValueByKey(String key);
	
}