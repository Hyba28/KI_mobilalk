package com.example.ki_mobilalk;

import java.util.ArrayList;

public class User {

    private  String uid;
    private String nickName;
    private String realName;

    private ArrayList<GasMeter> gasMeters;

    public User(String  id, String nickName, String realName, ArrayList<GasMeter> gasMeters) {
        this.uid = id;
        this.nickName = nickName;
        this.realName = realName;
        this.gasMeters = gasMeters;
    }

    public String getId() {
        return uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public ArrayList<GasMeter> getGasMeters() {
        return gasMeters;
    }

    public void setGasMeters(ArrayList<GasMeter> gasMeters) {
        this.gasMeters = gasMeters;
    }
}
