package com.jylee.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
// import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;



public class ConnectionManager {
    private static Log logger = LogFactory.getLog(ConnectionManager.class);
    
    private static PoolingHttpClientConnectionManager connectionManager = null;
    
    private static HttpClientBuilder builder = null;
    
    public static synchronized CloseableHttpClient getHttpClient() {
        if (connectionManager == null) {
            connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setMaxTotal(300);
            connectionManager.setDefaultMaxPerRoute(100);
        }
        if(builder==null) {
            builder = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).setConnectionManager(connectionManager);
        }
        
        CloseableHttpClient httpClient = builder.build();
        return httpClient;
    }

    public static void abort(HttpRequestBase httpRequest) {
        if (httpRequest != null) {
            try {
                httpRequest.abort();
            } catch (Exception e) {
                logger.error("error ***** "+e.getMessage(), e);
            }
        }
    }

    // 리소스 반납. 
    public static void release(HttpResponse response) {
        if (response != null && response.getEntity() != null)
            EntityUtils.consumeQuietly(response.getEntity());
    }
    
    public static void shutdown() {
        connectionManager.shutdown();
        connectionManager = null;
        builder = null;
    }
    
}

