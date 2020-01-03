package pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class UnReadMessage {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    String receiverId;
    String senderId;
    String msg;
    String sendTime;
    String receiverTime;
    int readState;
    int sendState;
    public UnReadMessage(String receiverId, String senderId, String msg, String sendTime, String receiverTime,int readState,int sendState) {
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.msg = msg;
        this.sendTime = sendTime;
        this.receiverTime = receiverTime;
    this.readState=readState;
    this.sendState=sendState;
    }

    public int getSendState() {
        return sendState;
    }

    public void setSendState(int sendState) {
        this.sendState = sendState;
    }

    public int getReadState() {
        return readState;
    }

    public void setReadState(int readState) {
        this.readState = readState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getReceiverTime() {
        return receiverTime;
    }

    public void setReceiverTime(String receiverTime) {
        this.receiverTime = receiverTime;
    }

    public UnReadMessage() {
    }
}
