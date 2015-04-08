package com.wxz.freecard.bean;

/**
 * 用户信息：可用户注册、登录、信息维护等
 * Created by Renzo on 2015-03-25.
 */
public class UserInfo {
    private String userNumber;      // 用户编号
    private String userName;        // 姓名
    private String loginPassword;  // 登录密码
    private String payPassword;    // 支付密码
    private String handPassword;   // 手势密码
    private String userSex;         // 性别
    private String BirthDay;        // 生日
    private String headPicture;    // 头像
    private String phoneNumber;    // 手机号码
    private boolean phoneValid;   // 手机已验证
    private String identifyCode;   // 手机验证码
    private String mailAddress;    // 邮箱
    private boolean mailValid;    // 邮箱已验证
    private String userAddress;    // 地址
    private String userLevel;      // 用户等级
    private String effectDate;     // 会员生效日期
    private String expireDate;     // 会员失效日期
    private String userStatus;     // 用户状态
    private String registerDate;   // 注册日期
    private String registerCustom; // 注册商家

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getHandPassword() {
        return handPassword;
    }

    public void setHandPassword(String handPassword) {
        this.handPassword = handPassword;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String birthDay) {
        BirthDay = birthDay;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isPhoneValid() {
        return phoneValid;
    }

    public void setPhoneValid(boolean phoneValid) {
        this.phoneValid = phoneValid;
    }

    public String getIdentifyCode() { return identifyCode; }

    public void setIdentifyCode(String identifyCode) { this.identifyCode = identifyCode; }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public boolean isMailValid() {
        return mailValid;
    }

    public void setMailValid(boolean mailValid) {
        this.mailValid = mailValid;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterCustom() {
        return registerCustom;
    }

    public void setRegisterCustom(String registerCustom) {
        this.registerCustom = registerCustom;
    }
}
