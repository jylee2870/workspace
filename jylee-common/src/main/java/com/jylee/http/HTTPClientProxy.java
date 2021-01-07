package com.jylee.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.jylee.data.IData;
import com.jylee.data.NodeData;
import com.jylee.data.SimpleData;



public class HTTPClientProxy {
    
    private static Log logger = LogFactory.getLog(HTTPClientProxy.class);
    
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    
    /**
     * 1) Multipart Related MIME 타입
     *  - Content-Type: Multipart/related <-- 기본형태
     *  - Content-Type: Application/X-FixedRecord
     * 2) XML Media의 타입
     *  - Content-Type: text/xml
     *  - Content-Type: Application/xml
     *  - Content-Type: Application/xml-external-parsed-entity
     *  - Content-Type: Application/xml-dtd
     *  - Content-Type: Application/mathtml+xml
     *  - Content-Type: Application/xslt+xml
     * 3) Application의 타입 
     *  - Content-Type: Application/EDI-X12 <--  Defined in RFC 1767 
     *  - Content-Type: Application/EDIFACT <--  Defined in RFC 1767 
     *  - Content-Type: Application/javascript <-- Defined in RFC 4329 
     *  - Content-Type: Application/octet-stream  : <-- 디폴트 미디어 타입은 운영체제 종종 실행파일, 다운로드를 의미
     *  - Content-Type: Application/ogg <-- Defined in RFC 3534
     *  - Content-Type: Application/x-shockwave-flash <-- Adobe Flash files
     *  - Content-Type: Application/json <-- JavaScript Object Notation JSON; Defined in RFC 4627 
     *  - Content-Type: Application/x-www-form-urlencode <-- HTML Form 형태
     *                  x-www-form-urlencode와 multipart/form-data은 둘다 폼 형태이지만 x-www-form-urlencode은 대용량 바이너리 테이터를 전송하기에 비능률적이기 때문에 
     *                  대부분 첨부파일은 multipart/form-data를 사용하게 된다.
     * 4) 오디오 타입
     *  - Content-Type: audio/mpeg <-- MP3 or other MPEG audio
     *  - Content-Type: audio/x-ms-wma <-- Windows Media Audio;
     *  - Content-Type: audio/vnd.rn-realaudio <--  RealAudio;  등등 
     * 5) Multipart 타입
     *  - Content-Type: multipart/mixed: MIME E-mail; 
     *  - Content-Type: multipart/alternative: MIME E-mail;
     *  - Content-Type: multipart/related: MIME E-mail <-- Defined in RFC 2387 and used by MHTML(HTML mail) 
     *  - Content-Type: multipart/formed-data  <-- 파일 첨부
     * 6) TEXT 타입 
     *  - Content-Type: text/css
     *  - Content-Type: text/html
     *  - Content-Type: text/javascript
     *  - Content-Type: text/plain
     *  - Content-Type: text/xml
     */
    
    private String method;
    private String contentType;
    private String reqUrl; 
    private String reqJson;
    private List<NameValuePair> reqParams = new ArrayList<NameValuePair>(); 
    private File[] reqFiles;
    
    int connectTimeout = 1000;
    int socketTimeout = 5000;
    
//    private HttpClient httpClient;
    
    private int status;
    private String content; 
    private String reason;
    
    // ======================================================================
    public HTTPClientProxy() {
        
    }
    
    public HTTPClientProxy(String method, String reqUrl) {
        this.method = method;
        this.reqUrl = reqUrl;
    }
    
    
    // ======================================================================
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public String getReqUrl() {
        return reqUrl;
    }
    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }
    public String getReqJson() {
        return reqJson;
    }
    public void setReqJson(String reqJson) {
        this.reqJson = reqJson;
    }
    public List<NameValuePair> getReqParams() {
        return reqParams;
    }
//    public void setReqParams(List<NameValuePair> reqParams) {
//        this.reqParams = reqParams;
//    }
    public void setReqParam(String key, String value) {
        reqParams.add(new BasicNameValuePair(key, value));
    }
    public void clearReqParams() {
        if(reqParams!=null)
            reqParams.clear();
    }
    public File[] getReqFiles() {
        return reqFiles;
    }
    public void setReqFiles(File[] reqFiles) {
        this.reqFiles = reqFiles;
    }
    public int getConnectTimeout() {
        return connectTimeout;
    }
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
    public int getSocketTimeout() {
        return socketTimeout;
    }
    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }
