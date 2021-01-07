package com.jylee.http;

//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
//import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
////import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;



public class HttpClientPool {
//    protected Log logger = LogFactory.getLog(getClass());
//    
//    private static volatile HttpClientPool instance;
//    
//    private MultiThreadedHttpConnectionManager connManager;
//    
////    private IdleConnectionTimeoutThread idleConnThr ;
//    
//    private HttpClientPool() {
//        connManager = new MultiThreadedHttpConnectionManager();
//        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
//        params.setDefaultMaxConnectionsPerHost(100);
//        params.setMaxTotalConnections(500);
//        connManager.setParams(params);
//        
////        idleConnThr = new IdleConnectionTimeoutThread();
////        idleConnThr.addConnectionManager(connManager);
////        idleConnThr.start();
//    }
//    
//    public static HttpClientPool getInstance() {
//        
//        if (instance == null ) {
//            synchronized (HttpClientPool.class) {
//                if (instance == null) {
//                    instance = new HttpClientPool();
//                }
//            }
//        }
//        return instance;
//        
//    }
//    
//    public HttpClient getClient() {
//        HttpClient httpClient = null;
//        httpClient = new HttpClient(connManager);
//        
//        return httpClient;
//    }
//    
//    public void shutdown() {
//        try {
//            connManager.closeIdleConnections(10*1000L);
//            Thread.sleep(1000L);
////            idleConnThr.shutdown();
////            Thread.sleep(1000L);
//            connManager.shutdown();
//            Thread.sleep(1000L);
//            instance = null;
//        } catch(Exception e) {
//            logger.error("error ***** "+e.getMessage(), e);
//        }
//    }
//    
}



