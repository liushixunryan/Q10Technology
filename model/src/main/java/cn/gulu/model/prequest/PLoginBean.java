package cn.gulu.model.prequest;

public class PLoginBean {

    /**
     * AppType : AppType
     * SystemID : SystemID
     * LoginKey : LoginKey
     * phone : phone
     * Password : Password
     */

    private String AppType;
    private String SystemID;
    private String LoginKey;
    private String phone;
    private String Password;

    public String getAppType() {
        return AppType;
    }

    public void setAppType(String appType) {
        AppType = appType;
    }

    public String getSystemID() {
        return SystemID;
    }

    public void setSystemID(String systemID) {
        SystemID = systemID;
    }

    public String getLoginKey() {
        return LoginKey;
    }

    public void setLoginKey(String loginKey) {
        LoginKey = loginKey;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