//    public HttpClient getHttpClient() {
//        return httpClient;
//    }
//    public void setHttpClient(HttpClient httpClient) {
//        this.httpClient = httpClient;
//    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public void shutdown() {
        ConnectionManager.shutdown();
    }
    
    // ======================================================================
    
    public void execute_Method() {
        // HttpClient httpClient = null;
        CloseableHttpClient httpClient = null;
        HttpResponse response = null;
        
        try {
            /** GET */
            if(METHOD_GET.equals(method)) {
                httpClient = ConnectionManager.getHttpClient();
                
                HttpGet httpget = null;
                if(!reqUrl.contains("?"))
                    httpget = new HttpGet(reqUrl+"?"+URLEncodedUtils.format(reqParams, "euc-kr"));
                else
                    httpget = new HttpGet(reqUrl+"&"+URLEncodedUtils.format(reqParams, "euc-kr"));
                System.out.println("GET : " + httpget.getURI());
                
                RequestConfig requestConfig = RequestConfig.custom()
                        .setSocketTimeout(socketTimeout)
                        .setConnectTimeout(connectTimeout)
                        .setConnectionRequestTimeout(connectTimeout)
                        // .setProxy(new HttpHost("myotherproxy", 8080))
                        .build();
                
                httpget.setConfig(requestConfig);
                
                response = httpClient.execute(httpget);
                
                status = response.getStatusLine().getStatusCode();
                if(status==HttpStatus.SC_OK) {
                    reason = response.getStatusLine().getReasonPhrase();
                    BufferedReader rd = new BufferedReader( new InputStreamReader(response.getEntity().getContent()));
                    StringBuffer contentBuf = new StringBuffer();
                    String line = "";
                    while ((line = rd.readLine()) != null) {
                        contentBuf.append(line);
                    }
                    content = contentBuf.toString();
                } else {
                    reason = response.getStatusLine().getReasonPhrase();
                }
            }
            /** POST */
            else if (METHOD_POST.endsWith(method)) {
                httpClient = ConnectionManager.getHttpClient();
                
                HttpPost httppost = new HttpPost(reqUrl);
                httppost.setEntity(new UrlEncodedFormEntity(reqParams, "UTF-8"));
                // httppost.setEntity(new UrlEncodedFormEntity(reqParams, "ISO-8859-1"));
                if(reqJson!=null) {
                    StringEntity jsonEntity = new StringEntity(reqJson);
                    httppost.addHeader("content-type", "application/json");
                    httppost.setEntity(jsonEntity);
                }
                RequestConfig requestConfig = RequestConfig.custom()
                        .setSocketTimeout(socketTimeout)
                        .setConnectTimeout(connectTimeout)
                        .setConnectionRequestTimeout(connectTimeout)
                        // .setProxy(new HttpHost("myotherproxy", 8080))
                        .build();
                httppost.setConfig(requestConfig);
                response = httpClient.execute(httppost);
                
                status = response.getStatusLine().getStatusCode();
                if(status==HttpStatus.SC_OK) {
                    reason = response.getStatusLine().getReasonPhrase();
                    BufferedReader rd = new BufferedReader( new InputStreamReader(response.getEntity().getContent()));
                    StringBuffer contentBuf = new StringBuffer();
                    String line = "";
                    while ((line = rd.readLine()) != null) {
                        contentBuf.append(line);
                    }
                    content = contentBuf.toString();
                } else {
                    reason = response.getStatusLine().getReasonPhrase();
                }
            }
            
        } catch(SocketTimeoutException ste) {
            logger.error("error ***** "+ste.getMessage(), ste);
            if(httpClient!=null) {
                try { httpClient.close(); } catch(IOException ioe ) {}
            }
        } catch(Exception e) {
            logger.error("error ***** "+e.getMessage(), e);
        } finally {
            ConnectionManager.release(response);
        }
        
    }
    
    
    public static void main(String args[]) {
        
        System.out.println("HTTPClientProxy start..!!");
        HTTPClientProxy httpProxy = null;
        try {
            
            // http://bbs1.agora.media.daum.net/gaia/do/debate/read?articleId=1218905&bbsId=D125&pageIndex=1
            
            httpProxy = new HTTPClientProxy(HTTPClientProxy.METHOD_GET, "http://bbs1.agora.media.daum.net/gaia/do/debate/read");
            httpProxy.setReqParam("articleId", "1218905");
            httpProxy.setReqParam("bbsId", "D125");
            httpProxy.setReqParam("pageIndex", "1");
            
            httpProxy.execute_Method();
            
            System.out.println("status : "+httpProxy.getStatus());
            System.out.println("reason : "+httpProxy.getReason());
            System.out.println("content : "+httpProxy.getContent());
            // httpProxy.shutdown();
            
            IData uuidReqData = get_UUIDSvcJson();
            httpProxy = new HTTPClientProxy(HTTPClientProxy.METHOD_POST, "http://192.168.100.100:8088/json.cmd");
            httpProxy.setConnectTimeout(1000);
            httpProxy.setSocketTimeout(5000);
            httpProxy.setReqJson(uuidReqData.toString());
            
            httpProxy.execute_Method();
            
            System.out.println("status : "+httpProxy.getStatus());
            System.out.println("reason : "+httpProxy.getReason());
            System.out.println("content : "+httpProxy.getContent());
            httpProxy.shutdown();
        } catch(Exception e) {
            logger.error("error ***** "+e.getMessage(), e);
        } finally {
            // httpProxy.shutdown();
        }
        
        System.out.println("HTTPClientProxy end..!!");
    }
    
    public static IData get_UUIDSvcJson() {
        IData reqData = new NodeData();
        IData headerData = new NodeData();
        headerData.put("trId", new SimpleData("500003"));
        reqData.put("header", headerData);
        
        IData bodyData = new NodeData();
        reqData.put("body", bodyData);
        
        return reqData;
    }

}


