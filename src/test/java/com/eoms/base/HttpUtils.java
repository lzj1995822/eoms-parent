package com.eoms.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * HttpUtils
 * <p></p>
 *
 * @author lxiang
 * @version V1.0
 * @since 16-7-4
 */
public class HttpUtils {

    private static final Logger LOGGER = Logger.getLogger(HttpUtils.class);

    /**
     * 请求编码
     */
    public final static String REQUEST_ENCODING = "UTF-8";

    /**
     * 连接超时
     */
    private static int CONNECT_TIME_OUT = 5000;

    /**
     * 读取数据超时
     */
    private static int READ_TIME_OUT = 10000;

    public static String doGet(String url, Map<String, String> headParams) {
        return callHttp(url, headParams, "GET");
    }

    public static String doDelete(String url, Map<String, String> headParams) {
        return callHttp(url, headParams, "DELETE");
    }

    public static String doPut(final String reqUrl, final Map parameters,
                               final String receiveEncoding, Map<String, String> headParams) {
        return execHttpRequest(reqUrl, parameters, receiveEncoding, headParams, "PUT");
    }

    public static String doPost(final String reqUrl, final Object parameters,
                                final String receiveEncoding, Map<String, String> headParams) {
        return execHttpRequest(reqUrl, parameters, receiveEncoding, headParams, "POST");
    }

    /**
     * 调用WEB接口
     *
     * @param url
     * @param method
     * @return
     * @throws Exception
     */
    public static String callHttp(String url, Map<String, String> headParams, String method) {
        StringBuffer resultBuffer = new StringBuffer();
        try {
            URL restServiceURL = new URL(url);
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            if (headParams != null) {
                for (String key : headParams.keySet()) {
                    httpConnection.setRequestProperty(key, headParams.get(key));
                }
            }
            httpConnection.setRequestMethod(method);
            httpConnection.setRequestProperty("Accept", "application/json");
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : " + httpConnection.getResponseCode());
            }
            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), REQUEST_ENCODING));
            String output = StringUtils.EMPTY;
            while ((output = responseBuffer.readLine()) != null) {
                resultBuffer.append(output);
            }
            httpConnection.disconnect();
        } catch (Exception ce) {
            LOGGER.warn("HTTP GET Request Failed with Error cod, the url is " + url, ce);
        }
        return resultBuffer.toString();
    }

    /**
     * <pre>
     * 发送带参数的POST的HTTP请求
     * </pre>
     *
     * @param reqUrl      HTTP请求URL
     * @param sendMessage 参数映射表
     * @return HTTP响应的字符串
     */
    public static String doPost(final String reqUrl, final String sendMessage,
                                final String receiveEncoding, Map<String, String> headParams) {
        HttpURLConnection url_con = null;
        String responseContent = null;
        try {
            url_con = createUrlConnection(reqUrl, headParams, sendMessage, "POST");
            responseContent = doPost(url_con, receiveEncoding);
        } catch (Exception e) {
            LOGGER.error("网络故障", e);
        } finally {
            if (url_con != null) {
                url_con.disconnect();
            }
        }
        return responseContent;
    }


    private static String doPost(HttpURLConnection url_con,final String receiveEncoding) throws Exception {
        InputStream in = url_con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(in, receiveEncoding));
        String tempLine = rd.readLine();
        StringBuffer tempStr = new StringBuffer();
        String separator = System.getProperty("line.separator");
        while (tempLine != null) {
            tempStr.append(tempLine).append(separator);
            tempLine = rd.readLine();
        }
        rd.close();
        in.close();
        return tempStr.toString();
    }

    private static HttpURLConnection createUrlConnection(String reqUrl, Map<String, String> headParams, Object parameters, String requestMethod) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String sendMessage = "";
        System.out.println(parameters);
        if (parameters != null) {
            sendMessage = objectMapper.writeValueAsString(parameters);
        }
        return createUrlConnection(reqUrl, headParams, sendMessage, requestMethod);
    }

    private static HttpURLConnection createUrlConnection(String reqUrl, Map<String, String> headParams, String sendMessage, String requestMethod) throws IOException {
        URL url = new URL(reqUrl);
        HttpURLConnection url_con = (HttpURLConnection) url.openConnection();
        if (headParams != null) {
            for (String key : headParams.keySet()) {
                url_con.setRequestProperty(key, headParams.get(key));
            }
        }
        url_con.setRequestMethod(requestMethod);
        if (requestMethod.equals("PUT")) {
            url_con.setDoOutput(true);// http正文内，因此需要设为true, 默认情况下是false;
            url_con.setDoInput(true);
        }
        url_con.setRequestProperty("Content-Type","application/json;charset=UTF-8");
        url_con.setConnectTimeout(CONNECT_TIME_OUT);//（单位：毫秒）jdk
        // 1.5换成这个,连接超时
        url_con.setReadTimeout(READ_TIME_OUT);//（单位：毫秒）jdk 1.5换成这个,读操作超时
        url_con.setDoOutput(true);
        byte[] b = sendMessage.getBytes();
        url_con.getOutputStream().write(b, 0, b.length);
        url_con.getOutputStream().flush();
        url_con.getOutputStream().close();
        return url_con;
    }

    private static String execHttpRequest(final String reqUrl, final Object parameters,
                                          final String receiveEncoding, Map<String, String> headParams,String requestMethod) {
        HttpURLConnection url_con = null;
        String responseContent = null;
        try {
            url_con = createUrlConnection(reqUrl, headParams, parameters, requestMethod);
            responseContent = doPost(url_con, receiveEncoding);
        } catch (Exception e) {
            LOGGER.error("网络故障", e);
        } finally {
            if (url_con != null) {
                url_con.disconnect();
            }
        }
        return responseContent;
    }

}
