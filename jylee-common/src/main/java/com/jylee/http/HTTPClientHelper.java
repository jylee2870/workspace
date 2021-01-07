package com.jylee.http;

//import java.io.BufferedReader;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpMethod;
//import org.apache.commons.httpclient.HttpStatus;
//import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
//import org.apache.commons.httpclient.NameValuePair;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.methods.StringRequestEntity;
//import org.apache.commons.httpclient.methods.multipart.FilePart;
//import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
//import org.apache.commons.httpclient.methods.multipart.Part;
//import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
//import org.apache.commons.httpclient.params.HttpMethodParams;
//import org.apache.commons.httpclient.util.EncodingUtil;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.bom.data.IData;
//import com.bom.data.NodeData;
//import com.bom.data.SimpleData;
//import com.bom.parser.JsonParser;



/** 
* <ul>
*  <li>업무 그룹명 : FREESIA </li>
*  <li>서브 업무명 : FREESIA 공통유틸 - HTTP관련 유틸리티  </li>
*  <li>설  명 : HTTP GET/POST 요청/응답 관련 유틸리티 클래스 </li>
*  <li>작성자 : LEE SANG YUB</li>
*  <li>작성일 : 2015. 09. 10 </li>
*  <li>Copyright ⓒ BOMSOFTWARE All Right Reserved </li>
*  <li>========================================</li>
*  <li>변경자/변경일 : </li>
*  <li>변경사유/내역 : </li>
*  <li>========================================</li>
* </ul>
*/

/**
 * commons-httpclient 3.1 라이브러리를 기준으로한 http 처리.
 * deprecated 되어 더이상 사용하지 않는다. 
 * @author leesangyub
 *
 */
