package com.example.ki_mobilalk;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class User {

    private  String uid;
    private String nickName;
    private String realName;

    private int value;
    private String dictateDate;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDictateDate() {
        return dictateDate;
    }

    public void setDictateDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dictateDate = dateFormat.format(calendar.getTime());
    }

    public User(String  id, String nickName, String realName) {
        this.uid = id;
        this.nickName = nickName;
        this.realName = realName;
        this.value =0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dictateDate = dateFormat.format(calendar.getTime());
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

}
