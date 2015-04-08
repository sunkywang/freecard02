package com.wxz.freecard.api;

import org.json.JSONException;
import org.json.JSONObject;

import com.wxz.freecard.bean.UserInfo;

/**
 * API客户端接口：用于解析服务器数据
 *
 * @author Renzo (http://my.oschina.net/renzo)
 * @version 1.0
 * @created 2015-03-24
 */
public class ApiClient {
    public static int ERROR_NUMBER = 0;            // 服务器返回错误码
    public static String ERROR_MESSAGE = null;     // 服务器返回错误信息

    /**
     * 更新splash图片
     * params: null
     * return:
     */
    public int refreshSplash() {
        try {
            // 请求服务
            JSONObject jsonResponseBody = new ApiHander().comm(null, "REFRESH_SPLASH");
            if (jsonResponseBody == null) {
                return ERROR_NUMBER;
            }

            // 解析响应报文
            return jsonResponseBody.getInt("REFRESH");
        } catch (JSONException e) {
            e.printStackTrace();
            ERROR_NUMBER = -1;
            ERROR_MESSAGE = "SYS0006";    // 系统错误:[解析响应报文失败]
        } catch (Exception e) {
            e.printStackTrace();
            ERROR_NUMBER = -1;
            ERROR_MESSAGE = "SYS0004";    // SYS0004：系统错误:[连接服务器失败]
        }
        return ERROR_NUMBER;
    }

    /**
     * 用户注册：用户通过手机号码注册
     *
     * @params: phoneNumber，identifyCode, loginPassword，handPassword
     * @return: userNumber
     */
    public int userRegister(UserInfo userInfo) {
        try {
            // 生成请求报文
            JSONObject jsonRequestBody = new JSONObject();
            jsonRequestBody.put("phoneNumber", userInfo.getPhoneNumber());
            jsonRequestBody.put("identifyCode", userInfo.getIdentifyCode());
            jsonRequestBody.put("loginPassword", userInfo.getLoginPassword());
            jsonRequestBody.put("handPassword", userInfo.getHandPassword());

            // 请求服务
            JSONObject jsonResponseBody = new ApiHander().comm(jsonRequestBody, "USER_REGISTER");
            if (jsonResponseBody == null) {
                return ERROR_NUMBER;
            }

            // 解析响应报文
            userInfo.setUserNumber(jsonResponseBody.getString("userNumber"));
        } catch (JSONException e) {
            e.printStackTrace();
            ERROR_NUMBER = -1;
            ERROR_MESSAGE = "SYS0006";    // 系统错误:[解析响应报文失败]
        } catch (Exception e) {
            e.printStackTrace();
            ERROR_NUMBER = -1;
            ERROR_MESSAGE = "SYS0004";    // SYS0004：系统错误:[连接服务器失败]
        }
        return ERROR_NUMBER;
    }

    /**
     * 用户登录：用户通过手机号码和密码登录
     *
     * @params: phoneNumber，loginPassword, handPassword
     * @return: userNumber
     */
    public int userLogin(UserInfo userInfo) {
        try {
            // 生成请求报文
            JSONObject jsonRequestBody = new JSONObject();
            jsonRequestBody.put("phoneNumber", userInfo.getPhoneNumber());
            jsonRequestBody.put("loginPassword", userInfo.getLoginPassword());
            jsonRequestBody.put("handPassword", userInfo.getHandPassword());

            // 请求服务
            JSONObject jsonResponseBody = new ApiHander().comm(jsonRequestBody, "USER_LOGIN");
            if (jsonResponseBody == null) {
                return ERROR_NUMBER;
            }

            // 解析响应报文
            userInfo.setUserNumber(jsonResponseBody.getString("userNumber"));
        } catch (JSONException e) {
            e.printStackTrace();
            ERROR_NUMBER = -1;
            ERROR_MESSAGE = "SYS0006";    // 系统错误:[解析响应报文失败]
        } catch (Exception e) {
            e.printStackTrace();
            ERROR_NUMBER = -1;
            ERROR_MESSAGE = "SYS0004";    // SYS0004：系统错误:[连接服务器失败]
        }

        return ERROR_NUMBER;
    }

    /**
     * 用户登录：用户重置密码
     *
     * @params: phoneNumber，loginPassword,payPassword,handPassword
     * @return: userNumber
     */
    public int userResetPassword(UserInfo userInfo) {
        try {
            // 生成请求报文
            JSONObject jsonRequestBody = new JSONObject();
            jsonRequestBody.put("phoneNumber", userInfo.getPhoneNumber());
            jsonRequestBody.put("loginPassword", userInfo.getLoginPassword());
            jsonRequestBody.put("payPassword", userInfo.getHandPassword());
            jsonRequestBody.put("handPassword", userInfo.getHandPassword());

            // 请求服务
            JSONObject jsonResponseBody = new ApiHander().comm(jsonRequestBody, "RESET_PASSWORD");
            if (jsonResponseBody == null) {
                return ERROR_NUMBER;
            }

            // 解析响应报文
            userInfo.setUserNumber(jsonResponseBody.getString("userNumber"));
        } catch (JSONException e) {
            e.printStackTrace();
            ERROR_NUMBER = -1;
            ERROR_MESSAGE = "SYS0006";    // 系统错误:[解析响应报文失败]
        } catch (Exception e) {
            e.printStackTrace();
            ERROR_NUMBER = -1;
            ERROR_MESSAGE = "SYS0004";    // SYS0004：系统错误:[连接服务器失败]
        }
        return ERROR_NUMBER;
    }
}
