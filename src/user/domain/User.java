package user.domain;

public class User {
    private String uid;
    private String username;
    private String password;
    private String verifyCode;
    private String code;
//    private boolean state;

    public User() {
    }

    public User(String uid, String username, String password, String verifyCode, String code) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.verifyCode = verifyCode;
        this.code = code;
//        this.state = state;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

//    public boolean isState() {
//        return state;
//    }
//
//    public void setState(boolean state) {
//        this.state = state;
//    }


    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
    //    public String toString() {
//        return "User{" +
//                "uid='" + uid + '\'' +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", email='" + verifyCode + '\'' +
//                ", code='" + code + '\'' +
//                ", state=" + state +
//                '}';
//    }
}
