package com.wxz.freecard.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.wxz.freecard.constant.Constant;

/**
 * API客户端接口：用于解析服务器数据
 *
 * @author Renzo (http://my.oschina.net/renzo)
 * @version 1.0
 * @created 2015-03-28
 */
public class ApiHander {
    private static String cookies = null;      // 服务器返回cookie

    public JSONObject comm(JSONObject jsonRequestBody, String serviceName) {
        String requestMessage = null;
        String responseMessage = null;

        /**
         * 输入接口合法性检查
         */
        if (serviceName == null || serviceName.isEmpty()) {
            ApiClient.ERROR_NUMBER = -1;
            ApiClient.ERROR_MESSAGE = "SYS0002";    // 系统错误:[请求服务名为空]
            return null;
        }

        /**
         * 生成请求报文
         */
        requestMessage = packMessage(jsonRequestBody, serviceName);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>>>>> requestMessage >>>>>>>>>>>>" + requestMessage);
        if (requestMessage == null) {
            return null;
        }

        try {
            /**
             * 请求Http服务
             */
            responseMessage = new apiPostHttp().execute(requestMessage).get();
            System.out.println(">>>>>>>> responseMessage >>>>>>>>>>>" + responseMessage);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            if (responseMessage == null) {
                return null;
            }

            /**
             * 解析返回报文
             */
            return unpackMessage(responseMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
            ApiClient.ERROR_NUMBER = -1;
            ApiClient.ERROR_MESSAGE = "SYS0004";    // SYS0004：系统错误:[连接服务器失败]
        } catch (ExecutionException e) {
            e.printStackTrace();
            ApiClient.ERROR_NUMBER = -1;
            ApiClient.ERROR_MESSAGE = "SYS0004";    // SYS0004：系统错误:[连接服务器失败]
        }

        return null;
    }

    private class apiPostHttp extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String readLine = null;
            HttpURLConnection urlConnection = null;

            try {
                /**
                 * 初始化服务器连接参数
                 */
                urlConnection = (HttpURLConnection) new URL(Constant.IP_ADDRESS).openConnection();

                urlConnection.setConnectTimeout(Constant.CONNECT_TIMEOUT); // 连接服务器超时时间
                urlConnection.setReadTimeout(Constant.READ_TIMEOUT);        // 读取内容超时时间
                urlConnection.setDoInput(true);             // 设置输入流采用字节流
                urlConnection.setDoOutput(true);            // 设置输出流采用字节流
                urlConnection.setRequestMethod("POST");     // POST请求方式
                urlConnection.setUseCaches(false);          // 设置缓存模式，POST请求不能使用缓存
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");   // 设置传送内容类型
                if (cookies != null && cookies.length() > 0) {
                    urlConnection.setRequestProperty("Cookie", cookies);        // 设置cookie
                }

                /**
                 * 与服务器建立通讯连接
                 * 从url.openConnection()至此的配置必须要在connect之前完成，
                 * 要注意的是connection.getOutputStream会隐含的进行connect。
                 */
                urlConnection.connect();

                /**
                 * 向服务器发送请求报文
                 */
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream(), Constant.CHARSET);
                outputStreamWriter.write("REQUEST=" + params[0]);             // 发送消息报文
                outputStreamWriter.flush();
                outputStreamWriter.close();

                /**
                 * 从服务器读取响应报文
                 */
                InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream(), Constant.CHARSET);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();
                while ((readLine = bufferedReader.readLine()) != null)
                    stringBuffer.append(readLine);
                bufferedReader.close();
                inputStreamReader.close();

                String cookie = urlConnection.getHeaderField("set-cookie");     // 储存服务器cookie
                if (cookie != null && cookie.length() > 0) {
                    cookies = cookie;
                }

                return stringBuffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
                ApiClient.ERROR_NUMBER = -1;
                ApiClient.ERROR_MESSAGE = "SYS0004";    // SYS0004：系统错误:[连接服务器失败]
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return null;
        }
    }

    /**
     * 向服务器发送请求报文
     */
    /**
     * 请求报文格式
     * {
     * "SYSTEM_HEAD":{
     * },
     * "SERVICE_HEAD":{
     * "SERVICE":"REGISTER"          // 用户注册
     * },
     * "SERVICE_BODY":{
     * "PHONE_NUMBER":"186****8180",      // 手机号码
     * "LOGIN_PASSWD":"***********"       // 用户登录密码
     * "MAIL_ADDRESS":"xxxxxx@zhongxin.cn" // 邮箱
     * }
     * }
     */
    private String packMessage(JSONObject jsonRequestBody, String serviceName) {
        JSONObject jsonRequest = new JSONObject();
        JSONObject jsonRequestHead = new JSONObject();

        try {
            // 生成报文头数据
            jsonRequest.put("SERVICE_HEAD", jsonRequestHead);
            jsonRequestHead.put("SERVICE", serviceName);

            // 生成报文体数据
            jsonRequest.put("SERVICE_BODY", jsonRequestBody);

            return jsonRequest.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            ApiClient.ERROR_NUMBER = -1;
            ApiClient.ERROR_MESSAGE = "SYS0005";    // 系统错误:[生成请求报文失败]
        }

        return null;
    }

    /**
     * 从服务器接收响应报文
     */
    /**
     * 响应报文格式
     * {
     * "SYSTEM_HEAD":{
     * },
     * "SERVICE_HEAD":{
     * "ERROR_NUMBER":0,               // 0：成功；-1：失败
     * "ERROR_MESSAGE":"SUCCESS"       // SUCCESS：成功；ERR0000：错误码
     * },
     * "SERVICE_BODY":{
     * "USER_NUMBER":"0000000001"      // 用户编号
     * }
     * }
     */
    private JSONObject unpackMessage(String responseMessage) {
        JSONObject jsonResponse = null;
        JSONObject jsonResponseHead = null;
        JSONObject jsonResponseBody = null;

        if (responseMessage == null) {
            ApiClient.ERROR_NUMBER = -1;
            ApiClient.ERROR_MESSAGE = "SYS0003";    // 系统错误:[响应报文为空]
            return null;
        }

        try {
            jsonResponse = new JSONObject(responseMessage);

            // 解析报文头数据
            jsonResponseHead = jsonResponse.getJSONObject("SERVICE_HEAD");
            ApiClient.ERROR_NUMBER = jsonResponseHead.getInt("ERROR_NUMBER");
            ApiClient.ERROR_MESSAGE = jsonResponseHead.getString("ERROR_MESSAGE");
            if (0 != ApiClient.ERROR_NUMBER) {
                return null;
            }

            // 解析报文体数据
            jsonResponseBody = jsonResponse.getJSONObject("SERVICE_BODY");

            return jsonResponseBody;
        } catch (JSONException e) {
            e.printStackTrace();
            ApiClient.ERROR_NUMBER = -1;
            ApiClient.ERROR_MESSAGE = "SYS0006";    // 系统错误:[解析响应报文失败]
        }

        return null;
    }
}