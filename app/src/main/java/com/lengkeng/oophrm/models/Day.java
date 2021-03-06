package com.lengkeng.oophrm.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Le Vinh Thien on 5/6/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class Day {
    public final static String NULL = "null";
    public final static String ALL_DAY = "cả ngày";
    public final static String HAFT_DAY = "nửa ngày";
    public final static String DAY_OFF_OK = "nghỉ có phép";
    public final static String DAY_OFF_CANCEL = "nghỉ không phép";

    private String title;
    private String timestamp;
    private int hours;

    public Day(int hours, String timestamp, String type) {
        this.hours = hours;
        this.timestamp = timestamp;
        this.title = type;
    }
    public Day(JSONObject obj){
        try {
            this.title = obj.has("title") ? obj.getString("title") : "null";
            this.timestamp = obj.has("timestamp") ? obj.getString("timestamp") : "";
            this.hours = obj.has("hours") ? obj.getInt("hours") : 0;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
