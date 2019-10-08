package cn.ytg.toolBox.entity;

public class User {
    private int userId;
    private String userName;
    private String password;
    private int userFlag;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(int userFlag) {
        this.userFlag = userFlag;
    }
}