public class HTTPClientHelper {
//    protected Log logger = LogFactory.getLog(getClass());
//    
//    private String urlStr = ""; 
//    private String content = ""; 
//    private String statusStr = "";
//    
//    private String jsonStr = null;
//    private List paramList = null; 
//    private File[] files;
//    
//    private int methodType = this.GETTYPE; 
//    private int iGetResultCode = 0; 
//    private int socketTimeout = 5000; // 단위 ms
//
//    public static int GETTYPE = 0; 
//    public static int POSTTYPE = 1; 
//    public static int MULTIPARTTYPE = 2; 
//    
//    private final String STR_GETTYPE = "GET"; 
//    private final String STR_POSTTYPE = "POST"; 
//    private final String STR_MULTIPARTTYPE = "MultiPart"; 
//    
//    public HTTPClientHelper(String urlStr){ 
//        this.urlStr = urlStr; 
//        this.paramList = new ArrayList(); 
//    } 
//    
//    /*
//     * GET methods
//     */
//    public HttpClient getHttpClient() {
//        return new HttpClient(new MultiThreadedHttpConnectionManager()); 
//    }
//    
//    public int getIGetResultCode() { 
//        return this.iGetResultCode; 
//    }
//    
//    public String getContent() { 
//        return this.content; 
//    } 
//    
//    public String getStatusStr() { 
//        return this.statusStr; 
//    } 
//    
//    public int getMethodType() { 
//        return this.methodType; 
//    } 
//    
//    public String getMethodTypeStr() {
//        String rtnTypeStr = this.STR_GETTYPE;
//        if (this.methodType == this.GETTYPE)
//            rtnTypeStr = this.STR_GETTYPE;
//        if (this.methodType == this.POSTTYPE)
//            rtnTypeStr = this.STR_POSTTYPE;
//        if (this.methodType == this.MULTIPARTTYPE)
//            rtnTypeStr = this.STR_MULTIPARTTYPE;
//        
//        return rtnTypeStr;
//    } 
//    
//    public int getSocketTimeout() { 
//        return this.socketTimeout; 
//    } 
//    
//    /*
//     * SET methods
//     */
//    public void clearParams() {
//        if(paramList!=null)
//            paramList.clear();
//    }
//    
//    public void setParam(String key, String value) { 
//        paramList.add(new NameValuePair(key, value)); 
//    } 
//    
//    public void setJsonString(String jsonStr) {
//        this.jsonStr = jsonStr;
//    }
//    
//    public void setMultipartFiles(File[] files) {
//        this.files = files;
//    }
//    
//    public void setMethodType(int methodType) { 
//        this.methodType = methodType; 
//    } 
//    
//    public void setSocketTiemout(int socketTimeout) { 
//        this.socketTimeout = socketTimeout; 
//    } 
//
//    public int execute() { 
//        return execute(null, true);
//    }
//    
//    public int execute(HttpClient inclient, boolean closeFlag) { 
//        iGetResultCode = 0;
//        HttpClient client = null;
//        HttpMethod method = null; 
//        HttpConnectionManagerParams managerParam = null;
//        
//        NameValuePair[] paramArray = new NameValuePair[paramList.size()]; 
//        paramList.toArray(paramArray); 
//        
//        BufferedReader br = null;
//
//        
//        try {
//            if(client==null) {
//                // client = new HttpClient(new MultiThreadedHttpConnectionManager()); 
//                client = new HttpClient();
//            } else
//                client = inclient;
//            
//            managerParam = client.getHttpConnectionManager().getParams();
//            managerParam.setSoTimeout(getSocketTimeout());
//
//            if (methodType == GETTYPE) { 
//                GetMethod getMethod = new GetMethod(urlStr); 
//                if(paramArray.length > 0) {
//                    logger.debug(""+EncodingUtil.formUrlEncode(paramArray,"euc-kr"));
//                    getMethod.setQueryString(EncodingUtil.formUrlEncode(paramArray,"euc-kr")); 
//                }
//                method = getMethod; 
//                
//                iGetResultCode = client.executeMethod(method); 
//                
//                if (iGetResultCode == HttpStatus.SC_OK)
//                    content = method.getResponseBodyAsString(); 
//                else
//                    statusStr = HttpStatus.getStatusText(iGetResultCode);
//                
//            } else if(methodType == POSTTYPE) { 
//                PostMethod postMethod = new PostMethod(urlStr); 
//                
//                for(int k = 0; k < paramArray.length; k++){ 
//                    String paramVal = new String(paramArray[k].getValue().getBytes(), "ISO-8859-1");
//                    // String paramVal = new String(paramArray[k].getValue().getBytes(), "UTF-8");
//                    // String modParamVal = qSidQuoteStr(paramVal);
//                    // logger.debug("post parameter "+paramArray[k].getName()+" : "+paramVal);
//                    postMethod.addParameter( paramArray[k].getName(), paramVal);
//                } 
//                if(this.jsonStr!=null) {
//                    StringRequestEntity requestEntity = new StringRequestEntity( jsonStr, "application/json", "UTF-8");
//                    postMethod.setRequestEntity(requestEntity);
//                }
//                method = postMethod; 
//                
//                iGetResultCode = client.executeMethod(method); 
//                if (iGetResultCode == HttpStatus.SC_OK)
//                    content = method.getResponseBodyAsString(); 
//                else
//                    statusStr = HttpStatus.getStatusText(iGetResultCode);
//                
//            } else if (methodType == MULTIPARTTYPE){ 
//                
//                for (int i=0; i<this.files.length; i++) {
//                    PostMethod filePostMethod = new PostMethod(urlStr);
//                    
//                    filePostMethod.getParams().setBooleanParameter(HttpMethodParams.USE_EXPECT_CONTINUE, false);
//                    Part[] parts = { new FilePart(this.files[i].getName(), files[i]) };
//                    
//                    filePostMethod.setRequestEntity(new MultipartRequestEntity(parts, filePostMethod.getParams()));
//                    
//                    iGetResultCode = client.executeMethod(filePostMethod); 
//                    if (iGetResultCode == HttpStatus.SC_OK) {
//                        content = filePostMethod.getResponseBodyAsString();
//                    }
//                    else {
//                        statusStr = HttpStatus.getStatusText(iGetResultCode);
//                    }
//                    filePostMethod.releaseConnection();
//                }
//            } 
//        } catch (Exception e) { 
//            logger.error("error ***** "+e.getMessage(), e);
//            iGetResultCode = 0; 
//        } finally { 
//            if(method != null && closeFlag) 
//                method.releaseConnection(); 
//        } 
//        return iGetResultCode; 
//    } 
//    
//    // curl 'http://localhost:8901/solr/admin/collections?action=CREATE&name=20150908&numShards=2&createNodeSet=freesia.bomsoftware.com:8901_solr,freesia.bomsoftware.com:8902_solr'
//    public static void main(String args[]) {
//        
//        Log logger = LogFactory.getLog(HTTPClientHelper.class);
//        System.out.println("HTTPClientHelper start..!!");
//        try {
//            
//            IData uuidReqData = get_UUIDSvcJson();
//            System.out.println("reqData : "+uuidReqData.toPrettyString());
//            HTTPClientHelper httpClient = new HTTPClientHelper("http://192.168.100.100:8088/json.cmd"); 
//            httpClient.setMethodType(HTTPClientHelper.POSTTYPE);
//            httpClient.setSocketTiemout(60*1000);
//            httpClient.setJsonString(uuidReqData.toString());
//            
//            IData responseData = null;;
//            try {
//                int httpRtnCode = httpClient.execute();
//                
//                if(httpRtnCode == HttpStatus.SC_OK) {
//                    String httpResponse = httpClient.getContent();
//                    
//                    JsonParser parser = new JsonParser();
//                    responseData = parser.parseFrom(httpResponse);
//                    
//                } else {
//                    responseData = new NodeData();
//                    IData headerData = new NodeData();
//                    headerData.put("rtnCode", new SimpleData("HTTP"+httpRtnCode));
//                    IData bodyData = new NodeData();
//                    bodyData.put("rtnMsg", new SimpleData(HttpStatus.getStatusText(httpRtnCode)));
//                    responseData.put("header", headerData);
//                    responseData.put("body", bodyData);
//                }
//            } catch(Exception e) {
//                
//            }
//            
//            System.out.println("respData : "+responseData.toPrettyString());
//            String uuid = responseData.getValueByKey("uuid");
//            System.out.println("uuid : "+uuid);
//            
//        } catch(Exception e) {
//            logger.error("error ***** "+e.getMessage(), e);
//            // e.printStackTrace();
//        } 
//    }
//    
//    public static IData get_UUIDSvcJson() {
//        IData reqData = new NodeData();
//        IData headerData = new NodeData();
//        headerData.put("trId", new SimpleData("500003"));
//        reqData.put("header", headerData);
//        
//        IData bodyData = new NodeData();
//        reqData.put("body", bodyData);
//        
//        return reqData;
//    }
//    
//    
}

