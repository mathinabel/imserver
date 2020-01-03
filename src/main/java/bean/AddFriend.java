package bean;

import java.util.Date;

public class AddFriend {

    private String fromUUID;
    private  String toUUID;
    private  String msg;
    public AddFriend(String fromUUID, String toUUID, String msg) {
        this.fromUUID = fromUUID;
        this.toUUID = toUUID;
        this.msg = msg;
    }

    public String getFromUUID() {
        return fromUUID;
    }

    public void setFromUUID(String fromUUID) {
        this.fromUUID = fromUUID;
    }

    public String getToUUID() {
        return toUUID;
    }

    public void setToUUID(String toUUID) {
        this.toUUID = toUUID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        Date date=new Date();
        return date.toString();
    }
}
