package cn.rjgc.robot.bean;

import java.util.Date;

/**
 * Created by Don on 2016/8/5.
 */
public class ChatMessage {
    private String name;
    private String msg;
    private TYPE type;
    private Date date;

    public enum TYPE{
        INCOMING,OUTCOMING
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
